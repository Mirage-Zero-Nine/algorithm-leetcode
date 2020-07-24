package Solution.BFS;

import java.util.*;

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

    public static void main(String[] args) {
        int[][] queens = new int[][]{{5, 6}, {7, 7}, {2, 1}, {0, 7}, {1, 6}, {5, 1}, {3, 7}, {0, 3}, {4, 0}, {1, 2}, {6, 3}, {5, 0}, {0, 4}, {2, 2}, {1, 1}, {6, 4}, {5, 4}, {0, 0}, {2, 6}, {4, 5}, {5, 2}, {1, 4}, {7, 5}, {2, 3}, {0, 5}, {4, 2}, {1, 0}, {2, 7}, {0, 1}, {4, 6}, {6, 1}, {0, 6}, {4, 3}, {1, 7}};
        int[] king = new int[]{3, 4};

        System.out.println(new QueensAttacktheKing_1222().queensAttacktheKing(queens, king));
    }
}
