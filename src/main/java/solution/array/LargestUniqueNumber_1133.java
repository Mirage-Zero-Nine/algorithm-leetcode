package solution.array;

/**
 * Given an array of integers A, return the largest integer that only occurs once.
 * If no integer occurs once, return -1.
 *
 * @author BorisMirage
 * Time: 2019/08/18 00:14
 * Created with IntelliJ IDEA
 */

public class LargestUniqueNumber_1133 {
    /**
     * Mapping frequency to an int array.
     *
     * @param A given array
     * @return largest integer that only occurs once
     */
    public int largestUniqueNumber(int[] A) {
        if (A.length == 1) {
            return A[0];
        }

        int max = -1;
        int[] temp = new int[1001];
        for (int j : A) {
            temp[j]++;
        }
        for (int i = temp.length - 1; i >= 0; i--) {
            if (temp[i] == 1) {
                return i;
            }
        }
        return max;
    }
}
