package Solution.DFS;

import Lib.ListNode.ListNode;
import Lib.Tree.BinaryTree.TreeNode;

/**
 * Given a binary tree root and a linked list with head as the first node.
 * Return True if all elements in the list starting from the head correspond to some downward path in the binary tree.
 * In this context downward path means a path that starts at some node and goes downwards.
 *
 * @author BorisMirage
 * Time: 2020/03/19 11:30
 * Created with IntelliJ IDEA
 */

public class IsSubPath_1367 {
    /**
     * DFS.
     * If current node in DFS is the head node value, try to find if the path contains all nodes in list.
     *
     * @param head head of list
     * @param root root of given tree
     * @return whether there is a downward path contains all nodes in given list
     */
    public boolean isSubPath(ListNode head, TreeNode root) {

        /* Corner case */
        if (root == null || head == null) {
            return false;
        }

        return dfs(root, head);
    }

    /**
     * Preorder traverse in tree to check if any path in tree is equal to the list.
     *
     * @param root root of given tree
     * @param head head of list
     * @return whether there is a downward path contains all nodes in given list
     */
    private boolean dfs(TreeNode root, ListNode head) {
        if (root == null) {
            return false;
        }

        if (root.val == head.val && search(root, head)) {
            return true;
        }

        return dfs(root.left, head) || dfs(root.right, head);
    }

    /**
     * Compare node in tree and list to check if current node is valid.
     *
     * @param root current tree node
     * @param head current node in list
     * @return if current node is valid
     */
    private boolean search(TreeNode root, ListNode head) {
        if (head == null) {
            return true;
        }

        if (root == null || root.val != head.val) {
            return false;
        }

        return search(root.left, head.next) || search(root.right, head.next);
    }
}
