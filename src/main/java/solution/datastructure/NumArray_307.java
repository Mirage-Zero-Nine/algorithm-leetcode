package solution.datastructure;

import java.util.Stack;

/**
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
 * The update(i, val) function modifies nums by updating the element at index i to val.
 *
 * @author BorisMirage
 * Time: 2019/06/14 16:47
 * Created with IntelliJ IDEA
 */

public class NumArray_307 {

    private node root;

    /**
     * Create a segment tree to store, find and update elements in array.
     *
     * @param nums given array
     */
    public NumArray_307(int[] nums) {
        root = segmentTreeBuilder(nums, 0, nums.length - 1);
    }

    /**
     * Recursively construct the segment tree.
     * The nodes in array contains left and right bound in array.
     * The value is the sum of given range.
     * If left and right are equal, then the sum is the element in array.
     * To construct the segment tree, traverse to the bottom of tree. Sum is from left sub tree and right sub tree.
     *
     * @param nums given array
     * @param min  left bound in array
     * @param max  right bound in array
     * @return constructed segment tree
     */
    private node segmentTreeBuilder(int[] nums, int min, int max) {

        if (min > max) {
            return null;
        } else {

            node n = new node(min, max);        // create node at current location
            if (min == max) {
                n.value = nums[min];        // leaf of tree
            } else {
                int mid = min + (max - min) / 2;
                n.left = segmentTreeBuilder(nums, min, mid);
                n.right = segmentTreeBuilder(nums, mid + 1, max);

                assert n.right != null;     // avoid null exception

                /* Add sum bottom-up */
                n.value = n.left.value + n.right.value;
            }
            return n;
        }
    }

    /**
     * Update ith element in tree.
     *
     * @param i   given index
     * @param val value to update
     */
    public void update(int i, int val) {
        updateNode(root, val, i);
    }

    /**
     * Recursively traverse to the given index and update the value.
     * Update parent nodes via same method in tree construction.
     *
     * @param n   current root node
     * @param val value to be updated
     * @param i   index of element in nums
     */
    private void updateNode(node n, int val, int i) {

        if (n.min == n.max) {       // found node to update
            n.value = val;
        } else {

            int mid = n.min + (n.max - n.min) / 2;      // binary search
            if (i <= mid) {
                updateNode(n.left, val, i);
            } else {
                updateNode(n.right, val, i);
            }

            n.value = n.left.value + n.right.value;     // parent node value should be update as well
        }
    }

    /**
     * Return the sum of given range in array.
     *
     * @param i left index of range
     * @param j right index of range
     * @return sum of given range
     */
    public int sumRange(int i, int j) {
        return findRangeSum(root, i, j);
    }

    /**
     * The sum is made up by all segments in tree that is within the range of given index.
     * If given range is
     *
     * @param n     current root node
     * @param start start index
     * @param end   end index
     * @return sum of given range of array
     */
    private int findRangeSum(node n, int start, int end) {

        if (n.min == start && n.max == end) {       // identical range
            return n.value;
        } else {

            int mid = n.min + (n.max - n.min) / 2;      // binary dividing segment
            if (end <= mid) {
                return findRangeSum(n.left, start, end);        // target range is in the left subtree
            } else if (start >= mid + 1) {
                return findRangeSum(n.right, start, end);       // target range is in the right subtree
            } else {

                /* Divide range into binary if indexes are within two different sub tree */
                return findRangeSum(n.right, mid + 1, end) + findRangeSum(n.left, start, mid);
            }
        }
    }

    /**
     * Node in segment tree.
     */
    private static class node {

        int min, max;       // left bound and right bound of array (inclusive)
        int value = 0;      // value store in node

        node left, right;        // left child and right child

        /**
         * @param min left bound of node
         * @param max right bound of node
         */
        public node(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }

    /**
     * Print all nodes in tree by in-order traversal.
     */
    public void printAllNodes() {
        Stack<node> s = new Stack<>();

        if (root == null) {
            System.out.println("Root node is NULL!");
        }

        while (this.root != null || !s.isEmpty()) {
            while (this.root != null) {
                s.add(this.root);
                this.root = this.root.left;
            }
            this.root = s.pop();
            System.out.println("Node Value: " + this.root.value);
            System.out.println("min: " + this.root.min + ", max: " + this.root.max);
            this.root = this.root.right;
        }
    }

    /**
     * Unit test.
     *
     * @param args given arguments
     */
    public static void main(String[] args) {
        NumArray_307 test = new NumArray_307(new int[]{0, 9, 5, 7, 3});
        System.out.println(test.sumRange(4, 4));
//        test.printAllNodes();
    }
}

