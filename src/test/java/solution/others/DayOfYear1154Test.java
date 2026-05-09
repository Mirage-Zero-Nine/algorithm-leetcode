package solution.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DayOfYear1154Test {

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
}
