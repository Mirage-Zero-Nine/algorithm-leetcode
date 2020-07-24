package Solution.DataStructure;

import java.util.HashMap;
import java.util.LinkedHashSet;

/**
 * Design and implement a data structure for Least Frequently Used (LFU) cache.
 * It should support the following operations: get and put.
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present.
 * When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item.
 * When there is a tie (i.e., two or more keys that have the same frequency), the LRU key would be evicted.
 *
 * @author BorisMirage
 * Time: 2018/09/29 20:59
 * Created with IntelliJ IDEA
 */

public class LFUCache_460 {
    private final HashMap<Integer, Integer> pair;           // store key-value pair
    private final HashMap<Integer, Integer> frequency;      // store frequency as key-count pair
    private final HashMap<Integer, LinkedHashSet<Integer>> keyUnderSameFrequency;        // key: frequency, value: cache key under this frequency
    private int min = -1;
    private final int capacity;

    /**
     * Init the cache.
     *
     * @param capacity cache capacity
     */
    public LFUCache_460(int capacity) {
        this.capacity = capacity;
        this.pair = new HashMap<>();
        this.frequency = new HashMap<>();
        this.keyUnderSameFrequency = new HashMap<>();
        this.keyUnderSameFrequency.put(1, new LinkedHashSet<>());     // first value
    }

    /**
     * <code>get()</code> method.
     * Return -1 if key is not found in cache.
     * Use a hash map to record the frequency.
     *
     * @param key requesting key
     * @return corresponding value, or -1.
     */
    public int get(int key) {

        if (!this.pair.containsKey(key)) {
            return -1;      // key does not exist
        }
        int frequency = this.frequency.get(key);
        this.frequency.put(key, frequency + 1);      // update frequency

        this.keyUnderSameFrequency.get(frequency).remove(key);        // current key's frequency has updated, recreate key-frequency pair
        if (frequency == this.min && this.keyUnderSameFrequency.get(frequency).size() == 0) {
            this.min++;      // Update min if min frequency in map has changed
        }
        if (!this.keyUnderSameFrequency.containsKey(frequency + 1)) {
            this.keyUnderSameFrequency.put(frequency + 1, new LinkedHashSet<>());
        }
        this.keyUnderSameFrequency.get(frequency + 1).add(key);

        return this.pair.get(key);
    }

    /**
     * <code>put()</code> method, put new key-value pair into cache.
     * If cache is oversize, it will remove Least Recently Used (LRU) Cache store in cache.
     *
     * @param key   new key
     * @param value new value
     */
    public void put(int key, int value) {

        if (this.capacity < 1) {
            return;
        }

        if (this.pair.containsKey(key)) {
            this.pair.put(key, value);
            get(key);
            return;
        }
        if (this.pair.size() >= this.capacity) {
            int removeLeast = this.keyUnderSameFrequency.get(this.min).iterator().next();
            this.keyUnderSameFrequency.get(this.min).remove(removeLeast);
            this.frequency.remove(removeLeast);
            this.pair.remove(removeLeast);
        }

        this.pair.put(key, value);
        this.frequency.put(key, 1);
        this.min = 1;
        this.keyUnderSameFrequency.get(1).add(key);
    }

    public static void main(String[] args) {
        LFUCache_460 testCache = new LFUCache_460(2);

        testCache.put(1, 1);
        testCache.put(2, 2);
        System.out.println(testCache.get(1));
        testCache.put(3, 3);
        System.out.println(testCache.get(2));
        System.out.println(testCache.get(3));
        testCache.put(4, 4);
        System.out.println(testCache.get(1));
        System.out.println(testCache.get(3));
        System.out.println(testCache.get(4));
        System.out.println(testCache);
    }
}