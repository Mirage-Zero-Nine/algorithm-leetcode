package solution.others;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class PlusOne66Test {

    private final PlusOne_66 test = new PlusOne_66();

    @Test
    public void testHappyCases() {
        assertArrayEquals(new int[]{1, 2, 4}, test.plusOne(new int[]{1, 2, 3}));
        assertArrayEquals(new int[]{4, 3, 2, 2}, test.plusOne(new int[]{4, 3, 2, 1}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertArrayEquals(new int[]{1, 0}, test.plusOne(new int[]{9}));
        assertArrayEquals(new int[]{1, 0, 0}, test.plusOne(new int[]{9, 9}));
    }

    @Test
    public void testLargeCase() {
        assertArrayEquals(new int[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            test.plusOne(new int[]{9, 9, 9, 9, 9, 9, 9, 9, 9}));
    }
}
