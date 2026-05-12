package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Arrays;
import java.util.List;
import library.tree.narytree.Node;
import org.junit.jupiter.api.Test;

public class Codec428Test {

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
}
