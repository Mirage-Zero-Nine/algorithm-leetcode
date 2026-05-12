package solution.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import library.tree.narytree.Node;
import org.junit.jupiter.api.Test;

public class LevelOrder429Test {

    private final LevelOrder_429 test = new LevelOrder_429();

    @Test
    public void testHappyCases() {
        Node root = new Node(1, Arrays.asList(
            new Node(3, Arrays.asList(new Node(5, List.of()), new Node(6, List.of()))),
            new Node(2, List.of()), new Node(4, List.of())));
        assertEquals(List.of(List.of(1), List.of(3, 2, 4), List.of(5, 6)), test.levelOrder(root));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(List.of(), test.levelOrder(null));
        assertEquals(List.of(List.of(1)), test.levelOrder(new Node(1, List.of())));
    }

    @Test
    public void testLargeCase() {
        Node root = new Node(1, Arrays.asList(new Node(2, List.of()), new Node(3, List.of()), new Node(4, List.of())));
        assertEquals(List.of(List.of(1), List.of(2, 3, 4)), test.levelOrder(root));
    }
}
