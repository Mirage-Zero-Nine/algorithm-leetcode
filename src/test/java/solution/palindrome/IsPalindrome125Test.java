package solution.palindrome;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IsPalindrome125Test {
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
}
