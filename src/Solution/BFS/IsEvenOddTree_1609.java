package Solution.BFS;

import Lib.Tree.BinaryTree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * A binary tree is named Even-Odd if it meets the following conditions:
 * 1. The root of the binary tree is at level 0, its children are at level 1, their children are at level index 2, etc.
 * 2. For every even-indexed level, all nodes have odd integer values in strictly increasing order (from left to right).
 * 3. For every odd-indexed level, all nodes have even integer values in strictly decreasing order (from left to right).
 * Given the root of a binary tree, return true if the binary tree is Even-Odd, otherwise return false.
 *
 * @author BorisMirage
 * Time: 2020/10/09 19:55
 * Created with IntelliJ IDEA
 */

public class IsEvenOddTree_1609 {
    /**
     * BFS, check each node in this level.
     * Since in each level the increasing/decreasing order should be checked, keep an integer for previous node value.
     * At the start of each level, it should be set to Integer.MIN_VALUE or Integer.MAX_VALUE, depending on level index.
     *
     * @param root root of the tree
     * @return if the binary tree is Even-Odd
     */
    public boolean isEvenOddTree(TreeNode root) {

        /* Corner case */
        if (root.val % 2 == 0) {
            return false;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        boolean isEven = true;
        int size = q.size(), previous;

        while (!q.isEmpty()) {
            previous = isEven ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode current = q.poll();
                if (isEven && (current.val <= previous || current.val % 2 == 0)) {
                    return false;
                }
                if (!isEven && (current.val >= previous || current.val % 2 != 0)) {
                    return false;
                }
                if (current.left != null) {
                    q.add(current.left);
                }
                if (current.right != null) {
                    q.add(current.right);
                }
                previous = current.val;
            }

            size = q.size();
            isEven = !isEven;
        }

        return true;
    }
}
