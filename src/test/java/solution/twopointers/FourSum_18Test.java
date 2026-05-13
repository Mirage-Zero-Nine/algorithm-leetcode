package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Comparator;
import java.util.List;
import org.junit.jupiter.api.Test;

public class FourSum_18Test {

    private final FourSum_18 test = new FourSum_18();

    @Test
    public void testHappyCases() {
        List<List<Integer>> result = test.fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0);
        assertEquals(3, result.size());
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.fourSum(new int[]{1, 2, 3}, 6).size());
        assertEquals(1, test.fourSum(new int[]{0, 0, 0, 0}, 0).size());
    }

    @Test
    public void testLargeCase() {
        List<List<Integer>> result = test.fourSum(new int[]{-3, -2, -1, 0, 0, 1, 2, 3}, 0);
        assertTrue(result.size() > 0);
    }

    @Test
    public void testAdditionalHappyCases() {
        assertQuadrupletsEqual(
            List.of(List.of(2, 2, 2, 2)),
            test.fourSum(new int[]{2, 2, 2, 2, 2}, 8)
        );
        assertQuadrupletsEqual(
            List.of(List.of(-3, -1, 2, 4), List.of(-3, 0, 1, 4), List.of(-1, 0, 1, 2)),
            test.fourSum(new int[]{-3, -1, 0, 1, 2, 4}, 2)
        );
    }

    @Test
    public void testAdditionalEdgeCases() {
        assertEquals(0, test.fourSum(new int[]{}, 0).size());
        assertEquals(0, test.fourSum(new int[]{1, 1, 1, 1}, 10).size());
        assertQuadrupletsEqual(
            List.of(List.of(-2, -1, 1, 2)),
            test.fourSum(new int[]{-2, -1, 1, 2}, 0)
        );
    }

    @Test
    public void testAdditionalGiantCase() {
        List<List<Integer>> result = test.fourSum(new int[]{-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5}, 0);
        assertTrue(result.size() >= 20);
    }

    @Test
    public void testNegativeTarget() {
        List<List<Integer>> result = test.fourSum(new int[]{-5, -4, -3, -2, -1}, -14);
        // -5 + -4 + -3 + -2 = -14
        assertEquals(1, result.size());
        assertQuadrupletsEqual(List.of(List.of(-5, -4, -3, -2)), result);
    }

    @Test
    public void testLargeTarget() {
        List<List<Integer>> result = test.fourSum(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 30);
        // multiple quadruplets sum to 30
        assertTrue(result.size() > 0);
        for (List<Integer> quad : result) {
            assertEquals(30, quad.stream().mapToInt(Integer::intValue).sum());
        }
    }

    @Test
    public void testAllZeros() {
        List<List<Integer>> result = test.fourSum(new int[]{0, 0, 0, 0, 0, 0}, 0);
        assertEquals(1, result.size());
    }

    @Test
    public void testDuplicateElements() {
        List<List<Integer>> result = test.fourSum(new int[]{1, 1, 1, 1, 1, 1}, 4);
        assertEquals(1, result.size());
        assertEquals(List.of(1, 1, 1, 1), result.get(0));
    }

    private void assertQuadrupletsEqual(List<List<Integer>> expected, List<List<Integer>> actual) {
        Comparator<List<Integer>> cmp = Comparator
            .comparing((List<Integer> quad) -> quad.get(0))
            .thenComparing(quad -> quad.get(1))
            .thenComparing(quad -> quad.get(2))
            .thenComparing(quad -> quad.get(3));
        assertEquals(expected.stream().sorted(cmp).toList(), actual.stream().sorted(cmp).toList());
    }
}
