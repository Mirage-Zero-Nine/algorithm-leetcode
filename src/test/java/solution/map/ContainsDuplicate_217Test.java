package solution.map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ContainsDuplicate_217Test {

    private final ContainsDuplicate_217 test = new ContainsDuplicate_217();

    @Test
    public void testHappyCases() {
        assertTrue(test.containsDuplicate(new int[]{1, 2, 3, 1}));
        assertFalse(test.containsDuplicate(new int[]{1, 2, 3, 4}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.containsDuplicate(new int[]{1}));
        assertTrue(test.containsDuplicate(new int[]{1, 1}));
        assertFalse(test.containsDuplicate(new int[]{}));
    }

    @Test
    public void testLargeCase() {
        int[] arr = new int[1000];
        for (int i = 0; i < 1000; i++) arr[i] = i;
        assertFalse(test.containsDuplicate(arr));
        arr[999] = 0;
        assertTrue(test.containsDuplicate(arr));
    }

    @Test
    public void testAllSameElements() {
        assertTrue(test.containsDuplicate(new int[]{5, 5, 5, 5, 5}));
    }

    @Test
    public void testNegativeNumbers() {
        assertTrue(test.containsDuplicate(new int[]{-1, -2, -3, -1}));
        assertFalse(test.containsDuplicate(new int[]{-1, -2, -3}));
    }

    @Test
    public void testMixedPositiveNegative() {
        assertFalse(test.containsDuplicate(new int[]{-1, 1, -2, 2}));
        assertTrue(test.containsDuplicate(new int[]{-1, 1, -1}));
    }

    @Test
    public void testTwoElements() {
        assertFalse(test.containsDuplicate(new int[]{1, 2}));
        assertTrue(test.containsDuplicate(new int[]{3, 3}));
    }

    @Test
    public void testDuplicateAtEnd() {
        assertTrue(test.containsDuplicate(new int[]{1, 2, 3, 4, 5, 1}));
    }

    @Test
    public void testDuplicateAtStart() {
        assertTrue(test.containsDuplicate(new int[]{7, 7, 1, 2, 3}));
    }

    @Test
    public void testGiantNoDuplicate() {
        int[] arr = new int[100000];
        for (int i = 0; i < 100000; i++) arr[i] = i;
        assertFalse(test.containsDuplicate(arr));
    }
}
