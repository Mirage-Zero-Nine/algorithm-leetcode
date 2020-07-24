package Solution.BFS;

import Lib.Tree.BinaryTree.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a binary tree, imagine yourself standing on the right side of it.
 * Return the values of the nodes you can see ordered from top to bottom.
 *
 * @author BorisMirage
 * Time: 2019/10/28 12:22
 * Created with IntelliJ IDEA
 */

public class RightSideView_199 {
    /**
     * DFS.
     * Traverse right subtree first, add each rightmost child that first meet in each level.
     *
     * @param root root node
     * @return values of the nodes you can see ordered from top to bottom
     */
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return new LinkedList<>();
        }

        List<Integer> out = new LinkedList<>();
        dfs(root, out, 0);
        return out;
    }

    /**
     * DFS traversal.
     * Each time, try to reach the rightmost child of each node.
     * The "first" node can be found by checking level and output list size.
     * The total number of accept nodes are same as level of tree.
     *
     * @param r     root node
     * @param out   output list
     * @param level # of largest reached level
     */
    private void dfs(TreeNode r, List<Integer> out, int level) {
        if (r == null) {
            return;
        }

        if (level == out.size()) {      // rightmost element in subtree
            out.add(r.val);
        }

        dfs(r.right, out, level + 1);
        dfs(r.left, out, level + 1);
    }

    /**
     * Another basic solution based on BFS.
     * Each time, add all nodes in same level and add the rightmost one to the output list.
     *
     * @param root given root node
     * @return values of the nodes you can see ordered from top to bottom
     */
    public List<Integer> bfs(TreeNode root) {
        if (root == null) {
            return new LinkedList<>();
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        List<Integer> out = new LinkedList<>();
        int size;

        while (!q.isEmpty()) {
            size = q.size();
            TreeNode tmp = null;

            for (int i = 0; i < size; i++) {
                tmp = q.poll();
                if (tmp.left != null) {
                    q.add(tmp.left);
                }
                if (tmp.right != null) {
                    q.add(tmp.right);
                }
            }

            out.add(tmp.val);       // add rightmost node value
        }

        return out;
    }
}
