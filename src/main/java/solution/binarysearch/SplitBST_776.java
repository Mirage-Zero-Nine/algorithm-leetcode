package solution.binarysearch;

import library.tree.binarytree.TreeNode;

import java.util.Stack;

/**
 * Given a Binary Search Tree (BST) with root node root, and a target value V.
 * Split the tree into two subtrees, one subtree nodes are all <= target value, while the other > target value.
 * It's not necessarily the case that the tree contains a node with value V.
 * Additionally, most of the structure of the original tree should remain.
 * You should output the root TreeNode of both subtrees after splitting, in any order.
 *
 * @author BorisMirage
 * Time: 2019/10/24 13:01
 * Created with IntelliJ IDEA
 */

public class SplitBST_776 {
    /**
     * DFS with BST character.
     * If the target is larger than root, the only consider the right subtree.
     * Otherwise, only consider the left subtree.
     * The first tree in array is the smaller one, and the second is larger one.
     * Therefore, the base case is when root is null, return two null nodes.
     * Otherwise, if target < root value, attach return array's second element to root's left subtree.
     * The second element in return array are elements larger than target, but smaller than root.
     * If target >= root value, attach return array's first element to root's right subtree with same reason.
     *
     * @param root root node
     * @param v    target value
     * @return two subtrees, one subtree nodes are all <= target value, while the other > target value
     */
    public TreeNode[] splitBST(TreeNode root, int v) {

        /* Corner case */
        if (root == null) {
            return new TreeNode[2];
        }

        if (v < root.val) {
            TreeNode[] left = splitBST(root.left, v);
            root.left = left[1];
            return new TreeNode[]{left[0], root};
        } else {
            TreeNode[] right = splitBST(root.right, v);
            root.right = right[0];
            return new TreeNode[]{root, right[1]};
        }
    }

    /**
     * Iterative approach with a stack to simulate the recursive process.
     *
     * @param root root node
     * @param v    target value
     * @return two subtrees, one subtree nodes are all <= target value, while the other > target value
     */
    public TreeNode[] iterative(TreeNode root, int v) {

        /* Corner case */
        if (root == null) {
            return new TreeNode[2];
        }

        TreeNode[] out = new TreeNode[2];
        Stack<TreeNode> s = new Stack<>();
        TreeNode tmp = root;

        while (tmp != null) {       // push all considered nodes into stack
            s.push(tmp);
            tmp = (v < tmp.val) ? tmp.left : tmp.right;
        }

        while (!s.isEmpty()) {
            tmp = s.pop();
            if (v < tmp.val) {
                tmp.left = out[1];
                out[1] = tmp;
            } else {
                tmp.right = out[0];
                out[0] = tmp;
            }
        }

        return out;
    }
}
