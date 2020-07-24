package Solution.DFS;

import Lib.Tree.BinaryTree.TreeNode;

/**
 * Given a binary tree where each path going from the root to any leaf form a valid sequence.
 * Check if a given string is a valid sequence in such binary tree.
 * A valid sequence is a path starts at root of tree, and ends at any leaf.
 *
 * @author BorisMirage
 * Time: 2020/05/11 16:33
 * Created with IntelliJ IDEA
 */

public class IsValidSequence_1430 {
    /**
     * Find path from root to leaf and check if current path is a valid sequence.
     * Conditions that a path is valid:
     * 1. Path should start from root and ends at leaf.
     * 2. Path length should be equal to the array length, which means the depth of path should be same to array length.
     * Hence, a simple DFS can finish this task. Keep an integer as index during the searching.
     * If current node's value is not match, end search and return false.
     * If current node matches the end of array, but it is not leaf node, return false.
     * If current node is null, return false.
     * Otherwise, searching in children of current node.
     *
     * @param root root of given tree
     * @param arr  given array
     * @return if a given string is a valid sequence in such binary tree
     */
    public boolean isValidSequence(TreeNode root, int[] arr) {

        return dfs(root, arr, 0);
    }

    /**
     * DFS to find a valid path.
     *
     * @param root  current node
     * @param arr   given array
     * @param index current element in array
     * @return if current path is a valid sequence
     */
    private boolean dfs(TreeNode root, int[] arr, int index) {

        if (root == null || index >= arr.length || arr[index] != root.val) {
            return false;
        }

        if (root.left == null && root.right == null) {
            return index == arr.length - 1;
        }

        return dfs(root.left, arr, index + 1) || dfs(root.right, arr, index + 1);
    }
}
