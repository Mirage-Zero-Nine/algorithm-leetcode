package solution.dfs;

import library.tree.binarytree.TreeNode;

/**
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
 * LCA is defined between two nodes p and q as the lowest node in T that has both p and q as descendants.
 *
 * @author BorisMirage
 * Time: 2019/06/11 17:07
 * Created with IntelliJ IDEA
 */

public class LowestCommonAncestor_235 {
    /**
     * Smaller node will be left part of BST, while larger node will be in right part of BST.
     * Therefore, if p < root < q, then root is LCA.
     * If (root - p) * (root - q) > 0, then they will be both left or right, depend on p and q's value.
     *
     * @param root root node
     * @param p    node
     * @param q    node
     * @return lowest common ancestor
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        int max = Math.max(p.val, q.val);
        while ((root.val - p.val) * (root.val - q.val) > 0) {
            root = root.val > max ? root.left : root.right;
        }
        return root;
    }
}
