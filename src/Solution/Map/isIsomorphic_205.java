package Solution.Map;

/**
 * Given two strings s and t, determine if they are isomorphic.
 * Two strings are isomorphic if the characters in s can be replaced to get t.
 * All occurrences of a character must be replaced with another character while preserving the order of characters.
 * No two characters may map to the same character but a character may map to itself.
 * You may assume both s and t have the same length.
 *
 * @author BorisMirage
 * Time: 2020/03/21 13:44
 * Created with IntelliJ IDEA
 */

public class isIsomorphic_205 {
    /**
     * Use int array to replace two maps. Note that two maps are required.
     * Count the frequency. If the frequency is not matched, return false.
     *
     * @param s first string
     * @param t second string
     * @return if strings are isomorphic
     */
    public boolean isIsomorphic(String s, String t) {

        /* Corner case */
        if (s.length() == 0 || t.length() == 0) {
            return true;
        }

        int[] m1 = new int[128];        // s -> t
        int[] m2 = new int[128];        // t -> s

        for (int i = 0; i < s.length(); i++) {
            char cs = s.charAt(i);
            char ct = t.charAt(i);

            if (m1[cs] != m2[ct]) {
                return false;
            }

            m1[cs] = i + 1;     // they should be same frequency
            m2[ct] = i + 1;
        }

        return true;
    }
}
