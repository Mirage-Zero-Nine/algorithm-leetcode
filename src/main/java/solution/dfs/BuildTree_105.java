package solution.dfs;

import library.tree.binarytree.TreeNode;

import java.util.HashMap;

/**
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 *
 * @author BorisMirage
 * Time: 2019/06/10 14:56
 * Created with IntelliJ IDEA
 */
public class BuildTree_105 {
    /**
     * preorder[0] is the root node.
     * Since array does not contain duplicated value, root node can be found in inorder[].
     * The left part of array in inorder[] is the left child of root, right part is right child.
     * Then preorder[1] is the left child's root, preorder[2] is the right child's root.
     * Repeat this process and finally this tree can be rebuilt.
     *
     * @param preorder pre order traversal array
     * @param inorder  in order traversal array
     * @return constructed tree
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {

        /* Corner case */
        if (preorder == null || inorder == null || inorder.length == 0 || inorder.length != preorder.length) {
            return null;
        }
        if (preorder.length == 1) {
            return new TreeNode(preorder[0]);
        }

        HashMap<Integer, Integer> m = new HashMap<>();      // pair: node and its index in inorder array
        for (int i = 0; i < inorder.length; i++) {
            m.put(inorder[i], i);
        }

        return buildTree(preorder, 0, preorder.length - 1, 0, inorder.length - 1, m);
    }

    /**
     * Find root of children in inorder array and in this way to find their left and right children.
     * The start position of preorder array is the root of current tree.
     * Find the position of current root in inorder array.
     * Then the size of left subtree can be found, which is the left part of inorder array.
     * And the root of left subtree is the next value in preorder array.
     * The size of right subtree can be found as well, which is the right part of inorder array.
     * Based on size of left subtree, the root of right subtree can be found.
     * Pass the parameter into the recursion, until start position is larger than end position.
     *
     * @param preorder preorder traversal array
     * @param preStart root in preorder traverse array
     * @param preEnd   end of tree in preorder array
     * @param inStart  start of left children
     * @param inEnd    end of right children
     * @param m        hash map for quickly find root index in inorder array
     * @return root node of current tree
     */
    private TreeNode buildTree(int[] preorder, int preStart, int preEnd, int inStart, int inEnd, HashMap<Integer, Integer> m) {

        if (preStart > preEnd || inStart > inEnd) {
            return null;        // end point
        }

        TreeNode root = new TreeNode(preorder[preStart]);       // first element in preorder is the root of current tree
        int inRoot = m.get(preorder[preStart]);                 // find index of root in inorder array
        int leftChild = inRoot - inStart;       // left part of root in inorder array is left child of current root node

        root.left = buildTree(preorder, preStart + 1, preStart + leftChild, inStart, inRoot - 1, m);
        root.right = buildTree(preorder, preStart + leftChild + 1, preEnd, inRoot + 1, inEnd, m);
        return root;
    }
}
