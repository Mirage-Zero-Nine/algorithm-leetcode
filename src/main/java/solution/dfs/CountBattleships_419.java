package solution.dfs;

/**
 * Given an m x n matrix board where each cell is a battleship 'X' or empty '.', return the number of the battleships on board.
 * Battleships can only be placed horizontally or vertically on board.
 * In other words, they can only be made of the shape 1 x k (1 row, k columns) or k x 1 (k rows, 1 column), where k can be of any size.
 * At least one horizontal or vertical cell separates between two battleships (i.e., there are no adjacent battleships).
 *
 * @author BorisMirage
 * Time: 2025/05/07 10:57
 * Created with IntelliJ IDEA
 */

public class CountBattleships_419 {

    /**
     * DFS to traverse and mark all cells of a ship as visited, ensuring that each ship is only counted once.
     *
     * @param board The 2D character array representing the board, where 'X' indicates a part of a battleship.
     * @return The total number of battleships on the board.
     */
    public int countBattleships(char[][] board) {

        if (board == null || board.length == 0 || board[0].length == 0) {
            return 0;
        }

        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'X') {
                    dfs(board, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Performs a depth-first search (DFS) to mark visited cells on the board.
     * This method is used to traverse and mark connected 'X' cells starting from the specified position (i, j).
     * During traversal, each visited cell is marked as '.' to indicate it has been processed.
     *
     * @param board The 2D character array representing the board.
     * @param i     The row index of the current cell.
     * @param j     The column index of the current cell.
     */
    private void dfs(char[][] board, int i, int j) {
        board[i][j] = '.';
        int[] direction = new int[]{0, -1, 0, 1, 0};
        for (int d = 0; d < 4; d++) {
            int nx = i + direction[d], ny = j + direction[d + 1];
            if (nx >= 0 && nx < board.length && ny >= 0 && ny < board[0].length && board[nx][ny] == 'X') {
                dfs(board, nx, ny);
            }
        }
    }

    /**
     * Another approach counts only the top-leftmost cell of each battleship, ensuring each ship is counted exactly once.
     * From the description, the ship won't be adjacent to any other ship and it can only be placed horizontally or vertically.
     * Hence, the current cell is the first cell of a ship iff the cell above and the cell to the left is not 'X'.
     * If both conditions are met, the current cell is considered the start of a new ship.
     *
     * @param board The 2D character array representing the board, where 'X' indicates a part of a battleship.
     * @return The total number of battleships on the board.
     */
    public int countBattleshipsQuick(char[][] board) {

        if (board == null || board.length == 0 || board[0].length == 0) {
            return 0;
        }

        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'X' && (i == 0 || board[i - 1][j] != 'X') && (j == 0 || board[i][j - 1] != 'X')) {
                    count++;
                }
            }
        }
        return count;
    }
}
