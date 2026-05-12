package solution.bitmanipulation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

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
}
