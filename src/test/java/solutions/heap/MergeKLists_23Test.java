package solutions.heap;

import library.listnode.ListNode;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.IdentityHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

class MergeKLists_23Test {

    private final MergeKLists_23 solution = new MergeKLists_23();

    @Test
    void emptyArrayReturnsNullFromBothApproaches() {
        assertBoth(new int[]{}, new int[][]{});
    }

    @Test
    void nullArrayReturnsNullFromBothApproaches() {
        assertNull(solution.mergeKListsHeap(null));
        assertNull(solution.mergeKLists(null));
    }

    @Test
    void allNullEntriesReturnNullFromBothApproaches() {
        assertBoth(new int[]{}, new int[][]{null, null, null});
    }

    @Test
    void sampleListsAreMergedInAscendingOrder() {
        assertBoth(new int[]{1, 1, 2, 3, 4, 4, 5, 6},
                new int[][]{{1, 4, 5}, {1, 3, 4}, {2, 6}});
    }

    @Test
    void twoInterleavedListsAreMerged() {
        assertBoth(new int[]{1, 2, 3, 4, 5, 6},
                new int[][]{{1, 3, 5}, {2, 4, 6}});
    }

    @Test
    void oneListIsReturnedWithAllOfItsNodes() {
        assertBoth(new int[]{-2, 0, 3}, new int[][]{{-2, 0, 3}});
    }

    @Test
    void emptyListsCanBeMixedWithNonEmptyLists() {
        assertBoth(new int[]{1, 2, 3, 4},
                new int[][]{new int[]{}, {1, 3}, null, {2, 4}});
    }

    @Test
    void negativeZeroAndPositiveValuesAreOrdered() {
        assertBoth(new int[]{-5, -4, -3, -1, 0, 2, 3, 4},
                new int[][]{{-5, -1, 4}, {-4, 0, 3}, {-3, 2}});
    }

    @Test
    void duplicateValuesKeepEveryNode() {
        assertBoth(new int[]{1, 1, 1, 1, 1, 1},
                new int[][]{{1, 1, 1}, {1, 1, 1}});
    }

    @Test
    void oddNumberOfListsCarriesTheUnpairedListToTheNextRound() {
        assertBoth(new int[]{0, 1, 2, 3, 4, 5, 6},
                new int[][]{{0, 6}, {1, 4}, {2, 5}, {3}});
    }

    @Test
    void veryDifferentListLengthsAreHandled() {
        assertBoth(new int[]{1, 2, 3, 4, 5, 6},
                new int[][]{{1}, {2, 3, 4, 5, 6}});
    }

    @Test
    void integerBoundariesAreComparedWithoutArithmeticOverflow() {
        assertBoth(new int[]{Integer.MIN_VALUE, -1, 0, 1, Integer.MAX_VALUE},
                new int[][]{{Integer.MIN_VALUE, 0, Integer.MAX_VALUE}, {-1, 1}});
    }

    @Test
    void regressionRepeatedNegativeValuesFollowedByNullList() {
        assertBoth(new int[]{-2, -1, -1, -1},
                new int[][]{{-2, -1, -1, -1}, null});
    }

    @Test
    void divideAndConquerUsesIterativeMergeForLongLists() {
        int length = 8_192;
        int[][] values = {new int[length], new int[length]};
        for (int i = 0; i < length; i++) {
            values[0][i] = i * 2;
            values[1][i] = i * 2 + 1;
        }

        ListNode[] lists = buildLists(values);
        assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            ListNode result = solution.mergeKLists(lists);
            assertSequence(result, length * 2);
        });
    }

    @Test
    void bothApproachesHandleAMeaningfulStressInputWithinTheContract() {
        int listCount = 64;
        int listLength = 256;
        int[][] values = new int[listCount][listLength];
        for (int list = 0; list < listCount; list++) {
            for (int position = 0; position < listLength; position++) {
                values[list][position] = list + position * listCount;
            }
        }

        assertTimeoutPreemptively(Duration.ofSeconds(5),
                () -> assertBothValuesOnly(new int[listCount * listLength], values));
    }

    @Test
    void repeatedCallsDoNotShareStateBetweenInvocations() {
        assertBoth(new int[]{1, 4}, new int[][]{{1}, {4}});
        assertBoth(new int[]{-3, 2, 8}, new int[][]{{-3, 2}, {8}});
    }

    private void assertBoth(int[] expected, int[][] values) {
        ListNode[] heapLists = buildLists(values);
        ListNode[] divideLists = buildLists(values);
        Map<ListNode, Boolean> heapNodes = nodesByIdentity(heapLists);
        Map<ListNode, Boolean> divideNodes = nodesByIdentity(divideLists);

        ListNode heapResult = solution.mergeKListsHeap(heapLists);
        ListNode divideResult = solution.mergeKLists(divideLists);

        assertListEquals(expected, heapResult);
        assertListEquals(expected, divideResult);
        assertReusesExactly(heapNodes, heapResult);
        assertReusesExactly(divideNodes, divideResult);
    }

    private void assertBothValuesOnly(int[] expected, int[][] values) {
        for (int i = 0; i < expected.length; i++) {
            expected[i] = i;
        }
        assertListEquals(expected, solution.mergeKListsHeap(buildLists(values)));
        assertListEquals(expected, solution.mergeKLists(buildLists(values)));
    }

    private ListNode[] buildLists(int[][] values) {
        ListNode[] lists = new ListNode[values.length];
        for (int i = 0; i < values.length; i++) {
            if (values[i] == null || values[i].length == 0) {
                continue;
            }
            ListNode dummy = new ListNode(0);
            ListNode tail = dummy;
            for (int value : values[i]) {
                tail.next = new ListNode(value);
                tail = tail.next;
            }
            lists[i] = dummy.next;
        }
        return lists;
    }

    private Map<ListNode, Boolean> nodesByIdentity(ListNode[] lists) {
        Map<ListNode, Boolean> nodes = new IdentityHashMap<>();
        for (ListNode head : lists) {
            for (ListNode current = head; current != null; current = current.next) {
                nodes.put(current, Boolean.TRUE);
            }
        }
        return nodes;
    }

    private void assertReusesExactly(Map<ListNode, Boolean> expectedNodes, ListNode result) {
        Map<ListNode, Boolean> remaining = new IdentityHashMap<>(expectedNodes);
        int nodeCount = expectedNodes.size();
        for (int i = 0; i < nodeCount; i++) {
            assertNotNull(result, "The result ended before all input nodes were reused");
            assertNotNull(remaining.remove(result), "The result contains a node that was not in the input");
            result = result.next;
        }
        assertNull(result, "The result contains a cycle or an extra node");
        assertTrue(remaining.isEmpty(), "The result dropped one or more input nodes");
    }

    private void assertListEquals(int[] expected, ListNode actual) {
        for (int value : expected) {
            assertNotNull(actual, "The result ended before all expected values were found");
            assertEquals(value, actual.val);
            actual = actual.next;
        }
        assertNull(actual, "The result contains extra nodes");
    }

    private void assertSequence(ListNode result, int length) {
        for (int value = 0; value < length; value++) {
            assertNotNull(result);
            assertEquals(value, result.val);
            result = result.next;
        }
        assertNull(result);
    }
}
