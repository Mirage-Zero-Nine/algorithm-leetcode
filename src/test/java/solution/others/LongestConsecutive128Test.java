package solution.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LongestConsecutive128Test {

    private final LongestConsecutive_128 test = new LongestConsecutive_128();

    @Test
    public void testHappyCases() {
        assertEquals(4, test.longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));
        assertEquals(9, test.longestConsecutive(new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.longestConsecutive(new int[]{}));
        assertEquals(1, test.longestConsecutive(new int[]{1}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(5, test.longestConsecutive(new int[]{1, 2, 3, 4, 5, 100, 200}));
    }
}
