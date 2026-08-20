package solutions.dfs;

import static library.tree.TreeParser.deserialize;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class IsValidBST_98Test {

    private final IsValidBST_98 test = new IsValidBST_98();

    /**
     * Every input is checked by every implementation. The cases cover empty
     * and singleton trees, valid shapes, boundary values, duplicates, local
     * ordering errors, and violations of an ancestor's bounds.
     */
    @ParameterizedTest(name = "{0} (expected {2})")
    @MethodSource("bstCases")
    public void testAllApproaches(String description, TreeNode root, boolean expected) {
        assertAllApproaches(root, expected);
    }

    private static Stream<Arguments> bstCases() {
        return Stream.of(
                testCase("empty tree", "null", true),
                testCase("single zero", "0", true),
                testCase("single Integer.MIN_VALUE", String.valueOf(Integer.MIN_VALUE), true),
                testCase("single Integer.MAX_VALUE", String.valueOf(Integer.MAX_VALUE), true),

                testCase("valid left child", "2,1", true),
                testCase("valid right child", "2,null,3", true),
                testCase("invalid left child is greater", "1,2", false),
                testCase("invalid right child is smaller", "2,null,1", false),
                testCase("LeetCode valid example", "2,1,3", true),
                testCase("LeetCode invalid subtree example", "5,1,4,null,null,3,6", false),
                testCase("LeetCode duplicate example", "1,1", false),
                testCase("LeetCode invalid ancestor-bound example", "5,4,6,null,null,3,7", false),

                testCase("valid balanced tree", "5,3,7,2,4,6,8", true),
                testCase("valid incomplete tree", "8,3,10,1,6,null,14,null,null,4,7,13", true),
                testCase("valid all-negative tree", "-5,-10,-1", true),
                testCase("valid mixed-sign tree", "0,-1,1", true),
                testCase("valid tree using both integer bounds", "0,-2147483648,2147483647", true),
                testCase("valid Integer.MIN_VALUE root", "-2147483648,null,0", true),
                testCase("valid Integer.MAX_VALUE root", "2147483647,-2147483648", true),
                testCase("valid left-skewed tree", "5,4,null,3,null,2,null,1", true),
                testCase("valid right-skewed tree", "1,null,2,null,3,null,4,null,5", true),
                testCase("valid zigzag tree", "10,5,null,null,7,6,8", true),
                testCase("valid deeper mixed tree", "20,10,30,5,15,25,35,null,null,12,17,23,27,33,40", true),
                testCase("valid negative skewed tree", "0,-1,null,-2,null,-3", true),

                testCase("invalid root with both children reversed", "5,6,4", false),
                testCase("invalid node too large in left subtree", "10,5,null,null,12", false),
                testCase("invalid node too small in right subtree", "10,null,15,6", false),
                testCase("invalid right subtree from LeetCode", "5,1,4,null,null,3,6", false),
                testCase("invalid right subtree violates root bound", "5,4,6,null,null,3,7", false),
                testCase("invalid deep left-subtree ancestor bound", "20,10,30,5,15,25,35,null,null,9", false),
                testCase("invalid deep right-subtree ancestor bound", "20,10,30,null,null,25,35,19", false),
                testCase("invalid minimum root with oversized left child", "-2147483648,2147483647", false),
                testCase("invalid maximum root with undersized right child", "2147483647,null,-2147483648", false),

                testCase("duplicate left child", "5,5", false),
                testCase("duplicate right child", "5,null,5", false),
                testCase("duplicate value in left subtree", "5,3,null,null,5", false),
                testCase("duplicate value in right subtree", "5,null,7,5", false),
                testCase("all nodes have the same value", "2,2,2", false),
                testCase("duplicate values below the root", "5,3,7,2,4,6,6", false),
                testCase("left subtree contains the root value", "10,5,null,null,10", false),
                testCase("right subtree contains the root value", "10,null,15,10", false)
        );
    }

    private static Arguments testCase(String description, String serializedTree, boolean expected) {
        return Arguments.of(description, deserialize(serializedTree), expected);
    }

    @Test
    public void testLargeBalancedTree() {
        assertAllApproaches(buildBalancedBST(1, 1023), true);
    }

    private void assertAllApproaches(TreeNode root, boolean expected) {
        assertAll(
                () -> assertEquals(expected, test.isValidBST(root), "recursive bounds approach"),
                () -> assertEquals(expected, test.isValidBSTInOrder(root), "recursive in-order approach"),
                () -> assertEquals(expected, test.isValidBSTStack(root), "iterative stack approach")
        );
    }

    private TreeNode buildBalancedBST(int lo, int hi) {
        if (lo > hi) {
            return null;
        }

        int mid = lo + (hi - lo) / 2;
        TreeNode node = new TreeNode(mid);
        node.left = buildBalancedBST(lo, mid - 1);
        node.right = buildBalancedBST(mid + 1, hi);
        return node;
    }
}
