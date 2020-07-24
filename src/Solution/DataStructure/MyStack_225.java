package Solution.DataStructure;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Implement the following operations of a stack using queues.
 * 1. push(x) -- Push element x onto stack.
 * 2. pop() -- Removes the element on top of the stack.
 * 3. top() -- Get the top element.
 * 4. empty() -- Return whether the stack is empty.
 *
 * @author BorisMirage
 * Time: 2020/05/14 20:08
 * Created with IntelliJ IDEA
 */

public class MyStack_225 {
    private final Queue<Integer> q;     // simulate stack
    Integer top;                        // top of the stack, initially it will be null

    /**
     * Constructor.
     */
    public MyStack_225() {
        top = null;
        q = new LinkedList<>();
    }

    /**
     * Push element x onto stack and update the value represent for top of the stack.
     *
     * @param x element to be added
     */
    public void push(int x) {
        q.add(x);
        this.top = x;
    }

    /**
     * Removes the element on top of the stack and returns that element.
     * Add the elements at top of the queue to the end of queue size - 1 times.
     * Then the top of the queue is the top of the stack.
     *
     * @return top of the stack
     */
    public int pop() {

        int size = q.size();
        for (int i = 0; i < size - 1; i++) {
            top = q.poll();
            q.add(top);
        }

        if (q.size() > 0) {
            if (q.size() == 1) {
                top = null;
            }

            return q.poll();
        }

        return top;
    }

    /**
     * Get the top element.
     *
     * @return top element in stack
     */
    public int top() {
        return top;
    }

    /**
     * Returns whether the stack is empty.
     *
     * @return whether the stack is empty
     */
    public boolean empty() {
        return top == null;
    }
}
