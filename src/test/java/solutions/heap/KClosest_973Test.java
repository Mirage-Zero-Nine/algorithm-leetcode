package solutions.heap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
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

    @Test
    public void testSinglePoint() {
        assertPointSetEquals(Set.of("5,5"), test.kClosest(new int[][]{{5, 5}}, 1));
    }

    @Test
    public void testOriginPoint() {
        assertPointSetEquals(Set.of("0,0"), test.kClosest(new int[][]{{0, 0}, {1, 1}, {2, 2}}, 1));
    }

    @Test
    public void testNegativeCoordinates() {
        assertPointSetEquals(Set.of("-1,-1"), test.kClosest(new int[][]{{-1, -1}, {-5, -5}, {10, 10}}, 1));
    }

    @Test
    public void testAllSameDistance() {
        int[][] result = test.kClosest(new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}}, 2);
        assertEquals(2, result.length);
    }

    @Test
    public void testKEqualsN() {
        int[][] input = {{1, 2}, {3, 4}, {5, 6}};
        int[][] result = test.kClosest(input, 3);
        assertEquals(3, result.length);
    }

    @Test
    public void testGiantCase() {
        int n = 500;
        int[][] points = new int[n][2];
        for (int i = 0; i < n; i++) {
            points[i] = new int[]{i, i};
        }
        int[][] result = test.kClosest(points, 10);
        assertEquals(10, result.length);
        Set<String> set = toPointSet(result);
        assertTrue(set.contains("0,0"));
        assertTrue(set.contains("1,1"));
    }

    @Test
    public void testLargeCoordinates() {
        int[][] result = test.kClosest(new int[][]{{10000, 10000}, {-10000, -10000}, {1, 1}}, 1);
        assertPointSetEquals(Set.of("1,1"), result);
    }

    @Test
    public void testKOneClosestFromMany() {
        int[][] result = test.kClosest(new int[][]{{3, 3}, {1, 1}, {5, 5}, {-2, -2}, {10, 10}}, 1);
        assertPointSetEquals(Set.of("1,1"), result);
    }

    @Test
    public void testMixNegativePositive() {
        int[][] result = test.kClosest(new int[][]{{-3, 4}, {2, -1}, {-1, -1}, {5, 5}, {-7, 8}}, 3);
        assertEquals(3, result.length);
        Set<String> set = toPointSet(result);
        assertTrue(set.contains("-1,-1"));
        assertTrue(set.contains("2,-1"));
        assertTrue(set.contains("-3,4"));
    }

    @Test
    public void testAllSameDistancePickK() {
        // All points at distance sqrt(2) from origin
        int[][] input = {{1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
        int[][] result = test.kClosest(input, 3);
        assertEquals(3, result.length);
        // Every returned point has squared distance 2
        for (int[] p : result) {
            assertEquals(2, p[0] * p[0] + p[1] * p[1]);
        }
    }

    @Test
    public void testPropertyDistanceOrdering() {
        int[][] input = {{1, 2}, {3, 4}, {-1, 0}, {5, 6}, {0, 1}, {-3, -3}, {7, 7}};
        int k = 3;
        int[][] result = test.kClosest(input, k);
        Set<String> resultSet = toPointSet(result);

        int maxReturnedDist = Arrays.stream(result)
                .mapToInt(p -> p[0] * p[0] + p[1] * p[1]).max().orElse(0);

        // Every non-returned point must have distance >= maxReturnedDist
        for (int[] p : input) {
            if (!resultSet.contains(p[0] + "," + p[1])) {
                int dist = p[0] * p[0] + p[1] * p[1];
                assertTrue(dist >= maxReturnedDist,
                        "Non-returned point " + p[0] + "," + p[1] + " dist=" + dist + " < max returned=" + maxReturnedDist);
            }
        }
    }

    @Test
    public void testPropertyResultLengthEqualsK() {
        int[][] inputs = {{1, 2}, {3, 4}, {5, 6}, {7, 8}, {9, 10}};
        for (int k = 1; k <= inputs.length; k++) {
            int[][] result = test.kClosest(inputs, k);
            assertEquals(k, result.length, "result.length should equal k=" + k);
        }
    }

    @Test
    public void testLargeRandom1000Seed42() {
        Random rng = new Random(42L);
        int n = 1000;
        int k = 50;
        int[][] points = new int[n][2];
        for (int i = 0; i < n; i++) {
            points[i] = new int[]{rng.nextInt(20001) - 10000, rng.nextInt(20001) - 10000};
        }

        int[][] result = test.kClosest(points, k);
        assertEquals(k, result.length);

        // Compute reference: sort all by squared distance, take top k distances
        int[] sortedDists = Arrays.stream(points)
                .mapToInt(p -> p[0] * p[0] + p[1] * p[1])
                .sorted().toArray();
        int kthDist = sortedDists[k - 1];

        // Every returned point must have squared distance <= kth smallest
        for (int[] p : result) {
            int dist = p[0] * p[0] + p[1] * p[1];
            assertTrue(dist <= kthDist,
                    "Returned point dist=" + dist + " exceeds kth dist=" + kthDist);
        }
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
