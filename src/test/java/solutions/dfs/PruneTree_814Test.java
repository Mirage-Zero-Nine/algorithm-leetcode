package solutions.dfs;

import static library.tree.TreeParser.deserialize;
import static library.tree.TreeParser.serialize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class PruneTree_814Test {

    private final PruneTree_814 solution = new PruneTree_814();

    @Test
    public void testNullRoot() {
        assertNull(solution.pruneTree(null));
    }

    @Test
    public void testSingleZeroNodeIsPruned() {
        assertNull(solution.pruneTree(new TreeNode(0)));
    }

    @Test
    public void testSingleOneNodeIsPreserved() {
        TreeNode root = new TreeNode(1);

        assertSame(root, solution.pruneTree(root));
        assertEquals("1", serialize(root));
    }

    @Test
    public void testAllZeroTreeIsPruned() {
        assertPruned("0,0,0,0,0,0,0", "null");
    }

    @Test
    public void testAllOneTreeIsUnchanged() {
        assertPruned("1,1,1,1,1,1,1", "1,1,1,1,1,1,1");
    }

    @Test
    public void testLeetCodeExampleOne() {
        assertPruned("1,0,1,0,0,0,1", "1,null,1,null,1");
    }

    @Test
    public void testLeetCodeExampleTwo() {
        assertPruned("1,0,1,0,0,0,0", "1,null,1");
    }

    @Test
    public void testZeroLeafChildrenOfOneRootAreRemoved() {
        assertPruned("1,0,0", "1");
    }

    @Test
    public void testZeroInternalNodeWithOneDescendantIsPreserved() {
        // The left zero node remains because its right descendant is a 1.
        assertPruned("0,0,1,0,1", "0,0,1,null,1");
    }

    @Test
    public void testOnlyLeftBranchWithOneAtTheBottomIsPreserved() {
        assertPruned("1,0,null,0,null,1", "1,0,null,0,null,1");
    }

    @Test
    public void testOnlyRightBranchWithOneAtTheBottomIsPreserved() {
        assertPruned("1,null,0,null,0,null,1", "1,null,0,null,0,null,1");
    }

    @Test
    public void testLeftSubtreeRemovedAndRightSubtreePreserved() {
        assertPruned("1,0,1,0,0,0,1", "1,null,1,null,1");
    }

    @Test
    public void testRightSubtreeRemovedAndLeftSubtreePreserved() {
        assertPruned("1,1,0,1,1,0,0", "1,1,null,1,1");
    }

    @Test
    public void testZeroRootWithOneInLeftSubtreeIsPreserved() {
        assertPruned("0,1,null", "0,1");
    }

    @Test
    public void testZeroRootWithOneInRightSubtreeIsPreserved() {
        assertPruned("0,null,1", "0,null,1");
    }

    @Test
    public void testMultipleLevelsArePrunedBottomUp() {
        // Both zero-only branches disappear, while the zero ancestors of the 1 remain.
        assertPruned("0,0,0,0,1,0,0", "0,0,null,null,1");
    }

    @Test
    public void testPruningIsInPlaceAndRetainsSurvivingNodeReferences() {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(1);
        TreeNode right = new TreeNode(0);
        TreeNode survivingGrandchild = new TreeNode(1);
        TreeNode removedGrandchild = new TreeNode(0);
        root.left = left;
        root.right = right;
        right.left = survivingGrandchild;
        right.right = removedGrandchild;

        TreeNode result = solution.pruneTree(root);

        assertSame(root, result);
        assertSame(left, result.left);
        assertSame(right, result.right);
        assertSame(survivingGrandchild, result.right.left);
        assertNull(result.right.right);
    }

    @Test
    public void testAlreadyPrunedTreeIsIdempotent() {
        TreeNode root = deserialize("1,0,1,0,0,0,1");

        TreeNode firstResult = solution.pruneTree(root);
        TreeNode secondResult = solution.pruneTree(firstResult);

        assertSame(firstResult, secondResult);
        assertEquals("1,null,1,null,1", serialize(secondResult));
    }

    @Test
    public void testDeepLeftChainWithOneAtTheBottomIsPreserved() {
        TreeNode root = new TreeNode(0);
        TreeNode current = root;
        int depth = 1_000;
        for (int i = 1; i < depth; i++) {
            current.left = new TreeNode(i == depth - 1 ? 1 : 0);
            current = current.left;
        }

        TreeNode result = solution.pruneTree(root);

        assertSame(root, result);
        assertEquals(depth, countLeftChain(result));
        assertEquals(1, current.val);
    }

    @Test
    public void testDeepRightAllZeroChainIsCompletelyPruned() {
        TreeNode root = new TreeNode(0);
        TreeNode current = root;
        int depth = 1_000;
        for (int i = 1; i < depth; i++) {
            current.right = new TreeNode(0);
            current = current.right;
        }

        assertNull(solution.pruneTree(root));
    }

    private void assertPruned(String input, String expected) {
        TreeNode root = deserialize(input);
        assertEquals(expected, serialize(solution.pruneTree(root)));
    }

    private int countLeftChain(TreeNode root) {
        int count = 0;
        while (root != null) {
            count++;
            assertNull(root.right);
            root = root.left;
        }
        return count;
    }
}
