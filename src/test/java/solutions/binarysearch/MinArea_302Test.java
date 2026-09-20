package solutions.binarysearch;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/** Contract tests for the binary-search solution to LeetCode 302. */
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

    @Test
    public void testSinglePixelsAtEveryImageCorner() {
        assertArea(new char[][]{{'1', '0'}, {'0', '0'}}, 0, 0);
        assertArea(new char[][]{{'0', '1'}, {'0', '0'}}, 0, 1);
        assertArea(new char[][]{{'0', '0'}, {'1', '0'}}, 1, 0);
        assertArea(new char[][]{{'0', '0'}, {'0', '1'}}, 1, 1);
    }

    @Test
    public void testSinglePixelInTheInterior() {
        assertArea(new char[][]{
            {'0', '0', '0'},
            {'0', '1', '0'},
            {'0', '0', '0'}
        }, 1, 1);
    }

    @Test
    public void testConnectedRectangleTouchesTopAndLeft() {
        assertArea(new char[][]{
            {'1', '1', '0', '0'},
            {'1', '1', '0', '0'},
            {'0', '0', '0', '0'}
        }, 0, 1);
    }

    @Test
    public void testConnectedRectangleTouchesBottomAndRight() {
        assertArea(new char[][]{
            {'0', '0', '0', '0'},
            {'0', '1', '1', '1'},
            {'0', '1', '1', '1'}
        }, 2, 3);
    }

    @Test
    public void testHorizontalLineTouchesBothSideBoundaries() {
        assertArea(new char[][]{{'1', '1', '1', '1', '1'}}, 0, 2);
    }

    @Test
    public void testVerticalLineTouchesBothRowBoundaries() {
        assertArea(new char[][]{
            {'1'}, {'1'}, {'1'}, {'1'}, {'1'}, {'1'}
        }, 3, 0);
    }

    @Test
    public void testIrregularShapeWithInteriorWhiteHole() {
        assertArea(new char[][]{
            {'0', '1', '1', '1', '0'},
            {'1', '1', '0', '1', '1'},
            {'1', '0', '0', '0', '1'},
            {'1', '1', '1', '1', '1'}
        }, 1, 1);
    }

    @Test
    public void testZigzagConnectedShape() {
        assertArea(new char[][]{
            {'1', '1', '0', '0', '0', '0'},
            {'0', '1', '1', '0', '0', '0'},
            {'0', '0', '1', '1', '0', '0'},
            {'0', '0', '0', '1', '1', '0'},
            {'0', '0', '0', '0', '1', '1'}
        }, 3, 3);
    }

    @Test
    public void testCrossShapeWithAnchorOnEachExtreme() {
        char[][] image = image(
            "00100",
            "00100",
            "11111",
            "00100",
            "00100");
        assertArea(image, 0, 2);
        assertArea(copy(image), 2, 0);
        assertArea(copy(image), 2, 4);
        assertArea(copy(image), 4, 2);
    }

    @Test
    public void testTopAndBottomSeparatedRowsConnectedByNarrowStem() {
        assertArea(new char[][]{
            {'0', '1', '1', '1', '0'},
            {'0', '0', '1', '0', '0'},
            {'0', '0', '1', '0', '0'},
            {'0', '1', '1', '1', '0'}
        }, 3, 1);
    }

    @Test
    public void testBlackRegionAtOneCellFromEveryBoundary() {
        assertArea(new char[][]{
            {'0', '0', '0', '0', '0', '0'},
            {'0', '1', '1', '0', '0', '0'},
            {'0', '0', '1', '0', '0', '0'},
            {'0', '0', '0', '0', '0', '0'},
            {'0', '0', '0', '0', '0', '0'}
        }, 1, 1);
    }

    @Test
    public void testAllBlackImageAtMaximumDimensions() {
        char[][] image = new char[100][100];
        for (int row = 0; row < image.length; row++) {
            java.util.Arrays.fill(image[row], '1');
        }
        assertArea(image, 50, 50);
    }

    @Test
    public void testMaximumSingleRowImage() {
        char[][] image = new char[1][100];
        java.util.Arrays.fill(image[0], '0');
        for (int col = 20; col <= 79; col++) {
            image[0][col] = '1';
        }
        assertArea(image, 0, 50);
    }

    @Test
    public void testMaximumSingleColumnImage() {
        char[][] image = new char[100][1];
        for (char[] row : image) {
            java.util.Arrays.fill(row, '0');
        }
        for (int row = 30; row <= 89; row++) {
            image[row][0] = '1';
        }
        assertArea(image, 60, 0);
    }

    @Test
    public void testGeneratedConnectedShapesAgainstIndependentOracle() {
        String[] patterns = {
            "1000|1100|0110|0011",
            "01110|01110|00100|00100",
            "11100|10100|11111|00100",
            "001000|011100|111110|001000|001000",
            "1111|0001|1111|1000|1111",
            "01010|11111|01010|11111"
        };
        for (String pattern : patterns) {
            char[][] image = image(pattern.split("\\|"));
            for (int row = 0; row < image.length; row++) {
                for (int col = 0; col < image[0].length; col++) {
                    if (image[row][col] == '1') {
                        assertArea(copy(image), row, col);
                    }
                }
            }
        }
    }

    @Test
    public void testRepeatedCallsDoNotShareSearchBounds() {
        assertArea(image("0001", "0011", "0110"), 0, 3);
        assertArea(image("10000", "11000", "01111"), 2, 4);
        assertArea(image("000", "010", "111", "010"), 1, 1);
    }

    @Test
    public void testInputIsNotMutated() {
        char[][] image = image("00100", "01110", "00100");
        char[][] original = copy(image);
        assertArea(image, 1, 1);
        for (int row = 0; row < image.length; row++) {
            assertArrayEquals(original[row], image[row]);
        }
    }

    @Test
    public void testIndependentOracleHandlesNonSquareImages() {
        assertArea(image(
            "0000001",
            "0000011",
            "0000110",
            "0001100"), 2, 4);
        assertArea(image(
            "0001",
            "0011",
            "0111",
            "1110",
            "1100",
            "1000"), 5, 0);
    }

    @Test
    public void testSparseRegionUsesInteriorAnchor() {
        assertArea(image(
            "000000",
            "001000",
            "011100",
            "001000",
            "000000"), 2, 2);
    }

    @Test
    public void testBoundingBoxMayContainManyWhitePixels() {
        assertArea(image(
            "11111",
            "10001",
            "10001",
            "10001",
            "11111"), 0, 0);
    }

    @Test
    public void testSingleBlackPixelAtEveryPositionInSmallImage() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 4; col++) {
                char[][] image = new char[3][4];
                for (char[] imageRow : image) {
                    java.util.Arrays.fill(imageRow, '0');
                }
                image[row][col] = '1';
                assertArea(image, row, col);
            }
        }
    }

    private void assertArea(char[][] image, int x, int y) {
        assertEquals(expectedBoundingBoxArea(image), test.minArea(image, x, y),
            "anchor=(" + x + "," + y + ")");
    }

    /** Independent linear oracle: scan every black pixel, including disconnected ones if supplied. */
    private static int expectedBoundingBoxArea(char[][] image) {
        int top = image.length;
        int bottom = -1;
        int left = image[0].length;
        int right = -1;
        for (int row = 0; row < image.length; row++) {
            for (int col = 0; col < image[row].length; col++) {
                if (image[row][col] == '1') {
                    top = Math.min(top, row);
                    bottom = Math.max(bottom, row);
                    left = Math.min(left, col);
                    right = Math.max(right, col);
                }
            }
        }
        return (bottom - top + 1) * (right - left + 1);
    }

    private static char[][] image(String... rows) {
        char[][] image = new char[rows.length][];
        for (int row = 0; row < rows.length; row++) {
            image[row] = rows[row].toCharArray();
        }
        return image;
    }

    private static char[][] copy(char[][] source) {
        char[][] result = new char[source.length][];
        for (int row = 0; row < source.length; row++) {
            result[row] = source[row].clone();
        }
        return result;
    }
}
