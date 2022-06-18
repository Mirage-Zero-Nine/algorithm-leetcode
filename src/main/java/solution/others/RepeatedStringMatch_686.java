package solution.others;

/**
 * Given two strings A and B, find the minimum number of times A has to be repeated such that B is a substring of it.
 * If no such solution, return -1.
 * For example, with A = "abcd" and B = "cdabcdab".
 * Return 3, by repeating A three times (“abcdabcdabcd”), B is a substring of it.
 * Note: The length of A and B will be between 1 and 10000.
 *
 * @author BorisMirage
 * Time: 2020/04/15 20:26
 * Created with IntelliJ IDEA
 */

public class RepeatedStringMatch_686 {
    /**
     * First of all, the combined string should be longer than B.
     * Second, if the combined string does not contains B, then try to append A one more time.
     * If still not contains B, then return -1.
     *
     * @param A string A
     * @param B string B
     * @return the minimum number of times A has to be repeated such that B is a substring of it, or -1 if not contains
     */
    public int repeatedStringMatch(String A, String B) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        while (sb.length() < B.length()) {
            sb.append(A);
            count++;
        }

        if (sb.toString().contains(B)) {
            return count;
        }
        if (sb.append(A).toString().contains(B)) {
            return count + 1;
        }

        return -1;
    }
}
