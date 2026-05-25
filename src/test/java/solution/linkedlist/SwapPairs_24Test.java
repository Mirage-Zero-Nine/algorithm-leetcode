package solution.linkedlist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import library.listnode.ListNode;
import org.junit.jupiter.api.Test;

public class SwapPairs_24Test {

    private final SwapPairs_24 test = new SwapPairs_24();

    private ListNode build(int... vals) {
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int v : vals) { cur.next = new ListNode(v); cur = cur.next; }
        return dummy.next;
    }

    private List<Integer> toList(ListNode head) {
        List<Integer> res = new ArrayList<>();
        while (head != null) { res.add(head.val); head = head.next; }
        return res;
    }

    @Test
    public void testHappyCases() {
        ListNode result = test.swapPairs(build(1, 2, 3, 4));
        assertEquals(2, result.val);
        assertEquals(1, result.next.val);
        assertEquals(4, result.next.next.val);
        assertEquals(3, result.next.next.next.val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.swapPairs(null));
        assertEquals(1, test.swapPairs(build(1)).val);
    }

    @Test
    public void testLargeCase() {
        ListNode result = test.swapPairs(build(1, 2, 3, 4, 5, 6));
        assertEquals(2, result.val);
        assertEquals(6, result.next.next.next.next.val);
    }

    @Test
    public void testThreeElements() {
        ListNode result = test.swapPairs(build(1, 2, 3));
        assertEquals(2, result.val);
        assertEquals(1, result.next.val);
        assertEquals(3, result.next.next.val);
    }

    @Test
    public void testTwoElements() {
        ListNode result = test.swapPairs(build(1, 2));
        assertEquals(2, result.val);
        assertEquals(1, result.next.val);
        assertNull(result.next.next);
    }

    @Test
    public void testFiveElements() {
        ListNode result = test.swapPairs(build(5, 4, 3, 2, 1));
        assertEquals(4, result.val);
        assertEquals(5, result.next.val);
        assertEquals(2, result.next.next.val);
        assertEquals(3, result.next.next.next.val);
        assertEquals(1, result.next.next.next.next.val);
    }

    @Test
    public void testSameValues() {
        ListNode result = test.swapPairs(build(1, 1, 1, 1));
        assertEquals(1, result.val);
        assertEquals(1, result.next.val);
    }

    @Test
    public void testNegativeValues() {
        ListNode result = test.swapPairs(build(-1, -2, -3, -4));
        assertEquals(-2, result.val);
        assertEquals(-1, result.next.val);
        assertEquals(-4, result.next.next.val);
        assertEquals(-3, result.next.next.next.val);
    }

    @Test
    public void testGiantCase() {
        ListNode result = test.swapPairs(build(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        assertEquals(2, result.val);
        assertEquals(1, result.next.val);
        assertEquals(4, result.next.next.val);
        assertEquals(3, result.next.next.next.val);
    }

    @Test
    public void testEightElements() {
        ListNode result = test.swapPairs(build(10, 20, 30, 40, 50, 60, 70, 80));
        assertEquals(20, result.val);
        assertEquals(10, result.next.val);
        assertEquals(40, result.next.next.val);
    }

    // --- NEW TESTS ---

    @Test
    public void testNullReturnsNull() {
        assertNull(test.swapPairs(null));
    }

    @Test
    public void testSingleNodeUnchanged() {
        ListNode result = test.swapPairs(build(42));
        assertEquals(42, result.val);
        assertNull(result.next);
    }

    @Test
    public void testTwoNodesSwapped() {
        ListNode result = test.swapPairs(build(1, 2));
        assertEquals(List.of(2, 1), toList(result));
    }

    @Test
    public void testThreeNodesFirstTwoSwapped() {
        ListNode result = test.swapPairs(build(1, 2, 3));
        assertEquals(List.of(2, 1, 3), toList(result));
    }

    @Test
    public void testFourNodesTwoSwappedPairs() {
        ListNode result = test.swapPairs(build(1, 2, 3, 4));
        assertEquals(List.of(2, 1, 4, 3), toList(result));
    }

    @Test
    public void testEvenLengthSixNodes() {
        ListNode result = test.swapPairs(build(10, 20, 30, 40, 50, 60));
        assertEquals(List.of(20, 10, 40, 30, 60, 50), toList(result));
    }

    @Test
    public void testOddLengthSevenNodesLastUnchanged() {
        ListNode result = test.swapPairs(build(1, 2, 3, 4, 5, 6, 7));
        assertEquals(List.of(2, 1, 4, 3, 6, 5, 7), toList(result));
    }

    @Test
    public void testAllSameValueCheckPointers() {
        ListNode a = new ListNode(5);
        ListNode b = new ListNode(5);
        ListNode c = new ListNode(5);
        ListNode d = new ListNode(5);
        a.next = b; b.next = c; c.next = d;

        ListNode result = test.swapPairs(a);
        // After swap: b -> a -> d -> c
        assertNotSame(a, result);
        assertEquals(b, result);
        assertEquals(a, result.next);
        assertEquals(d, result.next.next);
        assertEquals(c, result.next.next.next);
        assertNull(result.next.next.next.next);
    }

    @Test
    public void testLong1000NodesSeeded() {
        Random rng = new Random(42L);
        int[] vals = new int[1000];
        for (int i = 0; i < 1000; i++) vals[i] = rng.nextInt();
        ListNode result = test.swapPairs(build(vals));
        List<Integer> actual = toList(result);
        assertEquals(1000, actual.size());
        // Verify pair-wise swap
        for (int i = 0; i < 1000; i += 2) {
            assertEquals(vals[i + 1], actual.get(i));
            assertEquals(vals[i], actual.get(i + 1));
        }
    }

    @Test
    public void testDoubleSwapRestoresOriginal() {
        int[] vals = {1, 2, 3, 4, 5, 6, 7, 8};
        ListNode first = test.swapPairs(build(vals));
        ListNode second = test.swapPairs(first);
        List<Integer> expected = new ArrayList<>();
        for (int v : vals) expected.add(v);
        assertEquals(expected, toList(second));
    }
}
