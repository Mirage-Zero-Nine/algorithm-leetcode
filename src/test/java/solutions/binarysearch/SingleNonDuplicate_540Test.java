package solutions.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SingleNonDuplicate_540Test {

    private final SingleNonDuplicate_540 test = new SingleNonDuplicate_540();

    @Test
    public void testHappyCases() {
        assertEquals(2, test.singleNonDuplicate(new int[]{1, 1, 2, 3, 3, 4, 4, 8, 8}));
        assertEquals(10, test.singleNonDuplicate(new int[]{3, 3, 7, 7, 10, 11, 11}));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.singleNonDuplicate(new int[]{1}));
        assertEquals(1, test.singleNonDuplicate(new int[]{1, 2, 2}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(5, test.singleNonDuplicate(new int[]{1, 1, 2, 2, 3, 3, 4, 4, 5, 6, 6, 7, 7}));
    }

    @Test
    public void testSingleAtEnd() {
        assertEquals(9, test.singleNonDuplicate(new int[]{1, 1, 2, 2, 9}));
    }

    @Test
    public void testSingleAtBeginning() {
        assertEquals(-3, test.singleNonDuplicate(new int[]{-3, -2, -2, -1, -1}));
    }

    @Test
    public void testSingleInMiddle() {
        assertEquals(4, test.singleNonDuplicate(new int[]{0, 0, 1, 1, 4, 5, 5, 6, 6}));
    }

    @Test
    public void testAllNegativeValues() {
        assertEquals(-5, test.singleNonDuplicate(new int[]{-9, -9, -8, -8, -5, -4, -4}));
    }

    @Test
    public void testShortOddLength() {
        assertEquals(3, test.singleNonDuplicate(new int[]{2, 2, 3}));
    }

    @Test
    public void testAnotherBalancedCase() {
        assertEquals(100, test.singleNonDuplicate(new int[]{10, 10, 20, 20, 30, 30, 100, 200, 200}));
    }

    @Test
    public void testGiantCase() {
        int[] nums = new int[1999];
        int idx = 0;
        for (int i = 1; i <= 1000; i++) {
            if (i == 777) {
                nums[idx++] = 777;
            } else {
                nums[idx++] = i;
                nums[idx++] = i;
            }
        }
        assertEquals(777, test.singleNonDuplicate(nums));
    }
}
