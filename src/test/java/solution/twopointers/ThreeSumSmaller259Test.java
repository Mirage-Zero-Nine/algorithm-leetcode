package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ThreeSumSmaller259Test {

    private final ThreeSumSmaller_259 test = new ThreeSumSmaller_259();

    @Test
    public void testHappyCases() {
        assertEquals(2, test.threeSumSmaller(new int[]{-2, 0, 1, 3}, 2));
        assertEquals(3, test.threeSumSmaller(new int[]{-2, 0, 1, 3}, 4));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.threeSumSmaller(new int[]{1, 2}, 5));
        assertEquals(0, test.threeSumSmaller(new int[]{1, 2, 3}, 1));
    }

    @Test
    public void testLargeCase() {
        assertEquals(4, test.threeSumSmaller(new int[]{-1, 0, 1, 2, 3}, 3));
    }
}
