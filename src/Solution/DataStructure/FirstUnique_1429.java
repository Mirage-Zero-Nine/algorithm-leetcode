package Solution.DataStructure;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * You have a queue of integers, you need to retrieve the first unique integer in the queue.
 * Implement the class:
 * 1. FirstUnique(int[] nums): Initializes the object with the numbers in the queue.
 * 2. int showFirstUnique(): Returns the first unique integer of the queue, returns -1 if there is no such integer.
 * 3. void add(int value): Insert value to the queue.
 *
 * @author BorisMirage
 * Time: 2020/05/12 21:34
 * Created with IntelliJ IDEA
 */

public class FirstUnique_1429 {
    Map<Integer, Integer> m = new HashMap<>();      // key: element, value: occurrence of element
    Queue<Integer> q = new LinkedList<>();          // all elements in given array and later inserted elements

    /**
     * Lazy poll out duplicated element in array.
     * Keep a hash map to distinguish the unique elements and duplicated elements.
     * Keep a queue to track the order of elements in array.
     *
     * @param nums given array
     */
    public FirstUnique_1429(int[] nums) {
        for (int n : nums) {
            add(n);
        }
    }

    /**
     * Return the first unique element in array.
     * Poll out all the duplicated elements in queue first.
     * Check if the head of queue is duplicated by looking into hash map.
     *
     * @return the first unique integer in given array
     */
    public int showFirstUnique() {
        while (!q.isEmpty() && m.get(q.peek()) > 1) {
            q.poll();       // remove all duplicated elements
        }

        return q.isEmpty() ? -1 : q.peek();
    }

    /**
     * Insert value to the queue.
     * If the value is duplicated, update in hash map.
     *
     * @param value value to be added
     */
    public void add(int value) {
        m.put(value, m.getOrDefault(value, 0) + 1);
        q.offer(value);
    }

    public static void main(String[] args) {
        FirstUnique_1429 test = new FirstUnique_1429(new int[]{2, 3, 5});
        System.out.println(test.showFirstUnique());     // 2
        test.add(5);
        System.out.println(test.showFirstUnique());     // 2
        test.add(2);
        System.out.println(test.showFirstUnique());     // 3
        test.add(3);
        System.out.println(test.showFirstUnique());     // -1
    }
}
