package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import library.tree.narytree.Node;
import org.junit.jupiter.api.Test;

public class CloneTree_1490Test {

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

    @Test
    public void testDeepCopyIndependence() {
        Node root = new Node(1, Arrays.asList(new Node(2), new Node(3)));
        Node clone = test.cloneTree(root);
        clone.val = 99;
        assertEquals(1, root.val);
    }

    @Test
    public void testChildrenDeepCopy() {
        Node child = new Node(2);
        Node root = new Node(1, Arrays.asList(child));
        Node clone = test.cloneTree(root);
        assertNotSame(child, clone.children.get(0));
        assertEquals(2, clone.children.get(0).val);
    }

    @Test
    public void testSingleNodeNoChildren() {
        Node root = new Node(5);
        Node clone = test.cloneTree(root);
        assertEquals(5, clone.val);
        assertTrue(clone.children == null || clone.children.isEmpty());
    }

    @Test
    public void testEmptyChildrenList() {
        Node root = new Node(1, Collections.emptyList());
        Node clone = test.cloneTree(root);
        assertEquals(1, clone.val);
        assertTrue(clone.children.isEmpty());
    }

    @Test
    public void testDeepTree() {
        // Chain: 1 -> 2 -> 3 -> 4
        Node n4 = new Node(4, Collections.emptyList());
        Node n3 = new Node(3, Arrays.asList(n4));
        Node n2 = new Node(2, Arrays.asList(n3));
        Node root = new Node(1, Arrays.asList(n2));
        Node clone = test.cloneTree(root);
        assertEquals(4, clone.children.get(0).children.get(0).children.get(0).val);
        assertNotSame(n4, clone.children.get(0).children.get(0).children.get(0));
    }

    @Test
    public void testWideTree() {
        // Root with 5 children
        Node root = new Node(1, Arrays.asList(
                new Node(2), new Node(3), new Node(4), new Node(5), new Node(6)));
        Node clone = test.cloneTree(root);
        assertEquals(5, clone.children.size());
        for (int i = 0; i < 5; i++) {
            assertEquals(i + 2, clone.children.get(i).val);
        }
    }

    @Test
    public void testGiantTree() {
        // Build a tree with root having 100 children, each with 1 child
        Node root = new Node(0);
        Node[] children = new Node[100];
        for (int i = 0; i < 100; i++) {
            children[i] = new Node(i + 1, Arrays.asList(new Node(i + 101)));
        }
        root.children = Arrays.asList(children);
        Node clone = test.cloneTree(root);
        assertEquals(100, clone.children.size());
        assertEquals(50, clone.children.get(49).val);
        assertEquals(150, clone.children.get(49).children.get(0).val);
        assertNotSame(root.children.get(0), clone.children.get(0));
    }
}
