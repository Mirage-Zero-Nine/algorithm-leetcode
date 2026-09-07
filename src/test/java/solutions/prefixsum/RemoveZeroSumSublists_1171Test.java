package solutions.prefixsum;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.listnode.ListNode;
import org.junit.jupiter.api.Test;

public class RemoveZeroSumSublists_1171Test {

    @org.junit.jupiter.params.ParameterizedTest
    @org.junit.jupiter.params.provider.ValueSource(ints = {0, 1, 2, 7, 19, 42, 97, 211, 2026, 65537})
    void outputIsReachableByLegalZeroSumDeletions(int seed) {
        java.util.Random random = new java.util.Random(seed);
        for (int trial = 0; trial < 50; trial++) {
            int[] values = random.ints(1 + random.nextInt(7), -3, 4).toArray();
            java.util.List<Integer> input = java.util.Arrays.stream(values).boxed().toList();
            java.util.Set<java.util.List<Integer>> terminal = new java.util.HashSet<>();
            java.util.Set<java.util.List<Integer>> visited = new java.util.HashSet<>();
            java.util.ArrayDeque<java.util.List<Integer>> queue = new java.util.ArrayDeque<>();
            queue.add(input);
            visited.add(input);
            while (!queue.isEmpty()) {
                java.util.List<Integer> state = queue.remove();
                boolean canDelete = false;
                for (int left = 0; left < state.size(); left++) {
                    int sum = 0;
                    for (int right = left; right < state.size(); right++) {
                        sum += state.get(right);
                        if (sum != 0) continue;
                        canDelete = true;
                        java.util.List<Integer> next = new java.util.ArrayList<>(state);
                        next.subList(left, right + 1).clear();
                        if (visited.add(next)) queue.add(next);
                    }
                }
                if (!canDelete) terminal.add(state);
            }
            ListNode current = test.removeZeroSumSublists(build(values));
            java.util.List<Integer> actual = new java.util.ArrayList<>();
            for (int i = 0; current != null && i <= values.length; i++, current = current.next) actual.add(current.val);
            assertNull(current, "Result contains a cycle or additional nodes");
            org.junit.jupiter.api.Assertions.assertTrue(terminal.contains(actual), input + " -> " + actual);
        }
    }

    @Test
    void nestedCancellationLeavesOnlyTheLastOriginalNode() {
        int[] values = new int[999];
        for (int i = 0; i < 499; i++) {
            values[i] = i + 1;
            values[997 - i] = -i - 1;
        }
        values[998] = 1000;
        ListNode result = test.removeZeroSumSublists(build(values));
        assertEquals(1000, result.val);
        assertNull(result.next);
    }


    private final RemoveZeroSumSublists_1171 test = new RemoveZeroSumSublists_1171();

    private ListNode build(int... vals) {
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int v : vals) { cur.next = new ListNode(v); cur = cur.next; }
        return dummy.next;
    }

    @Test
    public void testHappyCases() {
        ListNode result = test.removeZeroSumSublists(build(1, 2, -3, 3, 1));
        assertEquals(3, result.val);
        assertEquals(1, result.next.val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.removeZeroSumSublists(build(1, -1)));
        assertEquals(1, test.removeZeroSumSublists(build(1)).val);
    }

    @Test
    public void testLargeCase() {
        ListNode result = test.removeZeroSumSublists(build(1, 2, 3, -3, -2, 4));
        assertEquals(1, result.val);
        assertEquals(4, result.next.val);
    }

    @Test
    public void testAllZeroSum() {
        assertNull(test.removeZeroSumSublists(build(1, 2, 3, -6)));
    }

    @Test
    public void testNoRemoval() {
        ListNode result = test.removeZeroSumSublists(build(1, 2, 3));
        assertEquals(1, result.val);
        assertEquals(2, result.next.val);
        assertEquals(3, result.next.next.val);
    }

    @Test
    public void testMultipleZeroSumGroups() {
        // [1, -1, 2, -2, 3] -> [3]
        ListNode result = test.removeZeroSumSublists(build(1, -1, 2, -2, 3));
        assertEquals(3, result.val);
        assertNull(result.next);
    }

    @Test
    public void testZeroValueNode() {
        // [0] sums to 0
        assertNull(test.removeZeroSumSublists(build(0)));
    }

    @Test
    public void testZeroInMiddle() {
        // [1, 0, 2] -> 0 alone sums to 0, removed
        ListNode result = test.removeZeroSumSublists(build(1, 0, 2));
        assertEquals(1, result.val);
        assertEquals(2, result.next.val);
    }

    @Test
    public void testNegativeOnly() {
        // [-1, -2, -3] no consecutive sum to 0
        ListNode result = test.removeZeroSumSublists(build(-1, -2, -3));
        assertEquals(-1, result.val);
        assertEquals(-2, result.next.val);
        assertEquals(-3, result.next.next.val);
    }

    @Test
    public void testComplexCase() {
        // [1, 2, -2, -1, 5] -> 2 + (-2) = 0 removed -> [1, -1, 5] -> 1 + (-1) = 0 removed -> [5]
        ListNode result = test.removeZeroSumSublists(build(1, 2, -2, -1, 5));
        assertEquals(5, result.val);
        assertNull(result.next);
    }

    @Test
    public void testGiantCase() {
        // Build a list of 500 elements that all cancel: [1, -1, 1, -1, ..., 1, -1, 99]
        int n = 501;
        int[] vals = new int[n];
        for (int i = 0; i < n - 1; i++) vals[i] = (i % 2 == 0) ? 1 : -1;
        vals[n - 1] = 99;
        ListNode result = test.removeZeroSumSublists(build(vals));
        assertEquals(99, result.val);
        assertNull(result.next);
    }
}
