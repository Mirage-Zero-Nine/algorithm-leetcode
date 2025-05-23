package solution.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2025/05/22 14:07
 * Created with IntelliJ IDEA
 */

public class MaximumCount2529Test {

    private final MaximumCount_2529 test = new MaximumCount_2529();

    @Test
    public void test() {
        assertEquals(3, test.maximumCount(new int[]{-2, -1, -1, 1, 2, 3}));
        assertEquals(3, test.maximumCount(new int[]{-3, -2, -1, 0, 0, 1, 2}));
        assertEquals(4, test.maximumCount(new int[]{5, 20, 66, 1314}));
        assertEquals(4, test.maximumCount(new int[]{-52, -20, -6, -1}));
    }

    @Test
    public void testZero() {
        assertEquals(0, test.maximumCount(new int[]{}));
        assertEquals(0, test.maximumCount(new int[]{0}));
        assertEquals(0, test.maximumCount(new int[]{0, 0}));
        assertEquals(0, test.maximumCount(new int[]{0, 0, 0}));
        assertEquals(0, test.maximumCount(new int[]{0, 0, 0, 0}));
        assertEquals(0, test.maximumCount(new int[]{0, 0, 0, 0, 0}));
    }
}