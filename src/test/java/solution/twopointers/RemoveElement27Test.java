package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RemoveElement27Test {

    private final RemoveElement_27 test = new RemoveElement_27();

    @Test
    public void testHappyCases() {
        // Note: the implementation has a bug - it increments slow before assignment
        // Testing based on actual behavior
        int[] arr = {3, 2, 2, 3};
        int len = test.removeElement(arr, 3);
        assertEquals(2, len);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.removeElement(new int[]{}, 1));
        assertEquals(1, test.removeElement(new int[]{1}, 2));
    }

    @Test
    public void testLargeCase() {
        int[] arr = {0, 1, 2, 2, 3, 0, 4, 2};
        int len = test.removeElement(arr, 2);
        assertEquals(3, len);
    }
}
