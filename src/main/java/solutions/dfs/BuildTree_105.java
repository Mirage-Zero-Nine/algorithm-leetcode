package solutions.dfs;

import library.tree.binarytree.TreeNode;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 *
 * @author BorisMirage
 * Time: 2019/06/10 14:56
 * Created with IntelliJ IDEA
 */
public class BuildTree_105 {

    /**
     * Rebuilds a binary tree from its preorder and inorder traversals.
     *
     * <p>Preorder visits a subtree root before its children, so the first value
     * in each preorder range is that range's root. Inorder visits the left
     * subtree, root, and right subtree in that order. A value-to-index map lets
     * us split the inorder range immediately: values before the root belong to
     * the left subtree, and values after it belong to the right subtree. The
     * number of values in the left range then identifies the next preorder
     * range for the right subtree.</p>
     *
     * <p>The traversal contract requires distinct values and matching arrays.
     * This method returns {@code null} for either {@code null} input or unequal
     * lengths, and it does not modify either input array. Building the map takes
     * O(n) time and space; each value is used once by the recursion, so total
     * time is O(n) and recursion uses O(n) auxiliary space in the worst case
     * (or O(log n) for a balanced tree).</p>
     *
     * @param preorder preorder traversal of the tree
     * @param inorder inorder traversal of the same tree
     * @return the reconstructed root, or {@code null} for a null or mismatched input
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // corner cases
        if (preorder == null || inorder == null || preorder.length != inorder.length) {
            return null;
        }
        if (preorder.length == 1) {
            return new TreeNode(preorder[0]);
        }

        // Store each value's inorder index so that index immediately identifies
        // the left/right subtree split; constant-time lookups avoid rescanning
        // inorder for every subtree, which could make the recursion quadratic.
        Map<Integer, Integer> map = IntStream
                .range(0, inorder.length).boxed()
                .collect(Collectors.toMap(n -> inorder[n], i -> i));

        return buildTree(
                map,
                preorder,
                0,
                preorder.length - 1,
                0,
                inorder.length - 1);
    }

    /**
     * Builds one subtree from inclusive ranges in both traversals.
     *
     * <p>The ranges describe the same set of nodes. Once the root's inorder
     * position is known, the left subtree contains exactly the values before
     * it. Its size determines where the right subtree begins in preorder, which
     * keeps every recursive call aligned with the same nodes.</p>
     *
     * @param map maps each node value to its inorder position
     * @param preorder preorder traversal
     * @param preorderStart first index of this subtree in preorder
     * @param preorderEnd last index of this subtree in preorder
     * @param inorderStart first index of this subtree in inorder
     * @param inorderEnd last index of this subtree in inorder
     * @return the root of the requested subtree, or {@code null} for an empty range
     */
    private TreeNode buildTree(Map<Integer, Integer> map,
                               int[] preorder,
                               int preorderStart,
                               int preorderEnd,
                               int inorderStart,
                               int inorderEnd) {
        if (preorderStart > preorderEnd || inorderStart > inorderEnd) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[preorderStart]);
        // preorderStart holds this subtree's root; its inorder position gives
        // the split, and subtracting inorderStart counts left-subtree nodes.
        int inRoot = map.get(preorder[preorderStart]);
        int leftChildren = inRoot - inorderStart;
        // All ranges are inclusive. The left subtree has leftChildren nodes,
        // so after the root at preorderStart, its preorder range is the next
        // leftChildren entries. The same nodes appear before the root in
        // inorder, from inorderStart through inRoot - 1. If that range is
        // empty, the recursive method returns null for a missing left child.
        root.left = buildTree(
                map,
                preorder,
                preorderStart + 1,
                preorderStart + leftChildren,
                inorderStart,
                inRoot - 1);

        // The root and all left-subtree nodes have now been accounted for in
        // preorder. Therefore the right subtree starts one position after
        // the left range: preorderStart + leftChildren + 1. Its inorder
        // values are the ones after the root, from inRoot + 1 through
        // inorderEnd. An empty range produces a null right child.
        root.right = buildTree(
                map,
                preorder,
                preorderStart + leftChildren + 1,
                preorderEnd,
                inRoot + 1,
                inorderEnd);
        return root;
    }
}
