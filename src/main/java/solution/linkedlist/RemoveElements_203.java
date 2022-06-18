package solution.linkedlist;

import library.listnode.ListNode;

/**
 * Remove all elements from a linked list of integers that have value val.
 *
 * @author BorisMirage
 * Time: 2019/10/22 12:07
 * Created with IntelliJ IDEA
 */

public class RemoveElements_203 {
    /**
     * Link previous node of node to be removed to this remove node's next.
     * Skip the node sequence that all nodes in this sequence will need to be removed.
     *
     * @param head head of list
     * @param val  remove value
     * @return removed list head
     */
    public ListNode removeElements(ListNode head, int val) {

        /* Corner case */
        if (head == null) {
            return head;
        }

        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode out = dummyHead;

        while (dummyHead != null && dummyHead.next != null) {
            if (dummyHead.next.val == val) {
                ListNode current = dummyHead.next;
                while (current != null && current.val == val) { // skip all nodes need to remove
                    current = current.next;
                }
                dummyHead.next = current;
            }

            dummyHead = dummyHead.next;
        }

        return out.next;
    }
}
