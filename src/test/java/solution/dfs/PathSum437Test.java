package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.TreeParser;
import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2023/04/15 23:38
 * Created with IntelliJ IDEA
 */

public class PathSum437Test {

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
}