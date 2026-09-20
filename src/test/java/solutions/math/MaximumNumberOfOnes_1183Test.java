package solutions.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaximumNumberOfOnes_1183Test {

    private final MaximumNumberOfOnes_1183 test = new MaximumNumberOfOnes_1183();

    @Test
    public void testHappyCases() {
        assertEquals(6, test.maximumNumberOfOnes(5, 3, 2, 1));
        assertEquals(0, test.maximumNumberOfOnes(2, 2, 2, 0));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.maximumNumberOfOnes(3, 3, 2, 0));
        assertEquals(1, test.maximumNumberOfOnes(1, 1, 1, 1));
    }

    @Test
    public void testLargeCase() {
        assertEquals(8, test.maximumNumberOfOnes(4, 4, 2, 2));
    }

    @Test
    public void testMaxOnesEqualsSideLengthSquared() {
        // all cells in sub-matrix can be 1
        assertEquals(9, test.maximumNumberOfOnes(3, 3, 3, 9));
    }

    @Test
    public void testWidthLargerThanSideLength() {
        assertEquals(2, test.maximumNumberOfOnes(4, 2, 2, 1));
    }

    @Test
    public void testHeightLargerThanSideLength() {
        assertEquals(2, test.maximumNumberOfOnes(2, 4, 2, 1));
    }

    @Test
    public void testSideLengthOne() {
        // sideLength=1, maxOnes=1 means every cell can be 1
        assertEquals(6, test.maximumNumberOfOnes(3, 2, 1, 1));
    }

    @Test
    public void testNegativeMaxOnesZero() {
        assertEquals(0, test.maximumNumberOfOnes(10, 10, 3, 0));
    }

    @Test
    public void testGiantMatrix() {
        // large width and height
        int result = test.maximumNumberOfOnes(100, 100, 5, 3);
        assertEquals(1200, result);
    }

    @Test
    public void testSquareMatrixEqualsSideLength() {
        assertEquals(2, test.maximumNumberOfOnes(2, 2, 2, 2));
    }

    @Test
    public void testAdditional1() {
        assertEquals(1, test.maximumNumberOfOnes(1, 1, 1, 1));
    }

    @Test
    public void testAdditional2() {
        assertEquals(4, test.maximumNumberOfOnes(2, 2, 1, 1));
    }

    @Test
    public void testAdditional3() {
        assertEquals(1, test.maximumNumberOfOnes(2, 2, 2, 1));
    }

    @Test
    public void testAdditional4() {
        assertEquals(4, test.maximumNumberOfOnes(3, 3, 2, 1));
    }

    @Test
    public void testAdditional5() {
        assertEquals(6, test.maximumNumberOfOnes(3, 3, 2, 2));
    }

    @Test
    public void testAdditional6() {
        assertEquals(4, test.maximumNumberOfOnes(4, 4, 2, 1));
    }

    @Test
    public void testAdditional7() {
        assertEquals(8, test.maximumNumberOfOnes(4, 4, 2, 2));
    }

    @Test
    public void testAdditional8() {
        assertEquals(6, test.maximumNumberOfOnes(5, 3, 2, 1));
    }

    @Test
    public void testAdditional9() {
        assertEquals(16, test.maximumNumberOfOnes(6, 6, 3, 4));
    }

    @Test
    public void testAdditional10() {
        assertEquals(500, test.maximumNumberOfOnes(100, 100, 10, 5));
    }
}
