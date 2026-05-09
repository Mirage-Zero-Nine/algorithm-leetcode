package solution.map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ContainsNearbyDuplicate219Test {

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
}
