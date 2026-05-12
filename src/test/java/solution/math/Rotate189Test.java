package solution.math;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class Rotate189Test {

    private final Rotate_189 test = new Rotate_189();

    @Test
    public void testHappyCases() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        test.rotate(arr, 3);
        assertArrayEquals(new int[]{5, 6, 7, 1, 2, 3, 4}, arr);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        int[] arr = {1};
        test.rotate(arr, 5);
        assertArrayEquals(new int[]{1}, arr);

        int[] arr2 = {1, 2};
        test.rotate(arr2, 2);
        assertArrayEquals(new int[]{1, 2}, arr2);
    }

    @Test
    public void testLargeCase() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        test.rotate(arr, 4);
        assertArrayEquals(new int[]{7, 8, 9, 10, 1, 2, 3, 4, 5, 6}, arr);
    }
}
