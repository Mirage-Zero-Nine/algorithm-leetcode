package solutions.stack;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.Timeout.ThreadMode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IsValid_20Test {
    private final IsValid_20 solution = new IsValid_20();

    @Test
    public void testEmptyString() {
        assertTrue(solution.isValid(""));
    }

    @Test
    public void testValidRoundParenthesisPair() {
        assertTrue(solution.isValid("()"));
    }

    @Test
    public void testValidSquareBracketPair() {
        assertTrue(solution.isValid("[]"));
    }

    @Test
    public void testValidCurlyBracketPair() {
        assertTrue(solution.isValid("{}"));
    }

    @Test
    public void testValidSequentialPairs() {
        assertTrue(solution.isValid("()[]{}"));
    }

    @Test
    public void testValidLongNestedAndSequentialInput() {
        assertTrue(solution.isValid("({[]})(){{[[(())]]}}{}"));
    }

    @Test
    public void testValidNestedPairFollowedByOtherTypes() {
        assertTrue(solution.isValid("(())[]{}"));
    }

    @Test
    public void testValidMixedNesting() {
        assertTrue(solution.isValid("{[()]}"));
    }

    @Test
    public void testValidOtherMixedNesting() {
        assertTrue(solution.isValid("({[]})"));
    }

    @Test
    public void testValidNestedThenSequentialPair() {
        assertTrue(solution.isValid("({[]})()"));
    }

    @Test
    public void testValidSameTypeNesting() {
        assertTrue(solution.isValid("(((((((((())))))))))"));
    }

    @Test
    public void testInvalidSingleOpener() {
        assertFalse(solution.isValid("("));
    }

    @Test
    public void testInvalidMultipleOpeners() {
        assertFalse(solution.isValid("((("));
    }

    @Test
    public void testInvalidTwoUnmatchedOpeners() {
        assertFalse(solution.isValid("(("));
    }

    @Test
    public void testInvalidSingleCloser() {
        assertFalse(solution.isValid(")"));
    }

    @Test
    public void testInvalidMultipleClosers() {
        assertFalse(solution.isValid(")))"));
    }

    @Test
    public void testInvalidTwoUnmatchedClosers() {
        assertFalse(solution.isValid("))"));
    }

    @Test
    public void testInvalidUnmatchedSquareCloser() {
        assertFalse(solution.isValid("]"));
    }

    @Test
    public void testInvalidUnmatchedCurlyCloser() {
        assertFalse(solution.isValid("}"));
    }

    @Test
    public void testInvalidRoundThenSquareMismatch() {
        assertFalse(solution.isValid("(]"));
    }

    @Test
    public void testInvalidSquareThenRoundMismatch() {
        assertFalse(solution.isValid("[)"));
    }

    @Test
    public void testInvalidCrossedRoundAndSquarePairs() {
        assertFalse(solution.isValid("([)]"));
    }

    @Test
    public void testInvalidCrossedRoundAndCurlyPairs() {
        assertFalse(solution.isValid("({)}"));
    }

    @Test
    public void testInvalidExtraClosers() {
        assertFalse(solution.isValid("()}}"));
    }

    @Test
    public void testInvalidCloserAfterValidPrefix() {
        assertFalse(solution.isValid("()[}"));
    }

    @Test
    public void testInvalidExtraCloserAfterNestedPairs() {
        assertFalse(solution.isValid("{[()]}}"));
    }

    @Test
    public void testInvalidCrossedSquareAndCurlyPairs() {
        assertFalse(solution.isValid("[{]}"));
    }

    @Test
    public void testInvalidCloserWithNoMatchingOpeners() {
        assertFalse(solution.isValid("]{"));
    }

    @Test
    public void testValidPreviouslyCoveredExample() {
        assertTrue(solution.isValid("{[({})]}"));
    }

    @Test
    @Timeout(value = 10, threadMode = ThreadMode.SEPARATE_THREAD)
    public void testExhaustiveShortStrings() {
        char[] brackets = {'(', ')', '[', ']', '{', '}'};
        // Exhaust all 55,987 strings of lengths zero through six.
        assertEquals(isValidByPairReduction(""), solution.isValid(""), "input: empty string");
        for (int length = 1; length <= 6; length++) {
            assertAllStringsOfLength(new StringBuilder(length), length, brackets);
        }
    }

    private void assertAllStringsOfLength(StringBuilder candidate, int length, char[] brackets) {
        if (candidate.length() == length) {
            String input = candidate.toString();
            assertEquals(isValidByPairReduction(input), solution.isValid(input), "input: " + input);
            return;
        }

        for (char bracket : brackets) {
            candidate.append(bracket);
            assertAllStringsOfLength(candidate, length, brackets);
            candidate.deleteCharAt(candidate.length() - 1);
        }
    }

    /** Recognizes short inputs independently by repeatedly removing adjacent matching pairs. */
    private boolean isValidByPairReduction(String input) {
        StringBuilder remaining = new StringBuilder(input);
        boolean removedPair;
        do {
            removedPair = false;
            for (int i = 0; i + 1 < remaining.length(); i++) {
                if (matches(remaining.charAt(i), remaining.charAt(i + 1))) {
                    remaining.delete(i, i + 2);
                    removedPair = true;
                    break;
                }
            }
        } while (removedPair);
        return remaining.isEmpty();
    }

    private boolean matches(char open, char close) {
        return (open == '(' && close == ')')
                || (open == '[' && close == ']')
                || (open == '{' && close == '}');
    }

    private String maximumLengthValidInput() {
        return "(".repeat(5_000) + ")".repeat(5_000);
    }

    @Test
    @Timeout(value = 10, threadMode = ThreadMode.SEPARATE_THREAD)
    public void testMaximumLengthValidInput() {
        String valid = maximumLengthValidInput();
        assertTrue(solution.isValid(valid), "A maximum-length balanced sequence should be valid");
    }

    @Test
    @Timeout(value = 10, threadMode = ThreadMode.SEPARATE_THREAD)
    public void testMaximumLengthInputMissingFinalCloser() {
        String valid = maximumLengthValidInput();
        assertFalse(solution.isValid(valid.substring(0, valid.length() - 1)),
                "A maximum-length prefix missing its final closer should be invalid");
    }

    @Test
    @Timeout(value = 10, threadMode = ThreadMode.SEPARATE_THREAD)
    public void testMaximumLengthInputWithWrongFinalCloser() {
        String valid = maximumLengthValidInput();
        String mismatchAtEnd = valid.substring(0, valid.length() - 1) + "]";
        assertFalse(solution.isValid(mismatchAtEnd),
                "A maximum-length sequence with a mismatched final closer should be invalid");
    }

    @Test
    public void testDeeplyNestedValidSequence() {
        String input = "(".repeat(500) + ")".repeat(500);
        assertTrue(solution.isValid(input));
    }

    @Test
    public void testDeeplyNestedMixedSequence() {
        StringBuilder input = new StringBuilder();
        char[] open = {'(', '[', '{'};
        char[] close = {')', ']', '}'};
        for (int i = 0; i < 300; i++) {
            input.append(open[i % 3]);
        }
        for (int i = 299; i >= 0; i--) {
            input.append(close[i % 3]);
        }
        assertTrue(solution.isValid(input.toString()));
    }

    @Test
    public void testRepeatedCallsDoNotShareStackState() {
        assertFalse(solution.isValid("("));
        assertTrue(solution.isValid("[]{}()"));
        assertTrue(solution.isValid(""));
        assertFalse(solution.isValid("]"));
    }
}
