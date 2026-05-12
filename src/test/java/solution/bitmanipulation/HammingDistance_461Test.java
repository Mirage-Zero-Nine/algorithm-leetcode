package solution.bitmanipulation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HammingDistance_461Test {
    private final HammingDistance_461 solver = new HammingDistance_461();

    @Test public void testBasic() {
        assertEquals(2, solver.hammingDistance(1, 4));
    }

    @Test public void testSameNumber() {
        assertEquals(0, solver.hammingDistance(5, 5));
    }

    @Test public void testZero() {
        assertEquals(0, solver.hammingDistance(0, 0));
    }

    @Test public void testOneZero() {
        assertEquals(3, solver.hammingDistance(0, 7));
    }

    @Test public void testLarger() {
        assertEquals(4, solver.hammingDistance(0b1010, 0b0101));
    }
}
