package solution.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FindMin154Test {

    private final FindMin_154 test = new FindMin_154();

    @Test
    public void testHappyCases() {
        assertEquals(0, test.findMin(new int[]{2, 2, 2, 0, 1}));
        assertEquals(0, test.findMin(new int[]{2, 5, 6, 7, 0, 0, 1, 2}));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.findMin(new int[]{1}));
        assertEquals(0, test.findMin(new int[]{0, 0, 0, 0, 0, 2}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(1, test.findMin(new int[]{3, 3, 3, 3, 1, 3, 3}));
    }
}
