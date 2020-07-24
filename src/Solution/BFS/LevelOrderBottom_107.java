package Solution.BFS;

import Lib.Tree.BinaryTree.TreeNode;

import java.util.LinkedList;
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
     * Use a double linked list to store level. Change order from add to addFirst.
     *
     * @param root root node of tree
     * @return bottom-up level order traversal
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> out = new LinkedList<>();

        if (root == null) {
            return out;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            int s = q.size();
            List<Integer> level = new LinkedList<>();
            for (int i = 0; i < s; i++) {
                TreeNode temp = q.poll();
                if (temp != null) {
                    level.add(temp.val);
                    if (temp.left != null) {
                        q.add(temp.left);
                    }
                    if (temp.right != null) {
                        q.add(temp.right);
                    }
                }
            }
            out.addFirst(level);
        }
        return out;
    }
}
