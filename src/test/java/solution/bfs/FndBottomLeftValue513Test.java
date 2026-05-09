package solution.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class FndBottomLeftValue513Test {

    private final FndBottomLeftValue_513 test = new FndBottomLeftValue_513();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1); root.right = new TreeNode(3);
        assertEquals(1, test.findBottomLeftValue(root));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.findBottomLeftValue(null));
        assertEquals(1, test.findBottomLeftValue(new TreeNode(1)));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        root.left.right.left = new TreeNode(7);
        assertEquals(7, test.findBottomLeftValue(root));
    }
}
