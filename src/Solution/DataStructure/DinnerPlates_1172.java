package Solution.DataStructure;

import java.util.*;

/**
 * There are infinite number of stacks arranged in a row and numbered (left to right) from 0.
 * Each of the stacks has the same maximum capacity.
 * Implement the DinnerPlates class:
 * 1. DinnerPlates(int capacity) Initializes the object with the maximum capacity of the stacks.
 * 2. void push(int val) pushes the given positive integer val into the leftmost stack with size less than capacity.
 * 3. int pop() returns the value at the top of the rightmost non-empty stack and removes it from that stack.
 * 4. int popAtStack(int index) returns the value at the top of the stack with the given index and removes it.
 * Returns -1 if no such element under pop().
 *
 * @author BorisMirage
 * Time: 2019/08/24 20:20
 * Created with IntelliJ IDEA
 */

public class DinnerPlates_1172 {
    private TreeMap<Integer, Stack<Integer>> m = new TreeMap<>();
    private int capacity;
    private TreeSet<Integer> ts = new TreeSet<>();      // save available stacks, excludes all new stacks
    private int max = 0;        // stacks has been used (this counter counting every used stack including empty stacks)

    /**
     * Use a TreeMap to store stacks, and a TreeSet to store all available stacks for push.
     *
     * @param capacity capacity for each stack
     */
    public DinnerPlates_1172(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Push elements into the leftmost stack with size less than capacity.
     *
     * @param val value to be pushed
     */
    public void push(int val) {
        int p = (ts.isEmpty()) ? max++ : ts.pollFirst();

        if (!m.containsKey(p)) {
            m.put(p, new Stack<>());
        }

        m.get(p).add(val);

        if (m.get(p).size() < capacity) {
            ts.add(p);
        }
    }

    /**
     * Pop out the right-most non-empty stack top value and removes it from that stack.
     * Remove the right-most non-empty stack and pop out the top.
     * If the stack is not empty, add it back to TreeMap.
     * No matter it is empty or not, add the # of stack into heap for push.
     *
     * @return the value at the top of the rightmost non-empty stack
     */
    public int pop() {

        if (m.isEmpty()) {
            return -1;
        }

        Map.Entry<Integer, Stack<Integer>> last = m.pollLastEntry();
        int k = last.getKey();
        Stack<Integer> tmp = last.getValue();
        int val = tmp.pop();

        if (!tmp.isEmpty()) {
            m.put(k, tmp);
        }
        ts.add(k);

        return val;
    }

    /**
     * Pop up the top of specific stack and removes it.
     * Get the stack by index, if that stack does not exist in map, return -1.
     * Otherwise, pop out the given stack and check if it is empty.
     * If it is empty, remove that stack.
     * No matter it is empty or not, add the # of stack into heap for push.
     *
     * @param index given index of stack
     * @return the value at the top of the stack with the given index
     */
    public int popAtStack(int index) {

        if (!m.containsKey(index)) {
            return -1;
        }

        Stack<Integer> tmp = m.get(index);
        int val = tmp.pop();
        if (tmp.isEmpty()) {
            m.remove(index);
        }
        ts.add(index);

        return val;
    }

    public static void main(String[] args) {
        DinnerPlates_1172 test = new DinnerPlates_1172(2);
        test.push(1);
        test.push(2);
        test.push(3);
        test.push(4);
        test.push(5);
        System.out.println(test.popAtStack(0));     // 2
        test.push(20);
        test.push(21);
        System.out.println(test.popAtStack(0));     // 20
        System.out.println(test.popAtStack(2));     // 21
        System.out.println("=====");
        System.out.println(test.pop());     // 5
        System.out.println(test.pop());     // 4
        System.out.println(test.pop());     // 3
        System.out.println(test.pop());     // 1
        System.out.println(test.pop());     // -1
    }
}
