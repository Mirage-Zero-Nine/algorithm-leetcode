package solutions.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import library.tree.narytree.Node;
import org.junit.jupiter.api.Test;

/** Tests the BFS implementation against independent level-order expectations. */
public class LevelOrder_429Test {

    private final LevelOrder_429 test = new LevelOrder_429();

    @Test
    public void testHappyCases() {
        Node root = new Node(1, Arrays.asList(
                new Node(3, Arrays.asList(new Node(5, List.of()), new Node(6, List.of()))),
                new Node(2, List.of()), new Node(4, List.of())));
        assertEquals(List.of(List.of(1), List.of(3, 2, 4), List.of(5, 6)), test.levelOrder(root));
    }

    @Test
    public void testOfficialSecondExample() {
        assertEquals(List.of(List.of(1), List.of(2, 3, 4, 5), List.of(6, 7, 8, 9, 10),
                List.of(11, 12, 13), List.of(14)), test.levelOrder(withFourteenLeaf()));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(List.of(), test.levelOrder(null));
        assertEquals(List.of(List.of(1)), test.levelOrder(new Node(1, List.of())));
    }

    @Test
    public void testLargeCase() {
        Node root = new Node(1, Arrays.asList(new Node(2, List.of()), new Node(3, List.of()), new Node(4, List.of())));
        assertEquals(List.of(List.of(1), List.of(2, 3, 4)), test.levelOrder(root));
    }

    @Test
    public void testSingleChain() {
        Node root = new Node(1, List.of(new Node(2, List.of(new Node(3, List.of())))));
        assertEquals(List.of(List.of(1), List.of(2), List.of(3)), test.levelOrder(root));
    }

    @Test
    public void testWideSingleLevel() {
        Node root = new Node(1, Arrays.asList(
                new Node(2, List.of()), new Node(3, List.of()), new Node(4, List.of()), new Node(5, List.of())));
        assertEquals(List.of(List.of(1), List.of(2, 3, 4, 5)), test.levelOrder(root));
    }

    @Test
    public void testThreeLevelsMixedBranching() {
        Node root = new Node(1, Arrays.asList(
                new Node(2, Arrays.asList(new Node(5, List.of()), new Node(6, List.of()))),
                new Node(3, List.of()), new Node(4, Arrays.asList(new Node(7, List.of())))));
        assertEquals(List.of(List.of(1), List.of(2, 3, 4), List.of(5, 6, 7)), test.levelOrder(root));
    }

    @Test
    public void testCrossParentOrderIsPreserved() {
        Node root = new Node(0, List.of(
                new Node(10, List.of(new Node(11, List.of()), new Node(12, List.of()))),
                new Node(20, List.of(new Node(21, List.of()))),
                new Node(30, List.of(new Node(31, List.of()), new Node(32, List.of()), new Node(33, List.of())))));
        assertEquals(List.of(List.of(0), List.of(10, 20, 30), List.of(11, 12, 21, 31, 32, 33)),
                test.levelOrder(root));
    }

    @Test
    public void testSparseDeepBranches() {
        Node root = new Node(100, List.of(
                new Node(1, List.of(new Node(2, List.of(new Node(3, List.of()))))),
                new Node(200, List.of()),
                new Node(300, List.of(new Node(301, List.of(new Node(302, List.of())))))));
        assertEquals(List.of(List.of(100), List.of(1, 200, 300), List.of(2, 301), List.of(3, 302)),
                test.levelOrder(root));
    }

    @Test
    public void testNegativeValues() {
        Node root = new Node(-1, Arrays.asList(new Node(-2, List.of()), new Node(-3, List.of())));
        assertEquals(List.of(List.of(-1), List.of(-2, -3)), test.levelOrder(root));
    }

    @Test
    public void testDuplicateValues() {
        Node root = new Node(1, Arrays.asList(new Node(1, List.of()), new Node(1, List.of())));
        assertEquals(List.of(List.of(1), List.of(1, 1)), test.levelOrder(root));
    }

    @Test
    public void testIntegerValueBoundaries() {
        Node root = new Node(Integer.MIN_VALUE, List.of(
                new Node(Integer.MAX_VALUE, List.of(new Node(Integer.MIN_VALUE, List.of()))),
                new Node(Integer.MAX_VALUE, List.of(new Node(Integer.MAX_VALUE, List.of())))));
        assertEquals(List.of(List.of(Integer.MIN_VALUE), List.of(Integer.MAX_VALUE, Integer.MAX_VALUE),
                List.of(Integer.MIN_VALUE, Integer.MAX_VALUE)), test.levelOrder(root));
    }

    @Test
    public void testNullChildEntriesIgnored() {
        Node root = new Node(1, Arrays.asList(null, new Node(2, Arrays.asList(null, new Node(3, List.of()), null)), null));
        assertEquals(List.of(List.of(1), List.of(2), List.of(3)), test.levelOrder(root));
    }

    @Test
    public void testVariableArityAndEmptyChildren() {
        Node root = new Node(0, List.of(
                new Node(1, List.of()),
                new Node(2, List.of(new Node(20, List.of()), new Node(21, List.of()), new Node(22, List.of()),
                        new Node(23, List.of()))),
                new Node(3, List.of(new Node(30, List.of(new Node(300, List.of())))))));
        assertEquals(List.of(List.of(0), List.of(1, 2, 3), List.of(20, 21, 22, 23, 30), List.of(300)),
                test.levelOrder(root));
    }

    @Test
    public void testAllNodesSameValue() {
        Node root = new Node(7, List.of(
                new Node(7, List.of(new Node(7, List.of()), new Node(7, List.of()))),
                new Node(7, List.of(new Node(7, List.of())))));
        assertEquals(List.of(List.of(7), List.of(7, 7), List.of(7, 7, 7)), test.levelOrder(root));
    }

    @Test
    public void testMaximumOfficialHeightBoundary() {
        Node root = new Node(0, List.of());
        Node current = root;
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(List.of(0));
        for (int i = 1; i < 1000; i++) {
            Node next = new Node(i, List.of());
            current.children = List.of(next);
            current = next;
            expected.add(List.of(i));
        }
        assertEquals(expected, test.levelOrder(root));
    }

    @Test
    public void testMaximumOfficialNodeCountWideBoundary() {
        List<Node> children = new ArrayList<>(9_999);
        List<Integer> expectedChildren = new ArrayList<>(9_999);
        for (int i = 1; i <= 9_999; i++) {
            children.add(new Node(i, List.of()));
            expectedChildren.add(i);
        }
        Node root = new Node(0, children);
        assertEquals(List.of(List.of(0), expectedChildren), test.levelOrder(root));
    }

    @Test
    public void testIndependentDepthFirstOracleOnSeededTrees() {
        for (int seed = 0; seed < 100; seed++) {
            Node root = randomTree(new Random(0x429L + seed), 1 + (seed * 37 % 180));
            assertEquals(depthFirstOracle(root), test.levelOrder(root), "seed=" + seed);
        }
    }

    @Test
    public void testIndependentOracleWithSignedAndDuplicateValues() {
        for (int seed = 0; seed < 40; seed++) {
            Random random = new Random(7_429L + seed);
            Node root = randomTree(random, 1 + random.nextInt(80));
            assertEquals(depthFirstOracle(root), test.levelOrder(root), "seed=" + seed);
        }
    }

    @Test
    public void testInputTopologyAndValuesRemainUnchanged() {
        Node root = randomTree(new Random(123_429L), 250);
        List<Node> nodes = collectNodes(root);
        Map<Node, Integer> values = new IdentityHashMap<>();
        Map<Node, List<Node>> children = new IdentityHashMap<>();
        for (Node node : nodes) {
            values.put(node, node.val);
            children.put(node, node.children);
        }
        test.levelOrder(root);
        assertEquals(nodes, collectNodes(root));
        for (Node node : nodes) {
            assertEquals(values.get(node), node.val);
            assertSame(children.get(node), node.children);
        }
    }

    @Test
    public void testRepeatedCallsOnSameInstanceAreIndependent() {
        Node firstRoot = new Node(1, List.of(new Node(2, List.of(new Node(3, List.of())))));
        Node secondRoot = new Node(10, List.of(new Node(20, List.of()), new Node(30, List.of())));
        assertEquals(List.of(List.of(1), List.of(2), List.of(3)), test.levelOrder(firstRoot));
        assertEquals(List.of(List.of(10), List.of(20, 30)), test.levelOrder(secondRoot));
        assertEquals(List.of(List.of(1), List.of(2), List.of(3)), test.levelOrder(firstRoot));
    }

    @Test
    public void testResultContainersAreFreshAndMutableWithoutAliasing() {
        Node root = new Node(1, List.of(new Node(2, List.of()), new Node(3, List.of())));
        List<List<Integer>> first = test.levelOrder(root);
        List<List<Integer>> second = test.levelOrder(root);
        assertNotSame(first, second);
        assertNotSame(first.get(0), second.get(0));
        first.get(0).set(0, 99);
        first.add(List.of(100));
        assertEquals(List.of(List.of(1), List.of(2, 3)), second);
        assertEquals(List.of(List.of(1), List.of(2, 3)), test.levelOrder(root));
    }

    @Test
    public void testNullResultsAreFreshAndMutable() {
        List<List<Integer>> first = test.levelOrder(null);
        List<List<Integer>> second = test.levelOrder(null);
        assertNotSame(first, second);
        first.add(List.of(1));
        assertEquals(List.of(), second);
        assertEquals(List.of(), test.levelOrder(null));
    }

    @Test
    public void testResultRowsDoNotAliasAcrossLevels() {
        Node root = new Node(1, List.of(new Node(2, List.of(new Node(4, List.of()))), new Node(3, List.of())));
        List<List<Integer>> output = test.levelOrder(root);
        assertNotSame(output.get(0), output.get(1));
        assertNotSame(output.get(1), output.get(2));
        output.get(1).set(0, 200);
        assertEquals(List.of(1), output.get(0));
        assertEquals(List.of(4), output.get(2));
    }

    @Test
    public void testMaximumWidthWithNegativeAndDuplicateValues() {
        List<Node> children = new ArrayList<>();
        List<Integer> expected = new ArrayList<>();
        for (int i = 0; i < 2_000; i++) {
            int value = i % 3 - 1_000;
            children.add(new Node(value, List.of()));
            expected.add(value);
        }
        Node root = new Node(-1_000, children);
        assertEquals(List.of(List.of(-1_000), expected), test.levelOrder(root));
    }

    @Test
    public void testEmptyLevelsAreNotInventedForLeafOnlyBranches() {
        Node root = new Node(5, List.of(new Node(6, List.of()), new Node(7, List.of())));
        List<List<Integer>> result = test.levelOrder(root);
        assertEquals(2, result.size());
        assertEquals(List.of(6, 7), result.get(1));
    }

    private static Node withFourteenLeaf() {
        Node fourteen = new Node(14, List.of());
        Node thirteen = new Node(13, List.of(fourteen));
        Node twelve = new Node(12, List.of());
        Node eleven = new Node(11, List.of());
        Node ten = new Node(10, List.of());
        Node nine = new Node(9, List.of());
        Node eight = new Node(8, List.of(thirteen));
        Node seven = new Node(7, List.of(twelve));
        Node six = new Node(6, List.of(eleven));
        return new Node(1, List.of(new Node(2, List.of()), new Node(3, List.of(six, seven)),
                new Node(4, List.of(eight)), new Node(5, List.of(nine, ten))));
    }

    private static Node randomTree(Random random, int nodeCount) {
        Node root = new Node(randomValue(random), new ArrayList<>());
        List<Node> nodes = new ArrayList<>(nodeCount);
        nodes.add(root);
        for (int i = 1; i < nodeCount; i++) {
            Node child = new Node(randomValue(random), new ArrayList<>());
            nodes.get(random.nextInt(nodes.size())).children.add(child);
            nodes.add(child);
        }
        return root;
    }

    private static int randomValue(Random random) {
        return switch (random.nextInt(8)) {
            case 0 -> Integer.MIN_VALUE;
            case 1 -> Integer.MAX_VALUE;
            case 2 -> -1;
            case 3 -> 0;
            case 4 -> 1;
            default -> random.nextInt(11) - 5;
        };
    }

    private static List<List<Integer>> depthFirstOracle(Node root) {
        List<List<Integer>> levels = new ArrayList<>();
        depthFirst(root, 0, levels);
        return levels;
    }

    private static void depthFirst(Node node, int depth, List<List<Integer>> levels) {
        if (node == null) {
            return;
        }
        while (levels.size() <= depth) {
            levels.add(new ArrayList<>());
        }
        levels.get(depth).add(node.val);
        if (node.children != null) {
            for (Node child : node.children) {
                depthFirst(child, depth + 1, levels);
            }
        }
    }

    private static List<Node> collectNodes(Node root) {
        if (root == null) {
            return List.of();
        }
        List<Node> nodes = new ArrayList<>();
        Deque<Node> queue = new ArrayDeque<>();
        Map<Node, Boolean> seen = new IdentityHashMap<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            if (seen.put(node, Boolean.TRUE) != null) {
                continue;
            }
            nodes.add(node);
            if (node.children != null) {
                for (Node child : node.children) {
                    if (child != null && !seen.containsKey(child)) {
                        queue.add(child);
                    }
                }
            }
        }
        return nodes;
    }
}
