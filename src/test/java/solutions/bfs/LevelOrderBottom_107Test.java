package solutions.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class LevelOrderBottom_107Test {

    private final LevelOrderBottom_107 test = new LevelOrderBottom_107();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9); root.right = new TreeNode(20);
        root.right.left = new TreeNode(15); root.right.right = new TreeNode(7);
        assertEquals(List.of(List.of(15, 7), List.of(9, 20), List.of(3)), test.levelOrderBottom(root));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(List.of(), test.levelOrderBottom(null));
        assertEquals(List.of(List.of(1)), test.levelOrderBottom(new TreeNode(1)));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        assertEquals(List.of(List.of(4, 5), List.of(2, 3), List.of(1)), test.levelOrderBottom(root));
    }

    @Test
    public void testLeftSkewedTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        assertEquals(List.of(List.of(3), List.of(2), List.of(1)), test.levelOrderBottom(root));
    }

    @Test
    public void testRightSkewedTree() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        assertEquals(List.of(List.of(3), List.of(2), List.of(1)), test.levelOrderBottom(root));
    }

    @Test
    public void testOnlyLeftChildAtTheRoot() {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(20);
        assertEquals(List.of(List.of(20), List.of(10)), test.levelOrderBottom(root));
    }

    @Test
    public void testOnlyRightChildAtTheRoot() {
        TreeNode root = new TreeNode(10);
        root.right = new TreeNode(30);
        assertEquals(List.of(List.of(30), List.of(10)), test.levelOrderBottom(root));
    }

    @Test
    public void testNegativeValues() {
        TreeNode root = new TreeNode(-1);
        root.left = new TreeNode(-2);
        root.right = new TreeNode(-3);
        assertEquals(List.of(List.of(-2, -3), List.of(-1)), test.levelOrderBottom(root));
    }

    @Test
    public void testSparseTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(5);
        assertEquals(List.of(List.of(4, 5), List.of(2, 3), List.of(1)), test.levelOrderBottom(root));
    }

    @Test
    public void testAlternatingSingleChildChain() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(3);
        root.left.right.left.right = new TreeNode(4);

        assertEquals(List.of(
                List.of(4), List.of(3), List.of(2), List.of(1), List.of(0)),
                test.levelOrderBottom(root));
    }

    @Test
    public void testSparseCrossParentOrdering() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        root.left.left.right = new TreeNode(6);
        root.right.right.left = new TreeNode(7);

        assertEquals(List.of(
                List.of(6, 7), List.of(4, 5), List.of(2, 3), List.of(1)),
                test.levelOrderBottom(root));
    }

    @Test
    public void testDuplicateValues() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(1);
        root.right = new TreeNode(1);
        assertEquals(List.of(List.of(1, 1), List.of(1)), test.levelOrderBottom(root));
    }

    @Test
    public void testFourLevels() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6); root.right.right = new TreeNode(7);
        root.left.left.left = new TreeNode(8);
        assertEquals(List.of(List.of(8), List.of(4, 5, 6, 7), List.of(2, 3), List.of(1)), test.levelOrderBottom(root));
    }

    @Test
    public void testGiantDepthTree() {
        TreeNode root = new TreeNode(0);
        TreeNode current = root;
        for (int i = 1; i <= 100; i++) {
            current.left = new TreeNode(i);
            current = current.left;
        }
        List<List<Integer>> out = test.levelOrderBottom(root);
        assertEquals(101, out.size());
        assertEquals(List.of(100), out.get(0));
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
                List.of(6, 7),
                List.of(4, 5),
                List.of(2, 3),
                List.of(1)), test.levelOrderBottom(root));
    }

    @Test
    public void testExtremeIntegerValues() {
        TreeNode root = new TreeNode(Integer.MIN_VALUE);
        root.left = new TreeNode(Integer.MAX_VALUE);
        root.right = new TreeNode(Integer.MIN_VALUE);
        root.left.right = new TreeNode(Integer.MAX_VALUE);

        assertEquals(List.of(
                List.of(Integer.MAX_VALUE),
                List.of(Integer.MAX_VALUE, Integer.MIN_VALUE),
                List.of(Integer.MIN_VALUE)), test.levelOrderBottom(root));
    }

    @Test
    public void testOfficialValueBoundsInEveryLevel() {
        TreeNode root = new TreeNode(-1000);
        root.left = new TreeNode(1000);
        root.right = new TreeNode(-1000);
        root.left.left = new TreeNode(1000);
        root.left.right = new TreeNode(-1000);
        root.right.right = new TreeNode(1000);

        assertEquals(List.of(
                List.of(1000, -1000, 1000),
                List.of(1000, -1000),
                List.of(-1000)), test.levelOrderBottom(root));
    }

    @Test
    public void testResultListsAreIndependentAcrossInvocations() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);

        List<List<Integer>> first = test.levelOrderBottom(root);
        List<List<Integer>> second = test.levelOrderBottom(root);

        assertEquals(first, second);
        assertNotSame(first, second);
        assertNotSame(first.get(0), second.get(0));

        // Changing one result must not affect a later result or the input tree.
        first.get(0).set(0, 99);
        assertEquals(List.of(List.of(4), List.of(2, 3), List.of(1)), second);
        assertEquals(1, root.val);
    }

    @Test
    public void testInputTreeIsNotModified() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(5);
        TreeNode originalLeft = root.left;
        TreeNode originalRight = root.right;
        TreeNode originalLeftRight = root.left.right;
        TreeNode originalRightLeft = root.right.left;

        test.levelOrderBottom(root);

        assertEquals(1, root.val);
        assertEquals(2, root.left.val);
        assertEquals(3, root.right.val);
        assertSame(originalLeft, root.left);
        assertSame(originalRight, root.right);
        assertSame(originalLeftRight, root.left.right);
        assertSame(originalRightLeft, root.right.left);
    }

    @Test
    public void testRepeatedCallsAcrossDifferentTreesDoNotRetainState() {
        TreeNode first = new TreeNode(1);
        first.left = new TreeNode(2);
        first.right = new TreeNode(3);
        TreeNode second = new TreeNode(9);
        second.right = new TreeNode(8);
        second.right.left = new TreeNode(7);

        assertEquals(List.of(List.of(2, 3), List.of(1)), test.levelOrderBottom(first));
        assertEquals(List.of(List.of(7), List.of(8), List.of(9)), test.levelOrderBottom(second));
        assertEquals(List.of(List.of(2, 3), List.of(1)), test.levelOrderBottom(first));
    }

    @Test
    public void testOuterResultMutationDoesNotAffectLaterCall() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        List<List<Integer>> first = test.levelOrderBottom(root);
        List<List<Integer>> second = test.levelOrderBottom(root);

        first.clear();
        assertEquals(List.of(List.of(2, 3), List.of(1)), second);
        assertEquals(List.of(List.of(2, 3), List.of(1)), test.levelOrderBottom(root));
    }

    @Test
    public void testCompleteTreeHasExpectedBottomUpLevelSizesAndValues() {
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

        List<List<Integer>> result = test.levelOrderBottom(root);
        assertEquals(depth, result.size());
        assertEquals(nodeCount, result.stream().mapToInt(List::size).sum());

        for (int resultLevel = 0; resultLevel < depth; resultLevel++) {
            int treeLevel = depth - 1 - resultLevel;
            int expectedSize = 1 << treeLevel;
            assertEquals(expectedSize, result.get(resultLevel).size());
            for (int offset = 0; offset < expectedSize; offset++) {
                assertEquals((1 << treeLevel) + offset, result.get(resultLevel).get(offset));
            }
        }
    }

    @Test
    public void testIndependentOracleAcrossSeededSparseTrees() {
        Random random = new Random(107_2026L);
        for (int caseNumber = 0; caseNumber < 80; caseNumber++) {
            int nodeCount = 1 + random.nextInt(120);
            TreeNode root = seededTree(random, nodeCount);
            assertEquals(bottomUpByDepthFirstTraversal(root), test.levelOrderBottom(root),
                    "seeded tree case " + caseNumber + " with " + nodeCount + " nodes");
        }
    }

    @Test
    public void testOfficialMaximumNodeCount() {
        TreeNode root = completePrefixTree(2000);
        List<List<Integer>> expected = bottomUpByDepthFirstTraversal(root);
        List<List<Integer>> actual = test.levelOrderBottom(root);

        assertEquals(expected, actual);
        assertEquals(2000, actual.stream().mapToInt(List::size).sum());
        assertEquals(11, actual.size());
        assertEquals(977, actual.get(0).size());
    }

    private static TreeNode seededTree(Random random, int nodeCount) {
        TreeNode root = new TreeNode(randomOfficialValue(random));
        List<TreeNode> nodes = new ArrayList<>();
        nodes.add(root);
        for (int index = 1; index < nodeCount; index++) {
            TreeNode child = new TreeNode(randomOfficialValue(random));
            while (true) {
                TreeNode parent = nodes.get(random.nextInt(nodes.size()));
                if (random.nextBoolean()) {
                    if (parent.left == null) {
                        parent.left = child;
                        break;
                    }
                } else if (parent.right == null) {
                    parent.right = child;
                    break;
                }
            }
            nodes.add(child);
        }
        return root;
    }

    private static int randomOfficialValue(Random random) {
        return random.nextInt(2001) - 1000;
    }

    private static TreeNode completePrefixTree(int nodeCount) {
        TreeNode[] nodes = new TreeNode[nodeCount];
        for (int index = 0; index < nodeCount; index++) {
            int value = (int) (((long) index * 7919) % 2001) - 1000;
            nodes[index] = new TreeNode(value);
        }
        for (int index = 0; index < nodeCount; index++) {
            int left = index * 2 + 1;
            int right = left + 1;
            if (left < nodeCount) {
                nodes[index].left = nodes[left];
            }
            if (right < nodeCount) {
                nodes[index].right = nodes[right];
            }
        }
        return nodes[0];
    }

    /**
     * Independent oracle: depth-first traversal groups values by depth, then reverses the
     * completed top-down groups. It does not use the queue/level-size algorithm under test.
     */
    private static List<List<Integer>> bottomUpByDepthFirstTraversal(TreeNode root) {
        List<List<Integer>> topDown = new ArrayList<>();
        collectByDepth(root, 0, topDown);
        Collections.reverse(topDown);
        return topDown;
    }

    private static void collectByDepth(TreeNode node, int depth, List<List<Integer>> levels) {
        if (node == null) {
            return;
        }
        while (levels.size() <= depth) {
            levels.add(new ArrayList<>());
        }
        levels.get(depth).add(node.val);
        collectByDepth(node.left, depth + 1, levels);
        collectByDepth(node.right, depth + 1, levels);
    }
}
