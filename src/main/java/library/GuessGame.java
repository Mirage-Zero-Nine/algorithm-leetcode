package library;

/**
 * API guess(int num) which returns 3 possible results (-1, 1, or 0).
 *
 * @author BorisMirage
 * Time: 2019/10/24 20:02
 * Created with IntelliJ IDEA
 */

public class GuessGame {
    private final int target;

    /**
     * Constructor of guess API.
     *
     * @param n target value
     */
    public GuessGame(int n) {
        this.target = n;
    }

    /**
     * Compare target value to given value
     *
     * @param n given value
     * @return -1 : target value is lower; 1 : target value is higher; 0: correct
     */
    public int guess(int n) {
        return Integer.compare(target, n);
    }
}
