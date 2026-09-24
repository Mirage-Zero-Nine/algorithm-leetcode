package solutions.design;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.time.Duration;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import library.tree.binarytree.TreeNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

/** Tests for the preorder, null-marker representation used by {@link Codec_297}. */
public class Codec_297Test {

    private final Codec_297 codec = new Codec_297();

    @Test
    public void nullTreeRoundTripsAndUsesTheDocumentedMarker() {
        assertEquals("#,", codec.serialize(null));
        assertNull(codec.deserialize("#,"));
        assertNull(codec.deserialize(codec.serialize(null)));
    }

    @Test
    public void singletonTreeRoundTrips() {
        assertRoundTrip(new TreeNode(1));
    }

    @Test
    public void officialExampleRoundTrips() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        assertRoundTrip(root);
    }

    @Test
    public void completeTreeWithSevenNodesRoundTrips() {
        assertRoundTrip(completeTree(7));
    }

    @Test
    public void completeTreeWithThirtyOneNodesRoundTrips() {
        assertRoundTrip(completeTree(31));
    }

    @Test
    public void treeWithOnlyLeftChildrenRoundTrips() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(2);
        root.left.left.left.left = new TreeNode(1);

        assertRoundTrip(root);
    }

    @Test
    public void treeWithOnlyRightChildrenRoundTrips() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(4);
        root.right.right.right.right = new TreeNode(5);

        assertRoundTrip(root);
    }

    @Test
    public void alternatingLeftAndRightPathRoundTrips() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(-1);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(-3);
        root.left.right.left.right = new TreeNode(4);
        root.left.right.left.right.left = new TreeNode(-5);

        assertRoundTrip(root);
    }

    @Test
    public void sparseTreePreservesMissingInteriorChildren() {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(6);
        root.right = new TreeNode(14);
        root.left.right = new TreeNode(8);
        root.right.left = new TreeNode(12);
        root.right.left.right = new TreeNode(13);

        assertRoundTrip(root);
    }

    @Test
    public void duplicateValuesDoNotCollapseDistinctNodes() {
        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(7);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(7);
        root.left.right = new TreeNode(7);
        root.right.right = new TreeNode(7);

        assertRoundTrip(root);
    }

    @Test
    public void negativeValuesAndZeroRoundTrip() {
        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(-20);
        root.right = new TreeNode(0);
        root.right.left = new TreeNode(-1);
        root.right.right = new TreeNode(30);

        assertRoundTrip(root);
    }

    @Test
    public void integerExtremeValuesRoundTripBecauseImplementationUsesIntNodes() {
        TreeNode root = new TreeNode(Integer.MIN_VALUE);
        root.left = new TreeNode(Integer.MAX_VALUE);
        root.right = new TreeNode(-1);
        root.right.left = new TreeNode(0);

        assertRoundTrip(root);
    }

    @Test
    public void leetCodeBoundaryValuesRoundTrip() {
        TreeNode root = new TreeNode(-1000);
        root.left = new TreeNode(1000);
        root.right = new TreeNode(-1000);
        root.left.right = new TreeNode(1000);

        assertRoundTrip(root);
    }

    @Test
    public void exactPreorderSerializationIncludesNullMarkers() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);

        assertEquals("1,2,#,#,3,4,#,#,#,", codec.serialize(root));
    }

    @Test
    public void exactSerializationOfAllRightPathIncludesEachNullChild() {
        TreeNode root = new TreeNode(-2);
        root.right = new TreeNode(8);

        assertEquals("-2,#,8,#,#,", codec.serialize(root));
    }

    @Test
    public void exactDeserializationBuildsTheExpectedSparseTopology() {
        TreeNode actual = codec.deserialize("1,2,#,4,#,#,3,#,5,#,#,");

        TreeNode expected = new TreeNode(1);
        expected.left = new TreeNode(2);
        expected.left.right = new TreeNode(4);
        expected.right = new TreeNode(3);
        expected.right.right = new TreeNode(5);
        assertTreeEquals(expected, actual);
    }

    @Test
    public void deserializationAcceptsNegativeAndZeroTokens() {
        TreeNode actual = codec.deserialize("-1,-2,#,#,0,#,#,");

        TreeNode expected = new TreeNode(-1);
        expected.left = new TreeNode(-2);
        expected.right = new TreeNode(0);
        assertTreeEquals(expected, actual);
    }

    @Test
    public void deserializationAcceptsIntegerExtremeTokens() {
        TreeNode actual = codec.deserialize("-2147483648,#,2147483647,#,#,");

        TreeNode expected = new TreeNode(Integer.MIN_VALUE);
        expected.right = new TreeNode(Integer.MAX_VALUE);
        assertTreeEquals(expected, actual);
    }

    @Test
    public void serializingDoesNotMutateTheInputTree() {
        TreeNode root = new TreeNode(9);
        root.left = new TreeNode(3);
        root.right = new TreeNode(12);
        root.left.right = new TreeNode(4);

        String before = "9,3,#,4,#,#,12,#,#,";
        assertEquals(before, codec.serialize(root));
        assertTreeEquals(root, codec.deserialize(before));
        assertEquals(before, codec.serialize(root));
    }

    @Test
    public void repeatedCallsOnOneCodecAreIndependent() {
        TreeNode first = new TreeNode(1);
        first.left = new TreeNode(2);
        TreeNode second = new TreeNode(8);
        second.right = new TreeNode(9);

        TreeNode firstResult = codec.deserialize(codec.serialize(first));
        TreeNode secondResult = codec.deserialize(codec.serialize(second));
        assertTreeEquals(first, firstResult);
        assertTreeEquals(second, secondResult);
    }

    @Test
    public void separateDeserializationsProduceSeparateMutableTrees() {
        String data = "1,2,#,#,3,#,#,";
        TreeNode first = codec.deserialize(data);
        TreeNode second = codec.deserialize(data);

        first.left.val = 99;
        assertEquals(2, second.left.val);
        assertEquals(data, codec.serialize(second));
    }

    @Test
    public void resultCanBeSerializedAgainWithoutAccumulatedState() {
        TreeNode root = new TreeNode(42);
        root.left = new TreeNode(-7);
        String data = codec.serialize(root);

        TreeNode result = codec.deserialize(data);
        assertEquals(data, codec.serialize(result));
        assertEquals(data, codec.serialize(codec.deserialize(data)));
    }

    @Test
    public void repeatedNullAndSingletonCallsDoNotLeakState() {
        assertNull(codec.deserialize(codec.serialize(null)));
        assertEquals(4, codec.deserialize(codec.serialize(new TreeNode(4))).val);
        assertNull(codec.deserialize(codec.serialize(null)));
        assertEquals(5, codec.deserialize(codec.serialize(new TreeNode(5))).val);
    }

    @Test
    public void exhaustivelyRoundTripsEveryShapeThroughFiveNodes() {
        // There are 65 ordered binary-tree shapes with 0 through 5 nodes.
        // This finite check exercises every placement of missing children;
        // values deliberately repeat so topology cannot be inferred from data.
        assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            for (int nodeCount = 0; nodeCount <= 5; nodeCount++) {
                for (TreeNode shape : allShapes(nodeCount)) {
                    assignRepeatingValues(shape, new int[]{-1, 0, 1}, new int[]{0});
                    assertRoundTrip(shape);
                }
            }
        });
    }

    @Test
    @Timeout(5)
    public void largeBalancedTreeAtTheTenThousandNodeConstraintRoundTrips() {
        TreeNode root = completeTree(10_000);

        TreeNode result = codec.deserialize(codec.serialize(root));
        assertTreeEquals(root, result);
    }

    @Test
    public void moderatelyDeepLeftSkewedTreeRoundTrips() {
        TreeNode root = skewedTree(250, true);

        assertRoundTrip(root);
    }

    @Test
    public void moderatelyDeepRightSkewedTreeRoundTrips() {
        TreeNode root = skewedTree(250, false);

        assertRoundTrip(root);
    }

    @Test
    public void deepTreeUsesRepeatedValuesAndPreservesEveryPosition() {
        TreeNode root = skewedTree(180, true);
        TreeNode cursor = root;
        int value = 0;
        while (cursor != null) {
            cursor.val = value++ % 3 - 1;
            cursor = cursor.left;
        }

        assertRoundTrip(root);
    }

    @Test
    public void completeTreeWithNegativeAndRepeatedValuesRoundTrips() {
        TreeNode root = completeTree(63);
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int value = -31;
        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            node.val = value++ % 9 - 4;
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }

        assertRoundTrip(root);
    }

    private void assertRoundTrip(TreeNode expected) {
        String serialized = codec.serialize(expected);
        TreeNode actual = codec.deserialize(serialized);
        assertTreeEquals(expected, actual);
    }

    private void assertTreeEquals(TreeNode expected, TreeNode actual) {
        Deque<TreeNode[]> pending = new ArrayDeque<>();
        pending.push(new TreeNode[]{expected, actual});
        while (!pending.isEmpty()) {
            TreeNode[] pair = pending.pop();
            TreeNode expectedNode = pair[0];
            TreeNode actualNode = pair[1];
            if (expectedNode == null || actualNode == null) {
                assertEquals(expectedNode, actualNode);
                continue;
            }
            assertEquals(expectedNode.val, actualNode.val);
            pending.push(new TreeNode[]{expectedNode.left, actualNode.left});
            pending.push(new TreeNode[]{expectedNode.right, actualNode.right});
        }
    }

    private TreeNode completeTree(int nodeCount) {
        TreeNode[] nodes = new TreeNode[nodeCount + 1];
        for (int i = 1; i <= nodeCount; i++) {
            nodes[i] = new TreeNode(i);
        }
        for (int i = 1; i <= nodeCount; i++) {
            if (2 * i <= nodeCount) {
                nodes[i].left = nodes[2 * i];
            }
            if (2 * i + 1 <= nodeCount) {
                nodes[i].right = nodes[2 * i + 1];
            }
        }
        return nodeCount == 0 ? null : nodes[1];
    }

    private TreeNode skewedTree(int nodeCount, boolean left) {
        if (nodeCount == 0) {
            return null;
        }
        TreeNode root = new TreeNode(0);
        TreeNode cursor = root;
        for (int value = 1; value < nodeCount; value++) {
            TreeNode child = new TreeNode(value);
            if (left) {
                cursor.left = child;
            } else {
                cursor.right = child;
            }
            cursor = child;
        }
        return root;
    }

    private List<TreeNode> allShapes(int nodeCount) {
        if (nodeCount == 0) {
            return java.util.Collections.singletonList(null);
        }
        java.util.ArrayList<TreeNode> result = new java.util.ArrayList<>();
        for (int leftCount = 0; leftCount < nodeCount; leftCount++) {
            int rightCount = nodeCount - 1 - leftCount;
            for (TreeNode left : allShapes(leftCount)) {
                for (TreeNode right : allShapes(rightCount)) {
                    TreeNode root = new TreeNode(0);
                    root.left = left;
                    root.right = right;
                    result.add(root);
                }
            }
        }
        return result;
    }

    private void assignRepeatingValues(TreeNode root, int[] values, int[] nextIndex) {
        if (root == null) {
            return;
        }
        root.val = values[nextIndex[0]++ % values.length];
        assignRepeatingValues(root.left, values, nextIndex);
        assignRepeatingValues(root.right, values, nextIndex);
    }
}
