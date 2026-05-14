package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import com.google.common.collect.Lists;
import library.tree.TreeParser;
import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BorisMirage
 * Time: 2022/10/31 00:21
 * Created with IntelliJ IDEA
 */

public class PreorderTraversal_144Test {

    private final PreorderTraversal_144 test = new PreorderTraversal_144();

    @Test
    public void test() {
        TreeNode root = TreeParser.deserialize("1,null,2,3");
        List<Integer> expected = Lists.newArrayList(1, 2, 3);
        assertIterableEquals(expected, test.preorderTraversal(root));
    }

    @Test
    public void testNull() {
        assertIterableEquals(new ArrayList<>(), test.preorderTraversal(null));
    }

    @Test
    public void testRootOnly() {
        assertIterableEquals(Lists.newArrayList(1), test.preorderTraversal(TreeParser.deserialize("1")));
    }

    @Test
    public void testLeftSkewed() {
        TreeNode root = TreeParser.deserialize("1,2,null,3,null,4");
        assertIterableEquals(Lists.newArrayList(1, 2, 3, 4), test.preorderTraversal(root));
    }

    @Test
    public void testRightSkewed() {
        TreeNode root = TreeParser.deserialize("1,null,2,null,3,null,4");
        assertIterableEquals(Lists.newArrayList(1, 2, 3, 4), test.preorderTraversal(root));
    }

    @Test
    public void testCompleteBinaryTree() {
        TreeNode root = TreeParser.deserialize("1,2,3,4,5,6,7");
        assertIterableEquals(Lists.newArrayList(1, 2, 4, 5, 3, 6, 7), test.preorderTraversal(root));
    }

    @Test
    public void testNegativeValues() {
        TreeNode root = TreeParser.deserialize("-1,-2,-3");
        assertIterableEquals(Lists.newArrayList(-1, -2, -3), test.preorderTraversal(root));
    }

    @Test
    public void testMixedValues() {
        TreeNode root = TreeParser.deserialize("0,-1,1");
        assertIterableEquals(Lists.newArrayList(0, -1, 1), test.preorderTraversal(root));
    }

    @Test
    public void testTwoNodes() {
        TreeNode root = TreeParser.deserialize("1,2");
        assertIterableEquals(Lists.newArrayList(1, 2), test.preorderTraversal(root));
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
        List<Integer> result = test.preorderTraversal(root);
        // preorder of complete tree: root should be first
        assert result.size() == 1023;
        assert result.get(0) == 1;
    }
}