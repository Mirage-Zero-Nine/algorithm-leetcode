package Solution.Others;

/**
 * Implement StrStr().
 * Return the index of the FIRST occurrence of needle in haystack, or -1 if needle is not part of haystack.
 * Clarification:
 * When needle is an empty string, return 0.
 * This is consistent to C's strstr() and Java's indexOf().
 *
 * @author BorisMirage
 * Time: 2018/06/13 22:41
 * Created with IntelliJ IDEA
 */

public class StrStr_28 {

    /**
     * @param haystack input string
     * @param needle   input string to be found in haystack
     * @return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack
     */
    public int strStr(String haystack, String needle) {

        /* Corner case */
        if (needle.length() == 0) {
            return 0;
        }
        if (haystack.length() == 0 || needle.length() > haystack.length()) {
            return -1;
        }

        for (int i = 0; i < haystack.length() - needle.length() + 1; i++) {
            String part = haystack.substring(i, i + needle.length());
            if (part.equals(needle)) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {

        /* StrStr_28 Test */
        StrStr_28 strStrTest = new StrStr_28();
        System.out.println(strStrTest.strStr("mississippi", "issip"));
    }
}
