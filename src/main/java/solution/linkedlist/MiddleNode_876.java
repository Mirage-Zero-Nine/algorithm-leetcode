package solution.linkedlist;

import library.listnode.ListNode;

/**
 * Given a non-empty, singly linked list with head node head, return a middle node of linked list.
 * If there are two middle nodes, return the second middle node.
 *
 * @author BorisMirage
 * Time: 2019/10/11 16:32
 * Created with IntelliJ IDEA
 */

public class MiddleNode_876 {
    /**
     * Two pointers. Fast one and slow one.
     *
     * @param head head of list
     * @return middle of list
     */
    public ListNode middleNode(ListNode head) {
        ListNode fast = head, slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }
}
