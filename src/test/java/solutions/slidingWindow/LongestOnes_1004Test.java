package solutions.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LongestOnes_1004Test {

    private final LongestOnes_1004 solution = new LongestOnes_1004();

    @Test
    public void testHappy_example1() {
        assertEquals(6, solution.longestOnes(new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 2));
    }

    @Test
    public void testHappy_example2() {
        assertEquals(10, solution.longestOnes(new int[]{0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1}, 3));
    }

    @Test
    public void testHappy_allOnesFlippable() {
        assertEquals(5, solution.longestOnes(new int[]{0, 0, 0, 0, 0}, 5));
    }

    @Test
    public void testHappy_kLargerThanZeros() {
        assertEquals(5, solution.longestOnes(new int[]{1, 0, 1, 0, 1}, 10));
    }

    @Test
    public void testNegative_kZeroNoOnes() {
        assertEquals(0, solution.longestOnes(new int[]{0, 0, 0}, 0));
    }

    @Test
    public void testEdge_singleOne() {
        assertEquals(1, solution.longestOnes(new int[]{1}, 0));
    }

    @Test
    public void testEdge_singleZeroKZero() {
        assertEquals(0, solution.longestOnes(new int[]{0}, 0));
    }

    @Test
    public void testEdge_singleZeroKOne() {
        assertEquals(1, solution.longestOnes(new int[]{0}, 1));
    }

    @Test
    public void testEdge_allOnes() {
        assertEquals(6, solution.longestOnes(new int[]{1, 1, 1, 1, 1, 1}, 2));
    }

    @Test
    public void testEdge_kZeroWithConsecutiveOnes() {
        assertEquals(3, solution.longestOnes(new int[]{0, 1, 1, 1, 0, 0}, 0));
    }

    @Test
    public void testGiant() {
        int[] nums = new int[100000];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = i % 3 == 0 ? 0 : 1; // pattern: 0,1,1,0,1,1,...
        }
        int result = solution.longestOnes(nums, 10000);
        // With 10000 flips available on a 100000 array with ~33333 zeros, window can be large
        assert result > 10000;
    }

    @org.junit.jupiter.params.ParameterizedTest
    @org.junit.jupiter.params.provider.CsvSource({"1,0,1", "0,0,0", "00111,1,4", "1010101,1,3", "110011,1,3", "000111,2,5", "100001,2,3", "111000111,2,5", "010101,3,6", "11111,0,5"})
    void additionalBoundaryCases(String values, int k, int expected) {
        int[] nums = values.chars().map(c -> c - '0').toArray();
        assertEquals(expected, solution.longestOnes(nums, k));
    }
}
