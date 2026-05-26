package solutions.dfs;

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

public class VerticalOrder_314Test {

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

    @Test
    void testNullRoot() {
        assertEquals(List.of(), test.verticalOrder(null));
    }

    @Test
    void testSingleNode() {
        TreeNode root = new TreeNode(1);
        assertEquals(List.of(List.of(1)), test.verticalOrder(root));
    }

    @Test
    void testOnlyLeftChildren() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(1);

        assertEquals(List.of(List.of(1), List.of(2), List.of(3)), test.verticalOrder(root));
    }

    @Test
    void testOnlyRightChildren() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);

        assertEquals(List.of(List.of(1), List.of(2), List.of(3)), test.verticalOrder(root));
    }

    @Test
    void testSameColumnSameHeightUsesDfsEncounterOrder() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(5);

        assertEquals(List.of(List.of(2), List.of(1, 4, 5), List.of(3)), test.verticalOrder(root));
    }

    @Test
    void testNegativeAndDuplicateValues() {
        TreeNode root = new TreeNode(-1);
        root.left = new TreeNode(-1);
        root.right = new TreeNode(-1);
        root.left.left = new TreeNode(-2);
        root.right.right = new TreeNode(-2);

        assertEquals(List.of(List.of(-2), List.of(-1), List.of(-1), List.of(-1), List.of(-2)), test.verticalOrder(root));
    }

    @Test
    void testBalancedTreeHappyCase() {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);

        assertEquals(List.of(List.of(1), List.of(2), List.of(4, 3, 5), List.of(6), List.of(7)), test.verticalOrder(root));
    }

    @Test
    void testEdgeCaseWithRepeatedColumnTransitions() {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(6);
        root.right = new TreeNode(14);
        root.left.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);

        assertEquals(List.of(List.of(6, 7), List.of(10, 8), List.of(14)), test.verticalOrder(root));
    }

    @Test
    void testGiantMixedTree() {
        TreeNode root = new TreeNode(50);
        root.left = new TreeNode(30);
        root.right = new TreeNode(70);
        root.left.left = new TreeNode(20);
        root.left.right = new TreeNode(40);
        root.right.left = new TreeNode(60);
        root.right.right = new TreeNode(80);
        root.left.left.left = new TreeNode(10);
        root.left.left.right = new TreeNode(25);
        root.left.right.left = new TreeNode(35);
        root.left.right.right = new TreeNode(45);
        root.right.left.left = new TreeNode(55);
        root.right.left.right = new TreeNode(65);
        root.right.right.left = new TreeNode(75);
        root.right.right.right = new TreeNode(90);

        assertEquals(
            List.of(
                List.of(10),
                List.of(20),
                List.of(30, 25, 35, 55),
                List.of(50, 40, 60),
                List.of(70, 45, 65, 75),
                List.of(80),
                List.of(90)
            ),
            test.verticalOrder(root)
        );
    }
}
