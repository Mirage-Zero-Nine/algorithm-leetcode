package solution.datastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 * 1. push(x) -- Push element x onto stack.
 * 2. pop() -- Removes the element on top of the stack.
 * 3. top() -- Get the top element.
 * 4. getMin() -- Retrieve the minimum element in the stack.
 *
 * @author BorisMirage
 * Time: 2019/07/26 16:44
 * Created with IntelliJ IDEA
 */

public class MinStack_155 {
    private final List<Integer> values;
    private final List<Integer> minValues;

    /**
     * Two lists, one maintains all the values added to the list.
     * The other list stores all the min values.
     * Add the new pushed value to min list when it's less than or equal to the current min value.
     */
    public MinStack_155() {
        values = new ArrayList<>();
        minValues = new ArrayList<>();
    }

    /**
     * Push value to the list.
     *
     * @param val value to be added
     */
    public void push(int val) {
        values.add(val);
        if (minValues.isEmpty() || val <= minValues.getLast()) {
            minValues.add(val);
        }
    }

    /**
     * Remove the value at top.
     */
    public void pop() {
        if (!values.isEmpty()) {
            if (Objects.equals(values.getLast(), minValues.getLast())) {
                minValues.removeLast();
            }
            values.removeLast();
        }
    }

    /**
     * Return the top value.
     *
     * @return top value
     */
    public int top() {
        return values.isEmpty() ? -1 : values.getLast();
    }

    /**
     * Return min value.
     *
     * @return min value
     */
    public int getMin() {
        return minValues.isEmpty() ? -1 : minValues.getLast();
    }
}
