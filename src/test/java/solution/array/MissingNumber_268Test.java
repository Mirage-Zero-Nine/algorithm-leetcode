package solution.array;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MissingNumber_268Test {
    private final MissingNumber_268 solution = new MissingNumber_268();

    @Test
    void testBasic() {
        assertEquals(2, solution.missingNumber(new int[]{3, 0, 1}));
    }

    @Test
    void testMissingLast() {
        assertEquals(8, solution.missingNumber(new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1}));
    }

    @Test
    void testMissingFirst() {
        assertEquals(0, solution.missingNumber(new int[]{1}));
    }

    @Test
    void testTwoElements() {
        assertEquals(2, solution.missingNumber(new int[]{0, 1}));
    }

    @Test
    void testSingleElement() {
        assertEquals(1, solution.missingNumber(new int[]{0}));
    }
}
