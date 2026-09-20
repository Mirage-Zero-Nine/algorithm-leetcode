package solutions.hashmap;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ContainsNearbyDuplicate_219Test {

    private final ContainsNearbyDuplicate_219 test = new ContainsNearbyDuplicate_219();

    @Test
    public void testHappyCases() {
        assertTrue(test.containsNearbyDuplicate(new int[]{1, 2, 3, 1}, 3));
        assertTrue(test.containsNearbyDuplicate(new int[]{1, 0, 1, 1}, 1));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.containsNearbyDuplicate(new int[]{1, 2, 3, 1, 2, 3}, 2));
        assertFalse(test.containsNearbyDuplicate(new int[]{1}, 1));
        assertFalse(test.containsNearbyDuplicate(new int[]{1, 2}, 0));
    }

    @Test
    public void testLargeCase() {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) arr[i] = i;
        assertFalse(test.containsNearbyDuplicate(arr, 50));
        arr[99] = 0;
        assertTrue(test.containsNearbyDuplicate(arr, 99));
    }

    @Test
    public void testEmptyArray() {
        assertFalse(test.containsNearbyDuplicate(new int[]{}, 1));
    }

    @Test
    public void testKNegative() {
        assertFalse(test.containsNearbyDuplicate(new int[]{1, 1}, -1));
    }

    @Test
    public void testKEqualsOne() {
        assertTrue(test.containsNearbyDuplicate(new int[]{1, 1}, 1));
        assertFalse(test.containsNearbyDuplicate(new int[]{1, 2, 1}, 1));
    }

    @Test
    public void testDuplicateExactlyAtK() {
        assertTrue(test.containsNearbyDuplicate(new int[]{1, 2, 3, 1}, 3));
    }

    @Test
    public void testDuplicateBeyondK() {
        assertFalse(test.containsNearbyDuplicate(new int[]{1, 2, 3, 4, 1}, 3));
    }

    @Test
    public void testAllSameElements() {
        assertTrue(test.containsNearbyDuplicate(new int[]{1, 1, 1, 1}, 1));
    }

    @Test
    public void testKLargerThanArray() {
        assertTrue(test.containsNearbyDuplicate(new int[]{1, 2, 1}, 100));
    }

    @Test
    public void testGiantArrayNoDuplicate() {
        int[] arr = new int[100000];
        for (int i = 0; i < 100000; i++) arr[i] = i;
        assertFalse(test.containsNearbyDuplicate(arr, 5));
    }
    @Test void extra01() { assertTrue(test.containsNearbyDuplicate(new int[]{1,1}, 1)); }
    @Test void extra02() { assertFalse(test.containsNearbyDuplicate(new int[]{1,1}, 0)); }
    @Test void extra03() { assertTrue(test.containsNearbyDuplicate(new int[]{1,2,1}, 2)); }
    @Test void extra04() { assertFalse(test.containsNearbyDuplicate(new int[]{1,2,1}, 1)); }
    @Test void extra05() { assertTrue(test.containsNearbyDuplicate(new int[]{0,0,0}, 1)); }
    @Test void extra06() { assertFalse(test.containsNearbyDuplicate(new int[]{-1,2,-1}, 1)); }
    @Test void extra07() { assertTrue(test.containsNearbyDuplicate(new int[]{1,2,3,4,1}, 4)); }
    @Test void extra08() { assertFalse(test.containsNearbyDuplicate(new int[]{}, 0)); }
    @Test void extra09() { assertTrue(test.containsNearbyDuplicate(new int[]{5,4,3,2,1,5}, 5)); }
    @Test void extra10() { assertFalse(test.containsNearbyDuplicate(new int[]{7}, 100)); }
}
