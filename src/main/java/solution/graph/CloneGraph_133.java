package solution.graph;

import library.graph.Node;

import java.util.*;

/**
 * Given a reference of a node in a connected undirected graph, return a deep copy (clone) of the graph.
 * Each node in the graph contains a val (int) and a list (List[Node]) of its neighbors.
 * Note:
 * 1. The number of nodes will be between 1 and 100.
 * 2. The undirected graph is a simple graph, which means no repeated edges and no self-loops in the graph.
 * 3. Since the graph is undirected, if node p has node q as neighbor, then node q must have node p as neighbor too.
 * 4. The solution should return the copy of the given node as a reference to the cloned graph.
 *
 * @author BorisMirage
 * Time: 2019/06/15 15:19
 * Created with IntelliJ IDEA
 */

public class CloneGraph_133 {
    /**
     * DFS to access all nodes in graph.
     *
     * @param node start node of original graph
     * @return start node of cloned graph
     */
    public Node cloneGraph(Node node) {

        HashMap<Integer, Node> map = new HashMap<>();
        return dfs(node, map);
    }

    /**
     * DFS to traverse every node in graph and create a new node to save neighbors.
     *
     * @param node start node of original graph
     * @param map  map to store previous visited nodes
     * @return start node of original graph
     */
    private Node dfs(Node node, HashMap<Integer, Node> map) {

        /* Corner case */
        if (node == null) {
            return null;
        }

        if (map.containsKey(node.val)) {
            return map.get(node.val);
        } else {

            Node newNode = new Node(node.val, new ArrayList<>());
            map.put(node.val, newNode);

            for (Node n : node.neighbors) {      // iter all elements in array
                newNode.neighbors.add(dfs(n, map));     // DFS
            }

            return newNode;
        }
    }

    private final Map<Node, Node> oldNewMap = new HashMap<>();        // hash map for BFS

    /**
     * BFS searching with hash map.
     *
     * @param node root node of original graph
     * @return root node of cloned graph
     */
    public Node cloneGraphBFS(Node node) {

        /* Corner case and end point */
        if (node == null) {
            return null;
        }

        if (oldNewMap.containsKey(node)) {
            return oldNewMap.get(node);
        }

        Node cloned = new Node(node.val, new ArrayList<>());

        oldNewMap.put(node, cloned);

        for (Node n : node.neighbors) {
            cloned.neighbors.add(cloneGraphBFS(n));        // add all connected nodes into neighbor
        }

        return cloned;
    }
}
