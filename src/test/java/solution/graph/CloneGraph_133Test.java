package solution.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import library.graph.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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
}
