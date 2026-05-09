package solution.stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class NextGreaterElement_496Test {
    private final NextGreaterElement_496 solver = new NextGreaterElement_496();

    @Test public void testExample1() {
        assertArrayEquals(new int[]{-1, 3, -1}, solver.nextGreaterElement(new int[]{4, 1, 2}, new int[]{1, 3, 4, 2}));
    }

    @Test public void testExample2() {
        assertArrayEquals(new int[]{3, -1}, solver.nextGreaterElement(new int[]{2, 4}, new int[]{1, 2, 3, 4}));
    }

    @Test public void testSingleElement() {
        assertArrayEquals(new int[]{-1}, solver.nextGreaterElement(new int[]{1}, new int[]{1}));
    }

    @Test public void testAllHaveGreater() {
        assertArrayEquals(new int[]{2, 3, 4}, solver.nextGreaterElement(new int[]{1, 2, 3}, new int[]{1, 2, 3, 4}));
    }

    @Test public void testDecreasing() {
        assertArrayEquals(new int[]{-1, -1}, solver.nextGreaterElement(new int[]{4, 3}, new int[]{4, 3, 2, 1}));
    }
}
