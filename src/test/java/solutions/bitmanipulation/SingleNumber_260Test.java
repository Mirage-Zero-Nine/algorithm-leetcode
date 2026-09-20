package solutions.bitmanipulation;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

/** Tests the two-singleton XOR partition contract for LeetCode 260. */
public class SingleNumber_260Test {
    private final SingleNumber_260 solver = new SingleNumber_260();

    private int[] sorted(int[] values) {
        int[] copy = values.clone();
        Arrays.sort(copy);
        return copy;
    }

    private int[] singlesByFrequency(int[] nums) {
        Map<Integer, Integer> frequencies = new HashMap<>();
        for (int num : nums) {
            frequencies.merge(num, 1, Integer::sum);
        }
        List<Integer> singles = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : frequencies.entrySet()) {
            if (entry.getValue() == 1) {
                singles.add(entry.getKey());
            }
        }
        assertEquals(2, singles.size(), "fixture must contain exactly two singletons");
        return new int[]{singles.get(0), singles.get(1)};
    }

    private void assertSingles(int[] nums, int first, int second) {
        int[] before = nums.clone();
        int[] result = solver.singleNumber(nums);
        assertNotSame(nums, result, "result must be a separate two-element array");
        assertArrayEquals(sorted(new int[]{first, second}), sorted(result));
        assertArrayEquals(before, nums, "the input array must not be modified");
    }

    private void assertMatchesFrequencyOracle(int[] nums) {
        int[] before = nums.clone();
        int[] expected = singlesByFrequency(nums);
        int[] actual = solver.singleNumber(nums);
        assertEquals(2, actual.length);
        assertArrayEquals(sorted(expected), sorted(actual));
        assertArrayEquals(before, nums, "the input array must not be modified");
    }

    private int[] shuffledPairs(int[] pairValues, int first, int second, long seed) {
        int[] values = new int[pairValues.length * 2 + 2];
        int index = 0;
        for (int value : pairValues) {
            values[index++] = value;
            values[index++] = value;
        }
        values[index++] = first;
        values[index] = second;
        Random random = new Random(seed);
        for (int i = values.length - 1; i > 0; i--) {
            int swapIndex = random.nextInt(i + 1);
            int temporary = values[i];
            values[i] = values[swapIndex];
            values[swapIndex] = temporary;
        }
        return values;
    }

    @Test
    public void officialExampleOne() {
        assertSingles(new int[]{1, 2, 1, 3, 2, 5}, 3, 5);
    }

    @Test
    public void officialExampleTwo() {
        assertSingles(new int[]{-1, 0}, -1, 0);
    }

    @Test
    public void officialExampleThree() {
        assertSingles(new int[]{0, 1}, 0, 1);
    }

    @Test
    public void pairsMayBeAbsent() {
        assertSingles(new int[]{42, 99}, 42, 99);
    }

    @Test
    public void zeroCanBeAUniqueValue() {
        assertSingles(new int[]{0, 1, 2, 2}, 0, 1);
    }

    @Test
    public void bothUniqueValuesCanBeNegative() {
        assertSingles(new int[]{-10, -20, 4, 4, 7, 7}, -10, -20);
    }

    @Test
    public void oneNegativeAndOnePositiveCanBeUnique() {
        assertSingles(new int[]{-7, 3, 5, 5, 3, 100}, -7, 100);
    }

    @Test
    public void adjacentUniqueValues() {
        assertSingles(new int[]{1, 2, 9, 9, 8, 8}, 1, 2);
    }

    @Test
    public void powersOfTwoExerciseSparseBits() {
        assertSingles(new int[]{4, 8, 4, 16, 8, 32}, 16, 32);
    }

    @Test
    public void signedIntegerExtremesAreSupported() {
        assertSingles(new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, 5, 5},
                Integer.MAX_VALUE, Integer.MIN_VALUE);
    }

    @Test
    public void uniquesDifferOnlyInTheSignBit() {
        for (int value : new int[]{0, 1, 42, 0x55555555, Integer.MAX_VALUE}) {
            int other = value ^ Integer.MIN_VALUE;
            assertSingles(new int[]{value, other, -7, -7}, value, other);
        }
    }

    @Test
    public void everyBitCanBeThePartitionBit() {
        for (int bit = 0; bit < Integer.SIZE; bit++) {
            int other = 1 << bit;
            assertSingles(new int[]{-1, other, 42, 0, -1, 42}, 0, other);
        }
    }

    @Test
    public void alternatingAndDenseBitPatterns() {
        assertSingles(new int[]{0xAAAAAAAA, 0x55555555, 0xAAAAAAAA, 0x13579BDF, 0x13579BDF, 0x2468ACE0},
                0x55555555, 0x2468ACE0);
        assertSingles(new int[]{0x7FFFFFFE, 0x80000001, 0x7FFFFFFE, 0x80000001, 0, -1}, 0, -1);
    }

    @Test
    public void resultXorMatchesTheIndependentInputXor() {
        int[] nums = {-31, 17, 17, Integer.MIN_VALUE, 99, -31, 99, Integer.MAX_VALUE};
        int inputXor = 0;
        for (int num : nums) {
            inputXor ^= num;
        }
        int[] result = solver.singleNumber(nums);
        assertEquals(inputXor, result[0] ^ result[1]);
        assertNotEquals(result[0], result[1]);
    }

    @Test
    public void unorderedInputAndInterleavedPairs() {
        assertMatchesFrequencyOracle(new int[]{11, -4, 7, 11, Integer.MIN_VALUE, 7, 0, -4, Integer.MAX_VALUE, 0});
    }

    @Test
    public void largeValuesAndRepeatedCallRemainIndependent() {
        int[] first = {1_000_000, 999_999, 1_000_000, 888_888};
        assertSingles(first, 999_999, 888_888);
        int[] second = {-1_000_001, 12, 12, 77, 77, 1_000_001};
        assertSingles(second, -1_000_001, 1_000_001);
    }

    @Test
    public void returnedArrayMutationDoesNotContaminateLaterCalls() {
        int[] input = {8, 8, 13, 21, 21, 34};
        int[] first = solver.singleNumber(input);
        first[0] = Integer.MIN_VALUE;
        first[1] = Integer.MAX_VALUE;
        assertSingles(input, 13, 34);
    }

    @Test
    public void generatedSmallFixturesUseIndependentFrequencyOracle() {
        Random random = new Random(260L);
        for (int trial = 0; trial < 250; trial++) {
            int pairCount = random.nextInt(25);
            int first = random.nextInt();
            int second;
            do {
                second = random.nextInt();
            } while (second == first);
            int[] pairValues = new int[pairCount];
            Map<Integer, Boolean> used = new HashMap<>();
            used.put(first, true);
            used.put(second, true);
            for (int i = 0; i < pairValues.length; i++) {
                int pair;
                do {
                    pair = random.nextInt();
                } while (used.containsKey(pair));
                used.put(pair, true);
                pairValues[i] = pair;
            }
            assertMatchesFrequencyOracle(shuffledPairs(pairValues, first, second, random.nextLong()));
        }
    }

    @Test
    public void generatedSignedBoundaryFixturesUseIndependentFrequencyOracle() {
        int[] pairValues = {Integer.MIN_VALUE + 1, -1, 0, 1, Integer.MAX_VALUE - 1, 0x40000000, 0x40000001};
        assertMatchesFrequencyOracle(shuffledPairs(pairValues, Integer.MIN_VALUE, Integer.MAX_VALUE, 260260L));
    }

    @Test
    public void maximumLengthFixtureWithUniquePairs() {
        int[] pairValues = new int[14_999];
        for (int i = 0; i < pairValues.length; i++) {
            pairValues[i] = i - 7_000;
        }
        int[] nums = shuffledPairs(pairValues, Integer.MIN_VALUE, Integer.MAX_VALUE, 260_300L);
        assertEquals(30_000, nums.length);
        assertMatchesFrequencyOracle(nums);
    }

    @Test
    public void deterministicLargeRandomFixtureUsesIndependentFrequencyOracle() {
        Random random = new Random(42L);
        int[] pairValues = new int[1_000];
        Map<Integer, Boolean> used = new HashMap<>();
        for (int i = 0; i < pairValues.length; i++) {
            int value;
            do {
                value = random.nextInt(1_000_000);
            } while (used.containsKey(value));
            used.put(value, true);
            pairValues[i] = value;
        }
        assertMatchesFrequencyOracle(shuffledPairs(pairValues, 1_000_001, -1_000_001, 42L));
    }

    @Test
    public void manyPairsWithOppositeBoundaryUniques() {
        int[] pairValues = new int[14_999];
        for (int i = 0; i < pairValues.length; i++) {
            pairValues[i] = i;
        }
        assertSingles(shuffledPairs(pairValues, Integer.MIN_VALUE, Integer.MAX_VALUE, 123L),
                Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
}
