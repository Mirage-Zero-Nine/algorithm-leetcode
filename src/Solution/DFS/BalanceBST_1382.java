package Solution.DFS;

import Lib.Tree.BinaryTree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary search tree, return a balanced binary search tree with the same node values.
 * A balanced binary search tree is that iff the depth of the two subtrees of every node not differ by more than 1.
 * If there is more than one answer, return any of them.
 *
 * @author BorisMirage
 * Time: 2020/03/16 12:56
 * Created with IntelliJ IDEA
 */

public class BalanceBST_1382 {
    /**
     * Flatten the tree into a array list, then build the tree based on the sorted list.
     * The building process is similar to binary search.
     *
     * @param root root of given BST
     * @return balanced BST
     */
    public TreeNode balanceBST(TreeNode root) {

        /* Corner case */
        if (root == null) {
            return null;
        }

        List<Integer> sorted = new ArrayList<>();       // note that array list is much more faster in this problem
        int[] count = new int[]{0};                     // avoid currency problem caused by global variable

        inorder(sorted, root, count);

        return buildTree(sorted, 0, count[0] - 1);
    }

    /**
     * Inorder traversal to flatten the BST.
     *
     * @param sorted output list
     * @param root   current node of tree
     * @param count  count total nodes in tree
     */
    private void inorder(List<Integer> sorted, TreeNode root, int[] count) {

        if (root == null) {
            return;
        }

        inorder(sorted, root.left, count);
        sorted.add(root.val);
        count[0]++;
        inorder(sorted, root.right, count);
    }

    /**
     * Build new balanced BST based on sorted array.
     *
     * @param sorted sorted array
     * @param left   lower boundary
     * @param right  upper boundary
     * @return balanced BST
     */
    private TreeNode buildTree(List<Integer> sorted, int left, int right) {
        if (left > right) {
            return null;
        }

        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(sorted.get(mid));
        root.left = buildTree(sorted, left, mid - 1);
        root.right = buildTree(sorted, mid + 1, right);

        return root;
    }
}
