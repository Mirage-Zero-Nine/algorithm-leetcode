package solutions.binarysearch;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IncreasingTriplet_334Test {

    private final IncreasingTriplet_334 test = new IncreasingTriplet_334();

    @Test
    public void testHappyCases() {
        assertTrue(test.increasingTriplet(new int[]{1, 2, 3, 4, 5}));
        assertTrue(test.increasingTriplet(new int[]{2, 1, 5, 0, 4, 6}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.increasingTriplet(new int[]{5, 4, 3, 2, 1}));
        assertFalse(test.increasingTriplet(new int[]{1, 2}));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.increasingTriplet(new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 3}));
    }

    @Test
    public void testExactlyThreeIncreasing() {
        assertTrue(test.increasingTriplet(new int[]{1, 2, 3}));
        assertTrue(test.traverse(new int[]{1, 2, 3}));
    }

    @Test
    public void testExactlyThreeNotIncreasing() {
        assertFalse(test.increasingTriplet(new int[]{3, 2, 1}));
        assertFalse(test.traverse(new int[]{3, 2, 1}));
    }

    @Test
    public void testWithDuplicatesNoTriplet() {
        int[] arr = new int[]{1, 1, 1, 2, 2};
        assertFalse(test.increasingTriplet(arr));
        assertFalse(test.traverse(arr));
    }

    @Test
    public void testNegativeValuesHasTriplet() {
        int[] arr = new int[]{-5, -4, -3, -2};
        assertTrue(test.increasingTriplet(arr));
        assertTrue(test.traverse(arr));
    }

    @Test
    public void testMixedValuesNoTriplet() {
        int[] arr = new int[]{2, 4, -2, -3};
        assertFalse(test.increasingTriplet(arr));
        assertFalse(test.traverse(arr));
    }

    @Test
    public void testLateTriplet() {
        int[] arr = new int[]{9, 8, 7, 1, 2, 3};
        assertTrue(test.increasingTriplet(arr));
        assertTrue(test.traverse(arr));
    }

    @Test
    public void testGiantCase() {
        int[] arr = new int[500];
        for (int i = 0; i < 498; i++) {
            arr[i] = 1000 - i;
        }
        arr[498] = 1;
        arr[499] = 2;
        assertFalse(test.increasingTriplet(arr));
        assertFalse(test.traverse(arr));
    }
}
