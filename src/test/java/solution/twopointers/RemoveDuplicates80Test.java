package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RemoveDuplicates80Test {

    private final RemoveDuplicates_80 test = new RemoveDuplicates_80();

    @Test
    public void testHappyCases() {
        assertEquals(5, test.removeDuplicates(new int[]{1, 1, 1, 2, 2, 3}));
        assertEquals(7, test.removeDuplicates(new int[]{0, 0, 1, 1, 1, 1, 2, 3, 3}));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, test.removeDuplicates(new int[]{1}));
        assertEquals(2, test.removeDuplicates(new int[]{1, 1}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(6, test.removeDuplicates(new int[]{1, 1, 1, 2, 2, 2, 3, 3, 3}));
    }
}
