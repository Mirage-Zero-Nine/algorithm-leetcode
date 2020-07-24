package Solution.Others;

/**
 * Given a year Y and a month M, return how many days there are in that month.
 *
 * @author BorisMirage
 * Time: 2020/05/01 13:29
 * Created with IntelliJ IDEA
 */

public class NumberOfDays_1118 {
    /**
     * Only case requires attention is the February in leap year.
     *
     * @param Y given year
     * @param M given month
     * @return how many days there are in that month
     */
    public int numberOfDays(int Y, int M) {
        int[] days = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        if (((Y % 4 == 0 && Y % 100 != 0) || Y % 400 == 0) && M == 2) {
            return 29;
        }

        return days[M - 1];
    }
}
