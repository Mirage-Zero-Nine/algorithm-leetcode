package Solution.DataStructure;

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
    private int maxSize;
    private int[] increment;        // stores

    private int[] array;            // use array instead of stack
    private int pointer = -1;       // initially, this is an empty stack

    /**
     * Lazy increment.
     * Use an extra int array to store the increment.
     * But in increment, only add to kth (or max size) element with increment.
     * Since when popping elements, only the top of the stack will be popped out.
     * Hence, the increment element can be moved from popped element to its previous element.
     *
     * @param maxSize max size of stack
     */
    public CustomStack_1381(int maxSize) {
        this.maxSize = maxSize;
        increment = new int[maxSize];
        array = new int[maxSize];
    }

    /**
     * Push elements into stack.
     *
     * @param x element to be added
     */
    public void push(int x) {
        if (pointer < array.length - 1) {
            array[++pointer] = x;
        }
    }

    /**
     * Pop out elements from top of the stack.
     * Note that the increment of current element should be added to its previous elements.
     *
     * @return top of the stack
     */
    public int pop() {
        if (pointer < 0) {
            return -1;
        }

        if (pointer > 0) {
            increment[pointer - 1] += increment[pointer];       // move increment value to previous element in stack
        }
        int extra = increment[pointer];
        increment[pointer] = 0;     // remove increment of pop out element

        return array[pointer--] + extra;
    }

    /**
     * Increment bottom k elements in stack with given value.
     * Only add kth element (or the top of the stack) with value.
     * Because when popping, only one element is popped. Therefore, the increment can be moved to next element in stack.
     * So only add to kth element from bottom is enough.
     *
     * @param k   bottom k elements
     * @param val value to be incremented
     */
    public void increment(int k, int val) {

        int index = Math.min(k - 1, pointer);       // check if k is larger than size
        if (index >= 0) {
            increment[index] += val;
        }
    }
}
