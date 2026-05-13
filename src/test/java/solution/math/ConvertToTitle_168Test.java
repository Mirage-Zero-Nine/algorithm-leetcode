package solution.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ConvertToTitle_168Test {

    private final ConvertToTitle_168 test = new ConvertToTitle_168();

    @Test
    public void testHappyCases() {
        assertEquals("A", test.convertToTitle(1));
        assertEquals("Z", test.convertToTitle(26));
        assertEquals("AA", test.convertToTitle(27));
    }

    @Test
    public void testEdgeCases() {
        assertEquals("", test.convertToTitle(0));
        assertEquals("AZ", test.convertToTitle(52));
    }

    @Test
    public void testLargeCase() {
        assertEquals("ZZZ", test.convertToTitle(18278));
    }

    @Test
    public void testSingleLetters() {
        assertEquals("B", test.convertToTitle(2));
        assertEquals("C", test.convertToTitle(3));
        assertEquals("Y", test.convertToTitle(25));
    }

    @Test
    public void testTwoLetters() {
        assertEquals("AB", test.convertToTitle(28));
        assertEquals("BA", test.convertToTitle(53));
        assertEquals("ZY", test.convertToTitle(701));
    }

    @Test
    public void testThreeLetters() {
        assertEquals("AAA", test.convertToTitle(703));
        assertEquals("AAB", test.convertToTitle(704));
    }

    @Test
    public void testColumnAZ() {
        assertEquals("AZ", test.convertToTitle(52));
    }

    @Test
    public void testNegativeCase() {
        // 0 is the only non-positive handled, returns empty
        assertEquals("", test.convertToTitle(0));
    }

    @Test
    public void testBoundaryBetweenOneAndTwoLetters() {
        assertEquals("Z", test.convertToTitle(26));
        assertEquals("AA", test.convertToTitle(27));
    }

    @Test
    public void testBoundaryBetweenTwoAndThreeLetters() {
        assertEquals("ZZ", test.convertToTitle(702));
        assertEquals("AAA", test.convertToTitle(703));
    }

    @Test
    public void testGiantCase() {
        // 26^4 + 26^3 + 26^2 + 26 = 475254 -> ZZZZ
        assertEquals("ZZZZ", test.convertToTitle(475254));
    }
}
