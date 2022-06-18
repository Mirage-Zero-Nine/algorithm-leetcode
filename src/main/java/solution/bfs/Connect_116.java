package solution.bfs;

import library.tree.binarytree.Node;

/**
 * A perfect binary tree is given where all leaves are on the same level, and every parent has two children.
 * Populate each next pointer to point to its next right node.
 * If there is no next right node, the next pointer should be set to NULL.
 * Initially, all next pointers are set to NULL.
 * Note:
 * 1. You may only use constant extra space.
 * 2. Recursive approach is fine, implicit stack space does not count as extra space for this problem.
 *
 * @author BorisMirage
 * Time: 2019/04/28 17:34
 * Created with IntelliJ IDEA
 */

public class Connect_116 {
    /**
     * BFS.
     * Each level in perfect binary search is full.
     * left sub node -> right sub node
     * right sub node -> next left sub node
     * If reach the end of current layer node, do nothing. The default next is null.
     *
     * @param root root node
     * @return populated tree
     */
    public Node connect(Node root) {
        Node level = root;

        while (level != null) {
            Node current = level;
            while (current != null) {

                if (current.left != null) {
                    current.left.next = current.right;      // link left and right sub node under same parent node
                }

                if (current.right != null && current.next != null) {
                    current.right.next = current.next.left;     // link right sub node to next parent node's left sub node
                }
                current = current.next;     // next node
            }
            level = level.left;     // next layer
        }

        return root;
    }
}
