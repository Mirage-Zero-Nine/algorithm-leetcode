package solutions.design;


import java.util.ArrayList;
import java.util.List;

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
    private final List<int[]> list;

    /**
     * Creates an empty stack whose entries store both the pushed value and the minimum for the
     * stack prefix ending at that entry. The last entry consequently contains both the top value
     * and the current minimum, so every operation reads or removes one list entry. The empty-stack
     * behavior of {@link #top()} and {@link #getMin()} is outside the problem contract; {@link #pop()}
     * safely does nothing when the stack is empty.
     * <p>
     * Time complexity: O(1) for construction.
     * Auxiliary space: O(1) for the initial empty stack; O(n) after n pushes.
     */
    public MinStack_155() {
        list = new ArrayList<>();
    }

    /**
     * Pushes a value together with the smaller of that value and the previous prefix minimum.
     * This cached minimum means that the new last entry immediately answers {@link #getMin()}.
     *
     * Time complexity: Amortized O(1); an occasional dynamic-array resize can take O(n).
     * Auxiliary space: O(1) beyond the new entry.
     *
     * @param value value to add to the top of the stack
     */
    public void push(int value) {
        int min = list.isEmpty() ? value : Math.min(value, list.getLast()[1]);

        // Entry slot 0 is the value; slot 1 is the minimum over this entry and all entries below it.
        list.add(new int[]{value, min});
    }

    /**
     * Removes the current top entry. Removing the cached pair also restores the value and minimum
     * that belonged to the preceding stack prefix.
     * <p>
     * Time complexity: O(1).
     * Auxiliary space: O(1) beyond the list storage.
     */
    public void pop() {
        if (!list.isEmpty()) {
            // The next entry, if any, already contains the correct previous prefix minimum.
            list.removeLast();
        }
    }

    /**
     * Returns the value in the last cached pair.
     *
     * @return the current top value; callers must keep the stack nonempty as required by the problem
     * <p>
     * Time complexity: O(1).
     * Auxiliary space: O(1).
     */
    public int top() {
        return list.getLast()[0];
    }

    /**
     * Returns the prefix minimum cached in the last pair.
     *
     * @return the current minimum; callers must keep the stack nonempty as required by the problem
     * <p>
     * Time complexity: O(1).
     * Auxiliary space: O(1).
     */
    public int getMin() {
        return list.getLast()[1];
    }
}
