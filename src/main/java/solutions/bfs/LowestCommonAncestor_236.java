package solutions.bfs;

import library.tree.binarytree.TreeNode;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 * LCA is defined between two nodes p and q as the lowest node in T that has both p and q as descendants.
 * The two target nodes are distinct, both are present in the tree, and node values are unique.
 * A node is considered to be its own ancestor and descendant for this definition.
 *
 * @author BorisMirage
 * Time: 2019/06/11 17:53
 * Created with IntelliJ IDEA
 */

public class LowestCommonAncestor_236 {
    /**
     * Finds the LCA with a post-order depth-first search. A recursive call returns either
     * {@code null}, the one target it found, or the LCA of both targets in that subtree.
     * If the current node is one of the targets, it is immediately returned: the problem
     * guarantees that both targets are in the tree, so this node is their LCA whenever the
     * other target is below it. When both child calls return a node, the targets are in
     * different child subtrees and the current node is the first node that contains both.
     * Otherwise the non-null child result is passed upward unchanged.
     *
     * <p>This method uses O(h) auxiliary call-stack space for tree height {@code h} and visits
     * each node at most once, so its time complexity is O(n). The compact DFS approach is
     * preferable for ordinary trees, but a very deep skewed tree can overflow the call stack;
     * the BFS approach below uses more heap memory and avoids that recursion limit. It does not mutate the tree.
     * The implementation compares values because this problem's input guarantees unique
     * node values; callers must supply the corresponding nodes from the tree.</p>
     *
     * @param root root of the binary tree
     * @param p    first target node
     * @param q    second target node
     * @return the lowest node that is an ancestor of both targets
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Reaching either target gives its subtree a candidate that can be resolved by its parent.
        if (root == null || root.val == p.val || root.val == q.val) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q), right = lowestCommonAncestor(root.right, p, q);

        // Only one side found a target, so that side's candidate is the only possible answer here.
        if (left == null) {
            return right;
        } else if (right == null) {
            return left;
        }
        // Each side found a target; this node is their lowest shared ancestor.
        return root;
    }

    /**
     * Finds the LCA by recording parent links with breadth-first search. The map stores each
     * node's parent, after which the method walks from {@code p} to the root and puts that
     * ancestor chain in a set. Walking from {@code q} upward then stops at the first node in
     * the set. Because this walk starts at {@code q} and moves toward the root, that first
     * intersection is the lowest common ancestor.
     *
     * <p>The tree is traversed once and each ancestor chain is walked at most once, giving
     * O(n) time and O(n) auxiliary heap space. This uses more memory than DFS, but it avoids
     * a call-stack overflow on a deeply skewed tree. The tree is not mutated. As required by the
     * problem contract, {@code root}, {@code p}, and {@code q} are non-null nodes in the
     * same tree.</p>
     *
     * @param root root node
     * @param p    first node
     * @param q    second node
     * @return lowest common ancestor (LCA) of two given nodes
     */
    public TreeNode lowestCommonAncestorBfsWithSet(TreeNode root, TreeNode p, TreeNode q) {

        Queue<TreeNode> queue = new ArrayDeque<>(List.of(root));
        Map<TreeNode, TreeNode> m = new HashMap<>(Collections.singletonMap(root, null));

        while (!m.containsKey(p) || !m.containsKey(q)) {
            TreeNode current = queue.poll();
            if (current != null) {
                // Record each edge once so either target can later be followed to the root.
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

        Set<TreeNode> s = new HashSet<>();

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
