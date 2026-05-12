package solution.map;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class ShortestDistanceColor1182Test {

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
}
