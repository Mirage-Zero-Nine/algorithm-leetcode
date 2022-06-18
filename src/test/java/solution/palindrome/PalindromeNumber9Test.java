package solution.palindrome;

import org.junit.Test;
import solution.palindrome.PalindromeNumber_9;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author BorisMirage
 * Time: 2022/06/18 14:05
 * Created with IntelliJ IDEA
 */

public class PalindromeNumber9Test {
    PalindromeNumber_9 test = new PalindromeNumber_9();

    @Test
    public void test(){
        assertTrue(test.isPalindrome(9));
        assertTrue(test.isPalindrome(121));
    }
}