package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import library.tree.TreeParser;
import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author BorisMirage
 * Time: 2023/04/21 14:28
 * Created with IntelliJ IDEA
 */

public class VerticalTraversal_987Test {

    private final VerticalTraversal_987 test = new VerticalTraversal_987();

    @Test
    public void testVerticalTraversal() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        List<List<Integer>> result = test.verticalTraversal(root);

        assertEquals(4, result.size());
        assertIterableEquals(List.of(9), result.get(0));
        assertIterableEquals(List.of(3, 15), result.get(1));
        assertIterableEquals(List.of(20), result.get(2));
        assertIterableEquals(List.of(7), result.get(3));
    }

    @Test
    public void testVerticalTraversal1() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        List<List<Integer>> result = test.verticalTraversal(root);

        assertEquals(5, result.size());
        assertIterableEquals(List.of(4), result.get(0));
        assertIterableEquals(List.of(2), result.get(1));
        assertIterableEquals(List.of(1, 5, 6), result.get(2));
        assertIterableEquals(List.of(3), result.get(3));
        assertIterableEquals(List.of(7), result.get(4));
    }

    @Test
    public void testVerticalTraversal2() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(6);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);

        List<List<Integer>> result = test.verticalTraversal(root);

        assertEquals(5, result.size());
        assertIterableEquals(List.of(4), result.get(0));
        assertIterableEquals(List.of(2), result.get(1));
        assertIterableEquals(List.of(1, 5, 6), result.get(2));
        assertIterableEquals(List.of(3), result.get(3));
        assertIterableEquals(List.of(7), result.get(4));
    }

    @Test
    public void testVerticalTraversal3() {
        TreeNode root = TreeParser.deserialize("0,8,1,null,null,3,2,null,4,5,null,null,7,6");

        List<List<Integer>> result = test.verticalTraversal(root);

        assertEquals(4, result.size());
        assertIterableEquals(List.of(8), result.get(0));
        assertIterableEquals(List.of(0, 3, 6), result.get(1));
        assertIterableEquals(List.of(1, 4, 5), result.get(2));
        assertIterableEquals(List.of(2, 7), result.get(3));
    }

    @Test
    public void testSingleNode() {
        TreeNode root = new TreeNode(42);
        List<List<Integer>> result = test.verticalTraversal(root);
        assertEquals(1, result.size());
        assertIterableEquals(List.of(42), result.get(0));
    }

    @Test
    public void testNullRoot() {
        List<List<Integer>> result = test.verticalTraversal(null);
        assertEquals(0, result.size());
    }

    @Test
    public void testLeftSkewed() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        List<List<Integer>> result = test.verticalTraversal(root);
        assertEquals(3, result.size());
        assertIterableEquals(List.of(3), result.get(0));
        assertIterableEquals(List.of(2), result.get(1));
        assertIterableEquals(List.of(1), result.get(2));
    }

    @Test
    public void testRightSkewed() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        List<List<Integer>> result = test.verticalTraversal(root);
        assertEquals(3, result.size());
        assertIterableEquals(List.of(1), result.get(0));
        assertIterableEquals(List.of(2), result.get(1));
        assertIterableEquals(List.of(3), result.get(2));
    }

    @Test
    public void testSamePositionSmallerFirst() {
        // Two nodes at same position: smaller value reported first
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(8);
        root.left.right = new TreeNode(1);
        root.right.left = new TreeNode(2);
        // nodes 1 and 2 are at same column (0) and same depth (2)
        List<List<Integer>> result = test.verticalTraversal(root);
        assertEquals(3, result.size());
        assertIterableEquals(List.of(9), result.get(0));
        assertIterableEquals(List.of(3, 1, 2), result.get(1));
        assertIterableEquals(List.of(8), result.get(2));
    }

    @Test
    public void testGiantBalancedTree() {
        // Build a complete binary tree of depth 4 (15 nodes)
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(4);
        root.right = new TreeNode(12);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(6);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(14);
        root.left.left.left = new TreeNode(1);
        root.left.left.right = new TreeNode(3);
        root.left.right.left = new TreeNode(5);
        root.left.right.right = new TreeNode(7);
        root.right.left.left = new TreeNode(9);
        root.right.left.right = new TreeNode(11);
        root.right.right.left = new TreeNode(13);
        root.right.right.right = new TreeNode(15);

        List<List<Integer>> result = test.verticalTraversal(root);
        assertEquals(7, result.size());
        // leftmost column has node 1
        assertIterableEquals(List.of(1), result.get(0));
        // rightmost column has node 15
        assertIterableEquals(List.of(15), result.get(6));
    }
}
