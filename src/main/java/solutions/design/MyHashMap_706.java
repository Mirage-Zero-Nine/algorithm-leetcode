package solutions.design;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Design a HashMap without using any built-in hash table libraries.
 * To be specific, your design should include these functions:
 * - put(key, value) : Insert a (key, value) pair into the HashMap. If the value already exists in the HashMap, update the value.
 * - get(key): Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
 * - remove(key) : Remove the mapping for the value key if this map contains the mapping for the key.
 *
 * @author BorisMirage
 * Time: 2018/10/07 17:49
 * Created with IntelliJ IDEA
 * No.100
 */

public class MyHashMap_706 {
    // Each inner list is a mutable collision chain; mapSize counts mappings, not occupied buckets.
    private List<ArrayList<int[]>> bucket;
    private int maxSize = 256;
    private int mapSize;

    /**
     * Creates an empty separate-chaining map with 256 bucket lists. Keys are grouped by
     * {@code key % capacity}; the distinct mapping count triggers doubling above a 75% load, while
     * updates reuse the existing mapping. Each mapping is a two-element {@code int[]} with its key
     * at index 0 and value at index 1. The bucket table and entries are retained map storage.
     *
     * <p>Time complexity: O(C), where {@code C} is the initial bucket count (256).
     * <p>Auxiliary space complexity: O(C) for the initial bucket table, which becomes retained map
     * storage after construction; with {@code n} mappings and current capacity {@code C}, retained
     * storage is O(n + C).
     */
    public MyHashMap_706() {
        bucket = IntStream.range(0, maxSize)
                .mapToObj(_ -> new ArrayList<int[]>())
                .toList();
        mapSize = 0;
    }

    /**
     * Adds a mapping or updates an existing key. A new mapping is stored in the selected collision
     * chain, and the table grows when its mapping load exceeds 75%.
     *
     * <p>Time complexity: Expected amortized O(1) when keys are well distributed, where {@code n}
     * is the number of mappings and {@code C} is the current capacity; worst-case O(n) to scan a
     * collision chain, plus O(n + C) for a resize.
     * <p>Auxiliary space complexity: O(n + C) worst-case while a growing collision list or resize
     * temporarily needs additional backing storage.
     *
     * @param key key to add or update
     * @param value value to associate with {@code key}
     */
    public void put(int key, int value) {
        // Only this chain can contain the key because its bucket index is key % maxSize.
        List<int[]> list = bucket.get(key % maxSize);
        for (int[] e : list) {
            if (e[0] == key) {
                // Updating in place must not change the number of mappings.
                e[1] = value;
                return;
            }
        }
        mapSize++;
        list.add(new int[]{key, value});
        if (mapSize > maxSize * 0.75) {
            // Count first so the load threshold reflects this newly stored mapping.
            rehash();
        }
    }

    /**
     * Searches only {@code key}'s collision chain and returns its value, or {@code -1} when absent.
     *
     * <p>Time complexity: Expected O(1) with well-distributed keys; worst-case O(n), where
     * {@code n} is the selected collision-chain length.
     * <p>Auxiliary space complexity: O(1), excluding the map storage retained by this object.
     *
     * @param key key to look up
     * @return the mapped value, or {@code -1} when absent
     */
    public int get(int key) {
        // Each stream element is an entry: index 0 stores its key and index 1 its value.
        return bucket.get(key % maxSize).stream()
                // Keep only entries whose stored key matches the requested key.
                .filter(arr -> arr[0] == key)
                // Transform the matching entry into its associated value.
                .map(arr -> arr[1])
                // Keys are unique, so the first match is the only possible result.
                .findFirst()
                // An empty result means no entry matched, so return the required sentinel.
                .orElse(-1);
    }

    /**
     * Searches {@code key}'s collision chain and removes the matching entry when present. The table
     * is not shrunk.
     *
     * <p>Time complexity: Expected O(1) with well-distributed keys; worst-case O(n), where
     * {@code n} is the selected collision-chain length.
     * <p>Auxiliary space complexity: O(1), excluding the map storage retained by this object.
     *
     * @param key key whose mapping should be removed
     */
    public void remove(int key) {
        var list = bucket.get(key % maxSize);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i)[0] == key) {
                list.remove(i);
                // Only a successful removal changes the mapping count.
                mapSize--;
                break;
            }
        }
    }

    /**
     * Doubles the table and places every existing entry into its bucket for the new capacity,
     * reusing entry arrays so the mapping count is preserved without a nested resize.
     *
     * <p>Time complexity: O(n + C), where {@code n} is the number of mappings and {@code C} is the
     * old bucket count allocated/scanned during this resize.
     * <p>Auxiliary space complexity: O(n + C) while old and new tables coexist; entry arrays are
     * reused, and the new table becomes retained map storage afterward.
     */
    private void rehash() {
        maxSize *= 2;
        List<ArrayList<int[]>> old = this.bucket;
        bucket = IntStream.range(0, maxSize).mapToObj(_ -> new ArrayList<int[]>()).toList();
        old.stream()
                .filter(list -> !list.isEmpty())
                // Reuse entries and leave mapSize unchanged during redistribution.
                .forEach(list -> list.forEach(arr -> bucket.get(arr[0] % maxSize).add(arr)));
    }
}
