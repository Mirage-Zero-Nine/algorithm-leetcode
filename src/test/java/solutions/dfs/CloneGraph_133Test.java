package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import library.graph.Node;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

/**
 * Tests for both graph-cloning traversals in {@link CloneGraph_133}.
 *
 * <p>The expected result is derived by an identity-based graph oracle rather than by comparing the
 * two implementations. This matters because graph values are labels, not object identity, and a
 * correct clone must preserve cycles, neighbor order, repeated references, and alias isolation.</p>
 */
class CloneGraph_133Test {

    @Test
    void cloneGraphReturnsNullForTheEmptyInput() {
        assertNull(new CloneGraph_133().cloneGraph(null));
    }

    @Test
    void cloneGraphBfsReturnsNullForTheEmptyInput() {
        assertNull(new CloneGraph_133().cloneGraphBFS(null));
    }

    @Test
    void clonesOfficialSingletonExample() {
        assertBoth(() -> graph(1, new int[][]{}));
    }

    @Test
    void clonesOfficialFourNodeExampleAndPreservesNeighborOrder() {
        assertBoth(() -> graph(4, new int[][]{{1, 2}, {1, 4}, {2, 3}, {3, 4}}));
    }

    @Test
    void clonesTwoNodeUndirectedCycle() {
        assertBoth(() -> graph(2, new int[][]{{1, 2}}));
    }

    @Test
    void clonesTriangleWithSeveralCycles() {
        assertBoth(() -> graph(3, new int[][]{{1, 2}, {2, 3}, {3, 1}}));
    }

    @Test
    void clonesSquareWithAChord() {
        assertBoth(() -> graph(4, new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 1}, {1, 3}}));
    }

    @Test
    void clonesADeepSparseChain() {
        assertBoth(() -> chain(25));
    }

    @Test
    void clonesSparseBranchingGraph() {
        assertBoth(() -> graph(9, new int[][]{
                {1, 2}, {1, 3}, {2, 4}, {2, 5}, {3, 6}, {6, 7}, {6, 8}, {8, 9}
        }));
    }

    @Test
    void clonesStarGraphWithTheCenterAsTheEntryPoint() {
        assertBoth(() -> star(10));
    }

    @Test
    void clonesStarGraphWhenTheEntryPointIsALeaf() {
        assertBothFrom(() -> star(10), 1);
    }

    @Test
    void clonesCompleteGraphOfFiveNodes() {
        assertBoth(() -> complete(5));
    }

    @Test
    void clonesCompleteGraphAtTheMaximumOfficialSize() {
        assertBoth(() -> complete(100));
    }

    @Test
    void clonesTheMaximumLengthChain() {
        assertBoth(() -> chain(100));
    }

    @Test
    void clonesAValidGraphWithTheMinimumAndMaximumLabels() {
        assertBoth(() -> graphWithLabels(1, 100));
    }

    @Test
    void clonesARequestedSelfLoopEvenThoughTheLeetCodeInputExcludesIt() {
        assertBoth(CloneGraph_133Test::selfLoop);
    }

    @Test
    void clonesSelfLoopAlongsideAnUndirectedCycle() {
        assertBoth(CloneGraph_133Test::selfLoopAndCycle);
    }

    @Test
    void clonesRepeatedNeighborReferencesWithoutSharingOriginalNodes() {
        assertBoth(CloneGraph_133Test::repeatedNeighborReferenceGraph);
    }

    @Test
    void cloneGraphIsADeepCopyAndOriginalMutationDoesNotLeakIntoIt() {
        assertMutationIsolation((solver, node) -> solver.cloneGraph(node));
    }

    @Test
    void cloneGraphBfsIsADeepCopyAndOriginalMutationDoesNotLeakIntoIt() {
        assertMutationIsolation((solver, node) -> solver.cloneGraphBFS(node));
    }

    @Test
    void cloneGraphDoesNotShareNeighborListsOrNodesWithTheOriginal() {
        assertNoAliasing((solver, node) -> solver.cloneGraph(node), CloneGraph_133Test::branchingCycle);
    }

    @Test
    void cloneGraphBfsDoesNotShareNeighborListsOrNodesWithTheOriginal() {
        assertNoAliasing((solver, node) -> solver.cloneGraphBFS(node), CloneGraph_133Test::branchingCycle);
    }

    @Test
    void cloneGraphCanBeReusedForIndependentGraphs() {
        CloneGraph_133 solver = new CloneGraph_133();
        Node first = graph(3, new int[][]{{1, 2}, {2, 3}});
        Node second = graph(4, new int[][]{{1, 2}, {1, 3}, {1, 4}});
        assertCloneMatches(first, solver.cloneGraph(first));
        assertCloneMatches(second, solver.cloneGraph(second));
    }

    @Test
    void cloneGraphBfsCanBeReusedForIndependentGraphs() {
        CloneGraph_133 solver = new CloneGraph_133();
        Node first = graph(3, new int[][]{{1, 2}, {2, 3}});
        Node second = graph(4, new int[][]{{1, 2}, {1, 3}, {1, 4}});
        assertCloneMatches(first, solver.cloneGraphBFS(first));
        assertCloneMatches(second, solver.cloneGraphBFS(second));
    }

    @Test
    void cloneGraphPreservesAnArbitraryEntryPointAndAllReachableNodes() {
        assertBothFrom(() -> graph(8, new int[][]{
                {1, 2}, {2, 3}, {3, 4}, {4, 5}, {5, 6}, {6, 7}, {7, 8}, {8, 1}, {2, 7}
        }), 3);
    }

    @Test
    void cloneGraphBfsPreservesRepeatedEdgesInTheirOriginalPositions() {
        Node original = repeatedNeighborReferenceGraph();
        Node clone = new CloneGraph_133().cloneGraphBFS(original);
        assertCloneMatches(original, clone);
        assertSame(clone.neighbors.get(0), clone.neighbors.get(1));
    }

    @Test
    void cloneGraphPreservesRepeatedEdgesInTheirOriginalPositions() {
        Node original = repeatedNeighborReferenceGraph();
        Node clone = new CloneGraph_133().cloneGraph(original);
        assertCloneMatches(original, clone);
        assertSame(clone.neighbors.get(0), clone.neighbors.get(1));
    }

    @Test
    void cloneGraphKeepsTheOriginalGraphUnchanged() {
        assertOriginalUnchanged((solver, node) -> solver.cloneGraph(node), CloneGraph_133Test::branchingCycle);
    }

    @Test
    void cloneGraphBfsKeepsTheOriginalGraphUnchanged() {
        assertOriginalUnchanged((solver, node) -> solver.cloneGraphBFS(node), CloneGraph_133Test::branchingCycle);
    }

    private void assertBoth(Supplier<Node> builder) {
        assertBothFrom(builder, 0);
    }

    private void assertBothFrom(Supplier<Node> builder, int valueMinusOne) {
        Node dfsInput = builder.get();
        Node bfsInput = builder.get();
        Node dfsEntry = entry(dfsInput, valueMinusOne);
        Node bfsEntry = entry(bfsInput, valueMinusOne);
        assertCloneMatches(dfsEntry, new CloneGraph_133().cloneGraph(dfsEntry));
        assertCloneMatches(bfsEntry, new CloneGraph_133().cloneGraphBFS(bfsEntry));
    }

    /**
     * The graph builders return node 1. For tests that want another entry point, walk by value so
     * this helper remains independent of the production traversal and does not reuse cloned nodes.
     */
    private Node entry(Node start, int valueMinusOne) {
        if (valueMinusOne == 0) {
            return start;
        }
        Deque<Node> queue = new ArrayDeque<>();
        Set<Node> visited = java.util.Collections.newSetFromMap(new IdentityHashMap<>());
        queue.add(start);
        visited.add(start);
        while (!queue.isEmpty()) {
            Node current = queue.remove();
            if (current.val == valueMinusOne + 1) {
                return current;
            }
            for (Node neighbor : current.neighbors) {
                if (visited.add(neighbor)) {
                    queue.add(neighbor);
                }
            }
        }
        throw new IllegalArgumentException("entry node is not reachable");
    }

    private void assertCloneMatches(Node original, Node clone) {
        assertTrue(original != null && clone != null);
        Map<Node, Node> originalToClone = new IdentityHashMap<>();
        Map<Node, Node> cloneToOriginal = new IdentityHashMap<>();
        Deque<NodePair> queue = new ArrayDeque<>();
        originalToClone.put(original, clone);
        cloneToOriginal.put(clone, original);
        queue.add(new NodePair(original, clone));

        while (!queue.isEmpty()) {
            NodePair pair = queue.remove();
            Node source = pair.source();
            Node copy = pair.copy();
            assertNotSame(source, copy);
            assertEquals(source.val, copy.val);
            assertEquals(source.neighbors.size(), copy.neighbors.size());
            assertNotSame(source.neighbors, copy.neighbors);

            for (int i = 0; i < source.neighbors.size(); i++) {
                Node sourceNeighbor = source.neighbors.get(i);
                Node copyNeighbor = copy.neighbors.get(i);
                Node expectedCopy = originalToClone.get(sourceNeighbor);
                if (expectedCopy == null) {
                    assertNull(cloneToOriginal.get(copyNeighbor));
                    originalToClone.put(sourceNeighbor, copyNeighbor);
                    cloneToOriginal.put(copyNeighbor, sourceNeighbor);
                    queue.add(new NodePair(sourceNeighbor, copyNeighbor));
                } else {
                    assertSame(expectedCopy, copyNeighbor);
                }
            }
        }
        assertEquals(originalToClone.size(), cloneToOriginal.size());
    }

    private void assertMutationIsolation(Cloner cloner) {
        Node original = branchingCycle();
        GraphSnapshot snapshot = GraphSnapshot.capture(original);
        Node clone = cloner.clone(new CloneGraph_133(), original);
        original.val = 900;
        original.neighbors.clear();
        assertSnapshotMatches(snapshot, clone);

        Node secondOriginal = branchingCycle();
        GraphSnapshot secondSnapshot = GraphSnapshot.capture(secondOriginal);
        Node secondClone = cloner.clone(new CloneGraph_133(), secondOriginal);
        secondClone.val = -900;
        secondClone.neighbors.clear();
        assertSnapshotUntouched(secondSnapshot);
    }

    private void assertNoAliasing(Cloner cloner, Supplier<Node> builder) {
        Node original = builder.get();
        Node clone = cloner.clone(new CloneGraph_133(), original);
        assertCloneMatches(original, clone);
        Set<Node> originals = java.util.Collections.newSetFromMap(new IdentityHashMap<>());
        originals.addAll(GraphSnapshot.capture(original).values.keySet());
        for (Node copied : GraphSnapshot.capture(clone).values.keySet()) {
            assertTrue(!originals.contains(copied));
        }
    }

    private void assertOriginalUnchanged(Cloner cloner, Supplier<Node> builder) {
        Node original = builder.get();
        GraphSnapshot snapshot = GraphSnapshot.capture(original);
        cloner.clone(new CloneGraph_133(), original);
        assertSnapshotUntouched(snapshot);
    }

    private static Node graph(int size, int[][] edges) {
        Node[] nodes = new Node[size];
        for (int i = 0; i < size; i++) {
            nodes[i] = new Node(i + 1, new ArrayList<>());
        }
        for (int[] edge : edges) {
            nodes[edge[0] - 1].neighbors.add(nodes[edge[1] - 1]);
            nodes[edge[1] - 1].neighbors.add(nodes[edge[0] - 1]);
        }
        return nodes[0];
    }

    private static Node graphWithLabels(int first, int second) {
        Node left = new Node(first, new ArrayList<>());
        Node right = new Node(second, new ArrayList<>());
        left.neighbors.add(right);
        right.neighbors.add(left);
        return left;
    }

    private static Node chain(int size) {
        Node root = new Node(1, new ArrayList<>());
        Node current = root;
        for (int value = 2; value <= size; value++) {
            Node next = new Node(value, new ArrayList<>());
            current.neighbors.add(next);
            next.neighbors.add(current);
            current = next;
        }
        return root;
    }

    private static Node star(int size) {
        Node center = new Node(1, new ArrayList<>());
        for (int value = 2; value <= size; value++) {
            Node leaf = new Node(value, new ArrayList<>());
            center.neighbors.add(leaf);
            leaf.neighbors.add(center);
        }
        return center;
    }

    private static Node complete(int size) {
        Node[] nodes = new Node[size];
        for (int i = 0; i < size; i++) {
            nodes[i] = new Node(i + 1, new ArrayList<>());
        }
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                nodes[i].neighbors.add(nodes[j]);
                nodes[j].neighbors.add(nodes[i]);
            }
        }
        return nodes[0];
    }

    private static Node selfLoop() {
        Node node = new Node(1, new ArrayList<>());
        node.neighbors.add(node);
        return node;
    }

    private static Node selfLoopAndCycle() {
        Node one = new Node(1, new ArrayList<>());
        Node two = new Node(2, new ArrayList<>());
        Node three = new Node(3, new ArrayList<>());
        one.neighbors.add(one);
        one.neighbors.add(two);
        two.neighbors.add(one);
        two.neighbors.add(three);
        three.neighbors.add(two);
        three.neighbors.add(one);
        one.neighbors.add(three);
        return one;
    }

    private static Node repeatedNeighborReferenceGraph() {
        Node one = new Node(1, new ArrayList<>());
        Node two = new Node(2, new ArrayList<>());
        Node three = new Node(3, new ArrayList<>());
        one.neighbors.add(two);
        one.neighbors.add(two);
        one.neighbors.add(three);
        two.neighbors.add(one);
        three.neighbors.add(one);
        return one;
    }

    private static Node branchingCycle() {
        return graph(6, new int[][]{{1, 2}, {1, 3}, {2, 4}, {3, 4}, {4, 5}, {5, 6}, {6, 1}});
    }

    private void assertSnapshotMatches(GraphSnapshot snapshot, Node clone) {
        Map<Node, Node> expectedToClone = new IdentityHashMap<>();
        Deque<Node> queue = new ArrayDeque<>();
        expectedToClone.put(snapshot.root, clone);
        queue.add(snapshot.root);
        while (!queue.isEmpty()) {
            Node expected = queue.remove();
            Node actual = expectedToClone.get(expected);
            assertEquals(snapshot.values.get(expected), actual.val);
            List<Node> expectedNeighbors = snapshot.neighbors.get(expected);
            assertEquals(expectedNeighbors.size(), actual.neighbors.size());
            for (int i = 0; i < expectedNeighbors.size(); i++) {
                Node expectedNeighbor = expectedNeighbors.get(i);
                Node actualNeighbor = expectedToClone.get(expectedNeighbor);
                if (actualNeighbor == null) {
                    actualNeighbor = actual.neighbors.get(i);
                    expectedToClone.put(expectedNeighbor, actualNeighbor);
                    queue.add(expectedNeighbor);
                }
                assertSame(actualNeighbor, actual.neighbors.get(i));
            }
        }
    }

    private void assertSnapshotUntouched(GraphSnapshot snapshot) {
        for (Node node : snapshot.values.keySet()) {
            assertEquals(snapshot.values.get(node), node.val);
            assertEquals(snapshot.neighbors.get(node), node.neighbors);
        }
    }

    private record NodePair(Node source, Node copy) {
    }

    private static final class GraphSnapshot {
        private final Node root;
        private final Map<Node, Integer> values;
        private final Map<Node, List<Node>> neighbors;

        private GraphSnapshot(Node root, Map<Node, Integer> values, Map<Node, List<Node>> neighbors) {
            this.root = root;
            this.values = values;
            this.neighbors = neighbors;
        }

        private static GraphSnapshot capture(Node root) {
            Map<Node, Integer> values = new IdentityHashMap<>();
            Map<Node, List<Node>> neighbors = new IdentityHashMap<>();
            Deque<Node> queue = new ArrayDeque<>();
            queue.add(root);
            values.put(root, root.val);
            while (!queue.isEmpty()) {
                Node node = queue.remove();
                neighbors.put(node, new ArrayList<>(node.neighbors));
                for (Node neighbor : node.neighbors) {
                    if (!values.containsKey(neighbor)) {
                        values.put(neighbor, neighbor.val);
                        queue.add(neighbor);
                    }
                }
            }
            return new GraphSnapshot(root, values, neighbors);
        }
    }

    @FunctionalInterface
    private interface Cloner {
        Node clone(CloneGraph_133 solver, Node node);
    }
}
