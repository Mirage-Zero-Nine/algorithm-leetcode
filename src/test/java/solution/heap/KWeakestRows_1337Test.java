package solution.heap;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class KWeakestRows_1337Test {

    private final KWeakestRows_1337 test = new KWeakestRows_1337();

    @Test
    public void testHappyCases() {
        assertArrayEquals(new int[]{2, 0, 3}, test.kWeakestRows(new int[][]{
            {1, 1, 0, 0, 0},
            {1, 1, 1, 1, 0},
            {1, 0, 0, 0, 0},
            {1, 1, 0, 0, 0},
            {1, 1, 1, 1, 1}
        }, 3));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertArrayEquals(new int[]{0}, test.kWeakestRows(new int[][]{{0, 0}, {1, 1}}, 1));
        assertArrayEquals(new int[]{0, 1}, test.kWeakestRows(new int[][]{{1}, {1}}, 2));
    }

    @Test
    public void testLargeCase() {
        assertArrayEquals(new int[]{0, 1, 2, 3}, test.kWeakestRows(new int[][]{
            {0, 0, 0, 0, 0},
            {1, 0, 0, 0, 0},
            {1, 1, 0, 0, 0},
            {1, 1, 1, 0, 0},
            {1, 1, 1, 1, 0},
            {1, 1, 1, 1, 1}
        }, 4));
    }
}
