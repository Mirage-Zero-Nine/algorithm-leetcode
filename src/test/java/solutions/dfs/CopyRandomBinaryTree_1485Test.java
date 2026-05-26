package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.randomnode.Node;
import library.randomnode.NodeCopy;
import org.junit.jupiter.api.Test;

public class CopyRandomBinaryTree_1485Test {

    private final CopyRandomBinaryTree_1485 test = new CopyRandomBinaryTree_1485();

    @Test
    public void testHappyCases() {
        Node root = new Node(1);
        Node n2 = new Node(2); Node n3 = new Node(3);
        root.left = n2; root.right = n3;
        n2.random = n3;
        NodeCopy copy = test.copyRandomBinaryTree(root);
        assertEquals(1, copy.val);
        assertNotSame(root, copy);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.copyRandomBinaryTree(null));
        assertEquals(1, test.copyRandomBinaryTree(new Node(1)).val);
    }

    @Test
    public void testLargeCase() {
        Node root = new Node(1);
        root.left = new Node(2); root.right = new Node(3);
        NodeCopy copy = test.copyRandomBinaryTree(root);
        assertEquals(2, copy.left.val);
        assertEquals(3, copy.right.val);
    }

    @Test
    public void testRandomPointsToSelf() {
        Node root = new Node(5);
        root.random = root;
        NodeCopy copy = test.copyRandomBinaryTree(root);
        assertEquals(5, copy.val);
        assertEquals(copy, copy.random);
        assertNotSame(root, copy);
    }

    @Test
    public void testRandomPointsToParent() {
        Node root = new Node(1);
        Node child = new Node(2);
        root.left = child;
        child.random = root;
        NodeCopy copy = test.copyRandomBinaryTree(root);
        assertEquals(1, copy.val);
        assertEquals(2, copy.left.val);
        assertEquals(copy, copy.left.random);
    }

    @Test
    public void testNoRandomPointers() {
        Node root = new Node(10);
        root.left = new Node(20);
        root.right = new Node(30);
        root.left.left = new Node(40);
        NodeCopy copy = test.copyRandomBinaryTree(root);
        assertEquals(10, copy.val);
        assertEquals(20, copy.left.val);
        assertEquals(40, copy.left.left.val);
        assertEquals(30, copy.right.val);
        assertNull(copy.random);
        assertNull(copy.left.random);
    }

    @Test
    public void testDeepCopyIndependence() {
        Node root = new Node(1);
        root.left = new Node(2);
        NodeCopy copy = test.copyRandomBinaryTree(root);
        // Modify original, copy should not change
        root.left.val = 99;
        assertEquals(2, copy.left.val);
    }

    @Test
    public void testAllRandomPointToSameNode() {
        Node root = new Node(1);
        Node n2 = new Node(2); Node n3 = new Node(3);
        root.left = n2; root.right = n3;
        root.random = n3; n2.random = n3; n3.random = n3;
        NodeCopy copy = test.copyRandomBinaryTree(root);
        assertEquals(copy.right, copy.random);
        assertEquals(copy.right, copy.left.random);
        assertEquals(copy.right, copy.right.random);
    }

    @Test
    public void testLeftSkewedWithRandom() {
        Node n1 = new Node(1); Node n2 = new Node(2); Node n3 = new Node(3);
        n1.left = n2; n2.left = n3;
        n3.random = n1;
        NodeCopy copy = test.copyRandomBinaryTree(n1);
        assertEquals(1, copy.val);
        assertEquals(2, copy.left.val);
        assertEquals(3, copy.left.left.val);
        assertEquals(copy, copy.left.left.random);
    }

    @Test
    public void testNegativeValues() {
        Node root = new Node(-1);
        root.left = new Node(-2);
        root.random = root.left;
        NodeCopy copy = test.copyRandomBinaryTree(root);
        assertEquals(-1, copy.val);
        assertEquals(-2, copy.left.val);
        assertEquals(copy.left, copy.random);
    }

    @Test
    public void testGiantTree() {
        // Build a large tree with 100 nodes in left-skewed fashion
        Node root = new Node(0);
        Node cur = root;
        for (int i = 1; i < 100; i++) {
            cur.left = new Node(i);
            cur = cur.left;
        }
        // Set random of deepest node to root
        cur.random = root;
        NodeCopy copy = test.copyRandomBinaryTree(root);
        // Traverse to deepest
        NodeCopy copyCur = copy;
        for (int i = 1; i < 100; i++) {
            copyCur = copyCur.left;
        }
        assertEquals(99, copyCur.val);
        assertEquals(copy, copyCur.random);
    }
}
