package solution.dfs;

import library.tree.binarytree.TreeNode;

/**
 * Given two binary trees and imagine that when you put one of them to cover the other.
 * Some nodes of the two trees are overlapped while the others are not.
 * You need to merge them into a new binary tree.
 * The merge rule is that if two nodes overlap, then sum node values up as the new value of the merged node.
 * Otherwise, the NOT null node will be used as the node of new tree.
 *
 * @author BorisMirage
 * Time: 2020/01/19 21:48
 * Created with IntelliJ IDEA
 */

public class MergeTrees_617 {
    /**
     * The merge is to add two overlapped tree nodes value. Then create a new node.
     * If one node exist and the other is not exist, create a new node and fill in the non-empty node.
     *
     * @param t1 first tree root
     * @param t2 second tree root
     * @return merged tree
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {

        /* Corner case */
        if (t1 == null && t2 == null) {
            return null;
        }

        int value = (t1 == null ? 0 : t1.val) + (t2 == null ? 0 : t2.val);      // merge operation
        TreeNode tmp = new TreeNode(value);

        tmp.left = mergeTrees(t1 == null ? null : t1.left, t2 == null ? null : t2.left);
        tmp.right = mergeTrees(t1 == null ? null : t1.right, t2 == null ? null : t2.right);

        return tmp;
    }
}
