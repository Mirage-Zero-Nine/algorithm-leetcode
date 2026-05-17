package solution.bitmanipulation;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
}
