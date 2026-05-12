package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class SortColors75Test {

    private final SortColors_75 test = new SortColors_75();

    @Test
    public void testHappyCases() {
        int[] arr = {2, 0, 2, 1, 1, 0};
        test.sortColors(arr);
        assertArrayEquals(new int[]{0, 0, 1, 1, 2, 2}, arr);
    }

    @Test
    public void testEdgeCases() {
        int[] arr = {0};
        test.sortColors(arr);
        assertArrayEquals(new int[]{0}, arr);
        int[] arr2 = {1, 0};
        test.sortColors(arr2);
        assertArrayEquals(new int[]{0, 1}, arr2);
    }

    @Test
    public void testLargeCase() {
        int[] arr = {2, 2, 2, 1, 1, 1, 0, 0, 0};
        test.sortColors(arr);
        assertArrayEquals(new int[]{0, 0, 0, 1, 1, 1, 2, 2, 2}, arr);
    }
}
