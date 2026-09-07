package solutions.unionfind;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2025/05/07 23:16
 * Created with IntelliJ IDEA
 */
public class CountComponents_323Test {

    @Test
    public void testAllFourVertexGraphsAgainstReachability() {

        int[][] possible = {{0, 1}, {0, 2}, {0, 3}, {1, 2}, {1, 3}, {2, 3}};
        for (int mask = 0; mask < 64; mask++) {
            java.util.List<int[]> edges = new java.util.ArrayList<>();
            boolean[][] connected = new boolean[4][4];
            for (int i = 0; i < 4; i++) connected[i][i] = true;
            for (int bit = 0; bit < 6; bit++) {
                if ((mask & (1 << bit)) != 0) {
                    edges.add(possible[bit].clone());
                    connected[possible[bit][0]][possible[bit][1]] = true;
                    connected[possible[bit][1]][possible[bit][0]] = true;
                }
            }
            for (int via = 0; via < 4; via++)
                for (int from = 0; from < 4; from++)
                    for (int to = 0; to < 4; to++)
                        connected[from][to] |= connected[from][via] && connected[via][to];
            int components = 0;
            for (int node = 0; node < 4; node++) {
                boolean firstInComponent = true;
                for (int prior = 0; prior < node; prior++) firstInComponent &= !connected[node][prior];
                if (firstInComponent) components++;
            }

            assertEquals(components, test.countComponents(4, edges.toArray(new int[0][])), "graph " + mask);
        }
    }


    private CountComponents_323 test;

    @BeforeEach
    void setUp() {
        test = new CountComponents_323();
    }

    @Test
    public void testBasic() {
        int n = 5;
        int[][] edges = {
                {0, 1},
                {1, 2},
                {3, 4}
        };
        assertEquals(2, test.countComponents(n, edges));
    }

    @Test
    public void testAllConnected() {
        int n = 4;
        int[][] edges = {
                {0, 1},
                {1, 2},
                {2, 3}
        };
        assertEquals(1, test.countComponents(n, edges));
    }

    @Test
    public void testDisconnected() {
        int n = 4;
        int[][] edges = {};
        assertEquals(4, test.countComponents(n, edges));
    }

    @Test
    public void testSingleNode() {
        int n = 1;
        int[][] edges = {};
        assertEquals(1, test.countComponents(n, edges));
    }

    @Test
    public void testZeroNodes() {
        int n = 0;
        int[][] edges = {};
        assertEquals(0, test.countComponents(n, edges));
    }

    @Test
    public void testCycle() {
        int n = 5;
        int[][] edges = {
                {0, 1},
                {1, 2},
                {2, 0},
                {3, 4}
        };
        assertEquals(2, test.countComponents(n, edges));
    }

    @Test
    public void testMultipleComponents() {
        int n = 6;
        int[][] edges = {
                {0, 1},
                {2, 3},
                {4, 5}
        };
        assertEquals(3, test.countComponents(n, edges));
    }

    @Test
    public void testTwoNodes() {
        assertEquals(1, test.countComponents(2, new int[][]{{0, 1}}));
    }

    @Test
    public void testStarGraph() {
        int[][] edges = {{0, 1}, {0, 2}, {0, 3}, {0, 4}};
        assertEquals(1, test.countComponents(5, edges));
    }

    @Test
    public void testGiantLinearChain() {
        int n = 1000;
        int[][] edges = new int[n - 1][2];
        for (int i = 0; i < n - 1; i++) {
            edges[i] = new int[]{i, i + 1};
        }
        assertEquals(1, test.countComponents(n, edges));
    }
}
