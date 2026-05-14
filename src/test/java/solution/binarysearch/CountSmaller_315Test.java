package solution.binarysearch;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2022/11/10 23:17
 * Created with IntelliJ IDEA
 */

public class CountSmaller_315Test {

    private final CountSmaller_315 test = new CountSmaller_315();

    @Test
    public void test() {
        assertIterableEquals(Lists.newArrayList(2, 1, 1, 0), test.countSmaller(new int[]{5, 2, 6, 1}));
        assertIterableEquals(Lists.newArrayList(0, 0), test.countSmaller(new int[]{-1, -1}));
    }

    @Test
    public void testEmpty() {
        assertIterableEquals(Lists.newArrayList(0), test.countSmaller(new int[]{-1}));
    }

    @Test
    public void testBrutal() {
        assertIterableEquals(Lists.newArrayList(2, 1, 1, 0), test.brutal(new int[]{5, 2, 6, 1}));
        assertIterableEquals(Lists.newArrayList(0, 0), test.brutal(new int[]{-1, -1}));
    }

    @Test
    public void testBrutalEmpty() {
        assertIterableEquals(Lists.newArrayList(0), test.brutal(new int[]{-1}));
    }

    @Test
    public void testAscendingArray() {
        assertIterableEquals(Lists.newArrayList(0, 0, 0, 0), test.countSmaller(new int[]{1, 2, 3, 4}));
    }

    @Test
    public void testDescendingArray() {
        assertIterableEquals(Lists.newArrayList(3, 2, 1, 0), test.countSmaller(new int[]{4, 3, 2, 1}));
    }

    @Test
    public void testWithDuplicates() {
        assertIterableEquals(Lists.newArrayList(0, 0, 0), test.countSmaller(new int[]{2, 2, 2}));
    }

    @Test
    public void testMixedValuesAgainstBrutal() {
        int[] nums = new int[]{3, -1, 2, -1, 0};
        assertIterableEquals(test.brutal(nums), test.countSmaller(nums));
    }

    @Test
    public void testBrutalEmptyArray() {
        assertIterableEquals(Lists.newArrayList(), test.brutal(new int[]{}));
        assertIterableEquals(Lists.newArrayList(), test.countSmaller(new int[]{}));
    }

    @Test
    public void testGiantCaseAgainstBrutal() {
        int[] nums = new int[200];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = (i * 37) % 97 - 50;
        }
        assertIterableEquals(test.brutal(nums), test.countSmaller(nums));
    }
}
