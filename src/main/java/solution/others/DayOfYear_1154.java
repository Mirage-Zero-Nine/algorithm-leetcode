package solution.others;

import java.time.LocalDate;

/**
 * Given a string date representing a Gregorian calendar date formatted as YYYY-MM-DD, return the day number of the year.
 *
 * @author BorisMirage
 * Time: 2019/08/10 19:31
 * Created with IntelliJ IDEA
 */

public class DayOfYear_1154 {
    /**
     * Aware February.
     *
     * @param date given date in string format
     * @return the day number of the year
     */
    public int dayOfYear(String date) {
        String[] d = date.split("-");

        int[] map = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int year = Integer.parseInt(d[0]);
        int month = Integer.parseInt(d[1]);
        int day = Integer.parseInt(d[2]);

        boolean isDouble;
        isDouble = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0 && year % 3200 != 0);

        int out = 0;
        for (int i = 1; i < month; i++) {
            if (i == 2 && isDouble) {
                out += 29;
            } else {
                out += map[i - 1];
            }
        }

        out += day;
        return out;
    }

    /**
     * Use Java API.
     * @param date given date in string format
     * @return the day number of the year
     */
    public int api(String date) {
        return LocalDate.parse(date).getDayOfYear();
    }

    public static void main(String[] args) {
        System.out.println(new DayOfYear_1154().dayOfYear("2004-03-01"));
    }
}
