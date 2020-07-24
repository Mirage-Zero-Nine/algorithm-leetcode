package Playground;

/**
 * @author BorisMirage
 * Time: 2018/05/26 19:15
 * Created with IntelliJ IDEA
 */

public class LongestCommonSubstring {
    private String str1;
    private String str2;

    /**
     * Find longest common substring between two strings.
     * This can be used in finding palindromic substring
     *
     * @param strA first input string
     * @param strB second input string
     */
    public String longestCommonSubstring(String strA, String strB) {

        /* Corner case */
        if (str1.length() == 0 || strB.length() == 0) {
            return strA.length() == 0 ? strA : strB;
        }

        str1 = strA;
        str2 = strB;
        return setTable();
    }

    /**
     * Fill the table that contains the length of common substring.
     * Note that the string length is longer than 0.
     *
     * @return longest common substring
     */
    private String setTable() {
        String result = "";

        /*
         * str1: 2D array's column; str2: 2D array's row.
         * First column and row filled with 0 for avoiding out of index error. */
        int[][] table = new int[str1.length() + 1][str2.length() + 1];

        for (int row = 1; row < str2.length() + 1; row++) {
            for (int column = 1; column < str1.length() + 1; column++) {
                if (str2.charAt(row) == str1.charAt(column)) {
                    table[column][row] = table[column - 1][row - 1] + 1;
                } else {
                    table[column][row] = Math.max(table[column - 1][row], table[column][row - 1]);

                }
            }
        }

//        /* Trace back */
        return result;
    }


}
