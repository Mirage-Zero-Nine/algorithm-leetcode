package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

public class ThreeSum15Test {

    private final ThreeSum_15 test = new ThreeSum_15();

    @Test
    public void testHappyCases() {
        List<List<Integer>> result = test.threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        assertEquals(2, result.size());
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.threeSum(new int[]{}).size());
        assertEquals(0, test.threeSum(new int[]{0}).size());
        assertEquals(1, test.threeSum(new int[]{0, 0, 0}).size());
    }

    @Test
    public void testLargeCase() {
        List<List<Integer>> result = test.threeSum(new int[]{-4, -2, -2, -2, 0, 1, 2, 2, 2, 3, 3, 4, 4, 6, 6});
        assertTrue(result.size() > 0);
    }
}
