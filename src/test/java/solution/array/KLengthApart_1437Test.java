package solution.array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KLengthApart_1437Test {
    private final KLengthApart_1437 solution = new KLengthApart_1437();

    @Test
    void testBasicValid() {
        assertTrue(solution.kLengthApart(new int[]{1, 0, 0, 0, 1, 0, 0, 1}, 2));
    }

    @Test
    void testBasicInvalid() {
        assertFalse(solution.kLengthApart(new int[]{1, 0, 0, 1, 0, 1}, 2));
    }

    @Test
    void testSingleOne() {
        assertTrue(solution.kLengthApart(new int[]{1, 0, 0, 0}, 3));
    }

    @Test
    void testNoOnes() {
        assertTrue(solution.kLengthApart(new int[]{0, 0, 0, 0}, 1));
    }

    @Test
    void testExactDistance() {
        assertTrue(solution.kLengthApart(new int[]{1, 0, 1}, 1));
    }
}
