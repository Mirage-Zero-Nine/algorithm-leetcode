package solution.dynamicprogramming;

/**
 * A company wants to give one of its best employees the option to travel among N cities to collect algorithm problems.
 * But all work and no play makes Jack a dull boy, you could take vacations in some particular cities and weeks.
 * Your job is to schedule the traveling to maximize the number of vacation days you could take.
 * There are certain rules and restrictions you need to follow.
 * Rules and restrictions:
 * 1. You can only travel among N cities, represented by indexes from 0 to N-1. Initially, you are in the city indexed 0 on Monday.
 * 2. The cities are connected by flights that represented as a N*N matrix (not necessary symmetrical), from the city i to j.
 * 3. If there is no flight from the city i to the city j, flights[i][j] = 0; Otherwise, flights[i][j] = 1. Also, flights[i][i] = 0 for all i.
 * 4. You totally have K weeks to travel. You can only take flights at most once per day.
 * 5. For each city, you only have restricted vacation days in different weeks, given an N*K matrix called days representing this relationship.
 * 6. For the value of days[i][j], it represents the maximum days you could take vacation in the city i in the week j.
 * Given the flights matrix and days matrix, output the maximum vacation days you could take during K weeks.
 * Note:
 * 1. N and K are positive integers, which are in the range of [1, 100].
 * 2. In the matrix flights, all the values are integers in the range of [0, 1].
 * 3. In the matrix days, all the values are integers in the range [0, 7].
 * 4. You could stay at a city beyond the number of vacation days, but you should work on the extra days, which won't be counted as vacation days.
 * 5. If you fly from the city A to the city B and take the vacation on that day, the deduction towards vacation days will count towards the vacation days of city B in that week.
 * 6. We don't consider the impact of flight hours towards the calculation of vacation days.
 *
 * @author BorisMirage
 * Time: 2019/08/08 18:45
 * Created with IntelliJ IDEA
 */

public class MaxVacationDays_568 {
    /**
     * Dynamic programming.
     * The state transition:
     * dp[i][j]: max vacation days one can have from week 1 to week j, and you are in city i in week j.
     * dp[i][j] = max(dp[i][j], dp[i'][j - 1] + days[i][j]), where 0 <= i' < n (previous city -> current city)
     * Initially state: if city 0 can fly to city i, then this city at week 0 has days[i][0] vacations.
     *
     * @param flights flights from the city i to j
     * @param days    city i has vacation days in weeks j
     * @return output the maximum vacation days you could take during K weeks
     */
    public int maxVacationDays(int[][] flights, int[][] days) {
        if (flights.length == 0 || days.length == 0) {
            return 0;
        }

        int n = days.length, k = days[0].length;
        int[][] dp = new int[n][k];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k; j++) {
                dp[i][j] = -1;
            }
            if (i != 0 && flights[0][i] == 1) {
                dp[i][0] = days[i][0];      // init state, if first week can directly fly to this city
            }
        }

        dp[0][0] = days[0][0];

        int max = 0;

        for (int week = 1; week < k; week++) {       // each week

            /*
             * Under each week and search each city pair: depart from city i' to destination city i.
             * If there is a flight from city i' to city i, then count the max days under current pair.
             * If city i is reachable from i', then dp[i'][j-1] should not be -1. */
            for (int depart = 0; depart < n; depart++) {        // start from each departure city
                for (int destination = 0; destination < n; destination++) {     // end at each destination city
                    if (dp[depart][week - 1] != -1 && ((depart == destination) || (flights[depart][destination] == 1))) {
                        dp[destination][week] = Math.max(dp[destination][week], dp[depart][week - 1] + days[destination][week]);
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            max = Math.max(dp[i][k - 1], max);      // find max days from last week's city
        }

        return max;
    }

    public static void main(String[] args) {
        System.out.println(new MaxVacationDays_568().maxVacationDays(new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}, new int[][]{{1, 1, 1}, {7, 7, 7}, {7, 7, 7}}));
    }
}
