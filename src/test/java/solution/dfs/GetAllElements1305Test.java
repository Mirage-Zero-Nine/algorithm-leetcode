package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class GetAllElements1305Test {

    private final GetAllElements_1305 test = new GetAllElements_1305();

    @Test
    public void testHappyCases() {
        TreeNode t1 = new TreeNode(2); t1.left = new TreeNode(1); t1.right = new TreeNode(4);
        TreeNode t2 = new TreeNode(1); t2.left = new TreeNode(0); t2.right = new TreeNode(3);
        assertEquals(List.of(0, 1, 1, 2, 3, 4), test.getAllElements(t1, t2));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(List.of(1), test.getAllElements(new TreeNode(1), null));
        assertEquals(List.of(), test.getAllElements(null, null));
    }

    @Test
    public void testLargeCase() {
        TreeNode t1 = new TreeNode(5); t1.left = new TreeNode(3); t1.right = new TreeNode(7);
        TreeNode t2 = new TreeNode(4); t2.left = new TreeNode(2); t2.right = new TreeNode(6);
        assertEquals(List.of(2, 3, 4, 5, 6, 7), test.getAllElements(t1, t2));
    }
}
