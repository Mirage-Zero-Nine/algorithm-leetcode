package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

public class FourSum18Test {

    private final FourSum_18 test = new FourSum_18();

    @Test
    public void testHappyCases() {
        List<List<Integer>> result = test.fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0);
        assertEquals(3, result.size());
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.fourSum(new int[]{1, 2, 3}, 6).size());
        assertEquals(1, test.fourSum(new int[]{0, 0, 0, 0}, 0).size());
    }

    @Test
    public void testLargeCase() {
        List<List<Integer>> result = test.fourSum(new int[]{-3, -2, -1, 0, 0, 1, 2, 3}, 0);
        assertTrue(result.size() > 0);
    }
}
