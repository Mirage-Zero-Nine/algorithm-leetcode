package playground;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2022/06/18 11:20
 * Created with IntelliJ IDEA
 */

public class LengthOfLongestIncreasingSubarrayTest {
    private final LengthOfLongestIncreasingSubarray test = new LengthOfLongestIncreasingSubarray();

    @Test
    public void test() {
        int[] testArray = new int[]{5, 2, 4, 1, 8, 9, 7};
        Assertions.assertEquals(test.longestSubarray(testArray), 3);
    }

    @Test
    public void testEmpty() {
        Assertions.assertEquals(test.longestSubarray(new int[0]), 0);
    }
}