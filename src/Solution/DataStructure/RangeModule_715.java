package Solution.DataStructure;

import java.util.TreeMap;

/**
 * A Range Module is a module that tracks ranges of numbers.
 * Design a data structure to track the ranges represented as half-open intervals and query about them.
 * A half-open interval [left, right) denotes all the real numbers x where left <= x < right.
 * Implement the RangeModule class:
 * - RangeModule() Initializes the object of the data structure.
 * - void addRange(int left, int right) Adds the half-open interval [left, right), tracking every real number in that interval.
 * - boolean queryRange(int left, int right) Returns true if every number in the interval [left, right) is currently being tracked, and false otherwise.
 * - void removeRange(int left, int right) Stops tracking every real number currently being tracked in the half-open interval [left, right).
 *
 * @author BorisMirage
 * Time: 2021/10/17 15:25
 * Created with IntelliJ IDEA
 */

public class RangeModule_715 {
    TreeMap<Integer, Integer> map;

    /**
     * Use a tree map to store the interval. The start of interval is the key and the value is the end of this interval.
     * When adding new interval, floor the interval between start and end. Merge if there is an overlap.
     * Clear any fully overlapped interval to avoid duplication in map.
     * When query specific range, check if there is a range in map.
     * When remove an interval, find if there is a range in map that will be removed partly.
     * Add the remaining part to the map first, then remove the interval.
     */
    public RangeModule_715() {
        map = new TreeMap<>();
    }

    /**
     * Add a new interval to the map.
     * Floor the left and right in map to see if there is an existing interval that has an overlap.
     * If there is an overlap, merge them. Then put the new interval to the map.
     * After add the interval to the map, remove any fully overlapped interval in the map.
     *
     * @param left  start of the interval
     * @param right end of the interval, not included
     */
    public void addRange(int left, int right) {

        /* Corner case */
        if (right < left) {
            return;
        }

        Integer startInterval = map.floorKey(left), endInterval = map.floorKey(right);

        if (startInterval != null && map.get(startInterval) >= left) { // left included in existing interval
            left = startInterval; // merge interval if there is an overlap
        }
        if (endInterval != null && map.get(endInterval) > right) { // right is larger than existing interval
            right = map.get(endInterval);
        }

        map.put(left, right);
        map.subMap(left, false, right, true).clear(); // remove all the intermediate intervals
    }

    /**
     * Check if a specific interval exists in the map.
     *
     * @param left  start of the query range
     * @param right end of the query range
     * @return return true if every number in the interval [left, right) is currently being tracked, and false otherwise
     */
    public boolean queryRange(int left, int right) {
        Integer start = map.floorKey(left);

        return start != null && map.get(start) >= right;
    }

    /**
     * Remove a specific range from the map.
     * Similar to the adding operation.
     * The difference is that there may be an interval in the map that will only be partly removed.
     * Hence, add the remaining part to the map first, then remove the requested interval.
     *
     * @param left  start of the query range
     * @param right end of the query range
     */
    public void removeRange(int left, int right) {

        /* Corner case */
        if (right < left) {
            return;
        }

        Integer start = map.floorKey(left), end = map.floorKey(right);
        if (end != null && map.get(end) > right) { // end should be first, since end is not included to be removed
            map.put(right, map.get(end));
        }
        if (start != null && map.get(start) > left) {
            map.put(start, left);
        }

        // remove all the intermediate intervals within range
        map.subMap(left, true, right, false).clear();
    }
}
