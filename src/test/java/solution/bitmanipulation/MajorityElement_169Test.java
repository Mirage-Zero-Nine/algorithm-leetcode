package solution.bitmanipulation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MajorityElement_169Test {
    private final MajorityElement_169 solver = new MajorityElement_169();

    @Test public void testBasic() {
        assertEquals(3, solver.majorityElement(new int[]{3, 2, 3}));
    }

    @Test public void testMoreElements() {
        assertEquals(2, solver.majorityElement(new int[]{2, 2, 1, 1, 1, 2, 2}));
    }

    @Test public void testSingle() {
        assertEquals(7, solver.majorityElement(new int[]{7}));
    }

    @Test public void testAllSame() {
        assertEquals(5, solver.majorityElement(new int[]{5, 5, 5, 5, 5}));
    }

    @Test public void testNegative() {
        assertEquals(-1, solver.majorityElement(new int[]{-1, -1, -1, 2, 3}));
    }
}
