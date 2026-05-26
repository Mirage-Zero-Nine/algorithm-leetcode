package solutions.design;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UndergroundSystem_1396Test {

    @Test
    public void testHappyCases() {
        UndergroundSystem_1396 us = new UndergroundSystem_1396();
        us.checkIn(45, "Leyton", 3);
        us.checkIn(32, "Paradise", 8);
        us.checkIn(27, "Leyton", 10);
        us.checkOut(45, "Waterloo", 15);
        us.checkOut(27, "Waterloo", 20);
        us.checkOut(32, "Cambridge", 22);
        assertEquals(11.0, us.getAverageTime("Leyton", "Waterloo"), 0.0001);
        assertEquals(11.0, us.getAverageTime("Leyton", "Waterloo"), 0.0001);
    }

    @Test
    public void testEdgeCases() {
        UndergroundSystem_1396 us = new UndergroundSystem_1396();
        us.checkIn(1, "A", 0);
        us.checkOut(1, "B", 10);
        assertEquals(10.0, us.getAverageTime("A", "B"), 0.0001);
    }

    @Test
    public void testLargeCase() {
        UndergroundSystem_1396 us = new UndergroundSystem_1396();
        for (int i = 1; i <= 5; i++) {
            us.checkIn(i, "Start", i);
            us.checkOut(i, "End", i + 10);
        }
        assertEquals(10.0, us.getAverageTime("Start", "End"), 0.0001);
    }

    @Test
    public void testMultipleTrips() {
        UndergroundSystem_1396 us = new UndergroundSystem_1396();
        us.checkIn(1, "A", 0);
        us.checkOut(1, "B", 10);
        us.checkIn(1, "A", 20);
        us.checkOut(1, "B", 40);
        // average = (10 + 20) / 2 = 15
        assertEquals(15.0, us.getAverageTime("A", "B"), 0.0001);
    }

    @Test
    public void testDifferentPaths() {
        UndergroundSystem_1396 us = new UndergroundSystem_1396();
        us.checkIn(1, "A", 0);
        us.checkOut(1, "B", 5);
        us.checkIn(2, "A", 0);
        us.checkOut(2, "C", 10);
        assertEquals(5.0, us.getAverageTime("A", "B"), 0.0001);
        assertEquals(10.0, us.getAverageTime("A", "C"), 0.0001);
    }

    @Test
    public void testNonExistentPath() {
        UndergroundSystem_1396 us = new UndergroundSystem_1396();
        // getAverageTime for a path that doesn't exist returns 0
        assertEquals(0.0, us.getAverageTime("X", "Y"), 0.0001);
    }

    @Test
    public void testSamePassengerMultipleRoutes() {
        UndergroundSystem_1396 us = new UndergroundSystem_1396();
        us.checkIn(1, "A", 0);
        us.checkOut(1, "B", 10);
        us.checkIn(1, "B", 15);
        us.checkOut(1, "C", 25);
        assertEquals(10.0, us.getAverageTime("A", "B"), 0.0001);
        assertEquals(10.0, us.getAverageTime("B", "C"), 0.0001);
    }

    @Test
    public void testAverageWithVariedTimes() {
        UndergroundSystem_1396 us = new UndergroundSystem_1396();
        us.checkIn(1, "A", 0);
        us.checkOut(1, "B", 4);
        us.checkIn(2, "A", 0);
        us.checkOut(2, "B", 6);
        us.checkIn(3, "A", 0);
        us.checkOut(3, "B", 8);
        // average = (4 + 6 + 8) / 3 = 6
        assertEquals(6.0, us.getAverageTime("A", "B"), 0.0001);
    }

    @Test
    public void testConcurrentPassengers() {
        UndergroundSystem_1396 us = new UndergroundSystem_1396();
        us.checkIn(1, "A", 0);
        us.checkIn(2, "A", 0);
        us.checkIn(3, "A", 0);
        us.checkOut(1, "B", 5);
        us.checkOut(2, "B", 10);
        us.checkOut(3, "B", 15);
        // average = (5 + 10 + 15) / 3 = 10
        assertEquals(10.0, us.getAverageTime("A", "B"), 0.0001);
    }

    @Test
    public void testGiantCase() {
        UndergroundSystem_1396 us = new UndergroundSystem_1396();
        int n = 1000;
        for (int i = 0; i < n; i++) {
            us.checkIn(i, "Start", i);
            us.checkOut(i, "End", i + 100);
        }
        assertEquals(100.0, us.getAverageTime("Start", "End"), 0.0001);
    }

    @Test
    public void testSingleTripExactAverage() {
        UndergroundSystem_1396 us = new UndergroundSystem_1396();
        us.checkIn(99, "X", 1000);
        us.checkOut(99, "Y", 1007);
        assertEquals(7.0, us.getAverageTime("X", "Y"), 0.0001);
    }
}
