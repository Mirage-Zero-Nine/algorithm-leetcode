package solution.dfs;

import library.tree.binarytree.TreeNode;

/**
 * Given a tree, rearrange the tree in in-order so that the leftmost node in the tree is now the root of the tree.
 * Every node has no left child and only 1 right child.
 *
 * @author BorisMirage
 * Time: 2019/02/28 12:52
 * Created with IntelliJ IDEA
 */

public class IncreasingBST_897 {
    private TreeNode p = null, r = null;

    /**
     * DFS.
     *
     * @param root root node
     * @return rearranged tree
     */
    public TreeNode increasingBST(TreeNode root) {

        if (root == null) {
            return null;
        }

        increasingBST(root.left);
        if (p != null) {
            root.left = null;
            p.right = root;
        }
        if (r == null) {
            r = root;        // init this tree
        }
        p = root;
        increasingBST(root.right);
        return r;
    }
}
