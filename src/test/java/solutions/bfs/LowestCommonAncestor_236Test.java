package solutions.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

public class LowestCommonAncestor_236Test {

    private final LowestCommonAncestor_236 test = new LowestCommonAncestor_236();

    @Test
    public void testHappyCases() {
        TreeNode root = new TreeNode(3);
        TreeNode n5 = new TreeNode(5); TreeNode n1 = new TreeNode(1);
        root.left = n5; root.right = n1;
        n5.left = new TreeNode(6); n5.right = new TreeNode(2);
        n1.left = new TreeNode(0); n1.right = new TreeNode(8);
        assertEquals(3, test.lowestCommonAncestor(root, n5, n1).val);
    }

    @Test
    public void testEdgeCases() {
        TreeNode root = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        root.left = n2;
        assertEquals(1, test.lowestCommonAncestor(root, root, n2).val);
    }

    @Test
    public void testLargeCase() {
        TreeNode root = new TreeNode(3);
        TreeNode n5 = new TreeNode(5); TreeNode n1 = new TreeNode(1);
        TreeNode n6 = new TreeNode(6); TreeNode n4 = new TreeNode(4);
        root.left = n5; root.right = n1;
        n5.left = n6; n5.right = n4;
        assertEquals(5, test.lowestCommonAncestor(root, n6, n4).val);
    }

    @Test
    public void testBothMethodsWhenNodesInDifferentSubtrees() {
        TreeNode root = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        root.left = n2; root.right = n3;
        assertEquals(1, test.lowestCommonAncestor(root, n2, n3).val);
        assertEquals(1, test.bfsWithSet(root, n2, n3).val);
    }

    @Test
    public void testBothMethodsWhenOneNodeIsAncestor() {
        TreeNode root = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        root.left = n2; n2.left = n3;
        assertEquals(2, test.lowestCommonAncestor(root, n2, n3).val);
        assertEquals(2, test.bfsWithSet(root, n2, n3).val);
    }

    @Test
    public void testBothMethodsSameNodeInput() {
        TreeNode root = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        root.left = n2;
        assertEquals(2, test.lowestCommonAncestor(root, n2, n2).val);
        assertEquals(2, test.bfsWithSet(root, n2, n2).val);
    }

    @Test
    public void testDeepTreeLcaNearRoot() {
        TreeNode root = new TreeNode(10);
        TreeNode a = new TreeNode(5);
        TreeNode b = new TreeNode(15);
        TreeNode c = new TreeNode(3);
        TreeNode d = new TreeNode(7);
        TreeNode e = new TreeNode(13);
        TreeNode f = new TreeNode(17);
        root.left = a; root.right = b;
        a.left = c; a.right = d;
        b.left = e; b.right = f;
        assertEquals(10, test.lowestCommonAncestor(root, c, e).val);
        assertEquals(10, test.bfsWithSet(root, c, e).val);
    }

    @Test
    public void testDeepTreeLcaAtIntermediateNode() {
        TreeNode root = new TreeNode(8);
        TreeNode n4 = new TreeNode(4);
        TreeNode n12 = new TreeNode(12);
        TreeNode n10 = new TreeNode(10);
        TreeNode n14 = new TreeNode(14);
        TreeNode n9 = new TreeNode(9);
        TreeNode n11 = new TreeNode(11);
        root.left = n4; root.right = n12;
        n12.left = n10; n12.right = n14;
        n10.left = n9; n10.right = n11;
        assertEquals(10, test.lowestCommonAncestor(root, n9, n11).val);
        assertEquals(10, test.bfsWithSet(root, n9, n11).val);
    }

    @Test
    public void testTreeWithNegativeValues() {
        TreeNode root = new TreeNode(-1);
        TreeNode n2 = new TreeNode(-2);
        TreeNode n3 = new TreeNode(-3);
        root.left = n2; root.right = n3;
        assertEquals(-1, test.lowestCommonAncestor(root, n2, n3).val);
        assertEquals(-1, test.bfsWithSet(root, n2, n3).val);
    }

    @Test
    public void testGiantBalancedTree() {
        TreeNode[] nodes = new TreeNode[256];
        for (int i = 1; i < nodes.length; i++) {
            nodes[i] = new TreeNode(i);
        }
        for (int i = 1; i <= 127; i++) {
            nodes[i].left = nodes[i * 2];
            nodes[i].right = nodes[i * 2 + 1];
        }
        TreeNode root = nodes[1];
        TreeNode p = nodes[200], q = nodes[201];
        assertEquals(100, test.lowestCommonAncestor(root, p, q).val);
        assertEquals(100, test.bfsWithSet(root, p, q).val);
    }
}
