package Solution.DataStructure;

import Lib.ListNode.ListNode;

import java.util.Random;

/**
 * Given a singly linked list, return a random node's value from the linked list.
 * Each node must have the same probability of being chosen.
 * Follow up:
 * What if the linked list is extremely large and its length is unknown to you?
 * Could you solve this efficiently without using extra space?
 *
 * @author BorisMirage
 * Time: 2020/03/16 12:29
 * Created with IntelliJ IDEA
 */

public class Solution_382 {
    ListNode head;
    Random random;

    /**
     * Generate random int by Random.randomInt(n).
     * If random int is same as current node's index, set out put to this node.
     * Generate n - 1 times of random int, where n is the length of list.
     * The initially return value will be the value of head node.
     *
     * @param head head of given linked list
     */
    public Solution_382(ListNode head) {
        this.head = head;
        this.random = new Random();
    }

    /**
     * Returns a random node's value.
     *
     * @return a random node's value
     */
    public int getRandom() {

        int out = head.val;
        int count = 1;
        ListNode current = this.head;

        while (current.next != null) {
            current = current.next;
            int rand = random.nextInt(count + 1);     // nextInt(int n): [0, n)
            if (rand == count) {
                out = current.val;
            }
            count++;
        }

        return out;
    }
}
