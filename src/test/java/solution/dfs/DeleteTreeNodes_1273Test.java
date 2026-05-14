package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeleteTreeNodes_1273Test {

    private final DeleteTreeNodes_1273 test = new DeleteTreeNodes_1273();

    @Test
    public void testHappyCases() {
        assertEquals(2, test.deleteTreeNodes(7, new int[]{-1, 0, 0, 1, 2, 2, 2}, new int[]{1, -2, 4, 0, -2, -1, -1}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.deleteTreeNodes(0, null, null));
        assertEquals(0, test.deleteTreeNodes(1, new int[]{-1}, new int[]{0}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(3, test.deleteTreeNodes(5, new int[]{-1, 0, 0, 1, 1}, new int[]{-672, 441, 18, 0, 0}));
    }

    @Test
    public void testSingleNodeNonZero() {
        assertEquals(1, test.deleteTreeNodes(1, new int[]{-1}, new int[]{5}));
    }

    @Test
    public void testEntireTreeSumZero() {
        // root=1, child=-1 -> sum=0
        assertEquals(0, test.deleteTreeNodes(2, new int[]{-1, 0}, new int[]{1, -1}));
    }

    @Test
    public void testNoSubtreeDeleted() {
        // All positive values, no subtree sums to 0
        assertEquals(4, test.deleteTreeNodes(4, new int[]{-1, 0, 0, 1}, new int[]{1, 2, 3, 4}));
    }

    @Test
    public void testLeafZero() {
        // Only a leaf has value 0 -> that leaf is removed
        assertEquals(2, test.deleteTreeNodes(3, new int[]{-1, 0, 0}, new int[]{1, 2, 0}));
    }

    @Test
    public void testSubtreeSumZeroNotLeaf() {
        // node 1 has children 3,4 with values that sum to 0 with node 1
        // tree: 0->1, 0->2, 1->3, 1->4; values: [10, 5, 3, -2, -3]
        // node1 subtree sum: 5 + (-2) + (-3) = 0 -> remove nodes 1,3,4
        assertEquals(2, test.deleteTreeNodes(5, new int[]{-1, 0, 0, 1, 1}, new int[]{10, 5, 3, -2, -3}));
    }

    @Test
    public void testMultipleZeroSubtrees() {
        // Two independent subtrees sum to 0
        // tree: 0->1, 0->2; values: [5, 0, 0]
        assertEquals(1, test.deleteTreeNodes(3, new int[]{-1, 0, 0}, new int[]{5, 0, 0}));
    }

    @Test
    public void testNegativeValues() {
        // All negative, no subtree sums to 0
        assertEquals(3, test.deleteTreeNodes(3, new int[]{-1, 0, 0}, new int[]{-1, -2, -3}));
    }

    @Test
    public void testGiantTree() {
        // Build a large tree: 1000 nodes, linear chain, all values 1 except last = -(999)
        int n = 1000;
        int[] parent = new int[n];
        int[] value = new int[n];
        parent[0] = -1;
        value[0] = 1;
        for (int i = 1; i < n; i++) {
            parent[i] = i - 1;
            value[i] = 1;
        }
        // Sum of entire tree = 1000, no subtree sums to 0
        assertEquals(1000, test.deleteTreeNodes(n, parent, value));
    }
}
