package solution.stack;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClosestKValues_272Test {
    private final ClosestKValues_272 solver = new ClosestKValues_272();

    private TreeNode buildBST() {
        //       4
        //      / \
        //     2   5
        //    / \
        //   1   3
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        return root;
    }

    @Test public void testBasic() {
        List<Integer> res = solver.closestKValues(buildBST(), 3.7, 2);
        assertTrue(res.containsAll(List.of(3, 4)));
    }

    @Test public void testK1() {
        List<Integer> res = solver.closestKValues(buildBST(), 2.1, 1);
        assertEquals(List.of(2), res);
    }

    @Test public void testAllElements() {
        List<Integer> res = solver.closestKValues(buildBST(), 3.0, 5);
        assertEquals(5, res.size());
    }

    @Test public void testTargetExact() {
        List<Integer> res = solver.closestKValues(buildBST(), 4.0, 3);
        assertTrue(res.containsAll(List.of(3, 4, 5)));
    }

    @Test public void testNullRoot() {
        List<Integer> res = solver.closestKValues(null, 1.0, 0);
        assertTrue(res.isEmpty());
    }

    @Test public void testSingleNode() {
        TreeNode root = new TreeNode(1);
        List<Integer> res = solver.closestKValues(root, 0.5, 1);
        assertEquals(List.of(1), res);
    }
}
