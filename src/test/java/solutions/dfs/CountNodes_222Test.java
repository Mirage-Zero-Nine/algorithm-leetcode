package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.function.IntUnaryOperator;
import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

/** Tests for counting nodes in complete binary trees. */
public class CountNodes_222Test {

    private final CountNodes_222 solution = new CountNodes_222();

    @Test
    public void officialSparseExampleHasSixNodes() {
        TreeNode root = buildCompleteTree(6, i -> i);

        assertEquals(6, solution.countNodes(root));
    }

    @Test
    public void nullTreeHasZeroNodes() {
        assertEquals(0, solution.countNodes(null));
    }

    @Test
    public void singletonTreeHasOneNode() {
        assertEquals(1, solution.countNodes(buildCompleteTree(1, i -> 0)));
    }

    @Test
    public void twoNodeTreeUsesOnlyTheLeftChild() {
        assertEquals(2, solution.countNodes(buildCompleteTree(2, i -> i)));
    }

    @Test
    public void everySmallPerfectTreeHeightHasExpectedCount() {
        for (int height = 1; height <= 8; height++) {
            int expected = (1 << height) - 1;
            assertEquals(expected, solution.countNodes(buildPerfectTree(height)),
                    "height=" + height);
        }
    }

    @Test
    public void largestPerfectTreeWithinProblemLimitDoesNotOverflow() {
        // Height 15 has 32,767 nodes; the next perfect tree has 65,535 nodes,
        // which is outside the problem's 50,000-node contract.
        assertEquals(32_767, solution.countNodes(buildPerfectTree(15)));
    }

    @Test
    public void everyLastLevelCutoffAtHeightFourIsCounted() {
        // Counts 8..15 contain the first through all eight nodes of level 4.
        for (int nodeCount = 8; nodeCount <= 15; nodeCount++) {
            assertEquals(nodeCount, solution.countNodes(buildCompleteTree(nodeCount, i -> i)),
                    "nodeCount=" + nodeCount);
        }
    }

    @Test
    public void everyLastLevelCutoffAtHeightFiveIsCounted() {
        // Counts 16..31 contain every possible cutoff of the fifth level.
        for (int nodeCount = 16; nodeCount <= 31; nodeCount++) {
            assertEquals(nodeCount, solution.countNodes(buildCompleteTree(nodeCount, i -> i)),
                    "nodeCount=" + nodeCount);
        }
    }

    @Test
    public void lastLevelCutoffsAtHigherHeightTransitionsAreCounted() {
        int[] counts = {32, 33, 40, 47, 48, 55, 62, 63, 64, 65, 96, 127, 128, 129};
        for (int nodeCount : counts) {
            assertEquals(nodeCount, solution.countNodes(buildCompleteTree(nodeCount, i -> i)),
                    "nodeCount=" + nodeCount);
        }
    }

    @Test
    public void allSmallCompleteTreeSizesAgreeWithIndependentBreadthFirstOracle() {
        for (int nodeCount = 0; nodeCount <= 256; nodeCount++) {
            TreeNode root = buildCompleteTree(nodeCount, i -> i % 17);

            assertEquals(countByBreadthFirstTraversal(root), solution.countNodes(root),
                    "nodeCount=" + nodeCount);
        }
    }

    @Test
    public void irregularCompleteTreeSizesAgreeWithIndependentOracle() {
        int[] counts = {3, 9, 10, 12, 20, 25, 37, 58, 99, 127, 130, 255, 300, 511, 777};
        for (int nodeCount : counts) {
            TreeNode root = buildCompleteTree(nodeCount, i -> (i * 31) % 50_001);

            assertEquals(countByBreadthFirstTraversal(root), solution.countNodes(root),
                    "nodeCount=" + nodeCount);
        }
    }

    @Test
    public void maximumAllowedNodeCountIsReturned() {
        TreeNode root = buildCompleteTree(50_000, i -> i);

        assertEquals(50_000, solution.countNodes(root));
    }

    @Test
    public void maximumAllowedCountWithAllDuplicateValuesIsReturned() {
        TreeNode root = buildCompleteTree(50_000, i -> 42);

        assertEquals(50_000, solution.countNodes(root));
    }

    @Test
    public void zeroAndMaximumAllowedValuesDoNotAffectCount() {
        TreeNode root = buildCompleteTree(2_047, i -> (i & 1) == 0 ? 0 : 50_000);

        assertEquals(2_047, solution.countNodes(root));
    }

    @Test
    public void duplicateValuesAtEveryPositionDoNotAffectCount() {
        TreeNode root = buildCompleteTree(63, i -> 7);

        assertEquals(63, solution.countNodes(root));
    }

    @Test
    public void valuesInReverseOrderDoNotAffectCount() {
        TreeNode root = buildCompleteTree(1_023, i -> 1_023 - i);

        assertEquals(1_023, solution.countNodes(root));
    }

    @Test
    public void repeatedCallsOnTheSameTreeAreIndependent() {
        TreeNode root = buildCompleteTree(4_095, i -> i);

        assertEquals(4_095, solution.countNodes(root));
        assertEquals(4_095, solution.countNodes(root));
        root.val = 50_000;
        assertEquals(4_095, solution.countNodes(root));
    }

    @Test
    public void oneSolutionInstanceCanCountDifferentTreesSequentially() {
        assertEquals(6, solution.countNodes(buildCompleteTree(6, i -> i)));
        assertEquals(31, solution.countNodes(buildCompleteTree(31, i -> 0)));
        assertEquals(0, solution.countNodes(null));
        assertEquals(127, solution.countNodes(buildCompleteTree(127, i -> 50_000)));
    }

    @Test
    public void countingDoesNotMutateTreeTopologyOrValues() {
        TreeNode root = buildCompleteTree(15, i -> i * 2);
        TreeNode originalLeft = root.left;
        TreeNode originalRight = root.right;
        int originalRootValue = root.val;
        int originalLeftValue = root.left.val;

        assertEquals(15, solution.countNodes(root));

        assertSame(originalLeft, root.left);
        assertSame(originalRight, root.right);
        assertEquals(originalRootValue, root.val);
        assertEquals(originalLeftValue, root.left.val);
    }

    @Test
    public void completeTreesAtEachPerfectToPartialTransitionAreCounted() {
        int[] counts = {1, 2, 3, 4, 7, 8, 15, 16, 31, 32, 63, 64, 127, 128,
                255, 256, 511, 512, 1_023, 1_024, 2_047, 2_048, 4_095, 4_096};
        for (int nodeCount : counts) {
            assertEquals(nodeCount, solution.countNodes(buildCompleteTree(nodeCount, i -> i % 2)),
                    "nodeCount=" + nodeCount);
        }
    }

    @Test
    public void exactLastLevelOneNodeCasesAcrossHeightsAreCounted() {
        int[] counts = {2, 4, 8, 16, 32, 64, 128, 256, 512, 1_024, 2_048, 4_096};
        for (int nodeCount : counts) {
            assertEquals(nodeCount, solution.countNodes(buildCompleteTree(nodeCount, i -> i)));
        }
    }

    @Test
    public void exactLastLevelFullCasesAcrossHeightsAreCounted() {
        int[] counts = {3, 7, 15, 31, 63, 127, 255, 511, 1_023, 2_047, 4_095};
        for (int nodeCount : counts) {
            assertEquals(nodeCount, solution.countNodes(buildCompleteTree(nodeCount, i -> i)));
        }
    }

    @Test
    public void independentOracleChecksMaximumTree() {
        TreeNode root = buildCompleteTree(50_000, i -> (i * 17) % 50_001);

        assertEquals(countByBreadthFirstTraversal(root), solution.countNodes(root));
    }

    @Test
    public void smallTreesWithBoundaryAndDuplicateValuesAgreeWithOracle() {
        int[] values = {0, 50_000, 0, 50_000, 50_000, 0};
        for (int nodeCount = 0; nodeCount <= values.length; nodeCount++) {
            TreeNode root = buildCompleteTree(nodeCount, i -> values[(i - 1) % values.length]);

            assertEquals(countByBreadthFirstTraversal(root), solution.countNodes(root),
                    "nodeCount=" + nodeCount);
        }
    }

    @Test
    public void partialLastLevelContainsExactlyTheRequestedNodes() {
        TreeNode root = buildCompleteTree(30, i -> i);

        assertEquals(30, solution.countNodes(root));
        assertEquals(30, countByBreadthFirstTraversal(root));
        assertNull(root.right.right.right.right);
    }

    private int countByBreadthFirstTraversal(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int count = 0;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            count++;
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return count;
    }

    private TreeNode buildPerfectTree(int height) {
        if (height == 0) {
            return null;
        }
        return buildCompleteTree((1 << height) - 1, i -> i);
    }

    private TreeNode buildCompleteTree(int nodeCount, IntUnaryOperator valueForIndex) {
        if (nodeCount == 0) {
            return null;
        }

        TreeNode[] nodes = new TreeNode[nodeCount + 1];
        for (int index = 1; index <= nodeCount; index++) {
            nodes[index] = new TreeNode(valueForIndex.applyAsInt(index));
        }
        for (int index = 1; index <= nodeCount; index++) {
            int leftIndex = index * 2;
            int rightIndex = leftIndex + 1;
            if (leftIndex <= nodeCount) {
                nodes[index].left = nodes[leftIndex];
            }
            if (rightIndex <= nodeCount) {
                nodes[index].right = nodes[rightIndex];
            }
        }
        return nodes[1];
    }
}
