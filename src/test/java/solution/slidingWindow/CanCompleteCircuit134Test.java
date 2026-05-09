package solution.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CanCompleteCircuit134Test {

    private final CanCompleteCircuit_134 test = new CanCompleteCircuit_134();

    @Test
    public void testHappyCases() {
        assertEquals(3, test.canCompleteCircuit(new int[]{1, 2, 3, 4, 5}, new int[]{3, 4, 5, 1, 2}));
        assertEquals(-1, test.canCompleteCircuit(new int[]{2, 3, 4}, new int[]{3, 4, 3}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(-1, test.canCompleteCircuit(new int[]{3, 1, 1}, new int[]{1, 2, 3}));
        assertEquals(-1, test.canCompleteCircuit(null, null));
    }

    @Test
    public void testLargeCase() {
        assertEquals(4, test.canCompleteCircuit(new int[]{1, 2, 3, 4, 5}, new int[]{2, 3, 4, 5, 1}));
    }
}
