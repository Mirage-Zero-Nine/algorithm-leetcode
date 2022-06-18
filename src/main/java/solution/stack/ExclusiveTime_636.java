package solution.stack;

import java.util.List;
import java.util.Stack;

/**
 * On a single threaded CPU, we execute some functions.  Each function has a unique id between 0 and N-1.
 * We store logs in timestamp order that describe when a function is entered or exited.
 * Each log is a string with this format: "{function_id}:{"start" | "end"}:{timestamp}".
 * For example, "0:start:3" means the function with id 0 started at the beginning of timestamp 3.
 * "1:end:2" means the function with id 1 ended at the end of timestamp 2.
 * A function's exclusive time is the number of units of time spent in this function.
 * Note that this does not include any recursive calls to child functions.
 * The CPU is single threaded which means that only one function is being executed at a given time unit.
 * Return the exclusive time of each function, sorted by their function id.
 *
 * @author BorisMirage
 * Time: 2020/02/27 17:33
 * Created with IntelliJ IDEA
 */

public class ExclusiveTime_636 {
    /**
     * Use a stack to temporary stores # of interrupted function.
     * If current log is a start command of function, then previous function has ended.
     * Calculate time of previous function and save the index to the stack for later execution.
     * If current log is the end of function, pop out index of current function and add time to the output array.
     * Each time, save the end time of current function for next round calculation.
     *
     * @param n    n functions
     * @param logs logs with functions
     * @return the exclusive time of each function, sorted by their function id
     */
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] out = new int[n];
        Stack<Integer> s = new Stack<>();
        int startTime = 0;       // start time is always 0

        for (String log : logs) {
            String[] arr = log.split(":");
            int functionIndex = Integer.parseInt(arr[0]);
            int currentTime = Integer.parseInt(arr[2]);

            if (arr[1].equals("start")) {       // a new interruption
                if (!s.isEmpty()) {             // if there is a job in process
                    out[s.peek()] += currentTime - startTime;        // previous function has been interrupted
                }
                s.push(functionIndex);          // add current function #
                startTime = currentTime;        // set start time of current function
            } else {        // end of current function
                out[s.pop()] += currentTime - startTime + 1;        // calculate time of finished function
                startTime = currentTime + 1;                        // the start time of next function should add 1
            }
        }

        return out;
    }
}
