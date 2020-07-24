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
     * Recursion DFS version.
     * Three conditions:
     * 1. p and q are under same node -> LCA is the node
     * 2. One of p or q is the LCA -> LCA is p or q
     * 3. p and q are in different part of root's children -> LCA is root
     * These conditions can be also implemented in sub tree.
     * Do the DFS.
     * If one node is found in the left sub tree, then search in the right sub tree.
     * If the other node does not exist in right sub tree, then it is under the left sub tree.
     * Otherwise, LCA is the root of current recursion.
     *
     * @param root root node
     * @param p    first node
     * @param q    second node
     * @return lowest common ancestor (LCA) of two given nodes
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        /* Corner case and end point */
        if (root == null || root.val == p.val || root.val == q.val) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);      // search in left sub tree
        TreeNode right = lowestCommonAncestor(root.right, p, q);        // search in right sub tree

        if (left != null && right != null) {
            return root;        // found node in both left sub tree and right sub tree
        }
        return left == null ? right : left;     // it will exist
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
