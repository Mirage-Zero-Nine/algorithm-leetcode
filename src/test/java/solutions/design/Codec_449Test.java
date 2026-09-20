package solutions.design;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Tests for the stateless preorder codec used by {@link Codec_449}.
 */
public class Codec_449Test {

    private final Codec_449 codec = new Codec_449();

    @Test
    public void testNullTreeRoundTripUsesEmptyEncoding() {
        assertEquals("", codec.serialize(null));
        assertNull(codec.deserialize(codec.serialize(null)));
    }

    @Test
    public void testDeserializeEmptyStringReturnsNull() {
        assertNull(codec.deserialize(""));
    }

    @Test
    public void testSingletonZeroRoundTrip() {
        assertRoundTrip(new TreeNode(0));
    }

    @Test
    public void testSingletonMinimumIntegerRoundTrip() {
        assertRoundTrip(new TreeNode(Integer.MIN_VALUE));
    }

    @Test
    public void testSingletonMaximumIntegerRoundTrip() {
        assertRoundTrip(new TreeNode(Integer.MAX_VALUE));
    }

    @Test
    public void testOfficialThreeNodeExampleRoundTrip() {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        assertRoundTrip(root);
    }

    @Test
    public void testKnownPreorderEncodingAndIndependentDecodeOracle() {
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(6);
        root.left.right.left = new TreeNode(4);
        root.left.right.right = new TreeNode(7);
        root.right = new TreeNode(10);
        root.right.right = new TreeNode(14);
        root.right.right.left = new TreeNode(13);

        assertEquals("8,3,1,6,4,7,10,14,13,", codec.serialize(root));
        TreeNode decoded = codec.deserialize("8,3,1,6,4,7,10,14,13,");
        assertSameTree(root, decoded);
    }

    @Test
    public void testCompleteBalancedTreeRoundTrip() {
        assertRoundTrip(buildBalancedTree(1, 15));
    }

    @Test
    public void testSparseTreeRoundTrip() {
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(3);
        root.left.right = new TreeNode(6);
        root.left.right.left = new TreeNode(4);
        root.right = new TreeNode(10);
        root.right.right = new TreeNode(14);
        root.right.right.left = new TreeNode(13);
        assertRoundTrip(root);
    }

    @Test
    public void testLeftSkewedTreeRoundTrip() {
        assertRoundTrip(buildLeftSkew(9, 10));
    }

    @Test
    public void testRightSkewedTreeRoundTrip() {
        assertRoundTrip(buildRightSkew(9, 10));
    }

    @Test
    public void testAlternatingZigzagTreeRoundTrip() {
        TreeNode root = new TreeNode(50);
        root.left = new TreeNode(20);
        root.left.right = new TreeNode(40);
        root.left.right.left = new TreeNode(30);
        root.right = new TreeNode(80);
        root.right.left = new TreeNode(60);
        root.right.left.right = new TreeNode(70);
        assertRoundTrip(root);
    }

    @Test
    public void testAllNegativeValuesRoundTrip() {
        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(-20);
        root.left.right = new TreeNode(-15);
        root.right = new TreeNode(-5);
        root.right.left = new TreeNode(-7);
        assertRoundTrip(root);
    }

    @Test
    public void testSignedIntegerBoundariesRoundTrip() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(Integer.MIN_VALUE);
        root.right = new TreeNode(Integer.MAX_VALUE);
        assertRoundTrip(root);
    }

    @Test
    public void testMinimumRootWithPositiveRightSubtreeRoundTrip() {
        TreeNode root = new TreeNode(Integer.MIN_VALUE);
        root.right = new TreeNode(-1);
        root.right.right = new TreeNode(Integer.MAX_VALUE);
        assertRoundTrip(root);
    }

    @Test
    public void testMaximumRootWithNegativeLeftSubtreeRoundTrip() {
        TreeNode root = new TreeNode(Integer.MAX_VALUE);
        root.left = new TreeNode(1);
        root.left.left = new TreeNode(Integer.MIN_VALUE);
        assertRoundTrip(root);
    }

    @Test
    public void testValuesAtOfficialBoundsRoundTrip() {
        TreeNode root = new TreeNode(5000);
        root.left = new TreeNode(0);
        root.left.right = new TreeNode(1);
        root.right = new TreeNode(10000);
        root.right.left = new TreeNode(9999);
        assertRoundTrip(root);
    }

    @Test
    public void testIrregularInsertionOrderShapeRoundTrip() {
        TreeNode root = null;
        int[] values = {50, 25, 75, 10, 40, 60, 90, 5, 15, 35, 45, 55, 65, 85, 95};
        for (int value : values) {
            root = insert(root, value);
        }
        assertRoundTrip(root);
    }

    @Test
    public void testSmallGappedValuesRoundTrip() {
        TreeNode root = new TreeNode(100);
        root.left = new TreeNode(-100);
        root.left.right = new TreeNode(0);
        root.right = new TreeNode(1000);
        root.right.left = new TreeNode(500);
        assertRoundTrip(root);
    }

    @Test
    public void testDenseThirtyOneNodeBalancedTreeRoundTrip() {
        assertRoundTrip(buildBalancedTree(1, 31));
    }

    @Test
    public void testDeepValidRightChainRoundTrip() {
        assertRoundTrip(buildRightSkew(3000, 1));
    }

    @Test
    public void testDeepValidLeftChainRoundTrip() {
        assertRoundTrip(buildLeftSkew(3000, 3000));
    }

    @Test
    public void testMaximumNodeCountRightSkewedTreeRoundTrip() {
        assertRoundTrip(buildRightSkew(10000, 0));
    }

    @Test
    public void testMaximumNodeCountLeftSkewedTreeRoundTrip() {
        assertRoundTrip(buildLeftSkew(10000, 10000));
    }

    @Test
    public void testMaximumNodeCountBalancedTreeRoundTrip() {
        TreeNode root = buildBalancedTree(1, 10000);
        String encoded = codec.serialize(root);
        assertEquals(10000, countCommaTokens(encoded));
        assertRoundTrip(root);
    }

    @Test
    public void testSerializationDoesNotMutateInputTopology() {
        TreeNode root = buildBalancedTree(1, 127);
        TreeNode snapshot = cloneTree(root);
        String encoded = codec.serialize(root);
        assertSameTree(snapshot, root);
        assertEquals(encoded, codec.serialize(root));
    }

    @Test
    public void testDeserializationCreatesIndependentTree() {
        TreeNode root = buildBalancedTree(1, 63);
        TreeNode decoded = codec.deserialize(codec.serialize(root));
        assertNotSame(root, decoded);
        assertNotSame(root.left, decoded.left);
        assertNotSame(root.right, decoded.right);

        decoded.left.val = -999;
        decoded.right.left = null;
        assertEquals(32, root.val);
        assertEquals(16, root.left.val);
        assertEquals(48, root.right.val);
        assertEquals(40, root.right.left.val);
    }

    @Test
    public void testSameCodecInstanceCanBeReusedWithoutStateLeakage() {
        TreeNode first = buildBalancedTree(1, 7);
        TreeNode second = buildRightSkew(6, 100);

        TreeNode firstDecoded = codec.deserialize(codec.serialize(first));
        assertNull(codec.deserialize(codec.serialize(null)));
        TreeNode secondDecoded = codec.deserialize(codec.serialize(second));

        assertSameTree(first, firstDecoded);
        assertSameTree(second, secondDecoded);
    }

    @Test
    public void testRepeatedSerializationIsStableForSameTree() {
        TreeNode root = buildBalancedTree(-63, 64);
        String first = codec.serialize(root);
        String second = codec.serialize(root);
        assertEquals(first, second);
        assertSameTree(root, codec.deserialize(first));
    }

    @Test
    public void testIndependentTreesWithSameValuesRemainStructurallyEquivalent() {
        TreeNode first = buildBalancedTree(1, 255);
        TreeNode second = cloneTree(first);
        assertEquals(codec.serialize(first), codec.serialize(second));
        assertSameTree(first, codec.deserialize(codec.serialize(second)));
    }

    private void assertRoundTrip(TreeNode root) {
        TreeNode inputSnapshot = cloneTree(root);
        String encoded = codec.serialize(root);
        if (root == null) {
            assertEquals("", encoded);
        } else {
            assertFalse(encoded.isEmpty());
        }

        TreeNode decoded = codec.deserialize(encoded);
        assertSameTree(inputSnapshot, decoded);
        assertSameTree(inputSnapshot, root);
        assertFreshNodes(root, decoded);
        assertEquals(encoded, codec.serialize(root));
    }

    private TreeNode buildBalancedTree(int first, int last) {
        if (first > last) {
            return null;
        }
        int mid = (int) (((long) first + last) / 2);
        TreeNode node = new TreeNode(mid);
        node.left = buildBalancedTree(first, mid - 1);
        node.right = buildBalancedTree(mid + 1, last);
        return node;
    }

    private TreeNode buildLeftSkew(int size, int rootValue) {
        TreeNode root = new TreeNode(rootValue);
        TreeNode current = root;
        for (int i = 1; i < size; i++) {
            current.left = new TreeNode(rootValue - i);
            current = current.left;
        }
        return root;
    }

    private TreeNode buildRightSkew(int size, int rootValue) {
        TreeNode root = new TreeNode(rootValue);
        TreeNode current = root;
        for (int i = 1; i < size; i++) {
            current.right = new TreeNode(rootValue + i);
            current = current.right;
        }
        return root;
    }

    private TreeNode insert(TreeNode root, int value) {
        if (root == null) {
            return new TreeNode(value);
        }
        if (value < root.val) {
            root.left = insert(root.left, value);
        } else {
            root.right = insert(root.right, value);
        }
        return root;
    }

    private TreeNode cloneTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode copy = new TreeNode(root.val);
        Deque<TreeNode> originals = new ArrayDeque<>();
        Deque<TreeNode> copies = new ArrayDeque<>();
        originals.push(root);
        copies.push(copy);
        while (!originals.isEmpty()) {
            TreeNode original = originals.pop();
            TreeNode currentCopy = copies.pop();
            if (original.right != null) {
                currentCopy.right = new TreeNode(original.right.val);
                originals.push(original.right);
                copies.push(currentCopy.right);
            }
            if (original.left != null) {
                currentCopy.left = new TreeNode(original.left.val);
                originals.push(original.left);
                copies.push(currentCopy.left);
            }
        }
        return copy;
    }

    private void assertSameTree(TreeNode expected, TreeNode actual) {
        if (expected == null || actual == null) {
            assertEquals(expected, actual);
            return;
        }
        Deque<TreeNode> expectedNodes = new ArrayDeque<>();
        Deque<TreeNode> actualNodes = new ArrayDeque<>();
        expectedNodes.push(expected);
        actualNodes.push(actual);
        while (!expectedNodes.isEmpty()) {
            TreeNode expectedNode = expectedNodes.pop();
            TreeNode actualNode = actualNodes.pop();
            assertEquals(expectedNode.val, actualNode.val);
            assertChildTreesEqual(expectedNode.left, actualNode.left, expectedNodes, actualNodes);
            assertChildTreesEqual(expectedNode.right, actualNode.right, expectedNodes, actualNodes);
        }
    }

    private void assertFreshNodes(TreeNode original, TreeNode decoded) {
        if (original == null) {
            assertNull(decoded);
            return;
        }
        Deque<TreeNode> originalNodes = new ArrayDeque<>();
        Deque<TreeNode> decodedNodes = new ArrayDeque<>();
        originalNodes.push(original);
        decodedNodes.push(decoded);
        while (!originalNodes.isEmpty()) {
            TreeNode originalNode = originalNodes.pop();
            TreeNode decodedNode = decodedNodes.pop();
            assertNotSame(originalNode, decodedNode);
            assertChildTreesEqual(originalNode.left, decodedNode.left, originalNodes, decodedNodes);
            assertChildTreesEqual(originalNode.right, decodedNode.right, originalNodes, decodedNodes);
        }
    }

    private void assertChildTreesEqual(TreeNode expected, TreeNode actual,
                                       Deque<TreeNode> expectedNodes,
                                       Deque<TreeNode> actualNodes) {
        if (expected == null || actual == null) {
            assertEquals(expected, actual);
        } else {
            expectedNodes.push(expected);
            actualNodes.push(actual);
        }
    }

    private int countCommaTokens(String encoded) {
        if (encoded.isEmpty()) {
            return 0;
        }
        int commas = 0;
        for (int i = 0; i < encoded.length(); i++) {
            if (encoded.charAt(i) == ',') {
                commas++;
            }
        }
        return commas;
    }
}
