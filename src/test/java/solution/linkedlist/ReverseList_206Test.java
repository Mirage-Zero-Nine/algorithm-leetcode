package solution.linkedlist;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.listnode.ListNode;
import org.junit.jupiter.api.Test;

public class ReverseList_206Test {

    private final ReverseList_206 test = new ReverseList_206();

    private ListNode build(int... vals) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (int v : vals) { cur.next = new ListNode(v); cur = cur.next; }
        return dummy.next;
    }

    private int[] toArray(ListNode head) {
        int count = 0;
        ListNode cur = head;
        while (cur != null) { count++; cur = cur.next; }
        int[] arr = new int[count];
        cur = head;
        for (int i = 0; i < count; i++) { arr[i] = cur.val; cur = cur.next; }
        return arr;
    }

    @Test
    public void testHappyCases() {
        ListNode result = test.reverseList(build(1, 2, 3, 4, 5));
        assertEquals(5, toArray(result)[0]);
        assertEquals(1, toArray(result)[4]);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.reverseList(null));
        ListNode single = build(1);
        assertEquals(1, test.reverseList(single).val);
    }

    @Test
    public void testLargeCase() {
        int[] vals = new int[100];
        for (int i = 0; i < 100; i++) vals[i] = i + 1;
        ListNode result = test.reverseList(build(vals));
        assertEquals(100, result.val);
    }

    @Test
    public void testTwoElements() {
        ListNode result = test.reverseList(build(1, 2));
        assertEquals(2, result.val);
        assertEquals(1, result.next.val);
        assertNull(result.next.next);
    }

    @Test
    public void testThreeElements() {
        int[] arr = toArray(test.reverseList(build(1, 2, 3)));
        assertEquals(3, arr[0]);
        assertEquals(2, arr[1]);
        assertEquals(1, arr[2]);
    }

    @Test
    public void testNegativeValues() {
        ListNode result = test.reverseList(build(-3, -2, -1));
        assertEquals(-1, result.val);
        assertEquals(-2, result.next.val);
        assertEquals(-3, result.next.next.val);
    }

    @Test
    public void testDuplicateValues() {
        int[] arr = toArray(test.reverseList(build(1, 1, 2, 2)));
        assertEquals(2, arr[0]);
        assertEquals(2, arr[1]);
        assertEquals(1, arr[2]);
        assertEquals(1, arr[3]);
    }

    @Test
    public void testRecursionNull() {
        assertNull(test.recursion(null));
    }

    @Test
    public void testRecursionSingle() {
        assertEquals(5, test.recursion(build(5)).val);
    }

    @Test
    public void testRecursionMultiple() {
        ListNode result = test.recursion(build(1, 2, 3, 4, 5));
        assertEquals(5, result.val);
        assertEquals(1, toArray(result)[4]);
    }

    @Test
    public void testGiantCase() {
        int n = 1000;
        int[] vals = new int[n];
        for (int i = 0; i < n; i++) vals[i] = i;
        ListNode result = test.reverseList(build(vals));
        assertEquals(999, result.val);
        assertEquals(998, result.next.val);
    }

    // --- NEW TESTS ---

    @Test
    public void testNullHeadReturnsNull() {
        assertNull(test.reverseList(null));
        assertNull(test.recursion(null));
    }

    @Test
    public void testSingleNodeNextIsNull() {
        ListNode single = build(42);
        ListNode r1 = test.reverseList(single);
        assertEquals(42, r1.val);
        assertNull(r1.next);

        ListNode single2 = build(42);
        ListNode r2 = test.recursion(single2);
        assertEquals(42, r2.val);
        assertNull(r2.next);
    }

    @Test
    public void testTwoNodesBothMethods() {
        int[] expected = {2, 1};
        assertArrayEquals(expected, toArray(test.reverseList(build(1, 2))));
        assertArrayEquals(expected, toArray(test.recursion(build(1, 2))));
    }

    @Test
    public void testAllSameValues() {
        int[] expected = {7, 7, 7, 7, 7};
        assertArrayEquals(expected, toArray(test.reverseList(build(7, 7, 7, 7, 7))));
        assertArrayEquals(expected, toArray(test.recursion(build(7, 7, 7, 7, 7))));
    }

    @Test
    public void testLongListLengthAndHeadTail() {
        int n = 2000;
        int[] vals = new int[n];
        for (int i = 0; i < n; i++) vals[i] = i + 1;
        ListNode result = test.reverseList(build(vals));
        int[] reversed = toArray(result);
        assertEquals(n, reversed.length);
        assertEquals(n, reversed[0]);       // head value == old tail
        assertEquals(1, reversed[n - 1]);   // tail value == old head
    }

    @Test
    public void testReverseOfReverseIsOriginal() {
        int[] original = {3, 1, 4, 1, 5, 9, 2, 6};
        ListNode reversed = test.reverseList(build(original));
        ListNode doubleReversed = test.reverseList(reversed);
        assertArrayEquals(original, toArray(doubleReversed));
    }

    @Test
    public void testNegativeValuesList() {
        int[] input = {-10, -20, -30, -40};
        int[] expected = {-40, -30, -20, -10};
        assertArrayEquals(expected, toArray(test.reverseList(build(input))));
        assertArrayEquals(expected, toArray(test.recursion(build(input))));
    }

    @Test
    public void testIntMinAndIntMax() {
        int[] input = {Integer.MIN_VALUE, 0, Integer.MAX_VALUE};
        int[] expected = {Integer.MAX_VALUE, 0, Integer.MIN_VALUE};
        assertArrayEquals(expected, toArray(test.reverseList(build(input))));
        assertArrayEquals(expected, toArray(test.recursion(build(input))));
    }

    @Test
    public void testIterativeAndRecursiveCrossCheck() {
        int[] input = {5, 3, 8, 1, 9, 2, 7, 4, 6};
        int[] iterResult = toArray(test.reverseList(build(input)));
        int[] recurResult = toArray(test.recursion(build(input)));
        assertArrayEquals(iterResult, recurResult);
    }

    @Test
    public void testRecursionReverseOfReverseIsOriginal() {
        int[] original = {100, -50, 0, 42, Integer.MAX_VALUE};
        ListNode reversed = test.recursion(build(original));
        ListNode doubleReversed = test.recursion(reversed);
        assertArrayEquals(original, toArray(doubleReversed));
    }
}
