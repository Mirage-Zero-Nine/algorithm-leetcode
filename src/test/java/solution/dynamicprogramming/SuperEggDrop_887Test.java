package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SuperEggDrop_887Test {

    private final SuperEggDrop_887 test = new SuperEggDrop_887();

    @Test
    public void testHappyCases() {
        assertEquals(2, test.superEggDrop(1, 2));
        assertEquals(6, test.superEggDrop(2, 6));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.superEggDrop(1, 1));
        assertEquals(1, test.superEggDrop(2, 1));
    }

    @Test
    public void testLargeCase() {
        assertEquals(10, test.superEggDrop(2, 10));
    }

    @Test
    public void testOneEgg() {
        // With 1 egg, must try linearly from bottom
        assertEquals(3, test.superEggDrop(1, 3));
        assertEquals(5, test.superEggDrop(1, 5));
        assertEquals(10, test.superEggDrop(1, 10));
    }

    @Test
    public void testTwoEggsVariousFloors() {
        assertEquals(9, test.superEggDrop(2, 9));
        assertEquals(14, test.superEggDrop(2, 14));
    }

    @Test
    public void testThreeEggs() {
        assertEquals(14, test.superEggDrop(3, 14));
        assertEquals(25, test.superEggDrop(3, 25));
    }

    @Test
    public void testManyEggs() {
        assertEquals(10, test.superEggDrop(10, 10));
    }

    @Test
    public void testSingleFloor() {
        assertEquals(1, test.superEggDrop(5, 1));
        assertEquals(1, test.superEggDrop(100, 1));
    }

    @Test
    public void testTwoFloors() {
        assertEquals(2, test.superEggDrop(1, 2));
        assertEquals(2, test.superEggDrop(2, 2));
        assertEquals(2, test.superEggDrop(3, 2));
    }

    @Test
    public void testGiantCase() {
        assertEquals(100, test.superEggDrop(2, 100));
    }
}
