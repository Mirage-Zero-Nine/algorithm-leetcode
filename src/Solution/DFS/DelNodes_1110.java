package Solution.DFS;

import Lib.Tree.BinaryTree.TreeNode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Given the root of a binary tree, each node in the tree has a distinct value.
 * After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).
 * Return the roots of the trees in the remaining forest. You may return the result in any order.
 *
 * @author BorisMirage
 * Time: 2019/07/16 22:14
 * Created with IntelliJ IDEA
 */

public class DelNodes_1110 {
    private List<TreeNode> out = new LinkedList<>();
    private HashSet<Integer> s = new HashSet<>();

    /**
     * DFS recursion.
     *
     * @param root      root node
     * @param to_delete nodes to be deleted
     * @return the roots of the trees in the remaining forest
     */
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        if (root == null) {
            return null;
        }

        for (int value : to_delete) {
            s.add(value);
        }

        dfs(root, true);
        return this.out;
    }

    /**
     * DFS traverse tree to find nodes that are in delete set.
     * If current node requires to be deleted, then mark its sub tree as new root and continue.
     * Note that if this node requires to be removed, then return null.
     * Otherwise, return node it self will suffice.
     *
     * @param r      root node
     * @param isRoot is current node a new root
     * @return if current node requires to be removed, return null; otherwise return current node
     */
    private TreeNode dfs(TreeNode r, boolean isRoot) {
        if (r == null) {
            return null;
        }

        boolean deleted = s.contains(r.val);

        if (isRoot && !deleted) {
            out.add(r);
        }

        r.left = dfs(r.left, deleted);
        r.right = dfs(r.right, deleted);

        return deleted ? null : r;
    }
}
