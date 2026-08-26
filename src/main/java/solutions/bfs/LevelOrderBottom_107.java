package solutions.bfs;

import library.tree.binarytree.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

/**
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values.
 * i.e., from left to right, level by level from leaf to root.
 *
 * @author BorisMirage
 * Time: 2019/04/28 17:12
 * Created with IntelliJ IDEA
 */

public class LevelOrderBottom_107 {

    /**
     * Traverses a binary tree level by level and returns the levels from bottom to top.
     *
     * <p>The queue size is captured before each level is processed, so children enqueued while
     * processing the current level are handled only on the next iteration. Levels are collected
     * in normal breadth-first order and reversed once after traversal.</p>
     *
     * <p>Reversing once is linear in the number of levels and avoids repeatedly inserting at the
     * front of an array-backed list.</p>
     *
     * @param root root node of the tree; may be {@code null}
     * @return the node values grouped from the deepest level to the root, or an empty list when
     * {@code root} is {@code null}
     * @see <a href="https://leetcode.com/problems/binary-tree-level-order-traversal-ii/">LeetCode 107</a>
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        // corner case
        if (root == null) {
            return new ArrayList<>();
        }

        List<List<Integer>> output = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> level = new ArrayList<>(levelSize);
            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();
                level.add(current.val);

                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
            output.add(level);
        }

        // BFS produced root-to-leaf order; reverse the level order for the required result.
        Collections.reverse(output);
        return output;
    }
}
