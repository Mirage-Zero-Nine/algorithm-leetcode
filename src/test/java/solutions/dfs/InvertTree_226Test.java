package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.tree.TreeParser;
import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class InvertTree_226Test {

    private final InvertTree_226 test = new InvertTree_226();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2); root.right = new TreeNode(7);
        root.left.left = new TreeNode(1); root.left.right = new TreeNode(3);
        TreeNode result = test.invertTree(root);
        assertEquals(7, result.left.val);
        assertEquals(2, result.right.val);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.invertTree(null));
        assertEquals(1, test.invertTree(new TreeNode(1)).val);
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        TreeNode result = test.invertTree(root);
        assertEquals(3, result.left.val);
        assertEquals(2, result.right.val);
    }

    @Test
    public void testFullBinaryTree() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2); root.right = new TreeNode(7);
        root.left.left = new TreeNode(1); root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(6); root.right.right = new TreeNode(9);
        TreeNode result = test.invertTree(root);
        assertEquals(7, result.left.val);
        assertEquals(9, result.left.left.val);
        assertEquals(6, result.left.right.val);
        assertEquals(2, result.right.val);
        assertEquals(3, result.right.left.val);
        assertEquals(1, result.right.right.val);
    }

    @Test
    public void testLeftOnlyTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        TreeNode result = test.invertTree(root);
        assertNull(result.left);
        assertEquals(2, result.right.val);
        assertNull(result.right.left);
        assertEquals(3, result.right.right.val);
    }

    @Test
    public void testRightOnlyTree() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        TreeNode result = test.invertTree(root);
        assertEquals(2, result.left.val);
        assertEquals(3, result.left.left.val);
        assertNull(result.right);
    }

    @Test
    public void testTwoNodes() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        TreeNode result = test.invertTree(root);
        assertNull(result.left);
        assertEquals(2, result.right.val);
    }

    @Test
    public void testInvertTwiceRestoresOriginal() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        TreeNode inverted = test.invertTree(root);
        TreeNode restored = test.invertTree(inverted);
        assertEquals(2, restored.left.val);
        assertEquals(3, restored.right.val);
    }

    @Test
    public void testAsymmetricTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.right = new TreeNode(4);
        TreeNode result = test.invertTree(root);
        assertEquals(3, result.left.val);
        assertEquals(2, result.right.val);
        assertEquals(4, result.right.left.val);
        assertNull(result.right.right);
    }

    @Test
    public void testNegativeValues() {
        TreeNode root = new TreeNode(-1);
        root.left = new TreeNode(-2); root.right = new TreeNode(-3);
        TreeNode result = test.invertTree(root);
        assertEquals(-3, result.left.val);
        assertEquals(-2, result.right.val);
    }

    @Test
    public void testGiantTree() {
        // Build a balanced tree of depth 10 and invert it
        TreeNode root = buildBalancedTree(1, 10);
        TreeNode result = test.invertTree(root);
        // After inversion, left child should be what was right (val*2+1) and vice versa
        assertEquals(3, result.left.val);
        assertEquals(2, result.right.val);
    }

    @Test
    public void testTwoNodesRootAndRightOnly() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        TreeNode result = test.invertTree(root);
        assertEquals(2, result.left.val);
        assertNull(result.right);
    }

    @Test
    public void testLeftSkewedFiveNodes() {
        TreeNode root = TreeParser.deserialize("1, 2, null, 3, null, 4, null, 5");
        TreeNode result = test.invertTree(root);
        // Left-skewed becomes right-skewed
        assertNull(result.left);
        assertEquals(2, result.right.val);
        assertNull(result.right.left);
        assertEquals(3, result.right.right.val);
        assertNull(result.right.right.left);
        assertEquals(4, result.right.right.right.val);
        assertEquals(5, result.right.right.right.right.val);
    }

    @Test
    public void testRightSkewedFiveNodes() {
        TreeNode root = TreeParser.deserialize("1, null, 2, null, 3, null, 4, null, 5");
        TreeNode result = test.invertTree(root);
        // Right-skewed becomes left-skewed
        assertNull(result.right);
        assertEquals(2, result.left.val);
        assertNull(result.left.right);
        assertEquals(3, result.left.left.val);
        assertNull(result.left.left.right);
        assertEquals(4, result.left.left.left.val);
        assertEquals(5, result.left.left.left.left.val);
    }

    @Test
    public void testPerfectTreeDepth4Mirrored() {
        // Perfect tree: 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15
        TreeNode root = TreeParser.deserialize("1,2,3,4,5,6,7,8,9,10,11,12,13,14,15");
        List<Integer> originalLevelOrder = TreeParser.convertToList(root);
        TreeNode result = test.invertTree(root);
        // After inversion, level order should mirror each level
        assertEquals(1, result.val);
        assertEquals(3, result.left.val);
        assertEquals(2, result.right.val);
        assertEquals(7, result.left.left.val);
        assertEquals(6, result.left.right.val);
        assertEquals(5, result.right.left.val);
        assertEquals(4, result.right.right.val);
        assertEquals(15, result.left.left.left.val);
        assertEquals(14, result.left.left.right.val);
        assertEquals(13, result.left.right.left.val);
        assertEquals(12, result.left.right.right.val);
        assertEquals(11, result.right.left.left.val);
        assertEquals(10, result.right.left.right.val);
        assertEquals(9, result.right.right.left.val);
        assertEquals(8, result.right.right.right.val);
    }

    @Test
    public void testDuplicateValues() {
        // Tree with duplicates: root=5, left=5, right=5, left.left=5, left.right=3
        TreeNode root = TreeParser.deserialize("5, 5, 5, 5, 3");
        TreeNode result = test.invertTree(root);
        assertEquals(5, result.val);
        assertEquals(5, result.left.val);
        assertEquals(5, result.right.val);
        assertEquals(3, result.right.left.val);
        assertEquals(5, result.right.right.val);
    }

    @Test
    public void testDoubleInvertComplexTree() {
        TreeNode root = TreeParser.deserialize("1, 2, 3, 4, 5, 6, 7, null, 8, null, 9");
        List<Integer> originalList = TreeParser.convertToList(root);
        TreeNode inverted = test.invertTree(root);
        TreeNode restored = test.invertTree(inverted);
        List<Integer> restoredList = TreeParser.convertToList(restored);
        assertEquals(originalList, restoredList);
    }

    @Test
    public void testInOrderReversalProperty() {
        TreeNode root = TreeParser.deserialize("4, 2, 7, 1, 3, 6, 9");
        List<Integer> originalInOrder = inOrder(root);
        TreeNode inverted = test.invertTree(root);
        List<Integer> invertedInOrder = inOrder(inverted);
        Collections.reverse(originalInOrder);
        assertEquals(originalInOrder, invertedInOrder);
    }

    @Test
    public void testLargeRandomTreeDoubleInvert() {
        Random rand = new Random(42L);
        TreeNode root = buildRandomTree(rand, 1000);
        List<Integer> originalList = TreeParser.convertToList(root);
        TreeNode inverted = test.invertTree(root);
        TreeNode restored = test.invertTree(inverted);
        List<Integer> restoredList = TreeParser.convertToList(restored);
        assertEquals(originalList, restoredList);
    }

    private TreeNode buildRandomTree(Random rand, int size) {
        if (size <= 0) return null;
        TreeNode root = new TreeNode(rand.nextInt(2001) - 1000);
        java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
        queue.offer(root);
        int count = 1;
        while (count < size && !queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (count < size && rand.nextBoolean()) {
                node.left = new TreeNode(rand.nextInt(2001) - 1000);
                queue.offer(node.left);
                count++;
            }
            if (count < size && rand.nextBoolean()) {
                node.right = new TreeNode(rand.nextInt(2001) - 1000);
                queue.offer(node.right);
                count++;
            }
        }
        return root;
    }

    private List<Integer> inOrder(TreeNode node) {
        List<Integer> result = new ArrayList<>();
        inOrderHelper(node, result);
        return result;
    }

    private void inOrderHelper(TreeNode node, List<Integer> result) {
        if (node == null) return;
        inOrderHelper(node.left, result);
        result.add(node.val);
        inOrderHelper(node.right, result);
    }

    private TreeNode buildBalancedTree(int val, int depth) {
        if (depth == 0) return null;
        TreeNode node = new TreeNode(val);
        node.left = buildBalancedTree(val * 2, depth - 1);
        node.right = buildBalancedTree(val * 2 + 1, depth - 1);
        return node;
    }
}
