package playground;

/**
 * Implementation of binary search.
 *
 * @author BorisMirage
 * Time: 2018/02/25 14:38
 * Created with IntelliJ IDEA
 */

public class BinarySearch {

    /**
     * Constructor of class
     */
    public BinarySearch() {
    }

    /**
     * Implement binary search to find target in sorted array.
     * @param array  given array
     * @param target target to be found
     * @return position of target, or return -1 if not found
     */
    public int searchPosition(int[] array, int target) {
        int start = 0, end = array.length;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (array[mid] == target) {
                return mid;
            }

            if (array[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new BinarySearch().searchPosition(new int[]{1, 4, 5, 6, 7, 8, 9}, 0));       // -1
        System.out.println(new BinarySearch().searchPosition(new int[]{1, 4, 5, 6, 7, 8, 9}, 1));       // 0
        System.out.println(new BinarySearch().searchPosition(new int[]{1, 4, 5, 6, 7, 8, 9}, 9));       // 6
        System.out.println(new BinarySearch().searchPosition(new int[]{1, 4, 5, 6, 7, 8, 9}, 5));       // 2
        System.out.println(new BinarySearch().searchPosition(new int[]{1, 1, 1, 1, 1, 8, 9}, 8));       // 5
    }
}
