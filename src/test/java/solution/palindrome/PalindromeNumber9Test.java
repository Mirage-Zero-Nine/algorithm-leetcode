package solution.palindrome;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2022/06/18 14:05
 * Created with IntelliJ IDEA
 */

public class PalindromeNumber9Test {
    PalindromeNumber_9 test = new PalindromeNumber_9();

    @Test
    public void test(){
        Assertions.assertTrue(test.isPalindrome(9));
        Assertions.assertTrue(test.isPalindrome(121));
    }
}