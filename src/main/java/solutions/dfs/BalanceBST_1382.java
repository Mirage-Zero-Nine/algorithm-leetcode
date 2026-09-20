package solutions.dfs;

import library.tree.binarytree.TreeNode;

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
     * Iterative inorder traversal to flatten the BST. The explicit stack keeps
     * a valid but maximally skewed 10,000-node input from exhausting the Java
     * call stack.
     *
     * @param sorted output list
     * @param root   current node of tree
     * @param count  count total nodes in tree
     */
    private void inorder(List<Integer> sorted, TreeNode root, int[] count) {
        List<TreeNode> stack = new ArrayList<>();
        TreeNode current = root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.add(current);
                current = current.left;
            }
            current = stack.remove(stack.size() - 1);
            sorted.add(current.val);
            count[0]++;
            current = current.right;
        }
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
