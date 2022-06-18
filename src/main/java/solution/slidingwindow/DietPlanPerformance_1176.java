package solution.slidingwindow;

/**
 * A dieter consumes calories[i] calories on the i-th day.
 * For every consecutive sequence of k days, they look at T, the total calories consumed during that sequence of k days:
 * If T < lower, they performed poorly on their diet and lose 1 point;
 * If T > upper, they performed well on their diet and gain 1 point;
 * Otherwise, they performed normally and there is no change in points.
 * Return the total number of points the dieter has after all calories.length days.
 * Note that: The total points could be negative.
 *
 * @author BorisMirage
 * Time: 2019/09/01 15:21
 * Created with IntelliJ IDEA
 */

public class DietPlanPerformance_1176 {
    /**
     * Basic sliding window.
     * Each time, move window 1 step forward.
     *
     * @param calories given calories
     * @param k        window size
     * @param lower    lower bound
     * @param upper    upper bound
     * @return total number of points the dieter has after all days
     */
    public int dietPlanPerformance(int[] calories, int k, int lower, int upper) {
        int score = 0, sum = 0;

        for (int i = 0; i < k; i++) {
            sum += calories[i];
        }

        score = (sum < lower) ? score - 1 : (sum > upper) ? score + 1 : score;

        for (int i = 1; i + k - 1 < calories.length; i++) {
            sum = sum - calories[i - 1] + calories[i + k - 1];
            score = (sum < lower) ? score - 1 : (sum > upper) ? score + 1 : score;
        }

        return score;
    }
}
