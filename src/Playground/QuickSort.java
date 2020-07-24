package Playground;

import java.util.Arrays;

/**
 * Quick sort implementation.
 *
 * @author BorisMirage
 * Time: 2018/03/25 23:33
 * Created with IntelliJ IDEA
 */

public class QuickSort {
    private int[] arr;

    /**
     * Class constructor.
     */
    public QuickSort() {
    }

    public void sort(int[] arr) {
        this.arr = arr;

        /* Corner case */
        if (this.arr.length < 2) {
            return;
        }

        sort(0, this.arr.length - 1);
    }

    /**
     * The main function that implements QuickSort().
     *
     * @param left  starting index
     * @param right ending index
     */
    private void sort(int left, int right) {

        if (left < right) {

            int partitionIndex = partition(left, right);

            sort(left, partitionIndex - 1);        // recursively sort elements before partition and after partition
            sort(partitionIndex + 1, right);
        }
    }

    /**
     * Quick sort with recursively partition.
     * Sort target array from small to large.
     *
     * @param left  left bound INDEX
     * @param right right bound INDEX
     */
    private int partition(int left, int right) {

        int pivot = this.arr[right];
        int i = (left - 1);      // index of smaller element

        for (int j = left; j < right; j++) {

            /*
             * j points to each element in array.
             * i points to element to be swapped.
             * If pivot < current element, then i move forward, since it should not be swapped.
             * Otherwise, i stops moving forward and once j points to an element smaller than pivot, swap i and j.
             * It swaps first element larger than or equal to pivot (arr[i]) and first element smaller than pivot (arr[j]). */
            if (pivot >= this.arr[j]) {      // if current element is smaller than or equal to pivot
                swap(++i, j);
            }
        }
//        System.out.println(Arrays.toString(arr));

        swap(i + 1, right);     // swap arr[i+1] and arr[high] (or pivot)

        return i + 1;       // divide array to smaller or equal to pivot, and larger than pivot
    }

    /**
     * Swap two elements in array.
     *
     * @param i first index
     * @param j second index
     */
    private void swap(int i, int j) {
        int temp = this.arr[i];
        this.arr[i] = this.arr[j];
        this.arr[j] = temp;
    }

    public static void main(String[] args) {

        int[] arr = new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1};
        new QuickSort().sort(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println("====");

        arr = new int[]{5, 4, 2, 8, 3, 7, 9, 1, 6};
        new QuickSort().sort(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println("====");

        arr = new int[]{8, 4, 7, 8, 5, 3, 5, 7, 1};
        new QuickSort().sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
