package solution.others;

/**
 * Given the string croakOfFrogs, which represents a combination of the string "croak" from different frogs.
 * Multiple frogs can croak at the same time, so multiple “croak” are mixed.
 * Return the minimum number of different frogs to finish all the croak in the given string.
 * A valid "croak" means a frog is printing 5 letters ‘c’, ’r’, ’o’, ’a’, ’k’ sequentially.
 * The frogs have to print all five letters to finish a croak.
 * If the given string is not a combination of valid "croak" return -1.
 *
 * @author BorisMirage
 * Time: 2020/04/22 06:56
 * Created with IntelliJ IDEA
 */

public class MinNumberOfFrogs_1419 {
    /**
     * Each letter in "croak" should be in order to make result valid.
     * Therefore, keep five counter to count the number of char in given string.
     * When reaches c, one new frog required. When reaches the k, one frog released.
     *
     * @param croak given string
     * @return minimum number of different frogs to finish all the croak in the given string, or -1 if invalid
     */
    public int minNumberOfFrogs(String croak) {
        int c = 0, r = 0, o = 0, a = 0, k = 0, newFrog = 0, out = 0;

        for (char d : croak.toCharArray()) {
            switch (d) {
                case 'c':
                    c++;
                    newFrog++;      // start of word, add a lock
                    break;
                case 'r':
                    r++;
                    break;
                case 'o':
                    o++;
                    break;
                case 'a':
                    a++;
                    break;
                case 'k':
                    k++;
                    newFrog--;      // reaches the end of word, release a frog lock
                    break;
            }
            out = Math.max(out, newFrog);

            if ((c < r) || (r < o) || (o < a) || (a < k)) {     // if chars are not in order, return -1
                return -1;
            }
        }

        return (newFrog == 0 && c == r && c == o && c == a && c == k) ? out : -1;       // if all chars are used
    }
}
