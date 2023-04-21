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

public class VerticalTraversal987Test {

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
}