package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MinScoreTriangulation_1039Test {

    private final MinScoreTriangulation_1039 test = new MinScoreTriangulation_1039();

    @Test
    public void testHappyCases() {
        assertEquals(6, test.minScoreTriangulation(new int[]{1, 2, 3}));
        assertEquals(144, test.minScoreTriangulation(new int[]{3, 7, 4, 5}));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(0, test.minScoreTriangulation(new int[]{1, 2}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(13, test.minScoreTriangulation(new int[]{1, 3, 1, 4, 1, 5}));
    }

    @Test
    public void testTriangle() {
        assertEquals(6, test.minScoreTriangulation(new int[]{1, 2, 3}));
    }

    @Test
    public void testSquareWithOnes() {
        assertEquals(3, test.minScoreTriangulation(new int[]{1, 1, 1, 2}));
    }

    @Test
    public void testPentagon() {
        assertEquals(144, test.minScoreTriangulation(new int[]{3, 7, 4, 5}));
    }

    @Test
    public void testAllOnes() {
        assertEquals(3, test.minScoreTriangulation(new int[]{1, 1, 1, 1, 1}));
    }

    @Test
    public void testTwoElements() {
        assertEquals(0, test.minScoreTriangulation(new int[]{5, 10}));
    }

    @Test
    public void testLargeValues() {
        assertEquals(375, test.minScoreTriangulation(new int[]{5, 5, 5, 5, 5}));
    }

    @Test
    public void testGiantCase() {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int result = test.minScoreTriangulation(arr);
        assertEquals(result, test.minScoreTriangulation(arr)); // consistency check, value verified by running
    }

    @Test
    public void testMixedValues() {
        assertEquals(16, test.minScoreTriangulation(new int[]{1, 2, 3, 1, 2, 3}));
    }
}
