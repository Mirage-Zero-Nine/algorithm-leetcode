package Solution.LinkedList;

import Lib.Tree.BinaryTree.Node;

/**
 * Given two nodes of a binary tree p and q, return their lowest common ancestor (LCA).
 * The tree node contains left child and right child, and the current node's parent.
 * Root node has a null parent.
 *
 * @author BorisMirage
 * Time: 2020/11/14 20:58
 * Created with IntelliJ IDEA
 */

public class LowestCommonAncestor_1650 {
    /**
     * The tree node contains a pointer to its parent. Hence, the tree is actually a binary linked list.
     * To find the LCA is actually to find the intersection of the two nodes.
     * Start at each node, move to the parent node at each move. If reaches the root, move to the opposite start node.
     * Finally, they will meet at a node, which is the LCA.
     *
     * @param p first node
     * @param q second node
     * @return lowest common ancestor of p and q
     */
    public Node lowestCommonAncestor(Node p, Node q) {
        Node p1 = p, p2 = q;

        while (p1 != p2) {
            p1 = (p1 == null) ? q : p1.parent;
            p2 = (p2 == null) ? p : p2.parent;
        }

        return p1;
    }
}
