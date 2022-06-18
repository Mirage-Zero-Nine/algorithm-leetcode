package solution.map;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an array of integers, find if the array contains any duplicates.
 * The function should return true if any value appears at least twice in the array,
 * It should return false if every element is distinct.
 *
 * @author BorisMirage
 * Time: 2019/06/13 15:23
 * Created with IntelliJ IDEA
 */

public class ContainsDuplicate_217 {
    /**
     * Use a hash set to save previous visited numbers. If find a element can not be added, then return true.
     *
     * @param nums given array
     * @return if the array contains any duplicates
     */
    public boolean containsDuplicate(int[] nums) {

        Set<Integer> s = new HashSet<>();

        for (int num : nums) {
            if (!s.add(num)) {      // add return false if set already has it
                return true;
            }
        }
        return false;
    }
}
