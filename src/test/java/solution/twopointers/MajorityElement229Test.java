package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

public class MajorityElement229Test {

    private final MajorityElement_229 test = new MajorityElement_229();

    @Test
    public void testHappyCases() {
        List<Integer> result = test.majorityElement(new int[]{3, 2, 3});
        assertTrue(result.contains(3));
        List<Integer> result2 = test.majorityElement(new int[]{1, 1, 1, 3, 3, 2, 2, 2});
        assertTrue(result2.contains(1) && result2.contains(2));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(List.of(), test.majorityElement(new int[]{}));
        List<Integer> result = test.majorityElement(new int[]{1});
        assertTrue(result.contains(1));
    }

    @Test
    public void testLargeCase() {
        List<Integer> result = test.majorityElement(new int[]{1, 1, 1, 1, 2, 2, 2, 2, 3});
        assertTrue(result.contains(1) && result.contains(2));
    }
}
