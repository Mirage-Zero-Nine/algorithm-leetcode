package solution.array;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class NextPermutation_31Test {
    private final NextPermutation_31 solution = new NextPermutation_31();

    @Test
    void testBasic() {
        int[] nums = {1, 2, 3};
        solution.nextPermutation(nums);
        assertArrayEquals(new int[]{1, 3, 2}, nums);
    }

    @Test
    void testDescending() {
        int[] nums = {3, 2, 1};
        solution.nextPermutation(nums);
        assertArrayEquals(new int[]{1, 2, 3}, nums);
    }

    @Test
    void testSingleElement() {
        int[] nums = {1};
        solution.nextPermutation(nums);
        assertArrayEquals(new int[]{1}, nums);
    }

    @Test
    void testTwoElements() {
        int[] nums = {1, 2};
        solution.nextPermutation(nums);
        assertArrayEquals(new int[]{2, 1}, nums);
    }

    @Test
    void testComplex() {
        int[] nums = {1, 3, 2};
        solution.nextPermutation(nums);
        assertArrayEquals(new int[]{2, 1, 3}, nums);
    }
}
