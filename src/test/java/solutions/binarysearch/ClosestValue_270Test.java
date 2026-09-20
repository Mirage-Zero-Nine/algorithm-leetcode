package solutions.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

/** Tests for the BST closest-value search, including its documented tie rule. */
public class ClosestValue_270Test {

    private final ClosestValue_270 solution = new ClosestValue_270();

    @Test
    public void officialExample() {
        assertClosest(balanced(1, 2, 3, 4, 5), 3.714286);
    }

    @Test
    public void singletonTree() {
        assertClosest(new TreeNode(1), 4.428571);
    }

    @Test
    public void exactMatchAtRoot() {
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(3);
        root.right = new TreeNode(10);
        assertEquals(8, solution.closestValue(root, 8.0));
    }

    @Test
    public void exactMatchAtInternalNode() {
        assertEquals(7, solution.closestValue(balanced(1, 3, 5, 7, 9, 11, 13), 7.0));
    }

    @Test
    public void tieChoosesSmallerWhenSuccessorIsVisitedFirst() {
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        assertEquals(4, solution.closestValue(root, 5.0));
    }

    @Test
    public void tieChoosesSmallerWhenPredecessorIsVisitedFirst() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        assertEquals(4, solution.closestValue(root, 5.0));
    }

    @Test
    public void targetJustBelowAnInteger() {
        assertClosest(balanced(0, 1, 2, 3, 4), 2.499999999);
    }

    @Test
    public void targetJustAboveAnInteger() {
        assertClosest(balanced(0, 1, 2, 3, 4), 2.500000001);
    }

    @Test
    public void targetBelowMinimum() {
        assertClosest(balanced(10, 20, 30, 40), -1_000_000_000.0);
    }

    @Test
    public void targetAboveMaximum() {
        assertClosest(balanced(10, 20, 30, 40), 1_000_000_000.0);
    }

    @Test
    public void negativeNodeValuesAndTarget() {
        assertClosest(balanced(-10, -5, -2, 0, 4), -3.6);
    }

    @Test
    public void mixedSignedValues() {
        assertClosest(balanced(-100, -25, -1, 17, 63, 100), 8.5);
    }

    @Test
    public void officialValueBoundaries() {
        TreeNode root = balanced(0, 1, 999_999_999, 1_000_000_000);
        assertClosest(root, 999_999_999.6);
        assertClosest(root, -1_000_000_000.0);
    }

    @Test
    public void JavaIntegerBoundaries() {
        TreeNode root = balanced(Integer.MIN_VALUE, -1, 0, 1, Integer.MAX_VALUE);
        assertClosest(root, (double) Integer.MIN_VALUE + 0.25);
        assertClosest(root, (double) Integer.MAX_VALUE - 0.25);
    }

    @Test
    public void decimalTargetsAcrossGaps() {
        TreeNode root = balanced(-20, -7, 0, 6, 25);
        assertClosest(root, -13.5);
        assertClosest(root, -3.5);
        assertClosest(root, 15.5);
    }

    @Test
    public void leftSkewedTree() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(2);
        root.left.left.left.left = new TreeNode(1);
        assertClosest(root, 1.7);
    }

    @Test
    public void rightSkewedTree() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(4);
        root.right.right.right.right = new TreeNode(5);
        assertClosest(root, 3.3);
    }

    @Test
    public void alternatingSkewedTree() {
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(4);
        root.left.right = new TreeNode(6);
        root.left.right.left = new TreeNode(5);
        root.right = new TreeNode(12);
        root.right.left = new TreeNode(10);
        root.right.left.right = new TreeNode(11);
        assertClosest(root, 9.2);
    }

    @Test
    public void balancedTreeSeveralTargetsAgainstOracle() {
        TreeNode root = balanced(-31, -20, -12, -5, 0, 4, 9, 18, 27, 40, 55);
        for (double target : new double[] {-100.0, -15.5, -5.0, 2.0, 8.5, 30.0, 100.0}) {
            assertClosest(root, target);
        }
    }

    @Test
    public void midpointTiesAcrossMultipleGapsChooseLowerValue() {
        int[] values = {-40, -10, 3, 18, 75};
        TreeNode root = balanced(values);
        assertEquals(-40, solution.closestValue(root, -25.0));
        assertEquals(-10, solution.closestValue(root, -3.5));
        assertEquals(3, solution.closestValue(root, 10.5));
        assertEquals(18, solution.closestValue(root, 46.5));
    }

    @Test
    public void exhaustiveSmallBstOracle() {
        int[] universe = {-3, -1, 0, 2, 5};
        for (int mask = 1; mask < (1 << universe.length); mask++) {
            List<Integer> values = new ArrayList<>();
            for (int i = 0; i < universe.length; i++) {
                if ((mask & (1 << i)) != 0) {
                    values.add(universe[i]);
                }
            }
            TreeNode root = balanced(values.stream().mapToInt(Integer::intValue).toArray());
            for (int targetTimesTwo = -16; targetTimesTwo <= 20; targetTimesTwo++) {
                assertClosest(root, targetTimesTwo / 2.0);
            }
        }
    }

    @Test
    public void maximumOfficialNodeCountBalancedTree() {
        int[] values = new int[10_000];
        for (int i = 0; i < values.length; i++) {
            values[i] = i;
        }
        TreeNode root = balanced(values);
        assertEquals(9_999, solution.closestValue(root, 10_000.0));
        assertEquals(5_000, solution.closestValue(root, 5_000.49));
    }

    @Test
    public void sameInstanceCanBeReusedWithoutStateLeakage() {
        TreeNode first = balanced(1, 4, 9);
        TreeNode second = balanced(-10, -3, 20);
        assertClosest(first, 6.0);
        assertClosest(second, -8.0);
        assertClosest(first, 8.0);
    }

    @Test
    public void inputTreeTopologyAndValuesRemainUnchanged() {
        TreeNode root = balanced(-8, -2, 0, 5, 13, 21);
        List<Integer> before = inOrder(root);
        TreeNode left = root.left;
        TreeNode right = root.right;
        solution.closestValue(root, 4.25);
        assertIterableEquals(before, inOrder(root));
        assertEquals(left, root.left);
        assertEquals(right, root.right);
    }

    @Test
    public void independentFreshTreesDoNotShareResults() {
        TreeNode first = balanced(0, 10, 20);
        TreeNode second = balanced(100, 110, 120);
        assertEquals(10, solution.closestValue(first, 9.9));
        assertEquals(110, solution.closestValue(second, 109.9));
        assertEquals(0, solution.closestValue(first, -100.0));
    }

    private void assertClosest(TreeNode root, double target) {
        assertEquals(expectedClosest(root, target), solution.closestValue(root, target),
                "unexpected closest value for target " + target);
    }

    private static int expectedClosest(TreeNode root, double target) {
        int best = root.val;
        double bestDistance = Math.abs(target - best);
        for (int value : inOrder(root)) {
            double distance = Math.abs(target - value);
            if (distance < bestDistance || (distance == bestDistance && value < best)) {
                best = value;
                bestDistance = distance;
            }
        }
        return best;
    }

    private static List<Integer> inOrder(TreeNode root) {
        List<Integer> values = new ArrayList<>();
        addInOrder(root, values);
        return values;
    }

    private static void addInOrder(TreeNode node, List<Integer> values) {
        if (node == null) {
            return;
        }
        addInOrder(node.left, values);
        values.add(node.val);
        addInOrder(node.right, values);
    }

    private static TreeNode balanced(int... values) {
        int[] sorted = values.clone();
        Arrays.sort(sorted);
        return balancedSorted(sorted, 0, sorted.length - 1);
    }

    private static TreeNode balancedSorted(int[] values, int low, int high) {
        if (low > high) {
            return null;
        }
        int middle = low + (high - low) / 2;
        TreeNode node = new TreeNode(values[middle]);
        node.left = balancedSorted(values, low, middle - 1);
        node.right = balancedSorted(values, middle + 1, high);
        return node;
    }
}
