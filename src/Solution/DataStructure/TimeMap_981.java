package Solution.DataStructure;

import java.util.HashMap;
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
    private HashMap<String, TreeMap<Integer, String>> m = new HashMap<>();

    /**
     * Initialize of map.
     */
    public TimeMap_981() {
    }

    /**
     * Stores the key and value, along with the given timestamp.
     *
     * @param k given key
     * @param v given value
     * @param t given time stamp
     */
    public void set(String k, String v, int t) {
        if (!m.containsKey(k)) {
            m.put(k, new TreeMap<>());
        }

        m.get(k).put(t, v);
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
        if (!m.containsKey(k)) {
            return "";
        }
        HashMap.Entry<Integer, String> e = m.get(k).floorEntry(t);
        return (e == null) ? "" : e.getValue();
    }
}
