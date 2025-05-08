package solution.unionfind;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2025/05/07 23:16
 * Created with IntelliJ IDEA
 */
public class CountComponents323Test {

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
}
