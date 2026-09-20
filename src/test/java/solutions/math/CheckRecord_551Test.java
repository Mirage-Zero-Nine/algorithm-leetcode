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

    @Test
    public void testAdditional1() {
        assertTrue(test.checkRecord(""));
    }

    @Test
    public void testAdditional2() {
        assertTrue(test.checkRecord("LLP"));
    }

    @Test
    public void testAdditional3() {
        assertFalse(test.checkRecord("LLLP"));
    }

    @Test
    public void testAdditional4() {
        assertTrue(test.checkRecord("APLP"));
    }

    @Test
    public void testAdditional5() {
        assertFalse(test.checkRecord("AAL"));
    }

    @Test
    public void testAdditional6() {
        assertTrue(test.checkRecord("PLPL"));
    }

    @Test
    public void testAdditional7() {
        assertTrue(test.checkRecord("PPLLA"));
    }

    @Test
    public void testAdditional8() {
        assertFalse(test.checkRecord("LALAL"));
    }

    @Test
    public void testAdditional9() {
        assertFalse(test.checkRecord("PPALLLL"));
    }

    @Test
    public void testAdditional10() {
        assertFalse(test.checkRecord("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAP"));
    }
}
