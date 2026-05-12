package solution.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.tree.binarytree.Node;
import org.junit.jupiter.api.Test;

public class Connect116Test {

    private final Connect_116 test = new Connect_116();

    private Node build(int val) {
        Node n = new Node(); n.val = val; return n;
    }

    @Test
    public void testHappyCases() {
        Node root = build(1);
        root.left = build(2); root.right = build(3);
        root.left.left = build(4); root.left.right = build(5);
        root.right.left = build(6); root.right.right = build(7);
        test.connect(root);
        assertEquals(3, root.left.next.val);
        assertEquals(5, root.left.left.next.val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.connect(null));
        Node single = build(1);
        assertNull(test.connect(single).next);
    }

    @Test
    public void testLargeCase() {
        Node root = build(1);
        root.left = build(2); root.right = build(3);
        root.left.left = build(4); root.left.right = build(5);
        root.right.left = build(6); root.right.right = build(7);
        test.connect(root);
        assertNull(root.right.next);
        assertNull(root.right.right.next);
    }
}
