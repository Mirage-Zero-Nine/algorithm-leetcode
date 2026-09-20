package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import library.randomnode.Node;
import library.randomnode.NodeCopy;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * Tests for the deep-copy contract of LeetCode 1485. The structural oracle
 * traverses only child links to enumerate the input tree, then checks every
 * child and random edge by object identity, so duplicate values cannot hide
 * aliasing or topology errors.
 */
public class CopyRandomBinaryTree_1485Test {

    private final CopyRandomBinaryTree_1485 solution = new CopyRandomBinaryTree_1485();

    @Test
    void nullRootProducesNull() {
        assertNull(solution.copyRandomBinaryTree(null));
    }

    @Test
    void singletonWithNullRandomIsCopied() {
        Node root = new Node(42);
        assertDeepCopy(root, solution.copyRandomBinaryTree(root));
    }

    @Test
    void singletonRandomSelfLoopIsCopiedToTheCopy() {
        Node root = new Node(7);
        root.random = root;
        NodeCopy copy = solution.copyRandomBinaryTree(root);
        assertDeepCopy(root, copy);
        assertSame(copy, copy.random);
        assertNotSame(root, copy);
    }

    @Test
    void officialExampleOne() {
        Node root = new Node(1);
        Node four = new Node(4);
        Node seven = new Node(7);
        root.right = four;
        four.left = seven;
        four.random = seven;
        seven.random = root;
        assertDeepCopy(root, solution.copyRandomBinaryTree(root));
    }

    @Test
    void officialExampleTwoWithDuplicateValuesAndSparseChildren() {
        Node root = new Node(1);
        Node second = new Node(1);
        Node third = new Node(1);
        Node fourth = new Node(1);
        root.right = second;
        second.left = third;
        second.right = fourth;
        root.random = third;
        second.random = root;
        third.random = fourth;
        fourth.random = fourth;
        assertDeepCopy(root, solution.copyRandomBinaryTree(root));
    }

    @Test
    void officialExampleThreeHasReverseRandomPermutation() {
        Node[] nodes = new Node[7];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new Node(i + 1);
        }
        nodes[0].left = nodes[1];
        nodes[0].right = nodes[2];
        nodes[1].left = nodes[3];
        nodes[1].right = nodes[4];
        nodes[2].left = nodes[5];
        nodes[2].right = nodes[6];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i].random = nodes[nodes.length - 1 - i];
        }
        assertDeepCopy(nodes[0], solution.copyRandomBinaryTree(nodes[0]));
    }

    @Test
    void completeTreeWithAllRandomPointersNull() {
        Node[] nodes = completeTree(15);
        assertDeepCopy(nodes[0], solution.copyRandomBinaryTree(nodes[0]));
    }

    @Test
    void leftSkewedTreeRandomPointersReachAncestorsAndDescendants() {
        Node[] nodes = leftChain(9, -10);
        nodes[0].random = nodes[8];
        nodes[3].random = nodes[1];
        nodes[8].random = nodes[0];
        assertDeepCopy(nodes[0], solution.copyRandomBinaryTree(nodes[0]));
    }

    @Test
    void rightSkewedTreeRandomPointersCrossBranches() {
        Node root = new Node(1);
        root.right = new Node(2);
        root.right.right = new Node(3);
        root.right.right.right = new Node(4);
        root.random = root.right.right;
        root.right.random = root.right.right.right;
        root.right.right.random = root;
        assertDeepCopy(root, solution.copyRandomBinaryTree(root));
    }

    @Test
    void sparseTreePreservesOnlyLeftOrOnlyRightChildAtEachLevel() {
        Node root = new Node(10);
        root.left = new Node(20);
        root.left.right = new Node(30);
        root.left.right.left = new Node(40);
        root.left.right.left.right = new Node(50);
        root.random = root.left.right.left;
        root.left.random = root.left.right.left.right;
        assertDeepCopy(root, solution.copyRandomBinaryTree(root));
    }

    @Test
    void randomPointersFormAThreeNodeCycle() {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.random = root.left;
        root.left.random = root.right;
        root.right.random = root;
        NodeCopy copy = solution.copyRandomBinaryTree(root);
        assertDeepCopy(root, copy);
        assertSame(copy.left, copy.random);
        assertSame(copy.right, copy.left.random);
        assertSame(copy, copy.right.random);
    }

    @Test
    void randomPointersAllAliasOneDescendant() {
        Node[] nodes = completeTree(7);
        for (Node node : nodes) {
            node.random = nodes[6];
        }
        assertDeepCopy(nodes[0], solution.copyRandomBinaryTree(nodes[0]));
    }

    @Test
    void duplicateValuesDoNotCollapseDistinctNodes() {
        Node root = new Node(5);
        root.left = new Node(5);
        root.right = new Node(5);
        root.left.left = new Node(5);
        root.left.right = new Node(5);
        root.random = root.right;
        root.left.random = root.left.right;
        root.right.random = root.left.left;
        assertDeepCopy(root, solution.copyRandomBinaryTree(root));
    }

    @Test
    void signedAndIntegerBoundaryValuesAreCopiedExactly() {
        Node root = new Node(Integer.MIN_VALUE);
        root.left = new Node(-1);
        root.right = new Node(0);
        root.left.right = new Node(Integer.MAX_VALUE);
        root.random = root.left.right;
        root.left.random = root;
        assertDeepCopy(root, solution.copyRandomBinaryTree(root));
    }

    @Test
    void nullAndSelfRandomEdgesCanBeMixed() {
        Node[] nodes = completeTree(7);
        nodes[0].random = null;
        nodes[1].random = nodes[1];
        nodes[2].random = null;
        nodes[3].random = nodes[0];
        nodes[4].random = nodes[6];
        nodes[5].random = null;
        nodes[6].random = nodes[5];
        assertDeepCopy(nodes[0], solution.copyRandomBinaryTree(nodes[0]));
    }

    @Test
    void randomEdgesCanJumpBetweenLeftAndRightSubtrees() {
        Node[] nodes = completeTree(7);
        nodes[1].random = nodes[6];
        nodes[2].random = nodes[3];
        nodes[3].random = nodes[5];
        nodes[4].random = nodes[2];
        assertDeepCopy(nodes[0], solution.copyRandomBinaryTree(nodes[0]));
    }

    @Test
    void generatedTreesAreCheckedAgainstIndependentIdentityOracle() {
        Random random = new Random(1485L);
        for (int trial = 0; trial < 40; trial++) {
            int size = 1 + random.nextInt(40);
            Node[] nodes = new Node[size];
            for (int i = 0; i < size; i++) {
                nodes[i] = new Node(random.nextInt(11) - 5);
                if (i > 0) {
                    int parent = random.nextInt(i);
                    while (nodes[parent].left != null && nodes[parent].right != null) {
                        parent = (parent + 1) % i;
                    }
                    if (nodes[parent].left == null && (nodes[parent].right != null || random.nextBoolean())) {
                        nodes[parent].left = nodes[i];
                    } else {
                        nodes[parent].right = nodes[i];
                    }
                }
            }
            for (Node node : nodes) {
                node.random = random.nextInt(4) == 0 ? null : nodes[random.nextInt(size)];
            }
            assertDeepCopy(nodes[0], solution.copyRandomBinaryTree(nodes[0]));
        }
    }

    @Test
    void exactMaximumNodeCountWithRandomCycles() {
        Node[] nodes = completeTree(1000);
        for (int i = 0; i < nodes.length; i++) {
            nodes[i].val = (i % 17) - 8;
            nodes[i].random = nodes[(i * 37 + 11) % nodes.length];
        }
        NodeCopy copy = solution.copyRandomBinaryTree(nodes[0]);
        assertDeepCopy(nodes[0], copy);
        assertEquals(1000, collectOriginal(nodes[0]).size());
    }

    @Test
    void exactMaximumNodeCountLeftChainIsCopied() {
        Node[] nodes = leftChain(1000, 1);
        for (int i = 0; i < nodes.length; i++) {
            nodes[i].random = i % 3 == 0 ? null : nodes[(i + 997) % nodes.length];
        }
        assertDeepCopy(nodes[0], solution.copyRandomBinaryTree(nodes[0]));
    }

    @Test
    void originalTreeTopologyAndValuesAreNotMutated() {
        Node[] nodes = completeTree(7);
        nodes[0].val = 99;
        nodes[0].random = nodes[4];
        nodes[2].left = new Node(100);
        nodes[5].random = nodes[0];
        IdentitySnapshot before = snapshot(nodes[0]);
        solution.copyRandomBinaryTree(nodes[0]);
        before.assertUnchanged(nodes[0]);
    }

    @Test
    void changingOriginalAfterCopyDoesNotChangeCopy() {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.random = root.right;
        root.left.random = root;
        NodeCopy copy = solution.copyRandomBinaryTree(root);
        root.val = 100;
        root.left.val = 200;
        root.left = null;
        root.random = null;
        assertEquals(1, copy.val);
        assertEquals(2, copy.left.val);
        assertEquals(3, copy.right.val);
        assertSame(copy.right, copy.random);
        assertSame(copy, copy.left.random);
    }

    @Test
    void changingCopyDoesNotChangeOriginal() {
        Node root = new Node(8);
        root.left = new Node(9);
        root.random = root.left;
        NodeCopy copy = solution.copyRandomBinaryTree(root);
        copy.val = 80;
        copy.left = null;
        copy.random = null;
        assertEquals(8, root.val);
        assertSame(root.left, root.random);
        assertEquals(9, root.left.val);
    }

    @Test
    void repeatedCallsReturnIndependentCopies() {
        Node root = new Node(1);
        root.left = new Node(2);
        root.random = root.left;
        NodeCopy first = solution.copyRandomBinaryTree(root);
        NodeCopy second = solution.copyRandomBinaryTree(root);
        assertDeepCopy(root, first);
        assertDeepCopy(root, second);
        assertNotSame(first, second);
        assertNotSame(first.left, second.left);
        first.left.val = 200;
        assertEquals(2, second.left.val);
        assertEquals(2, root.left.val);
    }

    @Test
    void differentSolutionInstancesDoNotShareState() {
        Node firstRoot = new Node(1);
        firstRoot.random = firstRoot;
        Node secondRoot = new Node(2);
        NodeCopy firstCopy = new CopyRandomBinaryTree_1485().copyRandomBinaryTree(firstRoot);
        NodeCopy secondCopy = new CopyRandomBinaryTree_1485().copyRandomBinaryTree(secondRoot);
        assertSame(firstCopy, firstCopy.random);
        assertNull(secondCopy.random);
        assertEquals(2, secondCopy.val);
    }

    @Test
    void everyCopiedNodeIsFreshIncludingAllRandomTargets() {
        Node[] nodes = completeTree(31);
        for (int i = 0; i < nodes.length; i++) {
            nodes[i].random = nodes[(i * 13 + 3) % nodes.length];
        }
        NodeCopy copy = solution.copyRandomBinaryTree(nodes[0]);
        List<Node> originals = collectOriginal(nodes[0]);
        List<NodeCopy> copies = collectCopies(copy);
        assertEquals(originals.size(), copies.size());
        for (Node original : originals) {
            for (NodeCopy candidate : copies) {
                assertNotSame(original, candidate);
            }
        }
    }

    @Test
    void randomTargetMappingUsesIdentityNotEqualValues() {
        Node root = new Node(4);
        root.left = new Node(4);
        root.right = new Node(4);
        root.left.random = root.right;
        root.right.random = root.left;
        NodeCopy copy = solution.copyRandomBinaryTree(root);
        assertSame(copy.right, copy.left.random);
        assertSame(copy.left, copy.right.random);
        assertNotSame(copy.left, copy.right);
    }

    @Test
    void maximumValueAndMinimalPositiveValueArePreserved() {
        Node root = new Node(1_000_000);
        root.left = new Node(1);
        root.right = new Node(999_999);
        root.random = root.left;
        root.left.random = root.right;
        assertDeepCopy(root, solution.copyRandomBinaryTree(root));
    }

    private static Node[] completeTree(int size) {
        Node[] nodes = new Node[size];
        for (int i = 0; i < size; i++) {
            nodes[i] = new Node(i + 1);
        }
        for (int i = 0; i < size; i++) {
            int left = 2 * i + 1;
            int right = left + 1;
            if (left < size) {
                nodes[i].left = nodes[left];
            }
            if (right < size) {
                nodes[i].right = nodes[right];
            }
        }
        return nodes;
    }

    private static Node[] leftChain(int size, int firstValue) {
        Node[] nodes = new Node[size];
        for (int i = 0; i < size; i++) {
            nodes[i] = new Node(firstValue + i);
            if (i > 0) {
                nodes[i - 1].left = nodes[i];
            }
        }
        return nodes;
    }

    private static void assertDeepCopy(Node root, NodeCopy copy) {
        if (root == null) {
            assertNull(copy);
            return;
        }
        assertNotSame(root, copy);
        List<Node> originals = collectOriginal(root);
        List<NodeCopy> copies = collectCopies(copy);
        assertEquals(originals.size(), copies.size(), "copy must contain exactly the tree nodes");

        Map<Node, NodeCopy> mapping = new IdentityHashMap<>();
        Map<NodeCopy, Node> reverse = new IdentityHashMap<>();
        ArrayDeque<Node> originalQueue = new ArrayDeque<>();
        ArrayDeque<NodeCopy> copyQueue = new ArrayDeque<>();
        originalQueue.add(root);
        copyQueue.add(copy);
        while (!originalQueue.isEmpty()) {
            Node original = originalQueue.remove();
            NodeCopy cloned = copyQueue.remove();
            assertTrue(mapping.put(original, cloned) == null, "tree node visited twice");
            assertTrue(reverse.put(cloned, original) == null, "copy node reused for two originals");
            assertNotSame(original, cloned);
            assertEquals(original.val, cloned.val);
            assertChildShapeAndQueue(original.left, cloned.left, originalQueue, copyQueue);
            assertChildShapeAndQueue(original.right, cloned.right, originalQueue, copyQueue);
        }
        for (Node original : originals) {
            NodeCopy cloned = mapping.get(original);
            assertSame(mapping.get(original.left), cloned.left);
            assertSame(mapping.get(original.right), cloned.right);
            if (original.random == null) {
                assertNull(cloned.random);
            } else {
                assertSame(mapping.get(original.random), cloned.random,
                        "random edge must target the corresponding copied identity");
            }
        }
        for (NodeCopy cloned : copies) {
            assertTrue(reverse.containsKey(cloned));
            assertFalse(originals.stream().anyMatch(original -> (Object) original == cloned),
                    "no pointer in the copy may be an original node");
        }
    }

    private static void assertChildShapeAndQueue(Node original, NodeCopy cloned,
                                                  ArrayDeque<Node> originalQueue,
                                                  ArrayDeque<NodeCopy> copyQueue) {
        if (original == null) {
            assertNull(cloned);
        } else {
            assertNotSame(original, cloned);
            originalQueue.add(original);
            copyQueue.add(cloned);
        }
    }

    private static List<Node> collectOriginal(Node root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<Node> result = new ArrayList<>();
        Set<Node> visited = Collections.newSetFromMap(new IdentityHashMap<>());
        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            if (!visited.add(node)) {
                continue;
            }
            result.add(node);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return result;
    }

    private static List<NodeCopy> collectCopies(NodeCopy root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<NodeCopy> result = new ArrayList<>();
        Set<NodeCopy> visited = Collections.newSetFromMap(new IdentityHashMap<>());
        ArrayDeque<NodeCopy> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            NodeCopy node = queue.remove();
            if (!visited.add(node)) {
                continue;
            }
            result.add(node);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return result;
    }

    private static IdentitySnapshot snapshot(Node root) {
        List<Node> nodes = collectOriginal(root);
        Map<Node, NodeState> states = new IdentityHashMap<>();
        for (Node node : nodes) {
            states.put(node, new NodeState(node.val, node.left, node.right, node.random));
        }
        return new IdentitySnapshot(states);
    }

    private record NodeState(int val, Node left, Node right, Node random) {
    }

    private record IdentitySnapshot(Map<Node, NodeState> states) {
        void assertUnchanged(Node root) {
            List<Node> currentNodes = collectOriginal(root);
            assertEquals(states.size(), currentNodes.size());
            for (Node node : currentNodes) {
                NodeState state = states.get(node);
                assertTrue(state != null);
                assertEquals(state.val(), node.val);
                assertSame(state.left(), node.left);
                assertSame(state.right(), node.right);
                assertSame(state.random(), node.random);
            }
        }
    }
}
