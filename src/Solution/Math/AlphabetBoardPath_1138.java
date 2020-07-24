package Solution.Math;

/**
 * On an alphabet board, we start at position (0, 0), corresponding to character board[0][0].
 * Here, board = ["abcde", "fghij", "klmno", "pqrst", "uvwxy", "z"].
 * We may make the following moves:
 * 'U' moves our position up one row, if the square exists;
 * 'D' moves our position down one row, if the square exists;
 * 'L' moves our position left one column, if the square exists;
 * 'R' moves our position right one column, if the square exists;
 * '!' adds the character board[r][c] at our current position (r, c) to the answer.
 * Return a sequence of moves that makes our answer equal to target in the minimum number of moves.
 * You may return any path that does so.
 *
 * @author BorisMirage
 * Time: 2019/07/27 21:45
 * Created with IntelliJ IDEA
 */

public class AlphabetBoardPath_1138 {
    /**
     * Math problem. Each time calculate the manhattan distance of two chars in board and finally return string.
     * Note that "z" is the only element at last row, hence to avoid overflow, any char should start to move at left.
     *
     * @param target target string
     * @return sequence of moves that makes our answer equal to target in the minimum number of moves
     */
    public String alphabetBoardPath(String target) {
        StringBuilder out = new StringBuilder();
        int x0 = 0, y0 = 0;

        for (int i = 0; i < target.length(); i++) {

            int cur = target.charAt(i) - 'a';
            int x1 = cur % 5, y1 = cur / 5;
            if (y1 < y0) {
                for (int j = 0; j < (y0 - y1); j++) {
                    out.append("L");
                }
            }
            if (x1 < x0) {
                for (int j = 0; j < (x0 - x1); j++) {
                    out.append("U");
                }
            }
            if (x1 > x0) {
                for (int j = 0; j < (x1 - x0); j++) {
                    out.append("D");
                }
            }
            if (y1 > y0) {
                for (int j = 0; j < (y1 - y0); j++) {
                    out.append("R");
                }
            }
            x0 = x1;
            y0 = y1;
            out.append("!");
        }
        return out.toString();
    }
}
