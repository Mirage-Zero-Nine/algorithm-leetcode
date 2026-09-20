package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

/** Tests for the edge-count diameter contract of LeetCode 543. */
public class DiameterOfBinaryTree_543Test {

    @Test
    public void nullRootHasZeroDiameter() {
        assertEquals(0, new DiameterOfBinaryTree_543().diameterOfBinaryTree(null));
    }

    @Test
    public void singletonHasZeroEdges() {
        assertEquals(0, diameter(new TreeNode(42)));
    }

    @Test
    public void officialExampleHasDiameterThree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        assertEquals(3, diameter(root));
    }

    @Test
    public void twoNodesOnLeftHaveOneEdge() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        assertEquals(1, diameter(root));
    }

    @Test
    public void twoNodesOnRightHaveOneEdge() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        assertEquals(1, diameter(root));
    }

    @Test
    public void balancedTreeUsesBothSidesOfRoot() {
        TreeNode root = completeTree(7);
        assertEquals(4, diameter(root));
    }

    @Test
    public void diameterCanPassThroughRootWhenOneSideIsEmpty() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.left.left = new TreeNode(2);
        root.left.left.left = new TreeNode(3);
        assertEquals(3, diameter(root));
    }

    @Test
    public void diameterCanBeEntirelyInsideLeftSubtree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.left.left.left = new TreeNode(5);
        root.left.right.right = new TreeNode(6);
        root.right = new TreeNode(7);

        assertEquals(4, diameter(root));
    }

    @Test
    public void diameterCanBeEntirelyInsideRightSubtree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        root.right.left.left = new TreeNode(6);
        root.right.right.right = new TreeNode(7);

        assertEquals(4, diameter(root));
    }

    @Test
    public void leftSkewedTreeCountsEdgesNotNodes() {
        TreeNode root = new TreeNode(1);
        TreeNode current = root;
        for (int value = 2; value <= 9; value++) {
            current.left = new TreeNode(value);
            current = current.left;
        }
        assertEquals(8, diameter(root));
    }

    @Test
    public void rightSkewedTreeCountsEdgesNotNodes() {
        TreeNode root = new TreeNode(1);
        TreeNode current = root;
        for (int value = 2; value <= 9; value++) {
            current.right = new TreeNode(value);
            current = current.right;
        }
        assertEquals(8, diameter(root));
    }

    @Test
    public void sparseAlternatingBranchesAreHandled() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(3);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.left.right = new TreeNode(6);
        root.right.left.right.right = new TreeNode(7);

        assertEquals(7, diameter(root));
    }

    @Test
    public void nodeValuesDoNotAffectDiameter() {
        TreeNode root = new TreeNode(Integer.MIN_VALUE);
        root.left = new TreeNode(100);
        root.right = new TreeNode(-100);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(Integer.MAX_VALUE);
        root.right.right = new TreeNode(-1);

        assertEquals(4, diameter(root));
    }

    @Test
    public void duplicateValuesRemainDistinctNodes() {
        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(7);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(7);
        root.right.right = new TreeNode(7);

        assertEquals(4, diameter(root));
    }

    @Test
    public void incompleteLevelDoesNotImplyCompleteTree() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.right = new TreeNode(2);
        root.left.right = new TreeNode(3);
        root.left.right.right = new TreeNode(4);
        root.right.left = new TreeNode(5);

        assertEquals(5, diameter(root));
    }

    @Test
    public void sameInstanceResetsMaximumBetweenCalls() {
        DiameterOfBinaryTree_543 solver = new DiameterOfBinaryTree_543();
        TreeNode wide = completeTree(7);

        assertEquals(4, solver.diameterOfBinaryTree(wide));
        TreeNode twoNodes = new TreeNode(1);
        twoNodes.right = new TreeNode(2);
        assertEquals(1, solver.diameterOfBinaryTree(twoNodes));
    }

    @Test
    public void sameInstanceNullCallResetsMaximumBetweenCalls() {
        DiameterOfBinaryTree_543 solver = new DiameterOfBinaryTree_543();
        assertEquals(4, solver.diameterOfBinaryTree(completeTree(7)));
        assertEquals(0, solver.diameterOfBinaryTree(null));
    }

    @Test
    public void repeatedCallsCanIncreaseAndDecreaseDiameter() {
        DiameterOfBinaryTree_543 solver = new DiameterOfBinaryTree_543();
        TreeNode chain = new TreeNode(1);
        chain.left = new TreeNode(2);
        chain.left.left = new TreeNode(3);

        assertEquals(2, solver.diameterOfBinaryTree(chain));
        assertEquals(0, solver.diameterOfBinaryTree(new TreeNode(9)));
        TreeNode twoNodes = new TreeNode(8);
        twoNodes.right = new TreeNode(7);
        assertEquals(1, solver.diameterOfBinaryTree(twoNodes));
    }

    @Test
    public void separateInstancesDoNotShareState() {
        TreeNode chain = new TreeNode(1);
        chain.right = new TreeNode(2);
        chain.right.right = new TreeNode(3);

        assertEquals(2, new DiameterOfBinaryTree_543().diameterOfBinaryTree(chain));
        assertEquals(0, new DiameterOfBinaryTree_543().diameterOfBinaryTree(new TreeNode(4)));
    }

    @Test
    public void traversalDoesNotMutateValuesOrTopology() {
        TreeNode root = completeTree(15);
        List<TreeNode> nodes = nodes(root);
        Map<TreeNode, TreeNode> left = new IdentityHashMap<>();
        Map<TreeNode, TreeNode> right = new IdentityHashMap<>();
        Map<TreeNode, Integer> values = new IdentityHashMap<>();
        for (TreeNode node : nodes) {
            left.put(node, node.left);
            right.put(node, node.right);
            values.put(node, node.val);
        }

        assertEquals(6, diameter(root));
        for (TreeNode node : nodes) {
            assertSame(left.get(node), node.left);
            assertSame(right.get(node), node.right);
            assertEquals(values.get(node), node.val);
        }
    }

    @Test
    public void seededRandomTreesMatchIndependentAllPairsOracle() {
        Random random = new Random(0x543D1A); // deterministic and reproducible
        for (int caseNumber = 0; caseNumber < 150; caseNumber++) {
            int nodeCount = 1 + random.nextInt(35);
            TreeNode root = randomTree(nodeCount, random);
            assertEquals(diameterByAllPairs(root), diameter(root), "random case " + caseNumber);
        }
    }

    @Test
    public void exhaustiveSmallCompletePrefixesMatchOracle() {
        for (int nodeCount = 1; nodeCount <= 63; nodeCount++) {
            TreeNode root = completeTree(nodeCount);
            assertEquals(diameterByAllPairs(root), diameter(root), "node count " + nodeCount);
        }
    }

    @Test
    public void maximumPermittedNodeCountAndDepthIsSupported() {
        TreeNode root = new TreeNode(0);
        TreeNode current = root;
        for (int i = 1; i < 10_000; i++) {
            current.left = new TreeNode((i % 201) - 100);
            current = current.left;
        }

        assertEquals(9_999, diameter(root));
    }

    @Test
    public void maximumPermittedNodeCountWithBalancedShapeIsSupported() {
        TreeNode root = completeTree(10_000);
        assertEquals(25, diameter(root));
    }

    @Test
    public void maximumLegalValueRangeDoesNotChangeResult() {
        TreeNode root = new TreeNode(-100);
        root.left = new TreeNode(100);
        root.right = new TreeNode(-100);
        root.left.left = new TreeNode(100);
        root.left.left.left = new TreeNode(-100);
        assertEquals(4, diameter(root));
    }

    @Test
    public void independentOracleHandlesSingleNodeAndDegenerateCases() {
        assertEquals(0, diameterByAllPairs(new TreeNode(0)));
        assertEquals(0, diameterByAllPairs(null));

        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        assertEquals(1, diameterByAllPairs(root));
    }

    @Test
    public void resultIsIndependentOfPreviousTreeReferences() {
        DiameterOfBinaryTree_543 solver = new DiameterOfBinaryTree_543();
        TreeNode first = completeTree(31);
        assertEquals(8, solver.diameterOfBinaryTree(first));
        first.left = null;

        TreeNode second = new TreeNode(2);
        second.right = new TreeNode(3);
        assertEquals(1, solver.diameterOfBinaryTree(second));
    }

    private static int diameter(TreeNode root) {
        return new DiameterOfBinaryTree_543().diameterOfBinaryTree(root);
    }

    /** Independent reference implementation: build the undirected tree and BFS from every node. */
    private static int diameterByAllPairs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        List<TreeNode> treeNodes = nodes(root);
        Map<TreeNode, List<TreeNode>> graph = new IdentityHashMap<>();
        for (TreeNode node : treeNodes) {
            graph.put(node, new ArrayList<>());
        }
        for (TreeNode node : treeNodes) {
            if (node.left != null) {
                graph.get(node).add(node.left);
                graph.get(node.left).add(node);
            }
            if (node.right != null) {
                graph.get(node).add(node.right);
                graph.get(node.right).add(node);
            }
        }

        int result = 0;
        for (TreeNode source : treeNodes) {
            Map<TreeNode, Integer> distance = new IdentityHashMap<>();
            Deque<TreeNode> queue = new ArrayDeque<>();
            distance.put(source, 0);
            queue.add(source);
            while (!queue.isEmpty()) {
                TreeNode node = queue.remove();
                result = Math.max(result, distance.get(node));
                for (TreeNode neighbor : graph.get(node)) {
                    if (!distance.containsKey(neighbor)) {
                        distance.put(neighbor, distance.get(node) + 1);
                        queue.add(neighbor);
                    }
                }
            }
        }
        return result;
    }

    private static List<TreeNode> nodes(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<TreeNode> result = new ArrayList<>();
        Set<TreeNode> seen = Collections.newSetFromMap(new IdentityHashMap<>());
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        seen.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            result.add(node);
            if (node.left != null && seen.add(node.left)) {
                queue.add(node.left);
            }
            if (node.right != null && seen.add(node.right)) {
                queue.add(node.right);
            }
        }
        return result;
    }

    private static TreeNode completeTree(int nodeCount) {
        if (nodeCount == 0) {
            return null;
        }
        TreeNode[] tree = new TreeNode[nodeCount];
        for (int i = 0; i < nodeCount; i++) {
            tree[i] = new TreeNode((i % 201) - 100);
        }
        for (int i = 0; i < nodeCount; i++) {
            int left = i * 2 + 1;
            int right = left + 1;
            if (left < nodeCount) {
                tree[i].left = tree[left];
            }
            if (right < nodeCount) {
                tree[i].right = tree[right];
            }
        }
        return tree[0];
    }

    private static TreeNode randomTree(int nodeCount, Random random) {
        TreeNode root = new TreeNode(random.nextInt(201) - 100);
        List<TreeNode> created = new ArrayList<>();
        created.add(root);
        for (int i = 1; i < nodeCount; i++) {
            TreeNode child = new TreeNode(random.nextInt(201) - 100);
            while (true) {
                TreeNode parent = created.get(random.nextInt(created.size()));
                if (parent.left == null && parent.right == null) {
                    if (random.nextBoolean()) {
                        parent.left = child;
                    } else {
                        parent.right = child;
                    }
                    break;
                }
                if (parent.left == null) {
                    parent.left = child;
                    break;
                }
                if (parent.right == null) {
                    parent.right = child;
                    break;
                }
            }
            created.add(child);
        }
        return root;
    }
}
