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
     * Simply traverse the haystack and find if haystack.substring(i, i + needle.length()) equals to needle.
     * Corner cases:
     * 1. needle > haystack, return -1.
     * 2. haystack.length() == 0, return -1;
     * 3. needle.length() == 0, return 0;
     *
     * @param haystack input string
     * @param needle   input string to be found in haystack
     * @return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack
     */
    public int strStr(String haystack, String needle) {

        if (needle.length() == 0) {
            return 0;
        }

        if (haystack.length() == 0) {
            return -1;
        }
        int length = haystack.length() - needle.length();
        for (int i = 0; i <= length; i++) {
            for (int j = 0; j < needle.length(); j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    break;
                }
                if (j == needle.length() - 1) {
                    return i;
                }
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
