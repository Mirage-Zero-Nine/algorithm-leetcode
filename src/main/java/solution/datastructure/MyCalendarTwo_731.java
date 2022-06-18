package solution.datastructure;

import java.util.Map;
import java.util.TreeMap;

/**
 * Implement a MyCalendarTwo class to store your events.
 * A new event can be added if adding the event will not cause a triple booking.
 * Your class will have one method, book(int start, int end).
 * Formally, this represents a half open interval [start, end), the range of real numbers x such that start <= x < end.
 * A triple booking happens when three events have some non-empty intersection.
 * For each call to the method MyCalendar.book, return true if the event can be added to the calendar successfully.
 * Otherwise, return false and do not add the event to the calendar.
 *
 * @author BorisMirage
 * Time: 2020/04/16 15:24
 * Created with IntelliJ IDEA
 */

public class MyCalendarTwo_731 {
    TreeMap<Integer, Integer> m;

    /**
     * Keep a tree map for previous book.
     * The key is the time (start time or end time), and the value is the number of event at this time.
     * The initial value for start time is 1, and initial value for end is -1.
     * In this way, the sum of all time points will be 0.
     * Add the time of booking into map first, then traverse the map.
     * If there is a part that the sum of value is large than 2, then it is triple booking.
     * Note that if the event can not be booked, then the point should be removed.
     */
    public MyCalendarTwo_731() {
        this.m = new TreeMap<>();
    }

    /**
     * Traverse the tree map.
     * If there is a part that the sum of value is larger than 2, then this event can not be added.
     *
     * @param start start time
     * @param end   end time
     * @return whether the event can be added to the calendar successfully
     */
    public boolean book(int start, int end) {
        m.put(start, m.getOrDefault(start, 0) + 1);     // initial value for start is 1
        m.put(end, m.getOrDefault(end, 0) - 1);         // initial value for end is -1
        int count = 0;

        for (Map.Entry<Integer, Integer> e : m.entrySet()) {
            count += e.getValue();
            if (count > 2) {
                m.put(start, m.get(start) - 1);
                m.put(end, m.get(end) + 1);
                m.remove(start, 0);     // remove previous added point (only when there is no other event)
                m.remove(end, 0);
                return false;
            }
        }

        return true;
    }
}
