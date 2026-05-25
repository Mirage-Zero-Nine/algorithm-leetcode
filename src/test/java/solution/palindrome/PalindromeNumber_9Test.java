package solution.palindrome;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * @author BorisMirage
 * Time: 2022/06/18 14:05
 * Created with IntelliJ IDEA
 */

public class PalindromeNumber_9Test {
    PalindromeNumber_9 test = new PalindromeNumber_9();

    @Test
    public void test(){
        Assertions.assertTrue(test.isPalindrome(9));
        Assertions.assertTrue(test.isPalindrome(121));
    }

    @Test
    public void testHappyCases() {
        Assertions.assertTrue(test.isPalindrome(0));
        Assertions.assertTrue(test.isPalindrome(11));
        Assertions.assertTrue(test.isPalindrome(1221));
        Assertions.assertTrue(test.isPalindrome(1234321));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        Assertions.assertFalse(test.isPalindrome(-121));
        Assertions.assertFalse(test.isPalindrome(10));
        Assertions.assertFalse(test.isPalindrome(123));
    }

    @Test
    public void testGiantCase() {
        Assertions.assertTrue(test.isPalindrome(2147447412));
    }

    @Test
    public void testSingleDigits() {
        for (int i = 0; i <= 9; i++) {
            Assertions.assertTrue(test.isPalindrome(i));
        }
    }

    @Test
    public void testEvenLengthPalindromes() {
        Assertions.assertTrue(test.isPalindrome(1001));
        Assertions.assertTrue(test.isPalindrome(123321));
        Assertions.assertTrue(test.isPalindrome(99));
    }

    @Test
    public void testOddLengthPalindromes() {
        Assertions.assertTrue(test.isPalindrome(12321));
        Assertions.assertTrue(test.isPalindrome(10001));
        Assertions.assertTrue(test.isPalindrome(9));
    }

    @Test
    public void testNonPalindromes() {
        Assertions.assertFalse(test.isPalindrome(12));
        Assertions.assertFalse(test.isPalindrome(1000));
        Assertions.assertFalse(test.isPalindrome(12345));
    }

    @Test
    public void testNegativeNumbers() {
        Assertions.assertFalse(test.isPalindrome(-1));
        Assertions.assertFalse(test.isPalindrome(-121));
        Assertions.assertFalse(test.isPalindrome(Integer.MIN_VALUE));
    }

    @Test
    public void testPowersOfTen() {
        Assertions.assertFalse(test.isPalindrome(10));
        Assertions.assertFalse(test.isPalindrome(100));
        Assertions.assertFalse(test.isPalindrome(1000));
        Assertions.assertFalse(test.isPalindrome(10000));
    }

    /**
     * Iterable sweep 0..200 cross-checked against a string-reverse reference.
     * The string-based check is the obvious-but-slow oracle; the impl under
     * test uses arithmetic reversal and must agree on every value. This
     * range covers single-, double-, and triple-digit palindromes
     * (0..9, 11, 22, ..., 99, 101, 111, ..., 191).
     */
    @ParameterizedTest(name = "isPalindrome({0})")
    @MethodSource("zeroToTwoHundred")
    public void testEveryValueFromZeroToTwoHundred(int input, boolean expected) {
        Assertions.assertEquals(expected, test.isPalindrome(input));
    }

    private static Stream<org.junit.jupiter.params.provider.Arguments> zeroToTwoHundred() {
        return IntStream.rangeClosed(0, 200)
                .mapToObj(i -> arguments(i, isPalindromeReference(i)));
    }

    /**
     * Spot-check selected larger values, including 4..7 digit palindromes
     * and the 32-bit boundary palindrome 2147447412.
     */
    @ParameterizedTest(name = "isPalindrome({0}) = {1}")
    @org.junit.jupiter.params.provider.CsvSource({
            "1001, true",
            "1221, true",
            "12321, true",
            "12345, false",
            "100001, true",
            "123321, true",
            "123456, false",
            "1234321, true",
            "10000001, true",
            "12344321, true",
            "999999999, true",
            "1000000001, true",
            "2147447412, true",
            "2147483647, false"
    })
    public void testLargerValuesSpotCheck(int input, boolean expected) {
        Assertions.assertEquals(expected, test.isPalindrome(input));
    }

    /**
     * String-reverse reference oracle. Negative inputs are never palindromes
     * per the problem definition.
     */
    private static boolean isPalindromeReference(int x) {
        if (x < 0) return false;
        String s = Integer.toString(x);
        return new StringBuilder(s).reverse().toString().equals(s);
    }

    /**
     * Negative inputs should always return false, no exceptions. Sweep a
     * representative sample including the int min boundary.
     */
    @ParameterizedTest(name = "negative isPalindrome({0}) -> false")
    @ValueSource(ints = {
            -1, -2, -9, -10, -11, -121, -1000, -12321, -1234321,
            -2147447412, Integer.MIN_VALUE
    })
    public void testNegativeInputsAlwaysFalse(int input) {
        Assertions.assertFalse(test.isPalindrome(input));
    }

    /**
     * Trailing-zero non-palindromes: any positive number ending in 0 (other
     * than 0 itself) cannot be a palindrome because it would need a leading
     * zero. Sweep multiples of 10 up to 100000.
     */
    @ParameterizedTest(name = "trailing-zero {0} -> false")
    @ValueSource(ints = {10, 20, 50, 100, 200, 1000, 1230, 9990, 12340, 99990, 100000})
    public void testTrailingZeroNumbersAreNeverPalindrome(int input) {
        Assertions.assertFalse(test.isPalindrome(input));
    }
}
