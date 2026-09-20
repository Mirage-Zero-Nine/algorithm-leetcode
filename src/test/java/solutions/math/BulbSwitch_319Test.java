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

    @Test
    public void testAdditional1() {
        assertEquals(2, test.bulbSwitch(6));
    }

    @Test
    public void testAdditional2() {
        assertEquals(2, test.bulbSwitch(7));
    }

    @Test
    public void testAdditional3() {
        assertEquals(2, test.bulbSwitch(8));
    }

    @Test
    public void testAdditional4() {
        assertEquals(3, test.bulbSwitch(15));
    }

    @Test
    public void testAdditional5() {
        assertEquals(6, test.bulbSwitch(36));
    }

    @Test
    public void testAdditional6() {
        assertEquals(7, test.bulbSwitch(49));
    }

    @Test
    public void testAdditional7() {
        assertEquals(7, test.bulbSwitch(50));
    }

    @Test
    public void testAdditional8() {
        assertEquals(9, test.bulbSwitch(99));
    }

    @Test
    public void testAdditional9() {
        assertEquals(100, test.bulbSwitch(10000));
    }

    @Test
    public void testAdditional10() {
        assertEquals(46340, test.bulbSwitch(2147395600));
    }
}
