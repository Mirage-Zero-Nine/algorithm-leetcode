package solution.palindrome;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
}
