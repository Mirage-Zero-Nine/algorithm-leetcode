package solution.linkedlist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.tree.binarytree.Node;
import org.junit.jupiter.api.Test;

public class LowestCommonAncestor1650Test {

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
}
