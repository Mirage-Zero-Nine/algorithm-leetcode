package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.common.collect.Lists;
import library.tree.TreeParser;
import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


class BinaryTreePathsTest {
    private BinaryTreePaths test = new BinaryTreePaths();

    private void assertListsEqualIgnoringOrder(List<String> expected, List<String> actual) {
        Set<String> expectedSet = new HashSet<>(expected);
        Set<String> actualSet = new HashSet<>(actual);
        assertTrue(expectedSet.equals(actualSet), "Expected and actual lists do not match");
    }

    @Test
    public void basicTest() {
        TreeNode root = TreeParser.deserialize("1,2,3,null,5");
        assertListsEqualIgnoringOrder(Lists.newArrayList("1->2->5", "1->3"), test.binaryTreePaths(root));
    }

    @Test
    public void singleNodeTest() {
        TreeNode root = TreeParser.deserialize("1");
        assertListsEqualIgnoringOrder(Lists.newArrayList("1"), test.binaryTreePaths(root));
    }


    @Test
    public void fullBinaryTreeTest() {
        TreeNode root = TreeParser.deserialize("1,2,3,4,5,6,7");
        assertListsEqualIgnoringOrder(Lists.newArrayList("1->2->4", "1->2->5", "1->3->6", "1->3->7"), test.binaryTreePaths(root));
    }

    @Test
    public void leftSkewedTreeTest() {
        TreeNode root = TreeParser.deserialize("1,2,null,3,null,4");
        assertListsEqualIgnoringOrder(Lists.newArrayList("1->2->3->4"), test.binaryTreePaths(root));
    }

    @Test
    public void rightSkewedTreeTest() {
        TreeNode root = TreeParser.deserialize("1,null,2,null,3,null,4");
        assertListsEqualIgnoringOrder(Lists.newArrayList("1->2->3->4"), test.binaryTreePaths(root));
    }

    @Test
    public void treeWithOnlyLeftChildrenTest() {
        TreeNode root = TreeParser.deserialize("1,2,3,null,4,null,5");
        assertListsEqualIgnoringOrder(Lists.newArrayList("1->2->4", "1->3->5"), test.binaryTreePaths(root));
    }

    @Test
    public void treeWithOnlyRightChildrenTest() {
        TreeNode root = TreeParser.deserialize("1,null,2,null,3,null,4");
        assertListsEqualIgnoringOrder(Lists.newArrayList("1->2->3->4"), test.binaryTreePaths(root));
    }

    @Test
    public void treeWithNoChildrenTest() {
        TreeNode root = TreeParser.deserialize("1");
        assertListsEqualIgnoringOrder(Lists.newArrayList("1"), test.binaryTreePaths(root));
    }
}