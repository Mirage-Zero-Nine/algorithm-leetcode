package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CombinationSum4377Test {

    private final CombinationSum4_377 test = new CombinationSum4_377();

    @Test
    public void testHappyCases() {
        assertEquals(7, test.combinationSum4(new int[]{1, 2, 3}, 4));
        assertEquals(0, test.combinationSum4(new int[]{9}, 3));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(1, test.combinationSum4(new int[]{1, 2, 3}, 0));
        assertEquals(1, test.combinationSum4(new int[]{1}, 1));
    }

    @Test
    public void testLargeCase() {
        assertEquals(274, test.combinationSum4(new int[]{1, 2, 3}, 10));
    }
}
