package solutions.dynamicprogramming;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * Given two integer arrays arr1 and arr2, return the minimum number of operations (possibly zero) needed to make arr1 strictly increasing.
 * In one operation, you can choose two indices 0 <= i < arr1.length and 0 <= j < arr2.length and do the assignment arr1[i] = arr2[j].
 * If there is no way to make arr1 strictly increasing, return -1.
 *
 * @author BorisMirage
 * Time: 2019/09/07 19:55
 * Created with IntelliJ IDEA
 */

public class MakeArrayIncreasing_1187 {
    /**
     * Dynamic programming.
     * State transition: for each processed prefix, retain the minimum replacement count
     * for every possible last value. Each state can keep the current value when it is
     * larger than the previous value, or replace it with the smallest available value
     * from {@code arr2} that is larger than the previous value. Dominated states with
     * the same last value are merged.
     *
     * @param arr1 first array
     * @param arr2 array contains elements can be replaced into arr1
     * @return minimum number of operations (possibly zero) needed to make arr1 strictly increasing
     *         or {@code -1} when no valid sequence exists
     * @implNote The map has at most {@code O(n^2)} states; time is {@code O(n^2 log n)}
     *          and auxiliary space is {@code O(n^2)} in the worst case.
     */
    public int makeArrayIncreasing(int[] arr1, int[] arr2) {

        int n = arr1.length;

        /* Corner case */
        if (n < 2) {
            return n - 1;
        }

        TreeSet<Integer> ts = new TreeSet<>();
        for (int value : arr2) {
            ts.add(value);
        }

        Map<Integer, Integer> states = new HashMap<>();
        states.put(Integer.MIN_VALUE, 0);
        for (int value : arr1) {
            Map<Integer, Integer> next = new HashMap<>();
            for (Map.Entry<Integer, Integer> state : states.entrySet()) {
                if (value > state.getKey()) {
                    next.merge(value, state.getValue(), Math::min);
                }
                Integer replacement = ts.higher(state.getKey());
                if (replacement != null) {
                    next.merge(replacement, state.getValue() + 1, Math::min);
                }
            }
            states = next;
            if (states.isEmpty()) {
                return -1;
            }
        }
        return states.values().stream().min(Integer::compareTo).orElse(-1);
    }


}
