package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.tree.binarytree.Node;
import org.junit.jupiter.api.Test;

public class InorderSuccessor510Test {

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
}
