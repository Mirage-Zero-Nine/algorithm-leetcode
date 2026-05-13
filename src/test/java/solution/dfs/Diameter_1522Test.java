package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import library.tree.narytree.Node;
import org.junit.jupiter.api.Test;

public class Diameter_1522Test {

    private final Diameter_1522 test = new Diameter_1522();

    @Test
    public void testHappyCases() {
        Node root = new Node(1, Arrays.asList(new Node(3, Arrays.asList(new Node(5), new Node(6))), new Node(2), new Node(4)));
        assertEquals(3, test.diameter(root));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.diameter(null));
        assertEquals(0, test.diameter(new Node(1)));
    }

    @Test
    public void testLargeCase() {
        Node root = new Node(1, Arrays.asList(new Node(2), new Node(3), new Node(4)));
        assertEquals(2, test.diameter(root));
    }

    @Test
    public void testSingleChild() {
        Node root = new Node(1, Collections.singletonList(new Node(2)));
        assertEquals(1, test.diameter(root));
    }

    @Test
    public void testLinearChain() {
        // 1 -> 2 -> 3 -> 4 (each has one child)
        Node n4 = new Node(4);
        Node n3 = new Node(3, Collections.singletonList(n4));
        Node n2 = new Node(2, Collections.singletonList(n3));
        Node root = new Node(1, Collections.singletonList(n2));
        assertEquals(3, test.diameter(root));
    }

    @Test
    public void testWideRoot() {
        // Root with 5 children, all leaves
        Node root = new Node(1, Arrays.asList(new Node(2), new Node(3), new Node(4), new Node(5), new Node(6)));
        // Diameter = 2 (through root, connecting any two leaves)
        assertEquals(2, test.diameter(root));
    }

    @Test
    public void testDiameterNotThroughRoot() {
        // Root -> child1 (which has deep subtree with diameter)
        Node deep = new Node(10, Arrays.asList(
                new Node(11, Collections.singletonList(new Node(12))),
                new Node(13, Collections.singletonList(new Node(14)))
        ));
        Node root = new Node(1, Arrays.asList(deep, new Node(2)));
        // Diameter is through node 10: 12->11->10->13->14 = 4
        assertEquals(4, test.diameter(root));
    }

    @Test
    public void testTwoLevelBalanced() {
        // Root with 3 children, each having 2 children
        Node root = new Node(1, Arrays.asList(
                new Node(2, Arrays.asList(new Node(5), new Node(6))),
                new Node(3, Arrays.asList(new Node(7), new Node(8))),
                new Node(4, Arrays.asList(new Node(9), new Node(10)))
        ));
        // Longest path: leaf -> child -> root -> child -> leaf = 4
        assertEquals(4, test.diameter(root));
    }

    @Test
    public void testEmptyChildrenList() {
        Node root = new Node(1, Collections.emptyList());
        assertEquals(0, test.diameter(root));
    }

    @Test
    public void testNullChildrenField() {
        Node root = new Node(1);
        root.children = null;
        assertEquals(0, test.diameter(root));
    }

    @Test
    public void testGiantTree() {
        // Build a tree: root with 2 long chains of depth 50 each
        Node leftChain = new Node(100);
        Node cur = leftChain;
        for (int i = 0; i < 49; i++) {
            Node next = new Node(i);
            cur.children = Collections.singletonList(next);
            cur = next;
        }
        Node rightChain = new Node(200);
        cur = rightChain;
        for (int i = 0; i < 49; i++) {
            Node next = new Node(i + 50);
            cur.children = Collections.singletonList(next);
            cur = next;
        }
        Node root = new Node(1, Arrays.asList(leftChain, rightChain));
        // Diameter = 50 + 50 = 100
        assertEquals(100, test.diameter(root));
    }
}
