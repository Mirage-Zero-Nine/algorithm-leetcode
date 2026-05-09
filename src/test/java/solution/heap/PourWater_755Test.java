package solution.heap;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class PourWater_755Test {

    private final PourWater_755 test = new PourWater_755();

    @Test
    public void testHappyCases() {
        assertArrayEquals(new int[]{2, 2, 2, 3, 2, 2, 2}, test.pourWater(new int[]{2, 1, 1, 2, 1, 2, 2}, 4, 3));
        assertArrayEquals(new int[]{2, 3, 3, 4}, test.pourWater(new int[]{1, 2, 3, 4}, 2, 2));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertArrayEquals(new int[]{2, 2, 2}, test.pourWater(new int[]{2, 1, 2}, 1, 1));
        assertArrayEquals(new int[]{5}, test.pourWater(new int[]{2}, 3, 0));
    }

    @Test
    public void testLargeCase() {
        assertArrayEquals(new int[]{3, 3, 3, 3, 3, 3, 2}, test.pourWater(new int[]{1, 2, 3, 2, 1, 2, 2}, 7, 3));
    }
}
