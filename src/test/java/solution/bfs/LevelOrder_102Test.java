package solution.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import library.tree.TreeParser;
import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class LevelOrder_102Test {

    private final LevelOrder_102 test = new LevelOrder_102();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9); root.right = new TreeNode(20);
        root.right.left = new TreeNode(15); root.right.right = new TreeNode(7);
        assertEquals(List.of(List.of(3), List.of(9, 20), List.of(15, 7)), test.levelOrder(root));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(List.of(), test.levelOrder(null));
        assertEquals(List.of(List.of(1)), test.levelOrder(new TreeNode(1)));
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        assertEquals(List.of(List.of(1), List.of(2, 3), List.of(4, 5)), test.levelOrder(root));
    }

    @Test
    public void testLeftSkewedTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        assertEquals(List.of(List.of(1), List.of(2), List.of(3)), test.levelOrder(root));
    }

    @Test
    public void testRightSkewedTree() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        assertEquals(List.of(List.of(1), List.of(2), List.of(3)), test.levelOrder(root));
    }

    @Test
    public void testNegativeValues() {
        TreeNode root = new TreeNode(-1);
        root.left = new TreeNode(-2);
        root.right = new TreeNode(-3);
        assertEquals(List.of(List.of(-1), List.of(-2, -3)), test.levelOrder(root));
    }

    @Test
    public void testSparseTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(5);
        assertEquals(List.of(List.of(1), List.of(2, 3), List.of(4, 5)), test.levelOrder(root));
    }

    @Test
    public void testDuplicateValues() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(1);
        root.right = new TreeNode(1);
        assertEquals(List.of(List.of(1), List.of(1, 1)), test.levelOrder(root));
    }

    @Test
    public void testFourLevels() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6); root.right.right = new TreeNode(7);
        root.left.left.left = new TreeNode(8);
        assertEquals(List.of(List.of(1), List.of(2, 3), List.of(4, 5, 6, 7), List.of(8)), test.levelOrder(root));
    }

    @Test
    public void testGiantDepthTree() {
        TreeNode root = new TreeNode(0);
        TreeNode current = root;
        for (int i = 1; i <= 100; i++) {
            current.left = new TreeNode(i);
            current = current.left;
        }
        List<List<Integer>> out = test.levelOrder(root);
        assertEquals(101, out.size());
        assertEquals(List.of(100), out.get(out.size() - 1));
    }

    @Test
    public void testPerfectBinaryTreeDepth3() {
        TreeNode root = TreeParser.deserialize("1,2,3,4,5,6,7");
        List<List<Integer>> result = test.levelOrder(root);
        assertEquals(List.of(List.of(1), List.of(2, 3), List.of(4, 5, 6, 7)), result);
        assertEquals(3, result.size());
        assertEquals(1, result.get(0).size());
        assertEquals(2, result.get(1).size());
        assertEquals(4, result.get(2).size());
    }

    @Test
    public void testImbalancedLeftDeeper() {
        // Left subtree depth 3, right subtree depth 1
        TreeNode root = TreeParser.deserialize("1,2,3,4,null,null,null,5");
        List<List<Integer>> result = test.levelOrder(root);
        assertEquals(4, result.size());
        assertEquals(List.of(1), result.get(0));
        assertEquals(List.of(2, 3), result.get(1));
        assertEquals(List.of(4), result.get(2));
        assertEquals(List.of(5), result.get(3));
    }

    @Test
    public void testLargeWideTreeViaTreeParser() {
        // Perfect binary tree of depth 7 = 127 nodes
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 127; i++) {
            if (i > 1) sb.append(",");
            sb.append(i);
        }
        TreeNode root = TreeParser.deserialize(sb.toString());
        List<List<Integer>> result = test.levelOrder(root);
        assertEquals(7, result.size());
        int totalNodes = result.stream().mapToInt(List::size).sum();
        assertEquals(127, totalNodes);
    }

    @Test
    public void testPropertyResultSizeEqualsDepth() {
        // Depth 5 left-skewed tree
        TreeNode root = TreeParser.deserialize("1,2,null,3,null,4,null,5");
        List<List<Integer>> result = test.levelOrder(root);
        assertEquals(5, result.size()); // depth == number of levels
    }

    @Test
    public void testPropertyTotalValuesEqualsTotalNodes() {
        TreeNode root = TreeParser.deserialize("10,20,30,40,50,null,60");
        List<List<Integer>> result = test.levelOrder(root);
        int totalNodes = result.stream().mapToInt(List::size).sum();
        assertEquals(6, totalNodes); // 6 non-null nodes in the tree
    }

    @Test
    public void testPropertyLevelSizeDoublesOrLess() {
        TreeNode root = TreeParser.deserialize("1,2,3,4,5,6,7,8,null,null,null,null,null,null,9");
        List<List<Integer>> result = test.levelOrder(root);
        for (int i = 1; i < result.size(); i++) {
            assertTrue(result.get(i).size() <= result.get(i - 1).size() * 2,
                    "Level " + i + " size " + result.get(i).size() +
                    " exceeds 2x of prior level size " + result.get(i - 1).size());
        }
    }

    @Test
    public void testTreeWithAllNegativeValues() {
        TreeNode root = TreeParser.deserialize("-10,-20,-30,-40,-50");
        List<List<Integer>> result = test.levelOrder(root);
        assertEquals(List.of(List.of(-10), List.of(-20, -30), List.of(-40, -50)), result);
    }

    @Test
    public void testTreeWithDuplicateValuesMultipleLevels() {
        TreeNode root = TreeParser.deserialize("5,5,5,5,5,5,5");
        List<List<Integer>> result = test.levelOrder(root);
        assertEquals(List.of(List.of(5), List.of(5, 5), List.of(5, 5, 5, 5)), result);
    }
}
