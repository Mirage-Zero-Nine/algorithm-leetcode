package Solution.TwoPointers;

import Lib.ListNode.ListNode;

/**
 * @author BorisMirage
 * Time: 2019/06/20 16:55
 * Created with IntelliJ IDEA
 */

public class GetIntersectionNode_160 {
    /**
     * Two pointers. Each pointer starts at the beginning of A/B.
     * When traverse to the end of each list, swap to the other list.
     * If there is an intersection, these two pointers will meet there.
     *
     * @param headA first list head
     * @param headB second list head
     * @return intersection node
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        /* Corner case */
        if (headA == null || headB == null) {
            return null;
        }

        ListNode p1 = headA, p2 = headB;

        while (p1 != p2) {
            p1 = p1 != null ? p1.next : headB; // if reaches the end, move to the head of next list
            p2 = p2 != null ? p2.next : headA;
        }

        return p1;
    }
}
