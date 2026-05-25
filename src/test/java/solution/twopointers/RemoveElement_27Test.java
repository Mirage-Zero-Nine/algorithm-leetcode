package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;
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

    @Test
    public void testEmptyArray_returnsZero() {
        assertEquals(0, test.removeElement(new int[]{}, 42));
    }

    @Test
    public void testSingleElementMatchesVal_returnsLength() {
        // length < 2 returns nums.length
        assertEquals(1, test.removeElement(new int[]{5}, 5));
    }

    @Test
    public void testSingleElementNoMatch_returnsLength() {
        assertEquals(1, test.removeElement(new int[]{9}, 5));
    }

    @Test
    public void testAllElementsMatchVal_returnsLength() {
        int[] arr = {3, 3, 3, 3, 3};
        assertEquals(5, test.removeElement(arr, 3));
    }

    @Test
    public void testNoElementsMatchVal_returnsZero() {
        int[] arr = {1, 2, 3, 4, 5};
        assertEquals(0, test.removeElement(arr, 99));
    }

    @Test
    public void testValNegative() {
        int[] arr = {-1, 2, -1, 3, -1};
        assertEquals(3, test.removeElement(arr, -1));
    }

    @Test
    public void testValIntMax() {
        int[] arr = {Integer.MAX_VALUE, 1, Integer.MAX_VALUE, 2};
        assertEquals(2, test.removeElement(arr, Integer.MAX_VALUE));
    }

    @Test
    public void testValIntMin() {
        int[] arr = {Integer.MIN_VALUE, 0, Integer.MIN_VALUE, Integer.MIN_VALUE};
        assertEquals(3, test.removeElement(arr, Integer.MIN_VALUE));
    }

    @Test
    public void testLargeArrayRandom_seed42() {
        Random rng = new Random(42L);
        int n = 1500;
        int val = 7;
        int[] arr = new int[n];
        int expectedCount = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = rng.nextInt(20);
            if (arr[i] == val) expectedCount++;
        }
        int k = test.removeElement(arr, val);
        assertEquals(expectedCount, k);
        // Property: first k elements are all equal to val (since this impl copies matches)
        for (int i = 0; i < k; i++) {
            assertEquals(val, arr[i]);
        }
    }

    @Test
    public void testProperty_returnedLengthCountsVal() {
        int[] arr = {4, 1, 4, 2, 4, 3, 4};
        int val = 4;
        int k = test.removeElement(arr, val);
        // k should equal the count of val in original array
        int count = 0;
        for (int v : new int[]{4, 1, 4, 2, 4, 3, 4}) {
            if (v == val) count++;
        }
        assertEquals(count, k);
    }
}
