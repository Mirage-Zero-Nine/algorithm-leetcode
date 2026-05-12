package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class SortedArrayToBST108Test {

    private final SortedArrayToBST_108 test = new SortedArrayToBST_108();

    @Test
    public void testHappyCases() {
        TreeNode result = test.sortedArrayToBST(new int[]{-10, -3, 0, 5, 9});
        assertEquals(0, result.val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.sortedArrayToBST(new int[]{}));
        assertEquals(1, test.sortedArrayToBST(new int[]{1}).val);
    }

    @Test
    public void testLargeCase() {
        TreeNode result = test.sortedArrayToBST(new int[]{1, 2, 3, 4, 5, 6, 7});
        assertEquals(4, result.val);
    }
}
