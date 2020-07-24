package Solution.TwoPointers;

import Lib.ListNode.ListNode;

/**
 * Given a sorted linked list, delete all duplicates such that each element appear only once.
 *
 * @author BorisMirage
 * Time: 2018/08/16 14:20
 * Created with IntelliJ IDEA
 */

public class DeleteDuplicates_83 {
    /**
     * Move next pointer in current node to next non-duplicated element.
     *
     * @param head head ListNode
     * @return removed head ListNode
     */
    public ListNode deleteDuplicates(ListNode head) {

        ListNode cache = head;

        while (cache != null && cache.next != null) {
            if (cache.val == cache.next.val) {      // if duplicated

                cache.next = cache.next.next;       // move next pointer to current's next and try again
            } else {

                cache = cache.next;     // if not duplicated, move pointer to this node
            }
        }
        return head;
    }
}
