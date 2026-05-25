package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import library.tree.TreeParser;
import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2023/04/15 23:38
 * Created with IntelliJ IDEA
 */

public class PathSum_437Test {

    @Test
    public void testOverflow() {
        PathSum_437 test = new PathSum_437();
        TreeNode root = TreeParser.deserialize("1000000000,1000000000,null,294967296,null,1000000000,null,1000000000,null,1000000000");
        assertEquals(0, test.pathSumWithHashMap(root, 0));
        assertEquals(0, test.pathSum(root, 0));
    }

    @Test
    public void testNormal() {
        PathSum_437 test = new PathSum_437();

        TreeNode root = TreeParser.deserialize("10,5,-3,3,2,null,11,3,-2,null,1");
        assertEquals(3, test.pathSumWithHashMap(root, 8));
        assertEquals(3, test.pathSum(root, 8));
    }

    @Test
    public void testNormal1() {
        PathSum_437 test = new PathSum_437();

        TreeNode root = TreeParser.deserialize("5,4,8,11,null,13,4,7,2,null,null,5,1");
        assertEquals(3, test.pathSumWithHashMap(root, 22));
        assertEquals(3, test.pathSum(root, 22));
    }

    @Test
    public void testNullRoot() {
        PathSum_437 test = new PathSum_437();
        assertEquals(0, test.pathSum(null, 5));
        assertEquals(0, test.pathSumWithHashMap(null, 5));
    }

    @Test
    public void testSingleNodeMatch() {
        PathSum_437 test = new PathSum_437();
        TreeNode root = new TreeNode(5);
        assertEquals(1, test.pathSum(root, 5));
    }

    @Test
    public void testSingleNodeMatchHashMap() {
        PathSum_437 test = new PathSum_437();
        TreeNode root = new TreeNode(5);
        assertEquals(1, test.pathSumWithHashMap(root, 5));
    }

    @Test
    public void testSingleNodeNoMatch() {
        PathSum_437 test = new PathSum_437();
        TreeNode root = new TreeNode(5);
        assertEquals(0, test.pathSum(root, 3));
        PathSum_437 test2 = new PathSum_437();
        assertEquals(0, test2.pathSumWithHashMap(root, 3));
    }

    @Test
    public void testNegativeValues() {
        PathSum_437 test = new PathSum_437();
        TreeNode root = new TreeNode(-2);
        root.left = new TreeNode(-3);
        // paths: -2 (sum=-2), -3 (sum=-3), -2->-3 (sum=-5)
        assertEquals(1, test.pathSum(root, -5));
        PathSum_437 test2 = new PathSum_437();
        assertEquals(1, test2.pathSumWithHashMap(root, -5));
    }

    @Test
    public void testAllZeros() {
        PathSum_437 test = new PathSum_437();
        // tree: 0 -> 0 -> 0 (left skewed)
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(0);
        root.left.left = new TreeNode(0);
        // paths summing to 0: node0, node0, node0, 0->0, 0->0, 0->0->0 = 6
        assertEquals(6, test.pathSum(root, 0));
        PathSum_437 test2 = new PathSum_437();
        assertEquals(6, test2.pathSumWithHashMap(root, 0));
    }

    @Test
    public void testTargetZeroNoMatch() {
        PathSum_437 test = new PathSum_437();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        assertEquals(0, test.pathSum(root, 0));
        PathSum_437 test2 = new PathSum_437();
        assertEquals(0, test2.pathSumWithHashMap(root, 0));
    }

    @Test
    public void testGiantTree() {
        PathSum_437 test = new PathSum_437();
        // Build a left-skewed tree of depth 100, all values 1, target = 1
        // Each node with value 1 is a path of sum 1, so count = 100
        TreeNode root = new TreeNode(1);
        TreeNode cur = root;
        for (int i = 1; i < 100; i++) {
            cur.left = new TreeNode(1);
            cur = cur.left;
        }
        assertEquals(100, test.pathSum(root, 1));
        PathSum_437 test2 = new PathSum_437();
        assertEquals(100, test2.pathSumWithHashMap(root, 1));
    }

    @Test
    public void testMultiplePathsThroughInternalNodes() {
        // Tree:       1
        //           /   \
        //          2     3
        //         / \
        //        3   1
        // target=3: paths are [2,1(left child)], [3(left-left)], [3(right child)] = 3 paths
        PathSum_437 test = new PathSum_437();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(1);
        // paths summing to 3: 3(root.right), 3(root.left.left), 2->1(root.left->root.left.right), 1->2(root->root.left) = 4
        assertEquals(4, test.pathSum(root, 3));
        assertEquals(4, new PathSum_437().pathSumWithHashMap(root, 3));
    }

    @Test
    public void testNegativeValuesCancelToTarget() {
        // Tree:    1
        //         / \
        //       -1    2
        //       /
        //      1
        // target=0: paths summing to 0: [1,-1], [-1,1] = 2
        PathSum_437 test = new PathSum_437();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(-1);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(1);
        assertEquals(2, test.pathSum(root, 0));
        assertEquals(2, new PathSum_437().pathSumWithHashMap(root, 0));
    }

    @Test
    public void testLongChainPrefixSums() {
        // Chain: 1->1->1->1->1, target=3
        // Paths of length 3: starting at index 0,1,2 = 3 paths
        PathSum_437 test = new PathSum_437();
        TreeNode root = new TreeNode(1);
        TreeNode cur = root;
        for (int i = 1; i < 5; i++) {
            cur.left = new TreeNode(1);
            cur = cur.left;
        }
        assertEquals(3, test.pathSum(root, 3));
        assertEquals(3, new PathSum_437().pathSumWithHashMap(root, 3));
    }

    @Test
    public void testAllSameValueManyPaths() {
        // Complete binary tree depth 3, all values 2, target=4
        // Tree:      2
        //          /   \
        //         2     2
        //        / \   / \
        //       2   2 2   2
        // Paths summing to 4: each parent->child pair = 6 edges, so 6 paths
        PathSum_437 test = new PathSum_437();
        TreeNode root = TreeParser.deserialize("2,2,2,2,2,2,2");
        assertEquals(6, test.pathSum(root, 4));
        assertEquals(6, new PathSum_437().pathSumWithHashMap(root, 4));
    }

    @Test
    public void testNegativeBacktrackPath() {
        // Tree:    5
        //         /
        //        3
        //       /
        //      -1
        //      /
        //      3
        // target=5: paths: [5], [3,-1,3] = 2
        PathSum_437 test = new PathSum_437();
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(-1);
        root.left.left.left = new TreeNode(3);
        assertEquals(2, test.pathSum(root, 5));
        assertEquals(2, new PathSum_437().pathSumWithHashMap(root, 5));
    }

    @Test
    public void testLargeTargetNoPath() {
        // No path can sum to a very large target
        PathSum_437 test = new PathSum_437();
        TreeNode root = TreeParser.deserialize("1,2,3,4,5,6,7");
        assertEquals(0, test.pathSum(root, 1000000));
        assertEquals(0, new PathSum_437().pathSumWithHashMap(root, 1000000));
    }

    @Test
    public void testResultNonNegativeProperty() {
        // Property: pathSum should never return negative
        PathSum_437 test = new PathSum_437();
        TreeNode root = TreeParser.deserialize("1,-2,3,-4,5,-6,7");
        int result = test.pathSum(root, 99);
        assertTrue(result >= 0, "Path count must be non-negative");
        int result2 = new PathSum_437().pathSumWithHashMap(root, 99);
        assertTrue(result2 >= 0, "Path count must be non-negative");
    }

    @Test
    public void testBothMethodsAgree() {
        // Property: both implementations should return the same result
        TreeNode root = TreeParser.deserialize("3,1,-1,2,4,0,5,-2,null,null,3");
        int target = 4;
        PathSum_437 test1 = new PathSum_437();
        int bruteForce = test1.pathSum(root, target);
        PathSum_437 test2 = new PathSum_437();
        int hashMap = test2.pathSumWithHashMap(root, target);
        assertEquals(bruteForce, hashMap, "Both methods must agree on path count");
    }
}
