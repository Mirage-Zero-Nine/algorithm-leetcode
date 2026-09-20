package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import library.tree.narytree.Node;
import org.junit.jupiter.api.Test;

/** Tests for the recursive deep-copy implementation of LeetCode 1490. */
public class CloneTree_1490Test {

    private final CloneTree_1490 solution = new CloneTree_1490();

    @Test
    public void nullRootProducesNull() {
        assertNull(solution.cloneTree(null));
    }

    @Test
    public void singletonWithNullChildrenListGetsIndependentEmptyList() {
        Node root = new Node(Integer.MIN_VALUE);

        Node clone = solution.cloneTree(root);

        assertEquivalentTree(root, clone);
        assertNotNull(clone.children);
        assertTrue(clone.children.isEmpty());
        assertNull(root.children);
    }

    @Test
    public void singletonWithExplicitEmptyChildrenListIsPreserved() {
        Node root = new Node(7, Collections.emptyList());

        Node clone = solution.cloneTree(root);

        assertEquivalentTree(root, clone);
        assertNotSame(root.children, clone.children);
        assertTrue(clone.children.isEmpty());
    }

    @Test
    public void officialExampleOnePreservesValuesOrderAndShape() {
        Node root = new Node(1, Arrays.asList(
                new Node(3, Arrays.asList(new Node(5), new Node(6))),
                new Node(2),
                new Node(4)));

        Node clone = solution.cloneTree(root);

        assertEquivalentTree(root, clone);
        assertEquals(Arrays.asList(3, 2, 4), values(clone.children));
        assertEquals(Arrays.asList(5, 6), values(clone.children.get(0).children));
    }

    @Test
    public void officialExampleTwoPreservesVariableArityShape() {
        Node root = new Node(1, Arrays.asList(
                new Node(2, Collections.emptyList()),
                new Node(3, Arrays.asList(new Node(6), new Node(7))),
                new Node(4, Arrays.asList(new Node(8, Arrays.asList(new Node(11))))),
                new Node(5, Arrays.asList(
                        new Node(9, Arrays.asList(new Node(12))),
                        new Node(10, Arrays.asList(new Node(13, Arrays.asList(new Node(14)))))))));

        Node clone = solution.cloneTree(root);

        assertEquivalentTree(root, clone);
        assertEquals(Arrays.asList(2, 3, 4, 5), values(clone.children));
        assertEquals(Arrays.asList(9, 10), values(clone.children.get(3).children));
    }

    @Test
    public void orderedChildrenRemainInInputOrder() {
        Node root = new Node(0, Arrays.asList(
                new Node(10), new Node(-10), new Node(3), new Node(3), new Node(1)));

        Node clone = solution.cloneTree(root);

        assertEquals(Arrays.asList(10, -10, 3, 3, 1), values(clone.children));
        assertEquivalentTree(root, clone);
    }

    @Test
    public void zeroOneAndManyChildrenAreAllHandled() {
        Node root = new Node(1, Arrays.asList(
                new Node(2),
                new Node(3, Arrays.asList(new Node(4))),
                new Node(5, Arrays.asList(new Node(6), new Node(7), new Node(8))),
                new Node(9, Arrays.asList(new Node(10), new Node(11), new Node(12), new Node(13)))));

        Node clone = solution.cloneTree(root);

        assertEquivalentTree(root, clone);
        assertEquals(0, clone.children.get(0).children.size());
        assertEquals(1, clone.children.get(1).children.size());
        assertEquals(3, clone.children.get(2).children.size());
        assertEquals(4, clone.children.get(3).children.size());
    }

    @Test
    public void repeatedValuesDoNotCauseNodesToBeShared() {
        Node root = new Node(5, Arrays.asList(
                new Node(5, Arrays.asList(new Node(5))),
                new Node(5, Arrays.asList(new Node(5), new Node(5))),
                new Node(5)));

        Node clone = solution.cloneTree(root);

        assertEquivalentTree(root, clone);
        assertNotSame(clone.children.get(0), clone.children.get(1));
        assertNotSame(clone.children.get(1).children.get(0), clone.children.get(1).children.get(1));
    }

    @Test
    public void signedAndIntegerBoundaryValuesAreCopiedExactly() {
        Node root = new Node(Integer.MAX_VALUE, Arrays.asList(
                new Node(Integer.MIN_VALUE),
                new Node(-1, Arrays.asList(new Node(0))),
                new Node(1)));

        Node clone = solution.cloneTree(root);

        assertEquivalentTree(root, clone);
        assertEquals(Integer.MIN_VALUE, clone.children.get(0).val);
        assertEquals(0, clone.children.get(1).children.get(0).val);
    }

    @Test
    public void nullChildEntriesAreCopiedAsNullForImplementationSupportedInput() {
        Node child = new Node(2, Collections.emptyList());
        Node root = new Node(1, Arrays.asList(null, child, null));

        Node clone = solution.cloneTree(root);

        assertEquivalentTree(root, clone);
        assertNull(clone.children.get(0));
        assertNotSame(child, clone.children.get(1));
        assertNull(clone.children.get(2));
    }

    @Test
    public void nullAndEmptyListsAtDifferentLevelsAreHandled() {
        Node root = new Node(1, Arrays.asList(
                new Node(2),
                new Node(3, Collections.emptyList()),
                new Node(4, Arrays.asList(new Node(5), new Node(6)))));

        Node clone = solution.cloneTree(root);

        assertEquivalentTree(root, clone);
        assertNull(root.children.get(0).children);
        assertTrue(root.children.get(1).children.isEmpty());
        assertTrue(clone.children.get(0).children.isEmpty());
        assertTrue(clone.children.get(1).children.isEmpty());
    }

    @Test
    public void deepChainAtDocumentedDepthIsCloned() {
        final int depth = 1000;
        Node root = new Node(0);
        Node current = root;
        for (int value = 1; value < depth; value++) {
            Node child = new Node(value % 17 - 8);
            current.children = Collections.singletonList(child);
            current = child;
        }

        Node clone = solution.cloneTree(root);

        assertEquivalentTree(root, clone);
        assertEquals(depth, countNodes(clone));
        assertEquals(root.val, clone.val);
    }

    @Test
    public void wideTreeWithTenThousandNodesIsCloned() {
        final int nodeCount = 10_000;
        Node root = new Node(123);
        List<Node> children = new ArrayList<>(nodeCount - 1);
        for (int i = 1; i < nodeCount; i++) {
            children.add(new Node(i % 19 - 9));
        }
        root.children = children;

        Node clone = solution.cloneTree(root);

        assertEquivalentTree(root, clone);
        assertEquals(nodeCount, countNodes(clone));
        assertEquals(-8, clone.children.get(0).val);
        assertEquals((nodeCount - 1) % 19 - 9, clone.children.get(nodeCount - 2).val);
    }

    @Test
    public void mixedBranchingTreeWithTenThousandNodesIsCloned() {
        Node root = new Node(0, new ArrayList<>());
        List<Node> frontier = new ArrayList<>();
        frontier.add(root);
        int created = 1;
        int nextValue = 1;
        while (created < 10_000) {
            List<Node> next = new ArrayList<>();
            for (Node parent : frontier) {
                int arity = Math.min(4, 10_000 - created);
                List<Node> children = new ArrayList<>(arity);
                for (int i = 0; i < arity; i++) {
                    Node child = new Node((nextValue++ % 31) - 15);
                    children.add(child);
                    next.add(child);
                    created++;
                }
                parent.children = children;
                if (created == 10_000) {
                    break;
                }
            }
            frontier = next;
        }

        Node clone = solution.cloneTree(root);

        assertEquivalentTree(root, clone);
        assertEquals(10_000, countNodes(clone));
    }

    @Test
    public void immutableInputChildrenDoNotConstrainCloneMutation() {
        Node root = new Node(1, Collections.unmodifiableList(Arrays.asList(
                new Node(2, Collections.unmodifiableList(Collections.singletonList(new Node(4)))),
                new Node(3))));

        Node clone = solution.cloneTree(root);
        clone.children.add(new Node(5));
        clone.children.get(0).children.clear();

        assertEquals(2, root.children.size());
        assertEquals(1, root.children.get(0).children.size());
        assertEquals(3, clone.children.size());
        assertTrue(clone.children.get(0).children.isEmpty());
    }

    @Test
    public void changingRootCloneDoesNotChangeOriginal() {
        Node root = new Node(1, Arrays.asList(new Node(2), new Node(3)));
        String before = signature(root);

        Node clone = solution.cloneTree(root);
        clone.val = 99;
        clone.children.remove(0);

        assertEquals(before, signature(root));
        assertEquals(1, root.val);
        assertEquals(2, root.children.size());
    }

    @Test
    public void changingNestedCloneDoesNotChangeOriginal() {
        Node root = new Node(1, Arrays.asList(
                new Node(2, Arrays.asList(new Node(4))), new Node(3)));
        String before = signature(root);

        Node clone = solution.cloneTree(root);
        clone.children.get(0).val = -20;
        clone.children.get(0).children.get(0).val = -40;
        clone.children.get(0).children.add(new Node(5));

        assertEquals(before, signature(root));
        assertEquals(2, root.children.get(0).val);
        assertEquals(4, root.children.get(0).children.get(0).val);
        assertEquals(1, root.children.get(0).children.size());
    }

    @Test
    public void changingOriginalAfterCloneDoesNotChangeClone() {
        Node originalChild = new Node(2, new ArrayList<>(Collections.singletonList(new Node(4))));
        Node root = new Node(1, new ArrayList<>(Collections.singletonList(originalChild)));

        Node clone = solution.cloneTree(root);
        root.val = 100;
        root.children.clear();
        originalChild.val = 200;
        originalChild.children.clear();

        assertEquals(1, clone.val);
        assertEquals(1, clone.children.size());
        assertEquals(2, clone.children.get(0).val);
        assertEquals(1, clone.children.get(0).children.size());
        assertEquals(4, clone.children.get(0).children.get(0).val);
    }

    @Test
    public void everyNodeAndChildrenListIsFresh() {
        Node root = new Node(1, Arrays.asList(
                new Node(2, Arrays.asList(new Node(4), new Node(5))),
                new Node(3, Arrays.asList(new Node(6)))));

        Node clone = solution.cloneTree(root);

        assertEquivalentTree(root, clone);
        assertNotSame(root.children, clone.children);
        for (int i = 0; i < root.children.size(); i++) {
            assertNotSame(root.children.get(i), clone.children.get(i));
            assertNotSame(root.children.get(i).children, clone.children.get(i).children);
            for (int j = 0; j < root.children.get(i).children.size(); j++) {
                assertNotSame(root.children.get(i).children.get(j), clone.children.get(i).children.get(j));
            }
        }
    }

    @Test
    public void repeatedCallsReturnIndependentClones() {
        Node root = new Node(1, Arrays.asList(new Node(2, Arrays.asList(new Node(3)))));

        Node first = solution.cloneTree(root);
        Node second = solution.cloneTree(root);
        first.children.get(0).children.get(0).val = 30;
        first.children.add(new Node(4));

        assertEquivalentTree(root, second);
        assertEquals(3, second.children.get(0).children.get(0).val);
        assertEquals(1, second.children.size());
        assertNotSame(first, second);
    }

    @Test
    public void resultListsAreIndependentAcrossSeparateRoots() {
        Node firstRoot = new Node(1, new ArrayList<>(Collections.singletonList(new Node(2))));
        Node secondRoot = new Node(1, new ArrayList<>(Collections.singletonList(new Node(2))));

        Node firstClone = solution.cloneTree(firstRoot);
        Node secondClone = solution.cloneTree(secondRoot);
        firstClone.children.clear();

        assertEquals(1, secondClone.children.size());
        assertNotSame(firstClone.children, secondClone.children);
    }

    @Test
    public void deterministicSmallTreesMatchIndependentOracle() {
        Random random = new Random(1490L);
        for (int caseNumber = 0; caseNumber < 200; caseNumber++) {
            Node root = randomTree(random, 1 + random.nextInt(40));
            Node clone = solution.cloneTree(root);
            assertEquivalentTree(root, clone);
        }
    }

    @Test
    public void cloneDoesNotMutateOriginalStructureOrValues() {
        Node root = new Node(8, Arrays.asList(
                new Node(1, Arrays.asList(new Node(2), new Node(3))),
                new Node(4, Arrays.asList(new Node(5), new Node(6), new Node(7)))));
        String before = signature(root);

        Node clone = solution.cloneTree(root);

        assertEquals(before, signature(root));
        assertEquivalentTree(root, clone);
    }

    @Test
    public void cloneOutputCanBeClearedWithoutAffectingOriginal() {
        Node root = new Node(1, Arrays.asList(new Node(2), new Node(3)));
        Node clone = solution.cloneTree(root);

        clone.children.clear();

        assertEquals(2, root.children.size());
        assertEquals(0, clone.children.size());
    }

    @Test
    public void cloningSameTreeAfterOriginalMutationReadsCurrentInput() {
        Node root = new Node(1, new ArrayList<>(Collections.singletonList(new Node(2))));
        Node first = solution.cloneTree(root);
        root.val = 9;
        root.children.add(new Node(3));

        Node second = solution.cloneTree(root);

        assertEquals(1, first.val);
        assertEquals(1, first.children.size());
        assertEquals(9, second.val);
        assertEquals(Arrays.asList(2, 3), values(second.children));
    }

    @Test
    public void cloneRetainsLargeDuplicateValueWorkload() {
        Node root = new Node(42);
        List<Node> children = new ArrayList<>();
        for (int i = 0; i < 500; i++) {
            children.add(new Node(42, Arrays.asList(new Node(42), new Node(42))));
        }
        root.children = children;

        Node clone = solution.cloneTree(root);

        assertEquivalentTree(root, clone);
        assertEquals(1501, countNodes(clone));
    }

    private static Node randomTree(Random random, int nodeCount) {
        Node root = new Node(random.nextInt(2_001) - 1_000);
        List<Node> frontier = new ArrayList<>();
        frontier.add(root);
        int created = 1;
        while (created < nodeCount) {
            List<Node> next = new ArrayList<>();
            for (Node parent : frontier) {
                if (created == nodeCount) {
                    break;
                }
                int arity = Math.min(nodeCount - created, random.nextInt(4));
                List<Node> children = new ArrayList<>(arity);
                for (int i = 0; i < arity; i++) {
                    Node child = new Node(random.nextInt(2_001) - 1_000);
                    children.add(child);
                    next.add(child);
                    created++;
                }
                parent.children = children;
            }
            if (next.isEmpty() && created < nodeCount) {
                Node parent = frontier.get(random.nextInt(frontier.size()));
                Node child = new Node(random.nextInt(2_001) - 1_000);
                List<Node> children = parent.children == null
                        ? new ArrayList<>() : new ArrayList<>(parent.children);
                children.add(child);
                parent.children = children;
                next.add(child);
                created++;
            }
            frontier = next;
        }
        return root;
    }

    private static List<Integer> values(List<Node> nodes) {
        List<Integer> values = new ArrayList<>();
        for (Node node : nodes) {
            values.add(node.val);
        }
        return values;
    }

    private static int countNodes(Node root) {
        if (root == null) {
            return 0;
        }
        int count = 1;
        if (root.children != null) {
            for (Node child : root.children) {
                count += countNodes(child);
            }
        }
        return count;
    }

    /**
     * Independent oracle for ordered tree equality and deep-copy identity.
     * A null source child is an implementation-supported edge case and must remain null.
     */
    private static void assertEquivalentTree(Node original, Node clone) {
        if (original == null) {
            assertNull(clone);
            return;
        }
        assertNotNull(clone);
        assertNotSame(original, clone);
        assertEquals(original.val, clone.val);
        assertNotSame(original.children, clone.children);

        int expectedChildren = original.children == null ? 0 : original.children.size();
        assertNotNull(clone.children);
        assertEquals(expectedChildren, clone.children.size());
        if (original.children == null) {
            return;
        }
        for (int i = 0; i < original.children.size(); i++) {
            assertEquivalentTree(original.children.get(i), clone.children.get(i));
        }
    }

    private static String signature(Node node) {
        if (node == null) {
            return "#";
        }
        StringBuilder result = new StringBuilder();
        result.append(node.val).append(node.children == null ? "N{" : "L{");
        if (node.children != null) {
            for (Node child : node.children) {
                result.append(signature(child));
            }
        }
        return result.append('}').toString();
    }
}
