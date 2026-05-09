package solution.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DayOfTheWeek1185Test {

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
}
