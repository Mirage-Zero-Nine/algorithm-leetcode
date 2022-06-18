package solution.dfs;

import library.tree.binarytree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree, return the in order traversal of its nodes' values.
 *
 * @author BorisMirage
 * Time: 2018/10/02 20:22
 * Created with IntelliJ IDEA
 */

public class InorderTraversal_94 {
    /**
     * Simply follow the in order traversal rule: left, root, right.
     * Use stack to temporary store node during the process.
     *
     * @param root root node
     * @return list of nodes in inorder traversal order
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> output = new ArrayList<>();

        /* Corner case */
        if (root == null) {
            return output; // this is actually not required in this case
        }

        dfs(root, output);
        return output;
    }

    private void dfs(TreeNode root, List<Integer> output) {
        if (root == null) {
            return;
        }

        dfs(root.left, output); // traverse to left most node (which is actually always null)
        output.add(root.val); // add root (which is the left most non-null node)
        dfs(root.right, output); // go to the right sub-tree
    }
}
