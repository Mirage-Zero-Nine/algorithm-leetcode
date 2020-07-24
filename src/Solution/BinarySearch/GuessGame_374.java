package Solution.BinarySearch;

import Lib.GuessGame;

/**
 * We are playing the Guess Game. The game is as follows:
 * I pick a number from 1 to n. You have to guess which number I picked.
 * Every time you guess wrong, I'll tell you whether the number is higher or lower.
 * You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0):
 * -1 : My number is lower
 * 1 : My number is higher
 * 0 : Congrats! You got it!
 *
 * @author BorisMirage
 * Time: 2019/10/24 20:01
 * Created with IntelliJ IDEA
 */

public class GuessGame_374 extends GuessGame {
    public GuessGame_374(int n) {
        super(n);
    }

    /**
     * Basic binary search.
     *
     * @param n number range from 1 to n
     * @return target int
     */
    public int guessNumber(int n) {
        int lower = 1, upper = n, mid;

        while (lower <= upper) {
            mid = lower + (upper - lower) / 2;
            if (guess(mid) == -1) {
                upper = mid - 1;
            } else if (guess(mid) == 1) {
                lower = mid + 1;
            } else {
                return mid;
            }
        }

        return -1;
    }
}
