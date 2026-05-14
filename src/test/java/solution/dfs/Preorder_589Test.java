package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import library.tree.narytree.Node;
import org.junit.jupiter.api.Test;

public class Preorder_589Test {

    private final Preorder_589 test = new Preorder_589();

    @Test
    public void testHappyCases() {
        Node root = new Node(1, Arrays.asList(new Node(3, Arrays.asList(new Node(5), new Node(6))), new Node(2), new Node(4)));
        assertEquals(List.of(1, 3, 5, 6, 2, 4), test.preorder(root));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(List.of(), test.preorder(null));
        assertEquals(List.of(1), test.preorder(new Node(1)));
    }

    @Test
    public void testLargeCase() {
        Node root = new Node(1, Arrays.asList(new Node(2), new Node(3), new Node(4)));
        assertEquals(List.of(1, 2, 3, 4), test.preorder(root));
    }

    @Test
    public void testNullRoot() {
        assertEquals(List.of(), test.preorder(null));
    }

    @Test
    public void testSingleNode() {
        assertEquals(List.of(42), test.preorder(new Node(42)));
    }

    @Test
    public void testNodeWithNullChildren() {
        Node root = new Node(1);
        root.children = null;
        assertEquals(List.of(1), test.preorder(root));
    }

    @Test
    public void testNodeWithEmptyChildren() {
        Node root = new Node(1, new ArrayList<>());
        assertEquals(List.of(1), test.preorder(root));
    }

    @Test
    public void testDeepLinearTree() {
        // Chain: 1 -> 2 -> 3 -> 4 -> 5
        Node n5 = new Node(5);
        Node n4 = new Node(4, List.of(n5));
        Node n3 = new Node(3, List.of(n4));
        Node n2 = new Node(2, List.of(n3));
        Node root = new Node(1, List.of(n2));
        assertEquals(List.of(1, 2, 3, 4, 5), test.preorder(root));
    }

    @Test
    public void testWideTree() {
        // Root with 5 children, no grandchildren
        Node root = new Node(0, Arrays.asList(new Node(1), new Node(2), new Node(3), new Node(4), new Node(5)));
        assertEquals(List.of(0, 1, 2, 3, 4, 5), test.preorder(root));
    }

    @Test
    public void testNegativeValues() {
        Node root = new Node(-1, Arrays.asList(new Node(-2), new Node(-3)));
        assertEquals(List.of(-1, -2, -3), test.preorder(root));
    }

    @Test
    public void testGiantTree() {
        // Build a tree with 1000 nodes: root with 100 children, each with 9 children
        List<Node> children = new ArrayList<>();
        int val = 1;
        for (int i = 0; i < 100; i++) {
            List<Node> grandchildren = new ArrayList<>();
            for (int j = 0; j < 9; j++) {
                grandchildren.add(new Node(val++));
            }
            children.add(new Node(val++, grandchildren));
        }
        Node root = new Node(0, children);
        List<Integer> result = test.preorder(root);
        assertEquals(1001, result.size());
        assertEquals(0, result.get(0));
    }
}
