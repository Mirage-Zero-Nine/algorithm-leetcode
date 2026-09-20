package solutions.backtracking;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CanIWin_464Test {

    private static void assertCanWin(int maxChoosableInteger, int desiredTotal, boolean expected) {
        // The online judge invokes a solution for one game. A fresh object keeps
        // memoized positions from a different game from affecting this case.
        assertEquals(expected, new CanIWin_464().canIWin(maxChoosableInteger, desiredTotal),
                () -> "max=" + maxChoosableInteger + ", target=" + desiredTotal);
    }

    /** Independent minimax oracle used only for exhaustive small games. */
    private static boolean oracle(int max, int desiredTotal) {
        int sum = max * (max + 1) / 2;
        if (desiredTotal <= max) {
            return true;
        }
        if (sum < desiredTotal) {
            return false;
        }
        return oracleTurn(max, desiredTotal, 0, new HashMap<>());
    }

    private static boolean oracleTurn(int max, int remaining, int chosenMask,
                                      Map<Long, Boolean> memo) {
        if (remaining <= 0) {
            return false;
        }
        long key = (((long) chosenMask) << 32) | remaining;
        Boolean saved = memo.get(key);
        if (saved != null) {
            return saved;
        }
        for (int choice = 1; choice <= max; choice++) {
            int bit = 1 << choice;
            if ((chosenMask & bit) == 0
                    && !oracleTurn(max, remaining - choice, chosenMask | bit, memo)) {
                memo.put(key, true);
                return true;
            }
        }
        memo.put(key, false);
        return false;
    }

    @Test
    void testCannotWin() {
        assertCanWin(10, 11, false);
    }

    @Test
    void testCanWin() {
        assertCanWin(10, 0, true);
    }

    @Test
    void testSmallNumbers() {
        assertCanWin(10, 40, false);
    }

    @Test
    void testImmediateWin() {
        assertCanWin(5, 5, true);
    }

    @Test
    void testLargeTotal() {
        assertCanWin(5, 50, false);
    }

    @Test
    void testDesiredTotalZero() {
        assertCanWin(3, 0, true);
    }

    @Test
    void testDesiredTotalOne() {
        assertCanWin(10, 1, true);
    }

    @Test
    void testMaxChoosable20() {
        assertCanWin(20, 210, false);
    }

    @Test
    void testCannotReachTotal() {
        // sum of 1..4 = 10, so total 11 is unreachable
        assertCanWin(4, 11, false);
    }

    @Test
    void testFirstPlayerWinsMax4Total6() {
        assertCanWin(4, 6, true);
    }

    @Test
    void testGiantCase() {
        // Maximum chooser range and the largest target allowed by the
        // contract. The implementation's reachability guard handles this
        // case immediately, keeping the test deterministic and fast.
        assertCanWin(20, 300, false);
    }

    @Test
    void testLargeCaseMatchesIndependentOracle() {
        // The reference minimax uses (chosen mask, remaining total), rather
        // than the production implementation's compact mask-only memo key.
        assertCanWin(10, 40, oracle(10, 40));
    }

    @Test
    void testMemoizationDoesNotLeakAcrossTargets() {
        CanIWin_464 solution = new CanIWin_464();

        assertEquals(false, solution.canIWin(10, 11));
        assertEquals(true, solution.canIWin(10, 12));
    }

    @Test
    void testMemoizationDoesNotLeakAcrossMaximums() {
        CanIWin_464 solution = new CanIWin_464();

        assertEquals(false, solution.canIWin(4, 5));
        // Choosing 1 leaves the opponent unable to reach 7 immediately;
        // whatever remains, the first player can then take a winning value.
        assertEquals(true, solution.canIWin(5, 7));
    }

    @Test
    void smallestChooserCannotReachTwo() {
        assertCanWin(1, 2, false);
    }

    @Test
    void twoChooserCanTakeTargetTwo() {
        assertCanWin(2, 2, true);
    }

    @Test
    void twoChooserLosesTargetThree() {
        assertCanWin(2, 3, false);
    }

    @Test
    void twoChooserLosesUnreachableTargetFour() {
        assertCanWin(2, 4, false);
    }

    @Test
    void threeChooserWinsTargetSix() {
        assertCanWin(3, 6, true);
    }

    @Test
    void threeChooserLosesAfterAllNumbersAreAvailable() {
        assertCanWin(3, 7, false);
    }

    @Test
    void fourChooserLosesTargetFive() {
        assertCanWin(4, 5, false);
    }

    @Test
    void fourChooserLosesTargetTen() {
        assertCanWin(4, 10, false);
    }

    @Test
    void fiveChooserWinsTargetFifteen() {
        assertCanWin(5, 15, true);
    }

    @Test
    void sixChooserLosesTargetSeven() {
        assertCanWin(6, 7, false);
    }

    @Test
    void sixChooserWinsTargetTwelve() {
        assertCanWin(6, 12, true);
    }

    @Test
    void tenChooserWinsTargetTwelve() {
        assertCanWin(10, 12, true);
    }

    @Test
    void tenChooserLosesWhenTargetExceedsTotalSum() {
        assertCanWin(10, 56, false);
    }

    @Test
    void maximumChooserWinsImmediateMaximumTarget() {
        assertCanWin(20, 20, true);
    }

    @Test
    void maximumChooserLosesTargetTwentyOne() {
        assertCanWin(20, 21, false);
    }

    @Test
    void maximumChooserWinsTargetFifty() {
        assertCanWin(20, 50, true);
    }

    @Test
    void maximumChooserLosesJustBelowExactSum() {
        assertCanWin(20, 209, false);
    }

    @Test
    void maximumChooserLosesBeyondSumOfAllNumbers() {
        assertCanWin(20, 300, false);
    }

    @Test
    void exhaustiveSmallGamesMatchIndependentMinimaxOracle() {
        // This covers every target through (and just beyond) the reachable sum
        // for max values 1..8, including every no-move and no-win outcome.
        for (int max = 1; max <= 8; max++) {
            int sum = max * (max + 1) / 2;
            for (int target = 0; target <= sum + 2; target++) {
                assertCanWin(max, target, oracle(max, target));
            }
        }
    }
}
