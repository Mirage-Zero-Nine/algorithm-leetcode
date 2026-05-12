package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import library.tree.narytree.Node;
import org.junit.jupiter.api.Test;

public class Postorder590Test {

    private final Postorder_590 test = new Postorder_590();

    @Test
    public void testHappyCases() {
        Node root = new Node(1, Arrays.asList(new Node(3, Arrays.asList(new Node(5), new Node(6))), new Node(2), new Node(4)));
        assertEquals(List.of(5, 6, 3, 2, 4, 1), test.postorder(root));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(List.of(), test.postorder(null));
        assertEquals(List.of(1), test.postorder(new Node(1)));
    }

    @Test
    public void testLargeCase() {
        Node root = new Node(1, Arrays.asList(new Node(2), new Node(3), new Node(4)));
        assertEquals(List.of(2, 3, 4, 1), test.postorder(root));
    }
}
