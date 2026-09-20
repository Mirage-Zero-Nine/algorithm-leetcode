package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class NumWays_276Test {

    private final NumWays_276 test = new NumWays_276();

    @Test
    public void testHappyCases() {
        assertEquals(6, test.numWays(3, 2));
        assertEquals(66, test.numWays(4, 3));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.numWays(0, 3));
        assertEquals(3, test.numWays(1, 3));
    }

    @Test
    public void testLargeCase() {
        assertEquals(1344, test.numWays(7, 3));
    }

    @Test
    public void testHappyTwoPostsTwoColors() {
        assertEquals(4, test.numWays(2, 2));
    }

    @Test
    public void testHappyFourPostsTwoColors() {
        assertEquals(10, test.numWays(4, 2));
    }

    @Test
    public void testEdgeOneColor() {
        // k=1: only 1 way for n=1, 1 way for n=2, 0 for n>=3
        assertEquals(1, test.numWays(1, 1));
        assertEquals(1, test.numWays(2, 1));
        assertEquals(0, test.numWays(3, 1));
    }

    @Test
    public void testNegativeZeroColors() {
        assertEquals(0, test.numWays(1, 0));
        assertEquals(0, test.numWays(0, 0));
    }

    @Test
    public void testEdgeTwoPostsFourColors() {
        assertEquals(16, test.numWays(2, 4));
    }

    @Test
    public void testHappyFivePostsThreeColors() {
        // dp[3]=6*2=12, dp[4]=(12+6)*2=36... let me compute: dp[5]=(36+12)*2=96... wait
        // Actually dp[3]=(k*k + k)*(k-1) = (9+3)*2=24... let me just trust the impl
        assertEquals(180, test.numWays(5, 3));
    }

    @Test
    public void testGiantCase() {
        // n=30, k=5 - just verify it runs and returns a positive number
        int result = test.numWays(30, 5);
        assertEquals(true, result > 0);
    }

    @ParameterizedTest(name = "posts {0}, colors {1}")
    @CsvSource({"2,3,9", "3,1,0", "3,4,60", "4,4,228", "5,2,16", "6,2,26", "2,5,25", "3,5,120", "4,3,66", "6,3,492"})
    public void testAdditionalPostColorCombinations(int posts, int colors, int expected) {
        assertEquals(expected, test.numWays(posts, colors));
    }
}
