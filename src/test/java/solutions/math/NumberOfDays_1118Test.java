package solutions.math;

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

    @Test
    public void testAdditional1() {
        assertEquals(31, test.numberOfDays(1971, 1));
    }

    @Test
    public void testAdditional2() {
        assertEquals(28, test.numberOfDays(1971, 2));
    }

    @Test
    public void testAdditional3() {
        assertEquals(31, test.numberOfDays(1971, 3));
    }

    @Test
    public void testAdditional4() {
        assertEquals(29, test.numberOfDays(1972, 2));
    }

    @Test
    public void testAdditional5() {
        assertEquals(29, test.numberOfDays(2000, 2));
    }

    @Test
    public void testAdditional6() {
        assertEquals(28, test.numberOfDays(1900, 2));
    }

    @Test
    public void testAdditional7() {
        assertEquals(30, test.numberOfDays(2020, 4));
    }

    @Test
    public void testAdditional8() {
        assertEquals(31, test.numberOfDays(2021, 12));
    }

    @Test
    public void testAdditional9() {
        assertEquals(31, test.numberOfDays(2024, 12));
    }

    @Test
    public void testAdditional10() {
        assertEquals(30, test.numberOfDays(1999, 9));
    }
}
