package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class DelNodes_1110Test {

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6); root.right.right = new TreeNode(7);
        List<TreeNode> result = new DelNodes_1110().delNodes(root, new int[]{3, 5});
        assertEquals(3, result.size());
    }

    @Test
    public void testEdgeCases() {
        assertEquals(0, new DelNodes_1110().delNodes(null, new int[]{1}).size());
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        List<TreeNode> result = new DelNodes_1110().delNodes(root, new int[]{1});
        assertEquals(2, result.size());
    }

    @Test
    public void testDeleteNothing() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        List<TreeNode> result = new DelNodes_1110().delNodes(root, new int[]{});
        assertEquals(1, result.size());
        assertEquals(1, result.get(0).val);
    }

    @Test
    public void testDeleteAllNodes() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        List<TreeNode> result = new DelNodes_1110().delNodes(root, new int[]{1, 2, 3});
        assertEquals(0, result.size());
    }

    @Test
    public void testDeleteLeafNodes() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        List<TreeNode> result = new DelNodes_1110().delNodes(root, new int[]{4, 5});
        assertEquals(1, result.size());
        assertEquals(1, result.get(0).val);
    }

    @Test
    public void testSingleNodeDeleted() {
        TreeNode root = new TreeNode(1);
        List<TreeNode> result = new DelNodes_1110().delNodes(root, new int[]{1});
        assertEquals(0, result.size());
    }

    @Test
    public void testSingleNodeNotDeleted() {
        TreeNode root = new TreeNode(1);
        List<TreeNode> result = new DelNodes_1110().delNodes(root, new int[]{2});
        assertEquals(1, result.size());
        assertEquals(1, result.get(0).val);
    }

    @Test
    public void testDeleteMiddleNode() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        List<TreeNode> result = new DelNodes_1110().delNodes(root, new int[]{2});
        assertEquals(3, result.size()); // root(1), 4, 5
    }

    @Test
    public void testGiantTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6); root.right.right = new TreeNode(7);
        root.left.left.left = new TreeNode(8); root.left.left.right = new TreeNode(9);
        // Delete 2 and 6 -> roots: 1, 4, 5, 6's children (none)
        List<TreeNode> result = new DelNodes_1110().delNodes(root, new int[]{2, 6});
        assertEquals(3, result.size()); // root(1), 4, 5
    }
}
