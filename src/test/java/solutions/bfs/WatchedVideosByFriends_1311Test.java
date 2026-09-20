package solutions.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.junit.jupiter.api.Test;

/**
 * Contract tests for LeetCode 1311, including both BFS implementations.
 *
 * <p>The expected values are calculated by an independent shortest-path BFS
 * oracle rather than by comparing the two implementations with each other.
 */
public class WatchedVideosByFriends_1311Test {
    private final WatchedVideosByFriends_1311 solution = new WatchedVideosByFriends_1311();

    @Test
    void officialExampleLevelOne() {
        assertBoth(
                videos("A,B", "C", "B,C", "D"),
                friends(new int[]{1, 2}, new int[]{0, 3}, new int[]{0, 3}, new int[]{1, 2}),
                0, 1);
    }

    @Test
    void officialExampleLevelTwo() {
        assertBoth(
                videos("A,B", "C", "B,C", "D"),
                friends(new int[]{1, 2}, new int[]{0, 3}, new int[]{0, 3}, new int[]{1, 2}),
                0, 2);
    }

    @Test
    void directFriendsAreOrderedByFrequencyThenAlphabetically() {
        assertBoth(
                videos("self", "B,A", "C,A", "D,D"),
                friends(new int[]{1, 2, 3}, new int[]{0}, new int[]{0}, new int[]{0}),
                0, 1);
    }

    @Test
    void deepChainFindsTheExactRequestedLevel() {
        assertBoth(
                videos("v0", "v1", "v2", "v3", "v4", "v5"),
                friends(new int[]{1}, new int[]{0, 2}, new int[]{1, 3},
                        new int[]{2, 4}, new int[]{3, 5}, new int[]{4}),
                0, 4);
    }

    @Test
    void cyclesDoNotRevisitEarlierLevels() {
        assertBoth(
                videos("self", "one", "two", "three", "four"),
                friends(new int[]{1, 4}, new int[]{0, 2}, new int[]{1, 3},
                        new int[]{2, 4}, new int[]{3, 0}),
                0, 2);
    }

    @Test
    void aShorterPathWinsWhenAPathAlsoAppearsLater() {
        assertBoth(
                videos("self", "direct", "via-two", "target"),
                friends(new int[]{1, 2}, new int[]{0, 2, 3}, new int[]{0, 1, 3}, new int[]{1, 2}),
                0, 2);
    }

    @Test
    void nonZeroSourceIdIsHandled() {
        assertBoth(
                videos("zero", "one", "source", "three", "four"),
                friends(new int[]{1}, new int[]{0, 2}, new int[]{1, 3},
                        new int[]{2, 4}, new int[]{3}),
                2, 1);
    }

    @Test
    void levelZeroReturnsOnlyTheSourceVideos() {
        assertBoth(
                videos("self", "friend", "other"),
                friends(new int[]{1}, new int[]{0, 2}, new int[]{1}),
                1, 0);
    }

    @Test
    void noPeopleAtRequestedLevelReturnsAnEmptyList() {
        assertBoth(
                videos("self", "friend", "tail"),
                friends(new int[]{1}, new int[]{0, 2}, new int[]{1}),
                0, 4);
    }

    @Test
    void emptyVideoListsProduceNoVideos() {
        assertBoth(
                videos("self", "", "", "target"),
                friends(new int[]{1, 2}, new int[]{0, 3}, new int[]{0, 3}, new int[]{1, 2}),
                0, 1);
    }

    @Test
    void videosMayHaveNoOverlap() {
        assertBoth(
                videos("self", "alpha", "beta", "gamma"),
                friends(new int[]{1, 2, 3}, new int[]{0}, new int[]{0}, new int[]{0}),
                0, 1);
    }

    @Test
    void duplicateEntriesInOneListFollowTheImplementationFrequencyContract() {
        assertBoth(
                videos("self", "Z,Z", "Z", "A"),
                friends(new int[]{1, 2}, new int[]{0, 3}, new int[]{0, 3}, new int[]{1, 2}),
                0, 1);
    }

    @Test
    void lexicalTieBreakingUsesNaturalStringOrder() {
        assertBoth(
                videos("self", "aa,b,B", "c,B", "z"),
                friends(new int[]{1, 2}, new int[]{0, 3}, new int[]{0, 3}, new int[]{1, 2}),
                0, 1);
    }

    @Test
    void aVideoWatchedByEveryTargetPersonHasItsTotalFrequency() {
        assertBoth(
                videos("self", "shared,rare1", "shared,rare2", "shared,rare3"),
                friends(new int[]{1, 2, 3}, new int[]{0}, new int[]{0}, new int[]{0}),
                0, 1);
    }

    @Test
    void allFriendsCanBeAtTheSameTargetLevel() {
        List<List<String>> watched = videos("self", "a,x", "b,x", "c,x", "d,x");
        int[][] graph = friends(new int[]{1, 2, 3, 4}, new int[]{0}, new int[]{0}, new int[]{0}, new int[]{0});
        assertBoth(watched, graph, 0, 1);
    }

    @Test
    void denseMaximumSizedStarExercisesManyFrequencyCounts() {
        int n = 100;
        List<List<String>> watched = new ArrayList<>();
        int[][] graph = new int[n][];
        int[] allFriends = new int[n - 1];
        for (int i = 1; i < n; i++) {
            allFriends[i - 1] = i;
        }
        watched.add(new ArrayList<>(List.of("source")));
        graph[0] = allFriends;
        for (int i = 1; i < n; i++) {
            watched.add(new ArrayList<>(List.of("v" + i, "shared")));
            graph[i] = new int[]{0};
        }
        assertBoth(watched, graph, 0, 1);
    }

    @Test
    void maximumLengthChainReachesLevelNMinusOne() {
        int n = 100;
        List<List<String>> watched = new ArrayList<>();
        int[][] graph = new int[n][];
        for (int i = 0; i < n; i++) {
            watched.add(new ArrayList<>(List.of("v" + i)));
            if (i == 0) {
                graph[i] = new int[]{1};
            } else if (i == n - 1) {
                graph[i] = new int[]{n - 2};
            } else {
                graph[i] = new int[]{i - 1, i + 1};
            }
        }
        assertBoth(watched, graph, 0, n - 1);
    }

    @Test
    void disconnectedComponentsDoNotContributeVideos() {
        assertBoth(
                videos("source", "near", "far", "other-source", "other-far"),
                friends(new int[]{1}, new int[]{0, 2}, new int[]{1}, new int[]{4}, new int[]{3}),
                0, 2);
    }

    @Test
    void sameTargetPersonReachedBySeveralParentsIsCountedOnce() {
        assertBoth(
                videos("source", "near-one", "near-two", "target,target"),
                friends(new int[]{1, 2}, new int[]{0, 3}, new int[]{0, 3}, new int[]{1, 2}),
                0, 2);
    }

    @Test
    void everyTargetPersonIsIncludedEvenWhenTheirOrderIsNotInputOrder() {
        assertBoth(
                videos("s", "one", "two", "three", "four"),
                friends(new int[]{2, 1}, new int[]{0, 3}, new int[]{0, 4}, new int[]{1}, new int[]{2}),
                0, 2);
    }

    @Test
    void repeatedCallsOnOneInstanceDoNotLeakTraversalState() {
        List<List<String>> firstVideos = videos("s", "first", "first-two");
        int[][] firstFriends = friends(new int[]{1}, new int[]{0, 2}, new int[]{1});
        assertBoth(firstVideos, firstFriends, 0, 1);
        assertBoth(videos("x", "second", "third"), firstFriends, 2, 1);
        assertBoth(firstVideos, firstFriends, 0, 2);
    }

    @Test
    void mutatingOneReturnedListDoesNotChangeLaterResults() {
        List<List<String>> watched = videos("s", "b,a", "c");
        int[][] graph = friends(new int[]{1, 2}, new int[]{0}, new int[]{0});
        List<String> first = solution.watchedVideosByFriends(watched, copy(graph), 0, 1);
        first.clear();
        assertEquals(oracle(watched, graph, 0, 1),
                solution.watchedVideosByFriends(watched, copy(graph), 0, 1));
        List<String> second = solution.watchedVideosByFriendsGraph(watched, copy(graph), 0, 1);
        second.add("not-an-input-video");
        assertEquals(oracle(watched, graph, 0, 1),
                solution.watchedVideosByFriendsGraph(watched, copy(graph), 0, 1));
    }

    @Test
    void inputsAreNotMutatedByEitherApproach() {
        List<List<String>> watched = videos("s", "b,a", "c");
        int[][] graph = friends(new int[]{1, 2}, new int[]{0}, new int[]{0});
        List<List<String>> watchedBefore = deepCopy(watched);
        int[][] graphBefore = copy(graph);
        assertBoth(watched, graph, 0, 1);
        assertEquals(watchedBefore, watched);
        assertEquals(Arrays.deepToString(graphBefore), Arrays.deepToString(graph));
    }

    @Test
    void isolatedSourceHasNoLevelOneVideos() {
        // This is valid under the problem's friends[i].length >= 0 constraint.
        assertBoth(
                videos("isolated", "other", "component"),
                friends(new int[]{}, new int[]{2}, new int[]{1}),
                0, 1);
    }

    @Test
    void emptySourceVideosAreReturnedAsEmptyAtLevelZero() {
        assertBoth(
                videos("", "friend"),
                friends(new int[]{1}, new int[]{0}),
                0, 0);
    }

    @Test
    void levelBeyondTheLongestChainRemainsEmpty() {
        assertBoth(
                videos("s", "one", "two"),
                friends(new int[]{1}, new int[]{0, 2}, new int[]{1}),
                1, 99);
    }

    @Test
    void duplicateNamesAcrossDifferentPeopleAreCountedByPeople() {
        assertBoth(
                videos("s", "same,unique-one", "same,unique-two", "same,unique-three"),
                friends(new int[]{1, 2, 3}, new int[]{0}, new int[]{0}, new int[]{0}),
                0, 1);
    }

    private void assertBoth(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        List<String> expected = oracle(watchedVideos, friends, id, level);
        assertEquals(expected,
                solution.watchedVideosByFriends(deepCopy(watchedVideos), copy(friends), id, level),
                "direct BFS");
        assertEquals(expected,
                solution.watchedVideosByFriendsGraph(deepCopy(watchedVideos), copy(friends), id, level),
                "explicit graph BFS");
    }

    private static List<String> oracle(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        int[] distance = new int[friends.length];
        Arrays.fill(distance, -1);
        Queue<Integer> queue = new ArrayDeque<>();
        distance[id] = 0;
        queue.add(id);
        while (!queue.isEmpty()) {
            int person = queue.remove();
            if (distance[person] == level) {
                continue;
            }
            for (int friend : friends[person]) {
                if (distance[friend] == -1) {
                    distance[friend] = distance[person] + 1;
                    queue.add(friend);
                }
            }
        }

        Map<String, Integer> frequencies = new HashMap<>();
        for (int person = 0; person < distance.length; person++) {
            if (distance[person] == level) {
                for (String video : watchedVideos.get(person)) {
                    frequencies.merge(video, 1, Integer::sum);
                }
            }
        }
        List<String> result = new ArrayList<>(frequencies.keySet());
        result.sort((left, right) -> {
            int byFrequency = Integer.compare(frequencies.get(left), frequencies.get(right));
            return byFrequency != 0 ? byFrequency : left.compareTo(right);
        });
        return result;
    }

    private static List<List<String>> videos(String... people) {
        List<List<String>> result = new ArrayList<>();
        for (String person : people) {
            if (person.isEmpty()) {
                result.add(new ArrayList<>());
            } else {
                result.add(new ArrayList<>(Arrays.asList(person.split(","))));
            }
        }
        return result;
    }

    private static int[][] friends(int[]... people) {
        return copy(people);
    }

    private static List<List<String>> deepCopy(List<List<String>> input) {
        List<List<String>> copy = new ArrayList<>();
        for (List<String> person : input) {
            copy.add(new ArrayList<>(person));
        }
        return copy;
    }

    private static int[][] copy(int[][] input) {
        int[][] copy = new int[input.length][];
        for (int i = 0; i < input.length; i++) {
            copy[i] = Arrays.copyOf(input[i], input[i].length);
        }
        return copy;
    }
}
