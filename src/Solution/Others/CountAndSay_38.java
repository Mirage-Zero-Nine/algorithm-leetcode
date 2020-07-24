package Solution.Others;

/**
 * The first line of the initial value is 1.
 * The second line reads the first line, one is 1, which is 11.
 * The third line reads the second line, 2, so the third line is 21.
 * The fourth line reads the third line, one 2, one 1, so the fourth line is 1211.
 * The fifth line reads the fourth line, one 1, one 2, two 1, so the fifth flight is 111221.
 * The sixth line reads the fifth line, three 1, two 2, one 1, so the sixth line is 312211.
 * Then the question asks for any number of lines from 1 - 30 to output the line.
 *
 * @author BorisMirage
 * Time: 2018/06/22 15:18
 * Created with IntelliJ IDEA
 */

public class CountAndSay_38 {
    /**
     * nth sequence is based on (n - 1)th sequence.
     *
     * @param n nth term
     * @return nth term of the count-and-say sequence
     */
    public String countAndSay(int n) {

        /* Corner case */
        if (n < 3) {
            return (n == 2) ? "11" : "1";
        }

        String out = "11";

        for (int i = 2; i < n; i++) {

            int count = 1;
            StringBuilder round = new StringBuilder();

            for (int j = 1; j < out.length(); j++) {        // next sequence is based on current sequence
                if (out.charAt(j - 1) == out.charAt(j)) {
                    count++;
                } else {
                    round.append(count).append(out.charAt(j - 1));
                    count = 1;
                }
            }
            round.append(count).append(out.charAt(out.length() - 1));
            out = round.toString();
        }

        return out;
    }

    public static void main(String[] args) {

        CountAndSay_38 countAndSayTest = new CountAndSay_38();
        System.out.println(countAndSayTest.countAndSay(6));
    }
}
