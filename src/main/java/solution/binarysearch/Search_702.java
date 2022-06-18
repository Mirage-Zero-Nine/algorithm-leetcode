package solution.binarysearch;

import library.ArrayReader;

/**
 * Given an integer array sorted in ascending order, write a function to search target in nums.
 * If target exists, then return its index, otherwise return -1. However, the array size is unknown to you.
 * The array can only be accessed by using an ArrayReader interface, where ArrayReader.get(k) returns the index k.
 * You may assume all integers in the array are less than 10000.
 * If you access the array out of bounds, ArrayReader.get will return 2147483647.
 *
 * @author BorisMirage
 * Time: 2019/09/21 11:17
 * Created with IntelliJ IDEA
 */

public class Search_702 {
    /**
     * Binary search. The range of search is enlarged each time by 2^n.
     *
     * @param reader given reader
     * @param target target number
     * @return target index, or -1 if target does not exist
     */
    public int search(ArrayReader reader, int target) {
        int end = 1;

        while (reader.get(end) < target) {
            end = end << 1;
        }

        int start = end >> 1;        // 1 >> 1 == 0

        while (start <= end) {
            int mid = start + (end - start) / 2, val = reader.get(mid);

            if (val == target) {
                return mid;
            } else if (val > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }
}
