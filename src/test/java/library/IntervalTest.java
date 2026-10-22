package library;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class IntervalTest {
    @Test void defaultValues() { Interval i = new Interval(); assertEquals(0, i.start); assertEquals(0, i.end); }
    @Test void storesPositiveBounds() { Interval i = new Interval(2, 9); assertEquals(2, i.start); assertEquals(9, i.end); }
    @Test void storesNegativeBounds() { Interval i = new Interval(-9, -2); assertEquals(-9, i.start); assertEquals(-2, i.end); }
    @Test void storesEqualBounds() { Interval i = new Interval(4, 4); assertEquals(i.start, i.end); }
    @Test void storesReversedBoundsWithoutNormalization() { Interval i = new Interval(8, 3); assertEquals(8, i.start); assertEquals(3, i.end); }
    @Test void storesIntegerMinimum() { assertEquals(Integer.MIN_VALUE, new Interval(Integer.MIN_VALUE, 0).start); }
    @Test void storesIntegerMaximum() { assertEquals(Integer.MAX_VALUE, new Interval(0, Integer.MAX_VALUE).end); }
    @Test void fieldsAreMutable() { Interval i = new Interval(1, 2); i.start = 5; i.end = 6; assertEquals(5, i.start); assertEquals(6, i.end); }
}
