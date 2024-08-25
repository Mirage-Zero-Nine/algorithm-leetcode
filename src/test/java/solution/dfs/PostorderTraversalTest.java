package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.google.common.collect.Lists;
import library.tree.TreeParser;
import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class PostorderTraversalTest {
    private PostorderTraversal test = new PostorderTraversal();

    @Test
    public void normalTest() {
        TreeNode root = TreeParser.deserialize("1,null,2,3");
        assertEquals(Lists.newArrayList(3, 2, 1), test.postorderTraversal(root));
        assertEquals(Lists.newArrayList(3, 2, 1), test.postorderTraversalStack(root));
    }

    @Test
    public void testNull() {
        assertEquals(Lists.newArrayList(), test.postorderTraversal(null));
    }

    @Test
    public void largeTest() {
        TreeNode root = TreeParser.deserialize("1,2,3,4,5,6,7");
        assertEquals(Lists.newArrayList(4, 5, 2, 6, 7, 3, 1), test.postorderTraversal(root));
        assertEquals(Lists.newArrayList(4, 5, 2, 6, 7, 3, 1), test.postorderTraversalStack(root));
    }

    @Test
    public void testSingleNode() {
        TreeNode root = TreeParser.deserialize("1");
        assertEquals(Lists.newArrayList(1), test.postorderTraversal(root));
        assertEquals(Lists.newArrayList(1), test.postorderTraversalStack(root));
    }

    @Test
    public void testLeftHeavyTree() {
        TreeNode root = TreeParser.deserialize("1,2,null,3,null,4");
        assertEquals(Lists.newArrayList(4, 3, 2, 1), test.postorderTraversal(root));
        assertEquals(Lists.newArrayList(4, 3, 2, 1), test.postorderTraversalStack(root));
    }

    @Test
    public void testRightHeavyTree() {
        TreeNode root = TreeParser.deserialize("1,null,2,null,3,null,4");
        assertEquals(Lists.newArrayList(4, 3, 2, 1), test.postorderTraversal(root));
        assertEquals(Lists.newArrayList(4, 3, 2, 1), test.postorderTraversalStack(root));
    }

    @Test
    public void testOnlyLeftSubtree() {
        TreeNode root = TreeParser.deserialize("1,2,null,3,null");
        assertEquals(Lists.newArrayList(3, 2, 1), test.postorderTraversal(root));
        assertEquals(Lists.newArrayList(3, 2, 1), test.postorderTraversalStack(root));
    }

    @Test
    public void testOnlyRightSubtree() {
        TreeNode root = TreeParser.deserialize("1,null,2,null,3");
        assertEquals(Lists.newArrayList(3, 2, 1), test.postorderTraversal(root));
        assertEquals(Lists.newArrayList(3, 2, 1), test.postorderTraversalStack(root));
    }

    @Test
    public void testTreeWithNulls() {
        TreeNode root = TreeParser.deserialize("1,2,3,null,null,4,5");
        assertEquals(Lists.newArrayList(2, 4, 5, 3, 1), test.postorderTraversal(root));
        assertEquals(Lists.newArrayList(2, 4, 5, 3, 1), test.postorderTraversalStack(root));
    }

    @Test
    public void testLargerBalancedTree() {
        TreeNode root = TreeParser.deserialize("1,2,3,4,5,6,7,8,9");
        assertEquals(Lists.newArrayList(8, 9, 4, 5, 2, 6, 7, 3, 1), test.postorderTraversal(root));
        assertEquals(Lists.newArrayList(8, 9, 4, 5, 2, 6, 7, 3, 1), test.postorderTraversalStack(root));
    }

    @Test
    public void testLargeBalancedTree() {
        TreeNode root = TreeParser.deserialize("1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31");
        assertEquals(Lists.newArrayList(16, 17, 8, 18, 19, 9, 4, 20, 21, 10, 22, 23, 11, 5, 2, 24, 25, 12, 26, 27, 13, 6, 28, 29, 14, 30, 31, 15, 7, 3, 1), test.postorderTraversal(root));
        assertEquals(Lists.newArrayList(16, 17, 8, 18, 19, 9, 4, 20, 21, 10, 22, 23, 11, 5, 2, 24, 25, 12, 26, 27, 13, 6, 28, 29, 14, 30, 31, 15, 7, 3, 1), test.postorderTraversalStack(root));
    }
}