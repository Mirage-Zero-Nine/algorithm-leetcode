package solutions.math;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CheckRecord_551Test {

    private final CheckRecord_551 test = new CheckRecord_551();

    @Test
    public void testHappyCases() {
        assertTrue(test.checkRecord("PPALLP"));
        assertFalse(test.checkRecord("PPALLL"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertTrue(test.checkRecord("P"));
        assertFalse(test.checkRecord("AA"));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.checkRecord("PPPPPPPPPP"));
        assertFalse(test.checkRecord("AALLPALLP"));
    }

    @Test
    public void testSingleA() {
        assertTrue(test.checkRecord("A"));
    }

    @Test
    public void testSingleL() {
        assertTrue(test.checkRecord("L"));
    }

    @Test
    public void testTwoConsecutiveL() {
        assertTrue(test.checkRecord("LL"));
    }

    @Test
    public void testThreeConsecutiveL() {
        assertFalse(test.checkRecord("LLL"));
    }

    @Test
    public void testTwoANotConsecutive() {
        assertFalse(test.checkRecord("APAP"));
    }

    @Test
    public void testLLSeparatedByOther() {
        assertTrue(test.checkRecord("LLPLL"));
    }

    @Test
    public void testGiantAllPresent() {
        String s = "P".repeat(10000);
        assertTrue(test.checkRecord(s));
    }
}
