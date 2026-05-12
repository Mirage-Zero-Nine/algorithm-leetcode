package solution.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class LargestValues515Test {

    private final LargestValues_515 test = new LargestValues_515();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3); root.right = new TreeNode(2);
        root.left.left = new TreeNode(5); root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(9);
        assertEquals(List.of(1, 3, 9), test.largestValues(root));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(List.of(), test.largestValues(null));
        assertEquals(List.of(1), test.largestValues(new TreeNode(1)));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.right.right = new TreeNode(5);
        assertEquals(List.of(1, 3, 5), test.largestValues(root));
    }
}
