package Solution.DataStructure;


/**
 * Given a 2D matrix matrix.
 * Find the sum of the elements inside the rectangle defined by its upper left corner and lower right corner.
 *
 * @author BorisMirage
 * Time: 2019/06/15 10:36
 * Created with IntelliJ IDEA
 */

class NumMatrix_308 {

    private SegmentTree2D root;             // root node
    private int ROW = 0, COLUMN = 0;        // total row and column in matrix
    private int[][] matrix;                 // given matrix

    /**
     * Use a 2D segment tree to create the range sum.
     * Update unit value by traverse to bottom of tree.
     * Find range sum by collect all range sum that is within given range.
     *
     * @param matrix given matrix
     */
    public NumMatrix_308(int[][] matrix) {

        /* Corner case */
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }

        this.ROW = matrix.length;
        this.COLUMN = matrix[0].length;
        this.matrix = matrix;

        root = build(0, 0, ROW - 1, COLUMN - 1);
    }

    /**
     * Build segment tree.
     *
     * @param row1 row index of first coordinate
     * @param col1 column index of first coordinate
     * @param row2 row index of second coordinate
     * @param col2 column index of second coordinate
     * @return root of 2D segment tree
     */
    private SegmentTree2D build(int row1, int col1, int row2, int col2) {

        /* End point */
        if (row1 > row2 || col1 > col2) {
            return null;
        }

        SegmentTree2D n = new SegmentTree2D(row1, col1, row2, col2);

        if (row1 == row2 && col1 == col2) {
            n.sum = matrix[row1][col1];     // unit point
            return n;
        }

        int midRow = row1 + (row2 - row1) / 2;
        int midCol = col1 + (col2 - col1) / 2;

        n.n11 = build(row1, col1, midRow, midCol);                      // top left
        n.n12 = build(row1, midCol + 1, midRow, col2);             // top right
        n.n21 = build(midRow + 1, col1, row2, midCol);            // bottom left
        n.n22 = build(midRow + 1, midCol + 1, row2, col2);   // bottom right

        n.sum += n.n11 == null ? 0 : n.n11.sum;
        n.sum += n.n12 == null ? 0 : n.n12.sum;
        n.sum += n.n21 == null ? 0 : n.n21.sum;
        n.sum += n.n22 == null ? 0 : n.n22.sum;

        return n;
    }

    /**
     * @param row row index of update coordinate
     * @param col column # of first coordinate
     * @param val update value
     */
    public void update(int row, int col, int val) {
        if (ROW == 0 || COLUMN == 0) return;
        updateNode(root, row, col, val - matrix[row][col]);
        matrix[row][col] = val;
    }

    /**
     * @param n
     * @param row row index of update coordinate
     * @param col column index of first coordinate
     * @param val update value
     */
    private void updateNode(SegmentTree2D n, int row, int col, int val) {

        if (n.row1 == n.row2 && n.col1 == n.col2) {
            n.sum += val;
            return;
        }

        int midRow = n.row1 + (n.row2 - n.row1) / 2;
        int midCol = n.col1 + (n.col2 - n.col1) / 2;

        if (row <= midRow) {                            // top
            if (col <= midCol) {
                updateNode(n.n11, row, col, val);       // top-left
            } else {
                updateNode(n.n12, row, col, val);       // top-right
            }
        } else {                                        // bottom
            if (col <= midCol) {
                updateNode(n.n21, row, col, val);       // bottom-left
            } else {
                updateNode(n.n22, row, col, val);       // bottom-right
            }
        }

        n.sum += val;
    }

    /**
     * @param row1 row index of first coordinate
     * @param col1 column index of first coordinate
     * @param row2 row index of second coordinate
     * @param col2 column index of second coordinate
     * @return range sum
     */
    public int sumRegion(int row1, int col1, int row2, int col2) {

        return findRangeSum(root, row1, col1, row2, col2);
    }

    /**
     * @param n    root node
     * @param row1 row index of first coordinate
     * @param col1 column index of first coordinate
     * @param row2 row index of second coordinate
     * @param col2 column index of second coordinate
     * @return sum of array in given range
     */
    private int findRangeSum(SegmentTree2D n, int row1, int col1, int row2, int col2) {

        if (n == null) {
            return 0;
        }

        if (n.row1 >= row1 && n.row2 <= row2 && n.col1 >= col1 && n.col2 <= col2) {
            return n.sum;
        }

        int res = 0;        // return sum
        if (n.n11 != null && row1 <= n.n11.row2 && col1 <= n.n11.col2) {
            res += findRangeSum(n.n11, row1, col1, row2, col2);
        }
        if (n.n12 != null && row1 <= n.n12.row2 && col2 >= n.n12.col1) {
            res += findRangeSum(n.n12, row1, col1, row2, col2);
        }
        if (n.n21 != null && row2 >= n.n21.row1 && col1 <= n.n21.col2) {
            res += findRangeSum(n.n21, row1, col1, row2, col2);
        }
        if (n.n22 != null && row2 >= n.n22.row1 && col2 >= n.n22.col1) {
            res += findRangeSum(n.n22, row1, col1, row2, col2);
        }

        return res;
    }

    /**
     * 2D Segment tree.
     * One node has four children.
     */
    class SegmentTree2D {

        SegmentTree2D n11;      // top left
        SegmentTree2D n12;      // top right
        SegmentTree2D n21;      // bottom left
        SegmentTree2D n22;      // bottom right

        int sum;                        // sum in given coordinate
        int row1, col1, row2, col2;     // (row1, col1), (row2, col2)

        /**
         * @param row1 row index of first coordinate
         * @param col1 column index of first coordinate
         * @param row2 row index of second coordinate
         * @param col2 column index of second coordinate
         */
        public SegmentTree2D(int row1, int col1, int row2, int col2) {
            this.row1 = row1;
            this.col1 = col1;
            this.row2 = row2;
            this.col2 = col2;
        }
    }
}