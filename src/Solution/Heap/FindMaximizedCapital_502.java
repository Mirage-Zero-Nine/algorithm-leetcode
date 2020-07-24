package Solution.Heap;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Suppose LeetCode will start its IPO soon.
 * In order to sell a good price of its shares to Venture Capital, LeetCode work on projects to increase its capital.
 * Since it has limited resources, it can only finish at most k distinct projects before the IPO.
 * Help LeetCode design the best way to maximize its total capital after finishing at most k distinct projects.
 * You are given several projects.
 * For each project i, it has a pure profit Pi and a minimum capital of Ci is needed to start the corresponding project.
 * Initially, you have W capital.
 * When you finish a project, you will obtain its pure profit and the profit will be added to your total capital.
 * To sum up, pick a list of at most k distinct projects from given projects to maximize your final capital.
 * Output your final maximized capital.
 *
 * @author BorisMirage
 * Time: 2020/03/14 17:28
 * Created with IntelliJ IDEA
 */

public class FindMaximizedCapital_502 {
    /**
     * Greedy solution with two heaps.
     * Each time, find a project with max profit within current capital capability.
     *
     * @param k       at most k operations
     * @param W       initially capital
     * @param Profits list of profits that each project will bring
     * @param Capital list of capital that each project will take
     * @return final maximized capital
     */
    public int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
        Queue<int[]> minCapital = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        Queue<int[]> maxProfit = new PriorityQueue<>((a, b) -> (b[1] - a[1]));

        for (int i = 0; i < Profits.length; i++) {
            minCapital.add(new int[]{Capital[i], Profits[i]});
        }

        for (int i = 0; i < k; i++) {
            while (!minCapital.isEmpty() && minCapital.peek()[0] <= W) {      // find all projects within current capability
                maxProfit.offer(minCapital.poll());
            }

            if (maxProfit.isEmpty()) {     // if no project under this condition
                break;
            }

            W += maxProfit.poll()[1];      // find a project with max profit
        }

        return W;
    }
}
