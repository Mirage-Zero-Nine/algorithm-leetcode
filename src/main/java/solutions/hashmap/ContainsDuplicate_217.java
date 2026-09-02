package solutions.hashmap;

import java.util.Arrays;
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
     * Determines whether any value appears at least twice in {@code nums}.
     *
     * <p>{@link Set#add(Object)} returns {@code false} when the value is
     * already in the set, so that return value directly identifies a
     * duplicate. {@code anyMatch} short-circuits as soon as a duplicate is
     * found, so the remaining values are not processed.
     *
     * @param nums array of integers to inspect; LeetCode guarantees it is non-null
     * @return {@code true} if any value occurs more than once; otherwise
     * {@code false}
     * @implNote Expected time complexity is {@code O(n)} and auxiliary space
     * complexity is {@code O(n)}, where {@code n} is the array length.
     */
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        // HashSet.add returns false for a value that has already been seen.
        return Arrays.stream(nums).anyMatch(i -> !set.add(i));
    }
}
