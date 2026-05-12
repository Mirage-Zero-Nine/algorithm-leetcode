package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.randomnode.Node;
import library.randomnode.NodeCopy;
import org.junit.jupiter.api.Test;

public class CopyRandomBinaryTree1485Test {

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
}
