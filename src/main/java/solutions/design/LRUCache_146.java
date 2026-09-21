package solutions.design;

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
    private final Node START = new Node(-1, -1);
    private final Node END = new Node(-1, -1);
    private final Map<Integer, Node> map = new HashMap<>();

    /**
     * Creates an empty cache with the supplied maximum number of entries.
     * A zero capacity cache accepts writes but immediately evicts them.
     *
     * <p>The implementation combines a hash map with a doubly linked list.
     * The map stores each key and its corresponding node, allowing a key to be
     * found in expected constant time. The linked list stores nodes in recency
     * order: the node immediately after {@code START} is the most recently
     * used, and the node immediately before {@code END} is the least recently
     * used. The sentinel nodes represent the two boundaries and remove special
     * cases when nodes are inserted or removed.
     *
     * <p>{@code get} looks up a node in the map and moves it to the front of
     * the list. {@code put} updates and moves an existing node, or inserts a
     * new node at the front. If insertion exceeds the capacity, the node
     * before {@code END} is removed from both the list and the map. Each
     * operation takes expected {@code O(1)} time, and the cache uses
     * {@code O(capacity)} auxiliary space.
     *
     * @param capacity maximum number of entries retained by the cache
     */
    public LRUCache_146(int capacity) {
        this.capacity = capacity;
        START.next = END;
        END.previous = START;
    }

    /**
     * Returns a value and marks its key as most recently used.
     *
     * <p>A successful lookup moves the node to the front of the recency list;
     * a miss leaves the list unchanged.</p>
     *
     * @param key key to look up
     * @return the stored value, or {@code -1} when the key is absent
     * @implNote The map finds the node and the doubly linked list promotes it
     * without traversing other entries, giving expected {@code O(1)} time.
     */
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Node n = map.get(key);
        moveToTop(n);
        return n.val;
    }

    /**
     * Inserts or updates a key and marks it as most recently used.
     * Updating an existing key changes its value without increasing the cache
     * size. Inserting beyond capacity evicts the least recently used key.
     *
     * @param key   key to insert or update
     * @param value value associated with {@code key}
     * @implNote Map insertion or lookup, list promotion, and optional tail
     * eviction each take expected {@code O(1)} time.
     */
    public void put(int key, int value) {
        Node n;
        if (map.containsKey(key)) {
            n = map.get(key);
            n.val = value;
        } else {
            n = new Node(key, value);
            map.put(key, n);
        }
        moveToTop(n);
        if (map.size() > capacity) {
            removeLast();
        }
    }

    /**
     * Removes {@code current} from its current list position and inserts it at
     * the front. A newly created node has no neighbors, so it is only inserted.
     * The most-recently-used node is already at the front and needs no work.
     *
     * @param current node to promote
     */
    private void moveToTop(Node current) {
        if (current == START.next) {
            return;
        }

        if (current.previous != null && current.next != null) {
            unlink(current);
        }

        Node first = START.next;
        first.previous = current;
        START.next = current;

        current.previous = START;
        current.next = first;
    }

    /**
     * Removes the least recently used node, which is immediately before the
     * tail sentinel, from both the list and the map.
     */
    private void removeLast() {
        Node remove = END.previous;
        unlink(remove);
        map.remove(remove.key);
    }

    /**
     * Removes a linked node while preserving the links between its neighbors.
     * The node is detached completely so it can be safely reinserted elsewhere.
     *
     * @param current linked node to detach
     */
    private void unlink(Node current) {
        current.previous.next = current.next;
        current.next.previous = current.previous;
        current.previous = null;
        current.next = null;
    }

    /**
     * A map entry plus its links in the recency-order list.
     */
    private static class Node {
        int key;
        int val;
        Node previous;
        Node next;

        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
}
