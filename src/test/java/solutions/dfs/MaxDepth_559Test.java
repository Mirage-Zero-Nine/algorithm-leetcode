package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import library.tree.narytree.Node;
import org.junit.jupiter.api.Test;

public class MaxDepth_559Test {

    private final MaxDepth_559 solution = new MaxDepth_559();

    @Test
    public void testLeetCodeExample() {
        Node root = new Node(1, Arrays.asList(
                new Node(3, Arrays.asList(new Node(5), new Node(6))),
                new Node(2),
                new Node(4)));

        assertBoth(3, root);
    }

    @Test
    public void testNullRoot() {
        assertBoth(0, null);
    }

    @Test
    public void testSingleNodeWithDefaultChildren() {
        assertBoth(1, new Node(42));
    }

    @Test
    public void testSingleNodeWithEmptyChildren() {
        assertBoth(1, new Node(42, Collections.emptyList()));
    }

    @Test
    public void testSingleNodeWithNullChildrenList() {
        Node root = new Node(42);
        root.children = null;

        assertBoth(1, root);
    }

    @Test
    public void testOnlyNullChildren() {
        Node root = new Node(1, Arrays.asList(null, null, null));

        assertBoth(1, root);
    }

    @Test
    public void testNullChildrenEntriesAreIgnored() {
        Node leaf = new Node(3);
        Node child = new Node(2, Arrays.asList(null, leaf, null));
        Node root = new Node(1, Arrays.asList(null, child, null));

        assertBoth(3, root);
    }

    @Test
    public void testRootWithManyLeafChildren() {
        List<Node> children = new ArrayList<>();
        for (int value = 1; value <= 100; value++) {
            children.add(new Node(value));
        }

        assertBoth(2, new Node(0, children));
    }

    @Test
    public void testSingleChildChain() {
        Node current = new Node(5);
        for (int value = 4; value >= 1; value--) {
            current = new Node(value, Collections.singletonList(current));
        }

        assertBoth(5, current);
    }

    @Test
    public void testBothApproachesOnDeeperTree() {
        final int depth = 1_000;
        Node root = new Node(0);
        Node current = root;

        for (int value = 1; value < depth; value++) {
            Node child = new Node(value);
            current.children = Collections.singletonList(child);
            current = child;
        }

        // Explicitly exercises both recursive and iterative DFS implementations.
        assertBoth(depth, root);
    }

    @Test
    public void testDeepestBranchIsNotFirst() {
        Node deepest = new Node(5, Collections.singletonList(new Node(6)));
        Node root = new Node(1, Arrays.asList(
                new Node(2),
                new Node(3, Collections.singletonList(deepest)),
                new Node(4)));

        assertBoth(4, root);
    }

    @Test
    public void testUnevenBranches() {
        Node root = new Node(1, Arrays.asList(
                new Node(2, Collections.singletonList(new Node(5))),
                new Node(3, Arrays.asList(
                        new Node(6),
                        new Node(7, Collections.singletonList(
                                new Node(8, Collections.singletonList(new Node(9))))))),
                new Node(4)));

        assertBoth(5, root);
    }

    @Test
    public void testNegativeAndDuplicateValuesDoNotAffectDepth() {
        Node root = new Node(-1, Arrays.asList(
                new Node(-1),
                new Node(0, Collections.singletonList(new Node(0))),
                new Node(Integer.MAX_VALUE, Collections.singletonList(
                        new Node(Integer.MIN_VALUE)))));

        assertBoth(3, root);
    }

    @Test
    public void testMultipleCallsOnSameInstanceAreIndependent() {
        Node deepTree = new Node(1, Collections.singletonList(
                new Node(2, Collections.singletonList(new Node(3)))));

        assertBoth(3, deepTree);
        assertBoth(1, new Node(4));
        assertBoth(0, null);
        assertBoth(2, new Node(5, Collections.singletonList(new Node(6))));
    }

    @Test
    public void testBalancedTree() {
        Node root = new Node(1, Arrays.asList(
                new Node(2, Arrays.asList(new Node(5), new Node(6))),
                new Node(3, Arrays.asList(new Node(7), new Node(8))),
                new Node(4, Arrays.asList(new Node(9), new Node(10)))));

        assertBoth(3, root);
    }

    @Test
    public void testLargeWideTree() {
        List<Node> children = new ArrayList<>();
        for (int value = 1; value <= 10_000; value++) {
            children.add(new Node(value));
        }

        assertBoth(2, new Node(0, children));
    }

    @Test
    public void testIterativeDfsHandlesVeryDeepTreeWithoutCallStack() {
        final int additionalNodes = 20_000;
        Node root = new Node(0);
        Node current = root;

        for (int value = 1; value <= additionalNodes; value++) {
            Node child = new Node(value);
            current.children = Collections.singletonList(child);
            current = child;
        }

        // The recursive version intentionally is not used here: this case
        // verifies that the iterative implementation is not call-stack bound.
        assertEquals(additionalNodes + 1, solution.maxDepthIterative(root));
    }

    private void assertBoth(int expected, Node root) {
        assertEquals(expected, solution.maxDepth(root));
        assertEquals(expected, solution.maxDepthIterative(root));
    }
}
