package Solution.LinkedList;

import Lib.ListNode.ListNode;

/**
 * Given a linked list and a value x.
 * Partition it such that all nodes less than x come before nodes greater than or equal to x.
 * Original relative order of the nodes should be preserved in each of the two partitions.
 * Example:
 * Input: head = 1->4->3->2->5->2, x = 3
 * Output: 1->2->2->4->3->5
 *
 * @author BorisMirage
 * Time: 2018/09/28 20:04
 * Created with IntelliJ IDEA
 */

public class PartitionList_86 {
    /**
     * Use two pointers, one connect to small part, and the other one connect to larger part (including equal).
     * Another two pointers connect each ListNode during partition.
     * For each ListNode, connect them to respective pointer and finally link small and large list.
     *
     * @param head head node
     * @param x    compare value
     * @return partitioned list
     */
    public ListNode partition(ListNode head, int x) {

        ListNode smallHead = new ListNode(0);       // dummy head of smaller part
        ListNode small = smallHead;
        ListNode largeHead = new ListNode(0);       // dummy head of larger part
        ListNode large = largeHead;

        while (head != null) {
            if (head.val >= x) {
                large.next = head;
                large = head;

            } else {
                small.next = head;
                small = head;
            }
            head = head.next;
        }
        large.next = null;              // mark as end of new list
        small.next = largeHead.next;    // combine two lists

        return smallHead.next;
    }
}
