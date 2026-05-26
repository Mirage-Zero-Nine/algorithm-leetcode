package solutions.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.tree.binarytree.Node;
import org.junit.jupiter.api.Test;

public class Connect_116Test {

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

    @Test
    public void testSecondLevelLinking() {
        Node root = build(10);
        root.left = build(20);
        root.right = build(30);
        test.connect(root);
        assertEquals(30, root.left.next.val);
        assertNull(root.right.next);
    }

    @Test
    public void testThirdLevelCrossParentLinking() {
        Node root = build(1);
        root.left = build(2); root.right = build(3);
        root.left.left = build(4); root.left.right = build(5);
        root.right.left = build(6); root.right.right = build(7);
        test.connect(root);
        assertEquals(6, root.left.right.next.val);
    }

    @Test
    public void testLeftMostAndRightMostNextPointers() {
        Node root = build(1);
        root.left = build(2); root.right = build(3);
        root.left.left = build(4); root.left.right = build(5);
        root.right.left = build(6); root.right.right = build(7);
        test.connect(root);
        assertNull(root.next);
        assertNull(root.right.right.next);
    }

    @Test
    public void testIdempotentWhenConnectCalledTwice() {
        Node root = build(1);
        root.left = build(2); root.right = build(3);
        root.left.left = build(4); root.left.right = build(5);
        root.right.left = build(6); root.right.right = build(7);
        test.connect(root);
        test.connect(root);
        assertEquals(5, root.left.left.next.val);
        assertEquals(7, root.right.left.next.val);
    }

    @Test
    public void testFourLevelPerfectTree() {
        Node[] nodes = new Node[16];
        for (int i = 1; i <= 15; i++) {
            nodes[i] = build(i);
        }
        for (int i = 1; i <= 7; i++) {
            nodes[i].left = nodes[i * 2];
            nodes[i].right = nodes[i * 2 + 1];
        }

        test.connect(nodes[1]);
        assertEquals(9, nodes[8].next.val);
        assertEquals(15, nodes[14].next.val);
        assertNull(nodes[15].next);
    }

    @Test
    public void testReturnSameRootReference() {
        Node root = build(1);
        root.left = build(2);
        root.right = build(3);
        Node out = test.connect(root);
        assertEquals(root, out);
    }

    @Test
    public void testGiantPerfectTree() {
        int levels = 8;
        int total = (1 << levels) - 1;
        Node[] nodes = new Node[total + 1];
        for (int i = 1; i <= total; i++) {
            nodes[i] = build(i);
        }
        for (int i = 1; i <= total / 2; i++) {
            nodes[i].left = nodes[i * 2];
            nodes[i].right = nodes[i * 2 + 1];
        }

        test.connect(nodes[1]);
        assertEquals(129, nodes[128].next.val);
        assertNull(nodes[255].next);
    }
}
