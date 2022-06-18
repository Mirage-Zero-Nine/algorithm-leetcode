package solution.intervals;

import library.Interval;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * We are given a list schedule of employees, which represents the working time for each employee.
 * Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.
 * Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.
 * (Even though we are representing Intervals in the form [x, y], the objects inside are Intervals, not lists or arrays.
 * For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined).
 * Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.
 *
 * @author BorisMirage
 * Time: 2019/12/02 18:56
 * Created with IntelliJ IDEA
 */

public class EmployeeFreeTime_759 {
    /**
     * Convert each interval's start and end to time point. Each time point contains time value and start/end.
     * Sort the time point list. Then it becomes to merge interval problem.
     *
     * @param schedule given interval list
     * @return the list of finite intervals representing common free time for all employees in sorted order
     */
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {

        List<Interval> out = new LinkedList<>();

        /* Corner case */
        if (schedule == null || schedule.size() == 0) {
            return out;
        }

        List<Point> list = new LinkedList<>();

        for (List<Interval> l : schedule) {
            for (Interval i : l) {
                list.add(new Point(i.start, true));
                list.add(new Point(i.end, false));
            }
        }

        Collections.sort(list);

        int count = 0;
        int start = Integer.MIN_VALUE, end;

        for (Point p : list) {
            if (p.isStart) {
                if (count++ == 0) {     // if count == 0, then there is no overlap here
                    end = p.val;        // if there is a new free interval, then current point should be end point
                    if (start > Integer.MIN_VALUE && start < end) {
                        out.add(new Interval(start, end));      // found a new free interval
                    }
                }
            } else {        // current point is the end of employee interval
                if (--count == 0) {     // if --count == 0, then there may be a new free interval based on next point
                    start = p.val;
                }
            }
        }

        return out;
    }

    /**
     * Time point with mark of start or end.
     */
    private static class Point implements Comparable<Point> {
        int val;
        boolean isStart;

        /**
         * Constructor of time point.
         *
         * @param val     value
         * @param isStart is current point representing a start of interval
         */
        Point(int val, boolean isStart) {
            this.val = val;
            this.isStart = isStart;
        }

        /**
         * Override the comparator.
         * If two points have different value, then compare the value.
         * Otherwise, end point has higher priority.
         *
         * @param p time point
         * @return priority of point
         */
        @Override
        public int compareTo(Point p) {
            if (this.val != p.val) {
                return this.val - p.val;
            } else {
                return this.isStart ? 1 : -1;
            }
        }
    }
}
