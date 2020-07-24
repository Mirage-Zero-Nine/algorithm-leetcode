package Playground;

import java.util.Arrays;

/**
 * Implementation of merge sort.
 *
 * @author BorisMirage
 * Time: 2020/03/03 11:21
 * Created with IntelliJ IDEA
 */

public class MergeSort {
    int[] arr;

    public MergeSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException();
        }
        this.arr = arr;
        sort(0, arr.length - 1);
    }

    /**
     * Main function that sorts array using merge().
     *
     * @param start start index
     * @param end   end index
     */
    public void sort(int start, int end) {
        if (start < end) {
            int middle = (start + end) / 2;        // find middle point

            sort(start, middle);                // sort left half
            sort(middle + 1, end);              // sort right half
            merge(start, middle, end);          // merge the sorted halves
        }
    }

    /**
     * Merge two sorted array.
     *
     * @param start  start index
     * @param middle middle of two array
     * @param end    end index
     */
    private void merge(int start, int middle, int end) {
        int leftSize = middle - start + 1;        // find sizes of two subarrays to be merged
        int rightSize = end - middle;

        int[] left = new int[leftSize];      // temp left half array
        int[] right = new int[rightSize];      // temp right right half array

        System.arraycopy(arr, start, left, 0, leftSize);     // copy data to temp arrays
        for (int j = 0; j < rightSize; ++j) {
            right[j] = arr[middle + 1 + j];
        }

        int i = 0, j = 0;       // initial indexes of first and second subarrays
        int k = start;          // initial index of merged subarray array
        while (i < leftSize && j < rightSize) {      // merge sort two sorted array and copy it to new int array
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                j++;
            }
            k++;
        }

        while (i < leftSize) {        // copy remaining elements of left if any
            arr[k] = left[i];
            i++;
            k++;
        }

        while (j < rightSize) {        // copy remaining elements of right if any
            arr[k] = right[j];
            j++;
            k++;
        }
    }

    /**
     * Test method.
     *
     * @param arr given array to be sorted
     */
    static void test(int[] arr) {
        int[] tmp = null;
        try {
            tmp = Arrays.copyOf(arr, arr.length);
            System.out.println("Merge sort result: " + Arrays.toString(new MergeSort(arr).arr));
            Arrays.sort(arr);
            System.out.println("Sorted result: " + Arrays.toString(arr));
        } catch (IllegalArgumentException | NullPointerException ex) {
            System.out.println("Illegal input array!");
        } finally {
            System.out.println(Arrays.toString(tmp));
        }
    }

    public static void main(String[] args) {
        test(new int[]{9, 9, 2, 3, 12, 2, 3, 4, 56, 6, 7});
        test(new int[]{});
        test(new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1});
        test(new int[]{1});
        test(new int[]{1, 2, 3, 4, 5});
        test(null);
    }
}
