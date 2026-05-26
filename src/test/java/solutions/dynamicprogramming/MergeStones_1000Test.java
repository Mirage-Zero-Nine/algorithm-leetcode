package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MergeStones_1000Test {

    private final MergeStones_1000 test = new MergeStones_1000();

    @Test
    public void testHappyCases() {
        assertEquals(20, test.mergeStones(new int[]{3, 2, 4, 1}, 2));
        assertEquals(25, test.mergeStones(new int[]{3, 5, 1, 2, 6}, 3));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(-1, test.mergeStones(new int[]{3, 2, 4, 1}, 3));
        assertEquals(0, test.mergeStones(new int[]{1}, 2));
    }

    @Test
    public void testLargeCase() {
        assertEquals(49, test.mergeStones(new int[]{1, 2, 3, 4, 5, 6, 7}, 3));
    }

    @Test
    public void testSingleElement() {
        assertEquals(0, test.mergeStones(new int[]{5}, 3));
    }

    @Test
    public void testTwoElementsK2() {
        assertEquals(3, test.mergeStones(new int[]{1, 2}, 2));
    }

    @Test
    public void testThreeElementsK3() {
        assertEquals(6, test.mergeStones(new int[]{1, 2, 3}, 3));
    }

    @Test
    public void testImpossibleCase() {
        assertEquals(-1, test.mergeStones(new int[]{1, 2, 3, 4}, 3));
    }

    @Test
    public void testAllOnes() {
        assertEquals(8, test.mergeStones(new int[]{1, 1, 1, 1}, 2));
    }

    @Test
    public void testK2FourElements() {
        assertEquals(24, test.mergeStones(new int[]{4, 3, 3, 2}, 2));
    }

    @Test
    public void testGiantCase() {
        assertEquals(90, test.mergeStones(new int[]{6, 4, 4, 6, 2, 4, 6}, 2));
    }
}
