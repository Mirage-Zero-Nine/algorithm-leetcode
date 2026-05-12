package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class SearchBST700Test {

    private final SearchBST_700 test = new SearchBST_700();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2); root.right = new TreeNode(7);
        root.left.left = new TreeNode(1); root.left.right = new TreeNode(3);
        assertEquals(2, test.searchBST(root, 2).val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.searchBST(null, 1));
        assertNull(test.searchBST(new TreeNode(1), 2));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3); root.right = new TreeNode(7);
        assertEquals(7, test.searchBST(root, 7).val);
    }
}
