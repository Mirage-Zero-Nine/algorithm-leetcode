package solution.linkedlist;

import library.listnode.ListNode;

/**
 * Two non-empty linked lists are given to represent two non-negative integers.
 * The digits are stored in reverse order and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked list.
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 *
 * @author BorisMirage
 * Time: 2017/11/24 12:26
 * Created with IntelliJ IDEA
 */

public class AddTwoNumbers_2 {
    /**
     * Sum two nodes and return the sum node until list is empty.
     *
     * @param l1 first int contains in ListNode
     * @param l2 second int contains in ListNode
     * @return result in ListNode
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        /* Corner case */
        if (l1 == null && l2 == null) {
            return null;
        }

        int carry = 0;
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while (l1 != null || l2 != null) {
            if (l1 != null) {
                carry += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                carry += l2.val;
                l2 = l2.next;
            }

            current.next = new ListNode(carry % 10);        // link node to (sum/10)
            current = current.next;                             // move result to its next

            carry = carry / 10;
        }

        if (carry != 0) {
            current.next = new ListNode(carry);
        }

        return dummy.next;
    }

    /**
     * Sum two nodes and return the sum node until list is empty.
     * This is recursion version, it is suggested that use iterative version instead.
     *
     * @param l1 first int contains in ListNode
     * @param l2 second int contains in ListNode
     * @return result in ListNode
     */
    public ListNode addTwoNumbersRecursion(ListNode l1, ListNode l2) {
        return addTwoNumbersRecursion(l1, l2, 0);
    }

    /**
     * Add non-empty nodes recursively until all nodes in two lists are reached.
     * Use a int to save carry for next calculation.
     *
     * @param n1    first node
     * @param n2    second node
     * @param carry carry from previous sum
     * @return sum of two nodes
     */
    private ListNode addTwoNumbersRecursion(ListNode n1, ListNode n2, int carry) {

        /* Corner case and end point */
        if (n1 == null && n2 == null && carry == 0) {
            return null;
        }

        if (n1 != null) {
            carry += n1.val;
        }
        if (n2 != null) {
            carry += n2.val;
        }

        ListNode current = new ListNode(carry % 10);        // create a node to store this digit
        current.next = addTwoNumbersRecursion((n1 == null) ? null : n1.next, (n2 == null) ? null : n2.next, carry / 10);

        return current;
    }
}


