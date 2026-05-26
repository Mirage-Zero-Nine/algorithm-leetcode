package solutions.linkedlist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import library.listnode.ListNode;
import org.junit.jupiter.api.Test;

public class ReverseKGroup_25Test {

    private final ReverseKGroup_25 test = new ReverseKGroup_25();

    private ListNode build(int... vals) {
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int v : vals) { cur.next = new ListNode(v); cur = cur.next; }
        return dummy.next;
    }

    private String toStr(ListNode n) {
        StringBuilder sb = new StringBuilder();
        while (n != null) { sb.append(n.val).append(","); n = n.next; }
        return sb.toString();
    }

    @Test
    public void testHappyCases() {
        ListNode result = test.reverseKGroup(build(1, 2, 3, 4, 5), 2);
        assertEquals(2, result.val);
        assertEquals(1, result.next.val);
        assertEquals(5, result.next.next.next.next.val);
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.reverseKGroup(build(1), 1).val);
        ListNode result = test.reverseKGroup(build(1, 2, 3, 4, 5), 3);
        assertEquals(3, result.val);
        assertEquals(4, result.next.next.next.val);
    }

    @Test
    public void testLargeCase() {
        ListNode result = test.reverseKGroup(build(1, 2, 3, 4, 5, 6), 3);
        assertEquals(3, result.val);
        assertEquals(6, result.next.next.next.val);
    }

    @Test
    public void testNullInput() {
        assertNull(test.reverseKGroup(null, 2));
    }

    @Test
    public void testKEqualsLength() {
        ListNode result = test.reverseKGroup(build(1, 2, 3, 4, 5), 5);
        assertEquals("5,4,3,2,1,", toStr(result));
    }

    @Test
    public void testKGreaterThanLength() {
        ListNode result = test.reverseKGroup(build(1, 2, 3), 5);
        assertEquals("1,2,3,", toStr(result));
    }

    @Test
    public void testKEqualsOne() {
        ListNode result = test.reverseKGroup(build(1, 2, 3, 4), 1);
        assertEquals("1,2,3,4,", toStr(result));
    }

    @Test
    public void testKEqualsTwo() {
        ListNode result = test.reverseKGroup(build(1, 2, 3, 4), 2);
        assertEquals("2,1,4,3,", toStr(result));
    }

    @Test
    public void testRemainder() {
        // 5 elements, k=2 -> last element stays
        ListNode result = test.reverseKGroup(build(1, 2, 3, 4, 5), 2);
        assertEquals("2,1,4,3,5,", toStr(result));
    }

    @Test
    public void testRecursionMethod() {
        ListNode result = test.reverseKGroupRecursion(build(1, 2, 3, 4, 5), 2);
        assertEquals("2,1,4,3,5,", toStr(result));
    }

    @Test
    public void testGiantCase() {
        int[] vals = new int[100];
        for (int i = 0; i < 100; i++) vals[i] = i + 1;
        ListNode result = test.reverseKGroup(build(vals), 10);
        // First element should be 10 (first group reversed)
        assertEquals(10, result.val);
    }

    @Test
    public void testKEquals2OnEvenLength() {
        ListNode result = test.reverseKGroup(build(1, 2, 3, 4, 5, 6), 2);
        assertEquals("2,1,4,3,6,5,", toStr(result));
    }

    @Test
    public void testKEquals2OnOddLength() {
        // last single node unchanged
        ListNode result = test.reverseKGroup(build(1, 2, 3, 4, 5, 6, 7), 2);
        assertEquals("2,1,4,3,6,5,7,", toStr(result));
    }

    @Test
    public void testKEquals3OnLength6() {
        // two fully reversed groups
        ListNode result = test.reverseKGroup(build(1, 2, 3, 4, 5, 6), 3);
        assertEquals("3,2,1,6,5,4,", toStr(result));
    }

    @Test
    public void testLengthNotDivisibleByK() {
        // 7 elements, k=3 -> two groups reversed, last element stays
        ListNode result = test.reverseKGroup(build(1, 2, 3, 4, 5, 6, 7), 3);
        assertEquals("3,2,1,6,5,4,7,", toStr(result));
    }

    @Test
    public void testLengthExactlyDivisibleByK() {
        // 9 elements, k=3 -> three groups all reversed
        ListNode result = test.reverseKGroup(build(1, 2, 3, 4, 5, 6, 7, 8, 9), 3);
        assertEquals("3,2,1,6,5,4,9,8,7,", toStr(result));
    }

    @Test
    public void testLongListWithK10VerifiedByReference() {
        // 105 nodes with seeded random values, k=10
        Random rng = new Random(42L);
        int[] vals = new int[105];
        for (int i = 0; i < 105; i++) vals[i] = rng.nextInt(1000);

        ListNode resultIter = test.reverseKGroup(build(vals), 10);
        ListNode resultRecur = test.reverseKGroupRecursion(build(vals), 10);

        // Both methods should produce the same result
        assertEquals(toStr(resultRecur), toStr(resultIter));

        // Verify first group is reversed (elements 0-9 reversed)
        ListNode cur = resultIter;
        for (int i = 9; i >= 0; i--) {
            assertEquals(vals[i], cur.val);
            cur = cur.next;
        }
        // Last 5 elements (105 % 10 = 5) should remain in original order
        ListNode tail = resultIter;
        for (int i = 0; i < 100; i++) tail = tail.next;
        for (int i = 100; i < 105; i++) {
            assertEquals(vals[i], tail.val);
            tail = tail.next;
        }
    }

    @Test
    public void testPropertyNodeCountPreserved() {
        ListNode input = build(1, 2, 3, 4, 5, 6, 7, 8);
        ListNode result = test.reverseKGroup(input, 3);
        int count = 0;
        while (result != null) { count++; result = result.next; }
        assertEquals(8, count);
    }

    @Test
    public void testPropertyValuesArePermutation() {
        int[] vals = {5, 3, 8, 1, 9, 2, 7, 4, 6};
        ListNode result = test.reverseKGroup(build(vals), 4);

        List<Integer> original = new ArrayList<>();
        for (int v : vals) original.add(v);

        List<Integer> resultVals = new ArrayList<>();
        while (result != null) { resultVals.add(result.val); result = result.next; }

        Collections.sort(original);
        Collections.sort(resultVals);
        assertEquals(original, resultVals);
    }

    @Test
    public void testSingleElementKEquals1() {
        ListNode result = test.reverseKGroup(build(42), 1);
        assertEquals("42,", toStr(result));
    }

    @Test
    public void testRecursionMethodKEquals3OnLength6() {
        ListNode result = test.reverseKGroupRecursion(build(1, 2, 3, 4, 5, 6), 3);
        assertEquals("3,2,1,6,5,4,", toStr(result));
    }
}
