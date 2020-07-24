package Solution.OrderedMap;

import java.util.HashMap;
import java.util.TreeSet;

/**
 * Given an array of integers.
 * Find out whether there are two distinct indices i and j in the array:
 * 1. Absolute difference between i and j is at most k.
 * 2. Absolute difference between nums[i] and nums[j] is at most t.
 *
 * @author BorisMirage
 * Time: 2019/06/13 14:21
 * Created with IntelliJ IDEA
 */

public class ContainsNearbyAlmostDuplicate_220 {
    /**
     * Bucket sort.
     * Keep a hash map with size of k.
     * Each time, convert number into long (avoid negative number) and put into bucket.
     * Bucket size is t + 1, to avoid t == 0.
     * If converted number put into a bucket with other elements, return true.
     * The max difference between two elements in same bucket is t.
     * Otherwise, check if two adjacent buckets has element that abs(nums[i] - nums[j]) is smaller than t.
     *
     * @param nums given array
     * @param k    largest absolute difference between i and j
     * @param t    largest absolute difference between nums[i] and nums[j]
     * @return whether there are two distinct indices i and j in the array satisfy the condition ofn k and t
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {

        /* Corner case */
        if (k < 1 || t < 0) {
            return false;
        }

        HashMap<Long, Long> map = new HashMap<>();
        long bucketSize = ((long) t + 1);        // avoid 0

        for (int i = 0; i < nums.length; i++) {
            long num = (long) nums[i] - Integer.MIN_VALUE;      // convert to long number to avoid negative number

            long bucket = num / bucketSize;

            /*
             * Bucket size is t + 1 (avoid 0).
             * If bucket already contains element, return true. The max difference between them is t.
             * Then check two adjacent bucket, if there exist element that difference is at most t, return true. */
            if (map.containsKey(bucket) || (map.containsKey(bucket - 1) && num - map.get(bucket - 1) <= t) || (map.containsKey(bucket + 1) && map.get(bucket + 1) - num <= t)) {
                return true;
            }

            if (i >= k) {
                long firstNum = ((long) nums[i - k] - Integer.MIN_VALUE) / bucketSize;
                map.remove(firstNum);
            }
            map.put(bucket, num);
        }

        return false;
    }

    /**
     * Tree set to save the value and keep size of tree set equals to k.
     * Find closest numbers in set and check if it is in the range of t.
     *
     * @param nums given array
     * @param k    largest absolute difference between i and j
     * @param t    largest absolute difference between nums[i] and nums[j]
     * @return whether there are two distinct indices i and j in the array satisfy the condition ofn k and t
     */
    public boolean treeSet(int[] nums, int k, int t) {

        /* Corner case */
        if (k < 1 || t < 0 || nums.length < 1) {
            return false;
        }

        TreeSet<Long> set = new TreeSet<>();        // use long to avoid overflow

        for (int i = 0; i < nums.length; i++) {

            /*
             * Find closest value in set.
             * Use Long to avoid NullPointerException. */
            Long floor = set.floor((long) nums[i]);       // greatest element in this set <= given element
            Long ceil = set.ceiling((long) nums[i]);       // least element in this set >=given element

            if ((floor != null && nums[i] - floor <= t) || (ceil != null && ceil - nums[i] <= t)) {
                return true;
            }

            set.add((long) nums[i]);

            /*
             * Keep size of set.
             * No need to consider duplicated problem.
             * If there is a duplicated element, it will directly return true (0 < k). */
            if (i >= k) {
                set.remove((long) nums[i - k]);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        ContainsNearbyAlmostDuplicate_220 test = new ContainsNearbyAlmostDuplicate_220();

        System.out.println(test.containsNearbyAlmostDuplicate(new int[]{1, 2, 3, 1}, 3, 0));
        System.out.println(test.containsNearbyAlmostDuplicate(new int[]{1, 5, 9, 1, 5, 9}, 2, 3));
    }
}
