package Solution.BFS;

import Lib.Tree.BinaryTree.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Given the root of a binary tree, return the maximum width of the given tree.
 * The maximum width of a tree is the maximum width among all levels.
 * The width of one level is defined as the length between the end-nodes (the leftmost and rightmost non-null nodes).
 * The null nodes between the end-nodes are also counted into the length calculation.
 * It is guaranteed that the answer will in the range of 32-bit signed integer.
 *
 * @author BorisMirage
 * Time: 2021/10/26 15:10
 * Created with IntelliJ IDEA
 */

public class WidthOfBinaryTree_662 {
    /**
     * Modified BFS. Each time, add the non-null left sub-tree and right sub-tree node to the queue.
     * Change the root value to the index of the current node.
     * In this way, the width can be directly calculated by checking the beginning and the ending of the queue.
     *
     * @param root root of the tree
     * @return the maximum width of the given tree
     */
    public int widthOfBinaryTree(TreeNode root) {

        /* Corner case */
        if (root == null) {
            return 0;
        }

        Deque<TreeNode> q = new LinkedList<>();
        root.val = 0;
        q.add(root);
        int maxWidth = 0, width;
        while (!q.isEmpty()) {
            int size = q.size();
            width = q.peekLast().val - q.peekFirst().val + 1;
            for (int i = 0; i < size; i++) {
                TreeNode current = q.poll();
                if (current.left != null) {
                    current.left.val = current.val * 2; // index of left sub-tree
                    q.add(current.left);
                }
                if (current.right != null) {
                    current.right.val = current.val * 2 + 1; // index of right sub-tree
                    q.add(current.right);
                }
            }
            maxWidth = Math.max(maxWidth, width);
        }

        return maxWidth;
    }
}
