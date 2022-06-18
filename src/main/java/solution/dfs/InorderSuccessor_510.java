package solution.dfs;

import library.tree.binarytree.Node;

/**
 * Given a node in a binary search tree, find the in-order successor of that node in the BST.
 * If that node has no in-order successor, return null.
 * The successor of a node is the node with the smallest key greater than node.val.
 * You will have direct access to the node but not to the root of the tree.
 * Each node will have a reference to its parent node.
 *
 * @author BorisMirage
 * Time: 2020/02/07 11:25
 * Created with IntelliJ IDEA
 */

public class InorderSuccessor_510 {
    /**
     * Two conditions.
     * 1. If right child is not null, find the left most child in right of current node.
     * 2. If right child is null, then the successor could be one of ancestor or null.
     * Under the second condition, find the parent that its left child points to it.
     *
     * @param node node to find its successor
     * @return successor of given node
     */
    public Node inorderSuccessor(Node node) {

        /* Corner case */
        if (node == null) {
            return null;
        }

        if (node.right != null) {
            node = node.right;
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }

        while (node.parent != null) {
            if (node.parent.left == node) {     // if node's parent's left child is same, then the successor is found
                return node.parent;
            }
            node = node.parent;
        }

        return null;
    }
}
