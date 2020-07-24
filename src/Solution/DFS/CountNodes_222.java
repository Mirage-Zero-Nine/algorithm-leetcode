package Solution.DFS;

import Lib.Tree.BinaryTree.TreeNode;

/**
 * @author BorisMirage
 * Time: 2019/06/11 15:32
 * Created with IntelliJ IDEA
 */

public class CountNodes_222 {
    /**
     * Calculate tree depth each time.
     * If the tree is full tree, then nodes = 2^depth - 1.
     * Otherwise, cut tree into left child and right child, until both tree is full binary tree.
     *
     * @param root root node
     * @return number of nodes in tree
     */
    public int countNodes(TreeNode root) {

        /* Corner case */
        if (root == null) {
            return 0;
        }

        int leftDepth = depth(root, true);      // left sub tree depth
        int rightDepth = depth(root, false);        // right sub tree length

        return leftDepth == rightDepth ? (1 << leftDepth) - 1 : (1 + countNodes(root.left) + countNodes(root.right));
    }

    /**
     * Calculate tree depth.
     *
     * @param r    root node
     * @param left search left child or right child
     * @return depth of current tree
     */
    private int depth(TreeNode r, boolean left) {
        int depth = 0;
        while (r != null) {
            r = left ? r.left : r.right;
            depth++;
        }
        return depth;
    }
}
