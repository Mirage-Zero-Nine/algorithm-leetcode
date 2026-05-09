package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TreeAncestor1483Test {

    @Test
    public void testHappyCases() {
        TreeAncestor_1483 ta = new TreeAncestor_1483(7, new int[]{-1, 0, 0, 1, 1, 2, 2});
        assertEquals(1, ta.getKthAncestor(3, 1));
        assertEquals(0, ta.getKthAncestor(5, 2));
        assertEquals(-1, ta.getKthAncestor(6, 3));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        TreeAncestor_1483 ta = new TreeAncestor_1483(3, new int[]{-1, 0, 1});
        assertEquals(-1, ta.getKthAncestor(0, 1));
        assertEquals(0, ta.getKthAncestor(1, 1));
    }

    @Test
    public void testLargeCase() {
        int n = 10;
        int[] parent = new int[n];
        parent[0] = -1;
        for (int i = 1; i < n; i++) parent[i] = i - 1;
        TreeAncestor_1483 ta = new TreeAncestor_1483(n, parent);
        assertEquals(0, ta.getKthAncestor(9, 9));
        assertEquals(-1, ta.getKthAncestor(9, 10));
    }
}
