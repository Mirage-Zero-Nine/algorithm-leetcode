package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class SuperEggDrop_887Test {

    private final SuperEggDrop_887 test = new SuperEggDrop_887();

    @Test
    public void testHappyCases() {
        assertEquals(2, test.superEggDrop(1, 2));
        assertEquals(3, test.superEggDrop(2, 6));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.superEggDrop(1, 1));
        assertEquals(1, test.superEggDrop(2, 1));
    }

    @Test
    public void testLargeCase() {
        assertEquals(4, test.superEggDrop(2, 10));
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
        assertEquals(4, test.superEggDrop(2, 9));
        assertEquals(5, test.superEggDrop(2, 14));
    }

    @Test
    public void testThreeEggs() {
        assertEquals(4, test.superEggDrop(3, 14));
        assertEquals(5, test.superEggDrop(3, 25));
    }

    @Test
    public void testManyEggs() {
        assertEquals(4, test.superEggDrop(10, 10));
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
        assertEquals(14, test.superEggDrop(2, 100));
    }

    @ParameterizedTest(name = "{0} eggs, {1} floors")
    @CsvSource({"1,0,0", "1,4,4", "2,3,2", "2,4,3", "2,5,3", "3,3,2", "3,6,3", "4,4,3", "5,5,3", "10,20,5"})
    public void testAdditionalEggAndFloorBoundaries(int eggs, int floors, int expected) {
        assertEquals(expected, test.superEggDrop(eggs, floors));
    }
}
