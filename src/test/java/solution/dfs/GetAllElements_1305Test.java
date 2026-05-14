package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class GetAllElements_1305Test {

    private final GetAllElements_1305 test = new GetAllElements_1305();

    @Test
    public void testHappyCases() {
        TreeNode t1 = new TreeNode(2); t1.left = new TreeNode(1); t1.right = new TreeNode(4);
        TreeNode t2 = new TreeNode(1); t2.left = new TreeNode(0); t2.right = new TreeNode(3);
        assertEquals(List.of(0, 1, 1, 2, 3, 4), test.getAllElements(t1, t2));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(List.of(1), test.getAllElements(new TreeNode(1), null));
        assertEquals(List.of(), test.getAllElements(null, null));
    }

    @Test
    public void testLargeCase() {
        TreeNode t1 = new TreeNode(5); t1.left = new TreeNode(3); t1.right = new TreeNode(7);
        TreeNode t2 = new TreeNode(4); t2.left = new TreeNode(2); t2.right = new TreeNode(6);
        assertEquals(List.of(2, 3, 4, 5, 6, 7), test.getAllElements(t1, t2));
    }

    @Test
    public void testBothNull() {
        assertEquals(List.of(), test.getAllElements(null, null));
    }

    @Test
    public void testFirstNull() {
        TreeNode t2 = new TreeNode(3); t2.left = new TreeNode(1); t2.right = new TreeNode(5);
        assertEquals(List.of(1, 3, 5), test.getAllElements(null, t2));
    }

    @Test
    public void testSecondNull() {
        TreeNode t1 = new TreeNode(2); t1.left = new TreeNode(1); t1.right = new TreeNode(3);
        assertEquals(List.of(1, 2, 3), test.getAllElements(t1, null));
    }

    @Test
    public void testSingleNodes() {
        assertEquals(List.of(1, 2), test.getAllElements(new TreeNode(1), new TreeNode(2)));
    }

    @Test
    public void testDuplicateValues() {
        TreeNode t1 = new TreeNode(2); t1.left = new TreeNode(1);
        TreeNode t2 = new TreeNode(2); t2.left = new TreeNode(1);
        assertEquals(List.of(1, 1, 2, 2), test.getAllElements(t1, t2));
    }

    @Test
    public void testLeftSkewedTrees() {
        TreeNode t1 = new TreeNode(3); t1.left = new TreeNode(2); t1.left.left = new TreeNode(1);
        TreeNode t2 = new TreeNode(6); t2.left = new TreeNode(5); t2.left.left = new TreeNode(4);
        assertEquals(List.of(1, 2, 3, 4, 5, 6), test.getAllElements(t1, t2));
    }

    @Test
    public void testRightSkewedTrees() {
        TreeNode t1 = new TreeNode(1); t1.right = new TreeNode(2); t1.right.right = new TreeNode(3);
        TreeNode t2 = new TreeNode(4); t2.right = new TreeNode(5); t2.right.right = new TreeNode(6);
        assertEquals(List.of(1, 2, 3, 4, 5, 6), test.getAllElements(t1, t2));
    }

    @Test
    public void testGiantCase() {
        // Build two balanced BSTs with many nodes
        TreeNode t1 = new TreeNode(8);
        t1.left = new TreeNode(4); t1.right = new TreeNode(12);
        t1.left.left = new TreeNode(2); t1.left.right = new TreeNode(6);
        t1.right.left = new TreeNode(10); t1.right.right = new TreeNode(14);

        TreeNode t2 = new TreeNode(7);
        t2.left = new TreeNode(3); t2.right = new TreeNode(11);
        t2.left.left = new TreeNode(1); t2.left.right = new TreeNode(5);
        t2.right.left = new TreeNode(9); t2.right.right = new TreeNode(13);

        assertEquals(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14), test.getAllElements(t1, t2));
    }
}
