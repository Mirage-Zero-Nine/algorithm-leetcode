package Solution.DataStructure;

import java.util.TreeMap;

/**
 * Implement a MyCalendar class to store your events.
 * A new event can be added if adding the event will not cause a double booking.
 * Your class will have the method, book(int start, int end).
 * Formally, this represents a half open interval [start, end), the range of real numbers x that start <= x < end.
 * A double booking happens when two events have some non-empty intersection.
 * For each call to the method, return true if the event can be added to the calendar successfully.
 * Otherwise, return false and do not add the event to the calendar.
 * Your class will be called like this: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)
 *
 * @author BorisMirage
 * Time: 2020/04/16 09:59
 * Created with IntelliJ IDEA
 */

public class MyCalendar_729 {
    TreeMap<Integer, Integer> m;        // key: start time, value: end time

    /**
     * Keep a tree map, the key is the start time and value is the end time of current event.
     */
    public MyCalendar_729() {
        this.m = new TreeMap<>();
    }

    /**
     * To add a new event, try to ceil the key of new event's end time.
     * If there is no such entry, then this event can be directly added.
     * If there is an entry, check if the end time of previous event is later than the start time of current event.
     *
     * @param start start time
     * @param end   end time
     * @return if new event can be added to the calendar successfully
     */
    public boolean book(int start, int end) {
        Integer previous = m.lowerKey(end);

        if (previous == null || m.get(previous) <= start) {
            m.put(start, end);
            return true;
        }

        return false;
    }
}
