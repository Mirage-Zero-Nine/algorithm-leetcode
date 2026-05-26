package solutions.hashmap;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class ShortestDistanceColor_1182Test {

    private final ShortestDistanceColor_1182 test = new ShortestDistanceColor_1182();

    @Test
    public void testHappyCases() {
        assertEquals(List.of(3, 0, 3),
            test.shortestDistanceColor(new int[]{1, 1, 2, 1, 3, 2, 2, 3, 3}, new int[][]{{1, 3}, {2, 2}, {6, 1}}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(List.of(-1), test.shortestDistanceColor(new int[]{1, 2, 3}, new int[][]{{0, 4}}));
        assertEquals(List.of(0), test.shortestDistanceColor(new int[]{1}, new int[][]{{0, 1}}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(List.of(0, 0, 0, 0, 0),
            test.shortestDistanceColor(new int[]{1, 2, 1, 2, 1}, new int[][]{{0, 1}, {1, 2}, {2, 1}, {3, 2}, {4, 1}}));
    }

    @Test
    public void testColorNotPresent() {
        assertEquals(List.of(-1, -1),
            test.shortestDistanceColor(new int[]{1, 1, 1}, new int[][]{{0, 2}, {2, 3}}));
    }

    @Test
    public void testSameIndexSameColor() {
        assertEquals(List.of(0),
            test.shortestDistanceColor(new int[]{2, 1, 3}, new int[][]{{1, 1}}));
    }

    @Test
    public void testDistanceToLeft() {
        // color 1 is at index 0, query at index 2 for color 1 -> distance 2
        assertEquals(List.of(2),
            test.shortestDistanceColor(new int[]{1, 2, 3}, new int[][]{{2, 1}}));
    }

    @Test
    public void testDistanceToRight() {
        // color 3 is at index 2, query at index 0 for color 3 -> distance 2
        assertEquals(List.of(2),
            test.shortestDistanceColor(new int[]{1, 2, 3}, new int[][]{{0, 3}}));
    }

    @Test
    public void testMinOfLeftAndRight() {
        // color 1 at index 0 and 4, query at index 3 -> min(3, 1) = 1
        assertEquals(List.of(1),
            test.shortestDistanceColor(new int[]{1, 2, 3, 2, 1}, new int[][]{{3, 1}}));
    }

    @Test
    public void testMultipleQueries() {
        assertEquals(List.of(0, 1, 0, 1, -1),
            test.shortestDistanceColor(new int[]{1, 2, 1, 2, 1},
                new int[][]{{0, 1}, {0, 2}, {2, 1}, {2, 2}, {0, 3}}));
    }

    @Test
    public void testAllSameColor() {
        assertEquals(List.of(0, 0, 0),
            test.shortestDistanceColor(new int[]{2, 2, 2, 2, 2},
                new int[][]{{0, 2}, {2, 2}, {4, 2}}));
    }

    @Test
    public void testGiantCase() {
        int[] colors = new int[10000];
        for (int i = 0; i < 10000; i++) colors[i] = (i % 3) + 1;
        int[][] queries = new int[100][2];
        for (int i = 0; i < 100; i++) {
            queries[i] = new int[]{i * 100, (i % 3) + 1};
        }
        List<Integer> result = test.shortestDistanceColor(colors, queries);
        // each query asks for the color at its own index, so distance is 0
        for (int r : result) {
            assertEquals(0, r);
        }
    }
}
