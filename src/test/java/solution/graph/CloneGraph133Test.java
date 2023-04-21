package solution.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

public class CloneGraph133Test {

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
}