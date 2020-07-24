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
     * If total gas can cover all cost of gas, then there will be a available start location.
     * Traverse array, if current tank can not cover the cost at current station, set start location at next location.
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

        int size = gas.length, sum = 0, count = 0, total = 0;

        for (int i = 0; i < size; ++i) {
            sum += gas[i] - cost[i];
            if (sum < 0) {
                total += sum;
                sum = 0;
                count = i + 1;
            }
        }

        total += sum;

        return total < 0 ? -1 : count;
    }
}
