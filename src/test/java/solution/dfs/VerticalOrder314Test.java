package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author BorisMirage
 * Time: 2023/04/21 14:24
 * Created with IntelliJ IDEA
 */

public class VerticalOrder314Test {

    private final VerticalOrder_314 test = new VerticalOrder_314();

    @Test
    void testVerticalOrder() {
        /*
         *      3
         *     / \
         *    9   8
         *   / \   \
         *  4   0   1
         *       \
         *        7
         *       / \
         *      2   5
         */
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(0);
        root.left.right.right = new TreeNode(7);
        root.left.right.right.left = new TreeNode(2);
        root.left.right.right.right = new TreeNode(5);
        root.right = new TreeNode(8);
        root.right.right = new TreeNode(1);

        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Collections.singletonList(4));
        expected.add(Collections.singletonList(9));
        expected.add(Arrays.asList(3, 0, 2));
        expected.add(Arrays.asList(8, 7));
        expected.add(Arrays.asList(1, 5));

        List<List<Integer>> result = test.verticalOrder(root);
        assertEquals(expected, result);
    }
}