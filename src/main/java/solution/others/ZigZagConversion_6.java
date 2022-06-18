package solution.others;

/**
 * Convert input string into zigzag format
 *
 * @author BorisMirage
 * Time: 2018/05/12 20:40
 * Created with IntelliJ IDEA
 */

public class ZigZagConversion_6 {
    /**
     * Base on zig zag division, input string can be divided into blocks based on index.
     * Blocks that contains chars that placed in same column and place in same diagonal.
     * For instance, string = "abcdefghijk", numRows = 4, then string can be divided into abcd|ef|ghij|k
     * These blocks' indexes reflect to represented row, which is the output basis.
     *
     * @param s       input string
     * @param numRows number of rows that divide sting into
     * @return string in zigzag format
     */
    public String convert(String s, int numRows) {
        if (s.length() <= 1 || numRows <= 1) {
            return s;
        }
        StringBuilder zigzag = new StringBuilder();

        /* Build output string in the order of row */
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j + i < s.length(); j = j + 2 * numRows - 2) {
                zigzag.append(s.charAt(j + i));

                /* First row and last row does not contain chars from diagonal */
                if (i == 0 || i == numRows - 1) {
                    continue;
                }

                /* Rest rows contain chars from diagonal, hence needs to be added */
                if (j + 2 * numRows - 2 - i < s.length()) {
                    zigzag.append(s.charAt(j + 2 * numRows - 2 - i));
                }
            }
        }

        return zigzag.toString();
    }

    public static void main(String[] args) {

        /* Zig Zag test */
        ZigZagConversion_6 zigZagConversionTest = new ZigZagConversion_6();
        String testS = "abcdefghijk";
        int numRow = 4;
        System.out.println(zigZagConversionTest.convert(testS, numRow));
    }
}