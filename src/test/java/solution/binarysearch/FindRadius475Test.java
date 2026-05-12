package solution.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FindRadius475Test {

    private final FindRadius_475 test = new FindRadius_475();

    @Test
    public void testHappyCases() {
        assertEquals(1, test.findRadius(new int[]{1, 2, 3}, new int[]{2}));
        assertEquals(1, test.findRadius(new int[]{1, 2, 3, 4}, new int[]{1, 4}));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(0, test.findRadius(new int[]{1}, new int[]{1}));
        assertEquals(2, test.findRadius(new int[]{1, 2, 3}, new int[]{1}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(4, test.findRadius(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, new int[]{1, 9}));
    }
}
