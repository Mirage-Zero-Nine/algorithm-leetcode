package solution.binarysearch;

/**
 * A conveyor belt has packages that must be shipped from one port to another within D days.
 * The i-th package on the conveyor belt has a weight of weights[i].
 * Each day, we load the ship with packages on the conveyor belt (in the order given by weights).
 * We may not load more weight than the maximum weight capacity of the ship.
 * Return the least weight capacity of the ship that will result in all the packages being shipped within D days.
 *
 * @author BorisMirage
 * Time: 2019/08/12 14:46
 * Created with IntelliJ IDEA
 */

public class ShipWithinDays_1011 {
    /**
     * Binary search on ship capacity.
     * Initially state is from max value in weights to sum of total weights.
     * And the average of these two value is the initially capacity of ship.
     * Each time, check if work can be finished under current capacity.
     * If takes more days, increase ship capacity. Otherwise, decrease ship capacity.
     *
     * @param weights array with package weights
     * @param days    total days
     * @return least weight capacity of the ship that will result in all the packages being shipped within D days
     */
    public int shipWithinDays(int[] weights, int days) {
        int sum = 0, max = 0;
        for (Integer w : weights) {
            sum += w;
            max = Math.max(w, max);
        }

        int start = max, end = sum;
        int mid;
        while (start < end) {

            mid = start + (end - start) / 2;
            int count = binarySearch(weights, mid);

            if (count <= days) {        // if finishing job within limit, decrease capacity to find min
                end = mid;
            }
            if (count > days) {         // if finishing job longer than limit, increase capacity
                start = mid + 1;
            }
        }

        return start;
    }

    /**
     * Binary search.
     *
     * @param arr given array
     * @param max ship capacity
     * @return days to transport all packages under current capacity
     */
    private int binarySearch(int[] arr, int max) {
        int temp = 0, count = 1;

        for (int i : arr) {
            temp += i;
            if (temp > max) {       // if current sum is larger than capacity
                temp = i;           // reset ship
                count++;
            }
        }
        return count;
    }
}
