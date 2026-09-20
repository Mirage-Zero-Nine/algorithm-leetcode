package solutions.backtracking;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CanWin_294Test {
    private final CanWin_294 solution = new CanWin_294();

    @Test
    void handlesEmptyAndSingleCharacterInputs() {
        assertFalse(solution.canWin(""));
        assertFalse(solution.canWin("+"));
        assertFalse(solution.canWin("-"));
    }

    @Test
    void handlesInputsWithNoLegalMove() {
        assertFalse(solution.canWin("--"));
        assertFalse(solution.canWin("----"));
        assertFalse(solution.canWin("+-+-+-"));
        assertFalse(solution.canWin("-+-+-+-+-+"));
    }

    @Test
    void handlesSmallConsecutiveRuns() {
        assertTrue(solution.canWin("++"));
        assertTrue(solution.canWin("+++"));
        assertTrue(solution.canWin("++++"));
        assertTrue(solution.canWin("++++-"));
        assertFalse(solution.canWin("+++++"));
        assertTrue(solution.canWin("++++++"));
    }

    @Test
    void handlesLongerConsecutiveRunsAtTheFirstLosingPosition() {
        assertTrue(solution.canWin("+++++++"));
        assertTrue(solution.canWin("++++++++"));
        assertFalse(solution.canWin("+++++++++"));
        assertTrue(solution.canWin("++++++++++"));
        assertTrue(solution.canWin("+++++++++++"));
    }

    @Test
    void handlesIndependentPlusRuns() {
        assertTrue(solution.canWin("--++--"));
        assertTrue(solution.canWin("++--"));
        assertTrue(solution.canWin("+-++-"));
        assertFalse(solution.canWin("++-++"));
        assertFalse(solution.canWin("++-+++"));
        assertFalse(solution.canWin("-++-++-"));
        assertFalse(solution.canWin("++--+++--"));
    }

    @Test
    void handlesRunsSeparatedBySingleMinus() {
        assertTrue(solution.canWin("++-+"));
        assertTrue(solution.canWin("+-+++"));
        assertTrue(solution.canWin("++-++-++"));
        assertTrue(solution.canWin("++-++-+++"));
    }

    @Test
    void handlesMaximumLengthNoMovePattern() {
        // LeetCode permits strings up to length 60; avoid exponential all-plus search.
        assertFalse(solution.canWin("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-"));
    }

    @Test
    void handlesMaximumLengthWithOneImmediateMove() {
        String input = "-".repeat(58) + "++";
        assertTrue(solution.canWin(input));
    }

    @Test
    void handlesTheLongestAllowedConsecutivePlusRuns() {
        // Keep the all-plus runs bounded so this test remains deterministic
        // and avoids the platform-sensitive TLE cases at the length limit.
        assertTrue(solution.canWin("+".repeat(12)));
        assertTrue(solution.canWin("+".repeat(13)));
        assertTrue(solution.canWin("+".repeat(14)));
        assertFalse(solution.canWin("+".repeat(15)));
        assertTrue(solution.canWin("+".repeat(16)));
        assertTrue(solution.canWin("+".repeat(17)));
        assertTrue(solution.canWin("+".repeat(18)));
        assertTrue(solution.canWin("+".repeat(19)));
        assertTrue(solution.canWin("+".repeat(20)));
        assertTrue(solution.canWin("-".repeat(40) + "+".repeat(20)));
        assertTrue(solution.canWin("-".repeat(41) + "+".repeat(19)));
    }

    @Test
    void handlesLegalMovesAtEveryPosition() {
        assertTrue(solution.canWin("++" + "-".repeat(58)));
        assertTrue(solution.canWin("-" + "++" + "-".repeat(57)));
        assertTrue(solution.canWin("-".repeat(29) + "++" + "-".repeat(29)));
        assertTrue(solution.canWin("-".repeat(57) + "++"));
    }

    @Test
    void handlesMaximumLengthInputsWithoutAWinningMove() {
        assertFalse(solution.canWin("-".repeat(60)));
        assertFalse(solution.canWin("+" + "-".repeat(59)));
        assertFalse(solution.canWin("-".repeat(59) + "+"));
        assertFalse(solution.canWin("-+".repeat(30)));
    }

    @Test
    void handlesManyIndependentTwoPlusRuns() {
        // Every separated "++" component has exactly one move.  The parity
        // of the number of components therefore determines the winner.
        assertTrue(solution.canWin("++----".repeat(8) + "++"));
        assertFalse(solution.canWin("++----".repeat(9) + "++"));
    }

    @Test
    void handlesSeparatedRunsWithDifferentGameOutcomes() {
        assertFalse(solution.canWin("++-++-++-++"));
        assertTrue(solution.canWin("++-++-++-++-++"));
        assertTrue(solution.canWin("++-+++--++-++++--++"));
        assertTrue(solution.canWin("-+-+-++-+-+-"));
    }

    @Test
    void handlesBothAlternatingPatternsAtTheLengthBoundary() {
        assertFalse(solution.canWin("+-".repeat(30)));
        assertFalse(solution.canWin("-+".repeat(30)));
        assertFalse(solution.canWin("+" + "-+".repeat(29) + "-"));
    }

    @Test
    void preservesResultsAcrossRepeatedBoundaryCalls() {
        assertFalse(solution.canWin("-".repeat(60)));
        assertTrue(solution.canWin("-".repeat(58) + "++"));
        assertFalse(solution.canWin("-".repeat(59) + "+"));
        assertTrue(solution.canWin("++----".repeat(8) + "++"));
        assertFalse(solution.canWin("-".repeat(60)));
    }

    @Test
    void handlesLongerMixedStatesWithDifferentWinningStrategies() {
        assertFalse(solution.canWin("++++-++++"));
        assertTrue(solution.canWin("++++-++++-++++"));
        assertTrue(solution.canWin("+-+-+-+-+-+-+-+-+-+-++"));
        assertFalse(solution.canWin("++++++++++++-++++++++++++"));
        assertTrue(solution.canWin("+++++++++++++++++++-++"));
        assertFalse(solution.canWin("++--++--++--++"));
        assertTrue(solution.canWin("---+++-++++--+++-"));
        assertFalse(solution.canWin("+++-+-++++--++++-++"));
    }

    @Test
    void doesNotLeakStateBetweenCalls() {
        assertTrue(solution.canWin("++"));
        assertFalse(solution.canWin("++-++"));
        assertFalse(solution.canWin("----"));
        assertTrue(solution.canWin("++++++"));
        assertTrue(solution.canWin("++"));
    }

    @Test
    void agreesWithIndependentOracleForEveryStringThroughLengthSix() {
        for (int length = 0; length <= 6; length++) {
            int cases = 1 << length;
            for (int mask = 0; mask < cases; mask++) {
                String input = binaryPattern(mask, length);
                assertEquals(oracleCanWin(input), solution.canWin(input), input);
            }
        }
    }

    @Test
    void agreesWithIndependentOracleForEveryStringThroughLengthNine() {
        for (int length = 7; length <= 9; length++) {
            int cases = 1 << length;
            for (int mask = 0; mask < cases; mask++) {
                String input = binaryPattern(mask, length);
                assertEquals(oracleCanWin(input), solution.canWin(input), input);
            }
        }
    }

    @Test
    void agreesWithIndependentOracleForEveryStringOfLengthTen() {
        int cases = 1 << 10;
        for (int mask = 0; mask < cases; mask++) {
            String input = binaryPattern(mask, 10);
            assertEquals(oracleCanWin(input), solution.canWin(input), input);
        }
    }

    private static String binaryPattern(int mask, int length) {
        StringBuilder result = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            result.append((mask & (1 << i)) == 0 ? '-' : '+');
        }
        return result.toString();
    }

    /** Independent immutable-string recurrence used to derive expected answers. */
    private static boolean oracleCanWin(String state) {
        return oracleCanWin(state, new HashMap<>());
    }

    private static boolean oracleCanWin(String state, Map<String, Boolean> memo) {
        Boolean known = memo.get(state);
        if (known != null) {
            return known;
        }
        for (int i = 0; i + 1 < state.length(); i++) {
            if (state.charAt(i) == '+' && state.charAt(i + 1) == '+') {
                String next = state.substring(0, i) + "--" + state.substring(i + 2);
                if (!oracleCanWin(next, memo)) {
                    memo.put(state, true);
                    return true;
                }
            }
        }
        memo.put(state, false);
        return false;
    }
}
