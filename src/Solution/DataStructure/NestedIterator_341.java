package Solution.DataStructure;

import Lib.NestedInteger;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * @author BorisMirage
 * Time: 2019/07/03 14:50
 * Created with IntelliJ IDEA
 */

public class NestedIterator_341 implements Iterator<Integer> {

    private Stack<NestedInteger> stack = new Stack<>();     // nested integers are given in reversed order

    /**
     * Initialize class.
     *
     * @param nestedList given nest integer list
     */
    public NestedIterator_341(List<NestedInteger> nestedList) {
        for (int i = nestedList.size() - 1; i >= 0; i--) {
            stack.push(nestedList.get(i));      // push it from back to front
        }
    }

    /**
     * Return next value in nested integer.
     *
     * @return next value in nested integer.
     */
    @Override
    public Integer next() {
        return stack.pop().getInteger();        // top of stack is the first nested integer
    }

    /**
     * Check if iterator has next element.
     *
     * @return if iterator has next element
     */
    @Override
    public boolean hasNext() {
        while (!stack.isEmpty()) {
            NestedInteger curr = stack.peek();
            if (curr.isInteger()) {
                return true;
            }
            stack.pop();
            for (int i = curr.getList().size() - 1; i >= 0; i--) {
                stack.push(curr.getList().get(i));
            }
        }
        return false;
    }
}
