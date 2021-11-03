package Solution.DataStructure;

import Lib.NestedInteger;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;

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

    private final Stack<ListIterator<NestedInteger>> stack = new Stack<>();

    /**
     * The NestedInteger is similar to an n-ary tree. The given list could be treated as a children list of a root node.
     * Hence, this problem is actually implementing an n-ary tree leaves iterator.
     * Keep a list iterator stack to store the lists.
     * Initially, it is storing the iterator of whole given list, which is actually the children of a root node.
     * To get the next available element, push NestedInteger with list to stack until find an element that is Integer.
     * When next() is called, return the next element of the top of the stack.
     *
     * @param nestedList given nest integer list
     */
    public NestedIterator_341(List<NestedInteger> nestedList) {
        stack.push(nestedList.listIterator());
    }

    /**
     * Return next value in nested integer.
     * Since the top of the stack is the previous element of the digit, call next() to get the next element.
     *
     * @return next value in nested integer.
     */
    @Override
    public Integer next() {
        return hasNext() ? stack.peek().next().getInteger() : null;
    }

    /**
     * This method combine "find next" and "check if it has next".
     * If the top of the stack does not have element left, pop it.
     * Then push element into stack until it is a digit.
     * Note that after found the digit element, move one element back.
     * In this way, if this method was called multiple times, it would not skip the next available value.
     *
     * @return if iterator has next element
     */
    @Override
    public boolean hasNext() {

        while (!stack.isEmpty()) {
            if (!stack.peek().hasNext()) {
                stack.pop(); // current nested integer has been used
            } else {
                NestedInteger next = stack.peek().next();
                if (next.isInteger()) {
                    stack.peek().previous(); // move to previous element
                    return true;
                }
                stack.push(next.getList().listIterator());
            }
        }
        return false;
    }
}
