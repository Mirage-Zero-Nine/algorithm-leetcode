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
    // Separate-chaining buckets; their count is always maxSize.
    private List<ArrayList<Entry>> buckets;
    // Number of buckets used to calculate a key's hash index.
    private int maxSize = 256;
    // Number of distinct key-to-value mappings stored in this map.
    private int mapSize;

    /**
     * Creates an empty hash map with 256 buckets.
     */
    public MyHashMap_706() {
        buckets = IntStream.range(0, maxSize).mapToObj(_ -> new ArrayList<Entry>()).toList();
    }

    /**
     * Associates {@code value} with {@code key}, replacing the previous value when the key exists.
     *
     * @param key the key to add or update
     * @param value the value associated with {@code key}
     */
    public void put(int key, int value) {
        int index = key % maxSize;
        List<Entry> list = buckets.get(index);
        for (Entry entry : list) {
            if (entry.key == key) {
                entry.value = value;
                return;
            }
        }
        list.add(new Entry(key, value));
        mapSize++;

        if (mapSize > maxSize * 0.75) {
            rehash();
        }
    }

    /**
     * Returns the value associated with {@code key}, or {@code -1} when the key is absent.
     *
     * @param key the key to look up
     * @return the mapped value, or {@code -1} when no mapping exists
     */
    public int get(int key) {
        int index = key % maxSize;
        List<Entry> list = buckets.get(index);
        for (Entry entry : list) {
            if (entry.key == key) {
                return entry.value;
            }
        }
        return -1;
    }

    /**
     * Removes the mapping for {@code key} when present; otherwise this operation has no effect.
     *
     * @param key the key to remove
     */
    public void remove(int key) {
        int index = key % maxSize;
        List<Entry> list = buckets.get(index);
        for (Entry entry : list) {
            if (entry.key == key) {
                list.remove(entry);
                mapSize--;
                return;
            }
        }
    }

    /**
     * Doubles the bucket count and redistributes the existing mappings using the new capacity.
     */
    private void rehash() {
        maxSize *= 2;
        List<ArrayList<Entry>> old = this.buckets;
        buckets = IntStream.range(0, maxSize).mapToObj(_ -> new ArrayList<Entry>()).toList();
        old.stream()
                .filter(list -> !list.isEmpty())
                .forEach(list -> list.forEach(e -> this.buckets.get(e.key % maxSize).add(e)));
    }

    /**
     * A key-to-value mapping stored in a bucket chain.
     */
    private static class Entry {
        int key;
        int value;

        Entry(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
