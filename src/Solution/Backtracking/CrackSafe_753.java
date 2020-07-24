package Solution.Backtracking;

import java.util.Collections;
import java.util.HashSet;

/**
 * There is a box protected by a password.
 * The password is a sequence of n digits where each digit can be one of the first k digits 0, 1, ..., k-1.
 * While entering a password, the last n digits entered will automatically be matched against the correct password.
 * Return any password of minimum length that is guaranteed to open the box at some point of entering it.
 *
 * @author BorisMirage
 * Time: 2019/09/14 15:01
 * Created with IntelliJ IDEA
 */

public class CrackSafe_753 {
    /**
     * Backtracking to find all possible combination, and append each char to result from 0 to k.
     *
     * @param n password sequence of n digits
     * @param k password combination from 0 to k - 1
     * @return any password of minimum length that is guaranteed to open the box at some point of entering it
     */
    public String crackSafe(int n, int k) {
        String init = String.join("", Collections.nCopies(n, "0"));
        HashSet<String> set = new HashSet<>();
        set.add(init);
        StringBuilder sb = new StringBuilder(init);
        backtracking((int) Math.pow(k, n), n, k, sb, set);
        return sb.toString();
    }

    /**
     * Backtracking to find all possible combination.
     *
     * @param limit number of combination with size of n and made up with digits from 0 to k - 1
     * @param n     password sequence of n digits
     * @param k     password combination from 0 to k - 1
     * @param sb    string builder to build one min length combination
     * @param set   contains combinations have been found
     * @return true if set is reached
     */
    private boolean backtracking(int limit, int n, int k, StringBuilder sb, HashSet<String> set) {
        if (set.size() == limit) {
            return true;
        }

        String last = sb.substring(sb.length() - n + 1);        // get last n - 1 digits

        for (int i = 0; i < k; i++) {
            String tmp = last + i;      // add one digit to create a new combination based on n - 1 digits
            if (!set.contains(tmp)) {
                set.add(tmp);
                sb.append(i);
                if (backtracking(limit, n, k, sb, set)) {
                    return true;
                }
                set.remove(tmp);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
        return false;
    }
}
