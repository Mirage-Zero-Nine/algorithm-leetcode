package solutions.design;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class MyCalendarTwo_731Test {

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

    @Test
    public void testNoOverlap() {
        MyCalendarTwo_731 cal = new MyCalendarTwo_731();
        assertTrue(cal.book(0, 10));
        assertTrue(cal.book(10, 20));
        assertTrue(cal.book(20, 30));
    }

    @Test
    public void testDoubleBookingAllowed() {
        MyCalendarTwo_731 cal = new MyCalendarTwo_731();
        assertTrue(cal.book(0, 20));
        assertTrue(cal.book(10, 30));
        // overlap [10,20) is double-booked, but not triple
    }

    @Test
    public void testTripleBookingBlocked() {
        MyCalendarTwo_731 cal = new MyCalendarTwo_731();
        assertTrue(cal.book(0, 20));
        assertTrue(cal.book(5, 25));
        assertFalse(cal.book(10, 15));
    }

    @Test
    public void testAdjacentAfterDoubleBook() {
        MyCalendarTwo_731 cal = new MyCalendarTwo_731();
        assertTrue(cal.book(0, 10));
        assertTrue(cal.book(0, 10));
        // adjacent interval should still be fine
        assertTrue(cal.book(10, 20));
    }

    @Test
    public void testPartialOverlapTriple() {
        MyCalendarTwo_731 cal = new MyCalendarTwo_731();
        assertTrue(cal.book(0, 30));
        assertTrue(cal.book(10, 20));
        // [10,20) is double-booked; booking [15,25) would triple [15,20)
        assertFalse(cal.book(15, 25));
    }

    @Test
    public void testBookAfterFailedAttempt() {
        MyCalendarTwo_731 cal = new MyCalendarTwo_731();
        assertTrue(cal.book(0, 10));
        assertTrue(cal.book(0, 10));
        assertFalse(cal.book(0, 10));
        // non-overlapping should still work
        assertTrue(cal.book(10, 20));
    }

    @Test
    public void testSingleUnitIntervals() {
        MyCalendarTwo_731 cal = new MyCalendarTwo_731();
        assertTrue(cal.book(1, 2));
        assertTrue(cal.book(1, 2));
        assertFalse(cal.book(1, 2));
        assertTrue(cal.book(2, 3));
    }

    @Test
    public void testGiantCase() {
        MyCalendarTwo_731 cal = new MyCalendarTwo_731();
        // book 500 non-overlapping intervals
        for (int i = 0; i < 500; i++) {
            assertTrue(cal.book(i * 4, i * 4 + 2));
        }
        // double-book all of them
        for (int i = 0; i < 500; i++) {
            assertTrue(cal.book(i * 4, i * 4 + 2));
        }
        // triple-book should fail
        assertFalse(cal.book(0, 2));
    }
}
