package solution.dfs;

import library.tree.binarytree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree, return all root-to-leaf paths.
 *
 * @author BorisMirage
 * Time: 2019/06/03 15:48
 * Created with IntelliJ IDEA
 */

public class BinaryTreePaths_257 {
    /**
     * DFS, pre-order traversal.
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
     * Basic DFS.
     *
     * @param n      node
     * @param s      path string
     * @param output all path from root to leaf
     */
    private void dfs(TreeNode n, String s, List<String> output) {
        if (n.right == null && n.left == null) {
            output.add(s + n.val);      // end point
        }

        if (n.left != null) {
            dfs(n.left, s + n.val + "->", output);
        }
        if (n.right != null) {
            dfs(n.right, s + n.val + "->", output);
        }
    }
}
