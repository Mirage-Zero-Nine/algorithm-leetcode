package Lib.ListNode;

/**
 * Generate a linked list.
 *
 * @author BorisMirage
 * Time: 2019/10/11 13:15
 * Created with IntelliJ IDEA
 */

public class LinkedListGenerator {
    /**
     * Generate linked list by integer array.
     *
     * @param arr given linked list
     * @return head node of linked list
     */
    public static ListNode generate(int[] arr) {
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;
        for (int n : arr) {
            current.next = new ListNode(n);
            current = current.next;
        }

        return dummy.next;
    }

    /**
     * Generate linked list by range [start, end].
     *
     * @param start start value
     * @param end   end value
     * @return head node of generated linked list
     */
    public static ListNode generate(int start, int end) {
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;
        for (int i = start; i <= end; i++) {
            current.next = new ListNode(i);
            current = current.next;
        }

        return dummy.next;
    }

    /**
     * Print all values in linked list.
     *
     * @param head head of given linked list
     */
    public static void printAll(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.println(current.val);
            current = current.next;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5};
        ListNode tmp = generate(arr);
        printAll(tmp);
    }
}
