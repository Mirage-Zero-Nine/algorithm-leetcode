package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class BuildTree106Test {

    private final BuildTree_106 test = new BuildTree_106();

    @Test
    public void testHappyCases() {
        TreeNode result = test.buildTree(new int[]{9, 3, 15, 20, 7}, new int[]{9, 15, 7, 20, 3});
        assertEquals(3, result.val);
        assertEquals(9, result.left.val);
        assertEquals(20, result.right.val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.buildTree(new int[]{}, new int[]{}));
        assertEquals(1, test.buildTree(new int[]{1}, new int[]{1}).val);
    }

    @Test
    public void testLargeCase() {
        TreeNode result = test.buildTree(new int[]{4, 2, 5, 1, 6, 3, 7}, new int[]{4, 5, 2, 6, 7, 3, 1});
        assertEquals(1, result.val);
    }
}
