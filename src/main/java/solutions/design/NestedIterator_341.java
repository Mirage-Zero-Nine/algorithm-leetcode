package solutions.design;

import library.NestedInteger;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

/**
 * You are given a nested list of integers nestedList.
 * Each element is either an integer or a list whose elements may also be integers or other lists.
 * Implement an iterator to flatten it.
 * Implement the NestedIterator class:
 * - NestedIterator(List<NestedInteger> nestedList) Initializes the iterator with the nested list nestedList.
 * - int next() Returns the next integer in the nested list.
 * - boolean hasNext() Returns true if there are still some integers in the nested list and false otherwise.
 * If res matches the expected flattened list, then your code will be judged as correct.
 *
 * @author BorisMirage
 * Time: 2019/07/03 14:50
 * Created with IntelliJ IDEA
 */

public class NestedIterator_341 implements Iterator<Integer> {
    private final Deque<NestedInteger> queue;

    /**
     * Initializes a lazy depth-first iterator over the nested values.
     * <p>
     * The deque holds the unvisited values in traversal order. When its front is a list, its
     * children are placed at the front in reverse order, so the first child is examined next.
     * This expands only lists needed to reach the next integer and does not modify the input
     * lists. After exhaustion, {@link #next()} returns {@code null} as this implementation's
     * sentinel behavior.
     * <p>
     * Time complexity: O(n) to copy the top-level references.
     * <p>
     * Auxiliary space: O(n) for the initial deque, where n is the top-level list size.
     *
     * @param nestedList values to traverse
     */
    public NestedIterator_341(List<NestedInteger> nestedList) {
        queue = new ArrayDeque<>(nestedList);
    }

    /**
     * Returns and consumes the next flattened integer. Calling {@link #hasNext()} first expands
     * any list nodes at the front; the integer is then removed from the deque. Exhaustion returns
     * {@code null}, matching this class's existing sentinel behavior.
     * <p>
     * Time complexity: The preparation cost is the same as {@link #hasNext()}; consuming an
     * integer already at the front takes O(1).
     * <p>
     * Auxiliary space: O(1) beyond the deque used by the iterator.
     *
     * @return the next integer, or {@code null} when exhausted
     */
    @Override
    public Integer next() {
        return hasNext() ? queue.poll().getInteger() : null;
    }

    /**
     * Advances the deque until its front is an integer or it is empty. Each encountered list is
     * replaced by its children at the front in reverse insertion order, preserving left-to-right
     * depth-first traversal. Stopping at the first integer leaves it untouched, so repeated calls
     * are idempotent until {@link #next()} consumes it.
     * <p>
     * Time complexity: Let N be the total number of integer and list nodes, and m the width of an
     * expanded list. Each list is expanded once and each child is added once. This takes O(N) total
     * when child lists support O(1) indexed access; with a general {@link List}, reverse indexing
     * can take O(sum(m^2)) across expanded lists. A call with no list to expand takes O(1).
     * <p>
     * Auxiliary space: O(N) in the worst case for unvisited nested values held in the deque.
     *
     * @return {@code true} when an integer remains, otherwise {@code false}
     */
    @Override
    public boolean hasNext() {
        while (!queue.isEmpty() && (!queue.peek().isInteger())) {
            // Polling removes this list even when it is empty; for nonempty lists, its children
            // replace it before the next check, so every iteration advances toward another value.
            List<NestedInteger> next = queue.poll().getList();
            // Add children in reverse so the original first child remains at the front.
            for (int i = next.size() - 1; i >= 0; i--) {
                queue.addFirst(next.get(i));
            }
        }
        // The loop stops only at an integer or exhaustion; leave any integer for next() to consume.
        return !queue.isEmpty();
    }
}
