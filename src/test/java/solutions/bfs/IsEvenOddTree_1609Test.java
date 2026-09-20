package solutions.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;

/** Tests for the level-parity and within-level ordering contract of Even-Odd Tree. */
public class IsEvenOddTree_1609Test {

    private final IsEvenOddTree_1609 solution = new IsEvenOddTree_1609();

    @Test
    public void officialExampleOneIsValid() {
        TreeNode root = tree(1, 10, 4, 3, null, 7, 9, 12, 8, 6, null, null, 2);
        assertTrue(solution.isEvenOddTree(root));
    }

    @Test
    public void officialExampleTwoRejectsEqualEvenLevelValues() {
        assertFalse(solution.isEvenOddTree(tree(5, 4, 2, 3, 3, 7)));
    }

    @Test
    public void officialExampleThreeRejectsOddValuesOnOddLevel() {
        assertFalse(solution.isEvenOddTree(tree(5, 9, 1, 3, 5, 7)));
    }

    @Test
    public void oneOddRootIsTheSmallestValidTree() {
        assertTrue(solution.isEvenOddTree(new TreeNode(1)));
    }

    @Test
    public void oneEvenRootIsInvalid() {
        assertFalse(solution.isEvenOddTree(new TreeNode(2)));
    }

    @Test
    public void oneChildAtOddLevelCanBeValid() {
        TreeNode root = new TreeNode(11);
        root.left = new TreeNode(10);
        assertTrue(solution.isEvenOddTree(root));
    }

    @Test
    public void oddLevelMustBeStrictlyDecreasing() {
        assertFalse(solution.isEvenOddTree(tree(1, 4, 6)));
    }

    @Test
    public void oddLevelEqualityIsInvalid() {
        assertFalse(solution.isEvenOddTree(tree(1, 4, 4)));
    }

    @Test
    public void oddLevelMustContainOnlyEvenValues() {
        assertFalse(solution.isEvenOddTree(tree(1, 4, 3)));
    }

    @Test
    public void evenLevelMustContainOnlyOddValues() {
        assertFalse(solution.isEvenOddTree(tree(1, 4, 2, 2)));
    }

    @Test
    public void evenLevelDescendingOrderIsInvalid() {
        assertFalse(solution.isEvenOddTree(tree(1, 10, 8, 9, 3)));
    }

    @Test
    public void evenLevelEqualityIsInvalid() {
        assertFalse(solution.isEvenOddTree(tree(1, 10, 8, 3, 3)));
    }

    @Test
    public void violationCanOccurAcrossDifferentParents() {
        TreeNode root = tree(1, 10, 8, 3, 5, 7, 9);
        root.right.left.val = 1; // level 2 is [3, 5, 1, 9], not increasing.
        assertFalse(solution.isEvenOddTree(root));
    }

    @Test
    public void sparseChildrenStillUseLeftToRightLevelOrder() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(10);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(3);
        root.right.right = new TreeNode(5);
        assertTrue(solution.isEvenOddTree(root));
    }

    @Test
    public void sparseCrossParentOrderingViolationIsDetected() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(10);
        root.right = new TreeNode(8);
        root.left.right = new TreeNode(7);
        root.right.left = new TreeNode(5);
        assertFalse(solution.isEvenOddTree(root)); // level 2 is [7, 5].
    }

    @Test
    public void validFourLevelTreeChecksBothDirectionsAtMultipleLevels() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(20);
        root.right = new TreeNode(18);
        root.left.left = new TreeNode(3);
        root.right.right = new TreeNode(9);
        root.left.left.right = new TreeNode(40);
        root.right.right.left = new TreeNode(20);
        root.right.right.right = new TreeNode(18);
        assertTrue(solution.isEvenOddTree(root));
    }

    @Test
    public void historicalLargeValidTreeRemainsValid() {
        TreeNode root = tree(1, 20, 18, 3, 5, 7, 9, 40, 38, 36, 34, 32, 30, 28, 26);
        assertTrue(solution.isEvenOddTree(root));
    }

    @Test
    public void historicalNonIncreasingLevelRemainsRejected() {
        TreeNode root = tree(1, 10, 4, 3, 7, 9, 5);
        assertFalse(solution.isEvenOddTree(root));
    }

    @Test
    public void alternatingSinglePathIsValidAtEveryDepth() {
        TreeNode root = new TreeNode(1);
        TreeNode current = root;
        for (int level = 1; level < 25; level++) {
            current.left = new TreeNode(level % 2 == 1 ? 2 : 3);
            current = current.left;
        }
        assertTrue(solution.isEvenOddTree(root));
    }

    @Test
    public void aDeepPathWithOneWrongParityIsRejectedAtItsExactLevel() {
        TreeNode root = new TreeNode(1);
        TreeNode current = root;
        for (int level = 1; level < 18; level++) {
            current.left = new TreeNode(level == 13 ? 3 : (level % 2 == 1 ? 2 : 5));
            current = current.left;
        }
        assertFalse(solution.isEvenOddTree(root));
    }

    @Test
    public void signedIntegerValuesAreHandledAsIntegersByTheClassContract() {
        assertTrue(solution.isEvenOddTree(tree(-3, -2, -4, -7, -5, -1, 1)));
    }

    @Test
    public void integerBoundaryValuesCanFormAValidTree() {
        TreeNode root = new TreeNode(Integer.MAX_VALUE);
        root.left = new TreeNode(Integer.MAX_VALUE - 1);
        root.right = new TreeNode(Integer.MIN_VALUE + 2);
        root.left.left = new TreeNode(Integer.MIN_VALUE + 1);
        root.left.right = new TreeNode(-1);
        root.right.left = new TreeNode(1);
        root.right.right = new TreeNode(3);
        assertTrue(solution.isEvenOddTree(root));
    }

    @Test
    public void maximumOfficialValueAndLowerOddValuesAreValid() {
        TreeNode root = new TreeNode(999_999);
        root.left = new TreeNode(1_000_000);
        root.right = new TreeNode(999_998);
        root.left.left = new TreeNode(1);
        root.right.right = new TreeNode(3);
        assertTrue(solution.isEvenOddTree(root));
    }

    @Test
    public void independentOracleCoversDeterministicArbitraryTrees() {
        Random random = new Random(1609L);
        for (int i = 0; i < 80; i++) {
            TreeNode root = randomTree(1 + random.nextInt(80), random);
            assertEquals(oracle(root), solution.isEvenOddTree(root), "random tree " + i);
        }
    }

    @Test
    public void independentOracleCoversValidSparseTrees() {
        Random random = new Random(91_609L);
        for (int i = 0; i < 40; i++) {
            TreeNode root = randomTree(1 + random.nextInt(120), random);
            assignValidLevelValues(root);
            assertTrue(oracle(root), "generated tree must satisfy its construction");
            assertTrue(solution.isEvenOddTree(root), "valid sparse tree " + i);
        }
    }

    @Test
    public void repeatedCallsDoNotLeakLevelState() {
        TreeNode valid = tree(1, 10, 8, 3, 5, 7, 9);
        TreeNode invalid = tree(1, 10, 8, 3, 3);
        assertTrue(solution.isEvenOddTree(valid));
        assertFalse(solution.isEvenOddTree(invalid));
        assertTrue(solution.isEvenOddTree(valid));
    }

    @Test
    public void callsOnSeparateTreesAreIndependent() {
        assertTrue(solution.isEvenOddTree(tree(1, 10, 8)));
        assertTrue(solution.isEvenOddTree(tree(5, 4, 2)));
        assertFalse(solution.isEvenOddTree(tree(5, 9, 1)));
    }

    @Test
    public void checkingDoesNotMutateValuesOrTopology() {
        TreeNode root = tree(1, 10, 8, 3, null, 7, 9, null, 6);
        Map<TreeNode, TreeNode[]> before = snapshot(root);
        int[] values = values(root);
        TreeNode sameRoot = root;
        solution.isEvenOddTree(root);
        assertSame(sameRoot, root);
        assertArrayEquals(values, values(root));
        assertSnapshotEquals(before, root);
    }

    @Test
    public void maximumNodeCountBoundaryIsLinearAndValid() {
        final int nodeCount = 100_000;
        TreeNode root = new TreeNode(1);
        TreeNode current = root;
        for (int level = 1; level < nodeCount; level++) {
            current.left = new TreeNode((level & 1) == 1 ? 2 : 1);
            current = current.left;
        }
        assertTrue(solution.isEvenOddTree(root));
    }

    @Test
    public void largeValidTreeCanFailOnlyAtTheLastLevel() {
        TreeNode root = new TreeNode(1);
        TreeNode current = root;
        for (int level = 1; level < 2_000; level++) {
            current.right = new TreeNode((level & 1) == 1 ? 2 : 1);
            current = current.right;
        }
        current.val = 2; // level 1999 is odd and therefore must be even; this remains valid.
        assertTrue(solution.isEvenOddTree(root));
        current.val = 1;
        assertFalse(solution.isEvenOddTree(root));
    }

    private static TreeNode tree(Integer... values) {
        if (values.length == 0 || values[0] == null) {
            throw new IllegalArgumentException("tests require a non-null root");
        }
        TreeNode root = new TreeNode(values[0]);
        Queue<TreeNode> pending = new ArrayDeque<>();
        pending.add(root);
        int index = 1;
        while (!pending.isEmpty() && index < values.length) {
            TreeNode parent = pending.remove();
            if (values[index] != null) {
                parent.left = new TreeNode(values[index]);
                pending.add(parent.left);
            }
            index++;
            if (index < values.length && values[index] != null) {
                parent.right = new TreeNode(values[index]);
                pending.add(parent.right);
            }
            index++;
        }
        if (index < values.length) {
            throw new IllegalArgumentException("orphaned level-order value");
        }
        return root;
    }

    /** Independent level-order reference implementation, deliberately separate from the solution's loop. */
    private static boolean oracle(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        boolean evenLevel = true;
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            for (int count = queue.size(); count > 0; count--) {
                TreeNode node = queue.remove();
                level.add(node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            for (int i = 0; i < level.size(); i++) {
                int value = level.get(i);
                if ((value & 1) != (evenLevel ? 1 : 0)) return false;
                if (i > 0 && (evenLevel ? value <= level.get(i - 1) : value >= level.get(i - 1))) {
                    return false;
                }
            }
            evenLevel = !evenLevel;
        }
        return true;
    }

    private static TreeNode randomTree(int nodeCount, Random random) {
        TreeNode root = new TreeNode(random.nextInt(2_000_001) - 1_000_000);
        List<TreeNode> nodes = new ArrayList<>();
        nodes.add(root);
        for (int i = 1; i < nodeCount; i++) {
            TreeNode child = new TreeNode(random.nextInt(2_000_001) - 1_000_000);
            TreeNode parent;
            do {
                parent = nodes.get(random.nextInt(nodes.size()));
            } while (parent.left != null && parent.right != null);
            if (parent.left == null && (parent.right != null || random.nextBoolean())) {
                parent.left = child;
            } else {
                parent.right = child;
            }
            nodes.add(child);
        }
        return root;
    }

    private static void assignValidLevelValues(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int level = 0;
        while (!queue.isEmpty()) {
            List<TreeNode> nodes = new ArrayList<>();
            for (int count = queue.size(); count > 0; count--) {
                TreeNode node = queue.remove();
                nodes.add(node);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            for (int i = 0; i < nodes.size(); i++) {
                nodes.get(i).val = level % 2 == 0
                        ? 100_001 + level * 100 + i * 2
                        : 900_000 - level * 100 - i * 2;
            }
            level++;
        }
    }

    private static Map<TreeNode, TreeNode[]> snapshot(TreeNode root) {
        Map<TreeNode, TreeNode[]> result = new IdentityHashMap<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            result.put(node, new TreeNode[]{node.left, node.right});
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
        return result;
    }

    private static void assertSnapshotEquals(Map<TreeNode, TreeNode[]> expected, TreeNode root) {
        Map<TreeNode, TreeNode[]> actual = snapshot(root);
        assertEquals(expected.keySet(), actual.keySet());
        for (TreeNode node : expected.keySet()) {
            assertEquals(expected.get(node)[0], actual.get(node)[0]);
            assertEquals(expected.get(node)[1], actual.get(node)[1]);
        }
    }

    private static int[] values(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            result.add(node.val);
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}
