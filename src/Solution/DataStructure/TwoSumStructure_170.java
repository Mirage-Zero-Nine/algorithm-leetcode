package Solution.DataStructure;

import java.util.*;

/**
 * Design and implement a TwoSum class. It should support the following operations: add and find.
 * add - Add the number to an internal data structure.
 * find - Find if there exists any pair of numbers which sum is equal to the value.
 *
 * @author BorisMirage
 * Time: 2018/10/11 22:04
 * Created with IntelliJ IDEA
 */

public class TwoSumStructure_170 {
    HashMap<Integer, Integer> temp;

    /**
     * Initialize data structure.
     */
    public TwoSumStructure_170() {
        temp = new HashMap<>();
    }

    /**
     * Add value to structure.
     *
     * @param number add value
     */
    public void add(int number) {
        temp.put(number, temp.getOrDefault(number, 0) + 1);
    }


    /**
     * Find if there exists any pair of numbers which sum is equal to the value.
     *
     * @param value given value
     * @return if two sum value can be found in this structure
     */
    public boolean find(int value) {
        for (Map.Entry<Integer, Integer> entry : temp.entrySet()) {
            int i = entry.getKey();
            int j = value - i;

            /* Note that if there is only one element in the set, return false */
            if ((i == j && entry.getValue() > 1) || (i != j && temp.containsKey(j))) {
                return true;
            }
        }
        return false;
    }
}
