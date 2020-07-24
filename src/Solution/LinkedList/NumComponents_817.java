package Solution.LinkedList;

import Lib.ListNode.ListNode;

import java.util.HashSet;

/**
 * We are given head, the head node of a linked list containing unique integer values.
 * We are also given the list G, a subset of the values in the linked list.
 * Return the number of connected components in G, where two values are connected if they appear consecutively.
 *
 * @author BorisMirage
 * Time: 2019/10/11 17:09
 * Created with IntelliJ IDEA
 */

public class NumComponents_817 {
    /**
     * Use a hash set to store all elements in G, since both list and array are unsorted.
     * Then traverse the linked list, if the connected value is in set first time, add one to result.
     *
     * @param head head of list
     * @param G    subset of the values in the linked list
     * @return number of connected components in G
     */
    public int numComponents(ListNode head, int[] G) {

        int count = 0;
        HashSet<Integer> set = new HashSet<>();

        for (int i : G) {
            set.add(i);
        }

        ListNode n = head;
        boolean connect = false;
        while (n != null) {
            if (set.contains(n.val) && !connect) {      // if find a connected component first time, add count
                connect = true;
                count++;
            } else if (!set.contains(n.val)) {
                connect = false;
            }
            n = n.next;
        }

        return count;
    }
}
