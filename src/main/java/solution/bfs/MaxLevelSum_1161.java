package solution.bfs;

import library.tree.binarytree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.
 * Return the smallest level X such that the sum of all the values of nodes at level X is maximal.
 *
 * @author BorisMirage
 * Time: 2019/08/17 19:32
 * Created with IntelliJ IDEA
 */

public class MaxLevelSum_1161 {
    /**
     * BFS level traversal.
     *
     * @param root given root node
     * @return smallest level X such that the sum of all the values of nodes at level X is maximal
     */
    public int maxLevelSum(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int size, maxLevel = 1, level = 1, max = Integer.MIN_VALUE;

        while (!q.isEmpty()) {
            int sum = 0;
            size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode n = q.poll();
                if (n != null) {
                    sum += n.val;
                    if (n.left != null) {
                        q.add(n.left);
                    }
                    if (n.right != null) {
                        q.add(n.right);
                    }
                }
            }

            System.out.println(sum);
            if (max < sum) {
                max = sum;
                maxLevel = level;
            }
            level++;
        }

        return maxLevel;
    }
}
