package solutions.hashmap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for {@link GetHint_299}.
 */
public class GetHint_299Test {

    private final GetHint_299 solver = new GetHint_299();

    @Test
    public void testLeetCodeExample() {
        // secret = "1807", guess = "7810" → "1A3B"
        assertEquals("1A3B", solver.getHint("1807", "7810"));
    }

    @Test
    public void testAllBulls() {
        assertEquals("4A0B", solver.getHint("1123", "1123"));
    }

    @Test
    public void testAllCows() {
        assertEquals("0A4B", solver.getHint("1234", "4321"));
    }

    @Test
    public void testNoMatch() {
        assertEquals("0A0B", solver.getHint("1234", "5678"));
    }

    @Test
    public void testDuplicateDigitsSecret() {
        // secret = "1122", guess = "1222" → "1A3B"
        // pos 0: 1==1 → bull
        // pos 1: 1!=2, tmp[1] becomes -1, tmp[2] becomes -1
        // pos 2: 2!=2 → wait, 2==2 → bull
        // Actually let me recalculate:
        // i=0: s=1,g=1 → a=1
        // i=1: s=1,g=2 → tmp[1]++, tmp[2]-- → tmp=[0,1,-1,0,0,0,0,0,0,0]
        // i=2: s=2,g=2 → a=2
        // i=3: s=2,g=2 → a=3
        // Result: "3A0B"
        assertEquals("3A0B", solver.getHint("1122", "1222"));
    }

    @Test
    public void testDuplicateDigitsGuess() {
        // secret = "1123", guess = "1123" → "4A0B"
        assertEquals("4A0B", solver.getHint("1123", "1123"));
    }

    @Test
    public void testPartialMatch() {
        // secret = "1", guess = "0" → "0A0B"
        assertEquals("0A0B", solver.getHint("1", "0"));
    }

    @Test
    public void testPartialMatch2() {
        // secret = "1", guess = "1" → "1A0B"
        assertEquals("1A0B", solver.getHint("1", "1"));
    }

    @Test
    public void testComplexDuplicates() {
        // secret = "1807", guess = "7810" → "1A3B"
        assertEquals("1A3B", solver.getHint("1807", "7810"));
    }

    @Test
    public void testAllSameDigits() {
        // secret = "1111", guess = "1111" → "4A0B"
        assertEquals("4A0B", solver.getHint("1111", "1111"));
    }

    @Test
    public void testAllSameDifferentGuess() {
        // secret = "1111", guess = "2222" → "0A0B"
        assertEquals("0A0B", solver.getHint("1111", "2222"));
    }

    @Test
    public void testMixedBullsAndCowsWithDuplicates() {
        // secret = "1122", guess = "2211"
        // i=0: s=1,g=2 → tmp[1]++, tmp[2]-- → tmp=[0,1,-1,0,...]
        // i=1: s=1,g=2 → tmp[1]++, tmp[2]-- → tmp=[0,2,-2,0,...]; tmp[2]<0 → b++
        // i=2: s=2,g=1 → tmp[2]++, tmp[1]-- → tmp=[0,1,-1,0,...]; tmp[1]>0 → b++
        // i=3: s=2,g=1 → tmp[2]++, tmp[1]-- → tmp=[0,0,0,0,...]; tmp[1]>0 → b++
        // Result: "0A4B"
        assertEquals("0A4B", solver.getHint("1122", "2211"));
    }
    @Test void extra01() { assertEquals("1A0B", solver.getHint("1", "1")); }
    @Test void extra02() { assertEquals("0A0B", solver.getHint("1", "0")); }
    @Test void extra03() { assertEquals("0A4B", solver.getHint("1234", "4321")); }
    @Test void extra04() { assertEquals("0A4B", solver.getHint("1122", "2211")); }
    @Test void extra05() { assertEquals("1A1B", solver.getHint("1123", "0111")); }
    @Test void extra06() { assertEquals("0A0B", solver.getHint("0000", "1111")); }
    @Test void extra07() { assertEquals("4A0B", solver.getHint("0000", "0000")); }
    @Test void extra08() { assertEquals("0A4B", solver.getHint("1122", "2211")); }
    @Test void extra09() { assertEquals("1A3B", solver.getHint("1807", "7810")); }
    @Test void extra10() { assertEquals("1A2B", solver.getHint("1123", "1211")); }
}
