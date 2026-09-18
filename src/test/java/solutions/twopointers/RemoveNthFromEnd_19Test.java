package solutions.twopointers;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.ArrayList;
import java.util.List;

import library.listnode.ListNode;
import org.junit.jupiter.api.Test;

public class RemoveNthFromEnd_19Test {

    private final RemoveNthFromEnd_19 solution = new RemoveNthFromEnd_19();

    @Test
    public void removesTheOnlyNode() {
        assertRemoved(new int[]{}, 1, 42);
    }

    @Test
    public void removesTheLastNodeFromTwoNodeList() {
        assertRemoved(new int[]{1}, 1, 1, 2);
    }

    @Test
    public void removesTheFirstNodeFromTwoNodeList() {
        assertRemoved(new int[]{2}, 2, 1, 2);
    }

    @Test
    public void removesTheLastNodeFromLongerList() {
        assertRemoved(new int[]{1, 2, 3, 4}, 1, 1, 2, 3, 4, 5);
    }

    @Test
    public void removesTheFirstNodeFromLongerList() {
        assertRemoved(new int[]{2, 3, 4, 5}, 5, 1, 2, 3, 4, 5);
    }

    @Test
    public void removesAnInteriorNodeFromOddLengthList() {
        assertRemoved(new int[]{1, 2, 4, 5}, 3, 1, 2, 3, 4, 5);
    }

    @Test
    public void removesAnInteriorNodeFromEvenLengthList() {
        assertRemoved(new int[]{10, 20, 40}, 2, 10, 20, 30, 40);
    }

    @Test
    public void preservesDuplicateValuesAroundRemovedNode() {
        assertRemoved(new int[]{7, 7, 7, 7}, 3, 7, 7, 7, 7, 7);
    }

    @Test
    public void supportsMinimumAndMaximumNodeValues() {
        assertRemoved(new int[]{0, 1, 50, 100}, 2, 0, 1, 50, 99, 100);
    }

    @Test
    public void removesTheCorrectOccurrenceWhenValuesRepeat() {
        assertRemoved(new int[]{5, 1, 5, 5}, 2, 5, 1, 5, 2, 5);
    }

    @Test
    public void supportsRepeatedCallsWithFreshLists() {
        assertRemoved(new int[]{1, 3, 4}, 3, 1, 2, 3, 4);
        assertRemoved(new int[]{6, 7, 8}, 1, 6, 7, 8, 9);
    }

    @Test
    public void removesTheRequestedNodeAtMaximumListLength() {
        assertRemoved(
                new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
                        17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30},
                15,
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16,
                17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30);
    }

    @Test
    public void removesTheSecondNodeAndPreservesOriginalNodeIdentities() {
        ListNode head = build(11, 22, 33, 44, 55);
        ListNode second = head.next;
        ListNode third = second.next;
        ListNode fourth = third.next;
        ListNode fifth = fourth.next;

        ListNode result = solution.removeNthFromEnd(head, 4);

        assertSame(head, result);
        assertSame(third, result.next);
        assertSame(fourth, result.next.next);
        assertSame(fifth, result.next.next.next);
        assertArrayEquals(new int[]{11, 33, 44, 55}, valuesOf(result));
    }

    @Test
    public void removesThePenultimateNodeAndPreservesOriginalNodeIdentities() {
        ListNode head = build(11, 22, 33, 44, 55);
        ListNode second = head.next;
        ListNode third = second.next;
        ListNode fourth = third.next;
        ListNode fifth = fourth.next;

        ListNode result = solution.removeNthFromEnd(head, 2);

        assertSame(head, result);
        assertSame(second, result.next);
        assertSame(third, result.next.next);
        assertSame(fifth, result.next.next.next);
        assertNull(fifth.next);
        assertArrayEquals(new int[]{11, 22, 33, 55}, valuesOf(result));
    }

    @Test
    public void removingTheHeadReturnsTheOriginalSecondNode() {
        ListNode head = build(0, 25, 50, 75, 100);
        ListNode originalSecond = head.next;
        ListNode originalThird = originalSecond.next;

        ListNode result = solution.removeNthFromEnd(head, 5);

        assertSame(originalSecond, result);
        assertSame(originalThird, result.next);
        assertArrayEquals(new int[]{25, 50, 75, 100}, valuesOf(result));
    }

    @Test
    public void removingTheTailMutatesTheOriginalPredecessorLink() {
        ListNode head = build(3, 6, 9, 12);
        ListNode predecessor = head.next.next;

        ListNode result = solution.removeNthFromEnd(head, 1);

        assertSame(head, result);
        assertSame(predecessor, result.next.next);
        assertNull(predecessor.next);
        assertArrayEquals(new int[]{3, 6, 9}, valuesOf(result));
        // The removed node was bypassed; traversal of the returned chain stops at the predecessor.
        assertNull(result.next.next.next);
    }

    @Test
    public void repeatedCallsOnOneSolutionInstanceDoNotReusePointerState() {
        ListNode firstInput = build(10, 20, 30, 40, 50, 60);
        ListNode firstResult = solution.removeNthFromEnd(firstInput, 5);
        assertArrayEquals(new int[]{10, 30, 40, 50, 60}, valuesOf(firstResult));

        ListNode secondInput = build(1, 2, 3);
        ListNode secondResult = solution.removeNthFromEnd(secondInput, 1);
        assertSame(secondInput, secondResult);
        assertArrayEquals(new int[]{1, 2}, valuesOf(secondResult));

        ListNode thirdInput = build(7, 8, 9, 10);
        ListNode thirdResult = solution.removeNthFromEnd(thirdInput, 4);
        assertSame(thirdInput.next, thirdResult);
        assertArrayEquals(new int[]{8, 9, 10}, valuesOf(thirdResult));
    }

    @Test
    public void canRemoveFromTheListReturnedByAnEarlierCall() {
        ListNode originalHead = build(2, 4, 6, 8, 10);
        ListNode originalSecond = originalHead.next;
        ListNode originalFourth = originalSecond.next.next;
        ListNode originalTail = originalFourth.next;
        ListNode returnedHead = solution.removeNthFromEnd(originalHead, 3);

        ListNode secondResult = solution.removeNthFromEnd(returnedHead, 2);

        assertSame(originalHead, secondResult);
        assertSame(originalSecond, secondResult.next);
        assertSame(originalTail, secondResult.next.next);
        assertArrayEquals(new int[]{2, 4, 10}, valuesOf(secondResult));
    }

    private void assertRemoved(int[] expected, int n, int... input) {
        // Build a new mutable list for every invocation so no test shares link state.
        ListNode result = solution.removeNthFromEnd(build(input), n);
        assertArrayEquals(expected, valuesOf(result));
    }

    private ListNode build(int... values) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        for (int value : values) {
            current.next = new ListNode(value);
            current = current.next;
        }
        return dummy.next;
    }

    private int[] valuesOf(ListNode head) {
        List<Integer> values = new ArrayList<>();
        for (ListNode current = head; current != null; current = current.next) {
            values.add(current.val);
        }
        return values.stream().mapToInt(Integer::intValue).toArray();
    }
}
