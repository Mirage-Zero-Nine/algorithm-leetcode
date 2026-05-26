package solutions.bfs;

import com.google.common.collect.Lists;
import library.tree.TreeParser;
import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

/**
 * @author BorisMirage
 * Time: 2022/10/30 12:12
 * Created with IntelliJ IDEA
 */

public class DistanceK_863Test {


    private DistanceK_863 test;

    @BeforeEach
    public void setUp() {
        test = new DistanceK_863();
    }

    @Test
    public void test() {
        List<Integer> expected = Lists.newArrayList(7, 4, 1);
        TreeNode testRoot = TreeParser.deserialize("3,5,1,6,2,0,8,null,null,7,4");
        TreeNode target = targetFinder(5, testRoot);
        assertIterableEquals(expected, test.distanceK(testRoot, target, 2));
    }

    @Test
    public void testApproach2() {
        List<Integer> expected = Lists.newArrayList(7, 4, 1);
        TreeNode testRoot = TreeParser.deserialize("3,5,1,6,2,0,8,null,null,7,4");
        TreeNode target = targetFinder(5, testRoot);
        assertIterableEquals(expected, test.distanceKDFSBFS(testRoot, target, 2));
    }

    @Test
    public void testDistanceZeroReturnsTarget() {
        TreeNode root = TreeParser.deserialize("1,2,3");
        TreeNode target = targetFinder(2, root);
        assertIterableEquals(List.of(2), test.distanceK(root, target, 0));
    }

    @Test
    public void testKTooLargeReturnsEmpty() {
        TreeNode root = TreeParser.deserialize("1,2,3");
        TreeNode target = targetFinder(2, root);
        assertIterableEquals(List.of(), test.distanceK(root, target, 5));
    }

    @Test
    public void testSingleNodeTree() {
        TreeNode root = new TreeNode(9);
        assertIterableEquals(List.of(9), test.distanceK(root, root, 0));
        assertIterableEquals(List.of(), test.distanceK(root, root, 1));
    }

    @Test
    public void testTargetIsRoot() {
        TreeNode root = TreeParser.deserialize("10,6,14,4,8,12,16");
        assertIterableEquals(List.of(4, 8, 12, 16), test.distanceK(root, root, 2));
    }

    @Test
    public void testTargetIsLeaf() {
        TreeNode root = TreeParser.deserialize("10,6,14,4,8,12,16");
        TreeNode target = targetFinder(4, root);
        assertIterableEquals(List.of(8, 10), test.distanceK(root, target, 2));
    }

    @Test
    public void testUnbalancedTreeHappyCase() {
        TreeNode root = TreeParser.deserialize("1,2,null,3,null,4,null,5");
        TreeNode target = targetFinder(3, root);
        assertIterableEquals(List.of(1, 5), test.distanceK(root, target, 2).stream().sorted().toList());
    }

    @Test
    public void testApproach2DistanceZero() {
        TreeNode root = TreeParser.deserialize("7,3,9");
        TreeNode target = targetFinder(9, root);
        assertIterableEquals(List.of(9), test.distanceKDFSBFS(root, target, 0));
    }

    @Test
    public void testApproach2LargeK() {
        TreeNode root = TreeParser.deserialize("7,3,9");
        TreeNode target = targetFinder(9, root);
        assertIterableEquals(List.of(), test.distanceKDFSBFS(root, target, 4));
    }

    @Test
    public void testGiantLinearTree() {
        TreeNode root = new TreeNode(0);
        TreeNode current = root;
        for (int i = 1; i <= 200; i++) {
            current.right = new TreeNode(i);
            current = current.right;
        }
        TreeNode target = targetFinder(100, root);
        List<Integer> out = test.distanceK(root, target, 50);
        assertIterableEquals(List.of(50, 150), out.stream().sorted().toList());
    }

    private TreeNode targetFinder(int val, TreeNode root) {
        if (root == null) {
            return null;
        }

        if (root.val == val) {
            return root;
        }

        TreeNode left = targetFinder(val, root.left), right = targetFinder(val, root.right);
        return left == null ? right : left;
    }
}
