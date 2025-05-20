package solution.array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author BorisMirage
 * Time: 2025/05/20 16:10
 * Created with IntelliJ IDEA
 */


public class IsZeroArray3355Test {
    private final IsZeroArray_3355 test = new IsZeroArray_3355();

    @Test
    public void testExample1() {
        int[] nums = {1, 0, 1};
        int[][] queries = {{0, 2}};
        assertTrue(test.isZeroArray(nums, queries));
    }

    @Test
    public void testExample2() {
        int[] nums = {4, 3, 2, 1};
        int[][] queries = {{1, 3}, {0, 2}};
        assertFalse(test.isZeroArray(nums, queries));
    }

    @Test
    public void testAlreadyZeroArray() {
        int[] nums = {0, 0, 0};
        int[][] queries = {{0, 1}, {1, 2}};
        assertTrue(test.isZeroArray(nums, queries));
    }

    @Test
    public void testSingleQueryPerfectMatch() {
        int[] nums = {1, 1, 1};
        int[][] queries = {{0, 2}};
        assertTrue(test.isZeroArray(nums, queries));
    }

    @Test
    public void testInsufficientQueries() {
        int[] nums = {2, 2};
        int[][] queries = {{0, 1}};
        assertFalse(test.isZeroArray(nums, queries));
    }

    @Test
    public void testMultipleDisjointQueries() {
        int[] nums = {1, 2, 1, 2};
        int[][] queries = {{0, 1}, {2, 3}, {0, 1}, {2, 3}};
        assertTrue(test.isZeroArray(nums, queries));
    }


    @Test
    public void testComplex() {
        int[] nums = {6, 9};
        int[][] queries = {
                {1, 1},
                {1, 1},
                {0, 1},
                {1, 1},
                {1, 1},
                {1, 1},
                {1, 1},
                {1, 1},
                {1, 1},
                {1, 1},
                {1, 1},
                {0, 1}
        };
        assertFalse(test.isZeroArray(nums, queries));
    }
    @Test
    public void testComplex1() {
        int[] nums = {1, 2, 1, 0, 0, 0};
        int[][] queries = {
                {0, 3},
                {2, 4}
        };
        assertFalse(test.isZeroArray(nums, queries));
    }


    @Test
    public void testEmptyNumsAndQueries() {
        int[] nums = {};
        int[][] queries = {};
        assertTrue(test.isZeroArray(nums, queries));
    }

    @Test
    public void testNoEffectQuery() {
        int[] nums = {1, 1};
        int[][] queries = {};  // No query
        assertFalse(test.isZeroArray(nums, queries));
    }
}