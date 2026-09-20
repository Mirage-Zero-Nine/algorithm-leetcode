package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import library.tree.narytree.Node;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class Postorder_590Test {

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

    @Test
    public void testSingleChildChain() {
        Node root = new Node(1, Collections.singletonList(
                new Node(2, Collections.singletonList(
                        new Node(3, Collections.singletonList(new Node(4)))))));
        assertEquals(List.of(4, 3, 2, 1), test.postorder(root));
    }

    @Test
    public void testWideTree() {
        Node root = new Node(1, Arrays.asList(new Node(2), new Node(3), new Node(4), new Node(5), new Node(6)));
        assertEquals(List.of(2, 3, 4, 5, 6, 1), test.postorder(root));
    }

    @Test
    public void testTwoLevelTree() {
        Node root = new Node(1, Arrays.asList(
                new Node(2, Arrays.asList(new Node(5), new Node(6))),
                new Node(3, Arrays.asList(new Node(7), new Node(8))),
                new Node(4)
        ));
        assertEquals(List.of(5, 6, 2, 7, 8, 3, 4, 1), test.postorder(root));
    }

    @Test
    public void testNodeWithNullChildren() {
        Node root = new Node(1);
        root.children = null;
        assertEquals(List.of(1), test.postorder(root));
    }

    @Test
    public void testNodeWithEmptyChildren() {
        Node root = new Node(1, Collections.emptyList());
        assertEquals(List.of(1), test.postorder(root));
    }

    @Test
    public void testNegativeValues() {
        Node root = new Node(-1, Arrays.asList(new Node(-2), new Node(-3)));
        assertEquals(List.of(-2, -3, -1), test.postorder(root));
    }

    @Test
    public void testGiantTree() {
        // Build a chain of 100 nodes
        Node cur = new Node(100);
        for (int i = 99; i >= 1; i--) {
            cur = new Node(i, Collections.singletonList(cur));
        }
        List<Integer> result = test.postorder(cur);
        assertEquals(100, result.size());
        // Postorder of a chain: deepest first
        assertEquals(100, result.get(0));
        assertEquals(1, result.get(99));
    }

    @ParameterizedTest(name = "N-ary chain size {0}")
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    public void testChainSizes(int size) {
        Node root = new Node(1);
        Node current = root;
        for (int value = 2; value <= size; value++) {
            Node child = new Node(value);
            current.children = Collections.singletonList(child);
            current = child;
        }
        List<Integer> result = test.postorder(root);
        assertEquals(size, result.size());
        for (int i = 0; i < size; i++) assertEquals(size - i, result.get(i));
    }
}
