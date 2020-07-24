package Solution.BFS;

import Lib.Tree.BinaryTree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree, find the leftmost value in the last row of the tree.
 * i.e., return the first element in last layer (from left to right).
 *
 * @author BorisMirage
 * Time: 2019/05/31 13:02
 * Created with IntelliJ IDEA
 */

public class FndBottomLeftValue_513 {
    /**
     * BFS level order traversal.
     *
     * @param root root node
     * @return the first element in last layer (from left to right)
     */
    public int findBottomLeftValue(TreeNode root) {

        /* Corner case */
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        TreeNode n;
        int val = root.val;
        while (!q.isEmpty()) {
            int size = q.size();
            val = q.peek().val;
            for (int i = 0; i < size; i++) {
                n = q.poll();
                if (n != null) {
                    if (n.left != null) {
                        q.add(n.left);
                    }
                    if (n.right != null) {
                        q.add(n.right);
                    }
                }
            }
        }
        return val;
    }
}
