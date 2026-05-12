package solution.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class RightSideView199Test {

    private final RightSideView_199 test = new RightSideView_199();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.right = new TreeNode(5); root.right.right = new TreeNode(4);
        assertEquals(List.of(1, 3, 4), test.rightSideView(root));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(List.of(), test.rightSideView(null));
        assertEquals(List.of(1), test.rightSideView(new TreeNode(1)));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        assertEquals(List.of(1, 2, 3), test.rightSideView(root));
    }
}
