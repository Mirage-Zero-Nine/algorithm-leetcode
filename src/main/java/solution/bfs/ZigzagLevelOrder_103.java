package solution.bfs;

import library.tree.binarytree.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a binary tree, return the zigzag level order traversal of its nodes' values.
 * i.e., from left to right, then right to left for the next level and alternate between.
 *
 * @author BorisMirage
 * Time: 2019/04/28 16:51
 * Created with IntelliJ IDEA
 */

public class ZigzagLevelOrder_103 {
    /**
     * Two double linked lists. One to store current level and next level, one to store values in correct order.
     * Add a boolean to check whether which order should be taken.
     *
     * @param root root node
     * @return zigzag level order traversal
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> out = new LinkedList<>();

        /* Corner case */
        if (root == null) {
            return out;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        boolean inverse = true;

        while (!q.isEmpty()) {
            int s = q.size();
            LinkedList<Integer> level = new LinkedList<>();
            for (int i = 0; i < s; i++) {
                TreeNode temp = q.poll();
                if (temp != null) {
                    if (inverse) {
                        level.add(temp.val);
                    } else {
                        level.addFirst(temp.val);
                    }
                    if (temp.left != null) {
                        q.add(temp.left);
                    }
                    if (temp.right != null) {
                        q.add(temp.right);
                    }
                }
            }
            inverse ^= true;
            out.add(level);
        }
        return out;
    }
}
