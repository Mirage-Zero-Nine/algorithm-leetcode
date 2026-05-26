package solutions.design;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

public class FileSharing_1500Test {

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
    public void testGiantCaseManyUsersAndRequests() {
        FileSharing_1500 fs = new FileSharing_1500(20);
        for (int i = 0; i < 1000; i++) {
            fs.join(List.of((i % 20) + 1));
        }
        int requester = fs.join(List.of());
        List<Integer> owners = fs.request(requester, 10);
        assertTrue(owners.size() >= 50);
        assertTrue(owners.get(0) >= 1);
    }
}
