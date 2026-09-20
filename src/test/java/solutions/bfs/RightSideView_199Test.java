package solutions.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Random;
import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

/** Tests both right-side-view implementations against an independent tree oracle. */
public class RightSideView_199Test {

    @Test
    public void nullRootReturnsEmptyViewForBothApproaches() {
        RightSideView_199 solution = new RightSideView_199();

        List<Integer> dfs = solution.rightSideView(null);
        List<Integer> bfs = solution.bfs(null);

        assertEquals(List.of(), dfs);
        assertEquals(List.of(), bfs);
        assertNotSame(dfs, bfs);
    }

    @Test
    public void singletonZeroIsVisible() {
        assertBoth(new TreeNode(0), List.of(0));
    }

    @Test
    public void officialExampleOne() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(4);

        assertBoth(root, List.of(1, 3, 4));
    }

    @Test
    public void officialExampleTwoShowsDeeperLeftBranch() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.left.left = new TreeNode(5);

        assertBoth(root, List.of(1, 3, 4, 5));
    }

    @Test
    public void officialExampleThreeHasOnlyRightChild() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(3);

        assertBoth(root, List.of(1, 3));
    }

    @Test
    public void completeTreeUsesEachLevelRightmostNode() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        root.left.left.left = new TreeNode(8);
        root.left.left.right = new TreeNode(9);

        assertBoth(root, List.of(1, 3, 7, 9));
    }

    @Test
    public void leftSkewedTreeStillHasOneVisibleNodePerLevel() {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(20);
        root.left.left = new TreeNode(30);
        root.left.left.left = new TreeNode(40);
        root.left.left.left.left = new TreeNode(50);

        assertBoth(root, List.of(10, 20, 30, 40, 50));
    }

    @Test
    public void rightSkewedTreeIsReturnedTopToBottom() {
        TreeNode root = new TreeNode(-1);
        root.right = new TreeNode(-2);
        root.right.right = new TreeNode(-3);
        root.right.right.right = new TreeNode(-4);

        assertBoth(root, List.of(-1, -2, -3, -4));
    }

    @Test
    public void rightSubtreeCanHaveADeeperLeftVisibleBranch() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        root.right.left.left = new TreeNode(4);
        root.right.left.left.right = new TreeNode(5);

        assertBoth(root, List.of(1, 2, 3, 4, 5));
    }

    @Test
    public void leftSubtreeSuppliesLevelsMissingFromRightSubtree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.left.right.left = new TreeNode(5);
        root.left.right.left.left = new TreeNode(6);

        assertBoth(root, List.of(1, 3, 4, 5, 6));
    }

    @Test
    public void sparseCrossParentOrderingUsesTheRightmostNodeByLevel() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.left.right = new TreeNode(6);
        root.left.left.right = new TreeNode(7);

        assertBoth(root, List.of(1, 3, 5, 6));
    }

    @Test
    public void alternatingSingleChildDirectionsRemainOrdered() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(3);
        root.left.right.left.right = new TreeNode(4);
        root.left.right.left.right.left = new TreeNode(5);

        assertBoth(root, List.of(0, 1, 2, 3, 4, 5));
    }

    @Test
    public void rightmostValuesMayBeNegativeAndDuplicated() {
        TreeNode root = new TreeNode(-7);
        root.left = new TreeNode(-7);
        root.right = new TreeNode(-7);
        root.left.right = new TreeNode(0);
        root.right.left = new TreeNode(0);
        root.right.left.left = new TreeNode(-7);

        assertBoth(root, List.of(-7, -7, 0, -7));
    }

    @Test
    public void officialValueBoundariesArePreserved() {
        TreeNode root = new TreeNode(-100);
        root.left = new TreeNode(100);
        root.right = new TreeNode(-100);
        root.left.left = new TreeNode(-100);
        root.left.right = new TreeNode(100);
        root.right.right = new TreeNode(100);

        assertBoth(root, List.of(-100, -100, 100));
    }

    @Test
    public void rightmostNodeCanBeTheLeftChildOfTheRightmostParent() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.left.left = new TreeNode(5);
        root.left.right = new TreeNode(6);
        root.left.right.right = new TreeNode(7);

        assertBoth(root, List.of(1, 3, 4, 5));
    }

    @Test
    public void aWideSparseLevelKeepsTheLastPresentNode() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(5);
        root.left.left.right = new TreeNode(6);
        root.left.right.left = new TreeNode(7);
        root.right.right.left = new TreeNode(8);
        root.right.right.right = new TreeNode(9);

        assertBoth(root, List.of(0, 2, 5, 9));
    }

    @Test
    public void exactlyOneHundredNodeCompletePrefixIsSupported() {
        TreeNode root = completePrefixTree(100);
        List<Integer> expected = independentOracle(root);

        assertBoth(root, expected);
        assertEquals(7, expected.size());
        assertEquals(99, expected.get(expected.size() - 1));
    }

    @Test
    public void exactlyOneHundredNodeRightSpineIsSupported() {
        TreeNode root = new TreeNode(-100);
        TreeNode current = root;
        for (int i = 1; i < 100; i++) {
            current.right = new TreeNode(i - 100);
            current = current.right;
        }

        List<Integer> expected = independentOracle(root);
        assertBoth(root, expected);
        assertEquals(100, expected.size());
        assertEquals(-1, expected.get(99));
    }

    @Test
    public void deepLeftSpineAtTheOfficialNodeLimitIsSupported() {
        TreeNode root = new TreeNode(100);
        TreeNode current = root;
        for (int i = 1; i < 100; i++) {
            current.left = new TreeNode(100 - i);
            current = current.left;
        }

        assertBoth(root, independentOracle(root));
    }

    @Test
    public void deterministicSparseTreesAgreeWithTheIndependentOracle() {
        Random random = new Random(199199L);
        for (int caseNumber = 0; caseNumber < 80; caseNumber++) {
            TreeNode root = new TreeNode(random.nextInt(201) - 100);
            List<TreeNode> frontier = new ArrayList<>();
            frontier.add(root);
            for (int i = 1; i < 40; i++) {
                TreeNode parent = frontier.get(random.nextInt(frontier.size()));
                TreeNode child = new TreeNode(random.nextInt(201) - 100);
                if (random.nextBoolean() && parent.left == null) {
                    parent.left = child;
                } else if (parent.right == null) {
                    parent.right = child;
                } else {
                    continue;
                }
                frontier.add(child);
            }
            assertBoth(root, independentOracle(root));
        }
    }

    @Test
    public void repeatedCallsOnOneInstanceDoNotLeakResults() {
        RightSideView_199 solution = new RightSideView_199();

        TreeNode first = new TreeNode(1);
        first.left = new TreeNode(2);
        first.right = new TreeNode(3);
        first.right.right = new TreeNode(4);
        assertEquals(List.of(1, 3, 4), solution.rightSideView(first));
        assertEquals(List.of(8), solution.rightSideView(new TreeNode(8)));

        TreeNode third = new TreeNode(5);
        third.left = new TreeNode(6);
        assertEquals(List.of(5, 6), solution.bfs(third));
        assertEquals(List.of(), solution.bfs(null));
    }

    @Test
    public void returnedListsAreIndependentAcrossCallsAndApproaches() {
        RightSideView_199 solution = new RightSideView_199();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);

        List<Integer> first = solution.rightSideView(root);
        first.clear();
        assertEquals(List.of(1, 2), solution.rightSideView(root));

        List<Integer> second = solution.bfs(root);
        second.clear();
        assertEquals(List.of(1, 2), solution.bfs(root));
    }

    @Test
    public void callsDoNotMutateInputTopologyOrValues() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(5);
        String before = treeEncoding(root);

        new RightSideView_199().rightSideView(root);
        assertEquals(before, treeEncoding(root));
        new RightSideView_199().bfs(root);
        assertEquals(before, treeEncoding(root));
    }

    @Test
    public void independentOracleHandlesAllLeavesOnTheSameLevel() {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(20);
        root.right = new TreeNode(30);
        root.left.left = new TreeNode(40);
        root.left.right = new TreeNode(50);
        root.right.left = new TreeNode(60);
        root.right.right = new TreeNode(70);
        root.left.left.left = new TreeNode(80);
        root.left.left.right = new TreeNode(90);
        root.left.right.left = new TreeNode(100);

        assertBoth(root, independentOracle(root));
    }

    @Test
    public void onlyRightChildAtEveryLevelIsNotSkipped() {
        TreeNode root = new TreeNode(100);
        TreeNode current = root;
        for (int value = 99; value >= 91; value--) {
            current.right = new TreeNode(value);
            current = current.right;
        }

        assertBoth(root, List.of(100, 99, 98, 97, 96, 95, 94, 93, 92, 91));
    }

    @Test
    public void aRightBranchCanHideALeftBranchAtEachEarlierLevel() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.left.left.left = new TreeNode(7);
        root.left.right.right = new TreeNode(8);
        root.right.left.left = new TreeNode(9);
        root.right.right.left = new TreeNode(10);

        assertBoth(root, List.of(0, 2, 6, 10));
    }

    @Test
    public void freshSolutionInstancesHaveNoSharedState() {
        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(100);
        root.right = new TreeNode(-100);

        assertEquals(List.of(-10, -100), new RightSideView_199().rightSideView(root));
        assertEquals(List.of(-10, -100), new RightSideView_199().bfs(root));
    }

    private static void assertBoth(TreeNode root, List<Integer> expected) {
        RightSideView_199 solution = new RightSideView_199();
        TreeNode dfsInput = cloneTree(root);
        TreeNode bfsInput = cloneTree(root);

        assertEquals(expected, solution.rightSideView(dfsInput));
        assertEquals(expected, solution.bfs(bfsInput));
        assertEquals(expected, independentOracle(root));
        assertEquals(treeEncoding(root), treeEncoding(dfsInput));
        assertEquals(treeEncoding(root), treeEncoding(bfsInput));
    }

    /**
     * Independent left-to-right DFS oracle: overwrite the value at each depth so the last
     * encountered node, rather than the production traversal order, defines that level's view.
     */
    private static List<Integer> independentOracle(TreeNode root) {
        List<Integer> view = new ArrayList<>();
        collectLastAtEachDepth(root, 0, view);
        return view;
    }

    private static void collectLastAtEachDepth(TreeNode node, int depth, List<Integer> view) {
        if (node == null) {
            return;
        }
        if (depth == view.size()) {
            view.add(node.val);
        } else {
            view.set(depth, node.val);
        }
        collectLastAtEachDepth(node.left, depth + 1, view);
        collectLastAtEachDepth(node.right, depth + 1, view);
    }

    private static TreeNode completePrefixTree(int nodeCount) {
        TreeNode[] nodes = new TreeNode[nodeCount];
        for (int i = 0; i < nodeCount; i++) {
            nodes[i] = new TreeNode(i);
        }
        for (int i = 0; i < nodeCount; i++) {
            int left = 2 * i + 1;
            int right = left + 1;
            if (left < nodeCount) {
                nodes[i].left = nodes[left];
            }
            if (right < nodeCount) {
                nodes[i].right = nodes[right];
            }
        }
        return nodes[0];
    }

    private static TreeNode cloneTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode copy = new TreeNode(root.val);
        copy.left = cloneTree(root.left);
        copy.right = cloneTree(root.right);
        return copy;
    }

    private static String treeEncoding(TreeNode root) {
        if (root == null) {
            return "[]";
        }
        List<String> values = new ArrayList<>();
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            values.add(String.valueOf(node.val));
            if (node.left != null) {
                queue.add(node.left);
            } else {
                values.add("null");
            }
            if (node.right != null) {
                queue.add(node.right);
            } else {
                values.add("null");
            }
        }
        return values.toString();
    }
}
