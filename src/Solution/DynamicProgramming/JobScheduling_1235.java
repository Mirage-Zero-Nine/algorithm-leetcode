package Solution.DynamicProgramming;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

/**
 * We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i] with a profit of profit[i].
 * Return the maximum profit that there are no two jobs with overlapping time range.
 * If you choose a job that ends at time X you will be able to start another job that starts at time X.
 *
 * @author BorisMirage
 * Time: 2021/10/13 10:26
 * Created with IntelliJ IDEA
 */

public class JobScheduling_1235 {
    /**
     * Similar to knapsack problems. Dynamic programming with TreeMap.
     * There are 2 cases:
     * 1. If current job is done: find the latest ending job, add profit to it.
     * 2. Don't do current job.
     * If finish current job could make more profit compare to current max profit in map, add it to the map.
     *
     * @param startTime start time array
     * @param endTime   end time array
     * @param profit    profit of each job
     * @return the maximum profit that there are no two jobs with overlapping time range
     */
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        int[][] jobs = new int[n][3];
        for (int i = 0; i < n; i++) {
            jobs[i] = new int[]{startTime[i], endTime[i], profit[i]};
        }

        Arrays.sort(jobs, Comparator.comparingInt(a -> a[1])); // sort by end time
        TreeMap<Integer, Integer> dp = new TreeMap<>(); // end time -> profit
        dp.put(0, 0);
        for (int[] job : jobs) {
            /*
             * Two cases, similar to knapsack problem:
             * 1. If current job is done: find the latest ending job, add profit to it.
             * 2. Don't do current job.
             * If finish current job could make more profit compare to current max profit in map, add it to the map. */
            int current = dp.floorEntry(job[0]).getValue() + job[2]; // get the profit if current job is done
            if (current > dp.lastEntry().getValue()) {
                dp.put(job[1], current);
            }
        }

        return dp.lastEntry().getValue();
    }
}
