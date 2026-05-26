package solutions.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import library.tree.narytree.Node;
import org.junit.jupiter.api.Test;

public class LevelOrder_429Test {

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

    @Test
    public void testSingleChain() {
        Node root = new Node(1, List.of(
                new Node(2, List.of(
                        new Node(3, List.of())
                ))
        ));
        assertEquals(List.of(List.of(1), List.of(2), List.of(3)), test.levelOrder(root));
    }

    @Test
    public void testWideSingleLevel() {
        Node root = new Node(1, Arrays.asList(
                new Node(2, List.of()), new Node(3, List.of()), new Node(4, List.of()), new Node(5, List.of())
        ));
        assertEquals(List.of(List.of(1), List.of(2, 3, 4, 5)), test.levelOrder(root));
    }

    @Test
    public void testThreeLevelsMixedBranching() {
        Node root = new Node(1, Arrays.asList(
                new Node(2, Arrays.asList(new Node(5, List.of()), new Node(6, List.of()))),
                new Node(3, List.of()),
                new Node(4, Arrays.asList(new Node(7, List.of())))
        ));
        assertEquals(List.of(List.of(1), List.of(2, 3, 4), List.of(5, 6, 7)), test.levelOrder(root));
    }

    @Test
    public void testNegativeValues() {
        Node root = new Node(-1, Arrays.asList(
                new Node(-2, List.of()),
                new Node(-3, List.of())
        ));
        assertEquals(List.of(List.of(-1), List.of(-2, -3)), test.levelOrder(root));
    }

    @Test
    public void testDuplicateValues() {
        Node root = new Node(1, Arrays.asList(
                new Node(1, List.of()),
                new Node(1, List.of())
        ));
        assertEquals(List.of(List.of(1), List.of(1, 1)), test.levelOrder(root));
    }

    @Test
    public void testNullChildEntriesIgnored() {
        Node root = new Node(1, Arrays.asList(
                null,
                new Node(2, List.of()),
                null
        ));
        assertEquals(List.of(List.of(1), List.of(2)), test.levelOrder(root));
    }

    @Test
    public void testGiantDepthChain() {
        Node root = new Node(0, List.of());
        Node current = root;
        for (int i = 1; i <= 80; i++) {
            Node next = new Node(i, List.of());
            current.children = List.of(next);
            current = next;
        }
        List<List<Integer>> out = test.levelOrder(root);
        assertEquals(81, out.size());
        assertEquals(List.of(80), out.get(out.size() - 1));
    }
}
