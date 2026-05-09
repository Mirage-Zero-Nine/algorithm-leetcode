package solution.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class LevelOrderBottom107Test {

    private final LevelOrderBottom_107 test = new LevelOrderBottom_107();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9); root.right = new TreeNode(20);
        root.right.left = new TreeNode(15); root.right.right = new TreeNode(7);
        assertEquals(List.of(List.of(15, 7), List.of(9, 20), List.of(3)), test.levelOrderBottom(root));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(List.of(), test.levelOrderBottom(null));
        assertEquals(List.of(List.of(1)), test.levelOrderBottom(new TreeNode(1)));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        assertEquals(List.of(List.of(4, 5), List.of(2, 3), List.of(1)), test.levelOrderBottom(root));
    }
}
