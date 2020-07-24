package Solution.DataStructure;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Implement a data structure supporting the following operations:
 * 1. Inc(Key) - Inserts a new key with value 1. Or increments an existing key by 1.
 * 2. Dec(Key) - If Key's value is 1, remove it from the data structure. Otherwise decrements an existing key by 1.
 * 3. GetMaxKey() - Returns one of the keys with maximal value. If no element exists, return an empty string "".
 * 4. GetMinKey() - Returns one of the keys with minimal value. If no element exists, return an empty string "".
 * Challenge: Perform all these in O(1) time complexity.
 *
 * @author BorisMirage
 * Time: 2020/04/04 13:31
 * Created with IntelliJ IDEA
 */

public class AllOne_432 {
    private HashMap<String, Integer> key;        // key - count pair
    private HashMap<Integer, Node> count;          // count - node pair, node stores all keys with same count
    private Node min;
    private Node max;

    /**
     * Constructor.
     * Two hash maps, one hash map stores key and its count, the other hash map stores count and keys under this count.
     * Achieve O(1) get and remove (increment and decrement): HashMap stores string and count as a pair
     * Achieve O(1) get max and get min: linked list, index is the frequency and value is the string at this frequency.
     * Achieve O(1) update frequency without sort: HashMap, key is the frequency, value is the address of node in list.
     */
    public AllOne_432() {
        key = new HashMap<>();
        count = new HashMap<>();

        min = new Node(0, new HashSet<>());
        max = new Node(Integer.MAX_VALUE, new HashSet<>());
        min.next = max;
        max.previous = min;
    }

    /**
     * Inserts a new key with value 1. Or increments an existing key by 1.
     *
     * @param key key to insert or increment
     */
    public void inc(String key) {

        if (this.key.containsKey(key)) {      // key exist, increment key count by 1
            int currentCount = this.key.get(key);       // current key's frequency
            int nextCount = currentCount + 1;

            this.key.put(key, nextCount);               // update frequency
            Node tmp, currentNode = count.get(currentCount);

            if (count.containsKey(nextCount)) {     // if there exist key at next frequency, then directly add it
                tmp = count.get(nextCount);
                tmp.add(key);
            } else {                                // otherwise, create a new node
                tmp = new Node(nextCount, new HashSet<>());
                tmp.add(key);
                count.put(nextCount, tmp);
                insertNode(currentNode, tmp);
            }

            currentNode.remove(key);        // remove key in previous frequency
            if (currentNode.set.isEmpty()) {        // if previous node is empty, remove it
                count.remove(currentCount);
                removeNode(currentNode);
            }

        } else {        // key does not exist, insert new key
            this.key.put(key, 1);

            if (count.containsKey(1)) {
                count.get(1).add(key);
            } else {
                Node tmp = new Node(1, new HashSet<>());
                insertNode(min, tmp);
                tmp.add(key);
                count.put(1, tmp);
            }
        }
    }

    /**
     * Decrements an existing key by 1. If Key's value is 1, remove it from the data structure.
     *
     * @param key key to decrement
     */
    public void dec(String key) {

        if (!this.key.containsKey(key)) {
            return;
        }

        int currentCount = this.key.get(key);
        int nextCount = currentCount - 1;

        Node currentNode = count.get(currentCount);
        currentNode.remove(key);

        if (nextCount != 0) {       // if count of the key is larger than 1, then keep it in the structure
            this.key.put(key, nextCount);       // update frequency
            Node tmp;

            if (count.containsKey(nextCount)) {
                tmp = count.get(nextCount);
                tmp.add(key);
            } else {
                tmp = new Node(nextCount, new HashSet<>());
                tmp.add(key);
                count.put(nextCount, tmp);
                insertNode(currentNode.previous, tmp);
            }
        } else {                // otherwise, it will be removed
            this.key.remove(key);
        }

        if (currentNode.set.isEmpty()) {
            count.remove(currentCount);
            removeNode(currentNode);
        }
    }

    /**
     * Returns one of the keys with maximal value.
     *
     * @return one of the keys with maximal value
     */
    public String getMaxKey() {
        return max.previous.count == 0 ? "" : max.previous.getKey();
    }

    /**
     * Returns one of the keys with minimal value.
     *
     * @return one of the keys with minimal value
     */
    public String getMinKey() {
        return min.next.count == Integer.MAX_VALUE ? "" : min.next.getKey();
    }

    /**
     * Remove given node.
     *
     * @param n node to be removed
     */
    private void removeNode(Node n) {
        Node tmp = n.previous;
        tmp.next = n.next;
        n.next.previous = tmp;
    }

    /**
     * Insert a node after the given previous node
     *
     * @param previous previous node
     * @param n        node inserted after previous node
     */
    private void insertNode(Node previous, Node n) {
        Node tmp = previous.next;
        n.previous = previous;
        n.next = tmp;
        tmp.previous = n;
        previous.next = n;
    }

    /**
     * Node store the frequency of string and all strings under this frequency.
     */
    static class Node {
        int count;
        HashSet<String> set;
        Node previous;
        Node next;

        /**
         * Constructor.
         *
         * @param count frequency of string
         * @param set   all strings under this frequency
         */
        Node(int count, HashSet<String> set) {
            this.count = count;
            this.set = set;
        }

        /**
         * Add a new string to the set
         *
         * @param key new string
         */
        public void add(String key) {
            this.set.add(key);
        }

        /**
         * Remove a string from set.
         *
         * @param key string to be removed
         */
        public void remove(String key) {
            this.set.remove(key);
        }

        /**
         * Get one string from the set.
         *
         * @return one string from the set
         */
        public String getKey() {
            return set.iterator().next();
        }
    }

    public static void main(String[] args) {
        AllOne_432 test = new AllOne_432();
        System.out.println(test.getMaxKey());
        System.out.println(test.getMinKey());
        test.inc("hello");
        test.inc("goodbye");
        test.inc("hello");
        test.inc("hello");
        System.out.println(test.getMaxKey());       // hello
        test.inc("leet");       // 1
        test.inc("code");       // 1
        test.inc("leet");       // 2
        test.dec("hello");      // 2
        test.inc("leet");       // 3
        test.inc("code");       // 2
        test.inc("code");       // 3
        System.out.println(test.getMaxKey());       // leet/code
    }
}
