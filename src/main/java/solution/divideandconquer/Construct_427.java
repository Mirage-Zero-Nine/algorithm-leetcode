package solution.divideandconquer;


/**
 * Given an n * n matrix grid of 0's and 1's only. We want to represent grid with a Quad-Tree.
 * Return the root of the Quad-Tree representing grid.
 * A Quad-Tree is a tree data structure in which each internal node has exactly four children. Besides, each node has two attributes:
 * - val: True if the node represents a grid of 1's or False if the node represents a grid of 0's. Notice that you can assign the val to True or False when isLeaf is False, and both are accepted in the answer.
 * - isLeaf: True if the node is a leaf node on the tree or False if the node has four children.
 * We can construct a Quad-Tree from a two-dimensional area using the following steps:
 * - If the current grid has the same value (i.e all 1's or all 0's) set isLeaf True and set val to the value of the grid and set the four children to Null and stop.
 * - If the current grid has different values, set isLeaf to False and set val to any value and divide the current grid into four sub-grids as shown in the photo.
 * Recurse for each of the children with the proper sub-grid.
 *
 * @author BorisMirage
 * Time: 2025/05/05 14:48
 * Created with IntelliJ IDEA
 */

public class Construct_427 {
    /**
     * Divide and conquer.
     * Divide the grid into 4 sub-grids with half of the length.
     * When the size is 1 for the grid, create a new node with value and set it as leaf node to return.
     * Time complexity: O(n^2 * lgn)
     * Space complexity: O(lgn) for recursing stack.
     *
     * @param grid given grid
     * @return the root of the Quad-Tree representing grid
     */
    public Node construct(int[][] grid) {
        int size = grid.length;
        return divide(grid, 0, 0, size);
    }

    /**
     * Divide and conquer.
     * Flow:
     * - If the current grid size is 1, return a new leaf node with the value of the grid based on passed coord.
     * - If not, divide the grid into 4 sub-grids recursively.
     * After the divide process is completed, check all 4 nodes to see if they are all leaf nodes with the same value.
     * If so, merge all the child nodes to the current root node.
     *
     * @param grid   given grid
     * @param row    current row
     * @param column current column
     * @param size   size of current grid
     * @return root of the tree.
     */
    private Node divide(int[][] grid, int row, int column, int size) {
        if (size == 1) {
            return new Node(grid[row][column] == 1, true, null, null, null, null);
        }
        Node node = new Node();

        int nextSize = size / 2;
        node.topLeft = divide(grid, row, column, nextSize);
        node.topRight = divide(grid, row, column + nextSize, nextSize);
        node.bottomLeft = divide(grid, row + nextSize, column, nextSize);
        node.bottomRight = divide(grid, row + nextSize, column + nextSize, nextSize);

        // if all values are the same, then merge all of the child node to the root as leaf node
        if (shouldMerge(node)) {
            node.val = node.topRight.val;
            node.isLeaf = true;
            node.topLeft = null;
            node.topRight = null;
            node.bottomLeft = null;
            node.bottomRight = null;
        }
        return node;
    }

    /**
     * Check if all the child nodes:
     * 1. Are leaf nodes.
     * 2. With the same value.
     *
     * @param node given node
     * @return if child nodes need to be merged
     */
    private boolean shouldMerge(Node node) {
        return node.topLeft.isLeaf &&
                node.topRight.isLeaf &&
                node.bottomLeft.isLeaf &&
                node.bottomRight.isLeaf
                && node.topLeft.val == node.topRight.val &&
                node.topRight.val == node.bottomLeft.val &&
                node.bottomLeft.val == node.bottomRight.val;
    }

    public static class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;


        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    }
}
