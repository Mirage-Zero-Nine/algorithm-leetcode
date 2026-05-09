package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MergeStones1000Test {

    private final MergeStones_1000 test = new MergeStones_1000();

    @Test
    public void testHappyCases() {
        assertEquals(20, test.mergeStones(new int[]{3, 2, 4, 1}, 2));
        assertEquals(25, test.mergeStones(new int[]{3, 5, 1, 2, 6}, 3));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(-1, test.mergeStones(new int[]{3, 2, 4, 1}, 3));
        assertEquals(0, test.mergeStones(new int[]{1}, 2));
    }

    @Test
    public void testLargeCase() {
        assertEquals(49, test.mergeStones(new int[]{1, 2, 3, 4, 5, 6, 7}, 3));
    }
}
