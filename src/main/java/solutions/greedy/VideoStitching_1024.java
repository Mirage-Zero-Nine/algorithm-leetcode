package solutions.greedy;
import java.util.Arrays;
import java.util.TreeMap;

/**
 * You are given a series of video clips from a sporting event that lasted T seconds.
 * These video clips can be overlapping with each other and have varied lengths.
 * Each video clip clips[i] is an interval:
 * Starts at time clips[i][0] and ends at time clips[i][1]. We can cut these clips into segments freely.
 * Return the minimum number of clips needed so that to cut the clips into segments cover the entire ([0, T]).
 * If the task is impossible, return -1.
 *
 * @author BorisMirage
 * Time: 2019/09/20 11:27
 * Created with IntelliJ IDEA
 */

public class VideoStitching_1024 {
    /**
     * Greedy.
     * Sort the clips by starting time first.
     * Then track current stitching position.
     * For each iteration, check all overlapping clips.
     * Pick the one that advances stitching position the furthest (find longest one).
     * Time: O(nlogn), where n is the number of clips. Sort all clips, then processing each clip only once.
     *
     * @param clips given clips array
     * @param T     time range
     * @return minimum number of clips needed
     */
    public int videoStitching(int[][] clips, int T) {

        // sort array based on start time
        Arrays.sort(clips, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });

        int i = 0, start = 0, end = 0, min = 0;
        while (start < T) {

            min++;      // select one more clip
            while (i < clips.length && clips[i][0] <= start) {
                end = Math.max(end, clips[i++][1]);     // select furthest end time
            }

            if (start == end) {
                return -1;      // if end time can not be moving forward, then T is not reachable
            }

            start = end;        // mark selected end time as start
        }

        return min;
    }

    private int min = Integer.MAX_VALUE;

    /**
     * Use backtracking to find all possible path from 0 to T. Then find a min one.
     *
     * @param clips given clips array
     * @param T     time range
     * @return minimum number of clips needed
     */
    public int backtracking(int[][] clips, int T) {

        int n = clips.length;
        TreeMap<Integer, Integer> m = new TreeMap<>();
        Arrays.sort(clips, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });

        for (int i = 0; i < n; i++) {
            m.put(clips[i][0], i);
        }

        for (int i = 0; i < n && clips[i][0] == 0; i++) {
            dfs(m, clips, i, T, 1);
        }

        return (min == Integer.MAX_VALUE) ? -1 : min;
    }

    /**
     * Backtracking to find all possible paths.
     *
     * @param m     tree map stores all start time with index
     * @param clips given clips array
     * @param index start location
     * @param t     given total time
     * @param l     number of clips taken
     */
    private void dfs(TreeMap<Integer, Integer> m, int[][] clips, int index, int t, int l) {

        if (l < min && clips[index][1] >= t) {
            min = Math.min(min, l);
            return;
        }
        if (l > min) {
            return;
        }

        Integer next = m.floorKey(clips[index][1]);
        if (next != null && next <= clips[index][1]) {
            for (int i = m.get(next); i > index; i--) {
                dfs(m, clips, i, t, l + 1);
            }
        }
    }

}
