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

public class PreorderTraversal144Test {

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
}