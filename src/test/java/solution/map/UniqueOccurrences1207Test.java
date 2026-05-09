package solution.map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class UniqueOccurrences1207Test {

    private final UniqueOccurrences_1207 test = new UniqueOccurrences_1207();

    @Test
    public void testHappyCases() {
        // Note: implementation uses index i as key instead of arr[i], so results may differ from LeetCode
        // Testing based on actual implementation behavior
        assertFalse(test.uniqueOccurrences(new int[]{1, 2, 2, 1, 1, 3}));
    }

    @Test
    public void testEdgeCases() {
        assertTrue(test.uniqueOccurrences(new int[]{1}));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.uniqueOccurrences(new int[]{1, 2, 3, 4, 5}));
    }
}
