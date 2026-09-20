package solutions.design;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.junit.jupiter.api.Test;

/** Contract and property tests for the reservoir-sampling implementation of LeetCode 398. */
public class RandomPickIndex_398Test {

    @Test
    public void officialExampleReturnsOnlyMatchingIndexes() {
        int[] nums = {1, 2, 3, 3, 3};
        RandomPickIndex_398 solution = new RandomPickIndex_398(nums);

        for (int call = 0; call < 100; call++) {
            int index = solution.pick(3);
            assertTrue(index == 2 || index == 3 || index == 4);
            assertEquals(3, nums[index]);
        }
        assertEquals(0, solution.pick(1));
    }

    @Test
    public void uniqueTargetsReturnTheirOnlyIndex() {
        int[] nums = {-8, 0, 17, 42, 99};
        RandomPickIndex_398 solution = new RandomPickIndex_398(nums);

        for (int index = 0; index < nums.length; index++) {
            assertEquals(index, solution.pick(nums[index]));
        }
    }

    @Test
    public void targetAtFirstIndexIsReturnedExactly() {
        RandomPickIndex_398 solution = new RandomPickIndex_398(new int[]{5, 1, 2, 3});

        for (int call = 0; call < 20; call++) {
            assertEquals(0, solution.pick(5));
        }
    }

    @Test
    public void targetAtLastIndexIsReturnedExactly() {
        RandomPickIndex_398 solution = new RandomPickIndex_398(new int[]{1, 2, 3, 99});

        for (int call = 0; call < 20; call++) {
            assertEquals(3, solution.pick(99));
        }
    }

    @Test
    public void twoOccurrencesReturnOnlyValidIndexes() {
        RandomPickIndex_398 solution = new RandomPickIndex_398(new int[]{3, 5, 3});
        Set<Integer> valid = Set.of(0, 2);

        for (int call = 0; call < 200; call++) {
            assertTrue(valid.contains(solution.pick(3)));
        }
    }

    @Test
    public void repeatedValuesReturnOnlyMatchingIndexes() {
        int[] nums = {7, 7, 7, 7, 7};
        RandomPickIndex_398 solution = new RandomPickIndex_398(nums);

        for (int call = 0; call < 500; call++) {
            int index = solution.pick(7);
            assertTrue(index >= 0 && index < nums.length);
            assertEquals(7, nums[index]);
        }
    }

    @Test
    public void negativeTargetsAreHandled() {
        int[] nums = {-1, -2, -1, -3, -1};
        RandomPickIndex_398 solution = new RandomPickIndex_398(nums);
        Set<Integer> valid = Set.of(0, 2, 4);

        for (int call = 0; call < 200; call++) {
            assertTrue(valid.contains(solution.pick(-1)));
        }
    }

    @Test
    public void integerBoundaryTargetsAreHandled() {
        int[] nums = {Integer.MIN_VALUE, 0, Integer.MAX_VALUE, Integer.MIN_VALUE,
                Integer.MAX_VALUE};
        RandomPickIndex_398 solution = new RandomPickIndex_398(nums);

        assertTrue(Set.of(0, 3).contains(solution.pick(Integer.MIN_VALUE)));
        assertTrue(Set.of(2, 4).contains(solution.pick(Integer.MAX_VALUE)));
        assertEquals(1, solution.pick(0));
    }

    @Test
    public void absentTargetUsesDocumentedNoMatchSentinel() {
        RandomPickIndex_398 solution = new RandomPickIndex_398(new int[]{-2, 0, 2});

        assertEquals(-1, solution.pick(1));
    }

    @Test
    public void emptyInputUsesNoMatchSentinel() {
        RandomPickIndex_398 solution = new RandomPickIndex_398(new int[0]);

        assertEquals(-1, solution.pick(123));
    }

    @Test
    public void picksDoNotMutateTheInputArray() {
        int[] nums = {4, 9, 4, 1, 4, 9, 4};
        int[] original = nums.clone();
        RandomPickIndex_398 solution = new RandomPickIndex_398(nums);

        for (int call = 0; call < 300; call++) {
            solution.pick(4);
            solution.pick(9);
        }

        assertArrayEquals(original, nums);
    }

    @Test
    public void separateInstancesKeepTheirOwnArraysAndRandomState() {
        RandomPickIndex_398 first = new RandomPickIndex_398(new int[]{8, 1, 8});
        RandomPickIndex_398 second = new RandomPickIndex_398(new int[]{2, 9, 2, 9});

        for (int call = 0; call < 100; call++) {
            assertTrue(Set.of(0, 2).contains(first.pick(8)));
            assertTrue(Set.of(1, 3).contains(second.pick(9)));
        }
    }

    @Test
    public void differentTargetsDoNotReuseOccurrenceCounts() {
        RandomPickIndex_398 solution = new RandomPickIndex_398(new int[]{4, 9, 4, 9, 9});

        for (int call = 0; call < 100; call++) {
            assertTrue(Set.of(0, 2).contains(solution.pick(4)));
            assertTrue(Set.of(1, 3, 4).contains(solution.pick(9)));
        }
    }

    @Test
    public void scriptedRandomCanSelectEveryValidOccurrence() {
        int[] nums = {6, 1, 6, 2, 3, 6, 4, 6};
        int[] matchingIndexes = {0, 2, 5, 7};

        for (int desiredOrdinal = 0; desiredOrdinal < matchingIndexes.length; desiredOrdinal++) {
            RandomPickIndex_398 solution = new RandomPickIndex_398(nums);
            solution.random = new ScriptedRandom(scriptToSelectOrdinal(matchingIndexes.length,
                    desiredOrdinal));

            assertEquals(matchingIndexes[desiredOrdinal], solution.pick(6));
        }
    }

    @Test
    public void reservoirUsesIncreasingOccurrenceBounds() {
        RandomPickIndex_398 solution = new RandomPickIndex_398(new int[]{4, 4, 4, 4});
        RecordingRandom random = new RecordingRandom(0, 1, 0, 1);
        solution.random = random;

        assertEquals(2, solution.pick(4));
        assertEquals(List.of(1, 2, 3, 4), random.bounds());
    }

    @Test
    public void nonMatchingElementsDoNotConsumeRandomDraws() {
        RandomPickIndex_398 solution = new RandomPickIndex_398(new int[]{1, 8, 1, 9, 1});
        RecordingRandom random = new RecordingRandom(0, 1, 0);
        solution.random = random;

        assertEquals(4, solution.pick(1));
        assertEquals(List.of(1, 2, 3), random.bounds());
    }

    @Test
    public void seededDistributionIsBroadlyUniform() {
        RandomPickIndex_398 solution = new RandomPickIndex_398(new int[]{11, 11, 11, 11});
        solution.random = new Random(398L);
        int[] counts = new int[4];

        for (int call = 0; call < 12_000; call++) {
            counts[solution.pick(11)]++;
        }

        for (int count : counts) {
            assertTrue(count >= 2_400 && count <= 3_600,
                    "count outside broad uniformity bounds: " + count);
        }
    }

    @Test
    public void seededSamplingCanObserveEveryOccurrence() {
        int[] nums = {5, 2, 5, 3, 5, 4, 5, 6};
        RandomPickIndex_398 solution = new RandomPickIndex_398(nums);
        solution.random = new Random(398_2026L);
        Set<Integer> observed = new HashSet<>();

        for (int call = 0; call < 2_000; call++) {
            observed.add(solution.pick(5));
        }

        assertEquals(Set.of(0, 2, 4, 6), observed);
    }

    @Test
    public void reseedingEquivalentInstancesProducesEquivalentCandidateSequences() {
        int[] nums = {3, 9, 3, 9, 3};
        RandomPickIndex_398 first = new RandomPickIndex_398(nums);
        RandomPickIndex_398 second = new RandomPickIndex_398(nums.clone());
        first.random = new Random(7L);
        second.random = new Random(7L);

        for (int call = 0; call < 100; call++) {
            assertEquals(first.pick(3), second.pick(3));
        }
    }

    @Test
    public void sparseMatchesInLargeArrayRemainValid() {
        int[] nums = new int[20_000];
        Arrays.fill(nums, -1);
        nums[0] = 123;
        nums[9_999] = 123;
        nums[19_999] = 123;
        RandomPickIndex_398 solution = new RandomPickIndex_398(nums);
        solution.random = new Random(123L);

        for (int call = 0; call < 100; call++) {
            assertTrue(Set.of(0, 9_999, 19_999).contains(solution.pick(123)));
        }
    }

    @Test
    public void maximumArrayLengthSupportsBoundaryMatches() {
        int[] nums = new int[20_000];
        Arrays.fill(nums, 0);
        nums[0] = Integer.MIN_VALUE;
        nums[19_999] = Integer.MAX_VALUE;
        RandomPickIndex_398 solution = new RandomPickIndex_398(nums);

        assertEquals(0, solution.pick(Integer.MIN_VALUE));
        assertEquals(19_999, solution.pick(Integer.MAX_VALUE));
    }

    @Test
    public void maximumAllowedCallCountRemainsValid() {
        int[] nums = {10, 20, 10, 30, 10};
        RandomPickIndex_398 solution = new RandomPickIndex_398(nums);
        solution.random = new Random(10_000L);
        Set<Integer> valid = Set.of(0, 2, 4);

        for (int call = 0; call < 10_000; call++) {
            assertTrue(valid.contains(solution.pick(10)));
        }
    }

    @Test
    public void interleavedTargetsRemainIndependentAtScale() {
        int[] nums = new int[1_000];
        for (int index = 0; index < nums.length; index++) {
            nums[index] = index % 5;
        }
        RandomPickIndex_398 solution = new RandomPickIndex_398(nums);
        solution.random = new Random(1L);

        for (int call = 0; call < 500; call++) {
            int first = solution.pick(0);
            int second = solution.pick(4);
            assertEquals(0, nums[first]);
            assertEquals(4, nums[second]);
        }
    }

    @Test
    public void scriptedRandomCanKeepTheFirstOccurrence() {
        RandomPickIndex_398 solution = new RandomPickIndex_398(new int[]{2, 2, 2});
        solution.random = new RecordingRandom(0, 1, 1);

        assertEquals(0, solution.pick(2));
    }

    @Test
    public void oneOccurrenceStillConsumesAValidBoundOneDraw() {
        RandomPickIndex_398 solution = new RandomPickIndex_398(new int[]{8, 4, 9});
        RecordingRandom random = new RecordingRandom(0);
        solution.random = random;

        assertEquals(1, solution.pick(4));
        assertEquals(List.of(1), random.bounds());
    }

    private static int[] scriptToSelectOrdinal(int occurrenceCount, int desiredOrdinal) {
        int[] draws = new int[occurrenceCount];
        draws[0] = 0;
        for (int occurrence = 1; occurrence < occurrenceCount; occurrence++) {
            draws[occurrence] = occurrence == desiredOrdinal ? 0 : 1;
        }
        return draws;
    }

    private static final class ScriptedRandom extends Random {
        private final int[] draws;
        private int next;

        private ScriptedRandom(int[] draws) {
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
