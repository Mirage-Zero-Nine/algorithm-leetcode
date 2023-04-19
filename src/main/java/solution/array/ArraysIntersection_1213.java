package solution.array;

import java.util.ArrayList;
import java.util.List;

/**
 * Given three integer arrays arr1, arr2 and arr3 sorted in strictly increasing order.
 * Return a sorted array of only the integers that appeared in all three arrays.
 *
 * @author BorisMirage
 * Time: 2019/10/06 11:10
 * Created with IntelliJ IDEA
 */

public class ArraysIntersection_1213 {
    /**
     * Three pointers. Unless three elements are equal, otherwise move one pointer forward each time.
     *
     * @param arr1 first given array
     * @param arr2 second given array
     * @param arr3 third given array
     * @return sorted array of only the integers that appeared in all three arrays
     */
    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        int p1 = 0, p2 = 0, p3 = 0;
        List<Integer> output = new ArrayList<>();

        while (p1 < arr1.length && p2 < arr2.length && p3 < arr3.length) {
            if (arr1[p1] == arr2[p2] && arr2[p2] == arr3[p3]) {
                output.add(arr1[p1]);
                p1++;
                p2++;
                p3++;
            } else if (arr1[p1] < arr2[p2]) {
                p1++;
            } else if (arr2[p2] < arr3[p3]) {
                p2++;
            } else {
                p3++;
            }
        }

        return output;
    }
}
