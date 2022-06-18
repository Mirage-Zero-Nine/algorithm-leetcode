package solution.bitmanipulation;

/**
 * Given a non-empty array of integers, every element appears twice except for one. Find that single one.
 *
 * @author BorisMirage
 * Time: 2019/02/12 17:30
 * Created with IntelliJ IDEA
 */

public class SingleNumber_136 {
    /**
     * XOR each adjacent number and the last number is the result.
     *
     * @param nums int array
     * @return number appears only once
     */
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res ^= num;     // bitwise XOR
        }
        return res;
    }
}
