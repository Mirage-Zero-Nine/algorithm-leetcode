package solution.findkth;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KSmallestPairs_373Test {
    private final KSmallestPairs_373 solver = new KSmallestPairs_373();

    @Test public void testBasic() {
        List<List<Integer>> res = solver.kSmallestPairs(new int[]{1, 7, 11}, new int[]{2, 4, 6}, 3);
        assertEquals(3, res.size());
        // Smallest pairs: [1,2],[1,4],[1,6]
        assertTrue(res.contains(List.of(1, 2)));
        assertTrue(res.contains(List.of(1, 4)));
        assertTrue(res.contains(List.of(1, 6)));
    }

    @Test public void testKLarger() {
        // Only 4 pairs available but k=10
        List<List<Integer>> res = solver.kSmallestPairs(new int[]{1, 1}, new int[]{2, 2}, 10);
        assertEquals(4, res.size());
    }

    @Test public void testSingleElementArrays() {
        List<List<Integer>> res = solver.kSmallestPairs(new int[]{1, 2}, new int[]{3}, 3);
        assertEquals(2, res.size());
        assertTrue(res.contains(List.of(1, 3)));
        assertTrue(res.contains(List.of(2, 3)));
    }

    @Test public void testEmpty() {
        List<List<Integer>> res = solver.kSmallestPairs(new int[]{}, new int[]{1, 2}, 3);
        assertTrue(res.isEmpty());
    }

    @Test public void testKZero() {
        List<List<Integer>> res = solver.kSmallestPairs(new int[]{1}, new int[]{1}, 0);
        assertTrue(res.isEmpty());
    }

    @Test public void testNullFirst() {
        List<List<Integer>> res = solver.kSmallestPairs(null, new int[]{1, 2}, 3);
        assertTrue(res.isEmpty());
    }

    @Test public void testNullSecond() {
        List<List<Integer>> res = solver.kSmallestPairs(new int[]{1, 2}, null, 3);
        assertTrue(res.isEmpty());
    }

    @Test public void testBothSingleElement() {
        List<List<Integer>> res = solver.kSmallestPairs(new int[]{5}, new int[]{10}, 1);
        assertEquals(1, res.size());
        assertEquals(List.of(5, 10), res.get(0));
    }

    @Test public void testNegativeNumbers() {
        List<List<Integer>> res = solver.kSmallestPairs(new int[]{-5, -3, 0}, new int[]{-2, 1, 4}, 3);
        assertEquals(3, res.size());
        assertEquals(List.of(-5, -2), res.get(0));
    }

    @Test public void testKEqualsOne() {
        List<List<Integer>> res = solver.kSmallestPairs(new int[]{1, 2, 3}, new int[]{4, 5, 6}, 1);
        assertEquals(1, res.size());
        assertEquals(List.of(1, 4), res.get(0));
    }

    @Test public void testGiantCase() {
        int[] nums1 = new int[500];
        int[] nums2 = new int[500];
        for (int i = 0; i < 500; i++) {
            nums1[i] = i;
            nums2[i] = i;
        }
        List<List<Integer>> res = solver.kSmallestPairs(nums1, nums2, 100);
        assertEquals(100, res.size());
        // First pair should be (0,0)
        assertEquals(List.of(0, 0), res.get(0));
    }
}
