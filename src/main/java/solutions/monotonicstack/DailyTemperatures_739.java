package solutions.monotonicstack;

import java.util.Stack;

/**
 * Given a list of daily temperatures T.
 * Return a list that for each day in the input, tells how many days have to wait until a warmer temperature.
 * If there is no future day for which this is possible, put 0 instead.
 * Note: The length of temperatures will be in the range [1, 30000]. Each temperature will be an integer in the range [30, 100].
 *
 * @author BorisMirage
 * Time: 2019/07/19 14:57
 * Created with IntelliJ IDEA
 */

public class DailyTemperatures_739 {

    /**
     * Uses a stack of unresolved day indices with nonincreasing temperatures from bottom to top.
     * A new warmer day resolves each cooler index it removes: since days are scanned from left to
     * right, that day is the first warmer one for each removed index. Any indices left in the
     * stack have no warmer day later in the input. The input is not modified, and the stack is
     * local to this call.
     * <p>
     * Time complexity: O(n), since each index is pushed once and popped at most once.
     * Auxiliary space: O(n) for the stack, excluding the returned array.
     *
     * @param temperatures daily temperatures, or {@code null} / empty for an empty result
     * @return days until the next strictly warmer temperature, or 0 if none exists
     */
    public int[] dailyTemperatures(int[] temperatures) {
        // corner cases
        if (temperatures == null || temperatures.length == 0) {
            return new int[0];
        }

        int[] output = new int[temperatures.length];
        // This stack contains indices without a warmer day yet: indices increase bottom to top,
        // while their temperatures do not. Equal temperatures stay until a strictly warmer day.
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                // The current day is the first warmer day for the index on top:
                // every earlier day was already checked and left it unresolved.
                output[stack.peek()] = i - stack.pop();
            }
            // Indices stay in increasing order and their temperatures do not increase
            // from bottom to top, so a later warmer day can resolve them in order.
            stack.push(i);
        }

        return output;
    }

    /**
     * Uses a primitive array as a stack of unresolved day indices, avoiding boxed indices and
     * synchronized {@link Stack} operations. The current day resolves colder indices from the
     * top until the remaining top is at least as warm; unresolved indices are stored in increasing
     * order with nonincreasing temperatures. The input is not modified, and all state is local.
     * <p>
     * Time complexity: O(n), since each index is pushed once and popped at most once.
     * Auxiliary space: O(n) for the index array, excluding the returned array.
     *
     * @param temperatures daily temperatures, or {@code null} / empty for an empty result
     * @return days until the next strictly warmer temperature, or 0 if none exists
     */
    public int[] dailyTemperaturesWithArrayStack(int[] temperatures) {
        // corner cases
        if (temperatures == null || temperatures.length == 0) {
            return new int[0];
        }

        int[] output = new int[temperatures.length], stack = new int[temperatures.length];
        int size = 0;

        // The primitive stack keeps unresolved indices in increasing order; their temperatures
        // are nonincreasing bottom to top, with equal values retained until a strictly warmer day.
        for (int i = 0; i < temperatures.length; i++) {
            // Strict comparison keeps equal temperatures unresolved because equal is not warmer.
            while (size > 0 && temperatures[i] > temperatures[stack[size - 1]]) {
                int unresolvedDay = stack[--size];
                output[unresolvedDay] = i - unresolvedDay;
            }
            // Every index enters once; size always points to the next free stack slot.
            stack[size++] = i;
        }

        return output;
    }
}
