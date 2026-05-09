package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Arrays;
import library.tree.narytree.Node;
import org.junit.jupiter.api.Test;

public class CloneTree1490Test {

    private final CloneTree_1490 test = new CloneTree_1490();

    @Test
    public void testHappyCases() {
        Node root = new Node(1, Arrays.asList(new Node(2), new Node(3)));
        Node clone = test.cloneTree(root);
        assertEquals(1, clone.val);
        assertNotSame(root, clone);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.cloneTree(null));
        assertEquals(1, test.cloneTree(new Node(1)).val);
    }

    @Test
    public void testLargeCase() {
        Node root = new Node(1, Arrays.asList(new Node(2), new Node(3), new Node(4)));
        Node clone = test.cloneTree(root);
        assertEquals(3, clone.children.size());
    }
}
