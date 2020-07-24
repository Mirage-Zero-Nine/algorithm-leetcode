package Solution.Intervals;

import java.util.*;

/**
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei).
 * Find the minimum number of conference rooms required.
 *
 * @author BorisMirage
 * Time: 2019/06/28 17:35
 * Created with IntelliJ IDEA
 */
public class MinMeetingRooms_253 {
    /**
     * Define a "time point" to represent each meeting's start and end time.
     * Then sort the list that contains all time points of meeting.
     * Iterate all points in list. If there is continues start point, then there is overlap.
     * Otherwise, one meeting is over and the room is cleaned for next meeting (see comparator for details).
     *
     * @param intervals given time interval
     * @return minimum non-overlapped number
     */
    public int minMeetingRooms(int[][] intervals) {

        /* Corner case */
        if (intervals == null) {
            return 0;
        }
        if (intervals.length <= 1) {
            return intervals.length;
        }

        List<Point> list = new LinkedList<>();
        for (int[] i : intervals) {
            list.add(new Point(i[0], true));
            list.add(new Point(i[1], false));
        }

        Collections.sort(list);

        int count = 0, max = 0;
        for (Point p : list) {
            if (p.isStart) {     // there is overlap
                count++;
            } else {            // one meeting is over, no overlap here
                count--;
            }

            max = Math.max(max, count);
        }

        return max;
    }

    /**
     * Point that represent each time stamp.
     * Each point has following fields:
     * val: time of current point.
     * isEnd: is current time a end time.
     */
    static class Point implements Comparable<Point> {
        int val;
        boolean isStart;

        /**
         * Constructor of time point.
         *
         * @param val     time of current point
         * @param isStart is current time a end time
         */
        Point(int val, boolean isStart) {
            this.val = val;
            this.isStart = isStart;
        }

        /**
         * Compare two points.
         * If they have different time, then point with earlier time has higher priority.
         * Otherwise, end point has higher priority.
         * Because if a end point and start point has same time, no extra room is required in this problem.
         *
         * @param p other point
         * @return -1, 0, or 1 as the first argument is less than, equal to, or greater than the second
         */
        @Override
        public int compareTo(Point p) {
            if (this.val != p.val) {
                return this.val - p.val;
            }

            return this.isStart ? 1 : -1;       // end point should be put first
        }
    }

    /**
     * Solution II.
     * Since it is only the end time conflict that requires one more room, therefore, use a heap to save ending time.
     * They are several cases:
     * 1. Heap is empty
     * 2. Two intervals has same start
     * 3. Interval ends with a time smaller than top of heap (start before the other end)
     * 4. Interval ends with a time larger than top of heap (start after the end)
     * 5. Interval ends with a time smaller than top of heap
     * For situation 1,2,3, add current interval end to heap and one more room is required.
     * For situation 4, their is no conflict, hence add the end time to heap.
     * For situation 5, assume no extra room is required, continue, do nothing.
     *
     * @param intervals given time interval
     * @return minimum non-overlapped number
     */
    public int heap(int[][] intervals) {

        /* Corner case */
        if (intervals.length < 2) {
            return (intervals.length == 0) ? 0 : 1;
        }

        Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));     // sort based on intervals[i][0]
        int count = 0;
        PriorityQueue<Integer> q = new PriorityQueue<>();

        for (int i = 0; i < intervals.length; i++) {
            if (q.isEmpty() || intervals[i][0] < q.peek() || intervals[i][0] == intervals[i - 1][0]) {
                q.offer(intervals[i][1]);
                count++;
            } else if (intervals[i][0] > q.peek()) {
                q.poll();
                q.offer(intervals[i][1]);
            }
        }

        return count;
    }

    /**
     * Solution III.
     * Sort start time and end time separately.
     * Then one pointer point at start time, one point at end time.
     * If start time < end time, then one more room is required.
     * Otherwise, there is no conflict, move end time pointer forward.
     *
     * @param intervals given time interval
     * @return minimum non-overlapped number
     */
    public int twoPointers(int[][] intervals) {
        int[] starts = new int[intervals.length];
        int[] ends = new int[intervals.length];

        for (int i = 0; i < intervals.length; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }

        Arrays.sort(starts);
        Arrays.sort(ends);
        int rooms = 0, end = 0;

        for (int start : starts) {
            if (start < ends[end]) {
                rooms++;
            } else {
                end++;
            }
        }

        return rooms;
    }

    public static void main(String[] args) {
        MinMeetingRooms_253 test = new MinMeetingRooms_253();
        System.out.println(test.minMeetingRooms(new int[][]{{0, 30}, {5, 10}, {15, 20}}));
        System.out.println(test.minMeetingRooms(new int[][]{{0, 5}, {5, 10}, {15, 20}}));
        System.out.println(test.minMeetingRooms(new int[][]{{7, 10}, {2, 4}, {15, 20}}));
        System.out.println(test.minMeetingRooms(new int[][]{{0, 30}, {0, 10}, {9, 20}}));
        System.out.println(test.minMeetingRooms(new int[][]{{6, 10}, {13, 14}, {12, 14}}));
        System.out.println(test.minMeetingRooms(new int[][]{{26, 29}, {19, 26}, {19, 28}, {4, 19}, {4, 25}}));      // 3
    }
}
