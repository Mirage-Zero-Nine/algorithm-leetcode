package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MakeArrayIncreasing1187Test {

    private final MakeArrayIncreasing_1187 test = new MakeArrayIncreasing_1187();

    @Test
    public void testHappyCases() {
        assertEquals(1, test.makeArrayIncreasing(new int[]{1, 5, 3, 6, 7}, new int[]{1, 3, 2, 4}));
        assertEquals(2, test.makeArrayIncreasing(new int[]{1, 5, 3, 6, 7}, new int[]{4, 3, 1}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(-1, test.makeArrayIncreasing(new int[]{1, 5, 3, 6, 7}, new int[]{1, 6, 3, 3}));
        assertEquals(0, test.makeArrayIncreasing(new int[]{1}, new int[]{2}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(0, test.makeArrayIncreasing(new int[]{1, 2, 3, 4, 5}, new int[]{10, 20, 30}));
    }
}
