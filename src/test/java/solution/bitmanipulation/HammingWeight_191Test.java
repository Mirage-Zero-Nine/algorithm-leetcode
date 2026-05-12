package solution.bitmanipulation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HammingWeight_191Test {
    private final HammingWeight_191 solver = new HammingWeight_191();

    @Test public void testBasic() {
        assertEquals(3, solver.hammingWeight(11));
    }

    @Test public void testZero() {
        assertEquals(0, solver.hammingWeight(0));
    }

    @Test public void testOne() {
        assertEquals(1, solver.hammingWeight(1));
    }

    @Test public void testPowerOfTwo() {
        assertEquals(1, solver.hammingWeight(128));
    }

    @Test public void testAllOnes() {
        assertEquals(5, solver.hammingWeight(0b11111));
    }
}
