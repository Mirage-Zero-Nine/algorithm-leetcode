package solution.map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2022/10/07 19:11
 * Created with IntelliJ IDEA
 */

public class ArrayChange2295Test {

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
}