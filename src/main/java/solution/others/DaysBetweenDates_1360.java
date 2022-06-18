package solution.others;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Write a program to count the number of days between two dates.
 * The two dates are given as strings, their format is YYYY-MM-DD as shown in the examples.
 *
 * @author BorisMirage
 * Time: 2020/03/18 10:02
 * Created with IntelliJ IDEA
 */

public class DaysBetweenDates_1360 {
    /**
     * Count days from 1971 to given dates, and find the absolute difference between them.
     *
     * @param date1 first given date
     * @param date2 second given date
     * @return count the number of days between two dates
     */
    public int daysBetweenDates(String date1, String date2) {
        String[] arr1 = date1.split("-"), arr2 = date2.split("-");
        int y1 = Integer.parseInt(arr1[0]), m1 = Integer.parseInt(arr1[1]), d1 = Integer.parseInt(arr1[2]);
        int y2 = Integer.parseInt(arr2[0]), m2 = Integer.parseInt(arr2[1]), d2 = Integer.parseInt(arr2[2]);
        int[] n1 = new int[]{y1, m1, d1}, n2 = new int[]{y2, m2, d2};

        return Math.abs(foundDays(n1) - foundDays(n2));
    }

    /**
     * Count days from 1971 (or 0 if the start year is 0) to current days.
     *
     * @param d given date array in integer
     * @return days from 1971 to current days
     */
    private int foundDays(int[] d) {
        int[] daysOfMonth = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int days = d[2];        // initially, date in month is fixed

        for (int i = 1971; i < d[0]; i++) {     // count days in years
            days += (isLeap(i) ? 366 : 365);
        }

        for (int i = 0; i < d[1] - 1; i++) {        // count days in previous month
            days += daysOfMonth[i];
        }

        if (d[1] > 2 && isLeap(d[0])) {     // leap year's February has 29 days
            days++;
        }

        return days;
    }

    /**
     * Check if current year is leap year.
     *
     * @param year given year
     * @return if current year is leap year.
     */
    private boolean isLeap(int year) {
        return year % 400 == 0 || (year % 100 != 0 && year % 4 == 0);
    }

    /**
     * Use Java API.
     *
     * @param date1 first given date
     * @param date2 second given date
     * @return count the number of days between two dates
     */
    public int daysBetweenDatesAPI(String date1, String date2) {
        return Math.abs((int) (ChronoUnit.DAYS.between(LocalDate.parse(date2), LocalDate.parse(date1))));
    }
}
