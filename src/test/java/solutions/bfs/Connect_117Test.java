package solutions.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.tree.binarytree.Node;
import org.junit.jupiter.api.Test;

public class Connect_117Test {

    private final Connect_117 test = new Connect_117();

    private Node build(int val) {
        Node n = new Node(); n.val = val; return n;
    }

    @Test
    public void testHappyCases() {
        Node root = build(1);
        root.left = build(2); root.right = build(3);
        root.left.left = build(4); root.right.right = build(5);
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
        root.left.right = build(4); root.right.left = build(5);
        test.connect(root);
        assertEquals(3, root.left.next.val);
        assertEquals(5, root.left.right.next.val);
    }

    @Test
    public void testLeftSkewedTreeAllNextNull() {
        Node root = build(1);
        root.left = build(2);
        root.left.left = build(3);
        root.left.left.left = build(4);
        test.connect(root);
        assertNull(root.next);
        assertNull(root.left.next);
        assertNull(root.left.left.next);
        assertNull(root.left.left.left.next);
    }

    @Test
    public void testRightSkewedTreeAllNextNull() {
        Node root = build(1);
        root.right = build(2);
        root.right.right = build(3);
        root.right.right.right = build(4);
        test.connect(root);
        assertNull(root.next);
        assertNull(root.right.next);
        assertNull(root.right.right.next);
        assertNull(root.right.right.right.next);
    }

    @Test
    public void testSparseCrossParentLinking() {
        Node root = build(1);
        root.left = build(2);
        root.right = build(3);
        root.left.right = build(4);
        root.right.right = build(5);
        test.connect(root);
        assertEquals(5, root.left.right.next.val);
        assertNull(root.right.right.next);
    }

    @Test
    public void testMultipleNodesSameLevelWithGaps() {
        Node root = build(1);
        root.left = build(2);
        root.right = build(3);
        root.left.left = build(4);
        root.right.left = build(5);
        root.right.right = build(6);
        test.connect(root);
        assertEquals(5, root.left.left.next.val);
        assertEquals(6, root.right.left.next.val);
        assertNull(root.right.right.next);
    }

    @Test
    public void testIdempotentCallTwice() {
        Node root = build(1);
        root.left = build(2);
        root.right = build(3);
        root.left.left = build(4);
        root.right.right = build(5);
        test.connect(root);
        test.connect(root);
        assertEquals(3, root.left.next.val);
        assertEquals(5, root.left.left.next.val);
    }

    @Test
    public void testReturnSameRootReference() {
        Node root = build(9);
        root.left = build(8);
        root.right = build(7);
        Node out = test.connect(root);
        assertEquals(root, out);
    }

    @Test
    public void testGiantSparseTree() {
        Node root = build(1);
        Node current = root;
        for (int i = 2; i <= 150; i++) {
            Node next = build(i);
            if (i % 2 == 0) {
                current.left = next;
            } else {
                current.right = next;
            }
            current = next;
        }
        test.connect(root);
        current = root;
        while (current != null) {
            assertNull(current.next);
            current = current.left != null ? current.left : current.right;
        }
    }
}
