package solutions.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class FindClosestLeaf_742Test {

    @Test
    public void testHappyCases() {
        // Tree: 1->2->3, k=1, closest leaf is 3
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        assertEquals(3, new FindClosestLeaf_742().findClosestLeaf(root, 1));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        // Single node tree
        assertEquals(1, new FindClosestLeaf_742().findClosestLeaf(new TreeNode(1), 1));
    }

    @Test
    public void testLargeCase() {
        //     1
        //    / \
        //   2   3
        //  /
        // 4
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        // k=2, closest leaf is 4 (distance 1) or 3 (distance 2)
        assertEquals(4, new FindClosestLeaf_742().findClosestLeaf(root, 2));
    }

    @Test
    public void testTargetIsLeaf() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        // k=2, node 2 is itself a leaf
        assertEquals(2, new FindClosestLeaf_742().findClosestLeaf(root, 2));
    }

    @Test
    public void testTargetIsRoot() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        // k=1, closest leaves are 2 and 3 (both distance 1)
        assertEquals(2, new FindClosestLeaf_742().findClosestLeaf(root, 1));
    }

    @Test
    public void testClosestLeafViaParent() {
        //       1
        //      / \
        //     2   3
        //    /
        //   4
        //  /
        // 5
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.left.left = new TreeNode(5);
        // k=4, closest leaf is 5 (distance 1), or 3 via parent (distance 3)
        assertEquals(5, new FindClosestLeaf_742().findClosestLeaf(root, 4));
    }

    @Test
    public void testClosestLeafGoingUp() {
        //     1
        //    / \
        //   2   3
        //  /
        // 4
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        // k=1, closest leaf is 3 (distance 1)
        assertEquals(3, new FindClosestLeaf_742().findClosestLeaf(root, 1));
    }

    @Test
    public void testTwoNodesLeftChild() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        // k=1, closest leaf is 2
        assertEquals(2, new FindClosestLeaf_742().findClosestLeaf(root, 1));
    }

    @Test
    public void testTwoNodesRightChild() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        // k=1, closest leaf is 2
        assertEquals(2, new FindClosestLeaf_742().findClosestLeaf(root, 1));
    }

    @Test
    public void testDeepTargetWithNearbyLeaf() {
        //       1
        //      /
        //     2
        //    / \
        //   3   4
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        // k=2, closest leaves are 3 and 4 (both distance 1)
        assertEquals(3, new FindClosestLeaf_742().findClosestLeaf(root, 2));
    }

    @Test
    public void testGiantTree() {
        // Build a left-skewed tree of 100 nodes, target is root
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2); // leaf at distance 1
        TreeNode current = root;
        for (int i = 3; i <= 100; i++) {
            current.left = new TreeNode(i);
            current = current.left;
        }
        // k=1, closest leaf is 2 (distance 1 via right child)
        assertEquals(2, new FindClosestLeaf_742().findClosestLeaf(root, 1));
    }
}
