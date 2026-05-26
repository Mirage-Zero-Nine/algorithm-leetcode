package solutions.hashmap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for {@link FindLeastNumOfUniqueInts_1481}.
 */
public class FindLeastNumOfUniqueInts_1481Test {

    private final FindLeastNumOfUniqueInts_1481 solver = new FindLeastNumOfUniqueInts_1481();

    @Test
    public void testLeetCodeExample1() {
        // arr = [5,5,4], k = 1
        // freq: 5->2, 4->1. Remove 4 (freq 1), k=0. Left: {5} → 1 unique
        int[] arr = {5, 5, 4};
        assertEquals(1, solver.findLeastNumOfUniqueInts(arr, 1));
    }

    @Test
    public void testLeetCodeExample2() {
        // arr = [4,3,1,1,3,3,2], k = 3
        // freq: 4->1, 3->3, 1->2, 2->1. Sort: [1,1,2,3]
        // Remove 4(freq 1), k=2; remove 2(freq 1), k=1; remove 1(freq 2), k=-1
        // k < 0, so tmp.size() - i + 1 = 4 - 3 + 1 = 2
        int[] arr = {4, 3, 1, 1, 3, 3, 2};
        assertEquals(2, solver.findLeastNumOfUniqueInts(arr, 3));
    }

    @Test
    public void testRemoveAll() {
        // arr = [1,2,3], k = 3
        // freq: 1->1, 2->1, 3->1. Sort: [1,1,1]
        // Remove all, k=0. Left: 0 unique
        int[] arr = {1, 2, 3};
        assertEquals(0, solver.findLeastNumOfUniqueInts(arr, 3));
    }

    @Test
    public void testRemoveNone() {
        // arr = [1,2,3], k = 0
        int[] arr = {1, 2, 3};
        assertEquals(3, solver.findLeastNumOfUniqueInts(arr, 0));
    }

    @Test
    public void testAllSame() {
        // arr = [1,1,1], k = 1
        // freq: 1->3. Remove 1(freq 3), k=-2
        // k < 0, so 1 - 1 + 1 = 1
        int[] arr = {1, 1, 1};
        assertEquals(1, solver.findLeastNumOfUniqueInts(arr, 1));
    }

    @Test
    public void testAllSameExactK() {
        // arr = [1,1,1], k = 3
        // freq: 1->3. Remove 1(freq 3), k=0
        // k == 0, so 1 - 1 = 0
        int[] arr = {1, 1, 1};
        assertEquals(0, solver.findLeastNumOfUniqueInts(arr, 3));
    }

    @Test
    public void testSingleElement() {
        // arr = [1], k = 1
        int[] arr = {1};
        assertEquals(0, solver.findLeastNumOfUniqueInts(arr, 1));
    }

    @Test
    public void testTwoElementsRemoveOne() {
        // arr = [1,2], k = 1
        // freq: 1->1, 2->1. Remove one, left with 1 unique
        int[] arr = {1, 2};
        assertEquals(1, solver.findLeastNumOfUniqueInts(arr, 1));
    }

    @Test
    public void testLargeFreq() {
        // arr = [1,2,3,4,5,6,7,8,9,10], k = 5
        // freq: all 1. Remove 5, left with 5 unique
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        assertEquals(5, solver.findLeastNumOfUniqueInts(arr, 5));
    }

    @Test
    public void testDuplicateHeavy() {
        // arr = [1,1,1,2,2,3], k = 2
        // freq: 3->1, 2->2, 1->3. Sort: [1,2,3]
        // Remove 3(freq 1), k=1; remove 2(freq 2), k=-1
        // k < 0, so 3 - 2 + 1 = 2
        int[] arr = {1, 1, 1, 2, 2, 3};
        assertEquals(2, solver.findLeastNumOfUniqueInts(arr, 2));
    }
}
