package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Trap42Test {

    private final Trap_42 test = new Trap_42();

    @Test
    public void testHappyCases() {
        assertEquals(6, test.trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
        assertEquals(9, test.trap(new int[]{4, 2, 0, 3, 2, 5}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.trap(new int[]{1, 2}));
        assertEquals(0, test.trap(new int[]{3, 2, 1}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(9, test.trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1, 0, 2}));
    }
}
