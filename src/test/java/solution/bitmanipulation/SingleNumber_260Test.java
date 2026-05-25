package solution.bitmanipulation;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SingleNumber_260Test {
    private final SingleNumber_260 solver = new SingleNumber_260();

    private int[] sorted(int[] arr) {
        int[] copy = arr.clone();
        Arrays.sort(copy);
        return copy;
    }

    @Test public void testBasic() {
        int[] result = solver.singleNumber(new int[]{1, 2, 1, 3, 2, 5});
        assertArrayEquals(new int[]{3, 5}, sorted(result));
    }

    @Test public void testNegative() {
        int[] result = solver.singleNumber(new int[]{-1, 0});
        assertArrayEquals(new int[]{-1, 0}, sorted(result));
    }

    @Test public void testTwoElements() {
        int[] result = solver.singleNumber(new int[]{7, 13});
        assertArrayEquals(new int[]{7, 13}, sorted(result));
    }

    @Test public void testMixed() {
        int[] result = solver.singleNumber(new int[]{1, 1, 2, 2, 3, 3, 4, 5});
        assertArrayEquals(new int[]{4, 5}, sorted(result));
    }

    @Test public void testWithZero() {
        int[] result = solver.singleNumber(new int[]{0, 1, 2, 2});
        assertArrayEquals(new int[]{0, 1}, sorted(result));
    }

    @Test public void testLargeNumbers() {
        int[] result = solver.singleNumber(new int[]{1000000, 999999, 1000000, 888888});
        assertArrayEquals(new int[]{888888, 999999}, sorted(result));
    }

    @Test public void testBothNegative() {
        int[] result = solver.singleNumber(new int[]{-3, -5, 1, 1});
        assertArrayEquals(new int[]{-5, -3}, sorted(result));
    }

    @Test public void testMixedNegativePositive() {
        int[] result = solver.singleNumber(new int[]{-1, 2, -1, 3, 2, 5});
        assertArrayEquals(new int[]{3, 5}, sorted(result));
    }

    @Test public void testPowersOfTwo() {
        int[] result = solver.singleNumber(new int[]{4, 8, 4, 16, 8, 32});
        assertArrayEquals(new int[]{16, 32}, sorted(result));
    }

    @Test public void testGiantCase() {
        // large array: pairs from 1 to 5000, plus two singles 0 and 10001
        int[] nums = new int[10002];
        int idx = 0;
        for (int i = 1; i <= 5000; i++) {
            nums[idx++] = i;
            nums[idx++] = i;
        }
        nums[idx++] = 0;
        nums[idx] = 10001;
        int[] result = solver.singleNumber(nums);
        assertArrayEquals(new int[]{0, 10001}, sorted(result));
    }

    @Test public void testTwoUniquesOnly() {
        // Two unique with no pairs at all
        int[] result = solver.singleNumber(new int[]{42, 99});
        assertEquals(Set.of(42, 99), Set.of(result[0], result[1]));
    }

    @Test public void testNegativePositiveUniques() {
        // Negative + positive uniques among pairs
        int[] result = solver.singleNumber(new int[]{-7, 3, 5, 5, 3, 100});
        assertEquals(Set.of(-7, 100), Set.of(result[0], result[1]));
    }

    @Test public void testTwoNegativeUniques() {
        int[] result = solver.singleNumber(new int[]{-10, -20, 4, 4, 7, 7});
        assertEquals(Set.of(-10, -20), Set.of(result[0], result[1]));
    }

    @Test public void testAdjacentValues() {
        // Adjacent values [1,2] both unique
        int[] result = solver.singleNumber(new int[]{1, 2, 9, 9, 8, 8});
        assertEquals(Set.of(1, 2), Set.of(result[0], result[1]));
    }

    @Test public void testIntMaxMin() {
        int[] result = solver.singleNumber(new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, 5, 5});
        assertEquals(Set.of(Integer.MAX_VALUE, Integer.MIN_VALUE), Set.of(result[0], result[1]));
    }

    @Test public void testZeroAmongUniques() {
        int[] result = solver.singleNumber(new int[]{0, 77, 3, 3, 9, 9});
        assertEquals(Set.of(0, 77), Set.of(result[0], result[1]));
    }

    @Test public void testLargeArray1000PairsSeed42() {
        Random rng = new Random(42L);
        int unique1 = 1_000_001, unique2 = -1_000_001;
        int[] nums = new int[2002];
        for (int i = 0; i < 1000; i++) {
            int v = rng.nextInt(1_000_000);
            nums[2 * i] = v;
            nums[2 * i + 1] = v;
        }
        nums[2000] = unique1;
        nums[2001] = unique2;
        int[] result = solver.singleNumber(nums);
        assertEquals(Set.of(unique1, unique2), Set.of(result[0], result[1]));
    }

    @Test public void testPropertyTwoDistinctValues() {
        int[] result = solver.singleNumber(new int[]{10, 20, 3, 3, 4, 4});
        assertEquals(2, result.length);
        assertNotEquals(result[0], result[1]);
    }

    @Test public void testPropertyXorOfResultEqualsXorOfInput() {
        int[] nums = {1, 1, 2, 2, 3, 3, 7, 11};
        int xorAll = 0;
        for (int n : nums) xorAll ^= n;
        int[] result = solver.singleNumber(nums);
        assertEquals(xorAll, result[0] ^ result[1]);
    }
}
