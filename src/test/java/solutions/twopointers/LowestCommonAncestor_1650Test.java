package solutions.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.binarytree.Node;
import org.junit.jupiter.api.Test;

public class LowestCommonAncestor_1650Test {

    private final LowestCommonAncestor_1650 test = new LowestCommonAncestor_1650();

    @Test
    public void testHappyCases() {
        //     3
        //    / \
        //   5   1
        Node root = new Node();
        root.val = 3;
        Node n5 = new Node(); n5.val = 5; n5.parent = root;
        Node n1 = new Node(); n1.val = 1; n1.parent = root;
        root.left = n5; root.right = n1;
        assertEquals(root, test.lowestCommonAncestor(n5, n1));
    }

    @Test
    public void testEdgeCases() {
        Node root = new Node(); root.val = 1;
        Node child = new Node(); child.val = 2; child.parent = root;
        assertEquals(root, test.lowestCommonAncestor(root, child));
    }

    @Test
    public void testLargeCase() {
        Node root = new Node(); root.val = 1;
        Node n2 = new Node(); n2.val = 2; n2.parent = root;
        Node n3 = new Node(); n3.val = 3; n3.parent = n2;
        Node n4 = new Node(); n4.val = 4; n4.parent = n2;
        assertEquals(n2, test.lowestCommonAncestor(n3, n4));
    }

    @Test
    public void testSameNode() {
        Node root = new Node(); root.val = 1;
        assertEquals(root, test.lowestCommonAncestor(root, root));
    }

    @Test
    public void testChildIsAncestor() {
        Node root = new Node(); root.val = 1;
        Node n2 = new Node(); n2.val = 2; n2.parent = root;
        Node n3 = new Node(); n3.val = 3; n3.parent = n2;
        root.left = n2; n2.left = n3;
        assertEquals(n2, test.lowestCommonAncestor(n2, n3));
    }

    @Test
    public void testDeepLeftAndRight() {
        //       1
        //      / \
        //     2   3
        //    /     \
        //   4       5
        Node root = new Node(); root.val = 1;
        Node n2 = new Node(); n2.val = 2; n2.parent = root;
        Node n3 = new Node(); n3.val = 3; n3.parent = root;
        Node n4 = new Node(); n4.val = 4; n4.parent = n2;
        Node n5 = new Node(); n5.val = 5; n5.parent = n3;
        root.left = n2; root.right = n3; n2.left = n4; n3.right = n5;
        assertEquals(root, test.lowestCommonAncestor(n4, n5));
    }

    @Test
    public void testUnevenDepths() {
        //     1
        //    /
        //   2
        //  /
        // 3
        Node root = new Node(); root.val = 1;
        Node n2 = new Node(); n2.val = 2; n2.parent = root;
        Node n3 = new Node(); n3.val = 3; n3.parent = n2;
        root.left = n2; n2.left = n3;
        assertEquals(root, test.lowestCommonAncestor(n3, root));
    }

    @Test
    public void testRightSkewedTree() {
        Node root = new Node(); root.val = 1;
        Node n2 = new Node(); n2.val = 2; n2.parent = root;
        Node n3 = new Node(); n3.val = 3; n3.parent = n2;
        Node n4 = new Node(); n4.val = 4; n4.parent = n3;
        root.right = n2; n2.right = n3; n3.right = n4;
        assertEquals(n2, test.lowestCommonAncestor(n2, n4));
    }

    @Test
    public void testSiblings() {
        Node root = new Node(); root.val = 1;
        Node n2 = new Node(); n2.val = 2; n2.parent = root;
        Node n3 = new Node(); n3.val = 3; n3.parent = root;
        root.left = n2; root.right = n3;
        assertEquals(root, test.lowestCommonAncestor(n2, n3));
    }

    @Test
    public void testCousins() {
        //       1
        //      / \
        //     2   3
        //    /   /
        //   4   5
        Node root = new Node(); root.val = 1;
        Node n2 = new Node(); n2.val = 2; n2.parent = root;
        Node n3 = new Node(); n3.val = 3; n3.parent = root;
        Node n4 = new Node(); n4.val = 4; n4.parent = n2;
        Node n5 = new Node(); n5.val = 5; n5.parent = n3;
        root.left = n2; root.right = n3; n2.left = n4; n3.left = n5;
        assertEquals(root, test.lowestCommonAncestor(n4, n5));
    }

    @Test
    public void testGiantChain() {
        // Chain of 50 nodes: 1 -> 2 -> 3 -> ... -> 50
        Node[] nodes = new Node[50];
        for (int i = 0; i < 50; i++) {
            nodes[i] = new Node(); nodes[i].val = i + 1;
        }
        for (int i = 1; i < 50; i++) {
            nodes[i].parent = nodes[i - 1];
            nodes[i - 1].left = nodes[i];
        }
        // LCA of node 25 and node 49 should be node 25
        assertEquals(nodes[24], test.lowestCommonAncestor(nodes[24], nodes[48]));
    }
}
