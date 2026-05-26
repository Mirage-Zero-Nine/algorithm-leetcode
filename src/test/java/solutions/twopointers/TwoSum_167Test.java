package solutions.twopointers;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class TwoSum_167Test {

    private final TwoSum_167 test = new TwoSum_167();

    @Test
    public void testHappyCases() {
        assertArrayEquals(new int[]{1, 2}, test.twoSum(new int[]{2, 7, 11, 15}, 9));
        assertArrayEquals(new int[]{1, 3}, test.twoSum(new int[]{2, 3, 4}, 6));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.twoSum(new int[]{1, 2, 3}, 10));
        assertArrayEquals(new int[]{1, 2}, test.twoSum(new int[]{-1, 0}, -1));
    }

    @Test
    public void testLargeCase() {
        assertArrayEquals(new int[]{1, 10}, test.twoSum(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 11));
    }

    @Test
    public void testAdditionalHappyCases() {
        assertArrayEquals(new int[]{2, 5}, test.twoSum(new int[]{1, 2, 3, 4, 4, 9}, 6));
        assertArrayEquals(new int[]{1, 4}, test.twoSum(new int[]{-3, -1, 0, 3, 5}, 0));
        assertArrayEquals(new int[]{3, 4}, test.twoSum(new int[]{1, 2, 50, 60}, 110));
    }

    @Test
    public void testAdditionalEdgeCases() {
        assertArrayEquals(new int[]{1, 2}, test.twoSum(new int[]{0, 0}, 0));
        assertNull(test.twoSum(new int[]{5}, 5));
        assertArrayEquals(new int[]{1, 2}, test.twoSum(new int[]{1, 2, 4, 8}, 3));
    }

    @Test
    public void testAdditionalGiantCase() {
        int[] numbers = new int[200];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i + 1;
        }
        assertArrayEquals(new int[]{100, 200}, test.twoSum(numbers, 300));
    }

    @Test
    public void testNegativeNumbers() {
        assertArrayEquals(new int[]{1, 5}, test.twoSum(new int[]{-10, -5, -3, 0, 7}, -3));
    }

    @Test
    public void testNoSolution() {
        assertNull(test.twoSum(new int[]{1, 3, 5, 7}, 2));
    }

    @Test
    public void testLargeValues() {
        assertArrayEquals(new int[]{1, 2}, test.twoSum(new int[]{Integer.MIN_VALUE / 2, Integer.MAX_VALUE / 2}, -1));
    }

    @Test
    public void testGiantSparse() {
        int[] numbers = new int[1000];
        for (int i = 0; i < 1000; i++) {
            numbers[i] = i * 2;
        }
        // first=0, last=1998, target=1998 -> indices 1 and 1000
        assertArrayEquals(new int[]{1, 1000}, test.twoSum(numbers, 1998));
    }
}
