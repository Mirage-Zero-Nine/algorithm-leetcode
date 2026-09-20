package solutions.design;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import org.junit.jupiter.api.Test;

/**
 * Behavioral tests for the stateful file-sharing system from LeetCode 1500.
 *
 * <p>The tests use fresh systems for independent scenarios and a separate
 * model based on ordered sets for long stateful sequences. The model does not
 * share the implementation's priority queue or hash-map representation.</p>
 */
public class FileSharing_1500Test {

    @Test
    public void testOfficialExampleSequence() {
        FileSharing_1500 fs = new FileSharing_1500(4);
        assertEquals(1, fs.join(List.of(1, 2)));
        assertEquals(2, fs.join(List.of(2, 3)));
        assertEquals(3, fs.join(List.of(4)));
        assertEquals(List.of(2), fs.request(1, 3));
        assertEquals(List.of(1, 2), fs.request(2, 2));
        fs.leave(1);
        assertEquals(List.of(), fs.request(2, 1));
        fs.leave(2);
        assertEquals(1, fs.join(List.of()));
    }

    @Test
    public void testHappyCases() {
        FileSharing_1500 fs = new FileSharing_1500(4);
        assertEquals(1, fs.join(List.of(1, 2)));
        assertEquals(2, fs.join(List.of(2, 3)));
        assertEquals(List.of(1, 2), fs.request(1, 2));
        fs.leave(1);
        assertEquals(1, fs.join(List.of(2, 4)));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        FileSharing_1500 fs = new FileSharing_1500(1);
        assertEquals(1, fs.join(List.of()));
        assertEquals(List.of(), fs.request(1, 1));
    }

    @Test
    public void testLargeCase() {
        FileSharing_1500 fs = new FileSharing_1500(5);
        int u1 = fs.join(List.of(1, 2, 3));
        int u2 = fs.join(List.of(3, 4, 5));
        List<Integer> result = fs.request(u1, 4);
        assertTrue(result.contains(u2));
    }

    @Test
    public void testIdsIncreaseMonotonicallyWithoutLeave() {
        FileSharing_1500 fs = new FileSharing_1500(3);
        assertEquals(1, fs.join(List.of()));
        assertEquals(2, fs.join(List.of()));
        assertEquals(3, fs.join(List.of()));
    }

    @Test
    public void testSmallestAvailableIdReusedAfterLeave() {
        FileSharing_1500 fs = new FileSharing_1500(3);
        int u1 = fs.join(List.of(1));
        int u2 = fs.join(List.of(2));
        fs.leave(u1);
        assertEquals(1, fs.join(List.of(3)));
        assertEquals(2, u2);
    }

    @Test
    public void testRequestReturnsSortedUserIds() {
        FileSharing_1500 fs = new FileSharing_1500(2);
        int u1 = fs.join(List.of(1));
        int u2 = fs.join(List.of(1));
        int u3 = fs.join(List.of(1));
        assertEquals(List.of(u1, u2, u3), fs.request(u2, 1));
    }

    @Test
    public void testRequestAddsChunkToRequesterWhenAvailable() {
        FileSharing_1500 fs = new FileSharing_1500(3);
        int u1 = fs.join(List.of(2));
        int u2 = fs.join(List.of());
        assertEquals(List.of(u1), fs.request(u2, 2));
        assertEquals(List.of(u1, u2), fs.request(u1, 2));
    }

    @Test
    public void testRequestNoOwnersDoesNotGrantChunk() {
        FileSharing_1500 fs = new FileSharing_1500(3);
        int u1 = fs.join(List.of());
        assertEquals(List.of(), fs.request(u1, 3));
        assertEquals(List.of(), fs.request(u1, 3));
    }

    @Test
    public void testLeaveRemovesUserFromChunkOwnership() {
        FileSharing_1500 fs = new FileSharing_1500(3);
        int u1 = fs.join(List.of(1, 2));
        int u2 = fs.join(List.of(2));
        fs.leave(u1);
        assertEquals(List.of(u2), fs.request(u2, 2));
    }

    @Test
    public void testGiantCaseManyUsersAndRequestsWithinContractBudget() {
        FileSharing_1500 fs = new FileSharing_1500(20);
        for (int i = 0; i < 998; i++) {
            fs.join(List.of((i % 20) + 1));
        }
        int requester = fs.join(List.of());
        List<Integer> owners = fs.request(requester, 10);
        assertEquals(50, owners.size());
        assertEquals(10, owners.get(0));
        assertEquals(990, owners.get(owners.size() - 1));
    }

    @Test
    public void testDuplicateChunksOnJoinAreStoredOnce() {
        FileSharing_1500 fs = new FileSharing_1500(3);
        int owner = fs.join(List.of(1, 1, 2, 2, 1));
        int requester = fs.join(List.of());
        assertEquals(List.of(owner), fs.request(requester, 1));
        assertEquals(List.of(owner), fs.request(requester, 2));
        assertEquals(List.of(owner, requester), fs.request(owner, 1));
    }

    @Test
    public void testRequestByExistingOwnerDoesNotDuplicateOwnership() {
        FileSharing_1500 fs = new FileSharing_1500(2);
        int first = fs.join(List.of(1));
        int second = fs.join(List.of(1));
        assertEquals(List.of(first, second), fs.request(first, 1));
        fs.leave(second);
        assertEquals(List.of(first), fs.request(first, 1));
    }

    @Test
    public void testRequestResultIsSnapshotBeforeRequesterGainsChunk() {
        FileSharing_1500 fs = new FileSharing_1500(2);
        int owner = fs.join(List.of(2));
        int requester = fs.join(List.of());
        List<Integer> firstResult = fs.request(requester, 2);
        assertEquals(List.of(owner), firstResult);
        firstResult.clear();
        assertEquals(List.of(owner, requester), fs.request(owner, 2));
    }

    @Test
    public void testNoOwnerRequestDoesNotCreateAnOwner() {
        FileSharing_1500 fs = new FileSharing_1500(2);
        int first = fs.join(List.of());
        int second = fs.join(List.of());
        assertEquals(List.of(), fs.request(first, 2));
        assertEquals(List.of(), fs.request(second, 2));
    }

    @Test
    public void testLeavingUserWithNoChunksStillReleasesId() {
        FileSharing_1500 fs = new FileSharing_1500(1);
        int emptyUser = fs.join(List.of());
        fs.leave(emptyUser);
        assertEquals(emptyUser, fs.join(List.of(1)));
        assertEquals(List.of(emptyUser), fs.request(emptyUser, 1));
    }

    @Test
    public void testSmallestAvailableIdWinsWhenSeveralUsersLeave() {
        FileSharing_1500 fs = new FileSharing_1500(3);
        assertEquals(1, fs.join(List.of()));
        assertEquals(2, fs.join(List.of()));
        assertEquals(3, fs.join(List.of()));
        assertEquals(4, fs.join(List.of()));
        fs.leave(3);
        fs.leave(1);
        fs.leave(2);
        assertEquals(1, fs.join(List.of()));
        assertEquals(2, fs.join(List.of()));
        assertEquals(3, fs.join(List.of()));
    }

    @Test
    public void testReusedIdDoesNotRetainFormerChunks() {
        FileSharing_1500 fs = new FileSharing_1500(3);
        int oldUser = fs.join(List.of(1, 2));
        int other = fs.join(List.of(3));
        fs.leave(oldUser);
        int reused = fs.join(List.of());
        assertEquals(oldUser, reused);
        assertEquals(List.of(), fs.request(other, 1));
        assertEquals(List.of(), fs.request(reused, 2));
    }

    @Test
    public void testLeavingOneOfManyOwnersKeepsTheOthersSorted() {
        FileSharing_1500 fs = new FileSharing_1500(1);
        int one = fs.join(List.of(1));
        int two = fs.join(List.of(1));
        int three = fs.join(List.of(1));
        fs.leave(two);
        assertEquals(List.of(one, three), fs.request(one, 1));
        fs.leave(one);
        assertEquals(List.of(three), fs.request(three, 1));
    }

    @Test
    public void testChunksRemainIndependent() {
        FileSharing_1500 fs = new FileSharing_1500(3);
        int first = fs.join(List.of(1));
        int second = fs.join(List.of(2));
        int requester = fs.join(List.of());
        assertEquals(List.of(first), fs.request(requester, 1));
        assertEquals(List.of(second), fs.request(requester, 2));
        assertEquals(List.of(), fs.request(requester, 3));
    }

    @Test
    public void testAcquiredChunkCanBeRelayedToAnotherUser() {
        FileSharing_1500 fs = new FileSharing_1500(1);
        int source = fs.join(List.of(1));
        int relay = fs.join(List.of());
        int third = fs.join(List.of());
        assertEquals(List.of(source), fs.request(relay, 1));
        assertEquals(List.of(source, relay), fs.request(third, 1));
    }

    @Test
    public void testMaximumChunkListAndMaximumOperationBudget() {
        FileSharing_1500 fs = new FileSharing_1500(1000);
        List<Integer> allChunks = range(1, 1000);
        int owner = fs.join(allChunks);
        int requester = fs.join(List.of());
        for (int chunk = 1; chunk <= 998; chunk++) {
            assertEquals(List.of(owner), fs.request(requester, chunk));
        }
        assertEquals(owner, 1);
        assertEquals(requester, 2);
    }

    @Test
    public void testMaximumNumberOfJoinCallsUsesDistinctIds() {
        FileSharing_1500 fs = new FileSharing_1500(1);
        for (int expected = 1; expected <= 1000; expected++) {
            assertEquals(expected, fs.join(List.of()));
        }
    }

    @Test
    public void testInputListMutationAfterJoinDoesNotChangeOwnership() {
        FileSharing_1500 fs = new FileSharing_1500(2);
        ArrayList<Integer> owned = new ArrayList<>(List.of(1));
        int owner = fs.join(owned);
        owned.clear();
        int requester = fs.join(List.of());
        assertEquals(List.of(owner), fs.request(requester, 1));
    }

    @Test
    public void testSeparateInstancesDoNotShareUsersOrChunks() {
        FileSharing_1500 first = new FileSharing_1500(2);
        FileSharing_1500 second = new FileSharing_1500(2);
        int firstUser = first.join(List.of(1));
        int secondUser = second.join(List.of());
        assertEquals(1, firstUser);
        assertEquals(1, secondUser);
        assertEquals(List.of(), second.request(secondUser, 1));
        assertEquals(List.of(firstUser), first.request(firstUser, 1));
    }

    @Test
    public void testSeededStatefulSequenceMatchesIndependentOracle() {
        final int chunks = 17;
        final int operations = 1000;
        FileSharing_1500 fs = new FileSharing_1500(chunks);
        ReferenceFileSharing oracle = new ReferenceFileSharing(chunks);
        Random random = new Random(1500L);

        for (int operation = 0; operation < operations; operation++) {
            if (oracle.activeUsers().isEmpty() || random.nextInt(100) < 40) {
                List<Integer> owned = randomOwnedChunks(random, chunks);
                assertEquals(oracle.join(owned), fs.join(owned), "join at operation " + operation);
            } else if (random.nextInt(100) < 65) {
                List<Integer> users = new ArrayList<>(oracle.activeUsers());
                int user = users.get(random.nextInt(users.size()));
                int chunk = 1 + random.nextInt(chunks);
                assertEquals(oracle.request(user, chunk), fs.request(user, chunk),
                        "request at operation " + operation);
            } else {
                List<Integer> users = new ArrayList<>(oracle.activeUsers());
                int user = users.get(random.nextInt(users.size()));
                oracle.leave(user);
                fs.leave(user);
            }
        }
    }

    @Test
    public void testSeededRepeatedJoinLeaveAndRequestsKeepStateIsolated() {
        FileSharing_1500 fs = new FileSharing_1500(5);
        ReferenceFileSharing oracle = new ReferenceFileSharing(5);
        Random random = new Random(42L);

        for (int cycle = 0; cycle < 80; cycle++) {
            List<Integer> owned = randomOwnedChunks(random, 5);
            assertEquals(oracle.join(owned), fs.join(owned));
            if (cycle % 3 == 0) {
                int user = 1 + random.nextInt(cycle + 1);
                if (oracle.activeUsers().contains(user)) {
                    int chunk = 1 + random.nextInt(5);
                    assertEquals(oracle.request(user, chunk), fs.request(user, chunk));
                }
            }
            if (cycle % 2 == 1) {
                List<Integer> users = new ArrayList<>(oracle.activeUsers());
                int user = users.get(random.nextInt(users.size()));
                oracle.leave(user);
                fs.leave(user);
            }
        }
        assertEquals(oracle.activeUsers().size(), fs.userToChunk.size());
    }

    private static List<Integer> randomOwnedChunks(Random random, int chunkCount) {
        ArrayList<Integer> owned = new ArrayList<>();
        for (int chunk = 1; chunk <= chunkCount; chunk++) {
            if (random.nextBoolean()) {
                owned.add(chunk);
            }
        }
        return owned;
    }

    private static List<Integer> range(int first, int last) {
        ArrayList<Integer> values = new ArrayList<>(last - first + 1);
        for (int value = first; value <= last; value++) {
            values.add(value);
        }
        return values;
    }

    /** Independent ordered-set model for stateful operation sequences. */
    private static final class ReferenceFileSharing {
        private final int chunkCount;
        private final Map<Integer, Set<Integer>> userToChunks = new HashMap<>();
        private final Map<Integer, Set<Integer>> chunkToUsers = new HashMap<>();
        private final TreeSet<Integer> availableIds = new TreeSet<>();
        private int nextId = 1;

        private ReferenceFileSharing(int chunkCount) {
            this.chunkCount = chunkCount;
        }

        private int join(List<Integer> ownedChunks) {
            int user = availableIds.isEmpty() ? nextId++ : availableIds.pollFirst();
            userToChunks.put(user, new HashSet<>(ownedChunks));
            for (int chunk : ownedChunks) {
                chunkToUsers.computeIfAbsent(chunk, ignored -> new TreeSet<>()).add(user);
            }
            return user;
        }

        private void leave(int user) {
            for (int chunk : userToChunks.remove(user)) {
                Set<Integer> owners = chunkToUsers.get(chunk);
                owners.remove(user);
                if (owners.isEmpty()) {
                    chunkToUsers.remove(chunk);
                }
            }
            availableIds.add(user);
        }

        private List<Integer> request(int user, int chunk) {
            TreeSet<Integer> owners = new TreeSet<>(chunkToUsers.getOrDefault(chunk, Set.of()));
            List<Integer> result = new ArrayList<>(owners);
            if (!owners.isEmpty()) {
                owners.add(user);
                userToChunks.get(user).add(chunk);
                chunkToUsers.computeIfAbsent(chunk, ignored -> new TreeSet<>()).add(user);
            }
            return result;
        }

        private Set<Integer> activeUsers() {
            return userToChunks.keySet();
        }
    }
}
