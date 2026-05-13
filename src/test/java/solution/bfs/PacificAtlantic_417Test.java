package solution.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class PacificAtlantic_417Test {

    private final PacificAtlantic_417 test = new PacificAtlantic_417();

    @Test
    public void testHappyCases() {
        List<int[]> result = test.pacificAtlantic(new int[][]{
            {1, 2, 2, 3, 5}, {3, 2, 3, 4, 4}, {2, 4, 5, 3, 1}, {6, 7, 1, 4, 5}, {5, 1, 1, 2, 4}
        });
        assertEquals(7, result.size());
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.pacificAtlantic(new int[][]{}).size());
        List<int[]> result = test.pacificAtlantic(new int[][]{{1}});
        assertEquals(1, result.size());
    }

    @Test
    public void testLargeCase() {
        List<int[]> result = test.pacificAtlantic(new int[][]{{1, 1}, {1, 1}});
        assertEquals(4, result.size());
    }

    @Test
    public void testSingleRowAllReachBoth() {
        List<int[]> result = test.pacificAtlantic(new int[][]{{1, 2, 3, 4}});
        assertEquals(4, result.size());
        assertContains(result, 0, 0);
        assertContains(result, 0, 3);
    }

    @Test
    public void testSingleColumnAllReachBoth() {
        List<int[]> result = test.pacificAtlantic(new int[][]{
                {4},
                {3},
                {2},
                {1}
        });
        assertEquals(4, result.size());
        assertContains(result, 0, 0);
        assertContains(result, 3, 0);
    }

    @Test
    public void testStrictlyIncreasingGrid() {
        List<int[]> result = test.pacificAtlantic(new int[][]{
                {1, 2, 3},
                {2, 3, 4},
                {3, 4, 5}
        });
        Set<String> s = asSet(result);
        assertTrue(s.contains("0,2"));
        assertTrue(s.contains("1,2"));
        assertTrue(s.contains("2,0"));
        assertTrue(s.contains("2,1"));
        assertTrue(s.contains("2,2"));
        assertEquals(5, s.size());
    }

    @Test
    public void testStrictlyDecreasingGrid() {
        List<int[]> result = test.pacificAtlantic(new int[][]{
                {9, 8, 7},
                {8, 7, 6},
                {7, 6, 5}
        });
        Set<String> s = asSet(result);
        assertTrue(s.contains("0,0"));
        assertTrue(s.contains("0,1"));
        assertTrue(s.contains("0,2"));
        assertTrue(s.contains("1,0"));
        assertTrue(s.contains("2,0"));
        assertEquals(5, s.size());
    }

    @Test
    public void testPlateauGridAllCellsReachBoth() {
        List<int[]> result = test.pacificAtlantic(new int[][]{
                {5, 5, 5},
                {5, 5, 5},
                {5, 5, 5}
        });
        assertEquals(9, result.size());
    }

    @Test
    public void testMixedHeightsSpecificCells() {
        List<int[]> result = test.pacificAtlantic(new int[][]{
                {10, 1, 10},
                {1, 1, 1},
                {10, 1, 10}
        });
        Set<String> s = asSet(result);
        assertTrue(s.contains("0,0"));
        assertTrue(s.contains("0,2"));
        assertTrue(s.contains("2,0"));
        assertTrue(s.contains("2,2"));
        assertEquals(9, s.size());
    }

    @Test
    public void testGiantGrid() {
        int n = 30;
        int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = i + j;
            }
        }
        List<int[]> result = test.pacificAtlantic(grid);
        assertEquals(59, result.size());
    }

    private void assertContains(List<int[]> result, int x, int y) {
        for (int[] p : result) {
            if (p[0] == x && p[1] == y) {
                return;
            }
        }
        throw new AssertionError("Missing coordinate: " + x + "," + y);
    }

    private Set<String> asSet(List<int[]> result) {
        Set<String> set = new HashSet<>();
        for (int[] p : result) {
            set.add(p[0] + "," + p[1]);
        }
        return set;
    }
}
