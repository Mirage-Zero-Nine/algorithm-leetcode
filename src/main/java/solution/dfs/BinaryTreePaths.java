package solution.dfs;

import library.tree.binarytree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 257: Given a binary tree, return all root-to-leaf paths.
 *
 * @author BorisMirage
 * Time: 2019/06/03 15:48
 * Created with IntelliJ IDEA
 */

public class BinaryTreePaths {
    /**
     * Pre-order traversal.
     * Note it's only needed to traversal to the leaf node instead of the child of leaf node.
     * In the recursion it's not able to tell if current node is from the child of leaf node (although they are null).
     *
     * @param root root node
     * @return all path from root to leaf
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> output = new ArrayList<>();
        if (root != null) {
            dfs(root, "", output);
        }
        return output;
    }

    /**
     * DFS till leaf node.
     * Add path to output list when traveling to leaf node.
     * Use string instead of StringBuilder to make sure there is no duplicate path since string is immutable.
     * If to use StringBuilder, be sure to reset it to its previous length when adding string to output list.
     * Same as what is required for backtracking.
     *
     * @param n      node
     * @param path   path string
     * @param output all path from root to leaf
     */
    private void dfs(TreeNode n, String path, List<String> output) {
        if (n.left == null && n.right == null) {
            output.add(path + n.val);
        }
        String currentPath = path + n.val + "->";
        if (n.left != null) {
            dfs(n.left, currentPath, output);
        }
        if (n.right != null) {
            dfs(n.right, currentPath, output);
        }
    }
}
