package solution.datastructure;

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
    private Integer next = null;
    private final Iterator<Integer> iter;

    /**
     * Initialize the class.
     *
     * @param iterator given iterator
     */
    public PeekingIterator_284(Iterator<Integer> iterator) {
        iter = iterator;
        if (iter.hasNext()) {
            next = iter.next();
        }
    }

    /**
     * Returns the next element in the iteration without advancing the iterator.
     *
     * @return return
     */
    public Integer peek() {
        return next;
    }

    /**
     * Return next element in iterator.
     *
     * @return next element
     */
    @Override
    public Integer next() {
        Integer out = next;
        next = iter.hasNext() ? iter.next() : null;
        return out;
    }

    /**
     * Check if iterator has next element.
     *
     * @return if iterator has next element
     */
    @Override
    public boolean hasNext() {
        return next != null;
    }
}
