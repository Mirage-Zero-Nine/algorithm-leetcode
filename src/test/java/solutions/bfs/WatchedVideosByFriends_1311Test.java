package solutions.bfs;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author BorisMirage
 * Time: 2022/10/30 17:22
 * Created with IntelliJ IDEA
 */

public class WatchedVideosByFriends_1311Test {
    private final WatchedVideosByFriends_1311 test = new WatchedVideosByFriends_1311();

    @Test
    public void test() {
        List<List<String>> videos = Lists.newArrayList(
                Lists.newArrayList("A", "B"),
                Lists.newArrayList("C"),
                Lists.newArrayList("B", "C"),
                Lists.newArrayList("D")
        );
        int[][] friends = new int[][]{{1, 2}, {0, 3}, {0, 3}, {1, 2}};
        List<String> expected = Lists.newArrayList("B", "C");
        assertIterableEquals(expected, test.watchedVideosByFriendsGraph(videos, friends, 0, 1));

        videos = Lists.newArrayList(
                Lists.newArrayList("A", "B"),
                Lists.newArrayList("C"),
                Lists.newArrayList("B", "C"),
                Lists.newArrayList("D")
        );
        friends = new int[][]{{1, 2}, {0, 3}, {0, 3}, {1, 2}};
        expected = Lists.newArrayList("D");
        assertIterableEquals(expected, test.watchedVideosByFriends(videos, friends, 0, 2));
    }

    @Test
    public void testHappyCases() {
        List<List<String>> videos = Lists.newArrayList(
                Lists.newArrayList("A"),
                Lists.newArrayList("B", "C"),
                Lists.newArrayList("B", "D")
        );
        int[][] friends = new int[][]{{1, 2}, {0}, {0}};
        assertIterableEquals(Lists.newArrayList("C", "D", "B"),
                test.watchedVideosByFriends(videos, friends, 0, 1));
        assertIterableEquals(Lists.newArrayList("C", "D", "B"),
                test.watchedVideosByFriendsGraph(videos, friends, 0, 1));

        videos = Lists.newArrayList(
                Lists.newArrayList("A"),
                Lists.newArrayList("B"),
                Lists.newArrayList("C"),
                Lists.newArrayList("D")
        );
        friends = new int[][]{{1}, {0, 2}, {1, 3}, {2}};
        assertIterableEquals(Lists.newArrayList("C"),
                test.watchedVideosByFriends(videos, friends, 0, 2));
        assertIterableEquals(Lists.newArrayList("C"),
                test.watchedVideosByFriendsGraph(videos, friends, 0, 2));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        List<List<String>> videos = Lists.newArrayList(
                Lists.newArrayList("Solo"),
                Lists.newArrayList("Friend")
        );
        int[][] friends = new int[][]{{1}, {0}};
        assertIterableEquals(Lists.newArrayList(),
                test.watchedVideosByFriends(videos, friends, 0, 3));
        assertIterableEquals(Lists.newArrayList(),
                test.watchedVideosByFriendsGraph(videos, friends, 0, 3));

        videos = Lists.newArrayList(
                Lists.newArrayList("A0"),
                Lists.newArrayList("A1"),
                Lists.newArrayList("A2")
        );
        friends = new int[][]{{1}, {0, 2}, {1}};
        assertIterableEquals(Lists.newArrayList("A0", "A2"),
                test.watchedVideosByFriends(videos, friends, 1, 1));
    }

    @Test
    public void testGiantCase() {
        List<List<String>> videos = Lists.newArrayList(
                Lists.newArrayList("self"),
                Lists.newArrayList("m1", "shared"),
                Lists.newArrayList("m2", "shared"),
                Lists.newArrayList("m3", "shared"),
                Lists.newArrayList("m4", "shared"),
                Lists.newArrayList("tail")
        );
        int[][] friends = new int[][]{
                {1, 2},
                {0, 3, 4},
                {0, 4},
                {1, 5},
                {1, 2, 5},
                {3, 4}
        };
        List<String> expected = Lists.newArrayList("m3", "m4", "shared");
        assertIterableEquals(expected, test.watchedVideosByFriends(videos, friends, 0, 2));
        assertIterableEquals(expected, test.watchedVideosByFriendsGraph(videos, friends, 0, 2));
    }

    @Test
    public void testLevel1DirectFriends() {
        List<List<String>> videos = Lists.newArrayList(
                Lists.newArrayList("A", "B"),
                Lists.newArrayList("C"),
                Lists.newArrayList("B", "C"),
                Lists.newArrayList("D")
        );
        int[][] friends = new int[][]{{1, 2}, {0, 3}, {0, 3}, {1, 2}};
        assertIterableEquals(Lists.newArrayList("B", "C"), test.watchedVideosByFriends(videos, friends, 0, 1));
    }

    @Test
    public void testAlphabeticalTieBreaking() {
        List<List<String>> videos = Lists.newArrayList(
                Lists.newArrayList("X"),
                Lists.newArrayList("B", "A"),
                Lists.newArrayList("C", "A")
        );
        int[][] friends = new int[][]{{1, 2}, {0}, {0}};
        // A appears 2 times, B and C appear 1 time each -> B, C (alphabetical), then A
        assertIterableEquals(Lists.newArrayList("B", "C", "A"), test.watchedVideosByFriends(videos, friends, 0, 1));
    }

    @Test
    public void testSinglePerson() {
        List<List<String>> videos = new java.util.ArrayList<>();
        videos.add(Lists.newArrayList("Movie"));
        int[][] friends = new int[][]{{}};
        assertIterableEquals(Lists.newArrayList(), test.watchedVideosByFriends(videos, friends, 0, 1));
    }

    @Test
    public void testLevel3Deep() {
        List<List<String>> videos = Lists.newArrayList(
                Lists.newArrayList("v0"),
                Lists.newArrayList("v1"),
                Lists.newArrayList("v2"),
                Lists.newArrayList("v3"),
                Lists.newArrayList("v4")
        );
        int[][] friends = new int[][]{{1}, {0, 2}, {1, 3}, {2, 4}, {3}};
        assertIterableEquals(Lists.newArrayList("v3"), test.watchedVideosByFriends(videos, friends, 0, 3));
    }

    @Test
    public void testDuplicateVideosAmongFriends() {
        List<List<String>> videos = Lists.newArrayList(
                Lists.newArrayList("X"),
                Lists.newArrayList("Z", "Z"),
                Lists.newArrayList("Z")
        );
        int[][] friends = new int[][]{{1, 2}, {0, 2}, {0, 1}};
        // Level 1 friends are 1 and 2; Z appears 3 times total
        assertIterableEquals(Lists.newArrayList("Z"), test.watchedVideosByFriends(videos, friends, 0, 1));
    }

    @Test
    public void testGraphMethodLevel2() {
        List<List<String>> videos = Lists.newArrayList(
                Lists.newArrayList("A", "B"),
                Lists.newArrayList("C"),
                Lists.newArrayList("B", "C"),
                Lists.newArrayList("D")
        );
        int[][] friends = new int[][]{{1, 2}, {0, 3}, {0, 3}, {1, 2}};
        assertIterableEquals(Lists.newArrayList("D"), test.watchedVideosByFriendsGraph(videos, friends, 0, 2));
    }
}
