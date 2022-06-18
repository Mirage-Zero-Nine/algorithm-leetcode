package solution.bfs;

import library.tree.binarytree.Node;

/**
 * Given a binary tree (not perfect).
 * Populate each next pointer to point to its next right node.
 * If there is no next right node, the next pointer should be set to NULL.
 * Initially, all next pointers are set to NULL.
 *
 * @author BorisMirage
 * Time: 2019/04/30 09:53
 * Created with IntelliJ IDEA
 */

public class Connect_117 {
    /**
     * BFS.
     * Use a Node to store previous node, link to next node.
     * Use a Node to store the next layer's head node for iteration.
     * Under each level, mark the previous node and head of level node as null.
     * If head node is null, connect it to first non-empty node under current level.
     * Each time, if current node is not null, update previous node until the end of level.
     * Time complexity: O(n)
     * Space complexity: O(1)
     *
     * @param root root node
     * @return populate tree
     */
    public Node connect(Node root) {
        Node dummyLevelHead = null;
        Node previous = null;
        Node current = root;

        while (current != null) {       // loop all nodes in tree
            while (current != null) {       // loop current level
                if (current.left != null) {
                    if (previous == null) {     // new level start
                        dummyLevelHead = current.left;
                    } else {
                        previous.next = current.left;
                    }
                    previous = current.left;
                }

                if (current.right != null) {
                    if (previous == null) {
                        dummyLevelHead = current.right;
                    } else {
                        previous.next = current.right;
                    }
                    previous = current.right;
                }

                current = current.next;     // update current node
            }

            current = dummyLevelHead;       // move to next level
            dummyLevelHead = null;          // reset level start node and previous node in new level
            previous = null;
        }

        return root;
    }
}
