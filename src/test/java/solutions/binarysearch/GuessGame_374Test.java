package solutions.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import library.GuessGame;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.Test;

public class GuessGame_374Test {

    @Test
    public void testHappyCases() {
        assertEquals(6, new GuessGame_374(6).guessNumber(10));
        assertEquals(1, new GuessGame_374(1).guessNumber(1));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(1, new GuessGame_374(1).guessNumber(2));
        assertEquals(2, new GuessGame_374(2).guessNumber(2));
    }

    @Test
    public void testLargeCase() {
        assertEquals(1702766719, new GuessGame_374(1702766719).guessNumber(2126753390));
    }

    @Test
    public void testTargetAtUpperBound() {
        assertEquals(100, new GuessGame_374(100).guessNumber(100));
    }

    @Test
    public void testTargetAtLowerBound() {
        assertEquals(1, new GuessGame_374(1).guessNumber(100));
    }

    @Test
    public void testTargetInMiddle() {
        assertEquals(50, new GuessGame_374(50).guessNumber(100));
    }

    @Test
    public void testSmallRangeValues() {
        assertEquals(3, new GuessGame_374(3).guessNumber(5));
        assertEquals(4, new GuessGame_374(4).guessNumber(5));
    }

    @Test
    public void testConsecutiveTargets() {
        for (int t = 1; t <= 20; t++) {
            assertEquals(t, new GuessGame_374(t).guessNumber(20));
        }
    }

    @Test
    public void testLargeRangeDifferentTargets() {
        assertEquals(999999999, new GuessGame_374(999999999).guessNumber(2000000000));
        assertEquals(1500000000, new GuessGame_374(1500000000).guessNumber(2000000000));
    }

    @Test
    public void testGiantBoundaryTarget() {
        int n = Integer.MAX_VALUE;
        assertEquals(n, new GuessGame_374(n).guessNumber(n));
    }

    @ParameterizedTest(name = "n={0}, pick={1}")
    @MethodSource("boundaryAndInteriorCases")
    public void testBoundaryAndInteriorCases(int n, int pick) {
        assertEquals(pick, new GuessGame_374(pick).guessNumber(n));
    }

    private static Stream<Arguments> boundaryAndInteriorCases() {
        return Stream.of(
                Arguments.of(1, 1),
                Arguments.of(2, 1),
                Arguments.of(2, 2),
                Arguments.of(3, 2),
                Arguments.of(4, 3),
                Arguments.of(5, 1),
                Arguments.of(5, 4),
                Arguments.of(5, 5),
                Arguments.of(10, 2),
                Arguments.of(10, 6),
                Arguments.of(10, 9),
                Arguments.of(11, 6),
                Arguments.of(100, 2),
                Arguments.of(100, 50),
                Arguments.of(100, 99),
                Arguments.of(1_000_000_000, 1),
                Arguments.of(1_000_000_000, 500_000_000),
                Arguments.of(1_000_000_000, 999_999_999),
                Arguments.of(2_000_000_000, 1_999_999_999),
                Arguments.of(Integer.MAX_VALUE, 1),
                Arguments.of(Integer.MAX_VALUE, Integer.MAX_VALUE / 2),
                Arguments.of(Integer.MAX_VALUE, Integer.MAX_VALUE - 1),
                Arguments.of(Integer.MAX_VALUE, Integer.MAX_VALUE));
    }

    @Test
    public void testEveryTargetInSmallRanges() {
        // Exhausting all valid targets catches interval-boundary and off-by-one errors
        // without relying on agreement between two implementations of binary search.
        for (int n = 1; n <= 64; n++) {
            for (int pick = 1; pick <= n; pick++) {
                assertEquals(pick, new GuessGame_374(pick).guessNumber(n),
                        "n=" + n + ", pick=" + pick);
            }
        }
    }

    @Test
    public void testGuessApiResponseContract() {
        GuessGame game = new GuessGame(42);

        assertEquals(-1, game.guess(43));
        assertEquals(1, game.guess(41));
        assertEquals(0, game.guess(42));
    }

    @Test
    public void testBothNonMatchingResponseDirectionsAreHandled() {
        ScriptedGuessGame game = new ScriptedGuessGame(6);

        assertEquals(6, game.guessNumber(10));
        assertTrue(game.guesses.stream().anyMatch(guess -> guess < 6));
        assertTrue(game.guesses.stream().anyMatch(guess -> guess > 6));
        assertTrue(game.guesses.contains(6));
        assertTrue(game.guesses.stream().allMatch(guess -> guess >= 1 && guess <= 10));
    }

    @Test
    public void testRepeatedCallsDoNotShareSearchBounds() {
        GuessGame_374 game = new GuessGame_374(73);

        assertEquals(73, game.guessNumber(100));
        assertEquals(73, game.guessNumber(73));
        assertEquals(73, game.guessNumber(1_000));
    }

    @Test
    public void testTargetOutsideRangeReturnsImplementationFallback() {
        // LeetCode never supplies these values, but this class explicitly returns -1
        // when its search interval is exhausted without finding the target.
        assertEquals(-1, new GuessGame_374(10).guessNumber(9));
        assertEquals(-1, new GuessGame_374(0).guessNumber(9));
        assertEquals(-1, new GuessGame_374(1).guessNumber(0));
        assertEquals(-1, new GuessGame_374(1).guessNumber(-4));
    }

    private static final class ScriptedGuessGame extends GuessGame_374 {
        private final int target;
        private final List<Integer> guesses = new ArrayList<>();

        private ScriptedGuessGame(int target) {
            super(1);
            this.target = target;
        }

        @Override
        public int guess(int num) {
            guesses.add(num);
            if (num > target) {
                return -1;
            }
            if (num < target) {
                return 1;
            }
            return 0;
        }
    }
}
