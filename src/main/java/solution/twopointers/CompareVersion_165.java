package solution.twopointers;

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

        int p1 = 0, p2 = 0, l1 = version1.length(), l2 = version2.length(), current1, current2;

        while (p1 < l1 || p2 < l2) {
            current1 = 0;
            current2 = 0;

            while (p1 < l1 && Character.isDigit(version1.charAt(p1))) {
                current1 = current1 * 10 + version1.charAt(p1++) - 48;
            }
            p1++;
            while (p2 < l2 && Character.isDigit(version2.charAt(p2))) {
                current2 = current2 * 10 + version2.charAt(p2++) - 48;
            }
            p2++;
            if (current1 < current2) {
                return -1;
            } else if (current1 > current2) {
                return 1;
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        System.out.println(new CompareVersion_165().compareVersion("0.1", "1.1"));      // -1
        System.out.println(new CompareVersion_165().compareVersion("1.0.1", "1"));      // 1
        System.out.println(new CompareVersion_165().compareVersion("7.5.2.4", "7.5.3"));      // -1
        System.out.println(new CompareVersion_165().compareVersion("1.01", "1.001"));      // 0
        System.out.println(new CompareVersion_165().compareVersion("1.0", "1.0.0"));      // 0
    }
}
