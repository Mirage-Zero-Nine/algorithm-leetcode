package Solution.LinkedList;

import Lib.ListNode.ListNode;

/**
 * Merge two sorted linked lists and return it as a new list.
 * The new list should be made by splicing together the nodes of the first two lists.
 *
 * @author BorisMirage
 * Time: 2018/06/12 19:03
 * Created with IntelliJ IDEA
 */

public class MergeTwoSortedLists_21 {

    /**
     * Simply compare value in two ListNode. Point smaller one as next node.
     *
     * @param l1 first node
     * @param l2 second node
     * @return next smaller node
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while (l1 != null || l2 != null) {
            if (l1 == null || (l2 != null && l1.val >= l2.val)) {
                current.next = l2;
                current = l2;
                l2 = l2.next;
            } else {
                current.next = l1;
                current = l1;
                l1 = l1.next;
            }
        }
        return dummy.next;
    }
}
