package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TreeAncestor_1483Test {

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

    @Test
    public void testRootNode() {
        TreeAncestor_1483 ta = new TreeAncestor_1483(5, new int[]{-1, 0, 0, 1, 1});
        assertEquals(-1, ta.getKthAncestor(0, 1));
        assertEquals(0, ta.getKthAncestor(0, 0));
    }

    @Test
    public void testKZero() {
        TreeAncestor_1483 ta = new TreeAncestor_1483(5, new int[]{-1, 0, 0, 1, 1});
        assertEquals(3, ta.getKthAncestor(3, 0));
        assertEquals(4, ta.getKthAncestor(4, 0));
    }

    @Test
    public void testDeepChain() {
        int n = 100;
        int[] parent = new int[n];
        parent[0] = -1;
        for (int i = 1; i < n; i++) parent[i] = i - 1;
        TreeAncestor_1483 ta = new TreeAncestor_1483(n, parent);
        assertEquals(0, ta.getKthAncestor(99, 99));
        assertEquals(49, ta.getKthAncestor(99, 50));
        assertEquals(-1, ta.getKthAncestor(99, 100));
    }

    @Test
    public void testBinaryTree() {
        // Complete binary tree: 0 is root, children of i are 2i+1 and 2i+2
        int n = 15;
        int[] parent = new int[n];
        parent[0] = -1;
        for (int i = 1; i < n; i++) parent[i] = (i - 1) / 2;
        TreeAncestor_1483 ta = new TreeAncestor_1483(n, parent);
        assertEquals(0, ta.getKthAncestor(14, 3)); // 14->6->2->0
        assertEquals(6, ta.getKthAncestor(14, 1));
        assertEquals(2, ta.getKthAncestor(14, 2));
    }

    @Test
    public void testKExceedsDepth() {
        TreeAncestor_1483 ta = new TreeAncestor_1483(4, new int[]{-1, 0, 1, 2});
        assertEquals(-1, ta.getKthAncestor(3, 5));
        assertEquals(-1, ta.getKthAncestor(1, 3));
    }

    @Test
    public void testSingleNode() {
        TreeAncestor_1483 ta = new TreeAncestor_1483(1, new int[]{-1});
        assertEquals(0, ta.getKthAncestor(0, 0));
        assertEquals(-1, ta.getKthAncestor(0, 1));
    }

    @Test
    public void testGiantCase() {
        int n = 50000;
        int[] parent = new int[n];
        parent[0] = -1;
        for (int i = 1; i < n; i++) parent[i] = i - 1;
        TreeAncestor_1483 ta = new TreeAncestor_1483(n, parent);
        assertEquals(0, ta.getKthAncestor(n - 1, n - 1));
        assertEquals(n / 2, ta.getKthAncestor(n - 1, n - 1 - n / 2));
        assertEquals(-1, ta.getKthAncestor(n - 1, n));
    }
}
