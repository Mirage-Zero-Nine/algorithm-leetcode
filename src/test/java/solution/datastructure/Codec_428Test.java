package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import library.tree.narytree.Node;
import org.junit.jupiter.api.Test;

public class Codec_428Test {

    private final Codec_428 test = new Codec_428();

    @Test
    public void testHappyCases() {
        Node root = new Node(1, Arrays.asList(
            new Node(3, Arrays.asList(new Node(5, List.of()), new Node(6, List.of()))),
            new Node(2, List.of()), new Node(4, List.of())));
        Node result = test.deserialize(test.serialize(root));
        assertEquals(1, result.val);
        assertEquals(3, result.children.get(0).val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.deserialize(test.serialize(null)));
        assertEquals(1, test.deserialize(test.serialize(new Node(1, List.of()))).val);
    }

    @Test
    public void testLargeCase() {
        Node root = new Node(1, Arrays.asList(
            new Node(2, List.of()), new Node(3, List.of()), new Node(4, List.of()), new Node(5, List.of())));
        Node result = test.deserialize(test.serialize(root));
        assertEquals(4, result.children.size());
        assertEquals(5, result.children.get(3).val);
    }

    @Test
    public void testSerializeNullAsEmptyString() {
        assertEquals("", test.serialize(null));
    }

    @Test
    public void testDeserializeEmptyString() {
        assertNull(test.deserialize(""));
    }

    @Test
    public void testSingleNodeRoundTrip() {
        Node root = new Node(42, List.of());
        assertTrue(isSameTree(root, test.deserialize(test.serialize(root))));
    }

    @Test
    public void testNegativeValuesRoundTrip() {
        Node root = new Node(-1, Arrays.asList(new Node(-2, List.of()), new Node(-3, List.of())));
        assertTrue(isSameTree(root, test.deserialize(test.serialize(root))));
    }

    @Test
    public void testDeepChainRoundTrip() {
        Node root = new Node(1, List.of(
            new Node(2, List.of(
                new Node(3, List.of(
                    new Node(4, List.of())
                ))
            ))
        ));
        assertTrue(isSameTree(root, test.deserialize(test.serialize(root))));
    }

    @Test
    public void testDuplicateValuesRoundTrip() {
        Node root = new Node(7, Arrays.asList(new Node(7, List.of()), new Node(7, List.of()), new Node(7, List.of())));
        assertTrue(isSameTree(root, test.deserialize(test.serialize(root))));
    }

    @Test
    public void testGiantNaryTreeRoundTrip() {
        Node root = buildTree(5, 4, 1);
        assertTrue(isSameTree(root, test.deserialize(test.serialize(root))));
    }

    private Node buildTree(int depth, int width, int startVal) {
        if (depth == 1) {
            return new Node(startVal, List.of());
        }
        List<Node> children = new ArrayList<>();
        for (int i = 0; i < width; i++) {
            children.add(buildTree(depth - 1, width, startVal * 10 + i + 1));
        }
        return new Node(startVal, children);
    }

    private boolean isSameTree(Node a, Node b) {
        if (a == null && b == null) {
            return true;
        }
        if (a == null || b == null || a.val != b.val || a.children.size() != b.children.size()) {
            return false;
        }
        for (int i = 0; i < a.children.size(); i++) {
            if (!isSameTree(a.children.get(i), b.children.get(i))) {
                return false;
            }
        }
        return true;
    }
}
