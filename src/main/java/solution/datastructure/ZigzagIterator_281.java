package solution.datastructure;

import java.util.List;

/**
 * Given two 1D vectors, implement an iterator to return their elements alternately.
 *
 * @author BorisMirage
 * Time: 2019/07/03 13:25
 * Created with IntelliJ IDEA
 */
public class ZigzagIterator_281 {

    private final List<Integer> l1;
    private final List<Integer> l2;
    private boolean isFirst = true;     // check if next value comes from first list
    private int count = 0;              // point to value to be returned

    /**
     * Do not use iterator.
     *
     * @param v1 first given list
     * @param v2 second given list
     */
    public ZigzagIterator_281(List<Integer> v1, List<Integer> v2) {
        this.l1 = v1;
        this.l2 = v2;
    }

    /**
     * Return next value.
     *
     * @return next value in order
     */
    public int next() {
        int out;

        if (isFirst && count < l1.size()) {
            out = l1.get(count);
            if (count < l2.size()) {
                isFirst = false;
            } else {
                count++;
            }
        } else {
            out = l2.get(count++);
            isFirst = count < l1.size();
        }
        return out;
    }

    /**
     * Check if next value exist.
     *
     * @return if next value exist
     */
    public boolean hasNext() {
        return count < this.l1.size() || count < this.l2.size();
    }
}
