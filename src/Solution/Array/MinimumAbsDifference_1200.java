package Solution.Array;

import java.util.ArrayList;
import java.util.Arrays;
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
        Arrays.sort(arr);
        int min = Integer.MAX_VALUE, n = arr.length;

        List<List<Integer>> out = new ArrayList<>();

        for (int i = 1; i < n; i++) {
            if (Math.abs(arr[i - 1] - arr[i]) <= min) {
                if (Math.abs(arr[i - 1] - arr[i]) < min) {
                    out = new ArrayList<>();
                    min = Math.abs(arr[i - 1] - arr[i]);
                }
                List<Integer> tmp = new ArrayList<>();
                tmp.add(arr[i - 1]);
                tmp.add(arr[i]);
                out.add(tmp);
            }
        }

        return out;
    }
}
