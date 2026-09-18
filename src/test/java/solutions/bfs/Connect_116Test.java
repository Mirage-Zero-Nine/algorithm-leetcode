package solutions.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.tree.binarytree.Node;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

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

    /**
     * Exercises twenty different valid trees, including every perfect-tree height from one
     * through twelve (the problem's maximum of 4095 nodes) and trees with duplicate,
     * negative, and boundary-valued node values.  The expected links are calculated by an
     * independent breadth-first traversal rather than by following the links being tested.
     */
    @Test
    public void testAllHeightsValuesAndEveryLevelAgainstIndependentBfsOracle() {
        for (int caseNumber = 0; caseNumber < 20; caseNumber++) {
            int levels = caseNumber < 12 ? caseNumber + 1 : caseNumber - 8;
            int valueMode = caseNumber;
            Node root = buildPerfectTree(levels, valueMode);
            Map<Node, Node[]> originalChildren = snapshotChildren(root);

            assertSame(root, test.connect(root));
            assertNextPointersMatchBfs(root);
            for (Map.Entry<Node, Node[]> entry : originalChildren.entrySet()) {
                assertSame(entry.getValue()[0], entry.getKey().left,
                        "connect must not replace a left child");
                assertSame(entry.getValue()[1], entry.getKey().right,
                        "connect must not replace a right child");
            }
        }
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

    private Node buildPerfectTree(int levels, int valueMode) {
        int total = (1 << levels) - 1;
        Node[] nodes = new Node[total];
        for (int i = 0; i < total; i++) {
            int value;
            if (valueMode == 12) {
                value = -1000;
            } else if (valueMode == 13) {
                value = 1000;
            } else if (valueMode == 14) {
                value = i % 2 == 0 ? 7 : 7;
            } else {
                value = (i * 37 + valueMode * 11) % 2001 - 1000;
            }
            nodes[i] = build(value);
        }
        for (int i = 0; i < total / 2; i++) {
            nodes[i].left = nodes[2 * i + 1];
            nodes[i].right = nodes[2 * i + 2];
        }
        return nodes[0];
    }

    private Map<Node, Node[]> snapshotChildren(Node root) {
        Map<Node, Node[]> snapshot = new IdentityHashMap<>();
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            snapshot.put(node, new Node[]{node.left, node.right});
            if (node.left != null) {
                queue.add(node.left);
                queue.add(node.right);
            }
        }
        return snapshot;
    }

    private void assertNextPointersMatchBfs(Node root) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int width = queue.size();
            List<Node> level = new ArrayList<>(width);
            for (int i = 0; i < width; i++) {
                Node node = queue.remove();
                level.add(node);
                if (node.left != null) {
                    queue.add(node.left);
                    queue.add(node.right);
                }
            }
            for (int i = 0; i < level.size(); i++) {
                Node expected = i + 1 < level.size() ? level.get(i + 1) : null;
                if (expected == null) {
                    assertNull(level.get(i).next, "the rightmost node must terminate its level");
                } else {
                    assertSame(expected, level.get(i).next,
                            "next must point to the adjacent node by identity");
                }
            }

            Node cursor = level.get(0);
            for (Node expected : level) {
                assertSame(expected, cursor, "next chain must visit each level node once");
                cursor = cursor.next;
            }
            assertNull(cursor, "next chain must not continue past the level");
        }
    }
}
