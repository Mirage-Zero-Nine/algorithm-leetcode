package solution.bitmanipulation;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

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
}
