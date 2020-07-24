package Solution.Others;

/**
 * Given two strings s1, s2 with same size, check if a permutation of s1 can break a permutation of s2 or vice-versa.
 * A string x can break string y (both of size n) if x[i] >= y[i] (in alphabetical order) for all i between 0 and n - 1.
 *
 * @author BorisMirage
 * Time: 2020/05/08 17:49
 * Created with IntelliJ IDEA
 */

public class CheckIfCanBreak_1433 {
    /**
     * The basic idea is to sort the both strings and check s1 is not strictly larger or not strictly smaller than s2.
     * However, the input only contains lowercase letters.
     * Hence, keep a integer array to count the appearance of chars in both string.
     * Add one at each char in first string, minus one at second.
     * Then, traverse the array and calculate the sum of array.
     * If the sum becomes positive, then the sum should always be larger or equal to 0.
     * This is the case which s1 is not smaller than s2.
     * If the sum becomes negative, then the sum should always be smaller or equal to 0, same principle.
     *
     * @param s1 first string
     * @param s2 second string
     * @return whether a permutation of s1 can break a permutation of s2 or vice-versa
     */
    public boolean checkIfCanBreak(String s1, String s2) {

        /* Corner case */
        if (s1 == null || s2 == null || s1.length() != s2.length()) {
            return false;
        }

        int[] tmp = new int[26];
        int n = s1.length();
        for (int i = 0; i < n; i++) {
            tmp[s1.charAt(i) - 'a']++;
            tmp[s2.charAt(i) - 'a']--;
        }

        int positive = 0, negative = 0, sum = 0;

        for (int i = 0; i < 26; i++) {
            sum += tmp[i];
            if (sum > 0) {
                positive = 1;
            }
            if (sum < 0) {
                negative = 1;
            }

            if (positive + negative > 1) {
                return false;
            }
        }

        return true;
    }
}
