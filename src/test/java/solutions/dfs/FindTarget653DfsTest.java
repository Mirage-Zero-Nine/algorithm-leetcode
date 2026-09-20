package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

/**
 * Contract tests for the DFS/set approach to LeetCode 653.
 *
 * <p>The expected result is calculated by an independent pair enumeration,
 * rather than by another traversal using complements. This also makes the
 * "two different nodes" requirement explicit.</p>
 */
public class FindTarget653DfsTest {

    private final FindTarget_653 solution = new FindTarget_653();

    @Test
    public void nullTreeHasNoPair() {
        assertMatches(null, 0);
    }

    @Test
    public void singletonCannotReuseItsValue() {
        assertMatches(new TreeNode(5), 10);
    }

    @Test
    public void singletonDoesNotMatchUnrelatedTarget() {
        assertMatches(new TreeNode(-7), 0);
    }

    @Test
    public void twoNodesMatch() {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        assertMatches(root, 3);
    }

    @Test
    public void twoNodesDoNotMatch() {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        assertMatches(root, 4);
    }

    @Test
    public void officialExampleFindsPair() {
        assertMatches(officialTree(), 9);
    }

    @Test
    public void officialExampleRejectsMissingPair() {
        assertMatches(officialTree(), 28);
    }

    @Test
    public void rootAndLeftChildPair() {
        assertMatches(officialTree(), 8);
    }

    @Test
    public void rootAndRightChildPair() {
        assertMatches(officialTree(), 11);
    }

    @Test
    public void twoLeavesPair() {
        assertMatches(officialTree(), 6);
    }

    @Test
    public void targetCanBeNegative() {
        TreeNode root = new TreeNode(-2);
        root.left = new TreeNode(-5);
        root.right = new TreeNode(4);
        assertMatches(root, -7);
    }

    @Test
    public void negativeAndPositiveValuesCanMakeZero() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(-10);
        root.right = new TreeNode(10);
        assertMatches(root, 0);
    }

    @Test
    public void allNegativeTreeHasPair() {
        TreeNode root = new TreeNode(-4);
        root.left = new TreeNode(-8);
        root.right = new TreeNode(-1);
        assertMatches(root, -9);
    }

    @Test
    public void allPositiveTreeHasPair() {
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(3);
        root.right = new TreeNode(12);
        assertMatches(root, 15);
    }

    @Test
    public void validLowerValueBoundaryPair() {
        TreeNode root = new TreeNode(-9_999);
        root.left = new TreeNode(-10_000);
        root.right = new TreeNode(-9_998);
        assertMatches(root, -19_999);
    }

    @Test
    public void validUpperValueBoundaryPair() {
        TreeNode root = new TreeNode(9_999);
        root.left = new TreeNode(9_998);
        root.right = new TreeNode(10_000);
        assertMatches(root, 19_998);
    }

    @Test
    public void minimumTargetHasNoPair() {
        assertMatches(officialTree(), -100_000);
    }

    @Test
    public void maximumTargetHasNoPair() {
        assertMatches(officialTree(), 100_000);
    }

    @Test
    public void targetEqualToTwiceRootStillNeedsAnotherNode() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(-2);
        root.right = new TreeNode(3);
        assertMatches(root, 0);
    }

    @Test
    public void absentPairBetweenExistingValuesIsFalse() {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(4);
        root.right = new TreeNode(20);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(7);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(25);
        assertMatches(root, 18);
    }

    @Test
    public void pairCanBeFoundAfterExploringBothSubtrees() {
        TreeNode root = new TreeNode(50);
        root.left = new TreeNode(25);
        root.right = new TreeNode(75);
        root.left.left = new TreeNode(10);
        root.left.right = new TreeNode(40);
        root.right.left = new TreeNode(60);
        root.right.right = new TreeNode(90);
        assertMatches(root, 100); // 10 + 90
    }

    @Test
    public void leftSkewedTreeFindsDistantPair() {
        TreeNode root = new TreeNode(5_000);
        TreeNode current = root;
        for (int value = 4_999; value >= 4_000; value--) {
            current.left = new TreeNode(value);
            current = current.left;
        }
        assertMatches(root, 9_000);
    }

    @Test
    public void rightSkewedTreeFindsDistantPair() {
        TreeNode root = new TreeNode(-5_000);
        TreeNode current = root;
        for (int value = -4_999; value <= -4_000; value++) {
            current.right = new TreeNode(value);
            current = current.right;
        }
        assertMatches(root, -9_000);
    }

    @Test
    public void balancedTreeFindsSmallestAndLargestPair() {
        assertMatches(buildBalancedBst(-15, 15), 0);
    }

    @Test
    public void balancedTreeRejectsTargetOutsideItsSumRange() {
        assertMatches(buildBalancedBst(-15, 15), 31);
    }

    @Test
    public void exactMaximumNodeCountBalancedTree() {
        TreeNode root = buildBalancedBst(-5_000, 4_999); // 10,000 valid nodes
        assertMatches(root, -1); // -5,000 + 4,999
    }

    @Test
    public void exactMaximumNodeCountTreeHasNoImpossiblePair() {
        TreeNode root = buildBalancedBst(-5_000, 4_999);
        assertMatches(root, 100_000);
    }

    @Test
    public void repeatedCallsDoNotReusePreviousSearchState() {
        TreeNode root = officialTree();
        assertEquals(expectedPairExists(root, 9), solution.findTarget_Set(root, 9));
        assertEquals(expectedPairExists(root, 1), solution.findTarget_Set(root, 1));
        assertEquals(expectedPairExists(root, 13), solution.findTarget_Set(root, 13));
    }

    @Test
    public void inputTreeTopologyAndValuesRemainUnchanged() {
        TreeNode root = officialTree();
        TreeNode left = root.left;
        TreeNode right = root.right;
        TreeNode leftLeft = left.left;
        TreeNode leftRight = left.right;
        TreeNode rightRight = right.right;

        assertMatches(root, 9);

        assertEquals(5, root.val);
        assertEquals(3, left.val);
        assertEquals(6, right.val);
        assertEquals(2, leftLeft.val);
        assertEquals(4, leftRight.val);
        assertEquals(7, rightRight.val);
        assertEquals(left, root.left);
        assertEquals(right, root.right);
        assertEquals(leftLeft, left.left);
        assertEquals(leftRight, left.right);
        assertEquals(rightRight, right.right);
    }

    @Test
    public void freshSolverInstancesHaveIndependentState() {
        TreeNode root = officialTree();
        FindTarget_653 another = new FindTarget_653();
        assertEquals(expectedPairExists(root, 9), solution.findTarget_Set(root, 9));
        assertEquals(expectedPairExists(root, 28), another.findTarget_Set(root, 28));
        assertEquals(expectedPairExists(root, 6), solution.findTarget_Set(root, 6));
    }

    /** Enumerates every distinct node pair; values are added as long to avoid oracle overflow. */
    private void assertMatches(TreeNode root, int target) {
        assertEquals(expectedPairExists(root, target), solution.findTarget_Set(root, target),
                "unexpected result for target " + target);
    }

    private boolean expectedPairExists(TreeNode root, int target) {
        List<Integer> values = new ArrayList<>();
        collectValues(root, values);
        for (int first = 0; first < values.size(); first++) {
            for (int second = first + 1; second < values.size(); second++) {
                if ((long) values.get(first) + values.get(second) == target) {
                    return true;
                }
            }
        }
        return false;
    }

    private void collectValues(TreeNode node, List<Integer> values) {
        if (node == null) {
            return;
        }
        values.add(node.val);
        collectValues(node.left, values);
        collectValues(node.right, values);
    }

    private TreeNode officialTree() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(7);
        return root;
    }

    private TreeNode buildBalancedBst(int low, int high) {
        if (low > high) {
            return null;
        }
        int middle = low + (high - low) / 2;
        TreeNode root = new TreeNode(middle);
        root.left = buildBalancedBst(low, middle - 1);
        root.right = buildBalancedBst(middle + 1, high);
        return root;
    }
}
