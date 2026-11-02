package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.InvocationInterceptor;
import org.junit.jupiter.api.extension.ReflectiveInvocationContext;

@Timeout(15)
@ExtendWith(MaxPathSum_124Test.ProcessTimeoutExtension.class)
public class MaxPathSum_124Test {

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        assertEquals(6, new MaxPathSum_124().maxPathSum(root));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(-3, new MaxPathSum_124().maxPathSum(new TreeNode(-3)));
        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        assertEquals(42, new MaxPathSum_124().maxPathSum(root));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(1);
        assertEquals(48, new MaxPathSum_124().maxPathSum(root));
    }

    @Test
    public void testSingleNode() {
        assertEquals(42, new MaxPathSum_124().maxPathSum(new TreeNode(42)));
    }

    @Test
    public void testAllNegative() {
        TreeNode root = new TreeNode(-1);
        root.left = new TreeNode(-2);
        root.right = new TreeNode(-3);
        // Best path is just -1 (the root alone)
        assertEquals(-1, new MaxPathSum_124().maxPathSum(root));
    }

    @Test
    public void testLeftSkewed() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        // Best path: 3 + 2 + 1 = 6
        assertEquals(6, new MaxPathSum_124().maxPathSum(root));
    }

    @Test
    public void testRightSkewed() {
        TreeNode root = new TreeNode(10);
        root.right = new TreeNode(5);
        root.right.right = new TreeNode(3);
        // Best path: 10 + 5 + 3 = 18
        assertEquals(18, new MaxPathSum_124().maxPathSum(root));
    }

    @Test
    public void testPathNotThroughRoot() {
        TreeNode root = new TreeNode(-100);
        root.left = new TreeNode(10);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(6);
        // Best path: 5 + 10 + 6 = 21, doesn't go through root
        assertEquals(21, new MaxPathSum_124().maxPathSum(root));
    }

    @Test
    public void testMixedValues() {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(-1);
        // Best path: 2 alone (since -1 would reduce it)
        assertEquals(2, new MaxPathSum_124().maxPathSum(root));
    }

    @Test
    public void testZeroValues() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(0);
        root.right = new TreeNode(0);
        assertEquals(0, new MaxPathSum_124().maxPathSum(root));
    }

    @Test
    public void testGiantTree() {
        // Build a perfect binary tree of depth 10 with all values = 1
        TreeNode root = buildPerfectTree(1, 10);
        // The max path goes through the root: sum of left chain + root + sum of right chain
        // Each chain from root to leaf has 10 nodes, so path = 10 + 10 - 1 = 19
        assertEquals(19, new MaxPathSum_124().maxPathSum(root));
    }

    private TreeNode buildPerfectTree(int val, int depth) {
        if (depth == 0) return null;
        TreeNode node = new TreeNode(val);
        node.left = buildPerfectTree(val, depth - 1);
        node.right = buildPerfectTree(val, depth - 1);
        return node;
    }

    @Test
    public void testSingleNodeNegativeLarge() {
        // Existing singleton extension beyond LeetCode's value bounds: no addition can overflow.
        assertEquals(-2147483648, new MaxPathSum_124().maxPathSum(new TreeNode(Integer.MIN_VALUE)));
    }

    @Test
    public void testAllNegativeDeeper() {
        // All negative: answer is the least negative (max single node)
        TreeNode root = new TreeNode(-5);
        root.left = new TreeNode(-2);
        root.right = new TreeNode(-8);
        root.left.left = new TreeNode(-4);
        root.left.right = new TreeNode(-1);
        root.right.left = new TreeNode(-9);
        // Least negative is -1
        assertEquals(-1, new MaxPathSum_124().maxPathSum(root));
    }

    @Test
    public void testPathThroughRootBothSubtrees() {
        // Best path uses both subtrees through root: 3 + 5 + 4 = 12
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(4);
        root.left.left = new TreeNode(-10);
        root.right.right = new TreeNode(-10);
        assertEquals(12, new MaxPathSum_124().maxPathSum(root));
    }

    @Test
    public void testPathEntirelyInLeftSubtree() {
        // Best path is entirely in left subtree: 8 + 10 + 9 = 27
        TreeNode root = new TreeNode(-50);
        root.left = new TreeNode(10);
        root.left.left = new TreeNode(8);
        root.left.right = new TreeNode(9);
        root.right = new TreeNode(-50);
        assertEquals(27, new MaxPathSum_124().maxPathSum(root));
    }

    @Test
    public void testLongChainPositive() {
        // Chain of 20 nodes each with value 5, best path = 20 * 5 = 100
        TreeNode root = new TreeNode(5);
        TreeNode cur = root;
        for (int i = 1; i < 20; i++) {
            cur.right = new TreeNode(5);
            cur = cur.right;
        }
        assertEquals(100, new MaxPathSum_124().maxPathSum(root));
    }

    @Test
    public void testLongChainMixed() {
        // Chain: 1 -> -100 -> 50 -> 50 -> 50, best subpath = 50+50+50 = 150
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(-100);
        root.left.left = new TreeNode(50);
        root.left.left.left = new TreeNode(50);
        root.left.left.left.left = new TreeNode(50);
        assertEquals(150, new MaxPathSum_124().maxPathSum(root));
    }

    @Test
    public void testPropertyResultGeqMaxNode() {
        // Property: result >= max single node value for various trees
        int[][] trees = {{1, 2, 3}, {-10, 9, 20, 15, 7}, {-1, -2, -3, -4, -5}};
        for (int[] vals : trees) {
            TreeNode root = buildFromArray(vals);
            int result = new MaxPathSum_124().maxPathSum(root);
            int maxNode = Integer.MIN_VALUE;
            for (int v : vals) maxNode = Math.max(maxNode, v);
            assertTrue(result >= maxNode, "Result " + result + " < max node " + maxNode);
            assertEquals(referenceMaxPath(root), result, "tree=" + Arrays.toString(vals));
        }
    }

    @Test
    public void testDiamondShapedTree() {
        // Both children have useful downward paths, without any shared nodes.
        //       1
        //      / \
        //    10   10
        //    /      \
        //   5        5
        // Best: 5+10+1+10+5 = 31
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(10);
        root.right = new TreeNode(10);
        root.left.left = new TreeNode(5);
        root.right.right = new TreeNode(5);
        assertEquals(31, new MaxPathSum_124().maxPathSum(root));
    }

    private TreeNode buildFromArray(int[] vals) {
        if (vals.length == 0) return null;
        TreeNode[] nodes = new TreeNode[vals.length];
        for (int i = 0; i < vals.length; i++) nodes[i] = new TreeNode(vals[i]);
        for (int i = 0; i < vals.length; i++) {
            if (2 * i + 1 < vals.length) nodes[i].left = nodes[2 * i + 1];
            if (2 * i + 2 < vals.length) nodes[i].right = nodes[2 * i + 2];
        }
        return nodes[0];
    }

    @Test
    public void testRepeatedCallsResetMaximum() {
        MaxPathSum_124 solution = new MaxPathSum_124();
        assertEquals(6, solution.maxPathSum(new TreeNode(6)));
        assertEquals(-2, solution.maxPathSum(new TreeNode(-2)));
        assertEquals(0, solution.maxPathSum(new TreeNode(0)));
        assertEquals(6, solution.maxPathSum(new TreeNode(6)));
    }

    @Test
    public void testSingleNodeValueBoundaries() {
        for (int value : new int[]{-1000, 0, 1000, Integer.MAX_VALUE}) {
            assertMaxPath(value, new TreeNode(value), "singleton=" + value);
        }
    }

    @Test
    public void testDiscardBothNegativeChildren() {
        assertMaxPath(5, buildFromArray(new int[]{5, -10, -2}), "both children reduce the sum");
    }

    @Test
    public void testNegativeRootCanJoinProfitableChildren() {
        assertMaxPath(9, buildFromArray(new int[]{-3, 5, 7}), "negative root still connects useful arms");
    }

    @Test
    public void testNegativeBridgeKeepsPositiveDescendant() {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(-3);
        root.left.left = new TreeNode(8);
        assertMaxPath(15, root, "the entire child gain matters, not just the child's value");
    }

    @Test
    public void testOnlyOneChildBranchCanBeReturnedToParent() {
        // Returning 20 + 1 + 30 to the parent would incorrectly count a three-armed path of 101.
        assertMaxPath(81, buildFromArray(new int[]{10, 1, 40, 20, 30}), "extend the right arm of a left child");
        TreeNode mirrored = new TreeNode(10);
        mirrored.left = new TreeNode(40);
        mirrored.right = new TreeNode(1);
        mirrored.right.left = new TreeNode(30);
        mirrored.right.right = new TreeNode(20);
        assertMaxPath(81, mirrored, "extend the left arm of a right child");
    }

    @Test
    public void testEqualChildGainsDoNotDuplicateTheBranch() {
        assertMaxPath(10, buildFromArray(new int[]{1, 2, 3, 4, 4}), "either tied arm may extend upward");
    }

    @Test
    public void testZeroBridgeJoinsPositiveSubtrees() {
        assertMaxPath(11, buildFromArray(new int[]{0, 5, 6}), "zero-valued connector");
    }

    @Test
    public void testBestPathMayEndAtInternalNodes() {
        assertMaxPath(7, buildFromArray(new int[]{4, 3, -50, -100, -200}), "leaf endpoints are not required");
    }

    @Test
    public void testZeroNodeBeatsNegativePaths() {
        assertMaxPath(0, buildFromArray(new int[]{-4, -2, 0}), "non-empty zero path");
    }

    @Test
    public void testMaximumSizeBalancedTree() {
        int[] values = new int[30_000];
        Arrays.fill(values, 1000);
        // Both root subtrees reach level 15, so a longest path has 15 + 15 - 1 nodes.
        assertMaxPath(29_000, buildFromArray(values), "30,000-node complete tree at maximum value");
    }

    @Test
    public void testMaximumHeightChains() {
        // These valid inputs require more stack than the default JVM supplies; see the process extension.
        // The positive chain also reaches the largest permitted sum: 30,000 * 1,000.
        assertMaxPath(30_000_000, buildChain(30_000, 1000, false), "maximum-height right chain");
        assertMaxPath(-1000, buildChain(30_000, -1000, true), "maximum-height all-negative left chain");
    }

    @Test
    public void testExhaustiveSmallTrees() {
        // All 11,496 non-empty ordered binary trees of 1..5 nodes over {-1, 0, 1}.
        // Larger trees and values are covered separately by seeded and explicit boundary cases.
        int checked = 0;
        for (int size = 1; size <= 5; size++) {
            int assignments = (int) Math.pow(3, size);
            for (Shape shape : shapes(size)) {
                for (int encoding = 0; encoding < assignments; encoding++) {
                    TreeNode root = materialize(shape, new int[]{encoding});
                    assertMaxPath(referenceMaxPath(root), root, "exhaustive tree=" + describe(root));
                    checked++;
                }
            }
        }
        assertEquals(11_496, checked);
    }

    @Test
    public void testSeededTreesAcrossFullValueRange() {
        Random random = new Random(124_124);
        for (int trial = 0; trial < 300; trial++) {
            TreeNode root = randomTree(random, 1 + random.nextInt(40));
            assertMaxPath(referenceMaxPath(root), root,
                    "seed=124124, trial=" + trial + ", tree=" + describe(root));
        }
    }

    private void assertMaxPath(int expected, TreeNode root, String context) {
        Map<TreeNode, NodeState> before = new IdentityHashMap<>();
        ArrayDeque<TreeNode> pending = new ArrayDeque<>();
        pending.push(root);
        while (!pending.isEmpty()) {
            TreeNode node = pending.pop();
            before.put(node, new NodeState(node.val, node.left, node.right));
            if (node.left != null) pending.push(node.left);
            if (node.right != null) pending.push(node.right);
        }
        MaxPathSum_124 solution = new MaxPathSum_124();
        assertEquals(expected, solution.maxPathSum(root), context);
        for (Map.Entry<TreeNode, NodeState> entry : before.entrySet()) {
            TreeNode node = entry.getKey();
            NodeState original = entry.getValue();
            assertEquals(original.value(), node.val, context + ": node value changed");
            assertSame(original.left(), node.left, context + ": left child changed");
            assertSame(original.right(), node.right, context + ": right child changed");
        }
        assertEquals(expected, solution.maxPathSum(root), context + ": repeated tree call");
    }

    private record NodeState(int value, TreeNode left, TreeNode right) {
    }

    private int referenceMaxPath(TreeNode root) {
        // Independently enumerate every start/end path in the undirected tree, without child gains.
        // O(n^2) is practical for the small exhaustive and seeded inputs only.
        Map<TreeNode, TreeNode> parents = new IdentityHashMap<>();
        List<TreeNode> nodes = new ArrayList<>();
        nodes.add(root);
        for (int i = 0; i < nodes.size(); i++) {
            TreeNode node = nodes.get(i);
            if (node.left != null) {
                parents.put(node.left, node);
                nodes.add(node.left);
            }
            if (node.right != null) {
                parents.put(node.right, node);
                nodes.add(node.right);
            }
        }
        int best = Integer.MIN_VALUE;
        for (TreeNode start : nodes) {
            ArrayDeque<PathStep> paths = new ArrayDeque<>();
            paths.push(new PathStep(start, null, start.val));
            while (!paths.isEmpty()) {
                PathStep path = paths.pop();
                best = Math.max(best, path.sum());
                for (TreeNode next : new TreeNode[]{path.node().left, path.node().right, parents.get(path.node())}) {
                    if (next != null && next != path.previous()) {
                        paths.push(new PathStep(next, path.node(), path.sum() + next.val));
                    }
                }
            }
        }
        return best;
    }

    private record PathStep(TreeNode node, TreeNode previous, int sum) {
    }

    private record Shape(Shape left, Shape right) {
    }

    private List<Shape> shapes(int size) {
        if (size == 0) return Collections.singletonList(null);
        List<Shape> result = new ArrayList<>();
        for (int leftSize = 0; leftSize < size; leftSize++) {
            for (Shape left : shapes(leftSize)) {
                for (Shape right : shapes(size - 1 - leftSize)) result.add(new Shape(left, right));
            }
        }
        return result;
    }

    private TreeNode materialize(Shape shape, int[] encoding) {
        if (shape == null) return null;
        TreeNode node = new TreeNode(encoding[0] % 3 - 1);
        encoding[0] /= 3;
        node.left = materialize(shape.left(), encoding);
        node.right = materialize(shape.right(), encoding);
        return node;
    }

    private TreeNode randomTree(Random random, int size) {
        TreeNode root = new TreeNode(random.nextInt(2001) - 1000);
        List<TreeNode> availableParents = new ArrayList<>();
        availableParents.add(root);
        for (int i = 1; i < size; i++) {
            int index = random.nextInt(availableParents.size());
            TreeNode parent = availableParents.get(index);
            TreeNode child = new TreeNode(random.nextInt(2001) - 1000);
            if (parent.left == null && (parent.right != null || random.nextBoolean())) parent.left = child;
            else parent.right = child;
            if (parent.left != null && parent.right != null) availableParents.remove(index);
            availableParents.add(child);
        }
        return root;
    }

    private TreeNode buildChain(int size, int value, boolean leftward) {
        TreeNode root = new TreeNode(value);
        TreeNode current = root;
        for (int i = 1; i < size; i++) {
            TreeNode child = new TreeNode(value);
            if (leftward) current.left = child;
            else current.right = child;
            current = child;
        }
        return root;
    }

    private String describe(TreeNode root) {
        if (root == null) return "#";
        return root.val + "(" + describe(root.left) + "," + describe(root.right) + ")";
    }

    // DFS does not honor interruption, so each test runs in a JVM that is forcibly stopped on timeout.
    // Only maximum-height chains get a larger stack; the 30,000-node balanced case uses the default.
    static final class ProcessTimeoutExtension implements InvocationInterceptor {
        @Override
        public void interceptTestMethod(Invocation<Void> invocation,
                                        ReflectiveInvocationContext<Method> context, ExtensionContext extensionContext)
                throws Throwable {
            Path log = Files.createTempFile("max-path-sum-124-test-", ".log");
            Process process = null;
            try {
                List<String> command = new ArrayList<>();
                command.add(System.getProperty("java.home") + "/bin/java");
                if (context.getExecutable().getName().equals("testMaximumHeightChains")) command.add("-Xss16m");
                command.addAll(List.of("-cp",
                        System.getProperty("surefire.test.class.path", System.getProperty("java.class.path")),
                        MaxPathSum_124Test.class.getName(), context.getExecutable().getName()));
                process = new ProcessBuilder(command).redirectErrorStream(true).redirectOutput(log.toFile()).start();
                if (!process.waitFor(10, TimeUnit.SECONDS)) {
                    fail("test exceeded enforceable 10-second process limit: " + context.getExecutable().getName());
                }
                if (process.exitValue() != 0) {
                    fail("child test failed: " + context.getExecutable().getName()
                            + System.lineSeparator() + Files.readString(log));
                }
                invocation.skip();
            } finally {
                try {
                    if (process != null && process.isAlive()) {
                        process.destroyForcibly();
                        if (!process.waitFor(2, TimeUnit.SECONDS)) {
                            fail("child test could not be terminated: " + context.getExecutable().getName());
                        }
                    }
                } finally {
                    Files.deleteIfExists(log);
                }
            }
        }
    }

    static void main(String[] args) {
        if (args.length != 1) throw new IllegalArgumentException("missing test method");
        try {
            MaxPathSum_124Test.class.getDeclaredMethod(args[0]).invoke(new MaxPathSum_124Test());
        } catch (InvocationTargetException exception) {
            throw new RuntimeException(exception.getCause());
        } catch (ReflectiveOperationException exception) {
            throw new RuntimeException(exception);
        }
    }
}
