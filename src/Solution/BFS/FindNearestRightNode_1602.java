package Solution.BFS;

import Lib.Tree.BinaryTree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given the root of a binary tree and a node u in the tree.
 * Return the nearest node on the same level that is to the right of u, or null if u is the rightmost node in its level.
 *
 * @author BorisMirage
 * Time: 2020/10/02 22:58
 * Created with IntelliJ IDEA
 */

public class FindNearestRightNode_1602 {
    /**
     * BFS to iterate the tree.
     * Two cases to return nul:
     * 1. Target node is the last node in tree.
     * 2. Target node is the last node in current level.
     *
     * @param root root node
     * @param u    target node
     * @return the nearest node on the same level to the right of u, or null if u is the rightmost node in its level
     */
    public TreeNode findNearestRightNode(TreeNode root, TreeNode u) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int size = q.size();
        while (!q.isEmpty()) {
            while (size-- > 0) {
                TreeNode tmp = q.poll();
                if (tmp.val == u.val) {
                    return q.isEmpty() || size == 0 ? null : q.poll();      // last node in current level or whole tree
                }
                if (tmp.left != null) {
                    q.add(tmp.left);
                }
                if (tmp.right != null) {
                    q.add(tmp.right);
                }
            }
            size = q.size();
        }

        return null;
    }
}
