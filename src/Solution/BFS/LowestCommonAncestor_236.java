package Solution.BFS;

import Lib.Tree.BinaryTree.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 * LCA is defined between two nodes p and q as the lowest node in T that has both p and q as descendants.
 *
 * @author BorisMirage
 * Time: 2019/06/11 17:53
 * Created with IntelliJ IDEA
 */

public class LowestCommonAncestor_236 {
    /**
     * DFS (Post-order traverse).
     * Two conditions:
     * 1. p and q are under same tree node, or under different part of root's subtree -> this tree node is the LCA
     * 2. One of p or q is the LCA -> LCA is p or q
     * During the post-order traverse:
     * - Based on how post-order traverse works, it will start at deepest node.
     * - If found p or q, return current node, since LCA will be this node or its parents
     * - If left subtree return null, then return the other half, since LCA will only be in the other half, or parents.
     * - If both left and right has a return node, then the current node is LCA, return current node.
     *
     * @param root root node
     * @param p    first node
     * @param q    second node
     * @return lowest common ancestor (LCA) of two given nodes
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        /* Corner case and end point */
        if (root == null || root.val == p.val || root.val == q.val) {
            return root; // if LCA is one of the p or q, then the search can stop when hit p or q.
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);   // search in left subtree
        TreeNode right = lowestCommonAncestor(root.right, p, q); // search in right subtree

        if (left == null) {
            return right; // right subtree may contain LCA which passed to parent node, or return null if no found
        } else if (right == null) {
            return left;  // no p or q in both subtree, return null
        }

        return root; // both node found in subtree, hence current node is LCA
    }

    /**
     * BFS + HashMap + HashSet.
     * HashMap save node's ancestor, HashSet save ancestor when trace back from p (or q).
     * Use BFS to find p and q, with a hash map to save the parents.
     * Then trace back to p, use a hash set to save the ancestor during tracing back until reach root.
     * Do the same thing on q, if there is any ancestor of q is in set, return this node.
     *
     * @param root root node
     * @param p    first node
     * @param q    second node
     * @return lowest common ancestor (LCA) of two given nodes
     */
    public TreeNode bfsWithSet(TreeNode root, TreeNode p, TreeNode q) {

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        HashMap<TreeNode, TreeNode> m = new HashMap<>();
        m.put(root, null);

        while (!m.containsKey(p) || !m.containsKey(q)) {
            TreeNode current = queue.poll();
            if (current != null) {
                if (current.left != null) {
                    queue.add(current.left);
                    m.put(current.left, current);
                }
                if (current.right != null) {
                    queue.add(current.right);
                    m.put(current.right, current);
                }
            }
        }

        HashSet<TreeNode> s = new HashSet<>();

        while (p != null) {
            s.add(p);
            p = m.get(p);
        }

        while (!s.contains(q)) {
            q = m.get(q);
        }

        return q;
    }
}
