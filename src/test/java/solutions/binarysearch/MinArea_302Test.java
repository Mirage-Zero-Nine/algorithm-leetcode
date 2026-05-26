package solutions.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MinArea_302Test {

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

    @Test
    public void testSingleRowConnectedRegion() {
        assertEquals(4, test.minArea(new char[][]{{'0', '1', '1', '1', '1', '0'}}, 0, 2));
    }

    @Test
    public void testSingleColumnConnectedRegion() {
        assertEquals(3, test.minArea(new char[][]{{'0'}, {'1'}, {'1'}, {'1'}, {'0'}}, 2, 0));
    }

    @Test
    public void testRectangleNotAtOrigin() {
        assertEquals(6, test.minArea(new char[][]{
            {'0', '0', '0', '0', '0'},
            {'0', '0', '1', '1', '0'},
            {'0', '0', '1', '1', '0'},
            {'0', '0', '1', '1', '0'}
        }, 2, 3));
    }

    @Test
    public void testAnchorAtBottomRightBlackPixel() {
        assertEquals(6, test.minArea(new char[][]{
            {'0', '0', '1', '0'},
            {'0', '1', '1', '0'},
            {'0', '1', '0', '0'}
        }, 2, 1));
    }

    @Test
    public void testThinVerticalConnectedShape() {
        assertEquals(5, test.minArea(new char[][]{
            {'0', '1', '0'},
            {'0', '1', '0'},
            {'0', '1', '0'},
            {'0', '1', '0'},
            {'0', '1', '0'}
        }, 4, 1));
    }

    @Test
    public void testConnectedLShapeBoundingBox() {
        assertEquals(9, test.minArea(new char[][]{
            {'1', '0', '0'},
            {'1', '0', '0'},
            {'1', '1', '1'}
        }, 2, 2));
    }

    @Test
    public void testGiantCaseConnectedBlock() {
        int rows = 80;
        int cols = 90;
        char[][] image = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                image[i][j] = '0';
            }
        }
        for (int i = 10; i <= 69; i++) {
            for (int j = 20; j <= 79; j++) {
                image[i][j] = '1';
            }
        }
        assertEquals(3600, test.minArea(image, 40, 50));
    }
}
