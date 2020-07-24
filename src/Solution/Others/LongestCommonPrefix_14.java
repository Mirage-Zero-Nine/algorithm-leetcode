package Solution.Others;

/**
 * Write a function to find the longest common prefix string amongst an array of strings.
 * If there is no common prefix, return an empty string "".
 *
 * @author BorisMirage
 * Time: 2018/06/10 15:34
 * Created with IntelliJ IDEA
 */

public class LongestCommonPrefix_14 {
    /**
     * Directly loop n words for k times, where k is the index of first word.
     * Note that the longest common prefix is actually based on shortest word in array.
     *
     * @param strs input string array
     * @return the longest common prefix string amongst an array of strings
     */
    public String longestCommonPrefix(String[] strs) {

        /* Corner case */
        if (strs == null || strs.length == 0 || strs[0].length() == 0) {
            return "";
        }

        int n = strs.length;            // total words
        int m = strs[0].length();       // length of first word, assume this is the shortest word in array
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m; i++) {           // index in each element
            for (int j = 1; j < n; j++) {       // loop n words
                if (i >= strs[j].length() || strs[j].charAt(i) != strs[j - 1].charAt(i)) {
                    return sb.toString();
                }
            }
            sb.append(strs[0].charAt(i));
        }

        return sb.toString();
    }
}
