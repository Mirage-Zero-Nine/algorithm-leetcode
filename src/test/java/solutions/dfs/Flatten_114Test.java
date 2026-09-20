package solutions.dfs;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Queue;
import java.util.function.Consumer;
import java.util.function.Supplier;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

/** Tests both in-place implementations of LeetCode 114. */
public class Flatten_114Test {

    private final Flatten_114 solution = new Flatten_114();

    @Test
    public void nullRootIsAcceptedByBothApproaches() {
        assertDoesNotThrow(() -> {
            solution.flatten(null);
            solution.flattenWithStack(null);
        });
    }

    @Test
    public void singleNodeKeepsItsIdentity() {
        assertBoth(() -> new TreeNode(1));
    }

    @Test
    public void canonicalExampleUsesPreorder() {
        assertBoth(() -> levelOrder(1, 2, 5, 3, 4, null, 6));
    }

    @Test
    public void completeBalancedTreeUsesPreorder() {
        assertBoth(() -> levelOrder(1, 2, 3, 4, 5, 6, 7));
    }

    @Test
    public void sparseTreePreservesGapsInPreorder() {
        assertBoth(() -> levelOrder(8, 4, 12, null, 6, 10, null, 5, null, null, 11));
    }

    @Test
    public void onlyLeftChildrenBecomeTheRightChain() {
        assertBoth(() -> leftChain(7));
    }

    @Test
    public void onlyRightChildrenRemainInOrder() {
        assertBoth(() -> rightChain(7));
    }

    @Test
    public void alternatingSingleChildrenAreHandled() {
        assertBoth(Flatten_114Test::alternatingSingleChildFixture);
    }

    @Test
    public void rightmostNodeOfLeftSubtreeWithItsOwnChainIsHandled() {
        assertBoth(Flatten_114Test::rightmostLeftSubtreeFixture);
    }

    @Test
    public void duplicateValuesDoNotHideDistinctNodes() {
        assertBoth(() -> levelOrder(7, 7, 7, 7, null, 7, 7, null, 7));
    }

    @Test
    public void negativeZeroAndPositiveValuesArePreserved() {
        assertBoth(() -> levelOrder(0, -1, 1, -2, 0, 2, 3));
    }

    @Test
    public void integerBoundaryValuesAreSupportedByTheClass() {
        assertBoth(() -> levelOrder(Integer.MIN_VALUE, Integer.MAX_VALUE, -1, 0, 1));
    }

    @Test
    public void rootWithOnlyOneChildAtEachSideUsesCorrectOrder() {
        assertBoth(() -> levelOrder(10, 5, 15, null, 7, 12));
    }

    @Test
    public void deeplySparseTreeDoesNotDependOnCompleteness() {
        assertBoth(() -> levelOrder(1, 2, 3, null, 4, null, 5, null, null, 6));
    }

    @Test
    public void allLeftDepthTwoThousandIsFlattened() {
        assertBoth(() -> leftChain(2_000));
    }

    @Test
    public void allRightDepthTwoThousandIsFlattened() {
        assertBoth(() -> rightChain(2_000));
    }

    @Test
    public void completePrefixWithTwoThousandNodesIsFlattened() {
        assertBoth(() -> completePrefix(2_000));
    }

    @Test
    public void alternatingLargeChainWithDuplicateValuesIsFlattened() {
        assertBoth(() -> alternatingChain(1_000));
    }

    @Test
    public void everyFlattenedNodeIsAnOriginalNode() {
        assertBoth(Flatten_114Test::identityFixture);
    }

    @Test
    public void flatteningAnAlreadyFlattenedTreeIsIdempotent() {
        assertRepeated(Flatten_114Test::rightmostLeftSubtreeFixture);
    }

    @Test
    public void oneSolverInstanceCanProcessFreshInputsRepeatedly() {
        assertFreshReuse(solution::flatten);
        assertFreshReuse(solution::flattenWithStack);
    }

    @Test
    public void flatteningDoesNotLeaveAnyLeftPointers() {
        assertBoth(() -> levelOrder(1, 2, 3, 4, null, null, 5, 6, null, null, 7));
    }

    @Test
    public void preorderIsIndependentOfValueOrdering() {
        assertBoth(() -> levelOrder(100, -100, 50, 0, -200, 75, 25));
    }

    @Test
    public void zeroValuedTreeStillContainsEveryNode() {
        assertBoth(() -> levelOrder(0, 0, 0, 0, 0, 0, 0, 0));
    }

    @Test
    public void narrowBranchesAtBothBoundariesAreFlattened() {
        assertBoth(Flatten_114Test::narrowBranchesFixture);
    }

    @Test
    public void aTreeWithOneHundredNodesUsesExactPreorderLength() {
        assertBoth(() -> completePrefix(100));
    }

    /**
     * Builds two fresh trees and checks both implementations against the independent
     * iterative preorder traversal. Identity assertions also prove that flattening is
     * in-place and that no node is lost or duplicated.
     */
    private void assertBoth(Supplier<TreeNode> factory) {
        assertFlattened(factory.get(), solution::flatten);
        assertFlattened(factory.get(), solution::flattenWithStack);
    }

    private void assertFlattened(TreeNode root, Consumer<TreeNode> flattener) {
        List<TreeNode> expected = preorder(root);
        flattener.accept(root);

        TreeNode current = root;
        for (TreeNode expectedNode : expected) {
            assertSame(expectedNode, current, "flattened nodes must follow preorder identity");
            assertEquals(expectedNode.val, current.val);
            assertNull(current.left, "the linked-list representation has no left pointers");
            current = current.right;
        }
        assertNull(current, "the final right pointer must terminate the list");
    }

    private void assertRepeated(Supplier<TreeNode> factory) {
        TreeNode root = factory.get();
        assertRepeatedFor(root, solution::flatten);

        root = factory.get();
        assertRepeatedFor(root, solution::flattenWithStack);
    }

    private void assertRepeatedFor(TreeNode root, Consumer<TreeNode> flattener) {
        List<TreeNode> expected = preorder(root);
        flattener.accept(root);
        flattener.accept(root);

        TreeNode current = root;
        for (TreeNode expectedNode : expected) {
            assertSame(expectedNode, current);
            assertNull(current.left);
            current = current.right;
        }
        assertNull(current);
    }

    private void assertFreshReuse(Consumer<TreeNode> flattener) {
        for (int offset = 0; offset < 4; offset++) {
            TreeNode root = levelOrder(offset, offset + 1, offset + 2, null, offset + 3);
            assertFlattened(root, flattener);
        }
    }

    /** Independent iterative oracle; it does not use either production traversal. */
    private List<TreeNode> preorder(TreeNode root) {
        List<TreeNode> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return result;
    }

    private static TreeNode levelOrder(Integer... values) {
        if (values.length == 0 || values[0] == null) {
            return null;
        }
        TreeNode root = new TreeNode(values[0]);
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int index = 1;
        while (!queue.isEmpty() && index < values.length) {
            TreeNode parent = queue.remove();
            if (index < values.length && values[index] != null) {
                parent.left = new TreeNode(values[index]);
                queue.add(parent.left);
            }
            index++;
            if (index < values.length && values[index] != null) {
                parent.right = new TreeNode(values[index]);
                queue.add(parent.right);
            }
            index++;
        }
        return root;
    }

    private static TreeNode leftChain(int size) {
        if (size == 0) {
            return null;
        }
        TreeNode root = new TreeNode(valueAt(0));
        TreeNode current = root;
        for (int i = 1; i < size; i++) {
            current.left = new TreeNode(valueAt(i));
            current = current.left;
        }
        return root;
    }

    private static TreeNode rightChain(int size) {
        if (size == 0) {
            return null;
        }
        TreeNode root = new TreeNode(valueAt(0));
        TreeNode current = root;
        for (int i = 1; i < size; i++) {
            current.right = new TreeNode(valueAt(i));
            current = current.right;
        }
        return root;
    }

    private static TreeNode alternatingChain(int size) {
        if (size == 0) {
            return null;
        }
        TreeNode root = new TreeNode(valueAt(0));
        TreeNode current = root;
        for (int i = 1; i < size; i++) {
            TreeNode next = new TreeNode(valueAt(i));
            if ((i & 1) == 0) {
                current.left = next;
            } else {
                current.right = next;
            }
            current = next;
        }
        return root;
    }

    private static TreeNode completePrefix(int size) {
        if (size == 0) {
            return null;
        }
        TreeNode[] nodes = new TreeNode[size];
        for (int i = 0; i < size; i++) {
            nodes[i] = new TreeNode(valueAt(i));
        }
        for (int i = 0; i < size; i++) {
            int left = 2 * i + 1;
            int right = left + 1;
            if (left < size) {
                nodes[i].left = nodes[left];
            }
            if (right < size) {
                nodes[i].right = nodes[right];
            }
        }
        return nodes[0];
    }

    private static TreeNode rightmostLeftSubtreeFixture() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(9);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(5);
        root.left.right.right = new TreeNode(6);
        root.right.left = new TreeNode(8);
        return root;
    }

    private static TreeNode alternatingSingleChildFixture() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.right = new TreeNode(3);
        root.left.right.left = new TreeNode(4);
        root.left.right.left.right = new TreeNode(5);
        return root;
    }

    private static TreeNode narrowBranchesFixture() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        return root;
    }

    private static TreeNode identityFixture() {
        return levelOrder(42, 42, 42, -1, null, null, Integer.MAX_VALUE, 0);
    }

    private static int valueAt(int index) {
        return (index % 11) - 5;
    }
}
