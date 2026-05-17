package solution.linkedlist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigInteger;

import library.listnode.ListNode;
import org.junit.jupiter.api.Test;

public class AddTwoNumbers_2Test {

    private final AddTwoNumbers_2 test = new AddTwoNumbers_2();

    private ListNode build(int... vals) {
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int v : vals) { cur.next = new ListNode(v); cur = cur.next; }
        return dummy.next;
    }

    @Test
    public void testHappyCases() {
        // 342 + 465 = 807
        ListNode result = test.addTwoNumbers(build(2, 4, 3), build(5, 6, 4));
        assertEquals(7, result.val);
        assertEquals(0, result.next.val);
        assertEquals(8, result.next.next.val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.addTwoNumbers(null, null));
        // 0 + 0 = 0
        assertEquals(0, test.addTwoNumbers(build(0), build(0)).val);
    }

    @Test
    public void testLargeCase() {
        // 9999999 + 9999999 = 19999998
        ListNode result = test.addTwoNumbers(build(9, 9, 9, 9, 9, 9, 9), build(9, 9, 9, 9));
        assertEquals(8, result.val);
        assertEquals(1, result.next.next.next.next.next.next.next.val);
    }

    @Test
    public void testDifferentLengths() {
        // 99 + 1 = 100
        ListNode result = test.addTwoNumbers(build(9, 9), build(1));
        assertEquals(0, result.val);
        assertEquals(0, result.next.val);
        assertEquals(1, result.next.next.val);
    }

    @Test
    public void testOneNullInput() {
        ListNode result = test.addTwoNumbers(build(1, 2, 3), null);
        assertEquals(1, result.val);
        assertEquals(2, result.next.val);
        assertEquals(3, result.next.next.val);
    }

    @Test
    public void testCarryPropagation() {
        // 999 + 1 = 1000
        ListNode result = test.addTwoNumbers(build(9, 9, 9), build(1));
        assertEquals(0, result.val);
        assertEquals(0, result.next.val);
        assertEquals(0, result.next.next.val);
        assertEquals(1, result.next.next.next.val);
    }

    @Test
    public void testNoCarry() {
        // 123 + 456 = 579
        ListNode result = test.addTwoNumbers(build(3, 2, 1), build(6, 5, 4));
        assertEquals(9, result.val);
        assertEquals(7, result.next.val);
        assertEquals(5, result.next.next.val);
    }

    @Test
    public void testSingleDigits() {
        // 5 + 5 = 10
        ListNode result = test.addTwoNumbers(build(5), build(5));
        assertEquals(0, result.val);
        assertEquals(1, result.next.val);
    }

    @Test
    public void testRecursionVersion() {
        ListNode result = test.addTwoNumbersRecursion(build(2, 4, 3), build(5, 6, 4));
        assertEquals(7, result.val);
        assertEquals(0, result.next.val);
        assertEquals(8, result.next.next.val);
    }

    @Test
    public void testRecursionBothNull() {
        assertNull(test.addTwoNumbersRecursion(null, null));
    }

    @Test
    public void testGiantCase() {
        // Two 100-digit numbers all 9s: sum is 199...98
        int n = 100;
        int[] nines = new int[n];
        for (int i = 0; i < n; i++) nines[i] = 9;
        ListNode result = test.addTwoNumbers(build(nines), build(nines));
        assertEquals(8, result.val);
        assertEquals(9, result.next.val);
        // last node should be 1 (carry)
        ListNode cur = result;
        while (cur.next != null) cur = cur.next;
        assertEquals(1, cur.val);
    }

    // --- NEW TESTS ---

    @Test
    public void testBothSingleNodeNoCarry() {
        // 5 + 4 = 9
        ListNode result = test.addTwoNumbers(build(5), build(4));
        assertEquals(9, result.val);
        assertNull(result.next);
    }

    @Test
    public void testBothSingleNodeWithCarry() {
        // 9 + 1 = 10 -> [0, 1]
        ListNode result = test.addTwoNumbers(build(9), build(1));
        assertEquals(0, result.val);
        assertEquals(1, result.next.val);
        assertNull(result.next.next);
    }

    @Test
    public void testDifferentLengthCarryPropagatesAll() {
        // 9999 + 1 = 10000 -> [0,0,0,0,1]
        ListNode result = test.addTwoNumbers(build(9, 9, 9, 9), build(1));
        assertEquals(0, result.val);
        assertEquals(0, result.next.val);
        assertEquals(0, result.next.next.val);
        assertEquals(0, result.next.next.next.val);
        assertEquals(1, result.next.next.next.next.val);
        assertNull(result.next.next.next.next.next);
    }

    @Test
    public void testAddZeroToList() {
        // 12345 + 0 = 12345 -> [5,4,3,2,1] + [0] = [5,4,3,2,1]
        ListNode result = test.addTwoNumbers(build(5, 4, 3, 2, 1), build(0));
        assertListEquals(new int[]{5, 4, 3, 2, 1}, result);
    }

    @Test
    public void testOneNullOtherNotNull() {
        // null + [3,2,1] = [3,2,1]
        ListNode result = test.addTwoNumbers(null, build(3, 2, 1));
        assertListEquals(new int[]{3, 2, 1}, result);
        // [7,8] + null = [7,8]
        ListNode result2 = test.addTwoNumbers(build(7, 8), null);
        assertListEquals(new int[]{7, 8}, result2);
    }

    @Test
    public void testRecursionOneNullAndCarry() {
        // Recursion: null + null = null, 9999 + 1 = 10000
        assertNull(test.addTwoNumbersRecursion(null, null));
        ListNode result = test.addTwoNumbersRecursion(build(9, 9, 9, 9), build(1));
        assertListEquals(new int[]{0, 0, 0, 0, 1}, result);
    }

    @Test
    public void testRecursionOneNull() {
        ListNode result = test.addTwoNumbersRecursion(null, build(4, 5, 6));
        assertListEquals(new int[]{4, 5, 6}, result);
    }

    @Test
    public void testLeadingZeros() {
        // [0,0,1] represents 100, [0,0,2] represents 200 -> 300 = [0,0,3]
        ListNode result = test.addTwoNumbers(build(0, 0, 1), build(0, 0, 2));
        assertListEquals(new int[]{0, 0, 3}, result);
    }

    @Test
    public void testLong150DigitsBigIntegerOracle() {
        // 150-digit numbers: cross-check with BigInteger
        int n = 150;
        int[] digits1 = new int[n], digits2 = new int[n];
        StringBuilder sb1 = new StringBuilder(), sb2 = new StringBuilder();
        for (int i = 0; i < n; i++) {
            digits1[i] = (i * 3 + 7) % 10;
            digits2[i] = (i * 7 + 3) % 10;
        }
        // BigInteger expects most-significant digit first, list is reversed
        for (int i = n - 1; i >= 0; i--) sb1.append(digits1[i]);
        for (int i = n - 1; i >= 0; i--) sb2.append(digits2[i]);
        BigInteger expected = new BigInteger(sb1.toString()).add(new BigInteger(sb2.toString()));
        ListNode result = test.addTwoNumbers(build(digits1), build(digits2));
        // Convert result list to BigInteger
        StringBuilder sbResult = new StringBuilder();
        for (ListNode cur = result; cur != null; cur = cur.next) sbResult.append(cur.val);
        String reversedResult = sbResult.reverse().toString();
        assertEquals(expected, new BigInteger(reversedResult));
    }

    @Test
    public void testPropertyBigIntegerCrossCheck() {
        // Property: iterative and recursion produce same result matching BigInteger
        int[][] pairs = {{9, 9, 9}, {1}, {5, 6, 7, 8}, {3, 4}, {0}, {0}};
        for (int i = 0; i < pairs.length; i += 2) {
            ListNode l1 = build(pairs[i]), l2 = build(pairs[i + 1]);
            BigInteger bi1 = listToBigInt(l1), bi2 = listToBigInt(l2);
            BigInteger expectedBi = bi1.add(bi2);
            ListNode iterResult = test.addTwoNumbers(build(pairs[i]), build(pairs[i + 1]));
            ListNode recurResult = test.addTwoNumbersRecursion(build(pairs[i]), build(pairs[i + 1]));
            assertEquals(expectedBi, listToBigInt(iterResult));
            assertEquals(expectedBi, listToBigInt(recurResult));
        }
    }

    // --- Helpers ---

    private void assertListEquals(int[] expected, ListNode head) {
        for (int val : expected) {
            assertEquals(val, head.val);
            head = head.next;
        }
        assertNull(head);
    }

    private BigInteger listToBigInt(ListNode head) {
        StringBuilder sb = new StringBuilder();
        for (ListNode cur = head; cur != null; cur = cur.next) sb.append(cur.val);
        String reversed = sb.reverse().toString();
        return reversed.isEmpty() ? BigInteger.ZERO : new BigInteger(reversed);
    }
}
