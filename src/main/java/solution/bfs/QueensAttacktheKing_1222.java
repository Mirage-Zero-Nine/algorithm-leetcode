package solution.bfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * On an 8x8 chessboard, there can be multiple Black Queens and one White King.
 * Given an array of integer coordinates queens that represents the positions of the Queens, and the White King.
 * Return the coordinates of all the queens (in any order) that can attack the King.
 *
 * @author BorisMirage
 * Time: 2019/10/12 20:11
 * Created with IntelliJ IDEA
 */

public class QueensAttacktheKing_1222 {
    /**
     * Search 8 directions from the king. At each direction, the first meet queen will be added to final result.
     * After first queen is met, this direction will break.
     *
     * @param queens given queens coord
     * @param king   given king coord
     * @return coordinates of all the queens (in any order) that can attack the King
     */
    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {

        int[] d1 = new int[]{-1, 0, 1};
        int[] d2 = new int[]{-1, 0, 1};
        List<List<Integer>> out = new LinkedList<>();

        boolean[][] q = new boolean[8][8];      // locate all queens

        for (int[] queen : queens) {
            q[queen[0]][queen[1]] = true;
        }

        for (int x : d1) {
            for (int y : d2) {
                if (x == 0 && y == 0) {
                    continue;
                }

                int a = king[0], b = king[1];
                while (a + x >= 0 && a + x < 8 && b + y >= 0 && b + y < 8) {
                    a += x;
                    b += y;
                    if (q[a][b]) {
                        out.add(Arrays.asList(a, b));
                        break;
                    }

                }

            }
        }
        return out;
    }
}
