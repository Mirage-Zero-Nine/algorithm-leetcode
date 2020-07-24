package Solution.DFS;

import Lib.Tree.BinaryTree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree, return the in order traversal of its nodes' values.
 *
 * @author BorisMirage
 * Time: 2018/10/02 20:22
 * Created with IntelliJ IDEA
 */

public class InorderTraversal_94 {
    /**
     * Simply follow the in order traversal rule.
     * Use stack to temporary store node during the process.
     *
     * @param root root node
     * @return list of nodes in inorder traversal order
     */
    public List<Integer> inorderTraversal(TreeNode root) {

        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> s = new Stack<>();
        TreeNode cur = root;

        /* Traverse process */
        while (cur != null || !s.isEmpty()) {

            while (cur != null) {
                s.add(cur);
                cur = cur.left;
            }

            cur = s.pop();
            res.add(cur.val);
            cur = cur.right;
        }

        return res;
    }
}
