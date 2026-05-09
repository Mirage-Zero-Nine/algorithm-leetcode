package solution.heap;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AssignBikes_1066Test {

    private final AssignBikes_1066 test = new AssignBikes_1066();

    @Test
    public void testHappyCases() {
        assertEquals(6, test.assignBikes(new int[][]{{0, 0}, {2, 1}}, new int[][]{{1, 2}, {3, 3}}));
        assertEquals(3, test.assignBikes(new int[][]{{0, 0}, {1, 1}}, new int[][]{{1, 0}, {2, 2}}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(1, test.assignBikes(new int[][]{{0, 0}}, new int[][]{{0, 1}}));
        assertEquals(0, test.assignBikes(new int[][]{{0, 0}}, new int[][]{{0, 0}}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(3, test.assignBikes(
            new int[][]{{0, 0}, {2, 2}, {4, 4}},
            new int[][]{{0, 1}, {2, 3}, {4, 5}, {8, 8}}
        ));
    }
}
