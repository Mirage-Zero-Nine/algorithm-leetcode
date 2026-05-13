package solution.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NumberOfDays_1118Test {

    private final NumberOfDays_1118 test = new NumberOfDays_1118();

    @Test
    public void testHappyCases() {
        assertEquals(31, test.numberOfDays(2019, 1));
        assertEquals(28, test.numberOfDays(2019, 2));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(29, test.numberOfDays(2000, 2));
        assertEquals(28, test.numberOfDays(1900, 2));
    }

    @Test
    public void testLargeCase() {
        assertEquals(31, test.numberOfDays(2020, 12));
        assertEquals(29, test.numberOfDays(2020, 2));
    }

    @Test
    public void testApril() {
        assertEquals(30, test.numberOfDays(2019, 4));
    }

    @Test
    public void testJune() {
        assertEquals(30, test.numberOfDays(2019, 6));
    }

    @Test
    public void testJuly() {
        assertEquals(31, test.numberOfDays(2019, 7));
    }

    @Test
    public void testSeptember() {
        assertEquals(30, test.numberOfDays(2019, 9));
    }

    @Test
    public void testNovember() {
        assertEquals(30, test.numberOfDays(2019, 11));
    }

    @Test
    public void testLeapYearDivisibleBy400() {
        assertEquals(29, test.numberOfDays(2400, 2));
    }

    @Test
    public void testNonLeapCentury() {
        assertEquals(28, test.numberOfDays(2100, 2));
    }
}
