package solution.linkedlist;

import library.randomnode.Node;

/**
 * A linked list is given such that each node contains an additional random pointer.
 * Return a deep copy of the list.
 *
 * @author BorisMirage
 * Time: 2019/08/27 18:55
 * Created with IntelliJ IDEA
 */

public class CopyRandomList_138 {
    /**
     * Traverse list for 3 times.
     * First time, duplicate node to original node's next.
     * Second time, set the new node's random pointer.
     * Finally, detach the original list and copied list, and return copied list.
     *
     * @param head head of original list
     * @return deep copy of the list
     */
    public Node copyRandomList(Node head) {
        Node h = head, next;

        while (h != null) {       // first traverse: duplicate original nodes
            next = h.next;
            h.next = new Node();
            h.next.val = h.val;
            h.next.next = next;
            h = next;
        }

        h = head;

        while (h != null) {        // second traverse: set duplicated node's random pointer
            if (h.random != null) {
                h.next.random = h.random.next;
            }
            h = h.next.next;
        }

        h = head;
        Node dummy = new Node();
        Node current = dummy, tmp;

        while (h != null) {
            next = h.next.next;     // next node in original list
            tmp = h.next;           // next node in copied list

            current.next = tmp;
            current = tmp;

            head.next = next;
            h = next;
        }

        return dummy.next;
    }
}
