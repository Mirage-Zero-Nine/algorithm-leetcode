package solution.datastructure;

import java.util.HashMap;
import java.util.Map;

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
    private final int capacity;
    private final Map<Integer, Node> map = new HashMap<>(); // key - value pair
    private final Node first = new Node();
    private final Node last = new Node();

    /**
     * To achieve O(1) for get / put operation, a hash map with key - value pair is required.
     * To achieve O(1) for LRU remove operation, a customized double linked node is required.
     * The node needs to be put in map as well to achieve O(1) when retrieving / updating / removing the node.
     *
     * @param capacity given capacity of cache
     */
    public LRUCache_146(int capacity) {
        this.capacity = capacity;
        first.next = last;
        last.previous = first;
    }

    /**
     * Retrieving value from cache. This would also move the cache entry to the most recently used entry.
     *
     * @param key identifier for getting value
     * @return value of the key in cache, or return -1 if key does not present in cache
     */
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Node node = map.get(key);
        removeNode(node);
        addToFirst(node);
        return map.get(key).value;
    }

    /**
     * Put new key - value pair to cache, or update existing value in cache.
     *
     * @param key   given key
     * @param value given value
     */
    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }

        // if a duplicated key is added, update the value and move the node to the top
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            removeNode(node);
            addToFirst(node);
            return;
        }

        Node node = new Node();
        node.key = key;
        node.value = value;

        // evict cache if reaches the capacity
        if (map.size() == capacity) {
            removeLast();
        }

        addToFirst(node);
        map.put(key, node);
    }

    /**
     * Unlink node from linked list.
     *
     * @param node given node
     */
    private void removeNode(Node node) {
        Node previousNode = node.previous;
        Node nextNode = node.next;
        previousNode.next = nextNode;
        nextNode.previous = previousNode;
    }

    /**
     * Add node to the top of the list.
     *
     * @param node given node
     */
    private void addToFirst(Node node) {
        node.next = first.next;
        node.previous = first;
        first.next.previous = node;
        first.next = node;
    }

    /**
     * Unlink last node in linked list. Also remove the key entry in hash map.
     */
    private void removeLast() {
        Node lastNode = last.previous;
        removeNode(lastNode);
        map.remove(lastNode.key);
    }

    /**
     * Customized double linked list node.
     * Double link is required to achieve O(1) get for the node instead of traversing the list.
     */
    static class Node {
        Node next;
        Node previous;
        int key;
        int value;
    }
}

