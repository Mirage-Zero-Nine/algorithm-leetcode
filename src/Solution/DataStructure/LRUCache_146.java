package Solution.DataStructure;

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
    private int capacity;
    private DoubleLinkedNode head;
    private DoubleLinkedNode end;
    private int c = 0;      // count total cache size
    private HashMap<Integer, DoubleLinkedNode> map = new HashMap<>();

    /**
     * Structure of cache:
     * Basically, use node to store key and value. A hash map in this cache is to store key-node pair for searching key.
     * Cache works as a double linked list, which contains previous Cache and next Cache.
     * When <code>put</code> operation finds a existing key, move corresponding node to top of the list.
     * When put a new pair into cache, first check size to avoid oversize, then add this node to top of list.
     * If cache is oversize, then remove last Cache in double linked list. And remove corresponding key as well.
     *
     * @param capacity cache capacity
     */
    public LRUCache_146(int capacity) {
        this.capacity = capacity;
        this.head = new DoubleLinkedNode();
        this.end = new DoubleLinkedNode();
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
        DoubleLinkedNode temp = map.get(key);
        if (temp == null) {
            return -1;
        }

        lastUsed(temp);
        return temp.val;
    }

    /**
     * <code>put</code> operation, put new key-value pair into cache.
     * If cache is oversize, it will remove Least Recently Used (LRU) Cache store in cache.
     *
     * @param key   new key
     * @param value new value
     */
    public void put(int key, int value) {

        DoubleLinkedNode node = map.get(key);

        if (node == null) {
            DoubleLinkedNode add = new DoubleLinkedNode();
            add.key = key;
            add.val = value;
            c++;
            if (c > capacity) {
                map.remove(popEnd().key);
                c--;
            }
            map.put(key, add);
            insertHead(add);
        } else {
            map.get(key).val = value;
            lastUsed(map.get(key));
        }
    }

    /**
     * Move given node next to head node.
     * Note that this node will be removed.
     *
     * @param node last node in list
     */
    private void lastUsed(DoubleLinkedNode node) {
        removeNode(node);
        insertHead(node);
    }

    /**
     * Remove given node.
     *
     * @param node given node
     */
    private void removeNode(DoubleLinkedNode node) {
        DoubleLinkedNode pre = node.previous;
        DoubleLinkedNode next = node.next;
        pre.next = next;
        next.previous = pre;
    }

    /**
     * Insert a new node to head.
     *
     * @param node node to be moved
     */
    private void insertHead(DoubleLinkedNode node) {
        node.previous = head;
        node.next = head.next;
        head.next.previous = node;
        head.next = node;
    }

    /**
     * Remove last node in cache based on LRU policy.
     * Hash map needs to know which key to remove, hence pop node is required.
     *
     * @return pop node
     */
    private DoubleLinkedNode popEnd() {
        DoubleLinkedNode old = end.previous;
        this.removeNode(old);
        return old;
    }

    /**
     * Node in cache, which is a double linked list.
     */
    static class DoubleLinkedNode {
        public int key;                        // cache key
        public int val;                        // value store in cache
        public DoubleLinkedNode previous;      // previous cache block pointer
        public DoubleLinkedNode next;          // next cache block pointer
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

