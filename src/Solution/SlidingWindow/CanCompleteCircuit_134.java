package Solution.SlidingWindow;

/**
 * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
 * You have a car with an unlimited gas tank.
 * It costs cost[i] of gas to travel from station i to its next station (i+1).
 * Begin the journey with an empty tank at one of the gas stations.
 * Return the starting station's index if it can travel around the circuit once in the clockwise direction.
 * Otherwise return -1.
 * Note:
 * 1. If there exists a solution, it is guaranteed to be unique.
 * 2. Both input arrays are non-empty and have the same length.
 * 3. Each element in the input arrays is a non-negative integer.
 *
 * @author BorisMirage
 * Time: 2019/06/18 15:13
 * Created with IntelliJ IDEA
 */

public class CanCompleteCircuit_134 {
    /**
     * If total gas can cover all cost of gas, then there will be an available start location.
     * Assume the start position is 0, then calculate the cost in each station.
     * If it can not cover the cost from previous station to current station, then set the start location to the next.
     * Meanwhile, add the cost from start location to current location. This will be checked after traverse complete.
     * If the remaining gas can cover the cost, the remaining gas should be more than the need before start location.
     * Finally, if total gas can not cover total cost, then return -1. Otherwise, return start location.
     *
     * @param gas  amount of gas
     * @param cost cost of gas
     * @return starting station's index if it can travel around the circuit once in the clockwise direction
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {

        /* Corner case */
        if (gas == null || cost == null || gas.length == 0 || cost.length == 0) {
            return -1;
        }

        int totalCost = 0, currentSum = 0, start = 0; // assume start position is 0

        for (int i = 0; i < gas.length; i++) {
            int currentCost = gas[i] - cost[i]; // cost from previous station to current station
            currentSum += currentCost;
            if (currentSum < 0) { // if this station is not reachable, set start location to next position
                totalCost += currentSum; // add cost from start to this position
                start = i + 1;
                currentSum = 0;
            }
        }

        totalCost += currentSum;

        return totalCost < 0 ? -1 : start;
    }
}
