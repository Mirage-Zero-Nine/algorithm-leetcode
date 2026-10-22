package library.tree.binarytree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TreeNodeTest {
    @Test void constructorStoresValueAndLeavesChildrenNull() {
        TreeNode node = new TreeNode(Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, node.val);
        assertNull(node.left);
        assertNull(node.right);
    }

    @Test void childrenCanBeLinkedAndValuesRemainMutable() {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;
        right.val = -4;
        assertSame(left, root.left);
        assertSame(right, root.right);
        assertEquals(-4, root.right.val);
    }
}
