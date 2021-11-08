package Solution.DataStructure;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

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
    private final HashMap<String, TreeMap<Integer, String>> map;

    /**
     * Keep a hash map.
     * The key of hash map is the given key.
     * The value of the hash map is a tree map that sorts all value under same key by timestamp.
     */
    public TimeMap_981() {
        this.map = new HashMap<>();
    }

    /**
     * Stores the key and value, along with the given timestamp.
     *
     * @param k given key
     * @param v given value
     * @param t given time stamp
     */
    public void set(String k, String v, int t) {
        map.putIfAbsent(k, new TreeMap<>());
        map.get(k).put(t, v);
    }

    /**
     * Returns a value such that set(key, value, timestamp_prev) was called previously, with timestamp_prev <= timestamp.
     * If there are multiple such values, it returns the one with the largest timestamp_prev.
     * If there are no values, it returns the empty string ("").
     *
     * @param k given key
     * @param t given time stamp
     * @return a value such that set(key, value, timestamp_prev) was called previously
     */
    public String get(String k, int t) {
        if (!map.containsKey(k)) {
            return "";
        }
        Map.Entry<Integer, String> e = map.get(k).floorEntry(t);
        return (e == null) ? "" : e.getValue();
    }
}
