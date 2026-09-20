package solutions.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CompareVersion_165Test {

    private final CompareVersion_165 test = new CompareVersion_165();

    @Test
    public void testHappyCases() {
        assertEquals(-1, test.compareVersion("0.1", "1.1"));
        assertEquals(1, test.compareVersion("1.0.1", "1"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.compareVersion("1.01", "1.001"));
        assertEquals(0, test.compareVersion("1.0", "1.0.0"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(-1, test.compareVersion("7.5.2.4", "7.5.3"));
        assertEquals(1, test.compareVersion("1.2.3", "1.2.2"));
    }

    @Test
    public void testAdditionalHappyCases() {
        assertEquals(1, test.compareVersion("2.0", "1.9.9"));
        assertEquals(-1, test.compareVersion("3.10", "3.11"));
        assertEquals(0, test.compareVersion("4.0.0.0", "4"));
    }

    @Test
    public void testAdditionalEdgeCases() {
        assertEquals(0, test.compareVersion("0", "0.0.0"));
        assertEquals(1, test.compareVersion("10.0.1", "10.0.0"));
        assertEquals(-1, test.compareVersion("1.0.0", "1.0.0.1"));
    }

    @Test
    public void testAdditionalGiantCase() {
        assertEquals(1, test.compareVersion("1.0.0.0.0.0.0.0.0.5", "1.0.0.0.0.0.0.0.0.4"));
        assertEquals(0, test.compareVersion("001.002.003.000", "1.2.3"));
    }

    @Test
    public void testSameVersion() {
        assertEquals(0, test.compareVersion("1.1.1", "1.1.1"));
    }

    @Test
    public void testSingleDigitVersions() {
        assertEquals(-1, test.compareVersion("1", "2"));
        assertEquals(1, test.compareVersion("9", "8"));
        assertEquals(0, test.compareVersion("5", "5"));
    }

    @Test
    public void testTrailingZeros() {
        assertEquals(0, test.compareVersion("1.0.0.0.0", "1"));
        assertEquals(1, test.compareVersion("1.0.0.0.1", "1"));
    }

    @Test
    public void testLargeVersionNumbers() {
        assertEquals(1, test.compareVersion("100.200.300", "100.200.299"));
        assertEquals(-1, test.compareVersion("99.99.99", "100.0.0"));
    }

    @Test
    public void testRevisionFieldsBeyondIntegerRange() {
        assertEquals(1, test.compareVersion("1.999999999999999999999999999999", "1.2"));
        assertEquals(-1, test.compareVersion("1.100000000000000000000000000001", "1.100000000000000000000000000002"));
        assertEquals(0, test.compareVersion("1." + "0".repeat(500), "1"));
    }

    @Test public void testLeadingZerosAtDifferentDepths() { assertEquals(0, test.compareVersion("1.0.0.0", "1")); }
    @Test public void testFirstRevisionDominates() { assertEquals(-1, test.compareVersion("2.9.9", "10")); }
    @Test public void testLaterRevisionDominates() { assertEquals(1, test.compareVersion("1.0.0.1", "1.0")); }
    @Test public void testZeroRevisionAgainstZeros() { assertEquals(0, test.compareVersion("0.0", "0")); }
    @Test public void testTwoDigitComparison() { assertEquals(1, test.compareVersion("1.10", "1.9.9")); }
    @Test public void testManyTrailingZeros() { assertEquals(0, test.compareVersion("3." + "0.".repeat(30) + "0", "3")); }
    @Test public void testLongerCanonicalFieldWins() { assertEquals(-1, test.compareVersion("1.000000000000000000001", "1.2")); }
    @Test public void testEqualLongFields() { assertEquals(0, test.compareVersion("000123.004", "123.4.0")); }
    @Test public void testTrailingNonzeroField() { assertEquals(-1, test.compareVersion("8.0.0.09", "8.0.0.10")); }
}
