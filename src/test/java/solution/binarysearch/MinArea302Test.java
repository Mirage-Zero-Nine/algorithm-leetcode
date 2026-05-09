package solution.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MinArea302Test {

    private final MinArea_302 test = new MinArea_302();

    @Test
    public void testHappyCases() {
        assertEquals(6, test.minArea(new char[][]{{'0', '0', '1', '0'}, {'0', '1', '1', '0'}, {'0', '1', '0', '0'}}, 0, 2));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.minArea(new char[][]{{'1'}}, 0, 0));
        assertEquals(4, test.minArea(new char[][]{{'1', '1'}, {'1', '1'}}, 0, 0));
    }

    @Test
    public void testLargeCase() {
        assertEquals(9, test.minArea(new char[][]{
            {'0', '1', '0', '0'},
            {'1', '1', '1', '0'},
            {'0', '1', '0', '0'}
        }, 1, 1));
    }
}
