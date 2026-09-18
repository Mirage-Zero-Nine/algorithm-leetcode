package solutions.linkedlist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Stream;

import library.listnode.ListNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ReorderList_143Test {

    private final ReorderList_143 solution = new ReorderList_143();

    /**
     * Every shared contract case is run against both implementations. The
     * helper builds a new set of nodes for each invocation, so one method
     * cannot affect the input observed by the other method.
     */
    @ParameterizedTest(name = "{0}")
    @MethodSource("validCases")
    void bothApproachesProduceTheRequiredOrder(String name, int[] values) {
        assertReordered(values, solution::reorderList);
        assertReordered(values, solution::reorderListWithHeadInsertion);
    }

    @Test
    void bothApproachesCanReorderTheSameNodesTwiceWithoutACycle() {
        assertReorderedTwice(new int[]{1, 2, 3, 4, 5, 6}, solution::reorderList);
        assertReorderedTwice(new int[]{1, 2, 3, 4, 5, 6},
                solution::reorderListWithHeadInsertion);
    }

    static Stream<Arguments> validCases() {
        int[] largeValues = new int[50_000];
        for (int i = 0; i < largeValues.length; i++) {
            largeValues[i] = (i % 11) - 5;
        }

        return Stream.of(
                Arguments.of("null list", (int[]) null),
                Arguments.of("one node", new int[]{42}),
                Arguments.of("two nodes", new int[]{2, 1}),
                Arguments.of("three nodes, odd midpoint", new int[]{1, 2, 3}),
                Arguments.of("four nodes, even midpoint", new int[]{1, 2, 3, 4}),
                Arguments.of("five nodes", new int[]{1, 2, 3, 4, 5}),
                Arguments.of("six nodes", new int[]{1, 2, 3, 4, 5, 6}),
                Arguments.of("seven nodes", new int[]{70, 10, 60, 20, 50, 30, 40}),
                Arguments.of("negative values", new int[]{-1, -20, 3, -400, 50}),
                Arguments.of("duplicate values", new int[]{7, 7, 7, 7, 7, 7}),
                Arguments.of("mixed ordering and extremes",
                        new int[]{Integer.MAX_VALUE, 0, Integer.MIN_VALUE, 9, -9, 1, 1, -1}),
                Arguments.of("large valid list", largeValues));
    }

    private void assertReordered(int[] values, Consumer<ListNode> reorder) {
        List<ListNode> original = buildNodes(values);
        List<ListNode> expected = expectedOrder(original);
        ListNode head = original.isEmpty() ? null : original.getFirst();

        reorder.accept(head);

        assertExactNodeOrder(expected, head);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                assertEquals(values[i], original.get(i).val,
                        "reordering must not change node values");
            }
        }
    }

    private void assertReorderedTwice(int[] values, Consumer<ListNode> reorder) {
        List<ListNode> original = buildNodes(values);
        ListNode head = original.getFirst();
        List<ListNode> firstExpected = expectedOrder(original);

        reorder.accept(head);
        assertExactNodeOrder(firstExpected, head);

        // The second expected order is derived from the first resulting order,
        // independently of either implementation's pointer manipulation.
        List<ListNode> secondExpected = expectedOrder(firstExpected);
        reorder.accept(head);
        assertExactNodeOrder(secondExpected, head);
    }

    private List<ListNode> buildNodes(int... values) {
        if (values == null) {
            return new ArrayList<>();
        }

        List<ListNode> nodes = new ArrayList<>(values.length);
        for (int value : values) {
            nodes.add(new ListNode(value));
        }
        for (int i = 1; i < nodes.size(); i++) {
            nodes.get(i - 1).next = nodes.get(i);
        }
        return nodes;
    }

    /**
     * Applies the problem's specification directly: take the leftmost
     * remaining node, then the rightmost remaining node, until exhausted.
     */
    private List<ListNode> expectedOrder(List<ListNode> original) {
        List<ListNode> expected = new ArrayList<>(original.size());
        int left = 0;
        int right = original.size() - 1;
        while (left <= right) {
            expected.add(original.get(left++));
            if (left <= right) {
                expected.add(original.get(right--));
            }
        }
        return expected;
    }

    private void assertExactNodeOrder(List<ListNode> expected, ListNode actualHead) {
        Set<ListNode> seen = Collections.newSetFromMap(new IdentityHashMap<>());
        ListNode current = actualHead;

        for (int index = 0; index < expected.size(); index++) {
            assertTrue(seen.add(current),
                    "cycle detected before reaching expected node " + index);
            assertSame(expected.get(index), current,
                    "unexpected node at position " + index);
            current = current.next;
        }

        // This checks both exact length and the required final null link.
        assertNull(current, "result must terminate with null");
        assertEquals(expected.size(), seen.size(), "result must contain every node once");
    }
}
