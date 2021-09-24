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
     * In this case, tree node contains a pointer to its parent. Hence, this tree is actually a binary linked list.
     * Finding the LCA of these nodes are actually to find the intersection of the two nodes.
     * Starting at each node, move toward the parent each time.
     * If reaches the root (the end of the linked list), move to the opposite start node.
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
