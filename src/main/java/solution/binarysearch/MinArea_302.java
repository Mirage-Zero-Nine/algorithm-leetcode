package solution.binarysearch;

/**
 * An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel.
 * The black pixels are connected, i.e., there is only one black region.
 * Pixels are connected horizontally and vertically.
 * Given the (x, y) of one black pixels, return the area of the smallest rectangle that encloses all black pixels.
 *
 * @author BorisMirage
 * Time: 2019/06/23 15:55
 * Created with IntelliJ IDEA
 */

public class MinArea_302 {
    /**
     * Binary search to find the bottom, top, left right bound of rectangle based on given coord.
     * top = search row [0...x], find first row contain 1,
     * bottom = search row[x+1, row], find first row with all 0
     * left = search col[0...y], find first column contain 1,
     * right = search col[y+1, col], find first col with all 0
     *
     * @param image given 2D matrix
     * @param x     row of one of the black pixels
     * @param y     column of one of the black pixels
     * @return area of the smallest (axis-aligned) rectangle that encloses all black pixels
     */
    public int minArea(char[][] image, int x, int y) {
        int left = searchBoundary(y, image, true, true);
        int right = searchBoundary(y, image, false, true);
        int top = searchBoundary(x, image, true, false);
        int bottom = searchBoundary(x, image, false, false);
        return (right - left + 1) * (bottom - top + 1);
    }

    /**
     * Search boundary.
     *
     * @param j          given row or column
     * @param g          given 2D matrix
     * @param direction  true -> search small boundary (search 0), false -> search big boundary (search 1)
     * @param horizontal true -> search horizontal boundary, false -> search vertical boundary
     * @return boundary of given input
     */
    private int searchBoundary(int j, char[][] g, boolean direction, boolean horizontal) {
        int n = horizontal ? g[0].length : g.length;       // length limit of given coord
        int left = direction ? 0 : j, right = direction ? j : n - 1;        // binary search start boundary
        int a = direction ? 0 : 1, b = 1 - a;     // find 0 or find 1

        while (left < right) {

            int mid = (left + right + a) / 2;
            if (checkLine(mid, horizontal, g) == direction) {
                right = mid - a;
            } else {
                left = mid + b;
            }
        }
        return right;
    }

    /**
     * Check each row or column to find 0 or 1, depend on given condition.
     *
     * @param j           given coord
     * @param checkColumn true -> checking column, false -> checking row
     * @param g           given 2D matrix
     * @return return true if found '1'
     */
    private boolean checkLine(int j, boolean checkColumn, char[][] g) {

        for (int n = checkColumn ? g.length : g[0].length, i = 0; i < n; i++) {
            if (checkColumn && g[i][j] == '1' || !checkColumn && g[j][i] == '1') {
                return true;
            }
        }
        return false;
    }
}
