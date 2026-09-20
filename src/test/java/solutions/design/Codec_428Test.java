package solutions.design;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import library.tree.narytree.Node;
import org.junit.jupiter.api.Test;

/** Tests the stateless preorder codec for ordered N-ary trees. */
public class Codec_428Test {

    private final Codec_428 codec = new Codec_428();

    @Test
    public void serializeNullReturnsEmptyString() {
        assertEquals("", codec.serialize(null));
    }

    @Test
    public void deserializeEmptyStringReturnsNull() {
        assertNull(codec.deserialize(""));
    }

    @Test
    public void singletonRoundTrip() {
        assertRoundTrip(new Node(42, List.of()));
    }

    @Test
    public void officialExampleRoundTrip() {
        Node root = node(1,
            node(3, node(5), node(6)),
            node(2),
            node(4));

        assertRoundTrip(root);
    }

    @Test
    public void serializeUsesPreorderValuesAndChildCounts() {
        Node root = node(1,
            node(3, node(5), node(6)),
            node(2),
            node(4));

        assertEquals("1,3,3,2,5,0,6,0,2,0,4,0", codec.serialize(root));
    }

    @Test
    public void deserializeKnownIndependentEncoding() {
        Node decoded = codec.deserialize("10,2,20,1,30,0,40,0");

        assertTree(decoded, 10,
            List.of(
                expected(20, expected(30)),
                expected(40)));
    }

    @Test
    public void wideRootPreservesChildOrder() {
        Node root = node(0, node(-1), node(5), node(5), node(99), node(Integer.MIN_VALUE));

        Node decoded = roundTrip(root);

        assertEquals(List.of(-1, 5, 5, 99, Integer.MIN_VALUE), values(decoded.children));
    }

    @Test
    public void variableArityAtMultipleLevels() {
        Node root = node(8,
            node(1, node(2), node(3, node(4), node(5), node(6))),
            node(7, node(9)),
            node(10, node(11), node(12)));

        assertRoundTrip(root);
    }

    @Test
    public void emptyChildListsArePreservedAtEveryPosition() {
        Node root = node(1,
            node(2),
            node(3, node(4), node(5)),
            node(6),
            node(7, node(8)));

        Node decoded = roundTrip(root);

        assertEquals(0, decoded.children.get(0).children.size());
        assertEquals(2, decoded.children.get(1).children.size());
        assertEquals(0, decoded.children.get(2).children.size());
        assertEquals(1, decoded.children.get(3).children.size());
    }

    @Test
    public void deepChainAtDocumentedHeightLimitRoundTrips() {
        Node root = deepChain(1000);

        Node decoded = roundTrip(root);

        assertEquals(1000, countNodes(decoded));
        assertEquals(1000, depth(decoded));
        assertEquals(1, decoded.children.get(0).val);
    }

    @Test
    public void maximumNodeCountWideTreeRoundTrips() {
        Node root = wideTree(10_000);

        Node decoded = roundTrip(root);

        assertEquals(10_000, countNodes(decoded));
        assertEquals(9_999, decoded.children.size());
        assertEquals(9_999, decoded.children.get(9_998).val);
    }

    @Test
    public void negativeAndIntegerBoundaryValuesRoundTrip() {
        Node root = node(Integer.MIN_VALUE,
            node(-1, node(Integer.MAX_VALUE)),
            node(0),
            node(Integer.MAX_VALUE));

        assertRoundTrip(root);
    }

    @Test
    public void duplicateValuesDoNotCollapseDistinctNodes() {
        Node root = node(7,
            node(7, node(7)),
            node(7),
            node(7, node(7), node(7)));

        Node decoded = roundTrip(root);

        assertEquals(7, decoded.val);
        assertEquals(3, decoded.children.size());
        assertEquals(1, decoded.children.get(0).children.size());
        assertEquals(0, decoded.children.get(1).children.size());
        assertEquals(2, decoded.children.get(2).children.size());
        assertEquals(7, countNodes(decoded));
    }

    @Test
    public void allZeroValuesStillPreserveShape() {
        Node root = node(0, node(0), node(0, node(0, node(0)), node(0)));

        assertRoundTrip(root);
    }

    @Test
    public void originalMutationAfterSerializationDoesNotChangeDecodedTree() {
        List<Node> children = new ArrayList<>(List.of(node(2), node(3)));
        Node root = new Node(1, children);
        String encoded = codec.serialize(root);

        children.clear();
        root.val = 99;
        Node decoded = codec.deserialize(encoded);

        assertTree(decoded, 1, List.of(expected(2), expected(3)));
    }

    @Test
    public void decodedMutationDoesNotChangeOriginalTree() {
        Node root = node(1, node(2), node(3));
        Node decoded = roundTrip(root);

        decoded.val = 99;
        decoded.children.remove(0);
        decoded.children.get(0).val = 88;

        assertTree(root, 1, List.of(expected(2), expected(3)));
    }

    @Test
    public void roundTripCreatesFreshNodesAndChildLists() {
        Node root = node(1, node(2, node(4)), node(3));
        Node decoded = roundTrip(root);

        assertNotSame(root, decoded);
        assertNotSame(root.children, decoded.children);
        assertNotSame(root.children.get(0), decoded.children.get(0));
        assertNotSame(root.children.get(0).children, decoded.children.get(0).children);
        assertNotSame(root.children.get(0).children.get(0), decoded.children.get(0).children.get(0));
    }

    @Test
    public void repeatedRoundTripsOnOneCodecAreStateless() {
        Node first = node(1, node(2));
        Node second = node(9, node(8, node(7)), node(6));

        Node firstDecoded = roundTrip(first);
        Node secondDecoded = roundTrip(second);
        Node firstAgain = roundTrip(first);

        assertTree(firstDecoded, 1, List.of(expected(2)));
        assertTree(secondDecoded, 9, List.of(expected(8, expected(7)), expected(6)));
        assertTree(firstAgain, 1, List.of(expected(2)));
    }

    @Test
    public void interleavedSerializedPayloadsDecodeIndependently() {
        Node first = node(1, node(2, node(3)));
        Node second = node(4, node(5), node(6, node(7), node(8)));
        String firstData = codec.serialize(first);
        String secondData = codec.serialize(second);

        Node secondDecoded = codec.deserialize(secondData);
        Node firstDecoded = codec.deserialize(firstData);

        assertTree(firstDecoded, 1, List.of(expected(2, expected(3))));
        assertTree(secondDecoded, 4, List.of(expected(5), expected(6, expected(7), expected(8))));
    }

    @Test
    public void mixedTreeWithLongBranchesAndManyLeavesRoundTrips() {
        Node root = node(0,
            deepBranch(1, 80),
            node(2, node(20), node(21), node(22), node(23), node(24)),
            deepBranch(3, 60),
            node(4));

        Node decoded = roundTrip(root);

        assertEquals(4, decoded.children.size());
        assertEquals(81, depth(decoded.children.get(0)));
        assertEquals(61, depth(decoded.children.get(2)));
        assertEquals(countNodes(root), countNodes(decoded));
    }

    @Test
    public void manySmallOrderedShapesRoundTripAgainstStructuralOracle() {
        for (int shape = 0; shape < 32; shape++) {
            Node root = generatedShape(shape);
            assertTree(roundTrip(root), root.val, expectedChildren(root));
        }
    }

    @Test
    public void serializationDoesNotMutateCallerChildOrderOrContents() {
        Node first = node(2);
        Node second = node(3);
        List<Node> children = new ArrayList<>(List.of(first, second));
        Node root = new Node(1, children);

        String encoded = codec.serialize(root);

        assertEquals(List.of(first, second), children);
        assertEquals(1, root.val);
        assertEquals("1,2,2,0,3,0", encoded);
    }

    @Test
    public void decodedChildrenAreMutableIndependentLists() {
        Node decoded = codec.deserialize("1,2,2,0,3,0");

        decoded.children.add(node(4));
        decoded.children.get(0).children.add(node(5));

        assertEquals(3, decoded.children.size());
        assertEquals(1, decoded.children.get(0).children.size());
        assertEquals(2, codec.deserialize("1,2,2,0,3,0").children.size());
    }

    @Test
    public void maximumDepthAndBranchingCanCoexistWithinNodeLimit() {
        Node root = node(1, deepBranch(2, 999), node(3, node(4), node(5)));

        Node decoded = roundTrip(root);

        assertEquals(countNodes(root), countNodes(decoded));
        assertEquals(1000, depth(decoded.children.get(0)));
        assertEquals(2, decoded.children.size());
    }

    private Node roundTrip(Node root) {
        return codec.deserialize(codec.serialize(root));
    }

    private static Node node(int value, Node... children) {
        return new Node(value, new ArrayList<>(Arrays.asList(children)));
    }

    private static Node expected(int value, Node... children) {
        return node(value, children);
    }

    private static Node deepChain(int nodes) {
        Node root = node(0);
        Node current = root;
        for (int value = 1; value < nodes; value++) {
            Node child = node(value);
            current.children.add(child);
            current = child;
        }
        return root;
    }

    private static Node deepBranch(int rootValue, int edges) {
        Node root = node(rootValue);
        Node current = root;
        for (int offset = 1; offset <= edges; offset++) {
            Node child = node(rootValue + offset);
            current.children.add(child);
            current = child;
        }
        return root;
    }

    private static Node wideTree(int nodes) {
        List<Node> children = new ArrayList<>(nodes - 1);
        for (int value = 1; value < nodes; value++) {
            children.add(node(value));
        }
        return new Node(0, children);
    }

    private static Node generatedShape(int shape) {
        Node root = node(shape - 16);
        int childCount = shape % 5;
        for (int child = 0; child < childCount; child++) {
            Node childNode = node(shape * 10 + child);
            int grandchildCount = (shape + child) % 4;
            for (int grandchild = 0; grandchild < grandchildCount; grandchild++) {
                childNode.children.add(node(-shape * 10 - grandchild));
            }
            root.children.add(childNode);
        }
        return root;
    }

    private static List<Node> expectedChildren(Node node) {
        List<Node> expected = new ArrayList<>();
        for (Node child : node.children) {
            expected.add(expected(child.val, expectedChildrenArray(child)));
        }
        return expected;
    }

    private static Node[] expectedChildrenArray(Node node) {
        return node.children.toArray(Node[]::new);
    }

    private static List<Integer> values(List<Node> nodes) {
        List<Integer> values = new ArrayList<>(nodes.size());
        for (Node node : nodes) {
            values.add(node.val);
        }
        return values;
    }

    private static int countNodes(Node root) {
        if (root == null) {
            return 0;
        }
        int count = 1;
        for (Node child : root.children) {
            count += countNodes(child);
        }
        return count;
    }

    private static int depth(Node root) {
        if (root == null) {
            return 0;
        }
        int maxChildDepth = 0;
        for (Node child : root.children) {
            maxChildDepth = Math.max(maxChildDepth, depth(child));
        }
        return maxChildDepth + 1;
    }

    private static void assertRoundTrip(Node root) {
        Codec_428 codec = new Codec_428();
        assertTree(codec.deserialize(codec.serialize(root)), root.val, expectedChildren(root));
    }

    private static void assertTree(Node actual, int expectedValue, List<Node> expectedChildren) {
        assertEquals(expectedValue, actual.val);
        assertEquals(expectedChildren.size(), actual.children.size());
        for (int index = 0; index < expectedChildren.size(); index++) {
            Node expected = expectedChildren.get(index);
            Node child = actual.children.get(index);
            assertTree(child, expected.val, expectedChildren(expected));
        }
    }
}
