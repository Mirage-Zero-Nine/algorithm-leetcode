package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.IdentityHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

/** Tests for the minimum adjacent-edge coin transfers in a binary tree. */
public class DistributeCoins_979Test {

    @Test
    public void officialExampleCoinsAtRoot() {
        TreeNode root = node(3, node(0), node(0));
        assertEquals(2, new DistributeCoins_979().distributeCoins(root));
    }

    @Test
    public void officialExampleCoinsAtInternalNode() {
        TreeNode root = node(0, node(3), node(0));
        assertEquals(3, new DistributeCoins_979().distributeCoins(root));
    }

    @Test
    public void singletonAlreadyBalancedRequiresNoMoves() {
        assertEquals(0, new DistributeCoins_979().distributeCoins(node(1)));
    }

    @Test
    public void nullRootIsHandledAsEmptyTree() {
        assertEquals(0, new DistributeCoins_979().distributeCoins(null));
    }

    @Test
    public void alreadyBalancedCompleteTreeRequiresNoMoves() {
        TreeNode root = node(1, node(1, node(1), node(1)), node(1, node(1), node(1)));
        assertMatchesOracle(root);
    }

    @Test
    public void excessAtRootFlowsToBothChildren() {
        assertMatchesOracle(node(3, node(0), node(0)));
    }

    @Test
    public void excessAtLeftLeafFlowsUpOneEdge() {
        assertMatchesOracle(node(0, node(2), null));
    }

    @Test
    public void excessAtRightLeafFlowsUpOneEdge() {
        assertMatchesOracle(node(0, null, node(2)));
    }

    @Test
    public void deficitAtRootIsSuppliedByBothLeaves() {
        assertMatchesOracle(node(0, node(2), node(2)));
    }

    @Test
    public void surplusAtDeepLeftLeafCrossesEveryAncestor() {
        TreeNode root = node(0, node(0, node(0, node(4), null), null), null);
        assertEquals(6, edgeFlowMoves(root));
        assertMatchesOracle(root);
    }

    @Test
    public void surplusAtDeepRightLeafCrossesEveryAncestor() {
        TreeNode root = node(0, null, node(0, null, node(0, null, node(4))));
        assertMatchesOracle(root);
    }

    @Test
    public void balancedSubtreeCanHaveZeroNetFlowButInternalMoves() {
        TreeNode root = node(1, node(0, node(3), node(0)), node(1));
        // The left subtree has exactly its three required coins, but its children
        // still exchange a total of three coins across their own edges.
        assertEquals(3, edgeFlowMoves(root));
        assertMatchesOracle(root);
    }

    @Test
    public void sparseTreeUsesOnlyExistingEdges() {
        TreeNode root = node(1, node(0, null, node(2)), null);
        assertMatchesOracle(root);
    }

    @Test
    public void alternatingLeftRightPathUsesAllPathEdges() {
        TreeNode root = node(0, node(0, null, node(0, node(0), null)), null);
        root.left.right.left.val = 4;
        assertMatchesOracle(root);
    }

    @Test
    public void duplicateCoinCountsAndZeroValuesAreCountedIndependently() {
        TreeNode root = node(0, node(2, node(0), node(2)), node(1));
        assertMatchesOracle(root);
    }

    @Test
    public void maximumLegalCoinCountAtOneNode() {
        int nodeCount = 100;
        TreeNode root = chain(nodeCount, true);
        assertEquals(nodeCount * (nodeCount - 1) / 2, edgeFlowMoves(root));
        assertMatchesOracle(root);
    }

    @Test
    public void maximumLegalNodeCountBalancedShape() {
        TreeNode root = completeTree(100);
        root.val = 100;
        assertMatchesOracle(root);
    }

    @Test
    public void allCoinsAtAnInternalNodeHaveBothUpwardAndDownwardFlows() {
        TreeNode root = completeTree(15);
        TreeNode internal = root.left;
        internal.val = 15;
        assertMatchesOracle(root);
    }

    @Test
    public void inputTopologyAndValuesRemainUnchanged() {
        TreeNode root = node(0, node(3, node(0), null), node(0));
        String before = snapshot(root);
        int expected = edgeFlowMoves(root);
        assertEquals(expected, new DistributeCoins_979().distributeCoins(root));
        assertEquals(before, snapshot(root));
    }

    @Test
    public void sameInstanceCanBeReusedForIndependentTrees() {
        DistributeCoins_979 solution = new DistributeCoins_979();
        TreeNode first = node(3, node(0), node(0));
        TreeNode second = node(0, node(3), node(0));
        assertEquals(edgeFlowMoves(first), solution.distributeCoins(first));
        assertEquals(edgeFlowMoves(second), solution.distributeCoins(second));
    }

    @Test
    public void nullCallDoesNotContaminateTheNextInvocation() {
        DistributeCoins_979 solution = new DistributeCoins_979();
        assertEquals(0, solution.distributeCoins(null));
        TreeNode root = node(0, node(3), node(0));
        assertEquals(edgeFlowMoves(root), solution.distributeCoins(root));
    }

    @Test
    public void repeatedCallOnSameTreeReturnsSameAnswer() {
        DistributeCoins_979 solution = new DistributeCoins_979();
        TreeNode root = node(0, node(3), node(0));
        int expected = edgeFlowMoves(root);
        assertEquals(expected, solution.distributeCoins(root));
        assertEquals(expected, solution.distributeCoins(root));
    }

    @Test
    public void freshInstancesDoNotShareMoveState() {
        TreeNode root = node(0, node(3), node(0));
        assertEquals(edgeFlowMoves(root), new DistributeCoins_979().distributeCoins(root));
        assertEquals(0, new DistributeCoins_979().distributeCoins(node(1)));
    }

    @Test
    public void exhaustiveSmallCoinDistributionsMatchIndependentEdgeOracle() {
        // Every distribution of three coins over a fixed five-node sparse tree.
        for (int first = 0; first <= 3; first++) {
            for (int second = 0; second <= 3 - first; second++) {
                for (int third = 0; third <= 3 - first - second; third++) {
                    int fourth = 3 - first - second - third;
                    TreeNode root = node(first, node(second, node(third), null), node(fourth));
                    assertMatchesOracle(root);
                }
            }
        }
    }

    @Test
    public void seededRandomValidTreesMatchIndependentEdgeOracle() {
        Random random = new Random(979_20260919L);
        for (int trial = 0; trial < 150; trial++) {
            int nodeCount = 1 + random.nextInt(100);
            TreeNode root = randomTree(nodeCount, random);
            assertMatchesOracle(root);
        }
    }

    @Test
    public void everyNodeCanBeTheSoleCoinSourceOnASevenNodeTree() {
        for (int source = 0; source < 7; source++) {
            TreeNode root = completeTree(7);
            nodeAtPreorder(root, source).val = 7;
            assertMatchesOracle(root);
        }
    }

    private static void assertMatchesOracle(TreeNode root) {
        int expected = edgeFlowMoves(root);
        assertEquals(expected, new DistributeCoins_979().distributeCoins(root), snapshot(root));
    }

    /** Independent iterative edge-flow oracle: each edge carries its child's subtree surplus. */
    private static int edgeFlowMoves(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Map<TreeNode, TreeNode> parent = new IdentityHashMap<>();
        List<TreeNode> preorder = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            preorder.add(current);
            if (current.right != null) {
                parent.put(current.right, current);
                stack.push(current.right);
            }
            if (current.left != null) {
                parent.put(current.left, current);
                stack.push(current.left);
            }
        }

        Map<TreeNode, Integer> balance = new IdentityHashMap<>();
        for (TreeNode current : preorder) {
            balance.put(current, current.val - 1);
        }
        int moves = 0;
        for (int index = preorder.size() - 1; index > 0; index--) {
            TreeNode current = preorder.get(index);
            int flow = balance.get(current);
            moves += Math.abs(flow);
            TreeNode ancestor = parent.get(current);
            balance.put(ancestor, balance.get(ancestor) + flow);
        }
        return moves;
    }

    private static TreeNode node(int value) {
        return new TreeNode(value);
    }

    private static TreeNode node(int value, TreeNode left, TreeNode right) {
        TreeNode result = new TreeNode(value);
        result.left = left;
        result.right = right;
        return result;
    }

    private static TreeNode chain(int size, boolean coinsAtTail) {
        TreeNode root = new TreeNode(0);
        TreeNode current = root;
        for (int index = 1; index < size; index++) {
            current.left = new TreeNode(0);
            current = current.left;
        }
        if (!coinsAtTail) {
            root.val = 1;
            for (TreeNode node = root.left; node != null; node = node.left) {
                node.val = 1;
            }
        } else {
            current.val = size;
        }
        return root;
    }

    private static TreeNode completeTree(int size) {
        List<TreeNode> nodes = new ArrayList<>(size);
        for (int index = 0; index < size; index++) {
            nodes.add(new TreeNode(0));
        }
        for (int index = 1; index < size; index++) {
            TreeNode parent = nodes.get((index - 1) / 2);
            if ((index & 1) == 1) {
                parent.left = nodes.get(index);
            } else {
                parent.right = nodes.get(index);
            }
        }
        return nodes.get(0);
    }

    private static TreeNode randomTree(int size, Random random) {
        TreeNode root = new TreeNode(0);
        List<TreeNode> available = new ArrayList<>();
        List<TreeNode> nodes = new ArrayList<>();
        available.add(root);
        nodes.add(root);
        for (int index = 1; index < size; index++) {
            int parentIndex = random.nextInt(available.size());
            TreeNode parent = available.get(parentIndex);
            TreeNode child = new TreeNode(0);
            if (parent.left == null && (parent.right != null || random.nextBoolean())) {
                parent.left = child;
            } else {
                parent.right = child;
            }
            nodes.add(child);
            if (parent.left != null && parent.right != null) {
                available.set(parentIndex, available.get(available.size() - 1));
                available.remove(available.size() - 1);
            }
            available.add(child);
        }
        for (int coin = 0; coin < size; coin++) {
            nodes.get(random.nextInt(size)).val++;
        }
        return root;
    }

    private static TreeNode nodeAtPreorder(TreeNode root, int target) {
        List<TreeNode> preorder = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            preorder.add(current);
            if (current.right != null) {
                stack.push(current.right);
            }
            if (current.left != null) {
                stack.push(current.left);
            }
        }
        return preorder.get(target);
    }

    private static String snapshot(TreeNode root) {
        if (root == null) {
            return "#";
        }
        StringBuilder result = new StringBuilder();
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            if (current == null) {
                result.append("#,");
                continue;
            }
            result.append(current.val).append(',');
            stack.push(current.right);
            stack.push(current.left);
        }
        return result.toString();
    }
}
