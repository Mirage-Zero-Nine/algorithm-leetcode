package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class BuildTree105Test {

    private final BuildTree_105 test = new BuildTree_105();

    @Test
    public void testHappyCases() {
        TreeNode result = test.buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});
        assertEquals(3, result.val);
        assertEquals(9, result.left.val);
        assertEquals(20, result.right.val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.buildTree(null, null));
        assertEquals(1, test.buildTree(new int[]{1}, new int[]{1}).val);
    }

    @Test
    public void testLargeCase() {
        TreeNode result = test.buildTree(new int[]{1, 2, 4, 5, 3, 6, 7}, new int[]{4, 2, 5, 1, 6, 3, 7});
        assertEquals(1, result.val);
        assertEquals(2, result.left.val);
        assertEquals(3, result.right.val);
    }
}
