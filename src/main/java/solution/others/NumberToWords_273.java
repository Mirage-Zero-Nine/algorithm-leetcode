package solution.others;

/**
 * Convert a non-negative integer to its english words representation.
 * Given input is guaranteed to be less than 2^31 - 1.
 *
 * @author BorisMirage
 * Time: 2019/11/05 11:58
 * Created with IntelliJ IDEA
 */
public class NumberToWords_273 {
    private final String[] belowTen = new String[]{"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};     // "" actually will not be used
    private final String[] belowTwenty = new String[]{"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private final String[] belowHundred = new String[]{"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    /**
     * Consider all corner case.
     * The unit of division is 1000. Then divide by 100 if larger than 100, divide by 10 if larger than 10.
     *
     * @param num given number
     * @return English word represents this number
     */
    public String numberToWords(int num) {

        /* Corner case */
        if (num == 0) {
            return "Zero";
        }

        return divide(num);
    }

    /**
     * Find the largest unit and recursively handle with the remainder.
     *
     * @param num given number
     * @return converted string
     */
    private String divide(int num) {
        String result;

        if (num < 10) {                 // 1 - 9
            result = belowTen[num];
        } else if (num < 20) {          // 10 - 19
            result = belowTwenty[num - 10];
        } else if (num < 100) {         // 20 - 99
            result = belowHundred[num / 10] + " " + divide(num % 10);
        } else if (num < 1000) {        // 100 - 999
            result = divide(num / 100) + " Hundred " + divide(num % 100);
        } else if (num < 1000000) {     // 1000 - 999999
            result = divide(num / 1000) + " Thousand " + divide(num % 1000);
        } else if (num < 1000000000) {  // 1000000 - 999999999
            result = divide(num / 1000000) + " Million " + divide(num % 1000000);
        } else {                        // 1000000000~
            result = divide(num / 1000000000) + " Billion " + divide(num % 1000000000);
        }

        return result.trim();
    }
}
