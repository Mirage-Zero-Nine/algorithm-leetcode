package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TilingRectangle_1240Test {

    @Test
    public void testHappyCases() {
        assertEquals(3, new TilingRectangle_1240().tilingRectangle(2, 3));
        assertEquals(5, new TilingRectangle_1240().tilingRectangle(5, 8));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, new TilingRectangle_1240().tilingRectangle(1, 1));
        assertEquals(1, new TilingRectangle_1240().tilingRectangle(3, 3));
    }

    @Test
    public void testLargeCase() {
        assertEquals(6, new TilingRectangle_1240().tilingRectangle(11, 13));
    }

    @Test
    public void testSquares() {
        assertEquals(1, new TilingRectangle_1240().tilingRectangle(2, 2));
        assertEquals(1, new TilingRectangle_1240().tilingRectangle(5, 5));
    }

    @Test
    public void testOneByN() {
        assertEquals(2, new TilingRectangle_1240().tilingRectangle(1, 2));
        assertEquals(3, new TilingRectangle_1240().tilingRectangle(1, 3));
        assertEquals(4, new TilingRectangle_1240().tilingRectangle(1, 4));
    }

    @Test
    public void testSymmetry() {
        // tilingRectangle(m, n) == tilingRectangle(n, m)
        assertEquals(new TilingRectangle_1240().tilingRectangle(2, 3),
            new TilingRectangle_1240().tilingRectangle(3, 2));
    }

    @Test
    public void testTwoByFour() {
        assertEquals(2, new TilingRectangle_1240().tilingRectangle(2, 4));
    }

    @Test
    public void testThreeByFive() {
        assertEquals(4, new TilingRectangle_1240().tilingRectangle(3, 5));
    }

    @Test
    public void testFourBySix() {
        assertEquals(3, new TilingRectangle_1240().tilingRectangle(4, 6));
    }

    @Test
    public void testSymmetryLarger() {
        assertEquals(new TilingRectangle_1240().tilingRectangle(5, 8),
            new TilingRectangle_1240().tilingRectangle(8, 5));
    }

    @Test
    public void testGiantCase() {
        assertEquals(6, new TilingRectangle_1240().tilingRectangle(13, 11));
    }

    @ParameterizedTest(name = "tile {0}x{1}")
    @CsvSource({"1,5,5", "1,6,6", "2,5,4", "2,6,3", "3,4,4", "3,6,2", "4,5,5", "4,7,5", "5,6,5", "6,8,4"})
    public void testAdditionalSmallRectangles(int height, int width, int expected) {
        assertEquals(expected, new TilingRectangle_1240().tilingRectangle(height, width));
    }
}
