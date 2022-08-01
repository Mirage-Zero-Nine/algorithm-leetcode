package playground;

import java.util.Stack;

/**
 * Implementation of segment tree.
 *
 * @author BorisMirage
 * Time: 2019/06/14 11:25
 * Created with IntelliJ IDEA
 */

public class SegmentTreeSample {

    /**
     * Build segment tree based on given int array
     *
     * @param nums given int array
     * @return root of segment tree
     */
    public SegmentTreeNode buildSegmentTree(int[] nums) {
        return treeBuilder(0, nums.length - 1, nums);
    }


    /**
     * Recursively build segment tree based on left/right boundary and given int array.
     *
     * @param left  left bound of array
     * @param right right bound of array
     * @param nums  given array
     * @return root node of segment tree
     */
    private SegmentTreeNode treeBuilder(int left, int right, int[] nums) {
        if (left > right) {
            return null;
        }

        if (left == right) {
            return new SegmentTreeNode(left, right, nums[left]);
        }

        SegmentTreeNode root = new SegmentTreeNode(left, right, findMin(nums, left, right));

        int mid = (left + right) / 2;
        root.left = treeBuilder(left, mid, nums);
        root.right = treeBuilder(mid + 1, right, nums);

        return root;
    }

    /**
     * Find min value in array.
     *
     * @param nums  given array
     * @param left  left bound, 0 <= left <= right <= length - 1
     * @param right right bound, 0 <= left <= right <= length - 1
     * @return min value in given array
     */
    private int findMin(int[] nums, int left, int right) {

        int min = Integer.MAX_VALUE;

        if (left == right) {
            return nums[left];
        }

        for (int i = left; i <= right; i++) {
            min = Math.min(nums[i], min);
        }
        return min;
    }

    /**
     * Unit test.
     *
     * @param args given arguments
     */
    public static void main(String[] args) {
        SegmentTreeSample test = new SegmentTreeSample();
        printAllNodes(test.buildSegmentTree(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
    }

    /**
     * Nodes in segment tree.
     * Each node in segment tree has at least these attributes:
     * 1. min: left bound of current node
     * 2. right bound of current node
     * 3. min/max value
     * The value is the min/max value in array[left, right]. And the left/right is where "segment" comes from.
     * One improvement: add a lazy propagation in node of segment tree, to reduce update time.
     */
    static class SegmentTreeNode {

        int min, max;       // left bound and right bound of array (inclusive)
        int value;      // value store in node

        SegmentTreeNode left, right;        // left child and right child

        /**
         * @param min   left bound of node
         * @param max   right bound of node
         * @param value value store in node
         */
        public SegmentTreeNode(int min, int max, int value) {
            this.min = min;
            this.max = max;
            this.value = value;
        }
    }

    /**
     * A helper method that prints all nodes in tree by in-order traversal.
     */
    public static void printAllNodes(SegmentTreeNode node) {
        SegmentTreeNode root = node;
        Stack<SegmentTreeNode> s = new Stack<>();

        if (root == null) {
            System.out.println("Root node is NULL!");
        }

        while (root != null || !s.isEmpty()) {
            while (root != null) {
                s.add(root);
                root = root.left;
            }
            root = s.pop();
            System.out.println("Node Value: " + root.value);
            System.out.println("min: " + root.min + ", max: " + root.max);
            root = root.right;
        }
    }
}

