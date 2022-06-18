package solution.dynamicprogramming;

import java.util.LinkedList;
import java.util.Queue;

/**
 * In a country popular for train travel, you have planned some train travelling one year in advance.
 * The days of the year that you will travel is given as an array days.
 * Each day is an integer from 1 to 365.
 * Train tickets are sold in 3 different ways:
 * 1. a 1-day pass is sold for costs[0] dollars;
 * 2. a 7-day pass is sold for costs[1] dollars;
 * 3. a 30-day pass is sold for costs[2] dollars.
 * The passes allow that many days of consecutive travel.
 * For example, if we get a 7-day pass on day 2, then we can travel for 7 days: day 2, 3, 4, 5, 6, 7, and 8.
 * Return the minimum number of dollars you need to travel every day in the given list of days.
 * Note:
 * 1. 1 <= days.length <= 365
 * 2. 1 <= days[i] <= 365
 * 3. days is in strictly increasing order.
 * 4. costs.length == 3
 * 5. 1 <= costs[i] <= 1000
 *
 * @author BorisMirage
 * Time: 2019/07/17 16:56
 * Created with IntelliJ IDEA
 */

public class MinCostTickets_983 {
    /**
     * Dynamic programming with a queue to track days of travel.
     * One queue save the costs within 7 weeks (one 7 days pass can cover).
     * The other queue save the cost within 1 month (one 30 days pass can cover).
     * If current date compare to head of each queue is larger than a week or 30 days, remove head of queue.
     * Then find min value between buy a one day pass, buy a seven days pass, or 30 days pass ahead.
     *
     * @param days  given days array
     * @param costs given cost array
     * @return minimum number of dollars for travel
     */
    public int minCostTickets(int[] days, int[] costs) {

        /* Corner case (days >= 1) */
        if (days.length < 2) {
            return costs[0];
        }

        Queue<int[]> last7 = new LinkedList<>(), last30 = new LinkedList<>();

        int total = 0;

        for (int date : days) {

            while (!last7.isEmpty() && last7.peek()[0] + 7 <= date) {        // if head of queue is longer than one week
                last7.poll();       // remove dates more than one week
            }
            while (!last30.isEmpty() && last30.peek()[0] + 30 <= date) {
                last30.poll();      // remove dates more than one month
            }

            /* After the poll process, the head of queue is the furthest cost with a 7 days pass or 30 days pass.
             * Continue add today's 7 day pass and 30 days pass to queue.
             * In this way, only to peek the head of queue can obtain the cost with a 7 days pass or a 30 days pass.
             * The reason is that any dates longer than 7 days or 30 days has been removed. */
            last7.add(new int[]{date, total + costs[1]});       // add date with seven days pass to the end of queue
            last30.add(new int[]{date, total + costs[2]});      // add date with monthly pass to the end of queue

            if (!last7.isEmpty() && !last30.isEmpty()) {
                total = Math.min(Math.min(last7.peek()[1], last30.peek()[1]), total + costs[0]);
            }
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println(new MinCostTickets_983().minCostTickets(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 30, 31}, new int[]{2, 7, 15}));
    }
}
