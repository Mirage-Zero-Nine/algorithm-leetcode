package Solution.Array;

/**
 * You are the operator of a Centennial Wheel that has four gondolas, and each gondola has room for up to four people.
 * You have the ability to rotate the gondolas counterclockwise, which costs you runningCost dollars.
 * Given an array customers of length n, customers[i] is the number of new customers arriving before the ith rotation.
 * This means you must rotate the wheel i times before customers[i] arrive.
 * Each customer pays boardingCost when they board on the gondola and will exit once it reaches the ground again.
 * You can stop the wheel at any time, including before serving all customers.
 * If you decide to stop serving customers, all subsequent rotations are free in order to get all the customers down.
 * Note that if there are more than 4 customers waiting, only 4 will board the gondola.
 * Return the minimum number of rotations you need to perform to maximize your profit.
 * If there is no scenario where the profit is positive, return -1.
 *
 * @author BorisMirage
 * Time: 2020/09/26 21:15
 * Created with IntelliJ IDEA
 */

public class MinOperationsMaxProfit_1599 {
    /**
     * Simple simulation.
     * Some corner cases:
     * 1. If in each rotation, 4 * boardingCost * 4 - runningCost <= 0, then no profit.
     * 2. Before all the customers coming, the wheel will rotate, no matter there is new customer or not.
     * 3. Finally, the last rotate may reduce profit if the onboard people is less than 4.
     *
     * @param customers    customers arrives at ith rotation
     * @param boardingCost profit made for each one onboard
     * @param runningCost  cost of each rotation
     * @return the minimum number of rotations you need to perform to maximize profit
     */
    public int minOperationsMaxProfit(int[] customers, int boardingCost, int runningCost) {
        int rotate = 0, waiting = 0, profit = 0, boarding;

        /* Corner case */
        if (boardingCost * 4 - runningCost < 0) {
            return -1;
        }

        for (int customer : customers) {
            waiting += customer;
            boarding = Math.min(waiting, 4);
            profit += boardingCost * boarding - runningCost;
            waiting -= boarding;
            rotate++;       // the wheel will rotate even though there is no customer
        }

        rotate += (waiting / 4);
        int last = waiting % 4;
        profit += (boardingCost * (waiting - last) - runningCost * waiting / 4);

        if (last * boardingCost - runningCost > 0) {
            rotate++;
            profit += (last * boardingCost - runningCost);
        }

        return profit > 0 ? rotate : -1;
    }
}
