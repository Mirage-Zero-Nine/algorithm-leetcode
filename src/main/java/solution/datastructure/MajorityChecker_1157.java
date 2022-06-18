package solution.datastructure;

/**
 * Implementing the class MajorityChecker, which has the following API:
 * 1. MajorityChecker(int[] arr) constructs an instance of MajorityChecker with the given array arr;
 * 2. int query(int left, int right, int threshold) has arguments such that: 0 <= left <= right < arr.length
 * Each query(...) returns the element in arr[left], arr[left+1], ..., arr[right] that occurs at least threshold times.
 * Or return -1 if no such element exists.
 *
 * @author BorisMirage
 * Time: 2019/08/10 20:26
 * Created with IntelliJ IDEA
 */

public class MajorityChecker_1157 {
    private final int[] arr;

    /**
     * Initialization of structure.
     *
     * @param arr given array
     */
    public MajorityChecker_1157(int[] arr) {
        this.arr = arr;
    }

    /**
     * Boyer-Moore voting algorithm.
     * Traverse array twice. First time find the majority and second time count if it is satisfied with threshold.
     * Majority element in array will be at least 2/n times.
     * Therefore, use a count and a temp to traverse the array.
     * If current element in array is temp, count++, otherwise, count--.
     * And if count == 0, replace temp with current element in array.
     * The majority element will be the last element in temp when traverse completed.
     *
     * @param left      left bound
     * @param right     right bound
     * @param threshold limit of occurrence
     * @return the element in a[left], a[left+1], ..., a[right] that occurs at least threshold times, or -1 if none
     */
    public int query(int left, int right, int threshold) {
        int count = 0, majority = -1;
        for (int i = left; i <= right; i++) {
            if (count == 0) {
                majority = arr[i];
            }
            count = (majority == arr[i]) ? count + 1 : count - 1;

        }

        count = 0;
        for (int i = left; i <= right; i++) {
            if (arr[i] == majority) {
                count++;
            }
        }
        return (count >= threshold) ? majority : -1;
    }

    public static void main(String[] args) {
        MajorityChecker_1157 test = new MajorityChecker_1157(new int[]{1, 1, 2, 2, 1, 1});
        System.out.println(test.query(0, 5, 4));
        System.out.println(test.query(0, 3, 3));
        System.out.println(test.query(2, 3, 2));
    }
}
