package Solution.Array;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given an array of distinct integers.
 * Return a list of pairs in ascending order(with respect to pairs), each pair [a, b] follows
 * 1. a, b are from arr
 * 2. a < b
 * 3. b - a equals to the minimum absolute difference of any two elements in arr
 *
 * @author BorisMirage
 * Time: 2019/09/22 10:25
 * Created with IntelliJ IDEA
 */

public class MinimumAbsDifference_1200 {
    /**
     * Sort array first, then traverse from left to right, if find a smaller difference, reclaim an array.
     * Otherwise, add result to array and return it.
     *
     * @param arr given array
     * @return all pairs of elements with the minimum absolute difference of any two elements
     */
    public List<List<Integer>> minimumAbsDifference(int[] arr) {

        /* Corner case */
        if (arr == null || arr.length == 0) {
            return new LinkedList<>();
        }

        Arrays.sort(arr);
        List<List<Integer>> out = new LinkedList<>();
        int min = Integer.MAX_VALUE;

        for (int i = 1; i < arr.length; i++) {
            int diff = Math.abs(arr[i] - arr[i - 1]);
            if (diff <= min) {
                List<Integer> pair = Arrays.asList(arr[i-1], arr[i]);
                if (diff != min) {
                    out = new LinkedList<>();
                }
                out.add(pair);
                min = diff;
            }
        }

        return out;
    }
}
