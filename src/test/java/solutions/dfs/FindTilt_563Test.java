package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.IdentityHashMap;
import java.util.Map;

public class FindTilt_563Test {

    @Test
    public void testNullTreeHasZeroTilt() {
        assertEquals(0, new FindTilt_563().findTilt(null));
    }

    @Test
    public void testLeafWithZeroValueHasZeroTilt() {
        assertEquals(0, new FindTilt_563().findTilt(new TreeNode(0)));
    }

    @Test
    public void testLeafWithNegativeValueHasZeroTilt() {
        assertEquals(0, new FindTilt_563().findTilt(new TreeNode(-1000)));
    }

    @Test
    public void testLeetCodeExampleOne() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        assertEquals(1, new FindTilt_563().findTilt(root));
    }

    @Test
    public void testLeetCodeExampleTwo() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2); root.right = new TreeNode(9);
        root.left.left = new TreeNode(3); root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(7);
        assertEquals(15, new FindTilt_563().findTilt(root));
    }

    @Test
    public void testLeetCodeExampleThree() {
        TreeNode root = new TreeNode(21);
        root.left = new TreeNode(7); root.right = new TreeNode(14);
        root.left.left = new TreeNode(1); root.left.right = new TreeNode(1);
        root.right.left = new TreeNode(2); root.right.right = new TreeNode(2);
        root.left.left.left = new TreeNode(3); root.left.left.right = new TreeNode(3);
        assertEquals(9, new FindTilt_563().findTilt(root));
    }

    @Test
    public void testOnlyLeftChild() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        assertEquals(2, new FindTilt_563().findTilt(root));
    }

    @Test
    public void testOnlyRightChild() {
        TreeNode root = new TreeNode(-1);
        root.right = new TreeNode(-2);
        assertEquals(2, new FindTilt_563().findTilt(root));
    }

    @Test
    public void testLeftSkewedTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(4);
        assertEquals(expectedTilt(root), new FindTilt_563().findTilt(root));
    }

    @Test
    public void testRightSkewedTree() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(4);
        assertEquals(expectedTilt(root), new FindTilt_563().findTilt(root));
    }

    @Test
    public void testPerfectBalancedTreeHasZeroTilt() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(2);
        root.left.left = new TreeNode(3); root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(3); root.right.right = new TreeNode(3);
        assertEquals(0, new FindTilt_563().findTilt(root));
    }

    @Test
    public void testAllNegativeValues() {
        TreeNode root = new TreeNode(-1);
        root.left = new TreeNode(-2); root.right = new TreeNode(-3);
        assertEquals(expectedTilt(root), new FindTilt_563().findTilt(root));
    }

    @Test
    public void testMixedSignsAndZero() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(5); root.right = new TreeNode(-5);
        root.left.right = new TreeNode(-2);
        assertEquals(expectedTilt(root), new FindTilt_563().findTilt(root));
    }

    @Test
    public void testDuplicateValuesAndUnequalSubtrees() {
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(4); root.right = new TreeNode(4);
        root.left.left = new TreeNode(4);
        root.right.right = new TreeNode(4);
        root.right.right.left = new TreeNode(4);
        assertEquals(expectedTilt(root), new FindTilt_563().findTilt(root));
    }

    @Test
    public void testTiltAccumulatesEveryInternalNode() {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(1); root.right = new TreeNode(20);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(8);
        root.right.left = new TreeNode(3);
        root.right.left.right = new TreeNode(9);
        assertEquals(expectedTilt(root), new FindTilt_563().findTilt(root));
    }

    @Test
    public void testMaximumAllowedNodeValues() {
        TreeNode root = new TreeNode(1000);
        root.left = new TreeNode(-1000); root.right = new TreeNode(1000);
        root.left.left = new TreeNode(1000); root.right.right = new TreeNode(-1000);
        assertEquals(expectedTilt(root), new FindTilt_563().findTilt(root));
    }

    @Test
    public void testSparseZigZagTree() {
        TreeNode root = new TreeNode(6);
        root.right = new TreeNode(-4);
        root.right.left = new TreeNode(7);
        root.right.left.right = new TreeNode(-3);
        root.right.left.right.left = new TreeNode(9);
        assertEquals(expectedTilt(root), new FindTilt_563().findTilt(root));
    }

    @Test
    public void testRepeatedCallsOnSameInstanceStartFresh() {
        FindTilt_563 solver = new FindTilt_563();
        TreeNode first = new TreeNode(1);
        first.left = new TreeNode(2); first.right = new TreeNode(5);
        TreeNode second = new TreeNode(10);
        second.left = new TreeNode(-4); second.right = new TreeNode(3);
        assertEquals(expectedTilt(first), solver.findTilt(first));
        assertEquals(expectedTilt(second), solver.findTilt(second));
    }

    @Test
    public void testNullCallAfterNonNullCallResetsState() {
        FindTilt_563 solver = new FindTilt_563();
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1000);
        assertEquals(expectedTilt(root), solver.findTilt(root));
        assertEquals(0, solver.findTilt(null));
    }

    @Test
    public void testDifferentSolverInstancesAreIndependent() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(-8); root.right = new TreeNode(11);
        assertEquals(expectedTilt(root), new FindTilt_563().findTilt(root));
        assertEquals(expectedTilt(root), new FindTilt_563().findTilt(root));
    }

    @Test
    public void testInputTreeIsNotMutated() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(1); root.right = new TreeNode(9);
        root.left.right = new TreeNode(-2);
        int expected = expectedTilt(root);
        assertEquals(expected, new FindTilt_563().findTilt(root));
        assertEquals(4, root.val);
        assertEquals(1, root.left.val);
        assertEquals(9, root.right.val);
        assertEquals(-2, root.left.right.val);
        assertEquals(expected, new FindTilt_563().findTilt(root));
    }

    @Test
    public void testSmallTreeAgainstIndependentLongPostorderOracle() {
        TreeNode root = new TreeNode(-7);
        root.left = new TreeNode(12); root.right = new TreeNode(-9);
        root.left.left = new TreeNode(-1000); root.left.right = new TreeNode(1000);
        root.right.left = new TreeNode(6);
        long expected = expectedTiltLong(root);
        assertEquals(expected, (long) new FindTilt_563().findTilt(root));
    }

    @Test
    public void testMaximumAllowedDepthWithTenThousandNodes() {
        TreeNode root = new TreeNode(1);
        TreeNode current = root;
        for (int i = 1; i < 10_000; i++) {
            current.left = new TreeNode(1);
            current = current.left;
        }
        assertEquals(expectedTilt(root), new FindTilt_563().findTilt(root));
    }

    @Test
    public void testMaximumNodeCountWithBalancedShape() {
        TreeNode[] nodes = new TreeNode[10_000];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new TreeNode((i % 7) - 3);
        }
        for (int i = 0; i < nodes.length; i++) {
            int left = 2 * i + 1;
            int right = left + 1;
            if (left < nodes.length) {
                nodes[i].left = nodes[left];
            }
            if (right < nodes.length) {
                nodes[i].right = nodes[right];
            }
        }
        long expected = expectedTiltLong(nodes[0]);
        assertEquals(expected, (long) new FindTilt_563().findTilt(nodes[0]));
    }

    @Test
    public void testLargePerfectSubtreesAtValueBounds() {
        TreeNode root = new TreeNode(0);
        root.left = perfectTree(12, 1000);
        root.right = perfectTree(12, -1000);
        long expected = expectedTiltLong(root);
        assertTrue(expected <= Integer.MAX_VALUE);
        assertEquals(expected, (long) new FindTilt_563().findTilt(root));
    }

    @Test
    public void testAllZeroTreeHasZeroTiltAtEveryNode() {
        TreeNode root = perfectTree(7, 0);
        assertEquals(0, new FindTilt_563().findTilt(root));
    }

    @Test
    public void testSubtreeSumsUseValuesNotNodeCount() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1000);
        root.right = new TreeNode(1);
        root.right.left = new TreeNode(1);
        root.right.right = new TreeNode(1);
        assertEquals(expectedTilt(root), new FindTilt_563().findTilt(root));
    }

    @Test
    public void testAsymmetricSparseTreeAgainstOracle() {
        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(2);
        root.left.left.right = new TreeNode(8);
        root.left.left.right.right = new TreeNode(-6);
        root.right = new TreeNode(4);
        assertEquals(expectedTilt(root), new FindTilt_563().findTilt(root));
    }

    private static int expectedTilt(TreeNode root) {
        long expected = expectedTiltLong(root);
        assertTrue(expected >= Integer.MIN_VALUE && expected <= Integer.MAX_VALUE,
                "test oracle must fit the int-returning solution");
        return (int) expected;
    }

    /**
     * Returns [subtree sum, subtree tilt] independently of the production accumulator.
     * The explicit postorder stacks keep the oracle valid for the 10,000-node
     * depth permitted by the problem without consuming the JVM call stack.
     */
    private static long[] postorder(TreeNode node) {
        if (node == null) {
            return new long[]{0, 0};
        }

        Deque<TreeNode> pending = new ArrayDeque<>();
        Deque<TreeNode> postorder = new ArrayDeque<>();
        Map<TreeNode, long[]> results = new IdentityHashMap<>();
        pending.push(node);
        while (!pending.isEmpty()) {
            TreeNode current = pending.pop();
            postorder.push(current);
            if (current.left != null) {
                pending.push(current.left);
            }
            if (current.right != null) {
                pending.push(current.right);
            }
        }
        while (!postorder.isEmpty()) {
            TreeNode current = postorder.pop();
            long[] left = current.left == null ? new long[]{0, 0} : results.get(current.left);
            long[] right = current.right == null ? new long[]{0, 0} : results.get(current.right);
            long subtotal = left[0] + right[0] + current.val;
            long tilt = left[1] + right[1] + Math.abs(left[0] - right[0]);
            results.put(current, new long[]{subtotal, tilt});
        }
        return results.get(node);
    }

    private static long expectedTiltLong(TreeNode root) {
        return postorder(root)[1];
    }

    private static TreeNode perfectTree(int height, int value) {
        if (height == 0) {
            return null;
        }
        TreeNode root = new TreeNode(value);
        root.left = perfectTree(height - 1, value);
        root.right = perfectTree(height - 1, value);
        return root;
    }
}
