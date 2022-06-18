package library;

import java.util.List;

/**
 * Nested integer class.
 *
 * @author BorisMirage
 * Time: 2019/05/30 15:01
 * Created with IntelliJ IDEA
 */
public class NestedInteger {
    boolean isInteger;
    Integer val;
    List<NestedInteger> list;

    /**
     * Constructor initializes an empty nested list.
     */
    public NestedInteger() {
    }

    /**
     * Constructor initializes a single integer.
     *
     * @param val value in current nested integer
     */
    public NestedInteger(Integer val) {
        this.val = val;
        isInteger = true;
    }

    /**
     * Return true if this NestedInteger holds a single integer, rather than a nested list.
     *
     * @return true if this NestedInteger holds a single integer, false if it holds a nested list.
     */
    public boolean isInteger() {
        return isInteger;
    }

    /**
     * Return the single integer that this NestedInteger holds, if it holds a single integer.
     * Return null if this NestedInteger holds a nested list.
     *
     * @return the integer it holds, return null if this NestedInteger holds a nested list
     */
    public Integer getInteger() {
        return val;
    }

    /**
     * Set this NestedInteger to hold a single integer.
     *
     * @param value value for NestedInteger to hold
     */
    public void setInteger(int value) {
        this.val = value;
    }

    /**
     * Set this NestedInteger to hold a nested list and adds a nested integer to it.
     *
     * @param ni new NestedInteger
     */
    public void add(NestedInteger ni) {
        list.add(ni);
    }

    /**
     * Return the nested list that this NestedInteger holds, if it holds a nested list.
     * Return null if this NestedInteger holds a single integer.
     *
     * @return the nested list it holds, return null if this NestedInteger holds a single integer
     */
    public List<NestedInteger> getList() {
        return list;
    }
}
