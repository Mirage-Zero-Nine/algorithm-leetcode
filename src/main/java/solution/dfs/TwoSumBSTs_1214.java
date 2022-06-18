package solution.dfs;

import library.tree.binarytree.TreeNode;

import java.util.HashSet;

/**
 * Given two binary search trees.
 * Return True iff there is a node in the first tree and a node in the second tree that sum up to a given target.
 *
 * @author BorisMirage
 * Time: 2019/10/09 11:30
 * Created with IntelliJ IDEA
 */

public class TwoSumBSTs_1214 {
    /**
     * Naive approach: do the binary search recursively.
     * Time complexity: O(n * m)
     * Space complexity: O(m + n)
     *
     * @param root1  first BST
     * @param root2  second BST
     * @param target target value
     * @return if there is a node in the first tree and a node in the second tree that sum up to a given target
     */
    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        if (root1 == null || root2 == null) {
            return false;
        }

        int tmp = root1.val + root2.val;
        if (tmp == target) {
            return true;
        } else if (tmp > target) {
            return twoSumBSTs(root1.left, root2, target) || twoSumBSTs(root1, root2.left, target);
        } else {
            return twoSumBSTs(root1.right, root2, target) || twoSumBSTs(root1, root2.right, target);
        }
    }

    /**
     * Use hash set to traverse the tree first, then search in second tree.
     *
     * @param root1  first BST
     * @param root2  second BST
     * @param target target value
     * @return if there is a node in the first tree and a node in the second tree that sum up to a given target
     */
    public boolean hashSet(TreeNode root1, TreeNode root2, int target) {
        if (root1 == null || root2 == null) {
            return false;
        }

        HashSet<Integer> s = new HashSet<>();
        inorder(s, root1);

        return search(root2, target, s);
    }

    /**
     * Search two sum target based on given value
     *
     * @param r2     second BST
     * @param target target value
     * @param s      hash set stores previous tree elements
     * @return if there is a node in the first tree and a node in the second tree that sum up to a given target
     */
    private boolean search(TreeNode r2, int target, HashSet<Integer> s) {

        if (r2 == null) {
            return false;
        }

        if (s.contains(target - r2.val)) {
            return true;
        } else {
            return search(r2.left, target, s) || search(r2.right, target, s);
        }
    }

    /**
     * Inorder traversal to save all nodes.
     *
     * @param s hash set
     * @param r root of tree
     */
    private void inorder(HashSet<Integer> s, TreeNode r) {
        if (r == null) {
            return;
        }

        inorder(s, r.left);
        s.add(r.val);
        inorder(s, r.right);
    }
}
