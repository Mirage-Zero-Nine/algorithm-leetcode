package Solution.DFS;

import Lib.Tree.BinaryTree.TreeNode;

/**
 * Two random elements of a binary search tree (BST) are swapped by mistake.
 * Recover the tree without changing its structure.
 *
 * @author BorisMirage
 * Time: 2019/02/22 22:58
 * Created with IntelliJ IDEA
 */

public class RecoverTree_99 {
    /**
     * Morris traversal. Store the root node of current subtree to right most leaf's right child.
     * Space: O(1).
     *
     * @param root root node of tree
     */
    public void recoverTree(TreeNode root) {
        TreeNode temp;
        TreeNode t1 = null, t2 = null;
        TreeNode previous = null;

        /*
         * Morris Traversal.
         * To complete the traversal without O(n) space, use the null in leaf node to store root of current sub tree.
         * Each time, set the right child of right leaf of current tree to point to root of current tree.
         * When in-order traversal is completed, the right child of last node is the root of current tree.
         * In this way, the right child of leaf (null) can be used as stack and to save space. */
        while (root != null) {
            if (root.left != null) {       // in order traversal, not reaches the end of left most
                temp = root.left;
                while (temp.right != null && temp.right != root) {      // move temp to left subtree's right most
                    temp = temp.right;
                }

                if (temp.right == null) {      // node not traversed yet
                    temp.right = root;
                    root = root.left;       // move to left subtree to repeat process
                } else {        // node has been traversed
                    if (previous != null && previous.val > root.val) {
                        if (t1 == null) {
                            t1 = previous;
                        }
                        t2 = root;
                    }
                    previous = root;
                    temp.right = null;
                    root = root.right;
                }
            } else {
                if (previous != null && previous.val > root.val) {
                    if (t1 == null) {
                        t1 = previous;
                    }
                    t2 = root;
                }
                previous = root;
                root = root.right;
            }
        }

        if (t1 != null) {       // swap two nodes value
            int t = t1.val;
            t1.val = t2.val;
            t2.val = t;
        }
    }

    private TreeNode wrong1 = null;
    private TreeNode wrong2 = null;
    private TreeNode previous = new TreeNode(Integer.MIN_VALUE);        // avoid null when root is the deepest left node

    /**
     * Normal in-order traversal approach. This method takes O(n) space.
     * The factor is to find two "root" nodes to swap.
     * Therefore, use in order traversal would found the incorrect left node and then incorrect right node.
     * This equals to add a "check" operation between two sub nodes.
     *
     * @param root root node of tree
     */
    public void normalInorderTraversal(TreeNode root) {
        traverse(root);

        /* Swap incorrect value */
        wrong1.val = wrong1.val + wrong2.val;
        wrong2.val = wrong1.val - wrong2.val;
        wrong1.val = wrong1.val - wrong2.val;
    }

    /**
     * In order traversal.
     *
     * @param r temp root node
     */
    private void traverse(TreeNode r) {
        if (r == null) {
            return;
        }

        traverse(r.left);

        if (wrong1 == null && previous.val >= r.val) {
            wrong1 = previous;      // left incorrect node, based on in order traversal (left -> root -> right)
        }
        if (wrong1 != null && previous.val >= r.val) {
            wrong2 = r;      // right incorrect node, based on in order traversal (left -> root -> right)
        }

        previous = r;
        traverse(r.right);
    }
}
