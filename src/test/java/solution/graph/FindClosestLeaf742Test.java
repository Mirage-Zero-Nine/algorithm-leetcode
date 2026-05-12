package solution.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class FindClosestLeaf742Test {

    private final FindClosestLeaf_742 test = new FindClosestLeaf_742();

    @Test
    public void testHappyCases() {
        // Tree: 1->2->3, k=1, closest leaf is 3
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        assertEquals(3, test.findClosestLeaf(root, 1));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        // Single node tree
        assertEquals(1, test.findClosestLeaf(new TreeNode(1), 1));
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
        assertEquals(4, test.findClosestLeaf(root, 2));
    }
}
