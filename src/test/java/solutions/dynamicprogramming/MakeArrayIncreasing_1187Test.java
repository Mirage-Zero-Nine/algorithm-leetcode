package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MakeArrayIncreasing_1187Test {

    private final MakeArrayIncreasing_1187 test = new MakeArrayIncreasing_1187();

    @Test
    public void testHappyCases() {
        assertEquals(1, test.makeArrayIncreasing(new int[]{1, 5, 3, 6, 7}, new int[]{1, 3, 2, 4}));
        assertEquals(2, test.makeArrayIncreasing(new int[]{1, 5, 3, 6, 7}, new int[]{4, 3, 1}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(-1, test.makeArrayIncreasing(new int[]{1, 5, 3, 6, 7}, new int[]{1, 6, 3, 3}));
        assertEquals(0, test.makeArrayIncreasing(new int[]{1}, new int[]{2}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(0, test.makeArrayIncreasing(new int[]{1, 2, 3, 4, 5}, new int[]{10, 20, 30}));
    }

    @Test
    public void testAlreadySorted() {
        assertEquals(0, test.makeArrayIncreasing(new int[]{1, 3, 5, 7, 9}, new int[]{2, 4}));
    }

    @Test
    public void testAllReplacements() {
        assertEquals(5, test.makeArrayIncreasing(new int[]{0, 11, 6, 1, 4, 3}, new int[]{5, 4, 11, 10, 1, 0}));
    }

    @Test
    public void testImpossible() {
        // arr1=[5,4,3,2,1], arr2=[5,4,3,2,1] -> can be made increasing with 4 ops
        assertEquals(4, test.makeArrayIncreasing(new int[]{5, 4, 3, 2, 1}, new int[]{5, 4, 3, 2, 1}));
    }

    @Test
    public void testSingleElement() {
        assertEquals(0, test.makeArrayIncreasing(new int[]{42}, new int[]{1, 2, 3}));
    }

    @Test
    public void testTwoElements() {
        assertEquals(0, test.makeArrayIncreasing(new int[]{1, 2}, new int[]{3}));
        assertEquals(1, test.makeArrayIncreasing(new int[]{2, 1}, new int[]{3}));
    }

    @Test
    public void testLargeReplacement() {
        assertEquals(8, test.makeArrayIncreasing(
                new int[]{5, 16, 19, 2, 1, 12, 7, 14, 5, 16},
                new int[]{6, 17, 4, 3, 6, 13, 4, 3, 18, 17, 16, 7, 14, 1, 16}));
    }

    @Test
    public void testGiantCase() {
        assertEquals(11, test.makeArrayIncreasing(
                new int[]{23, 10, 9, 12, 3, 14, 21, 16, 7, 10, 17, 12},
                new int[]{6, 5, 0, 15, 2, 17, 4, 11, 6, 5, 8, 15, 10, 1, 20, 11, 14, 13, 8}));
    }
}
