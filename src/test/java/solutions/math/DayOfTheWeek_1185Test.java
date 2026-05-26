package solutions.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DayOfTheWeek_1185Test {

    private final DayOfTheWeek_1185 test = new DayOfTheWeek_1185();

    @Test
    public void testHappyCases() {
        assertEquals("Saturday", test.dayOfTheWeek(31, 8, 2019));
        assertEquals("Sunday", test.dayOfTheWeek(18, 7, 1999));
    }

    @Test
    public void testEdgeCases() {
        assertEquals("Monday", test.dayOfTheWeek(1, 1, 2001));
        assertEquals("Wednesday", test.dayOfTheWeek(28, 2, 2018));
    }

    @Test
    public void testLargeCase() {
        assertEquals("Friday", test.dayOfTheWeek(15, 8, 1947));
    }

    @Test
    public void testLeapDay() {
        assertEquals("Saturday", test.dayOfTheWeek(29, 2, 2020));
    }

    @Test
    public void testNewYear2020() {
        assertEquals("Wednesday", test.dayOfTheWeek(1, 1, 2020));
    }

    @Test
    public void testChristmas() {
        assertEquals("Wednesday", test.dayOfTheWeek(25, 12, 2019));
    }

    @Test
    public void testY2K() {
        assertEquals("Saturday", test.dayOfTheWeek(1, 1, 2000));
    }

    @Test
    public void testHistoricDate() {
        // July 4, 1776
        assertEquals("Thursday", test.dayOfTheWeek(4, 7, 1776));
    }

    @Test
    public void testDecember31() {
        assertEquals("Tuesday", test.dayOfTheWeek(31, 12, 2019));
    }

    @Test
    public void testFarFuture() {
        // Jan 1, 2100
        assertEquals("Friday", test.dayOfTheWeek(1, 1, 2100));
    }
}
