package solutions.bfs;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import library.tree.binarytree.Node;
import org.junit.jupiter.api.Test;

/** Tests level links with an independent child-pointer BFS oracle. */
public class Connect_117Test {
    private final Connect_117 solution = new Connect_117();

    @Test
    void nullRootIsReturnedUnchanged() {
        assertNull(solution.connect(null));
    }

    @Test
    void singletonHasNullNext() {
        verify(levels(1));
    }

    @Test
    void completeThreeLevelTree() {
        verify(levels(1, 2, 3, 4, 5, 6, 7));
    }

    @Test
    void originalSparseExample() {
        verify(levels(1, 2, 3, 4, null, null, 5));
    }

    @Test
    void crossParentGapOnBothSides() {
        verify(levels(1, 2, 3, null, 4, null, 5));
    }

    @Test
    void onlyLeftChildren() {
        verify(levels(1, 2, null, 3, null, null, null, 4));
    }

    @Test
    void onlyRightChildren() {
        verify(levels(1, null, 2, null, null, null, 3, null, null, null,
                null, null, null, 4));
    }

    @Test
    void alternatingZigzagChain() {
        Node root = node(1);
        root.left = node(2);
        root.left.right = node(3);
        root.left.right.left = node(4);
        root.left.right.left.right = node(5);
        root.left.right.left.right.left = node(6);
        verify(root);
    }

    @Test
    void severalNodesAfterADeepGap() {
        Node root = node(9);
        root.left = node(8);
        root.right = node(7);
        root.left.right = node(6);
        root.right.right = node(5);
        root.left.right.left = node(4);
        root.right.right.right = node(3);
        verify(root);
    }

    @Test
    void originalSixNodeGapTree() {
        verify(levels(1, 2, 3, 4, null, 5, 6));
    }

    @Test
    void originalNineEightSevenRootScenario() {
        verify(levels(9, 8, 7));
    }

    @Test
    void duplicateValuesMustStillLinkByIdentity() {
        verify(levels(1, 1, 1, 1, null, 1, 1, null, 1, null, null, null, 1));
    }

    @Test
    void negativeAndBoundaryValuesArePreserved() {
        verify(levels(0, -100, 100, -1, 0, 1, 2));
    }

    @Test
    void everyContractValueEndpointAndDuplicateStillUsesIdentity() {
        Node root = node(-100);
        root.left = node(100);
        root.right = node(-100);
        root.left.left = node(100);
        root.left.right = node(-100);
        root.right.left = node(100);
        root.right.right = node(-100);
        root.left.right.left = node(100);
        root.right.left.right = node(-100);
        verify(root);
        assertSame(root.right, root.left.next);
        assertSame(root.left.right, root.left.left.next);
    }

    @Test
    void levelTailIsNullAtEveryDepth() {
        verify(levels(10, 20, 30, 40, null, null, 50, null, 60));
    }

    @Test
    void fourLevelsWithAlternatingParentGaps() {
        verify(levels(1, 2, 3, 4, null, 5, null, null, 6, null, null, 7));
    }

    @Test
    void broadSparseTree() {
        verify(levels(1, 2, 3, 4, 5, null, 6, null, 7, null, 8,
                null, null, 9, 10, null, null, null, null, null, null, null, null, null, null));
    }

    @Test
    void deepLeftSubtreeAndShallowRightSubtree() {
        Node root = node(100);
        root.left = node(1);
        root.right = node(2);
        root.left.left = node(3);
        root.left.left.left = node(4);
        root.left.left.left.right = node(5);
        root.left.left.left.right.left = node(6);
        root.right.right = node(7);
        verify(root);
    }

    @Test
    void deepRightSubtreeAndShallowLeftSubtree() {
        Node root = node(100);
        root.left = node(1);
        root.right = node(2);
        root.right.right = node(3);
        root.right.right.left = node(4);
        root.right.right.left.left = node(5);
        root.left.left = node(6);
        verify(root);
    }

    @Test
    void sameDepthNodesFromFourDifferentParents() {
        verify(levels(1, 2, 3, 4, 5, 6, 7, 8, null, null, 9,
                null, 10, null, 11, 12));
    }

    @Test
    void allNodesAtOneSparseDeepLevel() {
        verify(levels(1, 2, 3, 4, 5, 6, 7, null, null, null, 8,
                null, null, null, 9, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, 10));
    }

    @Test
    void twentyNodeIrregularTree() {
        Node root = node(1);
        root.left = node(2);
        root.right = node(3);
        root.left.right = node(4);
        root.right.left = node(5);
        root.left.right.left = node(6);
        root.right.left.right = node(7);
        root.left.right.left.right = node(8);
        root.right.left.right.left = node(9);
        root.left.right.left.right.left = node(10);
        root.right.left.right.left.right = node(11);
        Node tail = root.right.left.right.left.right;
        tail.left = node(12);
        tail = tail.left;
        tail.right = node(13);
        tail = tail.right;
        tail.left = node(14);
        tail = tail.left;
        tail.right = node(15);
        tail = tail.right;
        tail.left = node(16);
        tail = tail.left;
        tail.right = node(17);
        tail = tail.right;
        tail.left = node(18);
        tail = tail.left;
        tail.right = node(19);
        tail = tail.right;
        tail.left = node(20);
        assertEquals(20, snapshotChildren(root).size());
        verify(root);
    }

    @Test
    void longLeftChainHasNoCrossLevelLinks() {
        Node root = node(0), current = root;
        for (int value = 1; value <= 150; value++) {
            current.left = node(value % 201 - 100);
            current = current.left;
        }
        verify(root);
    }

    @Test
    void longRightChainHasNoCrossLevelLinks() {
        Node root = node(0), current = root;
        for (int value = 1; value <= 150; value++) {
            current.right = node(-(value % 101));
            current = current.right;
        }
        verify(root);
    }

    @Test
    void originalAlternating150NodeChainHasNoCrossLevelLinks() {
        Node root = node(1);
        Node current = root;
        for (int value = 2; value <= 150; value++) {
            Node next = node((value % 201) - 100);
            if (value % 2 == 0) {
                current.left = next;
            } else {
                current.right = next;
            }
            current = next;
        }
        assertEquals(150, snapshotChildren(root).size());
        verify(root);
    }

    @Test
    void largeCompleteTreeWithinThe6000NodeContract() {
        Node root = node(0);
        List<Node> frontier = List.of(root);
        int value = 1;
        int nodeCount = 1;
        for (int depth = 0; depth < 12; depth++) {
            List<Node> next = new ArrayList<>();
            for (Node parent : frontier) {
                if (nodeCount < 6000) {
                    parent.left = node(value++ % 201 - 100);
                    nodeCount++;
                    next.add(parent.left);
                }
                if (nodeCount < 6000) {
                    parent.right = node(value++ % 201 - 100);
                    nodeCount++;
                    next.add(parent.right);
                }
            }
            frontier = next;
        }
        assertEquals(6000, snapshotChildren(root).size());
        verify(root);
    }

    @Test
    void seededSparseTreeExercisesAdversarialCrossParentChains() {
        Node root = seededSparseTree(1024);
        assertEquals(1024, snapshotChildren(root).size());
        verify(root);
    }

    @Test
    void alternatingChainAtMaximumNodeCountHasOnlyNullSuccessors() {
        Node root = node(-100);
        Node current = root;
        for (int value = 1; value < 6000; value++) {
            Node next = node(value % 201 - 100);
            if ((value & 1) == 0) {
                current.left = next;
            } else {
                current.right = next;
            }
            current = next;
        }
        assertEquals(6000, snapshotChildren(root).size());
        verify(root);
    }

    @Test
    void repeatedCallRetainsCorrectLinksAndChildren() {
        Node root = levels(1, 2, 3, null, 4, 5);
        verify(root);
        Map<Node, Node[]> children = snapshotChildren(root);
        assertSame(root, solution.connect(root));
        assertChildrenUnchanged(children);
        assertLinksMatchOracle(root);
    }

    @Test
    void returnValueIsTheOriginalRootForIrregularTree() {
        Node root = levels(8, 3, 10, 1, null, 9, 14);
        assertSame(root, solution.connect(root));
        assertLinksMatchOracle(root);
    }

    private void verify(Node root) {
        Map<Node, Node[]> children = snapshotChildren(root);
        assertAllNextNull(children.keySet());
        assertSame(root, solution.connect(root));
        assertChildrenUnchanged(children);
        assertLinksMatchOracle(root);
    }

    private void assertLinksMatchOracle(Node root) {
        if (root == null) {
            return;
        }
        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int width = queue.size();
            Node previous = null;
            Set<Node> seen = java.util.Collections.newSetFromMap(new IdentityHashMap<>());
            for (int i = 0; i < width; i++) {
                Node current = queue.remove();
                assertTrue(seen.add(current), "next links must not create a cycle");
                if (previous != null) {
                    assertSame(current, previous.next);
                }
                previous = current;
                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
            }
            assertNull(previous.next, "the rightmost node of each level must terminate with null");
        }
    }

    private static Map<Node, Node[]> snapshotChildren(Node root) {
        Map<Node, Node[]> snapshot = new IdentityHashMap<>();
        if (root == null) {
            return snapshot;
        }
        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node current = queue.remove();
            if (snapshot.put(current, new Node[]{current.left, current.right}) != null) {
                continue;
            }
            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }
        return snapshot;
    }

    private static void assertChildrenUnchanged(Map<Node, Node[]> snapshot) {
        for (Map.Entry<Node, Node[]> entry : snapshot.entrySet()) {
            assertSame(entry.getValue()[0], entry.getKey().left);
            assertSame(entry.getValue()[1], entry.getKey().right);
        }
    }

    private static void assertAllNextNull(Set<Node> nodes) {
        for (Node node : nodes) {
            assertNull(node.next);
        }
    }

    private static Node node(int value) {
        Node result = new Node();
        result.val = value;
        return result;
    }

    /** Builds a deterministic irregular tree without using next pointers. */
    private static Node seededSparseTree(int nodeCount) {
        Node root = node(-100);
        List<Node> nodes = new ArrayList<>();
        nodes.add(root);
        long state = 0x5DEECE66DL;
        for (int value = 1; value < nodeCount; value++) {
            state = state * 6364136223846793005L + 1442695040888963407L;
            int parentIndex = (int) Math.floorMod(state, nodes.size());
            Node parent = nodes.get(parentIndex);
            boolean rightFirst = (state & 2L) != 0;
            Node child = node((int) Math.floorMod(state, 201) - 100);
            if (rightFirst && parent.right == null) {
                parent.right = child;
            } else if (parent.left == null) {
                parent.left = child;
            } else if (parent.right == null) {
                parent.right = child;
            } else {
                int offset = 1;
                while (true) {
                    Node candidate = nodes.get((parentIndex + offset) % nodes.size());
                    if (candidate.left == null || candidate.right == null) {
                        parent = candidate;
                        if (candidate.left == null) {
                            candidate.left = child;
                        } else {
                            candidate.right = child;
                        }
                        break;
                    }
                    offset++;
                }
            }
            nodes.add(child);
        }
        return root;
    }

    /** Builds a tree from heap positions; null entries denote absent nodes. */
    private static Node levels(Integer... values) {
        if (values.length == 0 || values[0] == null) return null;
        Node[] nodes = new Node[values.length];
        for (int i = 0; i < values.length; i++) {
            if (values[i] == null) continue;
            if (i > 0 && values[(i - 1) / 2] == null) {
                throw new IllegalArgumentException("a non-null heap entry must have a non-null parent");
            }
            nodes[i] = node(values[i]);
        }
        for (int i = 0; i < values.length; i++) {
            if (nodes[i] == null) {
                continue;
            }
            int left = i * 2 + 1;
            int right = left + 1;
            if (left < values.length) {
                nodes[i].left = nodes[left];
            }
            if (right < values.length) {
                nodes[i].right = nodes[right];
            }
        }
        return nodes[0];
    }
}
