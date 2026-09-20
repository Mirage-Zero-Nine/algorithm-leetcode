package solutions.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DaysBetweenDates_1360Test {

    private final DaysBetweenDates_1360 test = new DaysBetweenDates_1360();

    @Test
    public void testHappyCases() {
        assertEquals(1, test.daysBetweenDates("2019-06-29", "2019-06-30"));
        assertEquals(15, test.daysBetweenDates("2020-01-15", "2019-12-31"));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(0, test.daysBetweenDates("2019-01-01", "2019-01-01"));
        assertEquals(1, test.daysBetweenDates("2019-01-01", "2019-01-02"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(365, test.daysBetweenDates("2019-01-01", "2020-01-01"));
    }

    @Test
    public void testLeapYear() {
        assertEquals(366, test.daysBetweenDates("2020-01-01", "2021-01-01"));
    }

    @Test
    public void testReversedOrder() {
        assertEquals(1, test.daysBetweenDates("2019-06-30", "2019-06-29"));
    }

    @Test
    public void testCrossYearBoundary() {
        assertEquals(31, test.daysBetweenDates("2019-12-01", "2020-01-01"));
    }

    @Test
    public void testCenturyLeapYear() {
        assertEquals(366, test.daysBetweenDates("2000-01-01", "2001-01-01"));
    }

    @Test
    public void testNonLeapCentury() {
        // 2100 is not a leap year (divisible by 100 but not 400)
        assertEquals(365, test.daysBetweenDates("2100-01-01", "2101-01-01"));
    }

    @Test
    public void testMultipleYears() {
        assertEquals(730, test.daysBetweenDates("2018-01-01", "2020-01-01"));
    }

    @Test
    public void testGiantCase() {
        // Large span across many years
        int result = test.daysBetweenDates("1971-01-01", "2100-12-31");
        assertEquals(47481, result);
    }

    @Test
    public void testAdditional1() {
        assertEquals(0, test.daysBetweenDates("1971-01-01", "1971-01-01"));
    }

    @Test
    public void testAdditional2() {
        assertEquals(1, test.daysBetweenDates("1971-01-01", "1971-01-02"));
    }

    @Test
    public void testAdditional3() {
        assertEquals(1, test.daysBetweenDates("2019-12-31", "2020-01-01"));
    }

    @Test
    public void testAdditional4() {
        assertEquals(2, test.daysBetweenDates("2020-02-28", "2020-03-01"));
    }

    @Test
    public void testAdditional5() {
        assertEquals(1, test.daysBetweenDates("2019-02-28", "2019-03-01"));
    }

    @Test
    public void testAdditional6() {
        assertEquals(7305, test.daysBetweenDates("2000-01-01", "2020-01-01"));
    }

    @Test
    public void testAdditional7() {
        assertEquals(364, test.daysBetweenDates("1900-01-01", "1900-12-31"));
    }

    @Test
    public void testAdditional8() {
        assertEquals(1, test.daysBetweenDates("2020-12-31", "2021-01-01"));
    }

    @Test
    public void testAdditional9() {
        assertEquals(30, test.daysBetweenDates("2021-05-10", "2021-04-10"));
    }

    @Test
    public void testAdditional10() {
        assertEquals(47481, test.daysBetweenDates("1971-01-01", "2100-12-31"));
    }
}
