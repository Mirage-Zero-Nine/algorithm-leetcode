package solution.stack;

import java.util.Stack;
import java.util.TreeMap;

/**
 * You are given a string s and a robot that currently holds an empty string t.
 * Apply one of the following operations until s and t are both empty:
 * - Remove the first character of a string s. The robot will append this character to the string t.
 * - Remove the last character of a string t. The robot will write this character on paper.
 * Return the lexicographically smallest string that can be written on the paper.
 *
 * @author BorisMirage
 * Time: 2022/10/09 22:23
 * Created with IntelliJ IDEA
 */

public class RobotWithString_2434 {
    /**
     * To assemble the smallest lexicographically string, print the char when there is no smaller char in the string.
     * Iterate the string, push current char to stack if:
     * 1. Stack is empty.
     * 2. There is no lexicographically smaller char in the string (either printed or pushed to the stack).
     * Otherwise, print all the chars in the stack that has no smaller char ahead.
     * Finally, print all the chars remain in stack.
     * Note that in the coding part, a TreeMap is used to store the char frequency.
     * To reduce the time-consuming in the test cases, try to use as less `put` method as possible.
     * The overall time complexity is O(nlogn) in this case.
     * If array is used to count and search the smaller char, the overall time complexity can be O(26n), or O(nlog26).
     *
     * @param s given string
     * @return the smallest lexicographically string
     */
    public String robotWithString(String s) {
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        TreeMap<Character, Integer> frequencyMap = new TreeMap<>();
        int[] count = new int[26];

        // count frequency
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < count.length; i++) {
            // put frequency count to a tree map sorted by alphabet
            // reduce tree map put operation
            if (count[i] != 0) {
                frequencyMap.put((char) (i + 'a'), count[i]);
            }
        }

        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);

            // if the stack is empty, or the current char is smaller than previous char, push it to the stack
            if (stack.isEmpty() || current <= s.charAt(i - 1)) {
                stack.push(current);
            } else {

                // print all chars that has no smaller char ahead
                while (!stack.isEmpty() &&
                        stack.peek() <= s.charAt(i) &&
                        frequencyMap.firstKey() >= stack.peek()) {
                    char printed = stack.pop();
                    sb.append(printed);
                }

                // push current char into stack
                stack.push(s.charAt(i));
            }

            // if all of current char is pushed to stack, remove from frequency map
            if (frequencyMap.get(current) == 1) {
                frequencyMap.remove(current);
            } else {
                frequencyMap.put(current, frequencyMap.get(current) - 1);
            }
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.toString();
    }
}
