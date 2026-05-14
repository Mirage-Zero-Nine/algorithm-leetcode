package solution.map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author BorisMirage
 * Time: 2022/06/18 11:09
 * Created with IntelliJ IDEA
 */

public class TwoSum_1Test {

    private final TwoSum_1 test = new TwoSum_1();

    @Test
    public void testTwoSum() {
        int[] testArray = {3, 5, 6, 8, 7};
        assertArrayEquals(new int[]{0, 1}, Arrays.stream(test.twoSum(testArray, 8)).sorted().toArray());
    }

    @Test
    public void testHappyCases() {
        assertPairEquals(new int[]{0, 1}, test.twoSum(new int[]{2, 7, 11, 15}, 9));
        assertPairEquals(new int[]{1, 2}, test.twoSum(new int[]{3, 2, 4}, 6));
        assertPairEquals(new int[]{0, 1}, test.twoSum(new int[]{3, 3}, 6));
        assertPairEquals(new int[]{2, 4}, test.twoSum(new int[]{5, 75, 25, 10, 100}, 125));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.twoSum(new int[]{1, 2, 3}, 100));
        assertPairEquals(new int[]{0, 2}, test.twoSum(new int[]{-3, 4, 3, 90}, 0));
        assertPairEquals(new int[]{0, 3}, test.twoSum(new int[]{0, 4, 3, 0}, 0));
        assertPairEquals(new int[]{1, 2}, test.twoSum(new int[]{Integer.MAX_VALUE, -2, 1, Integer.MIN_VALUE}, -1));
    }

    @Test
    public void testGiantCase() {
        int[] nums = new int[1000];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = -1_000_000;
        }
        nums[123] = 111_111;
        nums[987] = 222_222;
        assertPairEquals(new int[]{123, 987}, test.twoSum(nums, 333_333));
    }

    @Test
    public void testNegativeNumbers() {
        assertPairEquals(new int[]{0, 1}, test.twoSum(new int[]{-5, -3, 4}, -8));
    }

    @Test
    public void testZeroTarget() {
        assertPairEquals(new int[]{0, 1}, test.twoSum(new int[]{0, 0, 5}, 0));
    }

    @Test
    public void testLargeNumbers() {
        assertPairEquals(new int[]{0, 1}, test.twoSum(new int[]{Integer.MAX_VALUE, 0}, Integer.MAX_VALUE));
    }

    @Test
    public void testFirstAndLast() {
        assertPairEquals(new int[]{0, 4}, test.twoSum(new int[]{1, 5, 6, 7, 9}, 10));
    }

    @Test
    public void testAdjacentElements() {
        assertPairEquals(new int[]{2, 3}, test.twoSum(new int[]{10, 20, 30, 40}, 70));
    }

    @Test
    public void testSinglePairAvailable() {
        assertPairEquals(new int[]{0, 1}, test.twoSum(new int[]{1, 2}, 3));
    }

    private void assertPairEquals(int[] expected, int[] actual) {
        assertArrayEquals(expected, Arrays.stream(actual).sorted().toArray());
    }
}
