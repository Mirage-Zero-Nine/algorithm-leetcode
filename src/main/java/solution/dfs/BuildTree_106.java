package solution.dfs;

import library.tree.binarytree.TreeNode;

import java.util.HashMap;

/**
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 *
 * @author BorisMirage
 * Time: 2019/06/10 18:02
 * Created with IntelliJ IDEA
 */

public class BuildTree_106 {
    /**
     * The last element of postorder is always the root of tree.
     *
     * @param inorder   inorder array
     * @param postorder postorder array
     * @return rebuild binary tree
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {

        /* Corner case */
        if (postorder.length == 0) {
            return null;
        }
        if (postorder.length == 1) {
            return new TreeNode(postorder[0]);
        }

        HashMap<Integer, Integer> m = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            m.put(inorder[i], i);
        }

        return buildTree(postorder, postorder.length - 1, 0, 0, inorder.length - 1, m);
    }

    /**
     * Find the root in postorder and then use the inorder array to find child's length and continue.
     * For better understanding, pStart is the last element in postorder, therefore pStart > pEnd.
     *
     * @param postorder postorder array
     * @param pStart    postorder start position in current tree (pStart > pEnd)
     * @param pEnd      postorder end position in current tree (pStart > pEnd)
     * @param inStart   inorder start position in current tree
     * @param inEnd     inorder end position in current tree
     * @param m         hash map
     * @return root node of current tree
     */
    private TreeNode buildTree(int[] postorder, int pStart, int pEnd, int inStart, int inEnd, HashMap<Integer, Integer> m) {

        if (inStart > inEnd || pEnd > pStart) {     // end point
            return null;
        }

        TreeNode root = new TreeNode(postorder[pStart]);
        int inRoot = m.get(postorder[pStart]);
        int leftChild = inRoot - inStart;       // size of left subtree

        root.right = buildTree(postorder, pStart - 1, pEnd + inRoot - inStart, inRoot + 1, inEnd, m);
        root.left = buildTree(postorder, pEnd + leftChild - 1, pEnd, inStart, inRoot - 1, m);

        return root;
    }
}
