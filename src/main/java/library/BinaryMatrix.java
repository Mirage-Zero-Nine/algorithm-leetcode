package library;

import java.util.List;

/**
 * BinaryMatrix class provide the access to its row and column number, and the API for value in given cell.
 *
 * @author BorisMirage
 * Time: 2020/05/23 13:27
 * Created with IntelliJ IDEA
 */

public interface BinaryMatrix {
    /**
     * Get the value in cell.
     *
     * @param row row #
     * @param col column #
     * @return value in given cell
     */
    int get(int row, int col);

    /**
     * Return a list contains the row and column number of the matrix.
     *
     * @return a list contains the row and column number of the matrix
     */
    List<Integer> dimensions();
}
