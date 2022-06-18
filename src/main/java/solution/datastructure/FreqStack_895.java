package solution.datastructure;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Design a stack-like data structure to push elements to the stack and pop the most frequent element from the stack.
 * Implement the FreqStack class:
 * - FreqStack() constructs an empty frequency stack.
 * - void push(int val) pushes an integer val onto the top of the stack.
 * - int pop() removes and returns the most frequent element in the stack.
 * If there is a tie for the most frequent element, the element closest to the stack's top is removed and returned.
 *
 * @author BorisMirage
 * Time: 2021/10/13 16:34
 * Created with IntelliJ IDEA
 */

public class FreqStack_895 {
    private final Map<Integer, Stack<Integer>> frequencyMap; // frequency and elements under this frequency
    private final Map<Integer, Integer> keyMap; // entry: value:frequency
    private int maxFrequency; // max frequency in this stack

    /**
     * Keep a hash map to store the value and its frequency.
     * Keep another hash map to store the frequency count and values under this frequency.
     * In the second map, use a stack, so that when pop() was called, it can return the value in O(1).
     */
    public FreqStack_895() {
        maxFrequency = 0;
        this.frequencyMap = new HashMap<>();
        this.keyMap = new HashMap<>();
    }

    /**
     * Push value into the stack.
     * Add the value to the value-frequency map first, then update the frequency map.
     * Push the value to the stack under corresponding frequency.
     *
     * @param val given value
     */
    public void push(int val) {
        int frequency = keyMap.getOrDefault(val, 0) + 1;
        keyMap.put(val, frequency);
        maxFrequency = Math.max(frequency, maxFrequency);
        if (!frequencyMap.containsKey(frequency)) {
            frequencyMap.put(frequency, new Stack<>());
        }

        frequencyMap.get(frequency).push(val);
    }

    /**
     * Pop the value from the stack.
     * The most frequent value could be found from the frequency map.
     * If all values under most frequent stack was popped out, reduce max frequency by 1.
     *
     * @return most frequency value in stack
     */
    public int pop() {
        Stack<Integer> mostFrequentStack = frequencyMap.get(maxFrequency);
        int mostFrequent = mostFrequentStack.pop();
        keyMap.put(mostFrequent, maxFrequency - 1);
        if (mostFrequentStack.size() == 0) {
            frequencyMap.remove(maxFrequency);
            maxFrequency--;
        }

        return mostFrequent;
    }
}
