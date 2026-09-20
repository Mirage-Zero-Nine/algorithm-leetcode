package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

/** Contract and regression tests for {@link GetAllElements_1305}. */
public class GetAllElements_1305Test {

    private final GetAllElements_1305 solution = new GetAllElements_1305();

    @Test
    public void officialExampleOne() {
        TreeNode first = node(2, node(1), node(4));
        TreeNode second = node(1, node(0), node(3));

        assertEquals(List.of(0, 1, 1, 2, 3, 4), solution.getAllElements(first, second));
    }

    @Test
    public void officialExampleTwo() {
        TreeNode first = node(1, null, node(8));
        TreeNode second = node(8, node(1), null);

        assertEquals(List.of(1, 1, 8, 8), solution.getAllElements(first, second));
    }

    @Test
    public void bothNullRootsProduceEmptyList() {
        assertEquals(List.of(), solution.getAllElements(null, null));
    }

    @Test
    public void nullFirstRootReturnsSecondTreeInOrder() {
        TreeNode second = node(3, node(1), node(5));

        assertEquals(List.of(1, 3, 5), solution.getAllElements(null, second));
    }

    @Test
    public void nullSecondRootReturnsFirstTreeInOrder() {
        TreeNode first = node(2, node(1), node(3));

        assertEquals(List.of(1, 2, 3), solution.getAllElements(first, null));
    }

    @Test
    public void singletonTreesAreOrdered() {
        assertEquals(List.of(-4, 9), solution.getAllElements(new TreeNode(9), new TreeNode(-4)));
    }

    @Test
    public void valuesInterleaveAcrossBothTrees() {
        TreeNode first = balanced(new int[]{1, 4, 7, 10, 13});
        TreeNode second = balanced(new int[]{2, 3, 8, 11, 12});

        assertEquals(List.of(1, 2, 3, 4, 7, 8, 10, 11, 12, 13),
                solution.getAllElements(first, second));
    }

    @Test
    public void tiesAtEveryMergeBoundaryAreRetained() {
        TreeNode first = bst(5, 3, 3, 5, 7, 7, 9);
        TreeNode second = bst(5, 3, 3, 5, 7, 7, 9);

        assertEquals(List.of(3, 3, 3, 3, 5, 5, 5, 5, 7, 7, 7, 7, 9, 9),
                solution.getAllElements(first, second));
    }

    @Test
    public void duplicatesWithinEachTreeAreRetained() {
        TreeNode first = bst(4, 2, 2, 2, 6, 6);
        TreeNode second = bst(4, 4, 1, 1, 7);

        assertMerged(first, second);
    }

    @Test
    public void allValuesFromOneTreeCanPrecedeTheOther() {
        TreeNode first = balanced(new int[]{-8, -6, -4, -2, 0});
        TreeNode second = balanced(new int[]{3, 5, 7, 9});

        assertEquals(List.of(-8, -6, -4, -2, 0, 3, 5, 7, 9),
                solution.getAllElements(first, second));
    }

    @Test
    public void signedValuesAndZeroAreSorted() {
        TreeNode first = bst(0, -10, -3, 4, 12);
        TreeNode second = bst(-1, -20, 2, 2, 10);

        assertEquals(List.of(-20, -10, -3, -1, 0, 2, 2, 4, 10, 12),
                solution.getAllElements(first, second));
    }

    @Test
    public void officialValueBoundsAreIncluded() {
        TreeNode first = node(-100_000, null, node(-99_999));
        TreeNode second = node(100_000, node(99_999), null);

        assertEquals(List.of(-100_000, -99_999, 99_999, 100_000),
                solution.getAllElements(first, second));
    }

    @Test
    public void implementationSupportsJavaIntegerBounds() {
        TreeNode first = node(0, new TreeNode(Integer.MIN_VALUE), new TreeNode(Integer.MAX_VALUE));
        TreeNode second = new TreeNode(Integer.MIN_VALUE);

        assertEquals(List.of(Integer.MIN_VALUE, Integer.MIN_VALUE, 0, Integer.MAX_VALUE),
                solution.getAllElements(first, second));
    }

    @Test
    public void leftSkewedTreesMergeWithoutDependingOnShape() {
        TreeNode first = leftSkewed(5, 1);
        TreeNode second = leftSkewed(5, 6);

        assertEquals(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10),
                solution.getAllElements(first, second));
    }

    @Test
    public void rightSkewedTreesMergeWithoutDependingOnShape() {
        TreeNode first = rightSkewed(1, 5);
        TreeNode second = rightSkewed(6, 10);

        assertEquals(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10),
                solution.getAllElements(first, second));
    }

    @Test
    public void alternatingSparseBranchesAreMerged() {
        TreeNode first = node(8, node(4, null, node(6, node(5), null)),
                node(12, node(10), null));
        TreeNode second = node(7, node(2, null, node(3)),
                node(14, node(13), null));

        assertMerged(first, second);
    }

    @Test
    public void balancedTreesProduceExactlySortedOutput() {
        TreeNode first = balanced(range(-15, 16, 2));
        TreeNode second = balanced(range(-14, 17, 2));

        assertEquals(rangeList(-15, 17), solution.getAllElements(first, second));
    }

    @Test
    public void oneBalancedAndOneSkewedTreeAreMerged() {
        TreeNode first = balanced(range(0, 20));
        TreeNode second = rightSkewed(20, 30);

        assertEquals(rangeList(0, 31), solution.getAllElements(first, second));
    }

    @Test
    public void exactFiveThousandNodeContractInputIsHandled() {
        TreeNode first = balanced(range(0, 2_500));
        TreeNode second = balanced(range(2_500, 5_000));

        assertEquals(rangeList(0, 5_000), solution.getAllElements(first, second));
    }

    @Test
    public void maximumNodesInOneTreeAreHandled() {
        TreeNode first = balanced(range(-100_000, -95_000));

        List<Integer> actual = solution.getAllElements(first, null);
        assertEquals(5_000, actual.size());
        assertEquals(rangeList(-100_000, -95_000), actual);
    }

    @Test
    public void largeLeftAndRightSpinesAreHandledIteratively() {
        TreeNode first = leftSkewed(2_500, 1);
        TreeNode second = rightSkewed(2_501, 5_000);

        assertEquals(rangeList(1, 5_001), solution.getAllElements(first, second));
    }

    @Test
    public void seededTreesMatchIndependentCollectAndSortOracle() {
        for (int seed = 0; seed < 50; seed++) {
            Random random = new Random(0x1305L + seed * 97L);
            TreeNode first = randomBst(random, 1 + seed % 35);
            TreeNode second = randomBst(random, seed % 35);

            assertMerged(first, second);
        }
    }

    @Test
    public void seededTreesWithNegativeAndDuplicateValuesMatchOracle() {
        for (int seed = 0; seed < 30; seed++) {
            Random random = new Random(9_001L + seed);
            TreeNode first = randomBst(random, 80);
            TreeNode second = randomBst(random, 80);

            assertMerged(first, second);
        }
    }

    @Test
    public void resultIsSortedAndContainsExactlyBothInputMultisets() {
        TreeNode first = randomBst(new Random(1305), 100);
        TreeNode second = randomBst(new Random(2024), 100);

        List<Integer> expected = expected(first, second);
        List<Integer> actual = solution.getAllElements(first, second);
        assertEquals(expected, actual);
        assertTrue(isSorted(actual));
        assertEquals(firstNodeCount(first) + firstNodeCount(second), actual.size());
    }

    @Test
    public void inputTreesRemainUnchanged() {
        TreeNode first = bst(8, 4, 2, 6, 12, 10, 14);
        TreeNode second = bst(7, 3, 1, 5, 11, 9, 13);
        Map<TreeNode, NodeState> snapshot = snapshot(first, second);

        assertMerged(first, second);

        assertUnchanged(snapshot);
    }

    @Test
    public void repeatedCallsOnOneInstanceDoNotLeakState() {
        TreeNode first = bst(4, 2, 6);
        TreeNode second = bst(5, 3, 7);
        List<Integer> expected = expected(first, second);

        assertEquals(expected, solution.getAllElements(first, second));
        assertEquals(List.of(), solution.getAllElements(null, null));
        assertEquals(expected, solution.getAllElements(first, second));
        assertEquals(List.of(-2, 0, 9), solution.getAllElements(new TreeNode(0), bst(9, -2)));
    }

    @Test
    public void eachInvocationReturnsAnIndependentList() {
        TreeNode first = bst(2, 1, 3);
        TreeNode second = bst(4, 0, 5);

        List<Integer> original = solution.getAllElements(first, second);
        List<Integer> expected = expected(first, second);
        original.clear();
        original.add(123_456);

        List<Integer> fresh = solution.getAllElements(first, second);
        assertNotSame(original, fresh);
        assertEquals(expected, fresh);
    }

    @Test
    public void separateInstancesProduceTheSameResult() {
        TreeNode first = bst(10, 5, 15, 3, 7, 12, 20);
        TreeNode second = bst(8, 1, 9, 6);
        List<Integer> expected = expected(first, second);

        assertEquals(expected, solution.getAllElements(first, second));
        assertEquals(expected, new GetAllElements_1305().getAllElements(first, second));
    }

    @Test
    public void passingTheSameTreeTwiceDuplicatesEveryNode() {
        TreeNode root = balanced(new int[]{1, 2, 3, 4, 5, 6, 7});

        List<Integer> actual = solution.getAllElements(root, root);
        List<Integer> expected = expected(root, root);
        assertEquals(expected, actual);
        assertEquals(List.of(1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7), actual);
    }

    private void assertMerged(TreeNode first, TreeNode second) {
        assertEquals(expected(first, second), solution.getAllElements(first, second));
    }

    /** Independent iterative oracle: collect both trees, then sort all values. */
    private static List<Integer> expected(TreeNode first, TreeNode second) {
        List<Integer> values = new ArrayList<>();
        collectInOrder(first, values);
        collectInOrder(second, values);
        values.sort(Integer::compare);
        return values;
    }

    private static void collectInOrder(TreeNode root, List<Integer> values) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode current = root;
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            values.add(current.val);
            current = current.right;
        }
    }

    private static boolean isSorted(List<Integer> values) {
        for (int index = 1; index < values.size(); index++) {
            if (values.get(index - 1) > values.get(index)) {
                return false;
            }
        }
        return true;
    }

    private static int firstNodeCount(TreeNode root) {
        int count = 0;
        Deque<TreeNode> stack = new ArrayDeque<>();
        if (root != null) {
            stack.push(root);
        }
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            count++;
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        return count;
    }

    private static TreeNode node(int value) {
        return new TreeNode(value);
    }

    private static TreeNode node(int value, TreeNode left, TreeNode right) {
        TreeNode root = new TreeNode(value);
        root.left = left;
        root.right = right;
        return root;
    }

    private static TreeNode bst(int... values) {
        TreeNode root = null;
        for (int value : values) {
            root = insert(root, value);
        }
        return root;
    }

    private static TreeNode insert(TreeNode root, int value) {
        if (root == null) {
            return new TreeNode(value);
        }
        TreeNode current = root;
        while (true) {
            if (value < current.val) {
                if (current.left == null) {
                    current.left = new TreeNode(value);
                    return root;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new TreeNode(value);
                    return root;
                }
                current = current.right;
            }
        }
    }

    private static TreeNode randomBst(Random random, int size) {
        TreeNode root = null;
        for (int index = 0; index < size; index++) {
            root = insert(root, random.nextInt(401) - 200);
        }
        return root;
    }

    private static TreeNode balanced(int[] values) {
        return balanced(values, 0, values.length - 1);
    }

    private static TreeNode balanced(int[] values, int low, int high) {
        if (low > high) {
            return null;
        }
        int middle = low + (high - low) / 2;
        return node(values[middle], balanced(values, low, middle - 1),
                balanced(values, middle + 1, high));
    }

    private static TreeNode leftSkewed(int count, int firstValue) {
        TreeNode root = null;
        for (int value = firstValue; value < firstValue + count; value++) {
            root = node(value, root, null);
        }
        return root;
    }

    private static TreeNode rightSkewed(int firstValue, int lastValue) {
        TreeNode root = null;
        for (int value = lastValue; value >= firstValue; value--) {
            root = node(value, null, root);
        }
        return root;
    }

    private static int[] range(int startInclusive, int endExclusive) {
        int[] values = new int[endExclusive - startInclusive];
        for (int index = 0; index < values.length; index++) {
            values[index] = startInclusive + index;
        }
        return values;
    }

    private static List<Integer> rangeList(int startInclusive, int endExclusive) {
        List<Integer> values = new ArrayList<>(endExclusive - startInclusive);
        for (int value = startInclusive; value < endExclusive; value++) {
            values.add(value);
        }
        return values;
    }

    private static int[] range(int startInclusive, int endExclusive, int step) {
        int size = (endExclusive - startInclusive + step - 1) / step;
        int[] values = new int[size];
        for (int index = 0; index < size; index++) {
            values[index] = startInclusive + index * step;
        }
        return values;
    }

    private static Map<TreeNode, NodeState> snapshot(TreeNode... roots) {
        Map<TreeNode, NodeState> snapshot = new IdentityHashMap<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        for (TreeNode root : roots) {
            if (root != null) {
                stack.push(root);
            }
        }
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (snapshot.put(node, new NodeState(node)) != null) {
                continue;
            }
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        return snapshot;
    }

    private static void assertUnchanged(Map<TreeNode, NodeState> snapshot) {
        for (Map.Entry<TreeNode, NodeState> entry : snapshot.entrySet()) {
            TreeNode node = entry.getKey();
            NodeState state = entry.getValue();
            assertEquals(state.value, node.val);
            assertSame(state.left, node.left);
            assertSame(state.right, node.right);
        }
    }

    private static final class NodeState {
        private final int value;
        private final TreeNode left;
        private final TreeNode right;

        private NodeState(TreeNode node) {
            value = node.val;
            left = node.left;
            right = node.right;
        }
    }
}
