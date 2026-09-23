package solutions.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;

/** Contract and regression tests for both LCA implementations. */
@Timeout(15)
public class LowestCommonAncestor_236Test {

    private final LowestCommonAncestor_236 test = new LowestCommonAncestor_236();

    @Test
    public void officialExampleSplittingAtRoot() {
        TreeNode root = officialTree();
        assertBoth(root, find(root, 5), find(root, 1));
    }

    @Test
    public void officialExampleAncestorIsAnswer() {
        TreeNode root = officialTree();
        assertBoth(root, find(root, 5), find(root, 4));
    }

    @Test
    public void officialExampleTwoNodeMinimum() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        assertBoth(root, root, root.left);
    }

    @Test
    public void oneNodeIsRootOnRight() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(7);
        assertBoth(root, root, root.right);
    }

    @Test
    public void oneNodeIsRootOnLeft() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(7);
        assertBoth(root, root.left, root);
    }

    @Test
    public void siblingLeavesUnderLeftChild() {
        TreeNode root = new TreeNode(10);
        TreeNode parent = new TreeNode(5);
        root.left = parent;
        parent.left = new TreeNode(2);
        parent.right = new TreeNode(8);
        assertBoth(root, parent.left, parent.right);
    }

    @Test
    public void siblingLeavesUnderRightChild() {
        TreeNode root = new TreeNode(10);
        TreeNode parent = new TreeNode(15);
        root.right = parent;
        parent.left = new TreeNode(12);
        parent.right = new TreeNode(18);
        assertBoth(root, parent.left, parent.right);
    }

    @Test
    public void cousinsAcrossTheRoot() {
        TreeNode root = new TreeNode(20);
        TreeNode left = new TreeNode(10);
        TreeNode right = new TreeNode(30);
        root.left = left;
        root.right = right;
        left.left = new TreeNode(5);
        left.right = new TreeNode(15);
        right.left = new TreeNode(25);
        right.right = new TreeNode(35);
        assertBoth(root, left.left, right.right);
    }

    @Test
    public void bothTargetsRemainInLeftSubtree() {
        TreeNode root = new TreeNode(50);
        TreeNode parent = new TreeNode(25);
        root.left = parent;
        parent.left = new TreeNode(10);
        parent.right = new TreeNode(40);
        assertBoth(root, parent.left, parent.right);
    }

    @Test
    public void bothTargetsRemainInRightSubtree() {
        TreeNode root = new TreeNode(50);
        TreeNode parent = new TreeNode(75);
        root.right = parent;
        parent.left = new TreeNode(60);
        parent.right = new TreeNode(90);
        assertBoth(root, parent.left, parent.right);
    }

    @Test
    public void ancestorIsTheLeftTargetSeveralLevelsDown() {
        TreeNode root = new TreeNode(8);
        TreeNode ancestor = new TreeNode(4);
        TreeNode target = new TreeNode(1);
        root.left = ancestor;
        ancestor.left = new TreeNode(2);
        ancestor.right = new TreeNode(6);
        ancestor.left.left = target;
        assertBoth(root, ancestor, target);
    }

    @Test
    public void ancestorIsTheRightTargetSeveralLevelsDown() {
        TreeNode root = new TreeNode(8);
        TreeNode ancestor = new TreeNode(12);
        TreeNode target = new TreeNode(15);
        root.right = ancestor;
        ancestor.left = new TreeNode(10);
        ancestor.right = new TreeNode(14);
        ancestor.right.right = target;
        assertBoth(root, target, ancestor);
    }

    @Test
    public void sparseCrossParentTree() {
        TreeNode root = new TreeNode(0);
        TreeNode left = new TreeNode(-2);
        TreeNode right = new TreeNode(2);
        root.left = left;
        root.right = right;
        left.right = new TreeNode(-1);
        right.left = new TreeNode(1);
        assertBoth(root, left.right, right.left);
    }

    @Test
    public void alternatingLeftRightSpine() {
        TreeNode root = new TreeNode(0);
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        root.left = n1;
        n1.right = n2;
        n2.left = n3;
        n3.right = n4;
        n4.left = n5;
        assertBoth(root, n2, n5);
    }

    @Test
    public void negativeAndZeroNodeValues() {
        TreeNode root = new TreeNode(-1);
        TreeNode left = new TreeNode(-100);
        TreeNode right = new TreeNode(0);
        root.left = left;
        root.right = right;
        left.left = new TreeNode(-200);
        right.right = new TreeNode(200);
        assertBoth(root, left.left, right.right);
    }

    @Test
    public void officialValueBoundaries() {
        TreeNode root = new TreeNode(0);
        TreeNode min = new TreeNode(-1_000_000_000);
        TreeNode max = new TreeNode(1_000_000_000);
        root.left = min;
        root.right = max;
        assertBoth(root, min, max);
    }

    @Test
    public void sameNodeImplementationEdgeCase() {
        // LeetCode supplies distinct p and q; this verifies the intentionally supported same-node extension.
        TreeNode root = new TreeNode(4);
        TreeNode target = new TreeNode(2);
        root.left = target;
        target.right = new TreeNode(3);
        assertBoth(root, target, target);
    }

    @Test
    public void returnedNodeUsesReferenceIdentity() {
        TreeNode root = new TreeNode(100);
        TreeNode parent = new TreeNode(-50);
        TreeNode p = new TreeNode(-75);
        TreeNode q = new TreeNode(-25);
        root.left = parent;
        parent.left = p;
        parent.right = q;
        assertSame(parent, test.lowestCommonAncestor(root, p, q));
        IdentityHashMap<TreeNode, TreeNode> copies = new IdentityHashMap<>();
        TreeNode copiedRoot = copy(root, copies);
        assertSame(copies.get(parent), test.lowestCommonAncestorBfsWithSet(copiedRoot, copies.get(p), copies.get(q)));
    }

    @Test
    public void repeatedCallsDoNotLeakStateAcrossTrees() {
        TreeNode first = new TreeNode(1);
        first.left = new TreeNode(2);
        first.right = new TreeNode(3);
        TreeNode second = new TreeNode(10);
        second.left = new TreeNode(20);
        second.right = new TreeNode(30);
        assertBoth(first, first.left, first.right);
        assertBoth(second, second.left, second.right);
        assertBoth(first, first, first.left);
    }

    @Test
    public void inputTopologyAndValuesAreNotMutated() {
        TreeNode root = officialTree();
        IdentityHashMap<TreeNode, TreeNode[]> before = snapshot(root);
        TreeNode expected = find(root, 5);
        assertBoth(root, find(root, 6), find(root, 4));
        assertSame(expected, find(root, 5));
        assertSnapshotUnchanged(before);
    }

    @Test
    public void deepLeftSkewedTree() {
        TreeNode root = new TreeNode(0);
        TreeNode[] nodes = new TreeNode[2_000];
        nodes[0] = root;
        for (int i = 1; i < nodes.length; i++) {
            nodes[i] = new TreeNode(i);
            nodes[i - 1].left = nodes[i];
        }
        assertBoth(root, nodes[1_234], nodes[1_999]);
    }

    @Test
    public void completeTreeAllLevels() {
        TreeNode[] nodes = completeNodes(511);
        assertBoth(nodes[0], nodes[255], nodes[510]);
    }

    @Test
    public void giantMaximumNodeCountBalancedTree() {
        TreeNode[] nodes = completeNodes(100_000);
        assertBoth(nodes[0], nodes[99_998], nodes[99_999]);
    }

    @Test
    public void allPairsOnSeededCompleteTreeMatchIndependentOracle() {
        TreeNode[] nodes = completeNodes(127);
        for (int i = 0; i < nodes.length; i += 7) {
            for (int j = i + 1; j < nodes.length; j += 11) {
                assertBoth(nodes[0], nodes[i], nodes[j]);
            }
        }
    }

    @Test
    public void exhaustiveOrderedPairsOnSmallCompleteTreeMatchIndependentOracle() {
        TreeNode[] nodes = completeNodes(31);
        for (TreeNode p : nodes) {
            for (TreeNode q : nodes) {
                assertBoth(nodes[0], p, q);
            }
        }
    }

    @Test
    public void seededSparseTreesMatchIndependentParentChainOracle() {
        Random random = new Random(236_2026L);
        for (int treeNumber = 0; treeNumber < 40; treeNumber++) {
            int size = 2 + random.nextInt(99);
            TreeNode[] nodes = randomTree(size, random);
            for (int pair = 0; pair < 8; pair++) {
                int p = random.nextInt(size);
                int q = random.nextInt(size - 1);
                if (q >= p) {
                    q++;
                }
                assertBoth(nodes[0], nodes[p], nodes[q]);
            }
        }
    }

    @Test
    public void everyLevelBoundaryPairInCompleteTree() {
        TreeNode[] nodes = completeNodes(2_047);
        for (int level = 1; level <= 10; level++) {
            int first = (1 << level) - 1;
            int last = (1 << (level + 1)) - 2;
            assertBoth(nodes[0], nodes[first], nodes[last]);
            assertBoth(nodes[0], nodes[first], nodes[(first + last) / 2]);
        }
    }

    @Test
    public void repeatedCallsWithReversedArgumentsReturnSameReference() {
        TreeNode root = officialTree();
        TreeNode p = find(root, 7);
        TreeNode q = find(root, 4);
        TreeNode expected = find(root, 2);
        assertSame(expected, test.lowestCommonAncestor(root, p, q));
        assertSame(expected, test.lowestCommonAncestor(root, q, p));

        IdentityHashMap<TreeNode, TreeNode> copies = new IdentityHashMap<>();
        TreeNode copyRoot = copy(root, copies);
        TreeNode copyP = copies.get(p);
        TreeNode copyQ = copies.get(q);
        TreeNode copyExpected = copies.get(expected);
        assertSame(copyExpected, test.lowestCommonAncestorBfsWithSet(copyRoot, copyP, copyQ));
        assertSame(copyExpected, test.lowestCommonAncestorBfsWithSet(copyRoot, copyQ, copyP));
    }

    @Test
    public void sparseTreeWithDeepCommonAncestor() {
        TreeNode root = new TreeNode(1);
        TreeNode ancestor = new TreeNode(2);
        root.left = ancestor;
        TreeNode left = ancestor;
        TreeNode right = ancestor;
        for (int i = 3; i <= 500; i++) {
            left.left = new TreeNode(i);
            left = left.left;
            right.right = new TreeNode(1_000 + i);
            right = right.right;
        }
        assertBoth(root, left, right);
    }

    private void assertBoth(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode expected = parentChainOracle(root, p, q);
        IdentityHashMap<TreeNode, TreeNode[]> before = snapshot(root);
        assertSame(expected, test.lowestCommonAncestor(root, p, q));
        assertSnapshotUnchanged(before);

        IdentityHashMap<TreeNode, TreeNode> copies = new IdentityHashMap<>();
        TreeNode copiedRoot = copy(root, copies);
        TreeNode copiedExpected = copies.get(expected);
        assertSame(copiedExpected, test.lowestCommonAncestorBfsWithSet(copiedRoot, copies.get(p), copies.get(q)));
    }

    private static TreeNode officialTree() {
        TreeNode root = new TreeNode(3);
        TreeNode n5 = new TreeNode(5);
        TreeNode n1 = new TreeNode(1);
        root.left = n5;
        root.right = n1;
        n5.left = new TreeNode(6);
        n5.right = new TreeNode(2);
        n1.left = new TreeNode(0);
        n1.right = new TreeNode(8);
        n5.right.left = new TreeNode(7);
        n5.right.right = new TreeNode(4);
        return root;
    }

    private static TreeNode find(TreeNode root, int value) {
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode current = queue.remove();
            if (current.val == value) {
                return current;
            }
            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }
        throw new AssertionError("node not found: " + value);
    }

    private static TreeNode parentChainOracle(TreeNode root, TreeNode p, TreeNode q) {
        IdentityHashMap<TreeNode, TreeNode> parent = new IdentityHashMap<>();
        Deque<TreeNode> queue = new ArrayDeque<>();
        parent.put(root, null);
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode current = queue.remove();
            if (current.left != null) {
                parent.put(current.left, current);
                queue.add(current.left);
            }
            if (current.right != null) {
                parent.put(current.right, current);
                queue.add(current.right);
            }
        }
        Set<TreeNode> ancestors = Collections.newSetFromMap(new IdentityHashMap<>());
        for (TreeNode current = p; current != null; current = parent.get(current)) {
            ancestors.add(current);
        }
        for (TreeNode current = q; current != null; current = parent.get(current)) {
            if (ancestors.contains(current)) {
                return current;
            }
        }
        throw new AssertionError("targets must exist in the tree");
    }

    private static TreeNode copy(TreeNode root, IdentityHashMap<TreeNode, TreeNode> copies) {
        TreeNode copiedRoot = new TreeNode(root.val);
        copies.put(root, copiedRoot);
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode current = queue.remove();
            TreeNode copied = copies.get(current);
            if (current.left != null) {
                TreeNode copiedLeft = new TreeNode(current.left.val);
                copied.left = copiedLeft;
                copies.put(current.left, copiedLeft);
                queue.add(current.left);
            }
            if (current.right != null) {
                TreeNode copiedRight = new TreeNode(current.right.val);
                copied.right = copiedRight;
                copies.put(current.right, copiedRight);
                queue.add(current.right);
            }
        }
        return copiedRoot;
    }

    private static IdentityHashMap<TreeNode, TreeNode[]> snapshot(TreeNode root) {
        IdentityHashMap<TreeNode, TreeNode[]> result = new IdentityHashMap<>();
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode current = queue.remove();
            result.put(current, new TreeNode[]{current.left, current.right, new TreeNode(current.val)});
            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }
        return result;
    }

    private static void assertSnapshotUnchanged(IdentityHashMap<TreeNode, TreeNode[]> before) {
        for (var entry : before.entrySet()) {
            TreeNode node = entry.getKey();
            TreeNode[] state = entry.getValue();
            assertSame(state[0], node.left);
            assertSame(state[1], node.right);
            assertEquals(state[2].val, node.val);
        }
    }

    private static TreeNode[] completeNodes(int size) {
        TreeNode[] nodes = new TreeNode[size];
        for (int i = 0; i < size; i++) {
            nodes[i] = new TreeNode(i - size / 2);
        }
        for (int i = 0; i < size; i++) {
            int left = i * 2 + 1;
            int right = left + 1;
            if (left < size) {
                nodes[i].left = nodes[left];
            }
            if (right < size) {
                nodes[i].right = nodes[right];
            }
        }
        return nodes;
    }

    private static TreeNode[] randomTree(int size, Random random) {
        TreeNode[] nodes = new TreeNode[size];
        List<TreeNode> available = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            nodes[i] = new TreeNode(-1_000_000 + i);
        }
        available.add(nodes[0]);
        for (int i = 1; i < size; i++) {
            int parentIndex = random.nextInt(available.size());
            TreeNode parent = available.get(parentIndex);
            if (parent.left == null && parent.right == null) {
                if (random.nextBoolean()) {
                    parent.left = nodes[i];
                } else {
                    parent.right = nodes[i];
                }
            } else if (parent.left == null) {
                parent.left = nodes[i];
            } else {
                parent.right = nodes[i];
            }
            if (parent.left != null && parent.right != null) {
                available.remove(parentIndex);
            }
            available.add(nodes[i]);
        }
        return nodes;
    }
}
