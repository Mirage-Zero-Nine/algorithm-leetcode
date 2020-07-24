package Solution.DataStructure;

/**
 * Design and implement an iterator to flatten a 2d vector.
 * It should support the following operations: next and hasNext.
 *
 * @author BorisMirage
 * Time: 2019/07/03 12:56
 * Created with IntelliJ IDEA
 */

public class Vector2D_251 {

    private int[][] vector;
    private int row;
    private int column;

    /**
     * Use two pointers point at row and column.
     *
     * @param v given 2D array
     */
    public Vector2D_251(int[][] v) {
        this.vector = v;
        this.row = 0;
        this.column = 0;
    }

    /**
     * @return next element in vector
     */
    public int next() {
        if (hasNext()) {
            if (this.column < this.vector[row].length) {
                this.column++;
                return this.vector[row][column - 1];
            }
        }
        return -1;
    }

    /**
     * @return check if next element exist
     */
    public boolean hasNext() {

        while (row < this.vector.length) {
            if (column < vector[row].length) {
                return true;
            } else {
                row++;
                column = 0;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Vector2D_251 test = new Vector2D_251(new int[][]{{1, 2, 3}, {4}, {5}});
        System.out.println(test.next());
        System.out.println(test.next());
        System.out.println(test.next());
        System.out.println(test.hasNext());
        System.out.println(test.next());
        System.out.println(test.next());
        System.out.println(test.hasNext());
    }
}
