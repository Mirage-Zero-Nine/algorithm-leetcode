package Solution.BFS;

import Lib.Tree.BinaryTree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree, determine if it is a complete binary tree.
 *
 * @author BorisMirage
 * Time: 2019/07/13 16:10
 * Created with IntelliJ IDEA
 */

public class IsCompleteTree_958 {
    /**
     * BFS to last level. If find an empty node, then the BFS queue will only have null nodes.
     * If any non-null nodes in queue, then it is not complete.
     *
     * @param root root node
     * @return if tree is complete binary tree
     */
    public boolean isCompleteTree(TreeNode root) {

        /* Corner case */
        if (root == null) {
            return true;
        }

        Queue<TreeNode> q = new LinkedList<>();

        q.offer(root);

        while (q.peek() != null) {      // add all nodes (include null) to queue
            TreeNode node = q.poll();
            q.offer(node.left);
            q.offer(node.right);
        }

        while (!q.isEmpty() && q.peek() == null) {      // remove all null nodes
            q.poll();
        }

        return q.isEmpty();     // if queue is not empty, then there exist leave node that after null node
    }
}
