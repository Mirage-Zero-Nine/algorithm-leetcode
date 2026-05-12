package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import library.tree.narytree.Node;
import org.junit.jupiter.api.Test;

public class Preorder589Test {

    private final Preorder_589 test = new Preorder_589();

    @Test
    public void testHappyCases() {
        Node root = new Node(1, Arrays.asList(new Node(3, Arrays.asList(new Node(5), new Node(6))), new Node(2), new Node(4)));
        assertEquals(List.of(1, 3, 5, 6, 2, 4), test.preorder(root));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(List.of(), test.preorder(null));
        assertEquals(List.of(1), test.preorder(new Node(1)));
    }

    @Test
    public void testLargeCase() {
        Node root = new Node(1, Arrays.asList(new Node(2), new Node(3), new Node(4)));
        assertEquals(List.of(1, 2, 3, 4), test.preorder(root));
    }
}
