package solutions.bitmanipulation;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SingleNumber_137Test {
    private final SingleNumber_137 solver = new SingleNumber_137();

    @Test public void testBasic() {
        assertEquals(3, solver.singleNumber(new int[]{2, 2, 3, 2}));
    }

    @Test public void testOneLonely() {
        assertEquals(99, solver.singleNumber(new int[]{0, 1, 0, 1, 0, 1, 99}));
    }

    @Test public void testSingleElement() {
        assertEquals(7, solver.singleNumber(new int[]{7}));
    }

    @Test public void testNegative() {
        assertEquals(-5, solver.singleNumber(new int[]{3, 3, 3, -5}));
    }

    @Test public void testLargerArray() {
        assertEquals(4, solver.singleNumber(new int[]{1, 1, 1, 2, 2, 2, 3, 3, 3, 4}));
    }

    @Test public void testZeroSingle() {
        assertEquals(0, solver.singleNumber(new int[]{5, 5, 5, 0}));
    }

    @Test public void testAllNegative() {
        assertEquals(-1, solver.singleNumber(new int[]{-2, -2, -2, -1}));
    }

    @Test public void testLargeValue() {
        assertEquals(Integer.MAX_VALUE, solver.singleNumber(new int[]{Integer.MAX_VALUE, 1, 1, 1}));
    }

    @Test public void testMinValue() {
        assertEquals(Integer.MIN_VALUE, solver.singleNumber(new int[]{Integer.MIN_VALUE, 2, 2, 2}));
    }

    @Test public void testMixedNegPos() {
        assertEquals(10, solver.singleNumber(new int[]{-3, -3, -3, 4, 4, 4, 10}));
    }

    @Test public void testGiantCase() {
        int[] nums = new int[301];
        for (int i = 0; i < 100; i++) {
            nums[3 * i] = i;
            nums[3 * i + 1] = i;
            nums[3 * i + 2] = i;
        }
        nums[300] = -777;
        assertEquals(-777, solver.singleNumber(nums));
    }

    @Test public void testFourElementsUniqueAtEnd() {
        assertEquals(99, solver.singleNumber(new int[]{7, 7, 7, 99}));
    }

    @Test public void testNegativeUniqueAmongPositives() {
        assertEquals(-42, solver.singleNumber(new int[]{1, 1, 1, 5, 5, 5, -42}));
    }

    @Test public void testZeroAsUniqueMixed() {
        assertEquals(0, solver.singleNumber(new int[]{-3, -3, -3, 7, 7, 7, 0}));
    }

    @Test public void testAllNegativeTripled() {
        assertEquals(-7, solver.singleNumber(new int[]{-1, -1, -1, -2, -2, -2, -7}));
    }

    @Test public void testMinMaxValueTogether() {
        assertEquals(Integer.MIN_VALUE, solver.singleNumber(
                new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE}));
    }

    @Test public void testLargeArray100TriplesSeed42() {
        Random rng = new Random(42L);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            int val = rng.nextInt();
            list.add(val); list.add(val); list.add(val);
        }
        int unique = 123456789;
        list.add(unique);
        Collections.shuffle(list, new Random(42L));
        int[] nums = list.stream().mapToInt(Integer::intValue).toArray();
        assertEquals(unique, solver.singleNumber(nums));
    }

    @Test public void testShuffleOrderSameAnswer() {
        int[] original = {4, 4, 4, -9, -9, -9, 11, 11, 11, 77};
        assertEquals(77, solver.singleNumber(original));
        List<Integer> shuffled = new ArrayList<>();
        for (int v : original) shuffled.add(v);
        Collections.shuffle(shuffled, new Random(99L));
        int[] shuffledArr = shuffled.stream().mapToInt(Integer::intValue).toArray();
        assertEquals(77, solver.singleNumber(shuffledArr));
    }

    @Test public void testAllBitsExercised() {
        // unique has all bits set (0xAAAAAAAA), triples cover complementary bits
        int unique = 0xAAAAAAAA; // exercises every other bit
        int complement = 0x55555555;
        int[] nums = {complement, complement, complement, unique};
        assertEquals(unique, solver.singleNumber(nums));
    }

    @Test public void testNegativeOneUnique() {
        // -1 is all bits set in two's complement
        assertEquals(-1, solver.singleNumber(new int[]{0, 0, 0, -1}));
    }

    @Test public void testActualGiantArrayOfTriplesAndOneUnique() {
        // 3 * 9,999 + 1 is the largest valid length not exceeding LeetCode's 30,000 limit.
        int[] values = new int[29998];
        for (int i = 0; i < 9999; i++)
            values[i] = values[i + 9999] = values[i + 19998] = i;
        values[29997] = Integer.MIN_VALUE;
        assertEquals(Integer.MIN_VALUE, solver.singleNumber(values));
    }

    @Test public void testUniqueAtEveryPositionBetweenTriples() {
        for (int position = 0; position < 10; position++) {
            int[] values = new int[10];
            int[] repeated = {Integer.MIN_VALUE, Integer.MAX_VALUE, 0};
            for (int i = 0, next = 0; i < values.length; i++)
                values[i] = i == position ? -1 : repeated[(next++) / 3];
            assertEquals(-1, solver.singleNumber(values));
        }
    }

    @Test public void testEachUniqueBitAndComplement() {
        for (int bit = 0; bit < 32; bit++) {
            int unique = 1 << bit;
            assertEquals(unique, solver.singleNumber(new int[]{~unique, unique, ~unique, ~unique}));
            assertEquals(~unique, solver.singleNumber(new int[]{unique, ~unique, unique, unique}));
        }
    }

    @Test public void testTriplesMayBeInterleavedInAnyOrder() {
        int[] nums = {7, -4, 7, 19, -4, 7, -4, 123, 19, 19};
        assertEquals(123, solver.singleNumber(nums));
    }

    @Test public void testSignBitAndBitCountRemainders() {
        int[] nums = {
                Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE,
                Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE,
                0, 0, 0,
                0x55555555, 0x55555555, 0x55555555,
                0xAAAAAAAA
        };
        assertEquals(frequencyOracle(nums), solver.singleNumber(nums));
        assertEquals(0xAAAAAAAA, solver.singleNumber(nums));
    }

    @Test public void testInputIsNotMutatedAndSolverCanBeReused() {
        int[] first = {
                Integer.MIN_VALUE, 4, 4, 4, 9, 9, 9,
                Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE
        };
        int[] firstBefore = first.clone();
        assertEquals(Integer.MIN_VALUE, solver.singleNumber(first));
        assertArrayEquals(firstBefore, first);

        int[] second = {8, 8, 8, -12};
        assertEquals(-12, solver.singleNumber(second));
        assertEquals(Integer.MIN_VALUE, solver.singleNumber(first));
        assertArrayEquals(firstBefore, first);
    }

    @Test public void testGeneratedValidArraysMatchIndependentFrequencyOracle() {
        Random random = new Random(137L);
        for (int scenario = 0; scenario < 100; scenario++) {
            int tripleCount = 1 + random.nextInt(40);
            int unique;
            Set<Integer> used = new HashSet<>();
            do {
                unique = random.nextInt();
            } while (!used.add(unique));

            List<Integer> values = new ArrayList<>(3 * tripleCount + 1);
            for (int i = 0; i < tripleCount; i++) {
                int repeated;
                do {
                    repeated = random.nextInt();
                } while (repeated == unique || !used.add(repeated));
                values.add(repeated);
                values.add(repeated);
                values.add(repeated);
            }
            values.add(unique);
            Collections.shuffle(values, random);
            int[] nums = values.stream().mapToInt(Integer::intValue).toArray();
            assertEquals(frequencyOracle(nums), solver.singleNumber(nums),
                    "scenario " + scenario + " should preserve the frequency invariant");
        }
    }

    @Test public void testSmallExhaustiveValueFamiliesMatchFrequencyOracle() {
        int[] candidates = {Integer.MIN_VALUE, -2, -1, 0, 1, 2, Integer.MAX_VALUE};
        for (int unique : candidates) {
            for (int repeated : candidates) {
                if (unique == repeated) {
                    continue;
                }
                int[] nums = {repeated, unique, repeated, repeated};
                assertEquals(frequencyOracle(nums), solver.singleNumber(nums),
                        "unique=" + unique + ", repeated=" + repeated);
            }
        }
    }

    private int frequencyOracle(int[] nums) {
        Map<Integer, Integer> frequencies = new HashMap<>();
        for (int num : nums) {
            frequencies.merge(num, 1, Integer::sum);
        }
        return frequencies.entrySet().stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow(() -> new AssertionError("valid input must contain one singleton"));
    }
}
