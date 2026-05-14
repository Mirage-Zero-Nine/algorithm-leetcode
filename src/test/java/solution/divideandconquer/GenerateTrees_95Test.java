package solution.divideandconquer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class GenerateTrees_95Test {
    private final GenerateTrees_95 solver = new GenerateTrees_95();

    /** Serialize tree in preorder with null markers so structurally different trees produce different strings. */
    private static String serialize(TreeNode node) {
        if (node == null) return "#";
        return node.val + "," + serialize(node.left) + "," + serialize(node.right);
    }

    private static boolean isBST(TreeNode root) {
        return isBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean isBST(TreeNode node, long min, long max) {
        if (node == null) return true;
        if (node.val <= min || node.val >= max) return false;
        return isBST(node.left, min, node.val) && isBST(node.right, node.val, max);
    }

    private static int count(TreeNode node) {
        if (node == null) return 0;
        return 1 + count(node.left) + count(node.right);
    }

    @Test public void nEqualsZeroReturnsEmptyList() {
        List<TreeNode> result = solver.generateTrees(0);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test public void nEqualsOneReturnsSingleNode() {
        List<TreeNode> result = solver.generateTrees(1);
        assertEquals(1, result.size());
        TreeNode only = result.get(0);
        assertNotNull(only);
        assertEquals(1, only.val);
        assertNull(only.left);
        assertNull(only.right);
    }

    @Test public void nEqualsTwoReturnsTwoTrees() {
        List<TreeNode> result = solver.generateTrees(2);
        assertEquals(2, result.size());
        for (TreeNode root : result) {
            assertTrue(isBST(root));
            assertEquals(2, count(root));
        }
        // the two shapes must be structurally distinct
        Set<String> shapes = new HashSet<>();
        for (TreeNode root : result) shapes.add(serialize(root));
        assertEquals(2, shapes.size());
    }

    @Test public void nEqualsThreeReturnsCatalanFive() {
        // Catalan(3) = 5 structurally unique BSTs
        List<TreeNode> result = solver.generateTrees(3);
        assertEquals(5, result.size());
        Set<String> shapes = new HashSet<>();
        for (TreeNode root : result) {
            assertTrue(isBST(root));
            assertEquals(3, count(root));
            shapes.add(serialize(root));
        }
        assertEquals(5, shapes.size());
    }

    @Test public void nEqualsFourReturnsCatalanFourteen() {
        // Catalan(4) = 14
        List<TreeNode> result = solver.generateTrees(4);
        assertEquals(14, result.size());
        Set<String> shapes = new HashSet<>();
        for (TreeNode root : result) {
            assertTrue(isBST(root));
            assertEquals(4, count(root));
            shapes.add(serialize(root));
        }
        assertEquals(14, shapes.size());
    }

    @Test public void allTreesContainValuesOneToN() {
        int n = 3;
        List<TreeNode> result = solver.generateTrees(n);
        List<Integer> expected = new ArrayList<>();
        for (int i = 1; i <= n; i++) expected.add(i);
        Collections.sort(expected);
        for (TreeNode root : result) {
            List<Integer> inorder = new ArrayList<>();
            inorderCollect(root, inorder);
            assertEquals(expected, inorder);
        }
    }

    @Test public void nEqualsFiveReturnsCatalanFortyTwo() {
        // Catalan(5) = 42
        List<TreeNode> result = solver.generateTrees(5);
        assertEquals(42, result.size());
        Set<String> shapes = new HashSet<>();
        for (TreeNode root : result) {
            assertTrue(isBST(root));
            assertEquals(5, count(root));
            shapes.add(serialize(root));
        }
        assertEquals(42, shapes.size());
    }

    @Test public void rootValuesSpanOneToN() {
        int n = 4;
        List<TreeNode> result = solver.generateTrees(n);
        Set<Integer> rootVals = new HashSet<>();
        for (TreeNode root : result) rootVals.add(root.val);
        assertEquals(Set.of(1, 2, 3, 4), rootVals);
    }

    @Test public void nEqualsOneInorderIsSingleValue() {
        List<TreeNode> result = solver.generateTrees(1);
        List<Integer> inorder = new ArrayList<>();
        inorderCollect(result.get(0), inorder);
        assertEquals(List.of(1), inorder);
    }

    @Test public void giantTestN7() {
        // Catalan(7) = 429
        List<TreeNode> result = solver.generateTrees(7);
        assertEquals(429, result.size());
    }

    private void inorderCollect(TreeNode node, List<Integer> out) {
        if (node == null) return;
        inorderCollect(node.left, out);
        out.add(node.val);
        inorderCollect(node.right, out);
    }
}
