package solution.twopointers;

/**
 * Given two strings s and t, determine if they are both one edit distance apart.
 *
 * @author BorisMirage
 * Time: 2019/10/21 13:58
 * Created with IntelliJ IDEA
 */

public class IsOneEditDistance_161 {
    /**
     * Two pointers. If the char that point at is different, the try to remove, replace or delete it.
     * There are 2 cases:
     * 1. If the 2 strings are having the same length, replace current char.
     * 2. If the 2 strings are having different length, then delete or insert a char at current position.
     * Insert and remove are actually same thing.
     * Replace: check the rest of string is same.
     * Remove: check the longer one after removing one char is equal to the other string.
     * Insert: check the shorter one is equal to the other one after adding a char.
     * Note that if two strings are equal, then they are not one edit distance.
     * The replace/remove/insert should happen in exact one time.
     *
     * @param s first string
     * @param t second string
     * @return determine if they are both one edit distance apart
     */
    public boolean isOneEditDistance(String s, String t) {
        int n1 = s.length(), n2 = t.length(), p1 = 0, p2 = 0;

        if (s.equals(t)) {
            return false;
        }

        while (p1 < n1 && p2 < n2) {
            if (s.charAt(p1) != t.charAt(p2)) {
                if (n1 == n2) { // if the two strings are having the same length, replace it
                    return s.substring(p1 + 1).equals(t.substring(p2 + 1));
                } else if (n1 > n2) { // if the first string is longer than second one, delete current char in first string
                    return t.equals(s.substring(0, p1) + s.substring(p1 + 1));
                } else {
                    return s.equals(t.substring(0, p2) + t.substring(p2 + 1));
                }
            }
            p1++;
            p2++;
        }

        return (n1 - p1 + n2 - p2) <= 1;        // if the string length difference is larger than 1
    }
}
