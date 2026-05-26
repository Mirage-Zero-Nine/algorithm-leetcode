package solutions.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BulbSwitch_319Test {

    private final BulbSwitch_319 test = new BulbSwitch_319();

    @Test
    public void testHappyCases() {
        assertEquals(1, test.bulbSwitch(3));
        assertEquals(2, test.bulbSwitch(5));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.bulbSwitch(0));
        assertEquals(1, test.bulbSwitch(1));
    }

    @Test
    public void testLargeCase() {
        assertEquals(10, test.bulbSwitch(100));
        assertEquals(31, test.bulbSwitch(1000));
    }

    @Test
    public void testTwo() {
        assertEquals(1, test.bulbSwitch(2));
    }

    @Test
    public void testFour() {
        assertEquals(2, test.bulbSwitch(4));
    }

    @Test
    public void testNine() {
        assertEquals(3, test.bulbSwitch(9));
    }

    @Test
    public void testSixteen() {
        assertEquals(4, test.bulbSwitch(16));
    }

    @Test
    public void testTwentyFive() {
        assertEquals(5, test.bulbSwitch(25));
    }

    @Test
    public void testNonPerfectSquare() {
        assertEquals(3, test.bulbSwitch(10));
    }

    @Test
    public void testGiantCase() {
        assertEquals(316, test.bulbSwitch(100000));
    }
}
