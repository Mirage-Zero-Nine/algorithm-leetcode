package solution.graph;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FindOrder210Test {

    private final FindOrder_210 test = new FindOrder_210();

    @Test
    public void testHappyCases() {
        assertArrayEquals(new int[]{0, 1}, test.findOrder(2, new int[][]{{1, 0}}));
        int[] result = test.findOrder(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}});
        assertEquals(4, result.length);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertArrayEquals(new int[]{}, test.findOrder(2, new int[][]{{1, 0}, {0, 1}}));
        assertArrayEquals(new int[]{0}, test.findOrder(1, new int[][]{}));
    }

    @Test
    public void testLargeCase() {
        int[] result = test.findOrder(5, new int[][]{{1, 0}, {2, 1}, {3, 2}, {4, 3}});
        assertEquals(5, result.length);
        assertEquals(0, result[0]);
        assertEquals(4, result[4]);
    }
}
