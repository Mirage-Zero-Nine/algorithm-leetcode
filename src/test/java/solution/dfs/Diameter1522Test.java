package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import library.tree.narytree.Node;
import org.junit.jupiter.api.Test;

public class Diameter1522Test {

    private final Diameter_1522 test = new Diameter_1522();

    @Test
    public void testHappyCases() {
        Node root = new Node(1, Arrays.asList(new Node(3, Arrays.asList(new Node(5), new Node(6))), new Node(2), new Node(4)));
        assertEquals(3, test.diameter(root));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.diameter(null));
        assertEquals(0, test.diameter(new Node(1)));
    }

    @Test
    public void testLargeCase() {
        Node root = new Node(1, Arrays.asList(new Node(2), new Node(3), new Node(4)));
        assertEquals(2, test.diameter(root));
    }
}
