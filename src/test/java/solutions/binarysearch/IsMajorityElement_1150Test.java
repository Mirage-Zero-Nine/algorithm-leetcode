package solutions.binarysearch;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IsMajorityElement_1150Test {

    private final IsMajorityElement_1150 test = new IsMajorityElement_1150();

    @Test
    public void testHappyCases() {
        assertTrue(test.isMajorityElement(new int[]{2, 4, 5, 5, 5, 5, 5, 6, 6}, 5));
        assertFalse(test.isMajorityElement(new int[]{10, 100, 101, 101}, 101));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertTrue(test.isMajorityElement(new int[]{1}, 1));
        assertFalse(test.isMajorityElement(new int[]{1, 2, 3}, 2));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.isMajorityElement(new int[]{1, 1, 1, 1, 1, 2, 3, 4, 5}, 1));
    }

    @Test
    public void testTargetNotPresent() {
        assertFalse(test.isMajorityElement(new int[]{1, 1, 2, 2, 3, 3}, 4));
    }

    @Test
    public void testExactlyHalfIsNotMajorityEvenLength() {
        assertFalse(test.isMajorityElement(new int[]{1, 1, 2, 2}, 1));
    }

    @Test
    public void testMajorityAtArrayStart() {
        assertTrue(test.isMajorityElement(new int[]{3, 3, 3, 3, 4, 5, 6}, 3));
    }

    @Test
    public void testMajorityAtArrayEnd() {
        assertTrue(test.isMajorityElement(new int[]{1, 2, 3, 4, 4, 4, 4}, 4));
    }

    @Test
    public void testSingleElementTargetAbsentScenarioByValue() {
        assertFalse(test.isMajorityElement(new int[]{9}, 8));
    }

    @Test
    public void testGiantCaseMajorityAndNonMajority() {
        int[] nums = new int[1000];
        for (int i = 0; i < 600; i++) {
            nums[i] = 7;
        }
        for (int i = 600; i < 1000; i++) {
            nums[i] = 8;
        }
        assertTrue(test.isMajorityElement(nums, 7));
        assertFalse(test.isMajorityElement(nums, 8));
    }

    @Test
    public void testNoMajorityOddLength() {
        assertFalse(test.isMajorityElement(new int[]{1, 2, 2, 3, 3}, 2));
    }
}
