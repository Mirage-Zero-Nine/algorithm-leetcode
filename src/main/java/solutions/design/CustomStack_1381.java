package solutions.design;

/**
 * Design a stack which supports the following operations.
 * 1. CustomStack(int maxSize): Stack with maxSize limits the max elements in stack. Do nothing when reached the limit.
 * 2. void push(int x) Adds x to the top of the stack if the stack hasn't reached the maxSize.
 * 3. int pop() Pops and returns the top of stack or -1 if the stack is empty.
 * 4. void inc(int k, int val): Increments bottom k elements of the stack by val. At most maxSize elements.
 *
 * @author BorisMirage
 * Time: 2020/03/16 20:34
 * Created with IntelliJ IDEA
 */

public class CustomStack_1381 {
    private final int[] array;
    private final int[] increments;
    // This is the last occupied index, so -1 represents an empty stack.
    private int topElementIndex = -1;

    /**
     * Initializes a stack with capacity {@code maxSize}.
     *
     * <p>One array stores the values, and a second stores additions that are waiting to be
     * applied. Instead of changing every value during an increment, the stack records the
     * addition once at the last affected position. For example, after pushing 10, 20, and 30,
     * incrementing the bottom three by 5 records 5 at the top position. Popping returns 35 and
     * passes that 5 to the position below, so later pops return 25 and 15. This keeps each
     * increment constant time, regardless of how many values it affects.
     *
     * <p>{@code increments[i]} is an addition to every current position from 0 through {@code i}.
     * When position {@code i} reaches the top, its own pending amount is included in the value
     * returned; amounts from higher positions have already been passed down to it. The top index
     * is -1 when the stack is empty and otherwise points to its last occupied position.
     *
     * <p>Time complexity: O(maxSize) to initialize both arrays.
     * <p>Auxiliary space: O(maxSize).
     *
     * @param maxSize maximum number of elements the stack can hold
     */
    public CustomStack_1381(int maxSize) {
        array = new int[maxSize];
        increments = new int[maxSize];
    }

    /**
     * Adds a value at the next free slot if the stack has room.
     *
     * <p>Time complexity: O(1).
     * <p>Auxiliary space: O(1).
     *
     * @param x value to push
     */
    public void push(int x) {
        if (topElementIndex < array.length - 1) {
            array[++topElementIndex] = x;
        }
    }

    /**
     * Removes and returns the top value, including additions passed down to it.
     *
     * <p>Before removing the top, its pending addition is passed to the position below. That
     * position may then pass it farther down when it is popped, so every affected value receives
     * the addition without updating all values during {@link #increment(int, int)}.
     *
     * <p>Time complexity: O(1).
     * <p>Auxiliary space: O(1).
     *
     * @return the incremented top value, or -1 when the stack is empty
     */
    public int pop() {
        if (topElementIndex < 0) {
            return -1;
        }
        if (topElementIndex > 0) {
            // increments[i] is still owed to every live element from the bottom through i.
            // Carry the top's amount to the next boundary so those lower elements receive it later.
            // For example, a pending 5 at index 2 covers all three elements; popping it leaves 5 at index 1.
            // At index 0 there is no lower element to carry the amount to, so the guard skips this step.
            increments[topElementIndex - 1] += increments[topElementIndex];
        }
        // Save the amount before clearing this slot; the top's return value still needs it.
        int increment = increments[topElementIndex];
        // Clear the popped slot so a future push reusing it starts without stale increments.
        increments[topElementIndex] = 0;
        // Return the stored value plus its pending amount, then move the top down one slot.
        return array[topElementIndex--] + increment;
    }

    /**
     * Adds {@code val} to the bottom {@code k} current values, or to all values if fewer than
     * {@code k} are in the stack.
     *
     * <p>The addition is recorded at the last affected position instead of being applied to each
     * value. As elements are popped, that amount moves down one position at a time and is included
     * in each returned value. This makes the operation constant time. The caller must provide
     * {@code k >= 1}, as required by the problem.
     *
     * <p>Time complexity: O(1).
     * <p>Auxiliary space: O(1).
     *
     * @param k   number of bottom elements to increment
     * @param val amount added to each of those elements
     */
    public void increment(int k, int val) {
        if (topElementIndex >= 0) {
            // Clamp the prefix boundary to the current top when k exceeds the stack size.
            increments[Math.min(k - 1, topElementIndex)] += val;
        }
    }
}
