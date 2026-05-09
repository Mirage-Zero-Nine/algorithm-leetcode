package solution.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DaysBetweenDates1360Test {

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
}
