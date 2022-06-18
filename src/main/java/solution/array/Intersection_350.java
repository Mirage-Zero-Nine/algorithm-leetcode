package solution.array;

import java.util.*;

/**
 * Given two arrays, write a function to compute their intersection.
 * Each element in the result should appear as many times as it shows in both arrays.
 *
 * @author BorisMirage
 * Time: 2019/02/22 20:51
 * Created with IntelliJ IDEA
 */

public class Intersection_350 {
    /**
     * Sort 2 arrays and then use 2 pointers respectively to save duplicate elements with appearance.
     *
     * @param nums1 first int array
     * @param nums2 second int array
     * @return common element with common appearance in both array
     */
    public int[] intersect(int[] nums1, int[] nums2) {

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int p1 = 0, p2 = 0;

        List<Integer> list = new ArrayList<>();

        while ((p1 < nums1.length) && (p2 < nums2.length)) {
            if (nums1[p1] < nums2[p2]) {
                p1++;
            } else if (nums1[p1] > nums2[p2]) {
                p2++;
            } else {
                list.add(nums1[p1]);
                p1++;
                p2++;
            }
        }
        int[] out = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            out[i] = list.get(i);
        }

        return out;
    }
}
