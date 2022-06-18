package solution.datastructure;

import java.util.*;

/**
 * Design and implement a data structure for Least Recently Used (LRU) cache.
 * It should support the following operations: get and put.
 * get(key): Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value): Set or insert the value if the key is not already present.
 * When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 *
 * @author BorisMirage
 * Time: 2018/09/28 21:04
 * Created with IntelliJ IDEA
 */

public class LRUCache_146 {

    /**
     * Two-way linked cache entry class.
     */
    static class Entry {
        final int key;
        int value;
        Entry previous;
        Entry next;

        /**
         * Initialization of entry
         *
         * @param key   cache key
         * @param value cache value
         */
        Entry(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private final HashMap<Integer, Entry> map;
    private int count;
    private final Entry end = new Entry(-1, -1);
    private final Entry head = new Entry(-1, -1);

    /**
     * Structure of cache:
     * Basically, use a two-way linked list to store cache entry.
     * The order of the list is newest at the top, oldest at the end.
     * Keep a hashmap as index table to achieve O(1) look up complexity.
     * For <code>get</code> operation, if entry exists, remove the entry from list, then move it to the top of the list.
     * If <code>put</code> operation found an existing key, update map, remove entry from list, then move to the top.
     * Otherwise, check the capacity.
     * If over-sized, remove the last used entry (GC required) from list and index map. Then add the new entry.
     *
     * @param capacity cache capacity
     */
    public LRUCache_146(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        head.next = end;
        end.previous = head;
    }

    /**
     * <code>get</code> operation. Return -1 if key is not found in cache.
     *
     * @param key requesting key
     * @return corresponding value, or -1.
     */
    public int get(int key) {
        Entry e = this.map.get(key);
        if (e == null) {
            return -1;
        }
        removeEntryFromList(e);
        insertToHead(e);
        return e.value;
    }

    /**
     * <code>put</code> operation, put new key-value pair into cache.
     * If cache is oversize, it will remove Least Recently Used (LRU) Cache store in cache.
     *
     * @param key   new key
     * @param value new value
     */
    public void put(int key, int value) {

        if (map.containsKey(key)) {
            Entry e = map.get(key);
            e.value = value;
            removeEntryFromList(e);
            insertToHead(e);

            return;
        }

        if (++count > capacity) {
            Entry last = end.previous;
            map.remove(last.key);
            removeEntryFromList(last);
            deleteEntry(last);
            count--;
        }
        Entry e = new Entry(key, value);
        map.put(key, e);
        insertToHead(e);
    }

    /**
     * Insert an entry to the top of the list (last recent used).
     * Note that it regard every entry as new entry, no previous connection to it.
     *
     * @param e entry to be inserted
     */
    private void insertToHead(Entry e) {
        e.previous = head;
        e.next = head.next;
        head.next = e;
        e.next.previous = e;
    }

    /**
     * Delete entry from cache.
     * This is actually not required in Java.
     *
     * @param e entry to be removed
     */
    private void deleteEntry(Entry e) {
        e.previous = null;
        e.next = null;
    }

    /**
     * Remove (but not delete) an entry from list.
     * e.g: a -> b -> c -> => a -> c, where b is
     *
     * @param e entry to be removed from list
     */
    private void removeEntryFromList(Entry e) {
        e.previous.next = e.next;
        e.next.previous = e.previous;
        e.previous = null;
        e.next = null;
    }

    public static void main(String[] args) {
        LRUCache_146 testCache = new LRUCache_146(2);

        testCache.put(1, 1);
        testCache.put(2, 2);
        System.out.println(testCache.get(1));
        testCache.put(3, 3);
        System.out.println(testCache.get(2));
        testCache.put(4, 4);
        System.out.println(testCache.get(1));
        System.out.println(testCache.get(3));
        System.out.println(testCache.get(4));
        System.out.println(testCache);
    }
}

