package solutions.dfs;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Given an integer n, return all the numbers in the range [1, n] sorted in lexicographical order.
 * You must write an algorithm that runs in O(n) time and uses O(1) extra space.
 *
 * @author BorisMirage
 * Time: 2026/08/17 23:50
 * Created with IntelliJ IDEA
 */

public class LexicalOrder_386 {
    /**
     * Returns the integers from 1 to n in dictionary (lexicographical) order.
     *
     * <p>Numbers can be viewed as an implicit tree: for example, the children
     * of 1 are 10, 11, ..., 19. Instead of storing a DFS stack, {@code current}
     * represents the next node to visit.
     * We descend to {@code current * 10} whenever that value is valid.
     * Otherwise, we move upward until the current node has an unvisited sibling
     * within the limit, then visit that sibling.</p>
     *
     * <p>Each number is visited once, so the time complexity is O(n). The
     * traversal uses O(1) auxiliary space, excluding the returned list.</p>
     *
     * @param n the upper bound, inclusive
     * @return the numbers from 1 to n in lexicographical order
     */
    public List<Integer> lexicalOrder(int n) {
        List<Integer> output = new ArrayList<>(n);
        int current = 1;

        for (int i = 0; i < n; i++) {
            output.add(current);

            // current value * 10 if it's still smaller than n
            if (current <= n / 10) {
                current *= 10;
            } else {
                // this is the "leave level" of current tree
                // moving to previous level either it's reaching trailing 9, or it's larger than n,
                while (current % 10 == 9 || current >= n) {
                    current /= 10;
                }
                current++;
            }
        }
        return output;
    }

    /**
     * Returns the integers from 1 to n in dictionary order using recursive DFS.
     *
     * <p>The numbers form an implicit tree. For example, 1 has children
     * 10, 11, ..., 19. A pre-order traversal visits each prefix before its
     * children, which produces lexicographical order.</p>
     *
     * <p>This method is included for comparison and learning. Its recursion
     * stack uses O(log n) auxiliary space, so {@link #lexicalOrder(int)} is the
     * method that satisfies the problem's strict O(1) extra-space constraint.
     * Each number is visited once, so the time complexity is O(n).</p>
     *
     * @param n the upper bound, inclusive
     * @return the numbers from 1 to n in lexicographical order
     */
    public List<Integer> lexicalOrderDfs(int n) {
        List<Integer> output = new ArrayList<>(n);

        // Start from each one-digit prefix in lexical order.
        IntStream.range(1, 10).forEach(firstDigit -> dfs(n, firstDigit, output));
        return output;
    }

    /**
     * Visits a numeric prefix and then recursively visits its valid children.
     *
     * @param n the upper bound, inclusive
     * @param current the current numeric prefix
     * @param output the traversal result
     */
    private void dfs(int n, int current, List<Integer> output) {
        if (current > n) {
            return;
        }

        // pre-order traversal: emit the prefix before its children.
        output.add(current);

        IntStream.range(0, 10)
                // Appending a digit creates the next level of the tree.
                .filter(digit -> current * 10 + digit <= n)
                .forEach(digit -> dfs(n, current * 10 + digit, output));
    }
}
