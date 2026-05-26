package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import library.listnode.ListNode;
import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class IsSubPath_1367Test {

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

    @Test
    public void testNullHead() {
        TreeNode root = new TreeNode(1);
        assertFalse(test.isSubPath(null, root));
    }

    @Test
    public void testSingleNodeMatch() {
        TreeNode root = new TreeNode(5);
        assertTrue(test.isSubPath(buildList(5), root));
    }

    @Test
    public void testSingleNodeNoMatch() {
        TreeNode root = new TreeNode(5);
        assertFalse(test.isSubPath(buildList(3), root));
    }

    @Test
    public void testPathInRightSubtree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(10);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(4);
        assertTrue(test.isSubPath(buildList(2, 3, 4), root));
    }

    @Test
    public void testPartialMatchThenFullMatch() {
        // Tree has a partial match that fails, then a full match deeper
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(99); // partial match fails here
        root.right = new TreeNode(1);
        root.right.left = new TreeNode(2);
        root.right.left.left = new TreeNode(3);
        root.right.left.left.left = new TreeNode(4); // full match
        assertTrue(test.isSubPath(buildList(1, 2, 3, 4), root));
    }

    @Test
    public void testListLongerThanTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        assertFalse(test.isSubPath(buildList(1, 2, 3, 4, 5), root));
    }

    @Test
    public void testMatchAtLeaf() {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(20);
        root.left.left = new TreeNode(30);
        assertTrue(test.isSubPath(buildList(30), root));
    }

    @Test
    public void testGiantTree() {
        // Build a deep tree (left chain of 200 nodes: 1,2,3,...,200)
        TreeNode root = new TreeNode(1);
        TreeNode cur = root;
        for (int i = 2; i <= 200; i++) {
            cur.left = new TreeNode(i);
            cur = cur.left;
        }
        // List is 150->151->...->200 (51 nodes), should match
        assertTrue(test.isSubPath(buildList(150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160), root));
        // List that doesn't exist
        assertFalse(test.isSubPath(buildList(150, 152), root));
    }
}
