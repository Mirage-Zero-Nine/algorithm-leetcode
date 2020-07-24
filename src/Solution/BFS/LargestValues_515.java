package Solution.BFS;

import Lib.Tree.BinaryTree.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Find the largest value in each level of a binary tree.
 *
 * @author BorisMirage
 * Time: 2019/05/31 13:59
 * Created with IntelliJ IDEA
 */

public class LargestValues_515 {
    /**
     * BFS level order traversal.
     *
     * @param root root node
     * @return largest value in each level of a binary tree
     */
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> out = new LinkedList<>();
        if (root == null) {
            return out;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int s = q.size();
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < s; i++) {

                TreeNode n = q.poll();
                if (n != null) {
                    int v = n.val;
                    max = v > max ? v : max;        // find max in current level
                    if (n.left != null) {
                        q.add(n.left);
                    }
                    if (n.right != null) {
                        q.add(n.right);
                    }
                }
            }
            out.add(max);
        }
        return out;
    }
}
