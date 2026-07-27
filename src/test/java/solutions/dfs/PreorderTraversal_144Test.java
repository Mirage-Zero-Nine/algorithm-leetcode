package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import com.google.common.collect.Lists;
import library.tree.TreeParser;
import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author BorisMirage
 * Time: 2022/10/31 00:21
 * Created with IntelliJ IDEA
 */

public class PreorderTraversal_144Test {

    private final PreorderTraversal_144 test = new PreorderTraversal_144();

    private void assertBothApproaches(List<Integer> expected, TreeNode root) {
        assertIterableEquals(expected, test.preorderTraversal(root));
        assertIterableEquals(expected, test.preorderTraversalStack(root));
    }

    @Test
    public void test() {
        TreeNode root = TreeParser.deserialize("1,null,2,3");
        List<Integer> expected = Lists.newArrayList(1, 2, 3);
        assertBothApproaches(expected, root);
    }

    @Test
    public void testNull() {
        assertBothApproaches(List.of(), null);
    }

    @Test
    public void testRootOnly() {
        assertBothApproaches(Lists.newArrayList(1), TreeParser.deserialize("1"));
    }

    @Test
    public void testLeftSkewed() {
        TreeNode root = TreeParser.deserialize("1,2,null,3,null,4");
        assertBothApproaches(Lists.newArrayList(1, 2, 3, 4), root);
    }

    @Test
    public void testRightSkewed() {
        TreeNode root = TreeParser.deserialize("1,null,2,null,3,null,4");
        assertBothApproaches(Lists.newArrayList(1, 2, 3, 4), root);
    }

    @Test
    public void testCompleteBinaryTree() {
        TreeNode root = TreeParser.deserialize("1,2,3,4,5,6,7");
        assertBothApproaches(Lists.newArrayList(1, 2, 4, 5, 3, 6, 7), root);
    }

    @Test
    public void testNegativeValues() {
        TreeNode root = TreeParser.deserialize("-1,-2,-3");
        assertBothApproaches(Lists.newArrayList(-1, -2, -3), root);
    }

    @Test
    public void testMixedValues() {
        TreeNode root = TreeParser.deserialize("0,-1,1");
        assertBothApproaches(Lists.newArrayList(0, -1, 1), root);
    }

    @Test
    public void testTwoNodes() {
        TreeNode root = TreeParser.deserialize("1,2");
        assertBothApproaches(Lists.newArrayList(1, 2), root);
    }

    @Test
    public void testGiantTree() {
        // Build a large complete binary tree with 1023 nodes (depth 10)
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 1023; i++) {
            if (i > 1) sb.append(",");
            sb.append(i);
        }
        TreeNode root = TreeParser.deserialize(sb.toString());
        List<Integer> recursiveResult = test.preorderTraversal(root);
        List<Integer> stackResult = test.preorderTraversalStack(root);
        // preorder of complete tree: root should be first
        assertEquals(1023, recursiveResult.size());
        assertEquals(1023, stackResult.size());
        assertEquals(1, recursiveResult.get(0));
        assertEquals(1, stackResult.get(0));
        assertIterableEquals(recursiveResult, stackResult);
    }

    @Test
    public void testDuplicateValues() {
        TreeNode root = TreeParser.deserialize("1,1,1,1,null,null,1");
        assertBothApproaches(Lists.newArrayList(1, 1, 1, 1, 1), root);
    }

    @Test
    public void testImbalancedTree() {
        // Left subtree deeper than right
        TreeNode root = TreeParser.deserialize("1,2,3,4,null,null,null,5");
        assertBothApproaches(Lists.newArrayList(1, 2, 4, 5, 3), root);
    }

    @Test
    public void testPerfectDepth3() {
        // Perfect binary tree: depth 3, 7 nodes
        TreeNode root = TreeParser.deserialize("10,20,30,40,50,60,70");
        assertBothApproaches(Lists.newArrayList(10, 20, 40, 50, 30, 60, 70), root);
    }

    @Test
    public void testPropertySizeEqualsNodeCount() {
        TreeNode root = TreeParser.deserialize("5,3,8,1,4,7,9");
        assertEquals(7, test.preorderTraversal(root).size());
        assertEquals(7, test.preorderTraversalStack(root).size());
    }

    @Test
    public void testPropertyFirstElementIsRoot() {
        TreeNode root = TreeParser.deserialize("42,10,99,1,15");
        assertEquals(42, test.preorderTraversal(root).get(0));
        assertEquals(42, test.preorderTraversalStack(root).get(0));
    }

    @Test
    public void testLargeNegativeValues() {
        TreeNode root = TreeParser.deserialize("-100,-200,300,-400,null,null,500");
        assertBothApproaches(Lists.newArrayList(-100, -200, -400, 300, 500), root);
    }
}
