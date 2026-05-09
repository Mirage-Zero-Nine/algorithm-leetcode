package solution.math;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class PrisonAfterNDays957Test {

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
}
