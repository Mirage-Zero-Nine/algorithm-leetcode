package solutions.dfs;

import library.graph.Node;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

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
     * Clones the reachable graph with recursive depth-first search.
     *
     * <p>The map stores the clone as soon as a source node is first seen.  That ordering is
     * essential: an undirected edge points back to an already visited node, and a cycle can point
     * back to a node whose recursive call is still in progress.  Returning the stored clone in
     * both cases preserves the original graph's sharing and cycles without recursing forever.</p>
     *
     * <p>Every reachable node and edge is processed once, so the time complexity is {@code O(V + E)}
     * and the auxiliary space is {@code O(V)} for the map and recursion stack.  The input graph is
     * never modified.  The problem's node values are unique, so values can identify source nodes
     * in the map.</p>
     *
     * @param node a node in the connected graph to copy, or {@code null}
     * @return the cloned node corresponding to {@code node}, or {@code null}
     */
    public Node cloneGraph(Node node) {
        return dfs(node, new HashMap<>());
    }

    /**
     * Creates a node before visiting its neighbors, then recursively fills its neighbor list.
     * Creating and recording the node first makes a back edge resolve to the same clone.
     *
     * @param node the source node currently being copied
     * @param map  source node values to the clones already created for them
     * @return the clone for {@code node}, or {@code null} when {@code node} is {@code null}
     */
    private Node dfs(Node node, Map<Integer, Node> map) {
        if (node == null) {
            return null;
        }

        if (map.containsKey(node.val)) {
            return map.get(node.val);
        }

        Node cloned = new Node(node.val, new ArrayList<>());
        // Record the clone before descending so cycles can point back to this same object.
        map.put(cloned.val, cloned);
        for (Node neighbor : node.neighbors) {
            cloned.neighbors.add(dfs(neighbor, map));
        }
        return cloned;
    }

    /**
     * Clones the reachable graph with iterative breadth-first search.
     *
     * <p>The map is both the visited set and the source-to-clone lookup.  The root clone is created
     * and the original root is queued first; each newly discovered neighbor is cloned and queued
     * once, while every occurrence is appended to the current clone's neighbor list.  This keeps
     * repeated references, cycles, and neighbor order identical to the input graph.</p>
     *
     * <p>The traversal processes each reachable node and edge once, giving {@code O(V + E)} time and
     * {@code O(V)} auxiliary space.  Unlike the recursive method, its traversal uses an explicit
     * queue, so it does not consume the call stack.  The input graph is never modified.</p>
     *
     * @param node a node in the connected graph to copy, or {@code null}
     * @return the cloned node corresponding to {@code node}, or {@code null}
     */
    public Node cloneGraphBFS(Node node) {
        return bfs(node, new HashMap<>());
    }

    /**
     * Performs the queue-based portion of {@link #cloneGraphBFS(Node)}.
     *
     * @param node the source node at which traversal starts
     * @param map  source node values to the clones already created for them
     * @return the clone for {@code node}
     */
    private Node bfs(Node node, Map<Integer, Node> map) {
        if (node == null) {
            return null;
        }
        Node clonedRoot = new Node(node.val, new ArrayList<>());
        map.put(clonedRoot.val, clonedRoot);

        Queue<Node> q = new ArrayDeque<>();
        // The root must be queued so its neighbors are copied and connected to the root clone.
        q.add(node);
        while (!q.isEmpty()) {
            Node current = q.poll();
            Node currentClone = map.get(current.val);
            for (Node neighbor : current.neighbors) {
                Node cloneNeighbor = map.computeIfAbsent(neighbor.val, val -> {
                    // Queue a source node only when its clone is first created.
                    q.add(neighbor);
                    return new Node(val, new ArrayList<>());
                });
                currentClone.neighbors.add(cloneNeighbor);
            }
        }
        return clonedRoot;
    }
}
