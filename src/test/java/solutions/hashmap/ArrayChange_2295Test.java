package solutions.hashmap;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2022/10/07 19:11
 * Created with IntelliJ IDEA
 */

public class ArrayChange_2295Test {

    private final ArrayChange_2295 test = new ArrayChange_2295();

    @Test
    public void test() {
        int[] nums = new int[]{1, 2, 4, 6};
        int[][] operations = new int[][]{{1, 3}, {4, 7}, {6, 1}};
        int[] expected = new int[]{3, 2, 7, 1};
        assertArrayEquals(expected, test.arrayChange(nums, operations));

        nums = new int[]{1, 2};
        operations = new int[][]{{1, 3}, {2, 1}, {3, 2}};
        expected = new int[]{2, 1};
        assertArrayEquals(expected, test.arrayChange(nums, operations));
    }

    @Test
    public void testHappyCases() {
        assertArrayEquals(new int[]{9, 7, 5},
                test.arrayChange(new int[]{1, 3, 5}, new int[][]{{1, 9}, {3, 7}, {5, 5}}));
        assertArrayEquals(new int[]{8},
                test.arrayChange(new int[]{4}, new int[][]{{4, 8}}));
        assertArrayEquals(new int[]{10, 20, 30, 40},
                test.arrayChange(new int[]{10, 20, 30, 40}, new int[][]{}));
    }

    @Test
    public void testEdgeAndChainedCases() {
        assertArrayEquals(new int[]{100, 200, 300},
                test.arrayChange(new int[]{1, 2, 3}, new int[][]{{1, 100}, {2, 200}, {3, 300}}));
        assertArrayEquals(new int[]{6, 5},
                test.arrayChange(new int[]{1, 2}, new int[][]{{1, 3}, {3, 4}, {4, 6}, {2, 5}}));
        assertArrayEquals(new int[]{9, 2, 4, 6},
                test.arrayChange(new int[]{1, 2, 4, 6}, new int[][]{{1, 3}, {3, 9}}));
    }

    @Test
    public void testGiantCase() {
        int[] nums = new int[200];
        int[][] operations = new int[200][2];
        for (int i = 0; i < 200; i++) {
            nums[i] = i + 1;
            operations[i][0] = i + 1;
            operations[i][1] = 10_000 + i;
        }
        int[] expected = new int[200];
        for (int i = 0; i < 200; i++) {
            expected[i] = 10_000 + i;
        }
        assertArrayEquals(expected, test.arrayChange(nums, operations));
    }

    @Test
    public void testSingleElement() {
        assertArrayEquals(new int[]{99}, test.arrayChange(new int[]{1}, new int[][]{{1, 99}}));
    }

    @Test
    public void testNoOperations() {
        assertArrayEquals(new int[]{5, 10, 15}, test.arrayChange(new int[]{5, 10, 15}, new int[][]{}));
    }

    @Test
    public void testChainedReplacements() {
        // 1->2, 2->3, 3->4
        assertArrayEquals(new int[]{4}, test.arrayChange(new int[]{1}, new int[][]{{1, 2}, {2, 3}, {3, 4}}));
    }

    @Test
    public void testMultipleElementsPartialOps() {
        assertArrayEquals(new int[]{99, 2, 3}, test.arrayChange(new int[]{1, 2, 3}, new int[][]{{1, 99}}));
    }

    @Test
    public void testLargeValues() {
        assertArrayEquals(new int[]{1000000}, test.arrayChange(new int[]{999999}, new int[][]{{999999, 1000000}}));
    }

    @Test
    public void testSwapLikeOperations() {
        // Replace first element, then replace second element
        assertArrayEquals(new int[]{100, 200},
            test.arrayChange(new int[]{1, 2}, new int[][]{{1, 100}, {2, 200}}));
    }
}
