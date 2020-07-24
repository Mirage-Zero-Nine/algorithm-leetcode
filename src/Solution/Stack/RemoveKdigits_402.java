package Solution.Stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Given a non-negative integer num represented as a string.
 * Remove k digits from the number so that the new number is the smallest possible.
 * Note:
 * 1. The length of num is less than 10002 and will be ≥ k.
 * 2. The given num does not contain any leading zero.
 *
 * @author BorisMirage
 * Time: 2019/06/28 10:12
 * Created with IntelliJ IDEA
 */

public class RemoveKdigits_402 {
    /**
     * Use a dequeue to store the numbers in given string.
     * When found a new number that is smaller than last element in dequeue, remove element in dequeue until meets larger.
     * Note that this remove operation can be done at most k.
     *
     * @param num given string represent numbers
     * @param k   k digits to be removed
     * @return new number is the smallest possible
     */
    public String removeKdigits(String num, int k) {

        /* Corner case */
        if (num.length() == k) {
            return "0";
        }
        Deque<Character> q = new LinkedList<>();        // use deque to replace stack to avoid reverse operation
        for (int i = 0; i < num.length(); i++) {
            while (!q.isEmpty() && q.peekLast() > num.charAt(i) && k > 0) {
                q.removeLast();
                k--;
            }
            q.addLast(num.charAt(i));
        }

        while (k > 0) {     // handle corner case such as "1111111" that will not be removed in q.peekLast() > num.charAt(i)
            q.removeLast();
            k--;
        }

        StringBuilder out = new StringBuilder();

        while (q.size() > 1 && q.peek() == '0') {
            q.poll();       // remove 0 at beginning of output
        }
        while (!q.isEmpty()) {
            out.append(q.poll());
        }

        return out.toString();
    }

    public static void main(String[] args) {
        RemoveKdigits_402 test = new RemoveKdigits_402();
        System.out.println(test.removeKdigits("1432219", 3));       // 1219
        System.out.println(test.removeKdigits("10200", 1));     // 200
        System.out.println(test.removeKdigits("112", 1));       // 11
        System.out.println(test.removeKdigits("1234567890", 9));        // 0
    }
}
