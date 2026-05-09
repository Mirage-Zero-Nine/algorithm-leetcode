package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class TwoSum167Test {

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
}
