package solution.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2025/05/22 11:54
 * Created with IntelliJ IDEA
 */

public class Search704Test {
    private final Search_704 test = new Search_704();

    @Test
    public void testTargetExistsInMiddle() {
        int[] nums = {-1, 0, 3, 5, 9, 12};
        int target = 9;
        assertEquals(4, test.search(nums, target));
    }

    @Test
    public void testTargetDoesNotExist() {
        int[] nums = {-1, 0, 3, 5, 9, 12};
        int target = 2;
        assertEquals(-1, test.search(nums, target));
    }

    @Test
    public void testTargetAtBeginning() {
        int[] nums = {1, 2, 3, 4, 5};
        int target = 1;
        assertEquals(0, test.search(nums, target));
    }

    @Test
    public void testTargetAtEnd() {
        int[] nums = {1, 2, 3, 4, 5};
        int target = 5;
        assertEquals(4, test.search(nums, target));
    }

    @Test
    public void testSingleElementFound() {
        int[] nums = {1};
        int target = 1;
        assertEquals(0, test.search(nums, target));
    }

    @Test
    public void testSingleElementNotFound() {
        int[] nums = {1};
        int target = 2;
        assertEquals(-1, test.search(nums, target));
    }

    @Test
    public void testEmptyArray() {
        int[] nums = {};
        int target = 3;
        assertEquals(-1, test.search(nums, target));
    }


    @Test
    public void testLargeArrayNoOverflow() {
        int size = 1_000_000;
        int[] nums = new int[size];
        for (int i = 0; i < size; i++) {
            nums[i] = i;
        }

        int target = 999_999;
        int result = test.search(nums, target);
        assertEquals(target, result);
    }
}
