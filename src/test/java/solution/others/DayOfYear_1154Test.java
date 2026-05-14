package solution.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DayOfYear_1154Test {

    private final DayOfYear_1154 test = new DayOfYear_1154();

    @Test
    public void testHappyCases() {
        assertEquals(9, test.dayOfYear("2019-01-09"));
        assertEquals(41, test.dayOfYear("2019-02-10"));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.dayOfYear("2019-01-01"));
        assertEquals(60, test.dayOfYear("2004-02-29"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(365, test.dayOfYear("2019-12-31"));
        assertEquals(366, test.dayOfYear("2000-12-31"));
    }

    @Test
    public void testMarch1NonLeap() {
        assertEquals(60, test.dayOfYear("2019-03-01"));
    }

    @Test
    public void testMarch1Leap() {
        assertEquals(61, test.dayOfYear("2004-03-01"));
    }

    @Test
    public void testJune15() {
        assertEquals(166, test.dayOfYear("2019-06-15"));
    }

    @Test
    public void testLastDayLeapYear() {
        assertEquals(366, test.dayOfYear("2004-12-31"));
    }

    @Test
    public void testFeb28NonLeap() {
        assertEquals(59, test.dayOfYear("2019-02-28"));
    }

    @Test
    public void testCenturyNonLeap() {
        // 1900 is not a leap year
        assertEquals(365, test.dayOfYear("1900-12-31"));
    }

    @Test
    public void testGiantYear() {
        // Year 2400 is a leap year (divisible by 400)
        assertEquals(61, test.dayOfYear("2400-03-01"));
    }
}
