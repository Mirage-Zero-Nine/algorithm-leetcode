package solution.datastructure;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
    private final Map<String, Integer> stringMap;  // key and its count
    private final Map<Integer, Node> countMap;     // count - node pair, node stores all keys with same count
    private final Node min;
    private final Node max;

    /**
     * Constructor.
     * Two hash maps, one hash map stores key and its count, the other hash map stores count and keys under this count.
     * Achieve O(1) get and remove (increment and decrement): HashMap stores string and count as a pair
     * Achieve O(1) get max and get min: linked list, index is the frequency and value is the string at this frequency.
     * Achieve O(1) update frequency without sort: HashMap, key is the frequency, value is the address of node in list.
     */
    public AllOne_432() {
        stringMap = new HashMap<>();
        countMap = new HashMap<>();

        min = new Node(0, new HashSet<>());
        max = new Node(Integer.MAX_VALUE, new HashSet<>());
        min.next = max;
        max.previous = min;
    }

    /**
     * Inserts a new key with value 1, or increments an existing key by 1.
     *
     * @param key key to insert or increment
     */
    public void inc(String key) {
        int currentCount = stringMap.getOrDefault(key, 0);
        int nextCount = currentCount + 1;

        if (stringMap.containsKey(key)) {      // key exist, increment key count by 1
            Node tmp, currentNode = countMap.get(currentCount);

            if (countMap.containsKey(nextCount)) {     // if there exist key at next frequency, then directly add it
                tmp = countMap.get(nextCount);
            } else {                                // otherwise, create a new node
                tmp = new Node(nextCount, new HashSet<>());
                countMap.put(nextCount, tmp);
                insertNode(currentNode, tmp);
            }
            tmp.keySet.add(key);

            currentNode.keySet.remove(key);        // remove key in previous frequency
            checkNodeToBeRemoved(currentNode);

        } else {        // key does not exist, insert new key
            if (countMap.containsKey(nextCount)) {
                countMap.get(nextCount).keySet.add(key);
            } else {
                Node tmp = new Node(nextCount, new HashSet<>());
                insertNode(min, tmp);
                tmp.keySet.add(key);
                countMap.put(nextCount, tmp);
            }
        }
        stringMap.put(key, nextCount);               // update frequency
    }

    /**
     * Decrements an existing key by 1.
     * If Key's value is 1, remove it from the data structure.
     *
     * @param key key to decrement
     */
    public void dec(String key) {

        if (!this.stringMap.containsKey(key)) {
            return;
        }

        int currentCount = this.stringMap.get(key);
        int nextCount = currentCount - 1;

        Node currentNode = countMap.get(currentCount);
        currentNode.keySet.remove(key);

        if (nextCount != 0) {       // if count of the key is larger than 1, keep it in the structure
            this.stringMap.put(key, nextCount);       // update frequency
            Node tmp;

            if (countMap.containsKey(nextCount)) {
                tmp = countMap.get(nextCount);
            } else {
                tmp = new Node(nextCount, new HashSet<>());
                countMap.put(nextCount, tmp);
                insertNode(currentNode.previous, tmp);
            }
            tmp.keySet.add(key);
        } else {                // otherwise, it will be removed
            this.stringMap.remove(key);
        }

        checkNodeToBeRemoved(currentNode);
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
     * Check if current node contains any key, if not, remove the node from double linked list.
     *
     * @param currentNode check if node needs to be removed
     */
    private void checkNodeToBeRemoved(Node currentNode) {
        if (currentNode.keySet.isEmpty()) {
            countMap.remove(currentNode.count);
            removeNode(currentNode);
        }
    }

    /**
     * Double linked list node store the frequency of string and all strings under this frequency.
     */
    static class Node {
        private final int count;
        private final Set<String> keySet;
        private Node previous;
        private Node next;

        /**
         * Constructor.
         *
         * @param count  frequency of string
         * @param keySet all strings under this frequency
         */
        Node(int count, HashSet<String> keySet) {
            this.count = count;
            this.keySet = keySet;
        }

        /**
         * Randomly return a string from the hash set.
         *
         * @return one string from the set
         */
        public String getKey() {
            return keySet.iterator().next();
        }
    }
}
