package solutions.design;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import library.listnode.ListNode;
import org.junit.jupiter.api.Test;

/** Contract and property tests for {@link Solution_382}'s reservoir sampler. */
public class Solution_382Test {

    @Test
    public void officialExampleReturnsOnlyValuesFromTheList() {
        Solution_382 solution = new Solution_382(build(1, 2, 3));
        Set<Integer> values = Set.of(1, 2, 3);

        for (int call = 0; call < 1_000; call++) {
            assertTrue(values.contains(solution.getRandom()));
        }
    }

    @Test
    public void singletonAlwaysReturnsItsOnlyValue() {
        Solution_382 solution = new Solution_382(build(42));

        for (int call = 0; call < 100; call++) {
            assertEquals(42, solution.getRandom());
        }
    }

    @Test
    public void twoNodeListReturnsEitherNode() {
        Solution_382 solution = new Solution_382(build(10, 20));
        Set<Integer> observed = new HashSet<>();

        for (int call = 0; call < 1_000; call++) {
            observed.add(solution.getRandom());
        }

        assertEquals(Set.of(10, 20), observed);
    }

    @Test
    public void scriptedRandomCanSelectEveryNode() {
        int[] values = {11, 22, 33, 44, 55};

        for (int desired = 0; desired < values.length; desired++) {
            Solution_382 solution = new Solution_382(build(values));
            int[] draws = new int[values.length - 1];
            for (int count = 1; count < values.length; count++) {
                draws[count - 1] = count == desired ? count : 0;
            }
            solution.random = new ScriptedRandom(draws);

            assertEquals(values[desired], solution.getRandom(),
                    "node " + desired + " was not selectable");
        }
    }

    @Test
    public void scriptedRandomUsesTheCorrectIncreasingBounds() {
        Solution_382 solution = new Solution_382(build(1, 2, 3, 4, 5));
        RecordingRandom random = new RecordingRandom(0, 0, 0, 4);
        solution.random = random;

        assertEquals(5, solution.getRandom());
        assertEquals(List.of(2, 3, 4, 5), random.bounds());
    }

    @Test
    public void everyPossibleDrawSequenceHasEqualFinalOutcomeCount() {
        int[] values = {1, 2, 3};
        int[] outcomes = new int[values.length];
        int sequenceCount = 2 * 3;

        for (int firstDraw = 0; firstDraw < 2; firstDraw++) {
            for (int secondDraw = 0; secondDraw < 3; secondDraw++) {
                Solution_382 solution = new Solution_382(build(values));
                solution.random = new ScriptedRandom(firstDraw, secondDraw);
                int selected = solution.getRandom();
                outcomes[indexOf(values, selected)]++;
            }
        }

        assertArrayEquals(new int[]{sequenceCount / 3, sequenceCount / 3, sequenceCount / 3},
                outcomes);
    }

    @Test
    public void duplicateValuesRemainValidAndDoNotCreateNewValues() {
        Solution_382 solution = new Solution_382(build(7, 7, 8, 7, 9, 8));
        Set<Integer> allowed = Set.of(7, 8, 9);

        for (int call = 0; call < 1_000; call++) {
            assertTrue(allowed.contains(solution.getRandom()));
        }
    }

    @Test
    public void negativeZeroAndPositiveValuesAreReturnedUnchanged() {
        int[] values = {-10_000, -1, 0, 1, 10_000};
        Solution_382 solution = new Solution_382(build(values));
        solution.random = new Random(382L);

        for (int call = 0; call < 500; call++) {
            assertTrue(contains(values, solution.getRandom()));
        }
    }

    @Test
    public void fullJavaIntegerValueRangeIsSupportedByTheImplementation() {
        int[] values = {Integer.MIN_VALUE, -1, 0, Integer.MAX_VALUE};
        Solution_382 solution = new Solution_382(build(values));
        solution.random = new Random(38_200L);
        Set<Integer> allowed = Set.of(Integer.MIN_VALUE, -1, 0, Integer.MAX_VALUE);

        for (int call = 0; call < 500; call++) {
            assertTrue(allowed.contains(solution.getRandom()));
        }
    }

    @Test
    public void seededSamplingIsBroadlyUniformForFourDistinctNodes() {
        int[] values = {101, 202, 303, 404};
        Solution_382 solution = new Solution_382(build(values));
        solution.random = new Random(382_2026L);
        int[] counts = new int[values.length];

        for (int call = 0; call < 20_000; call++) {
            counts[indexOf(values, solution.getRandom())]++;
        }

        for (int count : counts) {
            assertTrue(count >= 3_000 && count <= 7_000,
                    "sample count was implausibly unbalanced: " + count);
        }
    }

    @Test
    public void seededSamplingCanObserveEveryDistinctNode() {
        int[] values = {5, 10, 15, 20, 25, 30, 35, 40};
        Solution_382 solution = new Solution_382(build(values));
        solution.random = new Random(3_820_000L);
        Set<Integer> observed = new HashSet<>();

        for (int call = 0; call < 5_000; call++) {
            observed.add(solution.getRandom());
        }

        assertEquals(Set.of(5, 10, 15, 20, 25, 30, 35, 40), observed);
    }

    @Test
    public void repeatedCallsUseTheSameListAndRemainValid() {
        int[] values = {-4, 0, 9, 16};
        Solution_382 solution = new Solution_382(build(values));
        solution.random = new Random(7L);

        for (int call = 0; call < 2_000; call++) {
            assertTrue(contains(values, solution.getRandom()));
        }
    }

    @Test
    public void reseededEquivalentInstancesProduceEquivalentSequences() {
        int[] values = {8, 13, 21, 34, 55};
        Solution_382 first = new Solution_382(build(values));
        Solution_382 second = new Solution_382(build(values));
        first.random = new Random(123_456L);
        second.random = new Random(123_456L);

        for (int call = 0; call < 200; call++) {
            assertEquals(first.getRandom(), second.getRandom());
        }
    }

    @Test
    public void independentInstancesKeepIndependentListsAndRandomState() {
        Solution_382 first = new Solution_382(build(1, 2));
        Solution_382 second = new Solution_382(build(9, 10, 11));
        first.random = new Random(1L);
        second.random = new Random(1L);

        for (int call = 0; call < 500; call++) {
            assertTrue(Set.of(1, 2).contains(first.getRandom()));
            assertTrue(Set.of(9, 10, 11).contains(second.getRandom()));
        }
    }

    @Test
    public void callsDoNotMutateNodeValuesOrTopology() {
        ListNode head = build(3, -7, 11, 19, 23);
        ListNode[] nodes = nodes(head);
        ListNode[] next = new ListNode[nodes.length];
        int[] values = new int[nodes.length];
        for (int index = 0; index < nodes.length; index++) {
            next[index] = nodes[index].next;
            values[index] = nodes[index].val;
        }

        Solution_382 solution = new Solution_382(head);
        solution.random = new Random(382L);
        for (int call = 0; call < 1_000; call++) {
            solution.getRandom();
        }

        assertSame(head, solution.head);
        for (int index = 0; index < nodes.length; index++) {
            assertSame(next[index], nodes[index].next);
            assertEquals(values[index], nodes[index].val);
        }
    }

    @Test
    public void listNodeIdentityIsPreservedWhenValuesAreUnique() {
        ListNode head = build(100, 200, 300, 400);
        ListNode[] nodes = nodes(head);
        Solution_382 solution = new Solution_382(head);
        solution.random = new Random(400L);

        for (int call = 0; call < 500; call++) {
            int selected = solution.getRandom();
            assertTrue(selected == nodes[0].val || selected == nodes[1].val
                    || selected == nodes[2].val || selected == nodes[3].val);
        }

        assertSame(nodes[0], head);
        assertSame(nodes[1], head.next);
        assertSame(nodes[2], head.next.next);
        assertSame(nodes[3], head.next.next.next);
    }

    @Test
    public void firstAndLastNodesRemainSelectableAtListBoundaries() {
        int[] values = {-10_000, 0, 10_000};

        Solution_382 first = new Solution_382(build(values));
        first.random = new ScriptedRandom(0, 0);
        assertEquals(values[0], first.getRandom());

        Solution_382 last = new Solution_382(build(values));
        last.random = new ScriptedRandom(0, 2);
        assertEquals(values[2], last.getRandom());
    }

    @Test
    public void allNodesInAMaximumLengthListProduceValidValues() {
        int[] values = new int[10_000];
        for (int index = 0; index < values.length; index++) {
            values[index] = index - 10_000;
        }
        Solution_382 solution = new Solution_382(build(values));
        solution.random = new Random(10_000L);
        Set<Integer> allowed = new HashSet<>();
        for (int value : values) {
            allowed.add(value);
        }

        for (int call = 0; call < 100; call++) {
            assertTrue(allowed.contains(solution.getRandom()));
        }
    }

    @Test
    public void maximumAllowedCallCountRemainsValid() {
        int[] values = {-2, -1, 0, 1, 2};
        Solution_382 solution = new Solution_382(build(values));
        solution.random = new Random(10_000L);

        for (int call = 0; call < 10_000; call++) {
            assertTrue(contains(values, solution.getRandom()));
        }
    }

    @Test
    public void repeatedMaximumCallCountOnSingletonIsExact() {
        Solution_382 solution = new Solution_382(build(Integer.MAX_VALUE));

        for (int call = 0; call < 10_000; call++) {
            assertEquals(Integer.MAX_VALUE, solution.getRandom());
        }
    }

    @Test
    public void shortListWithDistinctValuesCanReachEachPosition() {
        int[] values = {31, 41, 59, 26};
        for (int desired = 0; desired < values.length; desired++) {
            Solution_382 solution = new Solution_382(build(values));
            int[] draws = {0, 0, 0};
            for (int count = 1; count < values.length; count++) {
                if (count == desired) {
                    draws[count - 1] = count;
                }
            }
            solution.random = new ScriptedRandom(draws);
            assertEquals(values[desired], solution.getRandom());
        }
    }

    @Test
    public void duplicateOnlyListAlwaysReturnsTheDuplicateValue() {
        Solution_382 solution = new Solution_382(build(7, 7, 7, 7, 7));
        solution.random = new Random(382L);

        for (int call = 0; call < 500; call++) {
            assertEquals(7, solution.getRandom());
        }
    }

    private static ListNode build(int... values) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        for (int value : values) {
            current.next = new ListNode(value);
            current = current.next;
        }
        return dummy.next;
    }

    private static ListNode[] nodes(ListNode head) {
        List<ListNode> result = new ArrayList<>();
        for (ListNode current = head; current != null; current = current.next) {
            result.add(current);
        }
        return result.toArray(ListNode[]::new);
    }

    private static boolean contains(int[] values, int target) {
        for (int value : values) {
            if (value == target) {
                return true;
            }
        }
        return false;
    }

    private static int indexOf(int[] values, int target) {
        for (int index = 0; index < values.length; index++) {
            if (values[index] == target) {
                return index;
            }
        }
        throw new AssertionError("unexpected sampled value: " + target);
    }

    private static final class ScriptedRandom extends Random {
        private final int[] draws;
        private int next;

        private ScriptedRandom(int... draws) {
            this.draws = draws;
        }

        @Override
        public int nextInt(int bound) {
            if (next == draws.length) {
                throw new AssertionError("solution requested more random draws than expected");
            }
            int draw = draws[next++];
            if (draw < 0 || draw >= bound) {
                throw new AssertionError("draw " + draw + " is outside bound " + bound);
            }
            return draw;
        }
    }

    private static final class RecordingRandom extends Random {
        private final int[] draws;
        private final List<Integer> bounds = new ArrayList<>();
        private int next;

        private RecordingRandom(int... draws) {
            this.draws = draws;
        }

        @Override
        public int nextInt(int bound) {
            bounds.add(bound);
            if (next == draws.length) {
                throw new AssertionError("solution requested more random draws than expected");
            }
            int draw = draws[next++];
            if (draw < 0 || draw >= bound) {
                throw new AssertionError("draw " + draw + " is outside bound " + bound);
            }
            return draw;
        }

        private List<Integer> bounds() {
            return bounds;
        }
    }
}
