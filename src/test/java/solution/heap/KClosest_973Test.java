package solution.heap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class KClosest_973Test {

    private final KClosest_973 test = new KClosest_973();

    @Test
    public void testHappyCases() {
        assertPointSetEquals(Set.of("1,3", "-2,2"), test.kClosest(new int[][]{{1, 3}, {-2, 2}}, 2));
        assertPointSetEquals(Set.of("3,3", "-2,4"), test.kClosest(new int[][]{{3, 3}, {5, -1}, {-2, 4}}, 2));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertPointSetEquals(Set.of("0,1"), test.kClosest(new int[][]{{0, 1}}, 1));
        assertPointSetEquals(Set.of("0,1", "1,0"), test.kClosest(new int[][]{{0, 1}, {1, 0}, {5, 5}}, 2));
    }

    @Test
    public void testLargeCase() {
        int[][] result = test.kClosest(new int[][]{
            {10, 10}, {1, 1}, {2, 2}, {3, 3}, {4, 4}, {5, 5}, {-1, -1}, {-2, -2}
        }, 4);
        Set<String> set = toPointSet(result);
        assertEquals(4, set.size());
        assertTrue(set.contains("1,1"));
        assertTrue(set.contains("-1,-1"));
        assertTrue(set.contains("2,2"));
        assertTrue(set.contains("-2,-2"));
    }

    private static void assertPointSetEquals(Set<String> expected, int[][] actual) {
        assertEquals(expected, toPointSet(actual));
    }

    private static Set<String> toPointSet(int[][] points) {
        Set<String> set = new HashSet<>();
        for (int[] point : points) {
            set.add(point[0] + "," + point[1]);
        }
        return set;
    }
}
