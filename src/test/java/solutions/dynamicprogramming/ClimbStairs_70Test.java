package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class ClimbStairs_70Test {

    private final ClimbStairs_70 test = new ClimbStairs_70();

    /**
     * Reference values for n in [1, 45], indexed by n. Follows the
     * Fibonacci-shifted relation climbStairs(n) = F(n + 1) where
     * F(1)=1, F(2)=1, F(k)=F(k-1)+F(k-2). Hardcoded to avoid testing the
     * algorithm against itself. n=45 is the LeetCode upper bound; beyond
     * that the result overflows int.
     */
    private static final int[] REFERENCE = new int[]{
            0, // index 0 (unused)
            1, 2, 3, 5, 8, 13, 21, 34, 55, 89,
            144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946,
            17711, 28657, 46368, 75025, 121393, 196418, 317811, 514229, 832040, 1346269,
            2178309, 3524578, 5702887, 9227465, 14930352, 24157817, 39088169, 63245986, 102334155, 165580141,
            267914296, 433494437, 701408733, 1134903170, 1836311903
    };

    @Test
    public void testHappyCases() {
        assertEquals(2, test.climbStairs(2));
        assertEquals(3, test.climbStairs(3));
    }

    @Test
    public void testEdgeCaseSingleStep() {
        // Smallest valid input.
        assertEquals(1, test.climbStairs(1));
    }

    @Test
    public void testEdgeCaseFour() {
        assertEquals(5, test.climbStairs(4));
    }

    @Test
    public void testSmallValues() {
        assertEquals(8, test.climbStairs(5));
        assertEquals(13, test.climbStairs(6));
        assertEquals(21, test.climbStairs(7));
    }

    @Test
    public void testMidRange() {
        assertEquals(34, test.climbStairs(8));
        assertEquals(55, test.climbStairs(9));
        assertEquals(89, test.climbStairs(10));
    }

    @Test
    public void testN20() {
        assertEquals(10946, test.climbStairs(20));
    }

    @Test
    public void testN30() {
        assertEquals(1346269, test.climbStairs(30));
    }

    @Test
    public void testUpperBoundOfConstraint() {
        // LeetCode constraint: 1 <= n <= 45. n=45 is the largest value
        // that fits in a signed 32-bit int.
        assertEquals(1836311903, test.climbStairs(45));
    }

    /**
     * Iterable sweep across the full LeetCode constraint range [1, 45]
     * using a hardcoded reference table.
     */
    @ParameterizedTest(name = "climbStairs({0}) = {1}")
    @MethodSource("oneToFortyFive")
    public void testEveryValueFromOneToFortyFive(int input, int expected) {
        assertEquals(expected, test.climbStairs(input));
    }

    private static Stream<org.junit.jupiter.params.provider.Arguments> oneToFortyFive() {
        return IntStream.rangeClosed(1, 45)
                .mapToObj(i -> arguments(i, REFERENCE[i]));
    }

    /**
     * Cross-validate against an independent iterative reference (no array,
     * O(1) memory). Catches any drift between the array-based DP and the
     * mathematical recurrence.
     */
    @ParameterizedTest(name = "climbStairs({0}) cross-check")
    @MethodSource("oneToFortyFive")
    public void testCrossCheckAgainstIterativeReference(int input, int expected) {
        assertEquals(referenceClimb(input), test.climbStairs(input));
    }

    private static int referenceClimb(int n) {
        if (n < 3) {
            return n;
        }
        int prev2 = 1, prev1 = 2;
        for (int i = 3; i <= n; i++) {
            int cur = prev1 + prev2;
            prev2 = prev1;
            prev1 = cur;
        }
        return prev1;
    }
}
