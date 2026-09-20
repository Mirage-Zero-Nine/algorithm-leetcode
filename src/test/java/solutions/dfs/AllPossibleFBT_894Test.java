package solutions.dfs;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Contract tests for {@link AllPossibleFBT_894}.
 *
 * <p>The expected number of shapes is calculated independently with the
 * Catalan-number formula. Every returned tree is also inspected rather than
 * trusting the count alone: this catches missing children, extra nodes,
 * non-zero values, and duplicate shapes.</p>
 */
public class AllPossibleFBT_894Test {

    private final AllPossibleFBT_894 test = new AllPossibleFBT_894();

    @Test
    public void testLeetCodeExamples() {
        assertForestContract(3, 1);
        assertForestContract(7, 5);
    }

    @Test
    public void testSingleNodeIsTheOnlyTree() {
        List<TreeNode> trees = test.allPossibleFBT(1);

        assertEquals(1, trees.size());
        assertEquals("0(#,#)", shape(trees.get(0)));
        assertForestContract(trees, 1, 1);
    }

    @Test
    public void testThreeNodesHasTwoLeafChildren() {
        List<TreeNode> trees = test.allPossibleFBT(3);

        assertEquals(List.of("0(0(#,#),0(#,#))"), trees.stream().map(this::shape).toList());
        assertForestContract(trees, 3, 1);
    }

    @Test
    public void testFiveNodesContainsBothOrientations() {
        Set<String> expected = Set.of(
                "0(0(#,#),0(0(#,#),0(#,#)))",
                "0(0(0(#,#),0(#,#)),0(#,#))");

        List<TreeNode> trees = test.allPossibleFBT(5);
        assertEquals(expected, new HashSet<>(trees.stream().map(this::shape).toList()));
        assertForestContract(trees, 5, 2);
    }

    @Test
    public void testNineNodesHasFourteenUniqueTrees() {
        assertForestContract(9, 14);
    }

    @Test
    public void testElevenNodesHasFortyTwoUniqueTrees() {
        assertForestContract(11, 42);
    }

    @Test
    public void testThirteenNodesHasOneHundredThirtyTwoUniqueTrees() {
        assertForestContract(13, 132);
    }

    @Test
    public void testFifteenNodesHasFourHundredTwentyNineUniqueTrees() {
        assertForestContract(15, 429);
    }

    @Test
    public void testSeventeenNodesHasOneThousandFourHundredThirtyTrees() {
        assertForestContract(17, 1430);
    }

    @Test
    public void testMaximumLeetCodeInputHasFourThousandEightHundredSixtyTwoTrees() {
        assertForestContract(19, 4862);
    }

    @Test
    public void testAllOddSizesMatchIndependentCatalanOracle() {
        for (int n = 1; n <= 19; n += 2) {
            List<TreeNode> trees = test.allPossibleFBT(n);
            assertEquals(catalan((n - 1) / 2), trees.size(), "n=" + n);
            assertEquals(trees.size(), trees.stream().map(this::shape).distinct().count(), "n=" + n);
        }
    }

    @Test
    public void testEvenZeroIsEmpty() {
        assertTrue(test.allPossibleFBT(0).isEmpty());
    }

    @Test
    public void testEvenTwoIsEmpty() {
        assertTrue(test.allPossibleFBT(2).isEmpty());
    }

    @Test
    public void testEvenFourIsEmpty() {
        assertTrue(test.allPossibleFBT(4).isEmpty());
    }

    @Test
    public void testMaximumEvenInputIsEmpty() {
        assertTrue(test.allPossibleFBT(20).isEmpty());
    }

    @Test
    public void testNegativeOddInputIsEmpty() {
        assertTrue(test.allPossibleFBT(-1).isEmpty());
        assertTrue(test.allPossibleFBT(-3).isEmpty());
    }

    @Test
    public void testNegativeEvenInputIsEmpty() {
        assertTrue(test.allPossibleFBT(Integer.MIN_VALUE).isEmpty());
    }

    @Test
    public void testEveryNodeHasZeroValue() {
        for (int n : new int[]{1, 3, 5, 9}) {
            for (TreeNode root : test.allPossibleFBT(n)) {
                assertAllValuesAreZero(root);
            }
        }
    }

    @Test
    public void testEveryReturnedTreeIsFullAndHasRequestedNodeCount() {
        for (int n : new int[]{1, 3, 7, 11}) {
            for (TreeNode root : test.allPossibleFBT(n)) {
                assertEquals(n, countNodes(root), "n=" + n);
                assertTrue(isFullBinaryTree(root), "n=" + n);
            }
        }
    }

    @Test
    public void testReturnedShapesAreUniqueForRepresentativeSizes() {
        for (int n : new int[]{5, 7, 9, 13}) {
            List<TreeNode> trees = test.allPossibleFBT(n);
            Set<String> shapes = new HashSet<>();
            for (TreeNode root : trees) {
                assertTrue(shapes.add(shape(root)), "duplicate shape for n=" + n);
            }
            assertEquals(trees.size(), shapes.size(), "n=" + n);
        }
    }

    @Test
    public void testRepeatedCallsOnSameSolverDoNotCarryState() {
        Set<String> first = shapesFor(5);
        assertTrue(test.allPossibleFBT(2).isEmpty());
        Set<String> second = shapesFor(5);

        assertEquals(first, second);
        assertEquals(2, second.size());
    }

    @Test
    public void testEmptyResultsAreNonNullForAllUnsupportedSizes() {
        for (int n : new int[]{-10, -1, 0, 2, 4, 6, 20}) {
            List<TreeNode> result = test.allPossibleFBT(n);
            assertNotNull(result, "n=" + n);
            assertTrue(result.isEmpty(), "n=" + n);
        }
    }

    private void assertForestContract(int n, long expectedCount) {
        assertForestContract(test.allPossibleFBT(n), n, expectedCount);
    }

    private void assertForestContract(List<TreeNode> trees, int n, long expectedCount) {
        assertNotNull(trees);
        assertEquals(expectedCount, trees.size());

        Set<String> shapes = new HashSet<>();
        for (TreeNode root : trees) {
            assertNotNull(root);
            assertEquals(n, countNodes(root));
            assertTrue(isFullBinaryTree(root));
            assertAllValuesAreZero(root);
            assertTrue(shapes.add(shape(root)), "duplicate tree shape");
        }
    }

    private Set<String> shapesFor(int n) {
        Set<String> shapes = new HashSet<>();
        for (TreeNode root : test.allPossibleFBT(n)) {
            shapes.add(shape(root));
        }
        return shapes;
    }

    private long catalan(int k) {
        BigInteger numerator = BigInteger.ONE;
        for (int i = 1; i <= k; i++) {
            numerator = numerator.multiply(BigInteger.valueOf(k + i)).divide(BigInteger.valueOf(i));
        }
        return numerator.divide(BigInteger.valueOf(k + 1)).longValueExact();
    }

    private int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    private boolean isFullBinaryTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.left == null && root.right == null) {
            return true;
        }
        return root.left != null && root.right != null
                && isFullBinaryTree(root.left)
                && isFullBinaryTree(root.right);
    }

    private void assertAllValuesAreZero(TreeNode root) {
        if (root == null) {
            return;
        }
        assertEquals(0, root.val);
        assertAllValuesAreZero(root.left);
        assertAllValuesAreZero(root.right);
    }

    private String shape(TreeNode root) {
        if (root == null) {
            return "#";
        }
        return root.val + "(" + shape(root.left) + "," + shape(root.right) + ")";
    }
}
