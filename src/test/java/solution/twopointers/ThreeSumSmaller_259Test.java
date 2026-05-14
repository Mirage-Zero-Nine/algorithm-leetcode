package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ThreeSumSmaller_259Test {

    private final ThreeSumSmaller_259 test = new ThreeSumSmaller_259();

    @Test
    public void testHappyCases() {
        assertEquals(2, test.threeSumSmaller(new int[]{-2, 0, 1, 3}, 2));
        assertEquals(3, test.threeSumSmaller(new int[]{-2, 0, 1, 3}, 4));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.threeSumSmaller(new int[]{1, 2}, 5));
        assertEquals(0, test.threeSumSmaller(new int[]{1, 2, 3}, 1));
    }

    @Test
    public void testLargeCase() {
        assertEquals(4, test.threeSumSmaller(new int[]{-1, 0, 1, 2, 3}, 3));
    }

    @Test
    public void testAdditionalHappyCases() {
        assertEquals(1, test.threeSumSmaller(new int[]{0, 0, 0}, 1));
        assertEquals(3, test.threeSumSmaller(new int[]{3, 1, 0, -2}, 4));
        assertEquals(8, test.threeSumSmaller(new int[]{-2, -1, 0, 1, 2}, 2));
    }

    @Test
    public void testAdditionalEdgeCases() {
        assertEquals(0, test.threeSumSmaller(new int[]{}, 0));
        assertEquals(0, test.threeSumSmaller(new int[]{1}, 2));
        assertEquals(0, test.threeSumSmaller(new int[]{5, 5, 5}, 15));
    }

    @Test
    public void testAdditionalGiantCase() {
        assertEquals(77, test.threeSumSmaller(new int[]{-5, -4, -3, -2, -1, 0, 1, 2, 3}, 3));
    }

    @Test
    public void testAllNegatives() {
        assertEquals(10, test.threeSumSmaller(new int[]{-5, -4, -3, -2, -1}, 0));
    }

    @Test
    public void testAllZeros() {
        assertEquals(1, test.threeSumSmaller(new int[]{0, 0, 0}, 1));
        assertEquals(0, test.threeSumSmaller(new int[]{0, 0, 0}, 0));
    }

    @Test
    public void testLargeTarget() {
        assertEquals(10, test.threeSumSmaller(new int[]{1, 2, 3, 4, 5}, 100));
    }

    @Test
    public void testGiantArray() {
        int[] nums = new int[100];
        for (int i = 0; i < 100; i++) {
            nums[i] = i;
        }
        // all triplets sum < 300 (max sum is 97+98+99=294 < 300)
        assertEquals(161700, test.threeSumSmaller(nums, 300));
    }
}
