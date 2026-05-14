package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IsValidSerialization_331Test {

    private final IsValidSerialization_331 test = new IsValidSerialization_331();

    @Test
    public void testHappyCases() {
        assertTrue(test.isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#"));
        assertFalse(test.isValidSerialization("1,#"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertTrue(test.isValidSerialization("#"));
        assertFalse(test.isValidSerialization("9,#,#,1"));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.isValidSerialization("1,2,3,#,#,#,#"));
    }

    @Test
    public void testSingleNullNode() {
        assertTrue(test.isValidSerialization("#"));
    }

    @Test
    public void testSimpleValidTree() {
        assertTrue(test.isValidSerialization("1,#,#"));
    }

    @Test
    public void testLeftSkewedTree() {
        assertTrue(test.isValidSerialization("1,2,#,#,#"));
    }

    @Test
    public void testRightSkewedTree() {
        assertTrue(test.isValidSerialization("1,#,2,#,#"));
    }

    @Test
    public void testInvalidExtraNodes() {
        assertFalse(test.isValidSerialization("1,#,#,#,#"));
    }

    @Test
    public void testInvalidTooFewNulls() {
        assertFalse(test.isValidSerialization("1,2,#,#"));
    }

    @Test
    public void testMultiDigitNodes() {
        assertTrue(test.isValidSerialization("12,34,#,#,56,#,#"));
    }

    @Test
    public void testGiantCase() {
        // Build a complete binary tree of depth 10 (1023 nodes + 1024 nulls)
        StringBuilder sb = new StringBuilder();
        int nodes = 1023;
        int nulls = 1024;
        for (int i = 0; i < nodes; i++) {
            sb.append(i).append(",");
        }
        for (int i = 0; i < nulls; i++) {
            sb.append("#");
            if (i < nulls - 1) sb.append(",");
        }
        // A complete binary tree serialized as all nodes then all nulls is valid preorder
        // for a right-skewed structure. Let's verify with the algorithm.
        // Actually this won't be valid preorder for a standard tree. Use a proper construction.
        // Build a perfect binary tree in preorder: node, left-subtree, right-subtree
        StringBuilder sb2 = new StringBuilder();
        buildPerfectTree(sb2, 10);
        String serialization = sb2.toString();
        assertTrue(test.isValidSerialization(serialization));
    }

    private void buildPerfectTree(StringBuilder sb, int depth) {
        if (depth == 0) {
            sb.append("#");
            return;
        }
        sb.append(depth);
        sb.append(",");
        buildPerfectTree(sb, depth - 1);
        sb.append(",");
        buildPerfectTree(sb, depth - 1);
    }
}
