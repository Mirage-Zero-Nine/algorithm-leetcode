package Solution.DFS;

import Lib.RandomNode.Node;
import Lib.RandomNode.NodeCopy;

import java.util.HashMap;

/**
 * A binary tree is given such that each node contains a random pointer could point to any node in the tree or null.
 * Return a deep copy of the tree.
 *
 * @author BorisMirage
 * Time: 2020/06/19 17:13
 * Created with IntelliJ IDEA
 */

public class CopyRandomBinaryTree_1485 {
    /**
     * One-pass DFS to traverse the tree, and add random node to new created node when DFS completed.
     * Note that the random node may not be created when DFS completed.
     * Therefore, to link the random node in newly created node, call DFS to check the node has been created or not.
     * If not, continue using DFS to construct the new tree. Otherwise, simply return the new node.
     * Hence, DFS will be called 3 times, left child, right child, and random child.
     *
     * @param root root of tree
     * @return deep copy of the tree
     */
    public NodeCopy copyRandomBinaryTree(Node root) {
        HashMap<Node, NodeCopy> m = new HashMap<>();

        return dfs(root, m);
    }

    /**
     * DFS to traverse the tree.
     *
     * @param root current node
     * @param m    hash map mapping the old node and newly created node
     * @return deep copy of the tree
     */
    private NodeCopy dfs(Node root, HashMap<Node, NodeCopy> m) {

        /* Corner case */
        if (root == null) {
            return null;
        }

        if (m.containsKey(root)) {      // avoid duplicate
            return m.get(root);
        }

        NodeCopy tmp = new NodeCopy(root.val);
        m.put(root, tmp);

        tmp.left = dfs(root.left, m);
        tmp.right = dfs(root.right, m);
        tmp.random = dfs(root.random, m);       // random node may have not been created yet

        return tmp;
    }
}
