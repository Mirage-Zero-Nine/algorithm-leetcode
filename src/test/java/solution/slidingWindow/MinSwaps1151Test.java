package solution.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MinSwaps1151Test {

    private final MinSwaps_1151 test = new MinSwaps_1151();

    @Test
    public void testHappyCases() {
        assertEquals(1, test.minSwaps(new int[]{1, 0, 1, 0, 1}));
        assertEquals(3, test.minSwaps(new int[]{1, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.minSwaps(new int[]{1}));
        assertEquals(0, test.minSwaps(new int[]{1, 1}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(2, test.minSwaps(new int[]{1, 0, 0, 1, 0, 0, 1, 0, 0, 1}));
    }
}
