package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.ArrayList;
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

    // ===== NEW TESTS BELOW =====

    @Test
    public void testStarWithManyChildren() {
        // Root with 100 leaf children -> diameter is always 2
        List<Node> children = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            children.add(new Node(i + 2));
        }
        Node root = new Node(1, children);
        assertEquals(2, test.diameter(root));
    }

    @Test
    public void testLongLinearChain() {
        // Chain of 200 nodes -> diameter = 199
        Node cur = new Node(200);
        for (int i = 199; i >= 1; i--) {
            cur = new Node(i, Collections.singletonList(cur));
        }
        assertEquals(199, test.diameter(cur));
    }

    @Test
    public void testBalancedTernaryTree() {
        // 3-level ternary tree: root -> 3 children -> each has 3 children
        // Height = 2, diameter = 4 (leaf-child-root-child-leaf)
        List<Node> level2 = new ArrayList<>();
        int id = 10;
        for (int i = 0; i < 3; i++) {
            level2.add(new Node(i + 2, Arrays.asList(new Node(id++), new Node(id++), new Node(id++))));
        }
        Node root = new Node(1, level2);
        assertEquals(4, test.diameter(root));
    }

    @Test
    public void testDeepAndWideBranches() {
        // Root has one deep branch (depth 10) and one wide branch (many leaves)
        // Deep branch: chain of 10
        Node deep = new Node(50);
        Node cur = deep;
        for (int i = 0; i < 9; i++) {
            Node next = new Node(51 + i);
            cur.children = Collections.singletonList(next);
            cur = next;
        }
        // Wide branch: node with 20 leaf children (depth 1)
        List<Node> wideChildren = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            wideChildren.add(new Node(100 + i));
        }
        Node wide = new Node(60, wideChildren);

        Node root = new Node(1, Arrays.asList(deep, wide));
        // Longest path: deepest leaf -> ... -> deep -> root -> wide -> leaf = 10 + 2 = 12
        assertEquals(12, test.diameter(root));
    }

    @Test
    public void testDiameterDeepInSubtree() {
        // Diameter is entirely within a subtree, not passing through root
        // Root -> A (leaf), B -> two chains of length 5
        Node chain1End = new Node(90);
        Node c1 = chain1End;
        for (int i = 0; i < 4; i++) {
            c1 = new Node(80 + i, Collections.singletonList(c1));
        }
        Node chain2End = new Node(70);
        Node c2 = chain2End;
        for (int i = 0; i < 4; i++) {
            c2 = new Node(60 + i, Collections.singletonList(c2));
        }
        Node subtreeRoot = new Node(2, Arrays.asList(c1, c2));
        Node root = new Node(1, Arrays.asList(subtreeRoot, new Node(3)));
        // Diameter through subtreeRoot: 5 + 5 = 10
        assertEquals(10, test.diameter(root));
    }

    @Test
    public void testAsymmetricTree() {
        // Root -> left chain(3) and right chain(7)
        // Diameter = 3 + 7 = 10
        Node left = new Node(10);
        Node cur = left;
        for (int i = 0; i < 2; i++) {
            Node next = new Node(11 + i);
            cur.children = Collections.singletonList(next);
            cur = next;
        }
        Node right = new Node(20);
        cur = right;
        for (int i = 0; i < 6; i++) {
            Node next = new Node(21 + i);
            cur.children = Collections.singletonList(next);
            cur = next;
        }
        Node root = new Node(1, Arrays.asList(left, right));
        // left depth = 3, right depth = 7, diameter = 10
        assertEquals(10, test.diameter(root));
    }

    @Test
    public void testPropertyDiameterBoundedByTwiceHeight() {
        // Build various trees and verify diameter <= 2 * height
        // Tree 1: balanced binary-like
        Node t1 = new Node(1, Arrays.asList(
                new Node(2, Arrays.asList(new Node(4), new Node(5))),
                new Node(3, Arrays.asList(new Node(6), new Node(7)))
        ));
        assertTrue(test.diameter(t1) <= 2 * height(t1));

        // Tree 2: linear chain of 10
        Node t2 = new Node(100);
        Node cur = t2;
        for (int i = 0; i < 9; i++) {
            Node next = new Node(i);
            cur.children = Collections.singletonList(next);
            cur = next;
        }
        assertTrue(test.diameter(t2) <= 2 * height(t2));

        // Tree 3: star
        List<Node> starChildren = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            starChildren.add(new Node(i));
        }
        Node t3 = new Node(1, starChildren);
        assertTrue(test.diameter(t3) <= 2 * height(t3));
    }

    @Test
    public void testThreeDeepBranches() {
        // Root with 3 branches of depth 4, 6, 8
        // Diameter = top two: 6 + 8 = 14
        Node b1 = buildChain(4);
        Node b2 = buildChain(6);
        Node b3 = buildChain(8);
        Node root = new Node(1, Arrays.asList(b1, b2, b3));
        assertEquals(14, test.diameter(root));
    }

    private Node buildChain(int depth) {
        Node cur = new Node(0);
        for (int i = 1; i < depth; i++) {
            cur = new Node(i, Collections.singletonList(cur));
        }
        return cur;
    }

    private int height(Node node) {
        if (node == null) return 0;
        if (node.children == null || node.children.isEmpty()) return 0;
        int max = 0;
        for (Node child : node.children) {
            max = Math.max(max, height(child));
        }
        return max + 1;
    }
}
