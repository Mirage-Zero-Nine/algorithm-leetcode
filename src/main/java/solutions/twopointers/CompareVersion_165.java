package solutions.twopointers;

/**
 * Compare two version numbers version1 and version2.
 * If version1 > version2 return 1; if version1 < version2 return -1; otherwise return 0.
 * The version strings are non-empty and contain only digits and the . character.
 * The . character does not represent a decimal point and is used to separate number sequences.
 * For instance, 2.5 is not "two and a half" or "half way to version three".
 * It is the fifth second-level revision of the second first-level revision.
 * Default revision number for each level of a version number to be 0.
 * For example, version number 3.4 has a revision number of 3 and 4 for its first and second level revision number.
 * Its third and fourth level revision number are both 0.
 *
 * @author BorisMirage
 * Time: 2019/07/14 21:19
 * Created with IntelliJ IDEA
 */

public class CompareVersion_165 {
    /**
     * Two pointers. Note that leading 0 in version # should not be considered.
     *
     * @param version1 first version #
     * @param version2 second version #
     * @return version1 > version2 return 1; version1 < version2 return -1; otherwise return 0.
     */
    public int compareVersion(String version1, String version2) {
        int p1 = 0, p2 = 0;
        while (p1 < version1.length() || p2 < version2.length()) {
            int end1 = p1;
            while (end1 < version1.length() && version1.charAt(end1) != '.') end1++;
            int end2 = p2;
            while (end2 < version2.length() && version2.charAt(end2) != '.') end2++;

            // Revision fields may be much larger than an int. Compare their
            // canonical lengths and then their digits lexicographically.
            int first1 = p1;
            while (first1 < end1 && version1.charAt(first1) == '0') first1++;
            int first2 = p2;
            while (first2 < end2 && version2.charAt(first2) == '0') first2++;
            int length1 = end1 - first1;
            int length2 = end2 - first2;
            if (length1 != length2) return length1 < length2 ? -1 : 1;
            for (int i = 0; i < length1; i++) {
                char digit1 = version1.charAt(first1 + i);
                char digit2 = version2.charAt(first2 + i);
                if (digit1 != digit2) return digit1 < digit2 ? -1 : 1;
            }

            p1 = end1 < version1.length() ? end1 + 1 : end1;
            p2 = end2 < version2.length() ? end2 + 1 : end2;
        }
        return 0;
    }

}
