package solution.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author BorisMirage
 * Time: 2022/06/18 21:28
 * Created with IntelliJ IDEA
 */

public class QueensAttacktheKing_1222Test {

    private final QueensAttacktheKing_1222 test = new QueensAttacktheKing_1222();

    @Test
    public void test() {
        List<List<Integer>> expected = Lists.newArrayList(
                Lists.newArrayList(2, 3),
                Lists.newArrayList(1, 4),
                Lists.newArrayList(1, 6),
                Lists.newArrayList(3, 7),
                Lists.newArrayList(4, 3),
                Lists.newArrayList(5, 4),
                Lists.newArrayList(4, 5)
        );
        int[][] queens = new int[][]{{5, 6}, {7, 7}, {2, 1}, {0, 7}, {1, 6}, {5, 1}, {3, 7}, {0, 3}, {4, 0}, {1, 2}, {6, 3}, {5, 0}, {0, 4}, {2, 2}, {1, 1}, {6, 4}, {5, 4}, {0, 0}, {2, 6}, {4, 5}, {5, 2}, {1, 4}, {7, 5}, {2, 3}, {0, 5}, {4, 2}, {1, 0}, {2, 7}, {0, 1}, {4, 6}, {6, 1}, {0, 6}, {4, 3}, {1, 7}};
        int[] king = new int[]{3, 4};
        assertEquals(expected, test.queensAttacktheKing(queens, king));
    }

    @Test
    public void testDirectionalCoverage() {
        assertEquals(List.of(List.of(0, 1)),
                test.queensAttacktheKing(new int[][]{{0, 1}}, new int[]{0, 0}));
        assertEquals(List.of(List.of(1, 1)),
                test.queensAttacktheKing(new int[][]{{1, 1}}, new int[]{0, 0}));
        assertEquals(List.of(List.of(1, 0)),
                test.queensAttacktheKing(new int[][]{{1, 0}}, new int[]{0, 0}));
        assertEquals(List.of(List.of(4, 3)),
                test.queensAttacktheKing(new int[][]{{4, 3}, {5, 3}}, new int[]{3, 3}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(List.of(),
                test.queensAttacktheKing(new int[][]{{0, 2}, {2, 5}, {6, 1}}, new int[]{4, 4}));
        assertEquals(List.of(List.of(2, 2), List.of(5, 5)),
                test.queensAttacktheKing(new int[][]{{1, 1}, {2, 2}, {5, 5}}, new int[]{3, 3}));
        assertEquals(List.of(List.of(6, 0), List.of(7, 1)),
                test.queensAttacktheKing(new int[][]{{6, 0}, {7, 1}}, new int[]{7, 0}));
    }

    @Test
    public void testGiantBoardCase() {
        int[][] queens = new int[][]{
                {0, 0}, {0, 3}, {0, 7}, {1, 1}, {1, 3}, {1, 5}, {2, 3}, {2, 6},
                {3, 0}, {3, 2}, {3, 7}, {4, 1}, {4, 5}, {5, 3}, {5, 5}, {6, 0},
                {6, 3}, {6, 6}, {7, 3}, {7, 7}
        };
        List<List<Integer>> expected = List.of(
                Lists.newArrayList(1, 1),
                Lists.newArrayList(2, 3),
                Lists.newArrayList(1, 5),
                Lists.newArrayList(3, 2),
                Lists.newArrayList(3, 7),
                Lists.newArrayList(6, 0),
                Lists.newArrayList(5, 3),
                Lists.newArrayList(5, 5)
        );
        assertEquals(expected, test.queensAttacktheKing(queens, new int[]{3, 3}));
    }

    @Test
    public void testNoQueens() {
        assertEquals(List.of(), test.queensAttacktheKing(new int[][]{}, new int[]{4, 4}));
    }

    @Test
    public void testKingInCorner() {
        // King at (0,0), queens at all 3 reachable directions
        List<List<Integer>> result = test.queensAttacktheKing(
                new int[][]{{0, 1}, {1, 0}, {1, 1}}, new int[]{0, 0});
        assertEquals(3, result.size());
    }

    @Test
    public void testKingAtEdge() {
        // King at (0,4), queens surrounding
        List<List<Integer>> result = test.queensAttacktheKing(
                new int[][]{{0, 5}, {0, 3}, {1, 4}, {1, 5}, {1, 3}}, new int[]{0, 4});
        assertEquals(5, result.size());
    }

    @Test
    public void testQueenBlocksAnother() {
        // Two queens in same direction, only closest attacks
        assertEquals(List.of(List.of(2, 3)),
                test.queensAttacktheKing(new int[][]{{2, 3}, {1, 3}}, new int[]{3, 3}));
    }

    @Test
    public void testAllEightDirections() {
        // Place queens in all 8 directions from king at (4,4)
        int[][] queens = new int[][]{{3, 3}, {3, 4}, {3, 5}, {4, 3}, {4, 5}, {5, 3}, {5, 4}, {5, 5}};
        List<List<Integer>> result = test.queensAttacktheKing(queens, new int[]{4, 4});
        assertEquals(8, result.size());
    }

    @Test
    public void testQueenFarAway() {
        // Queen at far corner can still attack diagonally
        assertEquals(List.of(List.of(7, 7)),
                test.queensAttacktheKing(new int[][]{{7, 7}}, new int[]{0, 0}));
    }
}
