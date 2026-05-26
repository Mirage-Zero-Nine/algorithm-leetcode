package solutions.math;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PrisonAfterNDays_957Test {

    private final PrisonAfterNDays_957 test = new PrisonAfterNDays_957();

    @Test
    public void testHappyCases() {
        assertArrayEquals(new int[]{0, 0, 1, 1, 0, 0, 0, 0},
            test.prisonAfterNDays(new int[]{0, 1, 0, 1, 1, 0, 0, 1}, 7));
        assertArrayEquals(new int[]{0, 0, 1, 1, 1, 1, 1, 0},
            test.prisonAfterNDays(new int[]{1, 0, 0, 1, 0, 0, 1, 0}, 1000000000));
    }

    @Test
    public void testEdgeCases() {
        assertArrayEquals(new int[]{0, 1, 1, 0, 0, 0, 0, 0},
            test.prisonAfterNDays(new int[]{0, 1, 0, 1, 1, 0, 0, 1}, 1));
    }

    @Test
    public void testLargeCase() {
        assertArrayEquals(new int[]{0, 0, 1, 1, 0, 0, 0, 0},
            test.prisonAfterNDays(new int[]{0, 1, 0, 1, 1, 0, 0, 1}, 7));
    }

    @Test
    public void testAllZeros() {
        assertArrayEquals(new int[]{0, 1, 1, 1, 1, 1, 1, 0},
            test.prisonAfterNDays(new int[]{0, 0, 0, 0, 0, 0, 0, 0}, 1));
    }

    @Test
    public void testAllOnes() {
        assertArrayEquals(new int[]{0, 1, 1, 1, 1, 1, 1, 0},
            test.prisonAfterNDays(new int[]{1, 1, 1, 1, 1, 1, 1, 1}, 1));
    }

    @Test
    public void testDay2() {
        assertArrayEquals(new int[]{0, 0, 0, 0, 1, 1, 1, 0},
            test.prisonAfterNDays(new int[]{0, 1, 0, 1, 1, 0, 0, 1}, 2));
    }

    @Test
    public void testDay14Cycle() {
        // After 14 days, should be same as day 14 (cycle is 14)
        int[] input = {0, 1, 0, 1, 1, 0, 0, 1};
        int[] day14 = test.prisonAfterNDays(input.clone(), 14);
        int[] day28 = test.prisonAfterNDays(input.clone(), 28);
        assertArrayEquals(day14, day28);
    }

    @Test
    public void testNEquals1() {
        assertArrayEquals(new int[]{0, 0, 0, 1, 0, 0, 1, 0},
            test.prisonAfterNDays(new int[]{1, 0, 0, 1, 0, 0, 1, 0}, 1));
    }

    @Test
    public void testGiantN() {
        // Very large N, should still work due to mod 14
        int[] result = test.prisonAfterNDays(new int[]{0, 1, 0, 1, 1, 0, 0, 1}, 999999999);
        assertEquals(8, result.length);
        assertEquals(0, result[0]);
        assertEquals(0, result[7]);
    }

    @Test
    public void testAlternating() {
        assertArrayEquals(new int[]{0, 1, 1, 1, 1, 1, 1, 0},
            test.prisonAfterNDays(new int[]{0, 1, 0, 1, 0, 1, 0, 1}, 1));
    }
}
