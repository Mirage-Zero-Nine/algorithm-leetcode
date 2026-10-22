package library;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class GuessGameTest {
    @ParameterizedTest @ValueSource(ints = {-10, -1, 0, 1, 10, Integer.MIN_VALUE, Integer.MAX_VALUE})
    void exactGuessReturnsZero(int target) { assertEquals(0, new GuessGame(target).guess(target)); }
    @ParameterizedTest @ValueSource(ints = {-10, -1, 0, 1, 10})
    void targetHigherReturnsOne(int target) { assertEquals(1, new GuessGame(target).guess(target - 1)); }
    @ParameterizedTest @ValueSource(ints = {-10, -1, 0, 1, 10})
    void targetLowerReturnsNegativeOne(int target) { assertEquals(-1, new GuessGame(target).guess(target + 1)); }
}
