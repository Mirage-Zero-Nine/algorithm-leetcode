package solution.twopointers;

/**
 * Given an array of integers that is already sorted in ascending order.
 * Find two numbers such that they add up to a specific target number.
 * The function twoSum should return indices of the two numbers, where index1 must be less than index2.
 * Index starts from 1.
 *
 * @author BorisMirage
 * Time: 2019/02/18 13:16
 * Created with IntelliJ IDEA
 */

public class TwoSum_167 {
    /**
     * Two pointers, one starts from beginning and one starts at end.
     *
     * @param numbers int array
     * @param target  target number
     * @return index array, or null if no matches
     */
    public int[] twoSum(int[] numbers, int target) {

        int p1 = 0;
        int p2 = numbers.length - 1;

        while (p1 < p2) {
            if (numbers[p1] + numbers[p2] == target) {
                return new int[]{p1 + 1, p2 + 1};
            }
            if (numbers[p1] + numbers[p2] < target) {
                p1++;
            } else if (numbers[p1] + numbers[p2] > target) {
                p2--;
            }
        }
        return null;
    }
}
