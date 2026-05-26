package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import library.graph.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * @author BorisMirage
 * Time: 2023/04/21 14:18
 * Created with IntelliJ IDEA
 */

public class CloneGraph_133Test {

    private CloneGraph_133 test;

    @BeforeEach
    public void setUp() {
        test = new CloneGraph_133();
    }

    @Test
    public void testCloneGraph() {

        // Create a sample graph: 1 -- 2
        Node node1 = new Node(1, new ArrayList<>());
        Node node2 = new Node(2, new ArrayList<>());
        node1.neighbors.add(node2);
        node2.neighbors.add(node1);

        // Clone the graph
        Node clonedNode1 = test.cloneGraph(node1);

        // Verify that the cloned graph is equal to the original graph
        assertEquals(node1.val, clonedNode1.val);
        assertEquals(node1.neighbors.size(), clonedNode1.neighbors.size());
        assertEquals(node2.val, clonedNode1.neighbors.get(0).val);
        assertEquals(node2.neighbors.size(), clonedNode1.neighbors.get(0).neighbors.size());
        assertEquals(node1.val, clonedNode1.neighbors.get(0).neighbors.get(0).val);
    }

    @Test
    public void testCloneGraphBFS() {
        // Create a sample graph: 1 -- 2
        Node node1 = new Node(1, new ArrayList<>());
        Node node2 = new Node(2, new ArrayList<>());
        node1.neighbors.add(node2);
        node2.neighbors.add(node1);

        // Clone the graph
        Node clonedNode1 = test.cloneGraphBFS(node1);

        // Verify that the cloned graph is equal to the original graph
        assertEquals(node1.val, clonedNode1.val);
        assertEquals(node1.neighbors.size(), clonedNode1.neighbors.size());
        assertEquals(node2.val, clonedNode1.neighbors.get(0).val);
        assertEquals(node2.neighbors.size(), clonedNode1.neighbors.get(0).neighbors.size());
        assertEquals(node1.val, clonedNode1.neighbors.get(0).neighbors.get(0).val);
    }

    @Test
    public void testCloneGraph1() {
        // create a sample graph: 1 -- 2 -- 3
        Node node1 = new Node(1, new ArrayList<>());
        Node node2 = new Node(2, new ArrayList<>());
        Node node3 = new Node(3, new ArrayList<>());
        node1.neighbors.add(node2);
        node2.neighbors.add(node1);
        node2.neighbors.add(node3);
        node3.neighbors.add(node2);

        // clone the graph
        Node clonedNode1 = test.cloneGraph(node1);

        // verify that the cloned graph is equal to the original graph
        assertEquals(node1.val, clonedNode1.val);
        assertEquals(node1.neighbors.size(), clonedNode1.neighbors.size());
        assertEquals(node2.val, clonedNode1.neighbors.get(0).val);
        assertEquals(node2.neighbors.size(), clonedNode1.neighbors.get(0).neighbors.size());
        assertEquals(node1.val, clonedNode1.neighbors.get(0).neighbors.get(0).val);
        assertEquals(node3.val, clonedNode1.neighbors.get(0).neighbors.get(1).val);
        assertEquals(node3.neighbors.size(), clonedNode1.neighbors.get(0).neighbors.get(1).neighbors.size());
        assertEquals(node2.val, clonedNode1.neighbors.get(0).neighbors.get(1).neighbors.get(0).val);
    }

    @Test
    public void test2() {
        // create a sample graph: 1 -- 2 -- 3
        Node node1 = new Node(1, new ArrayList<>());
        Node node2 = new Node(2, new ArrayList<>());
        Node node3 = new Node(3, new ArrayList<>());
        node1.neighbors.add(node2);
        node2.neighbors.add(node1);
        node2.neighbors.add(node3);
        node3.neighbors.add(node2);

        // clone the graph
        Node clonedNode1 = test.cloneGraphBFS(node1);

        // verify that the cloned graph is equal to the original graph
        assertEquals(node1.val, clonedNode1.val);
        assertEquals(node1.neighbors.size(), clonedNode1.neighbors.size());
        assertEquals(node2.val, clonedNode1.neighbors.get(0).val);
        assertEquals(node2.neighbors.size(), clonedNode1.neighbors.get(0).neighbors.size());
        assertEquals(node1.val, clonedNode1.neighbors.get(0).neighbors.get(0).val);
        assertEquals(node3.val, clonedNode1.neighbors.get(0).neighbors.get(1).val);
        assertEquals(node3.neighbors.size(), clonedNode1.neighbors.get(0).neighbors.get(1).neighbors.size());
        assertEquals(node2.val, clonedNode1.neighbors.get(0).neighbors.get(1).neighbors.get(0).val);
    }

    @Test
    public void testNullGraph() {
        assertNull(test.cloneGraph(null));
    }

    @Test
    public void testSingleNodeNoNeighbors() {
        Node node = new Node(1, new ArrayList<>());
        Node cloned = test.cloneGraph(node);
        assertEquals(1, cloned.val);
        assertTrue(cloned.neighbors.isEmpty());
        assertNotSame(node, cloned);
    }

    @Test
    public void testTriangleGraph() {
        Node n1 = new Node(1, new ArrayList<>());
        Node n2 = new Node(2, new ArrayList<>());
        Node n3 = new Node(3, new ArrayList<>());
        n1.neighbors.add(n2); n1.neighbors.add(n3);
        n2.neighbors.add(n1); n2.neighbors.add(n3);
        n3.neighbors.add(n1); n3.neighbors.add(n2);
        Node cloned = test.cloneGraph(n1);
        assertEquals(1, cloned.val);
        assertEquals(2, cloned.neighbors.size());
        assertNotSame(n1, cloned);
    }

    @Test
    public void testSquareGraph() {
        Node n1 = new Node(1, new ArrayList<>());
        Node n2 = new Node(2, new ArrayList<>());
        Node n3 = new Node(3, new ArrayList<>());
        Node n4 = new Node(4, new ArrayList<>());
        n1.neighbors.add(n2); n1.neighbors.add(n4);
        n2.neighbors.add(n1); n2.neighbors.add(n3);
        n3.neighbors.add(n2); n3.neighbors.add(n4);
        n4.neighbors.add(n3); n4.neighbors.add(n1);
        Node cloned = test.cloneGraph(n1);
        assertEquals(1, cloned.val);
        assertEquals(2, cloned.neighbors.size());
        // Verify deep copy
        assertNotSame(n1, cloned);
        assertNotSame(n2, cloned.neighbors.get(0));
    }

    @Test
    public void testCloneIsDeepCopy() {
        Node n1 = new Node(1, new ArrayList<>());
        Node n2 = new Node(2, new ArrayList<>());
        n1.neighbors.add(n2);
        n2.neighbors.add(n1);
        Node cloned = test.cloneGraph(n1);
        // Modifying original should not affect clone
        n1.val = 99;
        assertEquals(1, cloned.val);
    }

    @Test
    public void testStarGraph() {
        // Center node connected to 5 leaf nodes
        Node center = new Node(1, new ArrayList<>());
        for (int i = 2; i <= 6; i++) {
            Node leaf = new Node(i, new ArrayList<>());
            center.neighbors.add(leaf);
            leaf.neighbors.add(center);
        }
        Node cloned = test.cloneGraph(center);
        assertEquals(1, cloned.val);
        assertEquals(5, cloned.neighbors.size());
    }

    @Test
    public void testGiantGraph() {
        // Build a chain of 100 nodes
        Node[] nodes = new Node[100];
        for (int i = 0; i < 100; i++) {
            nodes[i] = new Node(i + 1, new ArrayList<>());
        }
        for (int i = 0; i < 99; i++) {
            nodes[i].neighbors.add(nodes[i + 1]);
            nodes[i + 1].neighbors.add(nodes[i]);
        }
        Node cloned = test.cloneGraph(nodes[0]);
        assertEquals(1, cloned.val);
        assertEquals(1, cloned.neighbors.size());
        assertEquals(2, cloned.neighbors.get(0).val);
    }

    @Test
    public void testSingleNodeSelfLoop() {
        // A node that has itself as a neighbor (self-loop)
        Node node = new Node(1, new ArrayList<>());
        node.neighbors.add(node);
        Node cloned = test.cloneGraph(node);
        assertEquals(1, cloned.val);
        assertEquals(1, cloned.neighbors.size());
        // Self-loop should point to the cloned node itself, not the original
        assertNotSame(node, cloned);
        assertTrue(cloned.neighbors.get(0) == cloned, "Self-loop should reference the cloned node itself");
    }

    @Test
    public void testNullGraphBFS() {
        assertNull(test.cloneGraphBFS(null));
    }

    @Test
    public void testCompleteGraph10Nodes() {
        // Complete graph K10: every node connected to every other node
        int n = 10;
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i + 1, new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    nodes[i].neighbors.add(nodes[j]);
                }
            }
        }
        Node cloned = test.cloneGraph(nodes[0]);
        // BFS to collect all cloned nodes
        Map<Integer, Node> clonedMap = collectAllNodes(cloned);
        assertEquals(n, clonedMap.size());
        for (int i = 1; i <= n; i++) {
            assertEquals(n - 1, clonedMap.get(i).neighbors.size());
        }
    }

    @Test
    public void testClonedNeighborsAreNewInstances() {
        // Verify that every neighbor in the cloned graph is a new object, never an original
        Node n1 = new Node(1, new ArrayList<>());
        Node n2 = new Node(2, new ArrayList<>());
        Node n3 = new Node(3, new ArrayList<>());
        n1.neighbors.add(n2); n1.neighbors.add(n3);
        n2.neighbors.add(n1); n2.neighbors.add(n3);
        n3.neighbors.add(n1); n3.neighbors.add(n2);

        Set<Node> originals = new HashSet<>();
        originals.add(n1); originals.add(n2); originals.add(n3);

        Node cloned = test.cloneGraph(n1);
        Map<Integer, Node> clonedMap = collectAllNodes(cloned);
        for (Node clonedNode : clonedMap.values()) {
            assertTrue(!originals.contains(clonedNode), "Cloned node must not be an original instance");
            for (Node neighbor : clonedNode.neighbors) {
                assertTrue(!originals.contains(neighbor), "Cloned neighbor must not be an original instance");
            }
        }
    }

    @Test
    public void testOriginalGraphUnchangedAfterClone() {
        // Verify original graph structure is not modified by cloning
        Node n1 = new Node(1, new ArrayList<>());
        Node n2 = new Node(2, new ArrayList<>());
        Node n3 = new Node(3, new ArrayList<>());
        n1.neighbors.add(n2); n1.neighbors.add(n3);
        n2.neighbors.add(n1);
        n3.neighbors.add(n1);

        // Record original state
        int n1Val = n1.val, n2Val = n2.val, n3Val = n3.val;
        int n1Size = n1.neighbors.size(), n2Size = n2.neighbors.size(), n3Size = n3.neighbors.size();

        test.cloneGraph(n1);

        // Verify nothing changed
        assertEquals(n1Val, n1.val);
        assertEquals(n2Val, n2.val);
        assertEquals(n3Val, n3.val);
        assertEquals(n1Size, n1.neighbors.size());
        assertEquals(n2Size, n2.neighbors.size());
        assertEquals(n3Size, n3.neighbors.size());
    }

    @Test
    public void testTreeShapedGraph() {
        // Tree: 1 -> {2, 3}, 2 -> {4, 5}, all edges bidirectional
        Node n1 = new Node(1, new ArrayList<>());
        Node n2 = new Node(2, new ArrayList<>());
        Node n3 = new Node(3, new ArrayList<>());
        Node n4 = new Node(4, new ArrayList<>());
        Node n5 = new Node(5, new ArrayList<>());
        n1.neighbors.add(n2); n1.neighbors.add(n3);
        n2.neighbors.add(n1); n2.neighbors.add(n4); n2.neighbors.add(n5);
        n3.neighbors.add(n1);
        n4.neighbors.add(n2);
        n5.neighbors.add(n2);

        Node cloned = test.cloneGraph(n1);
        Map<Integer, Node> clonedMap = collectAllNodes(cloned);
        assertEquals(5, clonedMap.size());
        assertEquals(2, clonedMap.get(1).neighbors.size());
        assertEquals(3, clonedMap.get(2).neighbors.size());
        assertEquals(1, clonedMap.get(3).neighbors.size());
        assertEquals(1, clonedMap.get(4).neighbors.size());
        assertEquals(1, clonedMap.get(5).neighbors.size());
    }

    @Test
    public void testTwoNodesMutuallyConnectedDeepCopyBFS() {
        Node n1 = new Node(1, new ArrayList<>());
        Node n2 = new Node(2, new ArrayList<>());
        n1.neighbors.add(n2);
        n2.neighbors.add(n1);

        Node cloned = test.cloneGraphBFS(n1);
        assertNotSame(n1, cloned);
        assertNotSame(n2, cloned.neighbors.get(0));
        // Verify circular reference in clone
        assertTrue(cloned.neighbors.get(0).neighbors.get(0) == cloned);
    }

    /** Helper: BFS to collect all nodes in a graph into a map keyed by val. */
    private Map<Integer, Node> collectAllNodes(Node start) {
        Map<Integer, Node> map = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(start);
        map.put(start.val, start);
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            for (Node neighbor : curr.neighbors) {
                if (!map.containsKey(neighbor.val)) {
                    map.put(neighbor.val, neighbor);
                    queue.add(neighbor);
                }
            }
        }
        return map;
    }
}
