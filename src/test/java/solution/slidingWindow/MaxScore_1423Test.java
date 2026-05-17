package solution.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MaxScore_1423Test {

    private final MaxScore_1423 solution = new MaxScore_1423();

    @Test
    public void testHappy_example1() {
        assertEquals(12, solution.maxScore(new int[]{1, 2, 3, 4, 5, 6, 1}, 3));
    }

    @Test
    public void testHappy_example2() {
        assertEquals(4, solution.maxScore(new int[]{2, 2, 2}, 2));
    }

    @Test
    public void testHappy_example3() {
        assertEquals(55, solution.maxScore(new int[]{9, 7, 7, 9, 7, 7, 9}, 7));
    }

    @Test
    public void testHappy_takeFromEnd() {
        assertEquals(15, solution.maxScore(new int[]{1, 1, 1, 1, 10, 5}, 2));
    }

    @Test
    public void testHappy_takeFromStart() {
        assertEquals(15, solution.maxScore(new int[]{10, 5, 1, 1, 1, 1}, 2));
    }

    @Test
    public void testNegative_allSameValues() {
        assertEquals(3, solution.maxScore(new int[]{1, 1, 1, 1, 1}, 3));
    }

    @Test
    public void testEdge_kEqualsLength() {
        assertEquals(10, solution.maxScore(new int[]{1, 2, 3, 4}, 4));
    }

    @Test
    public void testEdge_kEqualsOne() {
        assertEquals(5, solution.maxScore(new int[]{5, 1, 1, 1, 3}, 1));
    }

    @Test
    public void testEdge_singleElement() {
        assertEquals(7, solution.maxScore(new int[]{7}, 1));
    }

    @Test
    public void testEdge_twoElementsTakeOne() {
        assertEquals(10, solution.maxScore(new int[]{10, 3}, 1));
    }

    @Test
    public void testGiant() {
        int[] nums = new int[100000];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = 1;
        }
        nums[0] = 100;
        nums[nums.length - 1] = 100;
        // Take 2 cards: best is first and last = 200
        assertEquals(200, solution.maxScore(nums, 2));
    }
}
