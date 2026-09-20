package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class CountUnivalSubtrees_250Test {

    private final CountUnivalSubtrees_250 test = new CountUnivalSubtrees_250();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(1); root.right = new TreeNode(5);
        root.left.left = new TreeNode(5); root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(5);
        assertEquals(4, test.countUnivalSubtrees(root));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.countUnivalSubtrees(null));
        assertEquals(1, test.countUnivalSubtrees(new TreeNode(1)));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(1); root.right = new TreeNode(1);
        root.left.left = new TreeNode(1); root.left.right = new TreeNode(1);
        assertEquals(5, test.countUnivalSubtrees(root));
    }

    @Test
    public void testAllSameValues() {
        // Complete binary tree with all same values - all subtrees are unival
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(3); root.right = new TreeNode(3);
        root.left.left = new TreeNode(3); root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(3); root.right.right = new TreeNode(3);
        assertEquals(7, test.countUnivalSubtrees(root));
    }

    @Test
    public void testAllDifferentValues() {
        // No parent can form unival subtree with children, only leaves count
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        assertEquals(3, test.countUnivalSubtrees(root)); // only leaves: 4, 5, 3
    }

    @Test
    public void testLeftSkewedTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(1);
        root.left.left = new TreeNode(1);
        root.left.left.left = new TreeNode(1);
        assertEquals(4, test.countUnivalSubtrees(root));
    }

    @Test
    public void testRightSkewedTree() {
        TreeNode root = new TreeNode(2);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        // leaf 3 is unival, but node 2->3 is not, root 2->2->3 is not
        assertEquals(1, test.countUnivalSubtrees(root));
    }

    @Test
    public void testNegativeValues() {
        TreeNode root = new TreeNode(-1);
        root.left = new TreeNode(-1); root.right = new TreeNode(-1);
        assertEquals(3, test.countUnivalSubtrees(root));
    }

    @Test
    public void testMixedUnivalSubtrees() {
        // root=5, left subtree all 1s, right=5 with right child 5
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(1); root.right = new TreeNode(5);
        root.left.left = new TreeNode(1); root.left.right = new TreeNode(1);
        root.right.right = new TreeNode(5);
        // unival: left.left(1), left.right(1), left(1), right.right(5), right(5) = 5
        assertEquals(5, test.countUnivalSubtrees(root));
    }

    @Test
    public void testSingleChildNotMatching() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        // leaf 2 is unival, root 1 != child 2 so not unival
        assertEquals(1, test.countUnivalSubtrees(root));
    }

    @Test
    public void testGiantTree() {
        // The LeetCode limit is 1000 nodes; every node is then a univalue subtree.
        TreeNode root = buildUniformTree(5, 1000);
        assertEquals(1000, test.countUnivalSubtrees(root));
    }

    private TreeNode buildUniformTree(int val, int nodeCount) {
        if (nodeCount == 0) return null;
        TreeNode node = new TreeNode(val);
        int leftCount = (nodeCount - 1) / 2;
        node.left = buildUniformTree(val, leftCount);
        node.right = buildUniformTree(val, nodeCount - 1 - leftCount);
        return node;
    }

    @Test
    public void testPropertyResultLeqNodeCount() {
        // Property: result <= number of nodes for any tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(1);
        root.left.left = new TreeNode(2); root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(1); root.right.right = new TreeNode(1);
        int result = test.countUnivalSubtrees(root);
        assertTrue(result <= 7); // 7 nodes total
        assertTrue(result >= 0);
    }

    @Test
    public void testZeroValuedNodes() {
        // All zeros - ensure val=0 doesn't cause issues
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(0); root.right = new TreeNode(0);
        assertEquals(3, test.countUnivalSubtrees(root));
    }

    @Test
    public void testChildrenSameButDifferentFromParent() {
        // Both children are 2, parent is 1 -> children are unival leaves, parent is not
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(2);
        assertEquals(2, test.countUnivalSubtrees(root));
    }

    @Test
    public void testIntegerExtremeValues() {
        TreeNode root = new TreeNode(Integer.MAX_VALUE);
        root.left = new TreeNode(Integer.MAX_VALUE);
        root.right = new TreeNode(Integer.MIN_VALUE);
        // leaves are unival, root is not (children differ)
        assertEquals(2, test.countUnivalSubtrees(root));
    }

    @Test
    public void testDeepChainWithMismatchAtBottom() {
        // 1->1->1->1->2: the mismatch at bottom propagates up
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(1);
        root.left.left = new TreeNode(1);
        root.left.left.left = new TreeNode(1);
        root.left.left.left.left = new TreeNode(2);
        // only leaf(2) is unival, all ancestors have mismatching child
        assertEquals(1, test.countUnivalSubtrees(root));
    }

    @Test
    public void testOnlyRightSubtreeIsUnival() {
        // Left subtree has mismatch, right subtree is all same
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(1); root.right = new TreeNode(3);
        root.left.left = new TreeNode(2);
        root.right.left = new TreeNode(3); root.right.right = new TreeNode(3);
        // unival: left.left(2), right.left(3), right.right(3), right(3) = 4
        // left(1) has child 2 != 1, not unival; root has right=3 != 1, not unival
        assertEquals(4, test.countUnivalSubtrees(root));
    }

    @Test
    public void testRootMatchesOneChildOnly() {
        // root=5, left=5, right=3 -> left is unival leaf, right is unival leaf, root not unival
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(5); root.right = new TreeNode(3);
        assertEquals(2, test.countUnivalSubtrees(root));
    }

    @Test
    public void testFullBinaryTreeMixed() {
        //        1
        //      /   \
        //     1     1
        //    / \   / \
        //   1   2 1   1
        // unival: leaves 1,2,1,1 = 4; left(1) has child 2!=1 not unival;
        // right(1) children both 1 -> unival; root left subtree not unival -> root not unival
        // total = 4 (leaves) + 1 (right subtree) = 5
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(1); root.right = new TreeNode(1);
        root.left.left = new TreeNode(1); root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(1); root.right.right = new TreeNode(1);
        assertEquals(5, test.countUnivalSubtrees(root));
    }

    @Test
    public void testOfficialExampleThree() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(5);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(5);

        // The six non-null rooted subtrees are all univalue.
        assertEquals(6, test.countUnivalSubtrees(root));
    }

    @Test
    public void testSparseTreeWithSeveralMissingChildren() {
        TreeNode root = new TreeNode(7);
        root.right = new TreeNode(7);
        root.right.left = new TreeNode(7);
        root.right.left.right = new TreeNode(8);
        root.right.right = new TreeNode(7);

        assertMatchesOracle(root);
    }

    @Test
    public void testDuplicateValuesSeparatedByDifferentAncestors() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.right.right = new TreeNode(3);

        // Equal values in different branches do not make the whole tree univalue.
        assertMatchesOracle(root);
    }

    @Test
    public void testSignedValuesWithinProblemBounds() {
        TreeNode root = new TreeNode(-1000);
        root.left = new TreeNode(-1000);
        root.right = new TreeNode(1000);
        root.left.left = new TreeNode(-1000);
        root.left.right = new TreeNode(-999);

        assertMatchesOracle(root);
    }

    @Test
    public void testNonProblemJavaIntegerBounds() {
        TreeNode root = new TreeNode(Integer.MIN_VALUE);
        root.left = new TreeNode(Integer.MIN_VALUE);
        root.right = new TreeNode(Integer.MAX_VALUE);
        root.left.right = new TreeNode(Integer.MIN_VALUE);

        // TreeNode stores int values, so the class's broader implementation support
        // is also checked independently of the LeetCode [-1000, 1000] contract.
        assertMatchesOracle(root);
    }

    @Test
    public void testMaximumSizeRightSkewedUniformTree() {
        TreeNode root = new TreeNode(-4);
        TreeNode current = root;
        for (int i = 1; i < 1000; i++) {
            current.right = new TreeNode(-4);
            current = current.right;
        }

        assertEquals(1000, test.countUnivalSubtrees(root));
    }

    @Test
    public void testDeepChainWithSeveralMismatches() {
        TreeNode root = new TreeNode(0);
        TreeNode current = root;
        for (int i = 1; i < 30; i++) {
            current.left = new TreeNode(i % 5 == 0 ? 1 : 0);
            current = current.left;
        }

        assertMatchesOracle(root);
    }

    @Test
    public void testIndependentPostorderOracleOnExhaustiveSmallLabels() {
        // Every 7-node shape is fixed, but all 3^7 labelings are checked against
        // an explicit-stack postorder oracle that aggregates value sets.
        for (int encoding = 0; encoding < 2187; encoding++) {
            int remaining = encoding;
            TreeNode root = new TreeNode(labelDigit(remaining % 3));
            remaining /= 3;
            root.left = new TreeNode(labelDigit(remaining % 3));
            remaining /= 3;
            root.right = new TreeNode(labelDigit(remaining % 3));
            remaining /= 3;
            root.left.left = new TreeNode(labelDigit(remaining % 3));
            remaining /= 3;
            root.left.right = new TreeNode(labelDigit(remaining % 3));
            remaining /= 3;
            root.right.left = new TreeNode(labelDigit(remaining % 3));
            remaining /= 3;
            root.right.right = new TreeNode(labelDigit(remaining % 3));

            assertEquals(oracleCountByPostorderValueSets(root), test.countUnivalSubtrees(root),
                    "incorrect count for label encoding " + encoding);
        }
    }

    @Test
    public void testIndependentOracleOnSeededRandomTrees() {
        Random random = new Random(250L);
        for (int caseNumber = 0; caseNumber < 150; caseNumber++) {
            int nodeCount = random.nextInt(41);
            TreeNode root = randomTree(random, nodeCount);
            assertEquals(oracleCountByPostorderValueSets(root), test.countUnivalSubtrees(root),
                    "incorrect count for generated case " + caseNumber);
        }
    }

    @Test
    public void testInputTopologyAndValuesAreUnchanged() {
        TreeNode root = randomTree(new Random(251L), 60);
        List<Integer> before = snapshot(root);

        assertMatchesOracle(root);

        assertEquals(before, snapshot(root));
    }

    @Test
    public void testRepeatedCallsDoNotLeakCountState() {
        TreeNode first = new TreeNode(4);
        first.left = new TreeNode(4);
        TreeNode second = new TreeNode(9);
        second.left = new TreeNode(8);

        assertEquals(oracleCountByPostorderValueSets(first), test.countUnivalSubtrees(first));
        assertEquals(0, test.countUnivalSubtrees(null));
        assertEquals(oracleCountByPostorderValueSets(second), test.countUnivalSubtrees(second));
        assertEquals(oracleCountByPostorderValueSets(first), test.countUnivalSubtrees(first));
    }

    @Test
    public void testIndependentInstancesDoNotShareState() {
        TreeNode root = randomTree(new Random(252L), 75);
        int expected = oracleCountByPostorderValueSets(root);
        CountUnivalSubtrees_250 first = new CountUnivalSubtrees_250();
        CountUnivalSubtrees_250 second = new CountUnivalSubtrees_250();

        assertEquals(expected, first.countUnivalSubtrees(root));
        assertEquals(expected, second.countUnivalSubtrees(root));
    }

    @Test
    public void testResultIsNeverGreaterThanNodeCount() {
        TreeNode root = randomTree(new Random(253L), 100);
        int nodeCount = countNodes(root);
        int result = test.countUnivalSubtrees(root);

        assertTrue(result >= 0);
        assertTrue(result <= nodeCount);
        assertEquals(oracleCountByPostorderValueSets(root), result);
    }

    private void assertMatchesOracle(TreeNode root) {
        assertEquals(oracleCountByPostorderValueSets(root), test.countUnivalSubtrees(root));
    }

    /**
     * Independent postorder oracle: each node accumulates the distinct values in its
     * subtree. It intentionally does not use the production boolean recurrence.
     */
    private int oracleCountByPostorderValueSets(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Deque<TreeNode> forward = new ArrayDeque<>();
        Deque<TreeNode> postorder = new ArrayDeque<>();
        forward.push(root);
        while (!forward.isEmpty()) {
            TreeNode node = forward.pop();
            postorder.push(node);
            if (node.left != null) {
                forward.push(node.left);
            }
            if (node.right != null) {
                forward.push(node.right);
            }
        }

        Map<TreeNode, Set<Integer>> valuesByNode = new IdentityHashMap<>();
        int count = 0;
        while (!postorder.isEmpty()) {
            TreeNode node = postorder.pop();
            Set<Integer> values = new HashSet<>();
            values.add(node.val);
            if (node.left != null) {
                values.addAll(valuesByNode.get(node.left));
            }
            if (node.right != null) {
                values.addAll(valuesByNode.get(node.right));
            }
            valuesByNode.put(node, values);
            if (values.size() == 1) {
                count++;
            }
        }
        return count;
    }

    private int labelDigit(int digit) {
        return digit - 1;
    }

    private TreeNode randomTree(Random random, int nodeCount) {
        if (nodeCount == 0) {
            return null;
        }
        TreeNode root = new TreeNode(random.nextInt(2001) - 1000);
        List<TreeNode> availableParents = new ArrayList<>();
        availableParents.add(root);
        for (int i = 1; i < nodeCount; i++) {
            int parentIndex = random.nextInt(availableParents.size());
            TreeNode parent = availableParents.get(parentIndex);
            TreeNode child = new TreeNode(random.nextInt(2001) - 1000);
            if (parent.left == null && parent.right == null) {
                if (random.nextBoolean()) {
                    parent.left = child;
                } else {
                    parent.right = child;
                }
            } else if (parent.left == null) {
                parent.left = child;
            } else {
                parent.right = child;
            }
            availableParents.add(child);
            if (parent.left != null && parent.right != null) {
                availableParents.remove(parentIndex);
            }
        }
        return root;
    }

    private int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    private List<Integer> snapshot(TreeNode root) {
        List<TreeNode> queue = new ArrayList<>();
        List<Integer> values = new ArrayList<>();
        queue.add(root);
        for (int index = 0; index < queue.size(); index++) {
            TreeNode node = queue.get(index);
            if (node == null) {
                values.add(null);
                continue;
            }
            values.add(node.val);
            queue.add(node.left);
            queue.add(node.right);
        }
        return values;
    }
}
