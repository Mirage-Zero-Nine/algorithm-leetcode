package library.graph;

import java.util.List;

/**
 * Definition of node in graph.
 *
 * @author BorisMirage
 * Time: 2019/06/05 21:40
 * Created with IntelliJ IDEA
 */

public class Node {

    public int val;
    public List<Node> neighbors;

    /**
     * Constructor initialize a empty node in graph.
     */
    public Node() {
    }

    /**
     * Overload of constructor, create a new node with value and all its neighbors.
     *
     * @param _val       value store in cache
     * @param _neighbors all linked node to current graph node
     */
    public Node(int _val, List<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
