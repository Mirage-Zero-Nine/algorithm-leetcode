package solution.array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LargestNumber_179Test {
    private final LargestNumber_179 solution = new LargestNumber_179();

    @Test
    void testBasic() {
        assertEquals("210", solution.largestNumber(new int[]{10, 2}));
        assertEquals("9534330", solution.largestNumber(new int[]{3, 30, 34, 5, 9}));
    }

    @Test
    void testAllZeros() {
        assertEquals("0", solution.largestNumber(new int[]{0, 0, 0}));
    }

    @Test
    void testSingleElement() {
        assertEquals("1", solution.largestNumber(new int[]{1}));
    }

    @Test
    void testComparator() {
        assertEquals("9534330", solution.largestNumberComparator(new int[]{3, 30, 34, 5, 9}));
    }

    @Test
    void testWithZero() {
        assertEquals("88300", solution.largestNumber(new int[]{8, 30, 8, 0}));
    }
}
