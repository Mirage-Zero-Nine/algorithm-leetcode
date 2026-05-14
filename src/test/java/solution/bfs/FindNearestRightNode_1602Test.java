package solution.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class FindNearestRightNode_1602Test {

    private final FindNearestRightNode_1602 test = new FindNearestRightNode_1602();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(1);
        TreeNode n2 = new TreeNode(2); TreeNode n3 = new TreeNode(3);
        root.left = n2; root.right = n3;
        assertEquals(3, test.findNearestRightNode(root, n2).val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        TreeNode root = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        root.left = n2;
        assertNull(test.findNearestRightNode(root, n2));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        TreeNode n2 = new TreeNode(2); TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4); TreeNode n5 = new TreeNode(5);
        root.left = n2; root.right = n3;
        n2.left = n4; n2.right = n5;
        assertEquals(5, test.findNearestRightNode(root, n4).val);
    }

    @Test
    public void testRootHasNoRightNeighbor() {
        TreeNode root = new TreeNode(10);
        assertNull(test.findNearestRightNode(root, root));
    }

    @Test
    public void testRightNeighborAtSecondLevel() {
        TreeNode root = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        root.left = n2;
        root.right = n3;
        assertEquals(3, test.findNearestRightNode(root, n2).val);
    }

    @Test
    public void testRightMostAtSecondLevelReturnsNull() {
        TreeNode root = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        root.left = n2;
        root.right = n3;
        assertNull(test.findNearestRightNode(root, n3));
    }

    @Test
    public void testSparseTreeSameLevelNeighbor() {
        TreeNode root = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n5 = new TreeNode(5);
        TreeNode n7 = new TreeNode(7);
        root.left = n2; root.right = n3;
        n2.right = n5; n3.right = n7;
        assertEquals(7, test.findNearestRightNode(root, n5).val);
    }

    @Test
    public void testLastNodeInLevelReturnsNull() {
        TreeNode root = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        root.left = n2; root.right = n3;
        n2.left = n4;
        assertNull(test.findNearestRightNode(root, n4));
    }

    @Test
    public void testDuplicateValueBehaviorUsesValueMatch() {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(2);
        root.left = left;
        root.right = right;
        assertEquals(2, test.findNearestRightNode(root, left).val);
    }

    @Test
    public void testGiantCompleteTree() {
        TreeNode[] nodes = new TreeNode[128];
        for (int i = 1; i < nodes.length; i++) {
            nodes[i] = new TreeNode(i);
        }
        for (int i = 1; i <= 63; i++) {
            nodes[i].left = nodes[i * 2];
            nodes[i].right = nodes[i * 2 + 1];
        }
        TreeNode root = nodes[1];
        assertEquals(65, test.findNearestRightNode(root, nodes[64]).val);
        assertNull(test.findNearestRightNode(root, nodes[127]));
    }
}
