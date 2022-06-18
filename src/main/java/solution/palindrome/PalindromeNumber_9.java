package solution.palindrome;

/**
 * Determine whether an integer is a palindrome.
 * An integer is a palindrome when it reads the same backward as forward.
 *
 * @author BorisMirage
 * Time: 2018/05/19 17:25
 * Created with IntelliJ IDEA
 */

public class PalindromeNumber_9 {
    /**
     * Each time construct a new number by 10^n + x % 10.
     * If length of x is even, then the palindrome number will have same left and right part.
     * If length is even, then left part will be larger. And if it is palindrome, this one digit will be the center.
     * Check left part and right part to see if this given number is palindrome.
     *
     * @param x input int
     * @return true if number is palindrome
     */
    public boolean isPalindrome(int x) {

        /* Corner case */
        if (x < 0) {
            return false;
        }

        if (x <= 9) {
            return true;
        }

        int tmp = x, output = 0;
        while (tmp != 0) {
            int tail = tmp % 10;
            output = output * 10 + tail;
            tmp = tmp / 10;
        }

        return x == output;
    }
}
