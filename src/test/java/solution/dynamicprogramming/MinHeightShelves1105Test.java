package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MinHeightShelves1105Test {

    private final MinHeightShelves_1105 test = new MinHeightShelves_1105();

    @Test
    public void testHappyCases() {
        assertEquals(6, test.minHeightShelves(new int[][]{{1, 1}, {2, 3}, {2, 3}, {1, 1}, {1, 1}, {1, 1}, {1, 2}}, 4));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.minHeightShelves(new int[][]{{1, 1}}, 1));
    }

    @Test
    public void testLargeCase() {
        assertEquals(4, test.minHeightShelves(new int[][]{{1, 3}, {2, 4}, {3, 2}}, 6));
    }
}
