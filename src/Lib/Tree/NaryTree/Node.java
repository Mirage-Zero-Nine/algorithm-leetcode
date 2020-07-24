package Lib.Tree.NaryTree;

import java.util.List;

/**
 * Tree node of n-ary tree.
 *
 * @author BorisMirage
 * Time: 2020/02/03 16:36
 * Created with IntelliJ IDEA
 */

public class Node {
    public int val;
    public List<Node> children;

    /**
     * Constructor of node.
     */
    public Node() {
    }

    /**
     * Overload of constructor, with the initially value of node.
     *
     * @param val value store in this node
     */
    public Node(int val) {
        this.val = val;
    }

    /**
     * Overload of constructor, with the initially value and children of current node.
     *
     * @param val      value in current node
     * @param children children of current node
     */
    public Node(int val, List<Node> children) {
        this.val = val;
        this.children = children;
    }
}