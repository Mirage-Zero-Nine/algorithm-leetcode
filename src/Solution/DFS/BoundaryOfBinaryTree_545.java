package Solution.DFS;

import Lib.Tree.BinaryTree.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * Given a binary tree, return the values of its boundary in anti-clockwise direction starting from root.
 * Boundary includes left boundary, leaves, and right boundary in order without duplicate nodes.
 * (The values of the nodes may still be duplicates.)
 * Left boundary is defined as the path from root to the left-most node.
 * Right boundary is defined as the path from root to the right-most node.
 * If the root doesn't have left subtree or right subtree, then the root itself is left boundary or right boundary.
 * Note this definition only applies to the input binary tree, and not applies to any subtrees.
 * The left-most node is defined as a leaf node you could reach when you firstly travel to the left subtree if exists.
 * If not, travel to the right subtree. Repeat until you reach a leaf node.
 * The right-most node is also defined by the same way with left and right exchanged.
 *
 * @author BorisMirage
 * Time: 2020/03/03 17:46
 * Created with IntelliJ IDEA
 */

public class BoundaryOfBinaryTree_545 {
    /**
     * 4 times of DFS.
     * First time, find all left boundary nodes except left most leaf.
     * Second time, find all leaves nodes under left subtree of root.
     * Third time, find all leaves nodes under right subtree of root.
     * Forth time, find all right boundary nodes except right most leaf.
     * Note that the order is reversed compare to left boundary.
     * In this way to avoid duplication.
     *
     * @param root root of tree
     * @return the values of its boundary in anti-clockwise direction starting from root
     */
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {

        /* Corner case */
        if (root == null) {
            return new LinkedList<>();
        }

        List<Integer> out = new LinkedList<>();
        out.add(root.val);

        leftBoundary(root.left, out);       // left boundary
        leaves(root.left, out);             // leaves under left subtree of root
        leaves(root.right, out);            // leaves under right subtree of root
        rightBound(root.right, out);        // right boundary

        return out;
    }

    /**
     * Add all left boundary nodes of tree except left most leaf.
     *
     * @param root root node
     * @param out  output list
     */
    private void leftBoundary(TreeNode root, List<Integer> out) {
        if (root == null || (root.left == null && root.right == null)) {
            return;
        }

        out.add(root.val);

        if (root.left == null) {
            leftBoundary(root.right, out);
        } else {
            leftBoundary(root.left, out);
        }
    }

    /**
     * Add all leaves of current tree.
     *
     * @param root root of tree
     * @param out  output list
     */
    private void leaves(TreeNode root, List<Integer> out) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            out.add(root.val);
            return;
        }

        leaves(root.left, out);
        leaves(root.right, out);
    }

    /**
     * Add all right boundary nodes of tree except right most leaf.
     *
     * @param root root node
     * @param out  output list
     */
    private void rightBound(TreeNode root, List<Integer> out) {
        if (root == null || (root.left == null && root.right == null)) {
            return;
        }

        if (root.right == null) {
            rightBound(root.left, out);
        } else {
            rightBound(root.right, out);
        }

        out.add(root.val);      // reverse order
    }
}
