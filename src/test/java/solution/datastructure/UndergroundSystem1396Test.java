package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UndergroundSystem1396Test {

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
}
