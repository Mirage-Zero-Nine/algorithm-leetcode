package solution.map;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * You are given a 0-indexed array nums that consists of n distinct positive integers.
 * Apply m operations to this array, each operation replace the number operations[i][0] with operations[i][1].
 * It is guaranteed that in the ith operation:
 * - operations[i][0] exists in nums.
 * - operations[i][1] does not exist in nums.
 * Return the array obtained after applying all the operations.
 *
 * @author BorisMirage
 * Time: 2022/10/07 18:40
 * Created with IntelliJ IDEA
 */

public class ArrayChange_2295 {
    /**
     * Keep a hash map to store the value and its index.
     * Each time, update the map with new value and old index.
     * Finally, iterate the whole map, place all the values based on index stored in map.
     *
     * @param nums       given array
     * @param operations operation array
     * @return the array obtained after applying all the operations
     */
    public int[] arrayChange(int[] nums, int[][] operations) {
        Map<Integer, Integer> indexMap = new HashMap<>(); // store value in given nums and its index

        for (int i = 0; i < nums.length; i++) {
            indexMap.put(nums[i], i);
        }

        Arrays.stream(operations).forEach(
                o -> {
                    int index = indexMap.get(o[0]); // get index from value
                    indexMap.remove(o[0]); // old value was removed, remove index from map
                    indexMap.put(o[1], index); // add new value and its index
                }
        );

        int[] output = new int[nums.length];
        indexMap.forEach((key, value) -> output[value] = key); // get all value, placed back to output array with its index

        return output;
    }
}
