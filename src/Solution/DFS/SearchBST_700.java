package Solution.DFS;

import Lib.Tree.BinaryTree.TreeNode;

/**
 * Given the root node of a binary search tree (BST) and a value.
 * You need to find the node in the BST that the node's value equals the given value.
 * Return the subtree rooted with that node. If such node doesn't exist, you should return NULL.
 *
 * @author BorisMirage
 * Time: 2019/09/13 16:08
 * Created with IntelliJ IDEA
 */

public class SearchBST_700 {
    /**
     * Basic DFS with recursion. Utilize the rule of BST.
     *
     * @param root root node
     * @param val  target value
     * @return subtree rooted with that node, if such node doesn't exist, you should return NULL
     */
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (root.val == val) {
            return root;
        }
        return (root.val > val) ? searchBST(root.left, val) : searchBST(root.right, val);
    }
}
