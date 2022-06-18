package solution.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * There are n different online courses numbered from 1 to n.
 * Each course has some duration(course length) t and closed on dth day.
 * A course should be taken continuously for t days and must be finished before or on the dth day.
 * You will start at the 1st day.
 * Given n online courses represented by pairs (t,d), find the maximal number of courses that can be taken.
 *
 * @author BorisMirage
 * Time: 2020/04/12 18:37
 * Created with IntelliJ IDEA
 */

public class ScheduleCourse_630 {
    /**
     * Sort the array by duration first. Then keep a max heap to store the duration.
     * Keep an integer to count the total days taken and traverse the sorted array. Add course's end to the max heap.
     * If day count is later than current course end date, poll out the top of heap and subtract this course's time.
     * This means this course should not be taken.
     *
     * @param courses given array that contains duration and close day
     * @return the maximal number of courses that can be taken
     */
    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, Comparator.comparingInt(a -> a[1]));       // sort array by duration

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);        // sort by taken time
        int time = 0;
        for (int[] c : courses) {
            time += c[0];
            pq.add(c[0]);
            if (time > c[1]) {      // if current course is not reachable
                time -= pq.poll();
            }
        }

        return pq.size();
    }
}
