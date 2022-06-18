package solution.linkedlist;

import library.listnode.ListNode;

/**
 * Given a linked list, swap every two adjacent nodes and return its head.
 * Note:
 * 1. Algorithm should use only CONSTANT extra space.
 * 2. Do not modify the values in the list's nodes, only nodes itself may be changed.
 *
 * @author BorisMirage
 * Time: 2018/06/13 15:56
 * Created with IntelliJ IDEA
 */

public class SwapPairs_24 {
    /**
     * Suppose there are four nodes 1, 2, 3, 4 and linked as dummy -> 1 -> 2 -> 3 -> 4 -> null.
     * If swap nodes, then it should be dummy -> 2 -> 1 -> 3 -> 4.
     * Create two nodes as temporary variable, current and next.
     * First, point current and next to 1 and 2.
     * Next, point 1 to 3 and 2 to 1, which is current = temp.next and next = temp.next.next.
     * Then point 2 to 1 to finish swap. This is next.next = current.
     * After this process, swap is completed, but previous dummy head still points at 1. Hence, point dummy to 2.
     * This is temp.next = next.
     * Finally, new dummy head should be created to repeat this process, then there is temp = current.
     *
     * @param head first node
     * @return ListNodes with correct order
     */
    public ListNode swapPairs(ListNode head) {

        /* Corner case */
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(0);      // dummy head
        dummy.next = head;
        ListNode temp = dummy;
        ListNode current;
        ListNode next;

        while (temp.next != null && temp.next.next != null) {
            current = temp.next;        // current -> 1
            next = temp.next.next;      // next -> 2
            current.next = next.next;       // point current -> 3
            next.next = current;        // point temp -> 1
            temp.next = next;       // point dummy -> 2
            temp = current;     // move temp to 1 as dummy again
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        printAll(new SwapPairs_24().swapPairs(getList3()));
    }

    private static ListNode getList3() {
        ListNode n1 = new ListNode(5), n2 = new ListNode(4), n3 = new ListNode(3), n4 = new ListNode(2), n5 = new ListNode(1);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        return n1;
    }

    private static void printAll(ListNode head) {
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }
}
