package solution.bfs;

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

public class DistanceK863Test {


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