package solutions.bitmanipulation;

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

    @Test public void testBothOnes() {
        assertEquals("10", solver.addBinary("1", "1"));
    }

    @Test public void testOneZero() {
        assertEquals("1", solver.addBinary("1", "0"));
    }

    @Test public void testZeroOne() {
        assertEquals("1", solver.addBinary("0", "1"));
    }

    @Test public void testLongCarryChain() {
        assertEquals("100000000", solver.addBinary("11111111", "1"));
    }

    @Test public void testEqualLengthNoCarry() {
        assertEquals("1111", solver.addBinary("1010", "0101"));
    }

    @Test public void testGiantCase() {
        // 64-bit binary strings
        String a = "1".repeat(64);
        String b = "1";
        String result = solver.addBinary(a, b);
        // 64 ones + 1 = 1 followed by 64 zeros
        assertEquals("1" + "0".repeat(64), result);
    }
}
