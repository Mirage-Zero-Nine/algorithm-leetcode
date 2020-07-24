package Solution.DataStructure;

import java.util.Stack;

/**
 * Implement the following operations of a queue using stacks.
 * 1. push(x) -- Push element x to the back of queue.
 * 2. pop() -- Removes the element from in front of queue.
 * 3. peek() -- Get the front element.
 * 4. empty() -- Return whether the queue is empty.
 * Notes:
 * 1. You must use only standard operations of a stack.
 * 2. You may assume that all operations are valid .
 *
 * @author BorisMirage
 * Time: 2019/10/22 21:08
 * Created with IntelliJ IDEA
 */
public class MyQueue_232 {
    private Stack<Integer> s1 = new Stack<>();
    private Stack<Integer> s2 = new Stack<>();
    private int size;

    /**
     * Initialization.
     */
    public MyQueue_232() {
    }

    /**
     * Push element x to the back of queue.
     *
     * @param x push element
     */
    public void push(int x) {
        if (s1.isEmpty() && size != 0) {
            reverse();
        }
        s1.push(x);
        size++;
    }

    /**
     * Removes the element from in front of queue and returns that element.
     *
     * @return first element in head of queue and remove it
     */
    public int pop() {
        if (s2.isEmpty()) {
            reverse();
        }
        size--;
        return s2.pop();
    }

    /**
     * Get the front element.
     *
     * @return first element in head of queue
     */
    public int peek() {
        if (s2.isEmpty()) {
            reverse();
        }
        return s2.peek();
    }

    /**
     * Returns whether the queue is empty.
     *
     * @return true if queue is empty
     */
    public boolean empty() {
        return size == 0;
    }

    /**
     * Move content from one stack to the other empty stack.
     */
    private void reverse() {

        if (s1.isEmpty() && !s2.isEmpty()) {
            while (!s2.empty()) {
                s1.push(s2.pop());
            }
        } else if (!s1.isEmpty() && s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }
    }
}
