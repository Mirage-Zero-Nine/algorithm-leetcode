package solution.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class ZigzagLevelOrder103Test {

    private final ZigzagLevelOrder_103 test = new ZigzagLevelOrder_103();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9); root.right = new TreeNode(20);
        root.right.left = new TreeNode(15); root.right.right = new TreeNode(7);
        assertEquals(List.of(List.of(3), List.of(20, 9), List.of(15, 7)), test.zigzagLevelOrder(root));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(List.of(), test.zigzagLevelOrder(null));
        assertEquals(List.of(List.of(1)), test.zigzagLevelOrder(new TreeNode(1)));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        assertEquals(List.of(List.of(1), List.of(3, 2), List.of(4, 5)), test.zigzagLevelOrder(root));
    }
}
