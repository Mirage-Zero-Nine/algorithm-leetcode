package solution.bitmanipulation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddBinary_67Test {
    private final AddBinary_67 solver = new AddBinary_67();

    @Test public void testBasic() {
        assertEquals("100", solver.addBinary("11", "1"));
    }

    @Test public void testLonger() {
        assertEquals("10101", solver.addBinary("1010", "1011"));
    }

    @Test public void testZeros() {
        assertEquals("0", solver.addBinary("0", "0"));
    }

    @Test public void testDifferentLengths() {
        assertEquals("10000", solver.addBinary("1111", "1"));
    }

    @Test public void testNoCarry() {
        assertEquals("110", solver.addBinary("100", "10"));
    }
}
