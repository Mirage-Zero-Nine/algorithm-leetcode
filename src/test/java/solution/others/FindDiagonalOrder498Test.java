package solution.others;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class FindDiagonalOrder498Test {

    private final FindDiagonalOrder_498 test = new FindDiagonalOrder_498();

    @Test
    public void testHappyCases() {
        assertArrayEquals(new int[]{1, 2, 4, 7, 5, 3, 6, 8, 9},
            test.findDiagonalOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertArrayEquals(new int[]{}, test.findDiagonalOrder(new int[][]{}));
        assertArrayEquals(new int[]{1}, test.findDiagonalOrder(new int[][]{{1}}));
    }

    @Test
    public void testLargeCase() {
        assertArrayEquals(new int[]{1, 2, 3, 4}, test.findDiagonalOrder(new int[][]{{1, 2}, {3, 4}}));
    }
}
