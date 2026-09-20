package solutions.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import library.tree.TreeParser;
import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class LevelOrder_102Test {

    private final LevelOrder_102 test = new LevelOrder_102();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9); root.right = new TreeNode(20);
        root.right.left = new TreeNode(15); root.right.right = new TreeNode(7);
        assertEquals(List.of(List.of(3), List.of(9, 20), List.of(15, 7)), test.levelOrder(root));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(List.of(), test.levelOrder(null));
        assertEquals(List.of(List.of(1)), test.levelOrder(new TreeNode(1)));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        assertEquals(List.of(List.of(1), List.of(2, 3), List.of(4, 5)), test.levelOrder(root));
    }

    @Test
    public void testLeftSkewedTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        assertEquals(List.of(List.of(1), List.of(2), List.of(3)), test.levelOrder(root));
    }

    @Test
    public void testRightSkewedTree() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        assertEquals(List.of(List.of(1), List.of(2), List.of(3)), test.levelOrder(root));
    }

    @Test
    public void testNegativeValues() {
        TreeNode root = new TreeNode(-1);
        root.left = new TreeNode(-2);
        root.right = new TreeNode(-3);
        assertEquals(List.of(List.of(-1), List.of(-2, -3)), test.levelOrder(root));
    }

    @Test
    public void testSparseTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(5);
        assertEquals(List.of(List.of(1), List.of(2, 3), List.of(4, 5)), test.levelOrder(root));
    }

    @Test
    public void testDuplicateValues() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(1);
        root.right = new TreeNode(1);
        assertEquals(List.of(List.of(1), List.of(1, 1)), test.levelOrder(root));
    }

    @Test
    public void testFourLevels() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6); root.right.right = new TreeNode(7);
        root.left.left.left = new TreeNode(8);
        assertEquals(List.of(List.of(1), List.of(2, 3), List.of(4, 5, 6, 7), List.of(8)), test.levelOrder(root));
    }

    @Test
    public void testGiantDepthTree() {
        TreeNode root = new TreeNode(0);
        TreeNode current = root;
        for (int i = 1; i <= 100; i++) {
            current.left = new TreeNode(i);
            current = current.left;
        }
        List<List<Integer>> out = test.levelOrder(root);
        assertEquals(101, out.size());
        assertEquals(List.of(100), out.get(out.size() - 1));
    }

    @Test
    public void testPerfectBinaryTreeDepth3() {
        TreeNode root = TreeParser.deserialize("1,2,3,4,5,6,7");
        List<List<Integer>> result = test.levelOrder(root);
        assertEquals(List.of(List.of(1), List.of(2, 3), List.of(4, 5, 6, 7)), result);
        assertEquals(3, result.size());
        assertEquals(1, result.get(0).size());
        assertEquals(2, result.get(1).size());
        assertEquals(4, result.get(2).size());
    }

    @Test
    public void testImbalancedLeftDeeper() {
        // Left subtree depth 3, right subtree depth 1
        TreeNode root = TreeParser.deserialize("1,2,3,4,null,null,null,5");
        List<List<Integer>> result = test.levelOrder(root);
        assertEquals(4, result.size());
        assertEquals(List.of(1), result.get(0));
        assertEquals(List.of(2, 3), result.get(1));
        assertEquals(List.of(4), result.get(2));
        assertEquals(List.of(5), result.get(3));
    }

    @Test
    public void testLargeWideTreeViaTreeParser() {
        // Perfect binary tree of depth 7 = 127 nodes
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 127; i++) {
            if (i > 1) sb.append(",");
            sb.append(i);
        }
        TreeNode root = TreeParser.deserialize(sb.toString());
        List<List<Integer>> result = test.levelOrder(root);
        assertEquals(7, result.size());
        int totalNodes = result.stream().mapToInt(List::size).sum();
        assertEquals(127, totalNodes);
    }

    @Test
    public void testPropertyResultSizeEqualsDepth() {
        // Depth 5 left-skewed tree
        TreeNode root = TreeParser.deserialize("1,2,null,3,null,4,null,5");
        List<List<Integer>> result = test.levelOrder(root);
        assertEquals(5, result.size()); // depth == number of levels
    }

    @Test
    public void testPropertyTotalValuesEqualsTotalNodes() {
        TreeNode root = TreeParser.deserialize("10,20,30,40,50,null,60");
        List<List<Integer>> result = test.levelOrder(root);
        int totalNodes = result.stream().mapToInt(List::size).sum();
        assertEquals(6, totalNodes); // 6 non-null nodes in the tree
    }

    @Test
    public void testPropertyLevelSizeDoublesOrLess() {
        TreeNode root = TreeParser.deserialize("1,2,3,4,5,6,7,8,null,null,null,null,null,null,9");
        List<List<Integer>> result = test.levelOrder(root);
        for (int i = 1; i < result.size(); i++) {
            assertTrue(result.get(i).size() <= result.get(i - 1).size() * 2,
                    "Level " + i + " size " + result.get(i).size() +
                    " exceeds 2x of prior level size " + result.get(i - 1).size());
        }
    }

    @Test
    public void testTreeWithAllNegativeValues() {
        TreeNode root = TreeParser.deserialize("-10,-20,-30,-40,-50");
        List<List<Integer>> result = test.levelOrder(root);
        assertEquals(List.of(List.of(-10), List.of(-20, -30), List.of(-40, -50)), result);
    }

    @Test
    public void testTreeWithDuplicateValuesMultipleLevels() {
        TreeNode root = TreeParser.deserialize("5,5,5,5,5,5,5");
        List<List<Integer>> result = test.levelOrder(root);
        assertEquals(List.of(List.of(5), List.of(5, 5), List.of(5, 5, 5, 5)), result);
    }

    @Test
    public void testLeftToRightOrderAcrossUnevenLevels() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.left.right.right = new TreeNode(6);
        root.right.left.left = new TreeNode(7);

        assertEquals(List.of(
                List.of(1),
                List.of(2, 3),
                List.of(4, 5),
                List.of(6, 7)), test.levelOrder(root));
    }

    @Test
    public void testExtremeIntegerValues() {
        TreeNode root = new TreeNode(Integer.MIN_VALUE);
        root.left = new TreeNode(Integer.MAX_VALUE);
        root.right = new TreeNode(Integer.MIN_VALUE);
        root.left.right = new TreeNode(Integer.MAX_VALUE);

        assertEquals(List.of(
                List.of(Integer.MIN_VALUE),
                List.of(Integer.MAX_VALUE, Integer.MIN_VALUE),
                List.of(Integer.MAX_VALUE)), test.levelOrder(root));
    }

    @Test
    public void testResultListsAreIndependentAcrossInvocations() {
        TreeNode root = TreeParser.deserialize("1,2,3,4,5");

        List<List<Integer>> first = test.levelOrder(root);
        List<List<Integer>> second = test.levelOrder(root);

        assertEquals(first, second);
        assertNotSame(first, second);
        assertNotSame(first.get(0), second.get(0));

        // Changing one result must not affect a later result or the input tree.
        first.get(0).set(0, 99);
        assertEquals(List.of(List.of(1), List.of(2, 3), List.of(4, 5)), second);
        assertEquals(1, root.val);
    }

    @Test
    public void testInputTreeIsNotModified() {
        TreeNode root = TreeParser.deserialize("1,2,3,null,4,5,null");
        TreeNode originalLeft = root.left;
        TreeNode originalRight = root.right;
        TreeNode originalLeftRight = root.left.right;
        TreeNode originalRightLeft = root.right.left;

        test.levelOrder(root);

        assertEquals(1, root.val);
        assertEquals(2, root.left.val);
        assertEquals(3, root.right.val);
        assertSame(originalLeft, root.left);
        assertSame(originalRight, root.right);
        assertSame(originalLeftRight, root.left.right);
        assertSame(originalRightLeft, root.right.left);
    }

    @Test
    public void testCompleteTreeHasExpectedLevelSizesAndValues() {
        int depth = 10;
        int nodeCount = (1 << depth) - 1;
        TreeNode root = new TreeNode(1);
        List<TreeNode> currentLevel = List.of(root);
        int nextValue = 2;

        for (int level = 1; level < depth; level++) {
            List<TreeNode> nextLevel = new ArrayList<>(currentLevel.size() * 2);
            for (TreeNode node : currentLevel) {
                node.left = new TreeNode(nextValue++);
                node.right = new TreeNode(nextValue++);
                nextLevel.add(node.left);
                nextLevel.add(node.right);
            }
            currentLevel = nextLevel;
        }

        List<List<Integer>> result = test.levelOrder(root);
        assertEquals(depth, result.size());
        assertEquals(nodeCount, result.stream().mapToInt(List::size).sum());

        int value = 1;
        for (int level = 0; level < depth; level++) {
            int expectedSize = 1 << level;
            assertEquals(expectedSize, result.get(level).size());
            for (int actual : result.get(level)) {
                assertEquals(value++, actual);
            }
        }
    }

    @Test
    public void testOfficialMaximumNodeCountWithBoundaryValues() {
        // LeetCode permits 2,000 nodes and values in [-1000, 1000].  Build a complete
        // prefix so both the broadest levels and the final partial level are exercised.
        TreeNode root = new TreeNode(-1000);
        List<TreeNode> nodes = new ArrayList<>();
        nodes.add(root);
        for (int i = 1; i < 2000; i++) {
            TreeNode node = new TreeNode(i % 2 == 0 ? 1000 : -1000);
            TreeNode parent = nodes.get((i - 1) / 2);
            if (i % 2 == 1) {
                parent.left = node;
            } else {
                parent.right = node;
            }
            nodes.add(node);
        }

        List<List<Integer>> actual = test.levelOrder(root);
        assertEquals(expectedByIndependentBfs(root), actual);
        assertEquals(2000, actual.stream().mapToInt(List::size).sum());
        assertEquals(11, actual.size());
        assertEquals(977, actual.get(10).size());
    }

    @Test
    public void testOfficialMaximumDepthLeftSpine() {
        TreeNode root = new TreeNode(0);
        TreeNode current = root;
        for (int i = 1; i < 2000; i++) {
            current.left = new TreeNode(i - 1000);
            current = current.left;
        }

        List<List<Integer>> actual = test.levelOrder(root);
        assertEquals(expectedByIndependentBfs(root), actual);
        assertEquals(2000, actual.size());
        assertEquals(List.of(999), actual.get(1999));
    }

    @Test
    public void testSeededArbitraryTreesAgainstIndependentOracle() {
        Random random = new Random(1022026L);
        for (int caseNumber = 0; caseNumber < 100; caseNumber++) {
            int nodeCount = 1 + random.nextInt(150);
            TreeNode root = randomTree(nodeCount, random);
            assertEquals(expectedByIndependentBfs(root), test.levelOrder(root),
                    "seeded tree " + caseNumber + " with " + nodeCount + " nodes");
        }
    }

    @Test
    public void testAllIntegerBoundariesRemainInTheirOriginalLevels() {
        TreeNode root = new TreeNode(Integer.MIN_VALUE);
        root.left = new TreeNode(0);
        root.right = new TreeNode(Integer.MAX_VALUE);
        root.left.left = new TreeNode(Integer.MAX_VALUE);
        root.left.right = new TreeNode(Integer.MIN_VALUE);
        root.right.right = new TreeNode(0);
        root.left.right.left = new TreeNode(Integer.MAX_VALUE);

        assertEquals(List.of(
                List.of(Integer.MIN_VALUE),
                List.of(0, Integer.MAX_VALUE),
                List.of(Integer.MAX_VALUE, Integer.MIN_VALUE, 0),
                List.of(Integer.MAX_VALUE)), test.levelOrder(root));
    }

    @Test
    public void testFreshResultRowsRemainIndependentAfterNestedMutation() {
        TreeNode root = TreeParser.deserialize("8,4,12,2,6,10,14");
        TreeNode originalLeft = root.left;
        List<List<Integer>> first = test.levelOrder(root);
        List<List<Integer>> second = test.levelOrder(root);

        first.get(1).set(0, -1);
        first.add(List.of(99));

        assertEquals(List.of(List.of(8), List.of(4, 12), List.of(2, 6, 10, 14)), second);
        assertEquals(List.of(List.of(8), List.of(4, 12), List.of(2, 6, 10, 14)),
                test.levelOrder(root));
        assertNotSame(first.get(1), second.get(1));
        assertSame(originalLeft, root.left);
    }

    @Test
    public void testNullResultIsEmptyAndIndependentFromLaterNonEmptyCall() {
        List<List<Integer>> empty = test.levelOrder(null);
        empty.add(List.of(1));

        assertEquals(List.of(List.of(7)), test.levelOrder(new TreeNode(7)));
        assertEquals(List.of(List.of(1)), empty);
    }

    private static List<List<Integer>> expectedByIndependentBfs(TreeNode root) {
        List<List<Integer>> expected = new ArrayList<>();
        if (root == null) {
            return expected;
        }

        List<TreeNode> frontier = new ArrayList<>();
        frontier.add(root);
        while (!frontier.isEmpty()) {
            List<Integer> values = new ArrayList<>(frontier.size());
            List<TreeNode> next = new ArrayList<>();
            for (TreeNode node : frontier) {
                values.add(node.val);
                if (node.left != null) {
                    next.add(node.left);
                }
                if (node.right != null) {
                    next.add(node.right);
                }
            }
            expected.add(values);
            frontier = next;
        }
        return expected;
    }

    private static TreeNode randomTree(int nodeCount, Random random) {
        TreeNode root = new TreeNode(randomValue(random));
        List<TreeNode> availableParents = new ArrayList<>();
        availableParents.add(root);
        for (int i = 1; i < nodeCount; i++) {
            int parentIndex = random.nextInt(availableParents.size());
            TreeNode parent = availableParents.get(parentIndex);
            TreeNode child = new TreeNode(randomValue(random));
            if (parent.left == null && parent.right == null) {
                if (random.nextBoolean()) {
                    parent.left = child;
                } else {
                    parent.right = child;
                }
            } else if (parent.left == null) {
                parent.left = child;
            } else {
                parent.right = child;
            }
            if (parent.left != null && parent.right != null) {
                availableParents.remove(parentIndex);
            }
            availableParents.add(child);
        }
        return root;
    }

    private static int randomValue(Random random) {
        int selector = random.nextInt(20);
        if (selector == 0) {
            return Integer.MIN_VALUE;
        }
        if (selector == 1) {
            return Integer.MAX_VALUE;
        }
        return random.nextInt(2001) - 1000;
    }
}
