package solutions.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NumberOfSubarrays_1248Test {

    private final NumberOfSubarrays_1248 solution = new NumberOfSubarrays_1248();

    @Test
    public void testBasicCase() {
        assertEquals(2, solution.numberOfSubarrays(new int[]{1, 1, 2, 1, 1}, 3));
    }

    @Test
    public void testWithEvenPadding() {
        assertEquals(16, solution.numberOfSubarrays(new int[]{2, 2, 2, 1, 2, 2, 1, 2, 2, 2}, 2));
    }

    @Test
    public void testNoOddNumbers() {
        assertEquals(0, solution.numberOfSubarrays(new int[]{2, 4, 6}, 1));
    }

    @Test
    public void testAllOdd() {
        assertEquals(3, solution.numberOfSubarrays(new int[]{1, 1, 1, 1}, 2));
    }

    @Test
    public void testKLargerThanOddCount() {
        assertEquals(0, solution.numberOfSubarrays(new int[]{2, 2, 2, 1, 2, 2, 1, 2, 2, 2}, 3));
    }

    @Test
    public void testSingleOddElement() {
        assertEquals(1, solution.numberOfSubarrays(new int[]{1}, 1));
    }

    @Test
    public void testSingleEvenElement() {
        assertEquals(0, solution.numberOfSubarrays(new int[]{2}, 1));
    }

    @Test
    public void testKEqualsOne() {
        assertEquals(9, solution.numberOfSubarrays(new int[]{2, 2, 1, 2, 2}, 1));
    }

    @Test
    public void testConsecutiveOdds() {
        assertEquals(1, solution.numberOfSubarrays(new int[]{1, 1, 1}, 3));
    }

    @Test
    public void testEvensBetweenOdds() {
        // [1,2,2,1] has 2 odds, subarrays: [1,2,2,1], [2,2,1,2,1] - wait let me recalculate
        // nums = [2,1,2,2,1,2], k=2: subarrays with exactly 2 odds
        // odd at index 1 and 4. Left evens: 1 (index 0), right evens: 1 (index 5)
        // count = (1+1)*(1+1) = 4
        assertEquals(4, solution.numberOfSubarrays(new int[]{2, 1, 2, 2, 1, 2}, 2));
    }

    @Test
    public void testGiantCase() {
        int[] data = new int[100000];
        for (int i = 0; i < 100000; i++) {
            data[i] = i % 3 == 0 ? 1 : 2;
        }
        int result = solution.numberOfSubarrays(data, 5);
        // Just verify it runs without error and returns a positive number
        assert result > 0;
    }
}
