package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

/**
 * Contract tests for the set-backed DFS solution to LeetCode 653, Two Sum IV.
 *
 * <p>The expected value is computed independently by collecting and sorting the
 * node values, then using two pointers and a {@code long} sum. Thus the oracle
 * does not reproduce the implementation's complement lookup, and it explicitly
 * requires two distinct nodes.</p>
 */
public class FindTarget_653Test {

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
    public void singletonDoesNotMatchAnotherTarget() {
        assertMatches(new TreeNode(-7), 0);
    }

    @Test
    public void twoNodesCanMakeTarget() {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        assertMatches(root, 3);
    }

    @Test
    public void twoNodesCanRejectTarget() {
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
    public void rootAndLeftChildCanMakeTarget() {
        assertMatches(officialTree(), 8);
    }

    @Test
    public void rootAndRightChildCanMakeTarget() {
        assertMatches(officialTree(), 11);
    }

    @Test
    public void twoLeavesCanMakeTarget() {
        assertMatches(officialTree(), 6);
    }

    @Test
    public void negativeTargetAndAllNegativeValuesAreSupported() {
        TreeNode root = new TreeNode(-2);
        root.left = new TreeNode(-5);
        root.right = new TreeNode(4);
        assertMatches(root, -7);
    }

    @Test
    public void oppositeSignsCanMakeZero() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(-10);
        root.right = new TreeNode(10);
        assertMatches(root, 0);
    }

    @Test
    public void allPositiveValuesAreSupported() {
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(3);
        root.right = new TreeNode(12);
        assertMatches(root, 15);
    }

    @Test
    public void officialNodeValueLowerBoundaryCanMakePair() {
        TreeNode root = new TreeNode(-9_999);
        root.left = new TreeNode(-10_000);
        root.right = new TreeNode(-9_998);
        assertMatches(root, -19_999);
    }

    @Test
    public void officialNodeValueUpperBoundaryCanMakePair() {
        TreeNode root = new TreeNode(9_999);
        root.left = new TreeNode(9_998);
        root.right = new TreeNode(10_000);
        assertMatches(root, 19_998);
    }

    @Test
    public void targetBoundariesCanHaveNoPair() {
        TreeNode root = officialTree();
        assertMatches(root, -100_000);
        assertMatches(root, 100_000);
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
    public void pairMayBeAcrossBothSubtrees() {
        TreeNode root = new TreeNode(50);
        root.left = new TreeNode(25);
        root.right = new TreeNode(75);
        root.left.left = new TreeNode(10);
        root.left.right = new TreeNode(40);
        root.right.left = new TreeNode(60);
        root.right.right = new TreeNode(90);
        assertMatches(root, 100);
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
    public void balancedTreeCanRejectOutsideSumRange() {
        assertMatches(buildBalancedBst(-15, 15), 31);
    }

    @Test
    public void maximumNodeCountBalancedTreeFindsPair() {
        assertMatches(buildBalancedBst(-5_000, 4_999), -1);
    }

    @Test
    public void maximumNodeCountBalancedTreeRejectsImpossibleTarget() {
        assertMatches(buildBalancedBst(-5_000, 4_999), 100_000);
    }

    @Test
    public void maximumNodeCountRightSkewedTreeFindsPair() {
        TreeNode root = new TreeNode(-10_000);
        TreeNode current = root;
        for (int value = -9_999; value <= -1; value++) {
            current.right = new TreeNode(value);
            current = current.right;
        }
        assertMatches(root, -10_001); // -10,000 + -1, the distant pair
    }

    @Test
    public void repeatedCallsDoNotShareVisitedValues() {
        TreeNode root = officialTree();
        assertMatches(root, 9);
        assertMatches(root, 1);
        assertMatches(root, 13);
    }

    @Test
    public void inputTopologyAndValuesRemainUnchanged() {
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

    @Test
    public void implementationSupportsIntegerExtremePairWithoutOverflow() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(Integer.MIN_VALUE);
        root.right = new TreeNode(Integer.MAX_VALUE);
        assertMatches(root, -1);
    }

    @Test
    public void integerOverflowMustNotTurnAnOutOfRangeSumIntoMatch() {
        TreeNode root = new TreeNode(-2_147_483_647);
        root.left = new TreeNode(Integer.MIN_VALUE);
        assertMatches(root, 1);
    }

    private void assertMatches(TreeNode root, int target) {
        assertEquals(expectedPairExists(root, target), solution.findTarget_Set(root, target),
                "unexpected result for target " + target);
    }

    /** Independent O(n log n) oracle; uses long arithmetic and distinct indexes. */
    private boolean expectedPairExists(TreeNode root, int target) {
        List<Integer> values = new ArrayList<>();
        collectValues(root, values);
        values.sort(Integer::compare);
        int low = 0;
        int high = values.size() - 1;
        while (low < high) {
            long sum = (long) values.get(low) + values.get(high);
            if (sum == target) {
                return true;
            }
            if (sum < target) {
                low++;
            } else {
                high--;
            }
        }
        return false;
    }

    private void collectValues(TreeNode node, List<Integer> values) {
        // Keep the independent oracle iterative: a valid 10,000-node BST may be
        // completely skewed, and recursive collection would overflow the test
        // JVM stack before the solution is exercised.
        if (node == null) {
            return;
        }
        Deque<TreeNode> pending = new ArrayDeque<>();
        pending.push(node);
        while (!pending.isEmpty()) {
            TreeNode current = pending.pop();
            values.add(current.val);
            if (current.right != null) {
                pending.push(current.right);
            }
            if (current.left != null) {
                pending.push(current.left);
            }
        }
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
