package solution.linkedlist;

import library.listnode.ListNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given the head of a linked list.
 * Repeatedly delete consecutive sequences of nodes that sum to 0 until there are no such sequences.
 * After doing so, return the head of the final linked list. You may return any such answer.
 * (Note that in the examples below, all sequences are serializations of ListNode objects.)
 *
 * @author BorisMirage
 * Time: 2019/08/24 20:05
 * Created with IntelliJ IDEA
 */

public class RemoveZeroSumSublists_1171 {
    /**
     * Prefix sum.
     * Use a hash map to store prefix sum.
     * Prefix sum can only be increasing or decreasing if given array is monotonous.
     * When meet the same prefix, remove all elements of the subarray between them.
     * If it's a prefix that we've never seen, set m[prefix] = current.
     * If this prefix is in map, m[prefix] is the node achieved this prefix sum.
     * To skip all nodes between m[prefix] and cur.next (exclusive), simply link m[prefix].next = cur.next.
     *
     * @param head head node of list
     * @return list without such sequences that sum is 0
     */
    public ListNode removeZeroSumSublists(ListNode head) {
        ListNode dummy = new ListNode(0), cur = dummy;
        dummy.next = head;
        int prefix = 0;         // calculate prefix sum
        Map<Integer, ListNode> m = new HashMap<>();     // key: prefix sum; value: node with current prefix sum

        while (cur != null) {
            prefix += cur.val;
            if (m.containsKey(prefix)) {
                cur = m.get(prefix).next;
                int p = prefix + cur.val;

                while (p != prefix) {       // remove invalid prefix sum
                    m.remove(p);
                    cur = cur.next;
                    p += cur.val;
                }

                m.get(prefix).next = cur.next;
            } else {
                m.put(prefix, cur);
            }
            cur = cur.next;
        }
        return dummy.next;
    }
}
