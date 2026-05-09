package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CanCross403Test {

    private final CanCross_403 test = new CanCross_403();

    @Test
    public void testHappyCases() {
        assertTrue(test.canCross(new int[]{0, 1, 3, 5, 6, 8, 12, 17}));
        assertFalse(test.canCross(new int[]{0, 1, 2, 3, 4, 8, 9, 11}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertTrue(test.canCross(new int[]{}));
        assertFalse(test.canCross(new int[]{0, 2}));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.canCross(new int[]{0, 1, 3, 6, 10, 15, 21}));
    }
}
