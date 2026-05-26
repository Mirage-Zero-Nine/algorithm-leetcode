package solutions.design;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class MyCalendar_729Test {

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

    @Test
    public void testAdjacentIntervals() {
        MyCalendar_729 cal = new MyCalendar_729();
        assertTrue(cal.book(0, 10));
        assertTrue(cal.book(10, 20));
        assertTrue(cal.book(20, 30));
    }

    @Test
    public void testOverlapAtStart() {
        MyCalendar_729 cal = new MyCalendar_729();
        assertTrue(cal.book(10, 20));
        assertFalse(cal.book(5, 11));
    }

    @Test
    public void testOverlapAtEnd() {
        MyCalendar_729 cal = new MyCalendar_729();
        assertTrue(cal.book(10, 20));
        assertFalse(cal.book(19, 30));
    }

    @Test
    public void testContainedInterval() {
        MyCalendar_729 cal = new MyCalendar_729();
        assertTrue(cal.book(10, 30));
        assertFalse(cal.book(15, 25));
    }

    @Test
    public void testSupersetInterval() {
        MyCalendar_729 cal = new MyCalendar_729();
        assertTrue(cal.book(15, 25));
        assertFalse(cal.book(10, 30));
    }

    @Test
    public void testSingleUnitInterval() {
        MyCalendar_729 cal = new MyCalendar_729();
        assertTrue(cal.book(5, 6));
        assertTrue(cal.book(6, 7));
        assertFalse(cal.book(5, 6));
    }

    @Test
    public void testBookBetweenExisting() {
        MyCalendar_729 cal = new MyCalendar_729();
        assertTrue(cal.book(0, 5));
        assertTrue(cal.book(10, 15));
        assertTrue(cal.book(5, 10));
    }

    @Test
    public void testGiantCase() {
        MyCalendar_729 cal = new MyCalendar_729();
        for (int i = 0; i < 1000; i++) {
            assertTrue(cal.book(i * 2, i * 2 + 1));
        }
        // all gaps between booked intervals
        for (int i = 0; i < 999; i++) {
            assertFalse(cal.book(i * 2, i * 2 + 1));
        }
    }
}
