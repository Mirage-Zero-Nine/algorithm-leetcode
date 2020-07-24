package Solution.Others;

import java.time.Month;
import java.time.Year;

/**
 * Given a date, return the corresponding day of the week for that date.
 * The input is given as three integers representing the day, month and year respectively.
 * Return the answer as one of the following values {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}.
 *
 * @author BorisMirage
 * Time: 2019/09/07 19:36
 * Created with IntelliJ IDEA
 */

public class DayOfTheWeek_1185 {
    /**
     * Zeller Formula.
     *
     * @param day   given day in year
     * @param month given month in year
     * @param year  given year
     * @return corresponding day of the week for that date
     */
    public String dayOfTheWeek(int day, int month, int year) {
        String[] days = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        if (month < 3) {
            month += 12;
            year -= 1;
        }
        int c = year / 100;
        year = year % 100;
        int w = (c / 4 - 2 * c + year + year / 4 + 13 * (month + 1) / 5 + day - 1) % 7;
        return days[(w + 7) % 7];
    }

    /**
     * Use Java API.
     *
     * @param day   given day in year
     * @param month given month in year
     * @param year  given year
     * @return corresponding day of the week for that date
     */
    public String api(int day, int month, int year) {
        String[] weeks = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        int week = Year.of(year).atMonth(Month.of(month)).atDay(day).getDayOfWeek().getValue() - 1;
        return weeks[week];
    }

    public static void main(String[] args) {
        System.out.println(new DayOfTheWeek_1185().dayOfTheWeek(31, 8, 2019));
    }
}
