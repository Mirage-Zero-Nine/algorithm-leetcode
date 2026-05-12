package solution.array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for {@link SumOfDigits_1085}.
 */
public class SumOfDigits_1085Test {

    private final SumOfDigits_1085 solver = new SumOfDigits_1085();

    @Test
    public void testLeetCodeExample1() {
        // nums = [34,23,1,24,75,33,54,8]
        // min = 1, sum of digits = 1 (odd) → return 0
        int[] nums = {34, 23, 1, 24, 75, 33, 54, 8};
        assertEquals(0, solver.sumOfDigits(nums));
    }

    @Test
    public void testLeetCodeExample2() {
        // nums = [99,77,33,66,55]
        // min = 33, sum of digits = 3+3 = 6 (even) → return 1
        int[] nums = {99, 77, 33, 66, 55};
        assertEquals(1, solver.sumOfDigits(nums));
    }

    @Test
    public void testSingleElementEvenDigitSum() {
        // nums = [24], min = 24, sum = 2+4 = 6 (even) → 1
        int[] nums = {24};
        assertEquals(1, solver.sumOfDigits(nums));
    }

    @Test
    public void testSingleElementOddDigitSum() {
        // nums = [13], min = 13, sum = 1+3 = 4 (even) → 1
        int[] nums = {13};
        assertEquals(1, solver.sumOfDigits(nums));
    }

    @Test
    public void testMinIsSingleDigit() {
        // nums = [1,2,3], min = 1, sum = 1 (odd) → 0
        int[] nums = {1, 2, 3};
        assertEquals(0, solver.sumOfDigits(nums));
    }

    @Test
    public void testMinHasEvenDigitSum() {
        // nums = [10,20,30], min = 10, sum = 1+0 = 1 (odd) → 0
        int[] nums = {10, 20, 30};
        assertEquals(0, solver.sumOfDigits(nums));
    }

    @Test
    public void testMinHasLargeDigitSum() {
        // nums = [999,100,200], min = 100, sum = 1+0+0 = 1 (odd) → 0
        int[] nums = {999, 100, 200};
        assertEquals(0, solver.sumOfDigits(nums));
    }

    @Test
    public void testAllSameElements() {
        // nums = [5,5,5], min = 5, sum = 5 (odd) → 0
        int[] nums = {5, 5, 5};
        assertEquals(0, solver.sumOfDigits(nums));
    }

    @Test
    public void testMinWithEvenDigitSum() {
        // nums = [11,22,33], min = 11, sum = 1+1 = 2 (even) → 1
        int[] nums = {11, 22, 33};
        assertEquals(1, solver.sumOfDigits(nums));
    }
}
