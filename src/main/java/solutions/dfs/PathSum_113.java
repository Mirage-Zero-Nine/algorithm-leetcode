package solutions.dfs;

import library.tree.binarytree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
 *
 * @author BorisMirage
 * Time: 2019/03/23 11:16
 * Created with IntelliJ IDEA
 */

public class PathSum_113 {
    /**
     * Finds every root-to-leaf path whose node values add up to {@code targetSum}.
     *
     * <p>The search keeps one mutable path while traversing the tree. At each node,
     * the node value is appended to that path and subtracted from the remaining
     * sum. A copy of the path is added to the result only when a leaf reaches a
     * remaining sum of zero. The node is removed after both subtrees have been
     * searched so that sibling paths can reuse the same path object safely.</p>
     *
     * @param root      root of the binary tree; may be {@code null}
     * @param targetSum required sum of each returned root-to-leaf path
     * @return all matching root-to-leaf paths, or an empty list when none exist
     */
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> output = new ArrayList<>();
        dfs(root, targetSum, new ArrayList<>(), output);

        return output;
    }

    /**
     * Performs a depth-first traversal while maintaining the current path.
     *
     * @param root      node currently being visited
     * @param remaining sum still needed after the nodes above {@code root}
     * @param path      mutable root-to-current-node path
     * @param output    collection receiving copies of matching paths
     */
    private void dfs(TreeNode root, long remaining, List<Integer> path, List<List<Integer>> output) {
        if (root == null) {
            return;
        }

        // Include this node before checking whether the path ends here.
        path.add(root.val);
        long current = remaining - root.val;
        if (root.left == null && root.right == null && current == 0) {
            // Copy the path because the same mutable list is reused during DFS.
            output.add(new ArrayList<>(path));
        }

        dfs(root.left, current, path, output);
        dfs(root.right, current, path, output);

        // Backtrack so the parent can explore its other branch cleanly.
        path.removeLast();
    }
}
