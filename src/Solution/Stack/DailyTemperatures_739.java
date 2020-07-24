package Solution.Stack;

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
     * Use a stack to save the days that has not found a warmer day.
     * Compare current value to top of stack, pop days until reaches a day with higher value or stack is empty.
     * Use an array to simulate the stack to reduce time.
     *
     * @param T given daily temperatures
     * @return for each day in the input, tells how many days have to wait until a warmer temperature
     */
    public int[] dailyTemperatures(int[] T) {

//        Stack<Integer> s = new Stack<>();

        int[] s = new int[T.length];
        int[] out = new int[T.length];
        int top = -1;

        for (int i = 0; i < out.length; i++) {

//            while (!s.isEmpty() && T[i] > T[s.peek()]) {        // top of stack is the days that have not find warmer one
//                out[s.peek()] = i - s.pop();
//            }
//            s.push(i);

            while (top != -1 && T[i] > T[s[top]]) {
                out[s[top]] = i - s[top--];
            }
            s[++top] = i;
        }
        return out;
    }
}
