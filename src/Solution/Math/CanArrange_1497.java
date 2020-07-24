package Solution.Math;

import java.util.HashMap;

/**
 * Given an array of integers arr of even length n and an integer k.
 * We want to divide the array into exactly n / 2 pairs such that the sum of each pair is divisible by k.
 * Return True If you can find a way to do that or False otherwise.
 *
 * @author BorisMirage
 * Time: 2020/06/28 10:54
 * Created with IntelliJ IDEA
 */

public class CanArrange_1497 {
    /**
     * Each element divided by k will have two results: remainder is 0 or not 0.
     * Hence, keep a hash map.
     * The key of hash map is the remainder of n % k, where n is the current element in the array.
     * The value of hash map is the frequency of current remainder.
     * If the remainder is not 0, then it requires a complement to make the pair divisible. remainder + complement == k.
     * Therefore, calculate the complement of element based on the remainder and then check if complement exist in map.
     * Put remainder into hash map if the complement is not found.
     * Finally, check the totally found pair equals n / 2 (half of array length).
     *
     * @param arr given array
     * @param k   divisible by k
     * @return true if divide the array into exactly n / 2 pairs such that the sum of each pair is divisible by k
     */
    public boolean canArrange(int[] arr, int k) {
        HashMap<Integer, Integer> m = new HashMap<>();
        int count = 0;
        for (int n : arr) {
            int remainder = (n % k + k) % k;            // n could be negative, hence add k to keep remainder positive
            int complement = (k - remainder) % k;       // complement could be k itself, which should be converted to 0

            if (m.containsKey(complement) && m.get(complement) > 0) {
                count++;
                m.put(complement, m.get(complement) - 1);
            } else {
                m.put(remainder, m.getOrDefault(remainder, 0) + 1);
            }
        }

        return count == arr.length / 2;
    }
}
