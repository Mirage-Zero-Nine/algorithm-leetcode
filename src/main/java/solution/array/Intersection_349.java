package solution.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given two arrays, find their unique common element.
 *
 * @author BorisMirage
 * Time: 2019/02/22 20:34
 * Created with IntelliJ IDEA
 */

public class Intersection_349 {
    /**
     * Sort both array, use a hash set to store elements store in both array to avoid duplication.
     *
     * @param nums1 first int array
     * @param nums2 second int array
     * @return unique common int
     */
    public int[] intersection(int[] nums1, int[] nums2) {

        Set<Integer> s = new HashSet<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int p1 = 0, p2 = 0;

        while (p1 < nums1.length && p2 < nums2.length) {
            if (nums1[p1] < nums2[p2]) {
                p1++;
            } else if (nums1[p1] > nums2[p2]) {
                p2++;
            } else {
                s.add(nums1[p1]);
                p1++;
                p2++;
            }
        }

        int[] out = new int[s.size()];
        int k = 0;
        for (Integer num : s) {
            out[k++] = num;
        }

        return out;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] b = {2, 2, 0, 0, 11, 5};
        Intersection_349 t = new Intersection_349();
        System.out.println(Arrays.toString(t.intersection(a, b)));
    }
}
