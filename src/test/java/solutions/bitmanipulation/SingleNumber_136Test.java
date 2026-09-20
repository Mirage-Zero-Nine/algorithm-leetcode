package solutions.bitmanipulation;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SingleNumber_136Test {
    private final SingleNumber_136 solver = new SingleNumber_136();

    @Test public void testBasic() {
        assertEquals(1, solver.singleNumber(new int[]{2, 2, 1}));
    }

    @Test public void testFourPairs() {
        assertEquals(4, solver.singleNumber(new int[]{4, 1, 2, 1, 2}));
    }

    @Test public void testSingleElement() {
        assertEquals(1, solver.singleNumber(new int[]{1}));
    }

    @Test public void testNegative() {
        assertEquals(-5, solver.singleNumber(new int[]{-5, 1, 1, 2, 2}));
    }

    @Test public void testLargerArray() {
        assertEquals(7, solver.singleNumber(new int[]{3, 5, 3, 5, 7, 9, 9}));
    }

    @Test public void testZeroSingle() {
        assertEquals(0, solver.singleNumber(new int[]{1, 1, 0}));
    }

    @Test public void testLargeValue() {
        assertEquals(Integer.MAX_VALUE, solver.singleNumber(new int[]{Integer.MAX_VALUE, 1, 1}));
    }

    @Test public void testMinValue() {
        assertEquals(Integer.MIN_VALUE, solver.singleNumber(new int[]{Integer.MIN_VALUE, 99, 99}));
    }

    @Test public void testMixedNegPos() {
        assertEquals(3, solver.singleNumber(new int[]{-1, -1, 2, 2, 3}));
    }

    @Test public void testGiantCase() {
        int[] nums = new int[10001];
        for (int i = 0; i < 5000; i++) {
            nums[2 * i] = i;
            nums[2 * i + 1] = i;
        }
        nums[10000] = 99999;
        assertEquals(99999, solver.singleNumber(nums));
    }

    @Test public void testThreeElements() {
        assertEquals(7, solver.singleNumber(new int[]{3, 3, 7}));
    }

    @Test public void testSingletonAtFirstPosition() {
        assertEquals(-17, solver.singleNumber(new int[]{-17, 4, 9, 4, 9}));
    }

    @Test public void testSingletonInMiddlePosition() {
        assertEquals(30000, solver.singleNumber(new int[]{-8, -8, 30000, 12, 12}));
    }

    @Test public void testSingletonAtLastPosition() {
        assertEquals(23, solver.singleNumber(new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE,
                Integer.MIN_VALUE, Integer.MAX_VALUE, 23}));
    }

    @Test public void testAllNegative() {
        assertEquals(-3, solver.singleNumber(new int[]{-1, -2, -1, -2, -3}));
    }

    @Test public void testZeroAppearsTwicePlusUnique() {
        assertEquals(42, solver.singleNumber(new int[]{0, 0, 42}));
    }

    @Test public void testAllPositiveSameSign() {
        assertEquals(5, solver.singleNumber(new int[]{10, 20, 10, 20, 5}));
    }

    @Test public void testNegativeUnique() {
        assertEquals(-7, solver.singleNumber(new int[]{4, 4, -7, 8, 8}));
    }

    @Test public void testLargeArraySeed42() {
        Random rng = new Random(42L);
        int unique = 777777;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            int val = rng.nextInt();
            list.add(val);
            list.add(val);
        }
        list.add(unique);
        Collections.shuffle(list, new Random(42L));
        int[] nums = list.stream().mapToInt(Integer::intValue).toArray();
        assertEquals(unique, solver.singleNumber(nums));
    }

    @Test public void testIndependentFrequencyOracleOnGeneratedArrays() {
        Random rng = new Random(136L);
        for (int scenario = 0; scenario < 120; scenario++) {
            int unique = 25000 + scenario;
            List<Integer> values = new ArrayList<>();
            for (int pair = 0; pair < 40 + scenario % 20; pair++) {
                int value = -12000 + scenario * 67 + pair * 31;
                values.add(value);
                values.add(value);
            }
            values.add(unique);
            Collections.shuffle(values, rng);
            int[] nums = values.stream().mapToInt(Integer::intValue).toArray();
            assertEquals(frequencyOracle(nums), solver.singleNumber(nums));
        }
    }

    @Test public void testExhaustiveSmallPairConfigurations() {
        int[] domain = {-2, -1, 0, 1, 2};
        for (int unique : domain) {
            for (int first = 0; first < domain.length; first++) {
                if (domain[first] == unique) continue;
                for (int second = first + 1; second < domain.length; second++) {
                    if (domain[second] == unique) continue;
                    int[] nums = {domain[first], domain[first], unique,
                            domain[second], domain[second]};
                    assertEquals(frequencyOracle(nums), solver.singleNumber(nums));
                }
            }
        }
    }

    @Test public void testMaximumLeetCodeLength() {
        int[] nums = new int[29999];
        for (int i = 0; i < 14999; i++) {
            nums[2 * i] = i - 14999;
            nums[2 * i + 1] = i - 14999;
        }
        nums[nums.length - 1] = 30000;
        assertEquals(30000, solver.singleNumber(nums));
    }

    @Test public void testInputArrayIsNotMutated() {
        int[] nums = {9, -3, 9, -3, Integer.MIN_VALUE, Integer.MIN_VALUE, 27};
        int[] before = nums.clone();
        assertEquals(27, solver.singleNumber(nums));
        assertArrayEquals(before, nums);
    }

    @Test public void testSameInstanceCanBeReusedWithoutStateLeakage() {
        int[] first = {1, 1, 2, 2, -30000};
        int[] second = {Integer.MAX_VALUE, 4, 4, Integer.MAX_VALUE, 0};
        assertEquals(-30000, solver.singleNumber(first));
        assertEquals(0, solver.singleNumber(second));
        assertEquals(-30000, solver.singleNumber(first));
    }

    @Test public void testXorPropertyEqualsUnique() {
        int[] nums = {5, 3, 5, 9, 3, 9, -11};
        int xor = 0;
        for (int n : nums) xor ^= n;
        assertEquals(-11, xor);
        assertEquals(xor, solver.singleNumber(nums));
    }

    @Test public void testOrderIndependence() {
        int[] original = {8, 1, 8, 2, 1, 2, 99};
        int expected = solver.singleNumber(original);
        List<Integer> shuffled = new ArrayList<>();
        for (int n : original) shuffled.add(n);
        Collections.shuffle(shuffled, new Random(123L));
        int[] shuffledArr = shuffled.stream().mapToInt(Integer::intValue).toArray();
        assertEquals(expected, solver.singleNumber(shuffledArr));
    }

    @Test public void testUniqueAtEveryPositionWithBoundaryPairs() {
        int[] repeated = {Integer.MIN_VALUE, Integer.MAX_VALUE, 0, -1};
        for (int position = 0; position < 9; position++) {
            int[] values = new int[9];
            for (int i = 0, next = 0; i < values.length; i++)
                values[i] = i == position ? 12345 : repeated[(next++) / 2];
            assertEquals(12345, solver.singleNumber(values));
        }
    }

    @Test public void testEachBitCanBeTheUniqueValue() {
        for (int bit = 0; bit < 32; bit++) {
            int unique = 1 << bit;
            assertEquals(unique, solver.singleNumber(new int[]{~unique, unique, ~unique}));
        }
    }

    @Test public void testInterleavedPairsAtLargeSize() {
        int[] values = new int[29999];
        for (int i = 0; i < 14999; i++) values[i] = values[i + 14999] = i;
        values[29998] = Integer.MIN_VALUE;
        assertEquals(Integer.MIN_VALUE, solver.singleNumber(values));
    }

    private int frequencyOracle(int[] nums) {
        Map<Integer, Integer> frequencies = new HashMap<>();
        for (int num : nums) frequencies.merge(num, 1, Integer::sum);
        for (Map.Entry<Integer, Integer> entry : frequencies.entrySet()) {
            if (entry.getValue() == 1) return entry.getKey();
        }
        throw new AssertionError("The test input must contain one singleton");
    }
}
