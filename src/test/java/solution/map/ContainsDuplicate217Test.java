package solution.map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ContainsDuplicate217Test {

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
}
