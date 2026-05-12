package solution.bitmanipulation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReverseBits_190Test {
    private final ReverseBits_190 solver = new ReverseBits_190();

    @Test public void testBasic() {
        // 00000010100101000001111010011100 -> 00111001011110000010100101000000
        assertEquals(964176192, solver.reverseBits(43261596));
    }

    @Test public void testZero() {
        assertEquals(0, solver.reverseBits(0));
    }

    @Test public void testAllOnes() {
        // 0xFFFFFFFF reversed is 0xFFFFFFFF => -1
        assertEquals(-1, solver.reverseBits(-1));
    }

    @Test public void testOne() {
        // 0x00000001 -> 0x80000000
        assertEquals(0x80000000, solver.reverseBits(1));
    }

    @Test public void testPalindromeBits() {
        // 0x80000001 -> 0x80000001
        assertEquals(0x80000001, solver.reverseBits(0x80000001));
    }
}
