package Solution.LinkedList;

import Lib.ListNode.ListNode;

/**
 * Given a singly linked list, group all odd nodes together followed by the even nodes.
 * Please note here we are talking about the node number and not the value in the nodes.
 * You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.
 *
 * @author BorisMirage
 * Time: 2019/10/11 13:26
 * Created with IntelliJ IDEA
 */

public class OddEvenList_328 {
    /**
     * Use two list nodes as head. One odd head, one even head.
     * Each time, link odd element to even node's next, link even element node to odd node's next.
     *
     * @param head head of list
     * @return grouped list
     */
    public ListNode oddEvenList(ListNode head) {

        /* Corner case */
        if (head == null) {
            return null;
        }
        if (head.next == null || head.next.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode even = head;
        ListNode odd = head.next;
        ListNode oddHead = odd;

        while (odd != null && odd.next != null) {
            even.next = odd.next;
            even = even.next;
            odd.next = even.next;
            odd = odd.next;
        }

        even.next = oddHead;

        return dummy.next;
    }
}
