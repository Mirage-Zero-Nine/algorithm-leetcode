package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class MoveZeroes283Test {

    private final MoveZeroes_283 test = new MoveZeroes_283();

    @Test
    public void testHappyCases() {
        int[] arr = {0, 1, 0, 3, 12};
        test.moveZeroes(arr);
        assertArrayEquals(new int[]{1, 3, 12, 0, 0}, arr);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        int[] arr = {0};
        test.moveZeroes(arr);
        assertArrayEquals(new int[]{0}, arr);
        int[] arr2 = {1};
        test.moveZeroes(arr2);
        assertArrayEquals(new int[]{1}, arr2);
    }

    @Test
    public void testLargeCase() {
        int[] arr = {0, 0, 0, 1, 2, 3};
        test.moveZeroes(arr);
        assertArrayEquals(new int[]{1, 2, 3, 0, 0, 0}, arr);
    }
}
