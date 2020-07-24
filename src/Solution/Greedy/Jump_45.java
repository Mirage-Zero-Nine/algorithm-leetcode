package Solution.Greedy;

/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * i.e., steps can be taken to move forward <= nums[i].
 * Your goal is to reach the last index in the minimum number of jumps.
 * Traverse all possible move distance in each step, find max move distance in each distance. (greedy algorithm)
 *
 * @author BorisMirage
 * Time: 2018/06/28 10:40
 * Created with IntelliJ IDEA
 */

public class Jump_45 {
    /**
     * Greedy, before each jump, find max next length in current available range.
     * Then move to the next longest jumped position, until reaches (or over) the end point.
     *
     * @param nums input int array
     * @return minimum number of jumps
     */
    public int jump(int[] nums) {

        /* Corner case */
        if (nums.length == 1) {
            return 0;
        }

        int step = 0, maxJump = 0, end = 0;

        for (int i = 0; i < nums.length - 1; i++) {     // end condition is length - 1, avoid standing at last index
            maxJump = Math.max(maxJump, nums[i] + i);

            if (i == end) {     // if reaches the current max step jump
                step++;
                end = maxJump;
            }
        }

        return step;
    }

    public static void main(String[] args) {

//        System.out.println(new Jump_45().jump(new int[]{1, 2, 3, 4, 5}));

        Jump_45 jumpTest = new Jump_45();
        System.out.println("Min step: " + jumpTest.jump(new int[]{2, 3, 1, 1, 1}));
        System.out.println("Min step: " + jumpTest.jump(new int[]{1, 3, 2}));
        System.out.println("Min step: " + jumpTest.jump(new int[]{1, 1, 1, 1}));
        System.out.println("Min step: " + jumpTest.jump(new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 1, 0}));
        System.out.println("Min step: " + jumpTest.jump(new int[]{4, 1, 1, 3, 1, 1, 1}));
    }
}
