package Solution.TwoPointers;

/**
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 *
 * @author BorisMirage
 * Time: 2018/10/05 20:38
 * Created with IntelliJ IDEA
 */

public class MergeSortedArray_88 {
    /**
     * Two pointers.
     *
     * @param nums1 first array (larger array)
     * @param m     first array's length
     * @param nums2 second array
     * @param n     second array's length
     */
    public void mergeSortedArray(int[] nums1, int m, int[] nums2, int n) {

        int p1 = m - 1;      // nums1 actual length
        int p2 = n - 1;      // nums2
        int i = m + n - 1;      // num1 extra space end

        while (p1 > -1 && p2 > -1) {      // two arrays are sorted, so start at the end (largest)

            if (nums1[p1] > nums2[p2]) {        // fill larger one in two arrays
                nums1[i--] = nums1[p1--];
            } else {
                nums1[i--] = nums2[p2--];
            }
        }

        while (p2 > -1) {       // in case any element in nums2 is smaller than any in nums1
            nums1[i--] = nums2[p2--];
        }
    }
}
