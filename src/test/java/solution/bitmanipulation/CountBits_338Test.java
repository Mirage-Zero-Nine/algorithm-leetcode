package solution.bitmanipulation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountBits_338Test {
    private final CountBits_338 solver = new CountBits_338();

    @Test public void testBasic() {
        assertArrayEquals(new int[]{0, 1, 1, 2, 1, 2}, solver.countBits(5));
    }

    @Test public void testZero() {
        assertArrayEquals(new int[]{0}, solver.countBits(0));
    }

    @Test public void testOne() {
        assertArrayEquals(new int[]{0, 1}, solver.countBits(1));
    }

    @Test public void testSeven() {
        assertArrayEquals(new int[]{0, 1, 1, 2, 1, 2, 2, 3}, solver.countBits(7));
    }

    @Test public void testSixteen() {
        int[] result = solver.countBits(16);
        assertArrayEquals(new int[]{0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 2, 3, 2, 3, 3, 4, 1}, result);
    }

    @Test public void testTwo() {
        assertArrayEquals(new int[]{0, 1, 1}, solver.countBits(2));
    }

    @Test public void testThree() {
        assertArrayEquals(new int[]{0, 1, 1, 2}, solver.countBits(3));
    }

    @Test public void testFifteen() {
        int[] result = solver.countBits(15);
        assertArrayEquals(new int[]{0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 2, 3, 2, 3, 3, 4}, result);
    }

    @Test public void testPowerOfTwo() {
        int[] result = solver.countBits(8);
        // 8 = 1000, so countBits(8)[8] should be 1
        assertEquals(1, result[8]);
    }

    @Test public void testGiantCase() {
        int[] result = solver.countBits(100000);
        assertEquals(100001, result.length);
        assertEquals(0, result[0]);
        assertEquals(1, result[1]);
        // 1023 = 0b1111111111, 10 ones
        assertEquals(10, result[1023]);
    }
}
