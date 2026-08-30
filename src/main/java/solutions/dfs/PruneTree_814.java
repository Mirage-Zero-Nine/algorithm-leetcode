package solutions.dfs;

import library.tree.binarytree.TreeNode;

/**
 * Given the root of a binary tree, removes every subtree that does not contain a node with value {@code 1}.
 *
 * <p>The solution uses a postorder depth-first traversal. Children must be processed before their parent so that,
 * when the parent is evaluated, its child references already describe the pruned subtrees. A node can be removed
 * exactly when its value is {@code 0} and both children have been removed. The tree is modified in place, and the
 * method returns the root of the pruned tree.
 *
 * <p>Each node is visited once, so the time complexity is {@code O(n)}. The recursion stack uses {@code O(h)}
 * auxiliary space, where {@code h} is the height of the tree.
 *
 * @author BorisMirage
 * Time: 2026/08/29 20:19
 * Created with IntelliJ IDEA
 */

public class PruneTree_814 {
    /**
     * Prunes all-zero subtrees using postorder depth-first search.
     * The recursive calls first prune the left and right subtrees and return their new roots. Those returned roots
     * are assigned back to the current node, so a removed child becomes {@code null}. Once both children have been
     * processed, the current node is removable precisely when it is {@code 0} and has no remaining children: at that
     * point, neither this node nor any node in its subtree contains a {@code 1}. Otherwise, the current node remains
     * part of the result.
     *
     * @param root root of the binary tree; may be {@code null}
     * @return the pruned root, or {@code null} when the entire tree contains no {@code 1}
     */
    public TreeNode pruneTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        // Postorder traversal: determine the final state of both children before evaluating this node.
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);

        // After pruning the children, a zero node with no survivors has no 1 in its subtree and can be removed.
        if (root.val == 0 && root.left == null && root.right == null) {
            return null;
        }

        return root;
    }
}
