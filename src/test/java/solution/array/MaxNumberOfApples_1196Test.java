package solution.array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MaxNumberOfApples_1196Test {
    private final MaxNumberOfApples_1196 solution = new MaxNumberOfApples_1196();

    @Test
    void testBasic() {
        assertEquals(4, solution.maxNumberOfApples(new int[]{100, 200, 150, 1000}));
    }

    @Test
    void testAllFit() {
        assertEquals(5, solution.maxNumberOfApples(new int[]{900, 950, 800, 1000, 700, 800}));
    }

    @Test
    void testSingleApple() {
        assertEquals(1, solution.maxNumberOfApples(new int[]{5000}));
    }

    @Test
    void testTooHeavy() {
        assertEquals(0, solution.maxNumberOfApples(new int[]{6000}));
    }

    @Test
    void testSmallApples() {
        assertEquals(10, solution.maxNumberOfApples(new int[]{100, 100, 100, 100, 100, 100, 100, 100, 100, 100}));
    }
}
