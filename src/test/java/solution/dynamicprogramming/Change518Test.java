package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Change518Test {

    private final Change_518 test = new Change_518();

    @Test
    public void testHappyCases() {
        assertEquals(4, test.change(5, new int[]{1, 2, 5}));
        assertEquals(0, test.change(3, new int[]{2}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(1, test.change(0, new int[]{1, 2, 3}));
        assertEquals(1, test.change(1, new int[]{1}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(10, test.change(10, new int[]{1, 2, 5}));
    }
}
