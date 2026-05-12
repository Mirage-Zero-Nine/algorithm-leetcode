package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class MyCalendarTwo731Test {

    @Test
    public void testHappyCases() {
        MyCalendarTwo_731 cal = new MyCalendarTwo_731();
        assertTrue(cal.book(10, 20));
        assertTrue(cal.book(50, 60));
        assertTrue(cal.book(10, 40));
        assertFalse(cal.book(5, 15));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        MyCalendarTwo_731 cal = new MyCalendarTwo_731();
        assertTrue(cal.book(1, 5));
        assertTrue(cal.book(1, 5));
        assertFalse(cal.book(1, 5));
    }

    @Test
    public void testLargeCase() {
        MyCalendarTwo_731 cal = new MyCalendarTwo_731();
        assertTrue(cal.book(0, 10));
        assertTrue(cal.book(5, 15));
        assertTrue(cal.book(10, 20));
        assertFalse(cal.book(5, 10));
    }
}
