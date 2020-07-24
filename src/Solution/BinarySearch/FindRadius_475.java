package Solution.BinarySearch;

import java.util.Arrays;

/**
 * Winter is coming!
 * Your first job during the contest is to design a standard heater with fixed warm radius to warm all the houses.
 * You are given positions of houses and heaters on a horizontal line.
 * Find out minimum radius of heaters so that all houses could be covered by those heaters.
 * Note:
 * 1. Numbers of houses and heaters you are given are non-negative and will not exceed 25000.
 * 2. Positions of houses and heaters you are given are non-negative and will not exceed 10^9.
 * 3. As long as a house is in the heaters' warm radius range, it can be warmed.
 * 4. All the heaters follow your radius standard and the warm radius will the same.
 *
 * @author BorisMirage
 * Time: 2019/06/23 16:46
 * Created with IntelliJ IDEA
 */

public class FindRadius_475 {
    /**
     * The idea is to leverage decent Arrays.binarySearch() function provided by Java.
     * For each house, find its position between those heaters (thus we need the heaters array to be sorted).
     * Calculate the distances between this house and left heater and right heater, get a MIN value of those two values.
     * Corner cases are there is no left or right heater.
     * Get MAX value among distances in step 2. It's the answer.
     * Time complexity: max(O(nlogn), O(mlogn)) - m is the length of houses, n is the length of heaters.
     *
     * @param houses  given array
     * @param heaters given heater array
     * @return minimum radius of heaters so that all houses could be covered by those heaters
     */
    public int findRadius(int[] houses, int[] heaters) {

        Arrays.sort(heaters);
        int radius = Integer.MIN_VALUE;

        for (int house : houses) {

            /*
             * Arrays.binarySearch() returns index of the search key, if it is contained in the array;
             * otherwise, (-(insertion point) - 1).
             * The insertion point is defined as the point at which the key would be inserted into the array:
             * the index of the first element greater than the key,
             * or a.length if all elements in the array are less than the specified key.
             * Note that this guarantees that the return value will be >= 0 if and only if the key is found. */
            int index = Arrays.binarySearch(heaters, house);

            if (index < 0) {
                index = -(index + 1);       // if insert position is not in array
            }

            int dist1 = index - 1 >= 0 ? house - heaters[index - 1] : Integer.MAX_VALUE;
            int dist2 = index < heaters.length ? heaters[index] - house : Integer.MAX_VALUE;

            radius = Math.max(radius, Math.min(dist1, dist2));
        }
        return radius;
    }

    public static void main(String[] args) {
        FindRadius_475 test = new FindRadius_475();
        System.out.println(test.findRadius(new int[]{1, 2, 3}, new int[]{2}));
        System.out.println(test.findRadius(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, new int[]{1, 9}));

    }
}
