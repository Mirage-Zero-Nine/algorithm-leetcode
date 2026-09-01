package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NumRollsToTarget_1155Test {

    private final NumRollsToTarget_1155 test = new NumRollsToTarget_1155();

    @Test
    public void testHappyCases() {
        assertEquals(1, test.numRollsToTarget(1, 6, 3));
        assertEquals(6, test.numRollsToTarget(2, 6, 7));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.numRollsToTarget(2, 6, 1));
        assertEquals(1, test.numRollsToTarget(1, 1, 1));
    }

    @Test
    public void testLargeCase() {
        assertEquals(222616187, test.numRollsToTarget(30, 30, 500));
    }

    @Test
    public void testTargetTooLarge() {
        assertEquals(0, test.numRollsToTarget(1, 6, 7));
    }

    @Test
    public void testTargetEqualsMax() {
        assertEquals(1, test.numRollsToTarget(2, 6, 12));
    }

    @Test
    public void testTargetEqualsMin() {
        assertEquals(1, test.numRollsToTarget(3, 6, 3));
    }

    @Test
    public void testTwoDiceSixFaces() {
        assertEquals(2, test.numRollsToTarget(2, 6, 3));
    }

    @Test
    public void testOneDieTwoFaces() {
        assertEquals(1, test.numRollsToTarget(1, 2, 2));
    }

    @Test
    public void testThreeDiceSixFaces() {
        assertEquals(25, test.numRollsToTarget(3, 6, 9));
    }

    @Test
    public void testExhaustiveSmallValidInputs() {
        for (int n = 1; n <= 6; n++) {
            for (int k = 1; k <= 5; k++) {
                for (int target = 1; target <= n * k + 2; target++) {
                    assertEquals(
                            countWays(n, k, target),
                            test.numRollsToTarget(n, k, target),
                            "n=" + n + ", k=" + k + ", target=" + target);
                }
            }
        }
    }

    private int countWays(int dice, int faces, int target) {
        if (dice == 0) {
            return target == 0 ? 1 : 0;
        }

        int ways = 0;
        for (int face = 1; face <= faces; face++) {
            ways += countWays(dice - 1, faces, target - face);
        }
        return ways;
    }
}
