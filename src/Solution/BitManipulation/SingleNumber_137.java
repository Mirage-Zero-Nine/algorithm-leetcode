package Solution.BitManipulation;

/**
 * Given a non-empty array of integers, every element appears three times except for one, which appears exactly once.
 * Find that single one.
 *
 * @author BorisMirage
 * Time: 2019/02/12 17:34
 * Created with IntelliJ IDEA
 */

public class SingleNumber_137 {
    /**
     * Bitwise operation.
     *
     * @param nums int array
     * @return number appears only once
     */
    public int singleNumber(int[] nums) {
        int once = 0, twice = 0;

        for (int num : nums) {
            once = (once ^ num) & ~twice;        // ~ bitwise compliment, & bitwise and
            twice = (twice ^ num) & ~once;
        }
        return once;
    }
}
