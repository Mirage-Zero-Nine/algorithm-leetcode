package solution.heap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class MinStoneSum_1962Test {

    private final MinStoneSum_1962 test = new MinStoneSum_1962();

    @Test
    public void testHappyCases() {
        assertEquals(12, test.minStoneSum(new int[]{5, 4, 9}, 2));
        assertEquals(12, test.minStoneSum(new int[]{4, 3, 6, 7}, 3));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(1, test.minStoneSum(new int[]{1}, 5));
        assertEquals(0, test.minStoneSum(new int[]{0, 0}, 3));
    }

    @Test
    public void testLargeCase() {
        assertEquals(22, test.minStoneSum(new int[]{10, 10, 10, 10, 10, 10}, 10));
    }

    @Test
    public void testSinglePileManyOps() {
        assertEquals(1, test.minStoneSum(new int[]{100}, 100));
    }

    @Test
    public void testKZero() {
        assertEquals(15, test.minStoneSum(new int[]{5, 4, 6}, 0));
    }

    @Test
    public void testAllOnes() {
        assertEquals(5, test.minStoneSum(new int[]{1, 1, 1, 1, 1}, 10));
    }

    @Test
    public void testSinglePileSingleOp() {
        assertEquals(5, test.minStoneSum(new int[]{10}, 1));
    }

    @Test
    public void testTwoPiles() {
        // [5, 3] -> remove floor(5/2)=2 -> [3, 3] -> remove floor(3/2)=1 -> [2, 3] -> total = 5
        assertEquals(5, test.minStoneSum(new int[]{5, 3}, 2));
    }

    @Test
    public void testLargePile() {
        assertEquals(1, test.minStoneSum(new int[]{1024}, 20));
    }

    @Test
    public void testGiantCase() {
        int[] piles = new int[10000];
        for (int i = 0; i < 10000; i++) piles[i] = 10000;
        int result = test.minStoneSum(piles, 100000);
        assertTrue(result > 0);
    }

    @Test
    public void testMixedPiles() {
        assertEquals(3, test.minStoneSum(new int[]{1, 2, 3}, 3));
    }
}
