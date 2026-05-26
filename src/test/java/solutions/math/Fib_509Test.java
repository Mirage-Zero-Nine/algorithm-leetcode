package solutions.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class Fib_509Test {

    private final Fib_509 test = new Fib_509();

    /**
     * Reference Fibonacci values for n in [0, 30], hardcoded so the tests
     * cannot drift if the algorithm under test is wrong. Matches LeetCode
     * problem 509 constraint: 0 <= n <= 30.
     */
    private static final int[] REFERENCE = new int[]{
            0, 1, 1, 2, 3, 5, 8, 13, 21, 34,
            55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181,
            6765, 10946, 17711, 28657, 46368, 75025, 121393, 196418, 317811, 514229,
            832040
    };

    @Test
    public void testHappyCases() {
        assertEquals(1, test.fib(1));
        assertEquals(1, test.fib(2));
        assertEquals(5, test.fib(5));
    }

    @Test
    public void testEdgeCaseZero() {
        assertEquals(0, test.fib(0));
    }

    @Test
    public void testEdgeCaseOne() {
        assertEquals(1, test.fib(1));
    }

    @Test
    public void testSmallValues() {
        assertEquals(2, test.fib(3));
        assertEquals(3, test.fib(4));
        assertEquals(8, test.fib(6));
    }

    @Test
    public void testMidRange() {
        assertEquals(55, test.fib(10));
        assertEquals(144, test.fib(12));
        assertEquals(610, test.fib(15));
    }

    @Test
    public void testUpperBoundOfConstraint() {
        // LeetCode says 0 <= n <= 30; this is the largest valid input.
        assertEquals(832040, test.fib(30));
    }

    /**
     * Iterable sweep across the entire valid input range [0, 30] using
     * a hardcoded reference table.
     */
    @ParameterizedTest(name = "fib({0}) = {1}")
    @MethodSource("zeroToThirty")
    public void testEveryValueFromZeroToThirty(int input, int expected) {
        assertEquals(expected, test.fib(input));
    }

    private static Stream<org.junit.jupiter.params.provider.Arguments> zeroToThirty() {
        return IntStream.rangeClosed(0, 30)
                .mapToObj(i -> arguments(i, REFERENCE[i]));
    }

    /**
     * Cross-validate the recursive implementation against an independent
     * iterative reference for n in [0, 30]. Catches any drift between the
     * two definitions.
     */
    @ParameterizedTest(name = "fib({0}) cross-check")
    @MethodSource("zeroToThirty")
    public void testCrossCheckAgainstIterativeReference(int input, int expected) {
        assertEquals(iterativeFib(input), test.fib(input));
    }

    private static int iterativeFib(int n) {
        if (n < 2) {
            return n;
        }
        int a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            int c = a + b;
            a = b;
            b = c;
        }
        return b;
    }

    /**
     * Negative input is outside the LeetCode constraint and the current
     * implementation recurses indefinitely. Document the contract: callers
     * must stay within [0, 30]. We pin behaviour for n=0 to guard the base
     * case and rely on the parameterized sweep for the rest.
     */
    @Test
    public void testNegativeInputDocumentation() {
        // Base case must hold; recursive call with n-1, n-2 starting from 0
        // would otherwise infinitely descend.
        assertEquals(0, test.fib(0));
    }
}
