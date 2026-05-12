package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ThreeSumClosest16Test {

    private final ThreeSumClosest_16 test = new ThreeSumClosest_16();

    @Test
    public void testHappyCases() {
        assertEquals(2, test.threeSumClosest(new int[]{-1, 2, 1, -4}, 1));
        assertEquals(0, test.threeSumClosest(new int[]{0, 0, 0}, 1));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(0, test.threeSumClosest(new int[]{1, 1, -2}, 0));
    }

    @Test
    public void testLargeCase() {
        assertEquals(3, test.threeSumClosest(new int[]{-1, 0, 1, 2, -1, -4}, 3));
    }
}
