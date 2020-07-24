package Solution.DFS;

import Lib.Tree.NaryTree.Node;

import java.util.LinkedList;
import java.util.List;

/**
 * Given a root of an N-ary tree, return a deep copy (clone) of the tree.
 * Each node in the n-ary tree contains a val (int) and a list (List[Node]) of its children.
 *
 * @author BorisMirage
 * Time: 2020/06/28 11:34
 * Created with IntelliJ IDEA
 */

public class CloneTree_1490 {
    /**
     * DFS to iterate all the nodes.
     * Create a new node at each DFS iteration and iterate into all the children of original node.
     * Finally add children list back to new created node.
     *
     * @param root root of tree
     * @return deep copy (clone) of the tree
     */
    public Node cloneTree(Node root) {

        /* Corner case */
        if (root == null) {
            return null;
        }

        Node tmp = new Node(root.val);
        List<Node> children = new LinkedList<>();

        for (Node n : root.children) {
            children.add(cloneTree(n));
        }
        tmp.children = children;

        return tmp;
    }
}
