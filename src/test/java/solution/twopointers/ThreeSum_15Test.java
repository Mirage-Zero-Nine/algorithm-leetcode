package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Comparator;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ThreeSum_15Test {

    private final ThreeSum_15 test = new ThreeSum_15();

    @Test
    public void testHappyCases() {
        List<List<Integer>> result = test.threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        assertEquals(2, result.size());
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.threeSum(new int[]{}).size());
        assertEquals(0, test.threeSum(new int[]{0}).size());
        assertEquals(1, test.threeSum(new int[]{0, 0, 0}).size());
    }

    @Test
    public void testLargeCase() {
        List<List<Integer>> result = test.threeSum(new int[]{-4, -2, -2, -2, 0, 1, 2, 2, 2, 3, 3, 4, 4, 6, 6});
        assertTrue(result.size() > 0);
    }

    @Test
    public void testAdditionalHappyCases() {
        assertTripletsEqual(
            List.of(List.of(-2, 0, 2), List.of(-2, 1, 1)),
            test.threeSum(new int[]{-2, 0, 1, 1, 2})
        );
        assertTripletsEqual(
            List.of(List.of(-1, -1, 2), List.of(-1, 0, 1)),
            test.threeSum(new int[]{-1, -1, 0, 1, 2})
        );
    }

    @Test
    public void testAdditionalEdgeCases() {
        assertEquals(0, test.threeSum(new int[]{1, 2, 3, 4}).size());
        assertEquals(0, test.threeSum(new int[]{-4, -3, -2}).size());
        assertTripletsEqual(List.of(List.of(0, 0, 0)), test.threeSum(new int[]{0, 0, 0, 0}));
    }

    @Test
    public void testAdditionalGiantCase() {
        List<List<Integer>> result = test.threeSum(new int[]{-6, -4, -2, 0, 2, 4, 6, -1, 1, -3, 3, -5, 5});
        assertTrue(result.size() >= 10);
    }

    @Test
    public void testAllPositive() {
        assertEquals(0, test.threeSum(new int[]{1, 2, 3, 4, 5, 6}).size());
    }

    @Test
    public void testDuplicateElements() {
        List<List<Integer>> result = test.threeSum(new int[]{0, 0, 0, 0, 0});
        assertEquals(1, result.size());
        assertEquals(List.of(0, 0, 0), result.get(0));
    }

    @Test
    public void testTwoElements() {
        assertEquals(0, test.threeSum(new int[]{-1, 1}).size());
    }

    @Test
    public void testGiantWithManyDuplicates() {
        int[] nums = new int[300];
        for (int i = 0; i < 100; i++) nums[i] = -1;
        for (int i = 100; i < 200; i++) nums[i] = 0;
        for (int i = 200; i < 300; i++) nums[i] = 1;
        List<List<Integer>> result = test.threeSum(nums);
        // should contain [-1, 0, 1] and [0, 0, 0]
        assertEquals(2, result.size());
    }

    private void assertTripletsEqual(List<List<Integer>> expected, List<List<Integer>> actual) {
        Comparator<List<Integer>> cmp = Comparator
            .comparing((List<Integer> triplet) -> triplet.get(0))
            .thenComparing(triplet -> triplet.get(1))
            .thenComparing(triplet -> triplet.get(2));
        assertEquals(expected.stream().sorted(cmp).toList(), actual.stream().sorted(cmp).toList());
    }
}
