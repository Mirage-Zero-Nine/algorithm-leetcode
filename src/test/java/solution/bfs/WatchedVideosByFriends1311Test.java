package solution.bfs;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author BorisMirage
 * Time: 2022/10/30 17:22
 * Created with IntelliJ IDEA
 */

public class WatchedVideosByFriends1311Test {
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
}