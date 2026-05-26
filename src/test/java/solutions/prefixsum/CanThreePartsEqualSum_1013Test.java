package solutions.prefixsum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for {@link CanThreePartsEqualSum_1013}.
 */
public class CanThreePartsEqualSum_1013Test {

    private final CanThreePartsEqualSum_1013 solver = new CanThreePartsEqualSum_1013();

    @Test
    public void testClassicPartition() {
        int[] A = {0, 2, 1, -6, 6, -7, 9, 1, 2, 0, 1};
        assertTrue(solver.canThreePartsEqualSum(A));
    }

    @Test
    public void testSimplePartition() {
        // sum=0, parts of 0: {1,-1}, {1,-1} — only 2 parts, need 3
        int[] A = {1, -1, 1, -1};
        assertFalse(solver.canThreePartsEqualSum(A));
    }

    @Test
    public void testCannotPartition() {
        int[] A = {1, -1};
        assertFalse(solver.canThreePartsEqualSum(A));
    }

    @Test
    public void testAllZeros() {
        int[] A = {0, 0, 0, 0};
        assertTrue(solver.canThreePartsEqualSum(A));
    }

    @Test
    public void testSumNotDivisibleByThree() {
        int[] A = {1, 2, 3};
        assertFalse(solver.canThreePartsEqualSum(A));
    }

    @Test
    public void testEqualParts() {
        int[] A = {1, 1, 1};
        assertTrue(solver.canThreePartsEqualSum(A));
    }

    @Test
    public void testNegativeSumDivisibleByThree() {
        // sum=-3, each part=-1: {-1}, {-1}, {-1,-1} doesn't work since we need exactly 3 parts
        // {-1}, {-1}, {-1} works
        int[] A = {-1, -1, -1};
        assertTrue(solver.canThreePartsEqualSum(A));
    }

    @Test
    public void testLargeEqualParts() {
        int[] A = {1, 2, 3, 6};
        assertFalse(solver.canThreePartsEqualSum(A));
    }

    @Test
    public void testPartitionWithZerosAtEnd() {
        int[] A = {1, -1, 0, 0};
        assertTrue(solver.canThreePartsEqualSum(A));
    }

    @Test
    public void testSingleElement() {
        int[] A = {1};
        assertFalse(solver.canThreePartsEqualSum(A));
    }

    @Test
    public void testTwoElements() {
        int[] A = {1, -1};
        assertFalse(solver.canThreePartsEqualSum(A));
    }

    @Test
    public void testPartitionAtBoundaries() {
        int[] A = {1, 2, -3, 0, 0};
        assertTrue(solver.canThreePartsEqualSum(A));
    }
}
