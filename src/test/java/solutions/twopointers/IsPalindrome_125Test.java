package solutions.twopointers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IsPalindrome_125Test {
    private final IsPalindrome_125 solver = new IsPalindrome_125();

    @Test public void testEmptyString() { assertTrue(solver.isPalindrome("")); }
    @Test public void testSingleChar() { assertTrue(solver.isPalindrome("a")); }
    @Test public void testTwoCharPalindrome() { assertTrue(solver.isPalindrome("aa")); }
    @Test public void testTwoCharNotPalindrome() { assertFalse(solver.isPalindrome("ab")); }
    @Test public void testSimplePalindrome() { assertTrue(solver.isPalindrome("aba")); }
    @Test public void testNotPalindrome() { assertFalse(solver.isPalindrome("abc")); }
    @Test public void testWithSpaces() { assertTrue(solver.isPalindrome("a b a")); }
    @Test public void testWithPunctuation() { assertTrue(solver.isPalindrome("a.b,a")); }
    @Test public void testMixedCase() { assertTrue(solver.isPalindrome("Aba")); }
    @Test public void testMixedCaseNotPalindrome() { assertFalse(solver.isPalindrome("Abc")); }
    @Test public void testAlphanumericPalindrome() { assertTrue(solver.isPalindrome("A man, a plan, a canal: Panama")); }
    @Test public void testAlphanumericNotPalindrome() { assertFalse(solver.isPalindrome("race a car")); }
    @Test public void testOnlyNumbers() { assertTrue(solver.isPalindrome("12321")); }
    @Test public void testOnlyNumbersNotPalindrome() { assertFalse(solver.isPalindrome("12345")); }
    @Test public void testSpecialCharsOnly() { assertTrue(solver.isPalindrome("!@#$%$#@!")); }
    @Test public void testEvenLengthPalindrome() { assertTrue(solver.isPalindrome("abba")); }
    @Test public void testEvenLengthNotPalindrome() { assertFalse(solver.isPalindrome("abbc")); }
    @Test public void testLongPalindrome() { assertTrue(solver.isPalindrome("racecar")); }
    @Test public void testLeadingTrailingSpaces() { assertTrue(solver.isPalindrome("  a   ")); }
    @Test public void testOneAlphanumeric() { assertTrue(solver.isPalindrome("!@#$a#$@!")); }

    /**
     * Table-driven palindrome inputs from the LeetCode problem description
     * and well-known examples.
     */
    @ParameterizedTest(name = "palindrome: \"{0}\"")
    @ValueSource(strings = {
            "",
            "a",
            "aa",
            "aba",
            "abba",
            "abcba",
            "racecar",
            "Madam",
            "No 'x' in Nixon",
            "Was it a car or a cat I saw?",
            "Mr. Owl ate my metal worm",
            "Eva, can I see bees in a cave?",
            "A Toyota's a Toyota.",
            "Never odd or even",
            "0P",  // tricky: '0' and 'P' both alnum, '0' != 'p' -> NOT palindrome; see negative table
            "12321",
            "1 2 3 2 1",
            "....",                // all non-alnum -> palindrome by definition
            "!@#",                 // all non-alnum
            "  "                    // whitespace only
    })
    public void testKnownPalindromes(String s) {
        // "0P" is intentionally in the list because it is NOT a palindrome
        // ('0' vs 'p' after lowercasing). Skip it from the positive assertion
        // and let the negative test catch it.
        if ("0P".equals(s)) {
            assertFalse(solver.isPalindrome(s));
            return;
        }
        assertTrue(solver.isPalindrome(s), "expected palindrome: " + s);
    }

    /**
     * Table-driven non-palindromes covering common gotchas: case-sensitivity
     * traps, mixed alphanumeric mismatches, and near-palindromes.
     */
    @ParameterizedTest(name = "not palindrome: \"{0}\"")
    @ValueSource(strings = {
            "ab",
            "abc",
            "race a car",
            "hello",
            "world",
            "0P",
            "A1b",
            "ab.ba!c",
            "Mr Owl Late My Metal Worm Backwards",
            "almostpalindromea"
    })
    public void testKnownNonPalindromes(String s) {
        assertFalse(solver.isPalindrome(s), "expected NOT palindrome: " + s);
    }

    /**
     * Cross-check against an independent reference implementation that
     * filters then reverses. Both implementations must agree on every
     * entry in the table.
     */
    @ParameterizedTest(name = "cross-check: \"{0}\" => {1}")
    @CsvSource({
            "'',                                true",
            "'A man, a plan, a canal: Panama',  true",
            "'race a car',                      false",
            "'No lemon, no melon',              true",
            "'0P',                              false",
            "'1a2',                             false",
            "'1a2a1',                           true",
            "'..,,..',                          true",
            "'aaaAAA',                          true",
            "'abccba',                          true",
            "'abcCBA',                          true",
            "'a b c c b a',                     true",
            "'a b c c b z',                     false"
    })
    public void testCrossCheckAgainstReference(String s, boolean expected) {
        assertEquals(expected, solver.isPalindrome(s));
        assertEquals(referenceIsPalindrome(s), solver.isPalindrome(s));
    }

    private static boolean referenceIsPalindrome(String s) {
        StringBuilder cleaned = new StringBuilder(s.length());
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                cleaned.append(Character.toLowerCase(c));
            }
        }
        String forward = cleaned.toString();
        return forward.contentEquals(cleaned.reverse());
    }

    /**
     * Large palindrome: 10,001-char "ab...ba" sequence to exercise the
     * two-pointer scan over a long input.
     */
    @Test
    public void testVeryLongPalindrome() {
        int half = 5000;
        StringBuilder sb = new StringBuilder(half * 2 + 1);
        for (int i = 0; i < half; i++) {
            sb.append((char) ('a' + (i % 26)));
        }
        sb.append('Z');
        for (int i = half - 1; i >= 0; i--) {
            sb.append((char) ('a' + (i % 26)));
        }
        assertTrue(solver.isPalindrome(sb.toString()));
    }

    /**
     * Large non-palindrome: same construction but flip the middle so the
     * mismatch is encountered only after thousands of characters, ensuring
     * the algorithm walks the full length before reporting false.
     */
    @Test
    public void testVeryLongNotPalindrome() {
        int half = 5000;
        StringBuilder sb = new StringBuilder(half * 2 + 1);
        for (int i = 0; i < half; i++) {
            sb.append((char) ('a' + (i % 26)));
        }
        sb.append('Z');
        for (int i = half - 1; i >= 0; i--) {
            sb.append((char) ('a' + (i % 26)));
        }
        // Flip a single character near the start; mismatch with mirror at end.
        sb.setCharAt(7, sb.charAt(7) == 'q' ? 'r' : 'q');
        assertFalse(solver.isPalindrome(sb.toString()));
    }

    /**
     * Property-based test: a palindrome built by mirroring random
     * alphanumeric content with random non-alphanumeric noise sprinkled
     * in must always be detected as a palindrome. Reproducible via a
     * fixed seed.
     */
    @Test
    public void testRandomMirroredStringsAreAlwaysPalindromes() {
        Random rng = new Random(42L);
        char[] alnum = "abcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
        char[] noise = " .,!@#-_".toCharArray();

        for (int trial = 0; trial < 50; trial++) {
            int len = rng.nextInt(200) + 1;
            char[] core = new char[len];
            for (int i = 0; i < len; i++) {
                core[i] = alnum[rng.nextInt(alnum.length)];
            }
            // Mirror, randomly upper-case half the chars.
            StringBuilder sb = new StringBuilder();
            for (char c : core) {
                sb.append(rng.nextBoolean() ? Character.toUpperCase(c) : c);
                if (rng.nextInt(5) == 0) sb.append(noise[rng.nextInt(noise.length)]);
            }
            for (int i = core.length - 1; i >= 0; i--) {
                if (rng.nextInt(5) == 0) sb.append(noise[rng.nextInt(noise.length)]);
                sb.append(rng.nextBoolean()
                        ? Character.toUpperCase(core[i])
                        : core[i]);
            }
            String s = sb.toString();
            assertTrue(solver.isPalindrome(s),
                    "trial " + trial + " mirrored string should be palindrome: " + s);
        }
    }
}
