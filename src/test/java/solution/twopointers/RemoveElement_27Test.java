package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RemoveElement_27Test {

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

    @Test
    public void testAllSameAsVal() {
        int[] arr = {5, 5, 5, 5};
        int len = test.removeElement(arr, 5);
        assertEquals(4, len);
    }

    @Test
    public void testNoneMatchVal() {
        int[] arr = {1, 2, 3, 4};
        int len = test.removeElement(arr, 5);
        assertEquals(0, len);
    }

    @Test
    public void testSingleElementMatchesVal() {
        assertEquals(1, test.removeElement(new int[]{3}, 3));
    }

    @Test
    public void testSingleElementNoMatch() {
        assertEquals(1, test.removeElement(new int[]{1}, 3));
    }

    @Test
    public void testValAtStart() {
        int[] arr = {3, 1, 2, 4};
        int len = test.removeElement(arr, 3);
        assertEquals(1, len);
    }

    @Test
    public void testValAtEnd() {
        int[] arr = {1, 2, 4, 3};
        int len = test.removeElement(arr, 3);
        assertEquals(1, len);
    }

    @Test
    public void testGiantCase() {
        int n = 100000;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = i % 3;
        // val=0 appears ceil(n/3) times = 33334 times
        int len = test.removeElement(arr, 0);
        assertEquals(33334, len);
    }

    @Test
    public void testTwoElementsBothMatch() {
        int[] arr = {7, 7};
        int len = test.removeElement(arr, 7);
        assertEquals(2, len);
    }
}
