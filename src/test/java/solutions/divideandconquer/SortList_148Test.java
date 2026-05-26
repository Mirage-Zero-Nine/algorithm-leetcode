package solutions.divideandconquer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import library.listnode.ListNode;
import org.junit.jupiter.api.Test;

public class SortList_148Test {

    private final SortList_148 test = new SortList_148();

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

    private List<Integer> toList(ListNode n) {
        List<Integer> list = new ArrayList<>();
        while (n != null) { list.add(n.val); n = n.next; }
        return list;
    }

    @Test
    public void testHappyCases() {
        ListNode result = test.sortList(build(4, 2, 1, 3));
        assertEquals(1, result.val);
        assertEquals(4, result.next.next.next.val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.sortList(null));
        assertEquals(1, test.sortList(build(1)).val);
        ListNode result = test.sortList(build(-1, 5, 3, 4, 0));
        assertEquals(-1, result.val);
    }

    @Test
    public void testLargeCase() {
        ListNode result = test.sortList(build(10, 9, 8, 7, 6, 5, 4, 3, 2, 1));
        assertEquals(1, result.val);
        assertEquals(10, result.next.next.next.next.next.next.next.next.next.val);
    }

    @Test
    public void testAlreadySorted() {
        assertEquals("1,2,3,4,5,", toStr(test.sortList(build(1, 2, 3, 4, 5))));
    }

    @Test
    public void testTwoElements() {
        assertEquals("1,2,", toStr(test.sortList(build(2, 1))));
    }

    @Test
    public void testDuplicates() {
        assertEquals("1,1,2,2,3,", toStr(test.sortList(build(3, 1, 2, 1, 2))));
    }

    @Test
    public void testAllSameValues() {
        assertEquals("5,5,5,5,", toStr(test.sortList(build(5, 5, 5, 5))));
    }

    @Test
    public void testNegativeValues() {
        assertEquals("-5,-3,-1,0,2,", toStr(test.sortList(build(0, -3, 2, -5, -1))));
    }

    @Test
    public void testReverseSorted() {
        assertEquals("1,2,3,4,5,6,7,8,", toStr(test.sortList(build(8, 7, 6, 5, 4, 3, 2, 1))));
    }

    @Test
    public void testGiantCase() {
        int[] vals = new int[1000];
        for (int i = 0; i < 1000; i++) vals[i] = 1000 - i;
        ListNode result = test.sortList(build(vals));
        assertEquals(1, result.val);
        // Traverse to last
        ListNode cur = result;
        while (cur.next != null) cur = cur.next;
        assertEquals(1000, cur.val);
    }

    @Test
    public void testTwoNodesSorted() {
        assertEquals("1,2,", toStr(test.sortList(build(1, 2))));
    }

    @Test
    public void testTwoNodesReversed() {
        assertEquals("3,7,", toStr(test.sortList(build(7, 3))));
    }

    @Test
    public void testAllNegativeValues() {
        assertEquals("-10,-7,-3,-2,-1,", toStr(test.sortList(build(-3, -7, -1, -10, -2))));
    }

    @Test
    public void testMixNegativeAndPositive() {
        assertEquals("-5,-2,0,1,3,7,", toStr(test.sortList(build(3, -2, 7, 0, -5, 1))));
    }

    @Test
    public void testLongRandomListCrossCheckWithArraysSort() {
        Random rng = new Random(42L);
        int[] vals = new int[1500];
        for (int i = 0; i < vals.length; i++) vals[i] = rng.nextInt(20001) - 10000;

        int[] expected = vals.clone();
        Arrays.sort(expected);

        ListNode result = test.sortList(build(vals));
        List<Integer> resultList = toList(result);

        assertEquals(vals.length, resultList.size());
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], resultList.get(i), "Mismatch at index " + i);
        }
    }

    @Test
    public void testPropertyResultLengthEqualsInput() {
        int[] vals = {9, 1, 5, 3, 8, 2, 7, 4, 6};
        ListNode result = test.sortList(build(vals));
        assertEquals(vals.length, toList(result).size());
    }

    @Test
    public void testPropertyResultIsPermutationOfInput() {
        int[] vals = {4, 1, 9, 2, 7, 3, 8, 5, 6};
        List<Integer> input = new ArrayList<>();
        for (int v : vals) input.add(v);
        Collections.sort(input);

        ListNode result = test.sortList(build(vals));
        List<Integer> output = toList(result);
        Collections.sort(output);

        assertEquals(input, output);
    }

    @Test
    public void testPropertyResultIsNonDecreasing() {
        Random rng = new Random(42L);
        int[] vals = new int[500];
        for (int i = 0; i < vals.length; i++) vals[i] = rng.nextInt(1000) - 500;

        ListNode result = test.sortList(build(vals));
        List<Integer> list = toList(result);

        for (int i = 1; i < list.size(); i++) {
            assertTrue(list.get(i - 1) <= list.get(i),
                    "Not non-decreasing at index " + i + ": " + list.get(i - 1) + " > " + list.get(i));
        }
    }

    @Test
    public void testReverseSortedLongerList() {
        int[] vals = new int[20];
        for (int i = 0; i < 20; i++) vals[i] = 20 - i;
        ListNode result = test.sortList(build(vals));
        List<Integer> list = toList(result);
        for (int i = 0; i < 20; i++) {
            assertEquals(i + 1, list.get(i));
        }
    }
}
