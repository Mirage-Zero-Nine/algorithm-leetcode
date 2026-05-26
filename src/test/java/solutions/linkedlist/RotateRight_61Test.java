package solutions.linkedlist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import library.listnode.ListNode;
import org.junit.jupiter.api.Test;

public class RotateRight_61Test {

    private final RotateRight_61 test = new RotateRight_61();

    private ListNode build(int... vals) {
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int v : vals) { cur.next = new ListNode(v); cur = cur.next; }
        return dummy.next;
    }

    @Test
    public void testHappyCases() {
        assertEquals(4, test.rotateRight(build(1, 2, 3, 4, 5), 2).val);
        assertEquals(2, test.rotateRight(build(0, 1, 2), 4).val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.rotateRight(null, 1));
        assertEquals(1, test.rotateRight(build(1), 5).val);
        assertEquals(1, test.rotateRight(build(1, 2), 0).val);
    }

    @Test
    public void testLargeCase() {
        assertEquals(5, test.rotateRight(build(1, 2, 3, 4, 5, 6, 7), 3).val);
    }

    @Test
    public void testRotateByLength() {
        // rotating by length should return same list
        ListNode result = test.rotateRight(build(1, 2, 3), 3);
        assertEquals(1, result.val);
        assertEquals(2, result.next.val);
        assertEquals(3, result.next.next.val);
    }

    @Test
    public void testRotateByMultipleOfLength() {
        ListNode result = test.rotateRight(build(1, 2, 3), 6);
        assertEquals(1, result.val);
    }

    @Test
    public void testRotateByOne() {
        ListNode result = test.rotateRight(build(1, 2, 3, 4), 1);
        assertEquals(4, result.val);
        assertEquals(1, result.next.val);
    }

    @Test
    public void testTwoElementsRotateOne() {
        ListNode result = test.rotateRight(build(1, 2), 1);
        assertEquals(2, result.val);
        assertEquals(1, result.next.val);
    }

    @Test
    public void testKLargerThanLength() {
        ListNode result = test.rotateRight(build(1, 2, 3), 5);
        assertEquals(2, result.val);
        assertEquals(3, result.next.val);
        assertEquals(1, result.next.next.val);
    }

    @Test
    public void testGiantCase() {
        ListNode result = test.rotateRight(build(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 7);
        assertEquals(4, result.val);
    }

    @Test
    public void testRotateTwo() {
        ListNode result = test.rotateRight(build(1, 2, 3, 4, 5), 2);
        assertEquals(4, result.val);
        assertEquals(5, result.next.val);
        assertEquals(1, result.next.next.val);
    }

    // --- NEW TESTS ---

    @Test
    public void testNullHeadReturnsNull() {
        assertNull(test.rotateRight(null, 0));
        assertNull(test.rotateRight(null, 100));
    }

    @Test
    public void testKZeroReturnsUnchanged() {
        ListNode result = test.rotateRight(build(1, 2, 3, 4, 5), 0);
        assertEquals(List.of(1, 2, 3, 4, 5), toList(result));
    }

    @Test
    public void testKEqualsLengthReturnsUnchanged() {
        ListNode result = test.rotateRight(build(1, 2, 3, 4, 5), 5);
        assertEquals(List.of(1, 2, 3, 4, 5), toList(result));
    }

    @Test
    public void testKEqualsTwiceLengthReturnsUnchanged() {
        ListNode result = test.rotateRight(build(1, 2, 3, 4, 5), 10);
        assertEquals(List.of(1, 2, 3, 4, 5), toList(result));
    }

    @Test
    public void testKEqualsLengthPlusOneRotatesByOne() {
        // length=5, k=6 => effective rotation by 1
        ListNode result = test.rotateRight(build(1, 2, 3, 4, 5), 6);
        assertEquals(List.of(5, 1, 2, 3, 4), toList(result));
    }

    @Test
    public void testSingleNodeAnyK() {
        assertEquals(List.of(99), toList(test.rotateRight(build(99), 0)));
        assertEquals(List.of(99), toList(test.rotateRight(build(99), 1)));
        assertEquals(List.of(99), toList(test.rotateRight(build(99), 1000)));
    }

    @Test
    public void testTwoNodesKOneSwapped() {
        ListNode result = test.rotateRight(build(1, 2), 1);
        assertEquals(List.of(2, 1), toList(result));
        assertNull(result.next.next);
    }

    @Test
    public void testLeetCodeExample() {
        // [1,2,3,4,5] k=2 -> [4,5,1,2,3]
        ListNode result = test.rotateRight(build(1, 2, 3, 4, 5), 2);
        assertEquals(List.of(4, 5, 1, 2, 3), toList(result));
    }

    @Test
    public void testLargeKModCycle() {
        // length=4, k=13 => 13%4=1, rotate by 1
        ListNode result = test.rotateRight(build(10, 20, 30, 40), 13);
        assertEquals(List.of(40, 10, 20, 30), toList(result));
    }

    @Test
    public void testLongList100NodesPreservesMultisetAndLength() {
        Random rng = new Random(42L);
        int[] vals = new int[100];
        for (int i = 0; i < 100; i++) vals[i] = rng.nextInt(1000);

        List<Integer> original = new ArrayList<>();
        for (int v : vals) original.add(v);

        ListNode result = test.rotateRight(build(vals), 73);
        List<Integer> rotated = toList(result);

        // length preserved
        assertEquals(100, rotated.size());
        // same multiset (sorted comparison)
        List<Integer> sortedOrig = new ArrayList<>(original);
        List<Integer> sortedResult = new ArrayList<>(rotated);
        Collections.sort(sortedOrig);
        Collections.sort(sortedResult);
        assertEquals(sortedOrig, sortedResult);
    }

    private List<Integer> toList(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) { list.add(head.val); head = head.next; }
        return list;
    }
}
