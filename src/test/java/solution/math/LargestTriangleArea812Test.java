package solution.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LargestTriangleArea812Test {

    private final LargestTriangleArea_812 test = new LargestTriangleArea_812();

    @Test
    public void testHappyCases() {
        assertEquals(2.0, test.largestTriangleArea(new int[][]{{0, 0}, {0, 1}, {1, 0}, {0, 2}, {2, 0}}), 0.0001);
    }

    @Test
    public void testEdgeCases() {
        assertEquals(0.5, test.largestTriangleArea(new int[][]{{0, 0}, {1, 0}, {0, 1}}), 0.0001);
        assertEquals(0.0, test.largestTriangleArea(new int[][]{{0, 0}, {1, 1}, {2, 2}}), 0.0001);
    }

    @Test
    public void testLargeCase() {
        assertEquals(50.0, test.largestTriangleArea(new int[][]{{0, 0}, {10, 0}, {0, 10}, {5, 5}}), 0.0001);
    }
}
