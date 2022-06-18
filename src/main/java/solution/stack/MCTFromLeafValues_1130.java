package solution.stack;

import java.util.Stack;

/**
 * Given an array arr of positive integers, consider all binary trees such that:
 * 1. Each node has either 0 or 2 children;
 * 2. The values of arr correspond to the values of each leaf in an in-order traversal of the tree.
 * (Recall that a node is a leaf if and only if it has 0 children.)
 * 3. The value of each non-leaf node is equal to the product of the largest leaf value in its left and right subtree respectively.
 * Among all possible binary trees considered, return the smallest possible sum of the values of each non-leaf node.
 * It is guaranteed this sum fits into a 32-bit integer.
 *
 * @author BorisMirage
 * Time: 2019/07/27 14:17
 * Created with IntelliJ IDEA
 */

public class MCTFromLeafValues_1130 {
    /**
     * Larger value should be calculated last. Hence, combine small value first for each node.
     * The combine process is same as find next greater value in array.
     *
     * @param arr given array with leaves in in-order traversal of the tree
     * @return smallest possible sum of the values of each non-leaf node
     */
    public int mctFromLeafValues(int[] arr) {
        Stack<Integer> s = new Stack<>();
        s.push(Integer.MAX_VALUE);
        int total = 0;
        for (int value : arr) {
            while (s.peek() < value) {
                int mid = s.pop();
                total += mid * Math.min(s.peek(), value);       // combine smaller value for min tree sum
            }
            s.push(value);
        }

        while (s.size() > 2) {
            total += s.pop() * s.peek();        // cleaning rest of nodes
        }

        return total;
    }
}
