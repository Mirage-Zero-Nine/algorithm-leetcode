package Solution.DataStructure;

import java.util.ArrayList;

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
    private ArrayList<Integer> stack = new ArrayList<>();         // work as stack
    private ArrayList<Integer> minStack = new ArrayList<>();      // save min value from current value
    private int min = Integer.MAX_VALUE;

    /**
     * Initialize min stack.
     */
    public MinStack_155() {

    }

    /**
     * Push element into stack
     *
     * @param x push element
     */
    public void push(int x) {
        stack.add(x);
        if (x <= min) {     // if x is smaller than min value, replace min value and add it to stack
            min = x;
            minStack.add(x);
        }
    }

    /**
     * Pop out top of stack (latest inserted value)
     */
    public void pop() {
        if (minStack.size() > 0 && stack.size() > 0 && stack.get(stack.size() - 1) == min) {
            minStack.remove(minStack.size() - 1);
            min = (minStack.size() == 0) ? Integer.MAX_VALUE : minStack.get(minStack.size() - 1);
        }
        stack.remove(stack.size() - 1);
    }

    /**
     * @return top of stack (latest inserted value)
     */
    public int top() {
        return stack.get(stack.size() - 1);
    }

    /**
     * @return min value in stack
     */
    public int getMin() {
        return min;
    }

    public static void main(String[] args) {
        MinStack_155 test = new MinStack_155();

        test.push(2147483646);
        test.push(2147483646);
        test.push(2147483647);
        test.top();
        test.pop();
        System.out.println(test.getMin());
        test.pop();
        System.out.println(test.getMin());
        test.pop();
        test.push(2147483647);
        test.top();
        System.out.println(test.getMin());
        test.push(-2147483648);

//        test.push(395);
//        System.out.println(test.getMin());
//        test.top();
//        System.out.println(test.getMin());
//        test.push(276);
//        test.push(29);
//        System.out.println(test.getMin());
//        test.push(-482);
//        System.out.println(test.getMin());
//        test.pop();
//        test.push(-108);
//        test.push(-251);
    }
}
