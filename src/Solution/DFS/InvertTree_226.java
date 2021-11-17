package Solution.DFS;

import Lib.Tree.BinaryTree.TreeNode;

/**
 * Invert a binary tree.
 *
 * @author BorisMirage
 * Time: 2019/06/11 16:48
 * Created with IntelliJ IDEA
 */

public class InvertTree_226 {
    /**
     * DFS. Started at leaf node, swap left and right subtree.
     * Then back to previous level and keep swapping until reaches root.
     *
     * @param root root node
     * @return inverted tree
     */
    public TreeNode invertTree(TreeNode root) {

        /* Corner case */
        if (root == null) {
            return root;
        }

        root.left = invertTree(root.left);
        root.right = invertTree(root.right);

        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        return root;
    }
}
