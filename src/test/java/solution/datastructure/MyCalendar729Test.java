package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class MyCalendar729Test {

    @Test
    public void testHappyCases() {
        MyCalendar_729 cal = new MyCalendar_729();
        assertTrue(cal.book(10, 20));
        assertFalse(cal.book(15, 25));
        assertTrue(cal.book(20, 30));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        MyCalendar_729 cal = new MyCalendar_729();
        assertTrue(cal.book(1, 5));
        assertFalse(cal.book(1, 5));
        assertTrue(cal.book(5, 10));
    }

    @Test
    public void testLargeCase() {
        MyCalendar_729 cal = new MyCalendar_729();
        for (int i = 0; i < 10; i++) {
            assertTrue(cal.book(i * 10, i * 10 + 10));
        }
        assertFalse(cal.book(5, 15));
    }
}
