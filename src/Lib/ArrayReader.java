package Lib;

import java.util.HashMap;

/**
 * ArrayReader class.
 * ArrayReader.get(k) returns the element of the array at index k (0-indexed).
 *
 * @author BorisMirage
 * Time: 2019/09/21 11:17
 * Created with IntelliJ IDEA
 */

public class ArrayReader {
    private HashMap<Integer, Integer> m = new HashMap<>();

    /**
     * Create a mapping function to get elements with its index.
     *
     * @param arr given array
     */
    void setArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            m.put(arr[i], i);
        }
    }

    /**
     * Get index based on value.
     *
     * @param k value
     * @return index of currnet value
     */
    public int get(int k) {
        return m.getOrDefault(k, 2147483647);
    }
}
