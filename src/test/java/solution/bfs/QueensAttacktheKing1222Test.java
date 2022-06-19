package solution.bfs;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author BorisMirage
 * Time: 2022/06/18 21:28
 * Created with IntelliJ IDEA
 */

public class QueensAttacktheKing1222Test {

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
        assertIterableEquals(expected, test.queensAttacktheKing(queens, king));
    }
}