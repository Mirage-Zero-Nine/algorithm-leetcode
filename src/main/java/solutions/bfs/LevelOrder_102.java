package solutions.bfs;

import library.tree.binarytree.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Given a binary tree, return the level order traversal of its nodes' values (from left to right, level by level).
 *
 * @author BorisMirage
 * Time: 2019/02/21 14:02
 * Created with IntelliJ IDEA
 */

public class LevelOrder_102 {

    /**
     * Traverses a binary tree one level at a time, from left to right.
     *
     * <p>The queue contains the nodes that still need to be visited. The queue size is captured
     * before each level is processed, so children added while processing the current level are
     * reserved for the next level.</p>
     *
     * <p>Implementation note: {@link java.util.ArrayDeque} provides constant-time queue
     * operations without the per-node linked-list allocation of {@code LinkedList}.</p>
     *
     * @param root root node of the tree; may be {@code null}
     * @return the node values grouped by level, or an empty list when {@code root} is {@code null}
     * @see <a href="https://leetcode.com/problems/binary-tree-level-order-traversal/">LeetCode 102</a>
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
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

        return output;
    }
}
