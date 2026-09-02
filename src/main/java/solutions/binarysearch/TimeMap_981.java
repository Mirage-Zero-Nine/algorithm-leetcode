package solutions.binarysearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create a timebased key-value store class TimeMap, that supports two operations.
 * 1. set(string key, string value, int timestamp): Stores the key and value, along with the given timestamp.
 * 2. get(string key, int timestamp): Returns a value such that set(key, value, timestamp_prev) was called previously, with timestamp_prev <= timestamp.
 * If there are multiple such values, it returns the one with the largest timestamp_prev.
 * If there are no values, it returns the empty string ("").
 *
 * @author BorisMirage
 * Time: 2019/07/27 15:03
 * Created with IntelliJ IDEA
 */
public class TimeMap_981 {
    private final Map<String, List<Entry>> map;

    /**
     * Creates an empty time-based key-value store.
     */
    public TimeMap_981() {
        map = new HashMap<>();
    }

    /**
     * Stores a value for a key at the given timestamp.
     *
     * <p>The value is appended rather than inserted into a sorted collection.
     * This is valid because LeetCode guarantees that all {@code set} timestamps
     * are strictly increasing, so timestamps for an individual key are also in
     * increasing order.
     *
     * @param key       key to update
     * @param value     value to store
     * @param timestamp timestamp associated with the value
     */
    public void set(String key, String value, int timestamp) {
        map.computeIfAbsent(key, _ -> new ArrayList<>())
                .add(new Entry(timestamp, value));
    }

    /**
     * Returns the value stored at the latest timestamp not greater than the
     * requested timestamp.
     *
     * @param key       key to look up
     * @param timestamp requested timestamp
     * @return the value associated with the greatest stored timestamp less than
     * or equal to {@code timestamp}, or {@code ""} when no such value
     * exists
     */
    public String get(String key, int timestamp) {
        List<Entry> list = map.get(key);
        if (list == null) {
            return "";
        }

        return binarySearch(list, timestamp);
    }

    /**
     * Finds the value at the greatest timestamp that is less than or equal to
     * the requested timestamp.
     *
     * <p>The list is sorted by timestamp. {@code result} stores the best valid
     * candidate found so far. At each midpoint:
     * <ul>
     *     <li>An exact timestamp is the answer, so the search can stop.</li>
     *     <li>A timestamp before the request is a valid candidate, but a later
     *         candidate may exist in the right half.</li>
     *     <li>A timestamp after the request cannot be used, so the search moves
     *         to the left half.</li>
     * </ul>
     *
     * @param list      timestamped values for one key, sorted by timestamp
     * @param timestamp requested timestamp
     * @return the value at the greatest timestamp less than or equal to the
     * request, or {@code ""} if every stored timestamp is greater
     * @implNote Runs in {@code O(log n)} time and {@code O(1)} auxiliary space,
     * where {@code n} is the number of values stored for the key.
     */
    private String binarySearch(List<Entry> list, int timestamp) {
        int left = 0, right = list.size() - 1;
        String result = "";

        while (left <= right) {
            int pivot = left + (right - left) / 2;
            Entry entry = list.get(pivot);

            if (entry.timestamp() == timestamp) {
                // An exact match is the latest possible valid timestamp.
                return entry.value();
            }

            if (entry.timestamp() < timestamp) {
                // This entry is valid; search right for a newer valid entry.
                result = entry.value();
                left = pivot + 1;
            } else {
                // This entry is too recent; discard it and search left.
                right = pivot - 1;
            }
        }

        return result;
    }

    /**
     * Immutable value and timestamp pair stored in a key's history.
     */
    private record Entry(int timestamp, String value) {
    }
}
