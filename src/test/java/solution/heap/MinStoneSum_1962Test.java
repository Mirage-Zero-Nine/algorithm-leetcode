package solution.heap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

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

    @Test
    public void testPileTwoKOne() {
        // [2] k=1 -> remove floor(2/2)=1, pile becomes 1
        assertEquals(1, test.minStoneSum(new int[]{2}, 1));
    }

    @Test
    public void testPileThreeKOne() {
        // [3] k=1 -> remove floor(3/2)=1, pile becomes 2
        assertEquals(2, test.minStoneSum(new int[]{3}, 1));
    }

    @Test
    public void testAllSameValue() {
        // [5,5,5,5] k=4 -> each pile halved once: 5-2=3 each -> total=12
        assertEquals(12, test.minStoneSum(new int[]{5, 5, 5, 5}, 4));
    }

    @Test
    public void testLargeKConvergence() {
        // With enough operations, all piles converge to 1 (since floor(1/2)=0, no further reduction)
        int[] piles = {100, 200, 300, 400, 500};
        int result = test.minStoneSum(piles, 1000);
        assertEquals(5, result);
    }

    @Test
    public void testLeetCodeExample() {
        // LeetCode example: [4,3,6,7] k=3 -> 12
        assertEquals(12, test.minStoneSum(new int[]{4, 3, 6, 7}, 3));
    }

    @Test
    public void testPropertyResultLeqSum() {
        // Property: result is always <= sum of original piles
        int[] piles = {7, 13, 21, 8, 15};
        int originalSum = Arrays.stream(piles).sum();
        for (int k = 0; k <= 20; k++) {
            int result = test.minStoneSum(piles.clone(), k);
            assertTrue(result <= originalSum, "result should be <= original sum for k=" + k);
        }
    }

    @Test
    public void testPropertyEachOpReducesByFloorHalfMax() {
        // Simulate step-by-step and verify total matches
        int[] piles = {10, 6, 4};
        int k = 5;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        int expectedTotal = 0;
        for (int p : piles) { expectedTotal += p; pq.add(p); }
        for (int i = 0; i < k && !pq.isEmpty(); i++) {
            int max = pq.poll();
            expectedTotal -= max / 2;
            pq.add(max - max / 2);
        }
        assertEquals(expectedTotal, test.minStoneSum(new int[]{10, 6, 4}, k));
    }

    @Test
    public void testLargeRandom1000Seed42() {
        // 1000 random piles, cross-check with PriorityQueue reference implementation
        Random rand = new Random(42L);
        int[] piles = new int[1000];
        for (int i = 0; i < 1000; i++) piles[i] = rand.nextInt(10000) + 1;
        int k = 5000;

        // Reference calculation
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        int expected = 0;
        for (int p : piles) { expected += p; pq.add(p); }
        for (int i = 0; i < k; i++) {
            int max = pq.poll();
            expected -= max / 2;
            pq.add(max - max / 2);
        }

        assertEquals(expected, test.minStoneSum(piles.clone(), k));
    }

    @Test
    public void testAllOnesLargeK() {
        // All piles are 1, floor(1/2)=0 so no reduction possible regardless of k
        int[] piles = new int[100];
        Arrays.fill(piles, 1);
        assertEquals(100, test.minStoneSum(piles, 500));
    }
}
