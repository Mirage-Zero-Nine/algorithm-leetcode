package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.tree.binarytree.Node;
import org.junit.jupiter.api.Test;

public class InorderSuccessor_510Test {

    private final InorderSuccessor_510 test = new InorderSuccessor_510();

    @Test
    public void testHappyCases() {
        Node root = new Node(); root.val = 2;
        Node n1 = new Node(); n1.val = 1; n1.parent = root;
        Node n3 = new Node(); n3.val = 3; n3.parent = root;
        root.left = n1; root.right = n3;
        assertEquals(2, test.inorderSuccessor(n1).val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.inorderSuccessor(null));
        Node root = new Node(); root.val = 1;
        assertNull(test.inorderSuccessor(root));
    }

    @Test
    public void testLargeCase() {
        Node root = new Node(); root.val = 5;
        Node n3 = new Node(); n3.val = 3; n3.parent = root;
        Node n6 = new Node(); n6.val = 6; n6.parent = root;
        root.left = n3; root.right = n6;
        assertEquals(5, test.inorderSuccessor(n3).val);
    }

    @Test
    public void testSuccessorIsRightChild() {
        // Node has right child -> successor is leftmost in right subtree
        Node root = new Node(); root.val = 5;
        Node n7 = new Node(); n7.val = 7; n7.parent = root;
        root.right = n7;
        assertEquals(7, test.inorderSuccessor(root).val);
    }

    @Test
    public void testSuccessorIsLeftmostInRightSubtree() {
        Node root = new Node(); root.val = 5;
        Node n8 = new Node(); n8.val = 8; n8.parent = root;
        Node n6 = new Node(); n6.val = 6; n6.parent = n8;
        root.right = n8; n8.left = n6;
        assertEquals(6, test.inorderSuccessor(root).val);
    }

    @Test
    public void testRightmostNodeNoSuccessor() {
        Node root = new Node(); root.val = 2;
        Node n1 = new Node(); n1.val = 1; n1.parent = root;
        Node n3 = new Node(); n3.val = 3; n3.parent = root;
        root.left = n1; root.right = n3;
        assertNull(test.inorderSuccessor(n3));
    }

    @Test
    public void testSuccessorIsGrandparent() {
        // Tree:    5
        //        3
        //          4
        // Inorder: 3,4,5 -> successor of 4 is 5
        Node root = new Node(); root.val = 5;
        Node n3 = new Node(); n3.val = 3; n3.parent = root;
        root.left = n3;
        Node n4 = new Node(); n4.val = 4; n4.parent = n3;
        n3.right = n4;
        assertEquals(5, test.inorderSuccessor(n4).val);
    }

    @Test
    public void testRootWithRightSubtree() {
        Node root = new Node(); root.val = 10;
        Node n15 = new Node(); n15.val = 15; n15.parent = root;
        Node n12 = new Node(); n12.val = 12; n12.parent = n15;
        root.right = n15; n15.left = n12;
        assertEquals(12, test.inorderSuccessor(root).val);
    }

    @Test
    public void testLeftChildSuccessorIsParent() {
        Node root = new Node(); root.val = 10;
        Node n5 = new Node(); n5.val = 5; n5.parent = root;
        root.left = n5;
        assertEquals(10, test.inorderSuccessor(n5).val);
    }

    @Test
    public void testGiantTree() {
        // Build a chain: 1 <- 2 <- 3 <- ... <- 10 (right-skewed)
        Node[] nodes = new Node[11];
        for (int i = 1; i <= 10; i++) {
            nodes[i] = new Node(); nodes[i].val = i;
        }
        // Build as BST: each node's right child is next
        for (int i = 1; i < 10; i++) {
            nodes[i].right = nodes[i + 1];
            nodes[i + 1].parent = nodes[i];
        }
        // Successor of node 5 should be 6 (right child)
        assertEquals(6, test.inorderSuccessor(nodes[5]).val);
        // Successor of node 10 (rightmost) should be null
        assertNull(test.inorderSuccessor(nodes[10]));
        // Successor of node 1 (root with right child) should be 2
        assertEquals(2, test.inorderSuccessor(nodes[1]).val);
    }
}
