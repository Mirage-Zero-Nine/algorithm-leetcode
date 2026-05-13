package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class CountPairs_1530Test {

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        assertEquals(1, new CountPairs_1530().countPairs(root, 3));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(0, new CountPairs_1530().countPairs(null, 1));
        assertEquals(0, new CountPairs_1530().countPairs(new TreeNode(1), 1));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6); root.right.right = new TreeNode(7);
        assertEquals(2, new CountPairs_1530().countPairs(root, 3));
    }

    @Test
    public void testDistanceOne() {
        // Distance 1: only siblings with same parent (both leaves)
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        // leaves 2 and 3, distance = 2, so distance 1 gives 0
        assertEquals(0, new CountPairs_1530().countPairs(root, 1));
    }

    @Test
    public void testDistanceTwo() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        // leaves 2 and 3, distance = 2
        assertEquals(1, new CountPairs_1530().countPairs(root, 2));
    }

    @Test
    public void testLargeDistance() {
        // With large distance, all leaf pairs should count
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6); root.right.right = new TreeNode(7);
        // 4 leaves: (4,5), (4,6), (4,7), (5,6), (5,7), (6,7) = 6 pairs
        // distances: (4,5)=2, (4,6)=4, (4,7)=4, (5,6)=4, (5,7)=4, (6,7)=2
        // with distance=10, all 6 pairs qualify
        assertEquals(6, new CountPairs_1530().countPairs(root, 10));
    }

    @Test
    public void testSingleLeaf() {
        // Only one leaf, no pairs possible
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        assertEquals(0, new CountPairs_1530().countPairs(root, 5));
    }

    @Test
    public void testAllLeavesAtSameLevel() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6); root.right.right = new TreeNode(7);
        // distance=4: pairs (4,5)=2, (6,7)=2, (4,6)=4, (4,7)=4, (5,6)=4, (5,7)=4
        assertEquals(6, new CountPairs_1530().countPairs(root, 4));
    }

    @Test
    public void testUnbalancedTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(4); // leaf at depth 4
        root.right = new TreeNode(5); // leaf at depth 2
        // Only pair: (4, 5), distance = 4 + 1 = 5... wait let me think
        // 4 is at depth 3 from root (root->2->3->4), 5 is at depth 1 from root
        // distance from 4 to 5 = 3 + 1 = 4 (through root)... 
        // Actually: leaf 4 to root = 3 edges, leaf 5 to root = 1 edge, total = 4
        assertEquals(0, new CountPairs_1530().countPairs(root, 3));
        assertEquals(1, new CountPairs_1530().countPairs(root, 4));
    }

    @Test
    public void testNegativeDistance() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        // distance <= 0 means no pairs
        assertEquals(0, new CountPairs_1530().countPairs(root, 0));
    }

    @Test
    public void testGiantTree() {
        // Build a complete binary tree of depth 5 (31 nodes, 16 leaves)
        TreeNode root = buildComplete(5);
        // With distance=10 (large enough), all leaf pairs count
        // 16 leaves -> C(16,2) = 120 pairs, all within distance 10 (max distance between leaves = 2*5=10)
        int result = new CountPairs_1530().countPairs(root, 10);
        assertEquals(120, result);
    }

    private TreeNode buildComplete(int depth) {
        if (depth == 0) return null;
        TreeNode node = new TreeNode(1);
        node.left = buildComplete(depth - 1);
        node.right = buildComplete(depth - 1);
        return node;
    }
}
