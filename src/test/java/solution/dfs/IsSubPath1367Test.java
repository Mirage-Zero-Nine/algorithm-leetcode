package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import library.listnode.ListNode;
import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class IsSubPath1367Test {

    private final IsSubPath_1367 test = new IsSubPath_1367();

    private ListNode buildList(int... vals) {
        ListNode dummy = new ListNode(0), cur = dummy;
        for (int v : vals) { cur.next = new ListNode(v); cur = cur.next; }
        return dummy.next;
    }

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(4); root.right = new TreeNode(4);
        root.left.right = new TreeNode(2); root.left.right.left = new TreeNode(1);
        assertTrue(test.isSubPath(buildList(4, 2, 1), root));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertFalse(test.isSubPath(null, null));
        assertFalse(test.isSubPath(buildList(1), null));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        assertTrue(test.isSubPath(buildList(1, 2), root));
        assertFalse(test.isSubPath(buildList(1, 3, 2), root));
    }
}
