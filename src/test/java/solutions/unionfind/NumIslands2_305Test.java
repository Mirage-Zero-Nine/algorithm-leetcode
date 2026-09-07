package solutions.unionfind;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author BorisMirage
 * Time: 2025/05/11 21:21
 * Created with IntelliJ IDEA
 */

public class NumIslands2_305Test {

    @Test
    public void testSeededLandAdditionsAgainstFreshFloodFillAfterEveryStep() {
        java.util.Random random = new java.util.Random(3052026L);
        for (int sample = 0; sample < 50; sample++) {
            int[][] positions = new int[40][2];
            boolean[][] land = new boolean[5][5];
            java.util.List<Integer> expected = new java.util.ArrayList<>();
            for (int step = 0; step < positions.length; step++) {
                positions[step] = new int[]{random.nextInt(5), random.nextInt(5)};
                land[positions[step][0]][positions[step][1]] = true;
                boolean[][] seen = new boolean[5][5];
                int count = 0;
                for (int row = 0; row < 5; row++)
                    for (int column = 0; column < 5; column++) {
                        if (!land[row][column] || seen[row][column]) continue;
                        count++;
                        java.util.Queue<int[]> queue = new java.util.ArrayDeque<>();
                        queue.add(new int[]{row, column});
                        seen[row][column] = true;
                        while (!queue.isEmpty()) {
                            int[] cell = queue.remove();
                            for (int[] direction : new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}}) {
                                int r = cell[0] + direction[0], c = cell[1] + direction[1];
                                if (r >= 0 && r < 5 && c >= 0 && c < 5 && land[r][c] && !seen[r][c]) {
                                    seen[r][c] = true;
                                    queue.add(new int[]{r, c});
                                }
                            }
                        }
                    }
                expected.add(count);
            }
            assertEquals(expected, test.numIslands2(5, 5, positions), "sequence " + sample);
        }
    }

    @Test
    public void testCenterMergesFourSeparateIslandsExactlyOnce() {
        assertEquals(List.of(1, 2, 3, 4, 1, 1), test.numIslands2(3, 3,
                new int[][]{{0, 1}, {1, 0}, {1, 2}, {2, 1}, {1, 1}, {1, 1}}));
    }

    private NumIslands2_305 test;

    @BeforeEach
    void setUp() {
        test = new NumIslands2_305();
    }

    @Test
    public void testEmptyGrid() {
        int[][] positions = {};
        List<Integer> expected = List.of();
        assertIterableEquals(expected, test.numIslands2(0, 0, positions));
    }

    @Test
    public void testSinglePosition() {
        int[][] positions = {{0, 0}};
        List<Integer> expected = List.of(1);
        assertIterableEquals(expected, test.numIslands2(1, 1, positions));
    }

    @Test
    public void testMultiplePositions() {
        int[][] positions = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
        List<Integer> expected = Arrays.asList(1, 1, 1, 1);
        assertIterableEquals(expected, test.numIslands2(2, 2, positions));
    }

    @Test
    public void testDisconnectedIslands() {
        int[][] positions = {{0, 0}, {0, 1}, {1, 0}, {2, 2}};
        List<Integer> expected = Arrays.asList(1, 1, 1, 2);
        assertIterableEquals(expected, test.numIslands2(3, 3, positions));
    }

    @Test
    public void testLargeGrid() {
        int[][] positions = {{0, 0}, {0, 1}, {1, 0}, {1, 1}, {2, 2}, {3, 3}, {4, 4}};
        List<Integer> expected = Arrays.asList(1, 1, 1, 1, 2, 3, 4);
        assertIterableEquals(expected, test.numIslands2(5, 5, positions));
    }

    @Test
    public void testComplexScenario() {
        int[][] positions = {{0, 0}, {0, 1}, {1, 0}, {1, 1}, {2, 0}, {3, 3}};
        List<Integer> expected = Arrays.asList(1, 1, 1, 1, 1, 2);
        assertIterableEquals(expected, test.numIslands2(4, 4, positions));
    }

    @Test
    public void testZeroRowsAndColumns() {
        int[][] positions = {{0, 0}};
        List<Integer> expected = Arrays.asList();
        assertIterableEquals(expected, test.numIslands2(0, 0, positions));
    }

    @Test
    public void testDuplicatePosition() {
        int[][] positions = {{0, 0}, {0, 0}};
        List<Integer> expected = Arrays.asList(1, 1);
        assertIterableEquals(expected, test.numIslands2(2, 2, positions));
    }

    @Test
    public void testMergingMultipleIslands() {
        // Place corners first, then connect them
        int[][] positions = {{0, 0}, {0, 2}, {0, 1}};
        List<Integer> expected = Arrays.asList(1, 2, 1);
        assertIterableEquals(expected, test.numIslands2(1, 3, positions));
    }

    @Test
    public void testNullPositions() {
        List<Integer> expected = List.of();
        assertIterableEquals(expected, test.numIslands2(3, 3, null));
    }

    @Test
    public void testGiantGrid() {
        int m = 100, n = 100;
        int[][] positions = new int[200][2];
        for (int i = 0; i < 100; i++) {
            positions[i] = new int[]{0, i};
        }
        for (int i = 0; i < 100; i++) {
            positions[100 + i] = new int[]{1, i};
        }
        List<Integer> result = test.numIslands2(m, n, positions);
        assertEquals(200, result.size());
        // After filling row 0 and row 1 fully, everything is connected
        assertEquals(1, result.get(result.size() - 1));
    }
}
