package solutions.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Search_33Test {

    private final Search_33 test = new Search_33();

    @Test
    public void testHappyCases() {
        assertEquals(4, test.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
        assertEquals(0, test.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 4));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(-1, test.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 3));
        assertEquals(-1, test.search(new int[]{5}, 4));
    }

    @Test
    public void testExhaustiveSmallRotationsAndTargets() {
        for (int length = 1; length <= 8; length++) {
            int[] sorted = new int[length];
            for (int i = 0; i < length; i++) {
                sorted[i] = -10 + i * 2;
            }

            for (int pivot = 0; pivot < length; pivot++) {
                int[] rotated = new int[length];
                for (int i = 0; i < length; i++) {
                    rotated[i] = sorted[(pivot + i) % length];
                }

                for (int target : sorted) {
                    assertExpectedIndex(rotated, target);
                }
                for (int target : new int[]{-11, 6}) {
                    assertExpectedIndex(rotated, target);
                }
            }
        }
    }

    private void assertExpectedIndex(int[] nums, int target) {
        int expected = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                expected = i;
                break;
            }
        }
        assertEquals(expected, test.search(nums, target));
    }

    @Test
    public void testLargeCase() {
        assertEquals(5, test.search(new int[]{6, 7, 8, 9, 10, 1, 2, 3, 4, 5}, 1));
    }

    @Test
    public void testNotRotatedArray() {
        assertEquals(3, test.search(new int[]{1, 2, 3, 4, 5}, 4));
    }

    @Test
    public void testTwoElementRotatedArray() {
        assertEquals(1, test.search(new int[]{2, 1}, 1));
    }

    @Test
    public void testSingleElementFound() {
        assertEquals(0, test.search(new int[]{9}, 9));
    }

    @Test
    public void testSingleElementNotFound() {
        assertEquals(-1, test.search(new int[]{9}, 8));
    }

    @Test
    public void testTargetAtEnd() {
        assertEquals(6, test.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 2));
    }

    @Test
    public void testTargetAtPivot() {
        assertEquals(4, test.search(new int[]{30, 40, 50, 60, 10, 20}, 10));
    }

    @Test
    public void testGiantCase() {
        int n = 2000;
        int[] nums = new int[n];
        int pivot = 777;
        for (int i = 0; i < n; i++) {
            nums[i] = (i + pivot) % n;
        }
        assertEquals(123, test.search(nums, (123 + pivot) % n));
    }
}
