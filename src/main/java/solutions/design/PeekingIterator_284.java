package solutions.design;

import java.util.Iterator;

/**
 * Given an Iterator class interface with methods: next() and hasNext().
 * Design and implement a PeekingIterator that support the peek() operation.
 * It essentially peek() at the element that will be returned by the next call to next().
 *
 * @author BorisMirage
 * Time: 2019/07/03 14:13
 * Created with IntelliJ IDEA
 */

public class PeekingIterator_284 implements Iterator<Integer> {
    // A null value means there is no cached element, so source elements must be non-null.
    private Integer next = null;
    private final Iterator<Integer> iterator;

    /**
     * Initializes a one-element look-ahead over {@code iterator}.
     * <p>
     * The wrapper reads and caches the first value from the source iterator and retains it in
     * {@code next}. Peeks and availability checks then use that cached value, while each call to
     * {@code next()} returns it and loads the following value. Constructing this wrapper therefore
     * advances the source once when it is nonempty, and both iterators share the remaining state.
     * Source values must be non-null because {@code null} marks exhaustion. For the supported
     * empty/exhausted extension, {@link #peek()} and {@link #next()} return {@code null}.
     *
     * Time complexity: O(1), assuming the source iterator's operations take O(1).
     * Auxiliary space: O(1).
     *
     * @param iterator source of values to peek and consume
     */
    public PeekingIterator_284(Iterator<Integer> iterator) {
        this.iterator = iterator;
        if (iterator.hasNext()) {
            // Keep one source value ready so repeated peeks never advance the source.
            next = iterator.next();
        }
    }

    /**
     * Returns the cached value without consuming it; returns {@code null} when exhausted.
     *
     * Time complexity: O(1).
     * Auxiliary space: O(1).
     *
     * @return the next value, or {@code null} if no value remains
     */
    public Integer peek() {
        return next;
    }

    /**
     * Returns the cached value and refills the look-ahead slot from the source iterator.
     * The supported exhausted extension returns {@code null} rather than throwing.
     *
     * Time complexity: O(1), assuming the source iterator's operations take O(1).
     * Auxiliary space: O(1).
     *
     * @return the next value, or {@code null} if no value remains
     */
    @Override
    public Integer next() {
        Integer output = next;
        // Refill only after saving the cached value; if the source is empty, null remains the
        // exhaustion marker and repeated calls stay in the same exhausted state.
        next = iterator.hasNext() ? iterator.next() : null;
        return output;
    }

    /**
     * Reports whether a cached value is available without advancing the source.
     *
     * Time complexity: O(1).
     * Auxiliary space: O(1).
     *
     * @return {@code true} when the look-ahead slot contains a value
     */
    @Override
    public boolean hasNext() {
        return next != null;
    }
}
