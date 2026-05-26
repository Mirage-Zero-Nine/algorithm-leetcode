package solutions.bitmanipulation;

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

    @Test public void testN5Exact() {
        assertArrayEquals(new int[]{0, 1, 1, 2, 1, 2}, solver.countBits(5));
    }

    @Test public void testN16Has17Elements() {
        int[] result = solver.countBits(16);
        assertEquals(17, result.length);
    }

    @Test public void testLargeN1000CrossCheckBitCount() {
        int[] result = solver.countBits(1000);
        for (int i = 0; i <= 1000; i++) {
            assertEquals(Integer.bitCount(i), result[i], "Mismatch at i=" + i);
        }
    }

    @Test public void testPropertyLengthEqualsNPlusOne() {
        for (int n : new int[]{0, 1, 2, 10, 50, 255, 1024}) {
            assertEquals(n + 1, solver.countBits(n).length, "Length mismatch for n=" + n);
        }
    }

    @Test public void testPropertyMatchesIntegerBitCount() {
        int[] result = solver.countBits(500);
        for (int i = 0; i <= 500; i++) {
            assertEquals(Integer.bitCount(i), result[i]);
        }
    }

    @Test public void testPropertyEvenIndexRelation() {
        // result[2*i] == result[i]
        int[] result = solver.countBits(512);
        for (int i = 1; i <= 256; i++) {
            assertEquals(result[i], result[2 * i], "result[2*" + i + "] != result[" + i + "]");
        }
    }

    @Test public void testPropertyOddIndexRelation() {
        // result[2*i+1] == result[i] + 1
        int[] result = solver.countBits(513);
        for (int i = 0; i <= 256; i++) {
            assertEquals(result[i] + 1, result[2 * i + 1], "result[2*" + i + "+1] != result[" + i + "]+1");
        }
    }

    @Test public void testPowerOfTwoAlwaysOne() {
        int[] result = solver.countBits(1024);
        for (int p = 0; p <= 10; p++) {
            assertEquals(1, result[1 << p], "2^" + p + " should have 1 bit set");
        }
    }

    @Test public void testAllOnesNumbers() {
        // Numbers like 1, 3, 7, 15, 31... (2^k - 1) have k bits set
        int[] result = solver.countBits(1023);
        for (int k = 1; k <= 10; k++) {
            assertEquals(k, result[(1 << k) - 1], "(2^" + k + ")-1 should have " + k + " bits");
        }
    }
}
