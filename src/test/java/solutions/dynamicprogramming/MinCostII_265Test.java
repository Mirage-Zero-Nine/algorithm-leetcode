package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class MinCostII_265Test {

    private final MinCostII_265 test = new MinCostII_265();

    @Test
    public void testHappyCases() {
        assertEquals(5, test.minCostII(new int[][]{{1, 5, 3}, {2, 9, 4}}));
        assertEquals(2, test.minCostII(new int[][]{{1, 3}, {2, 1}}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.minCostII(new int[][]{}));
        assertEquals(1, test.minCostII(new int[][]{{1, 2, 3}}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(13, test.minCostII(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));
    }

    @Test
    public void testSingleColor() {
        assertEquals(5, test.minCostII(new int[][]{{5}}));
    }

    @Test
    public void testTwoHousesTwoColors() {
        assertEquals(2, test.minCostII(new int[][]{{1, 2}, {3, 1}}));
    }

    @Test
    public void testThreeHousesFourColors() {
        assertEquals(4, test.minCostII(new int[][]{{1, 2, 3, 4}, {1, 2, 3, 4}, {1, 2, 3, 4}}));
    }

    @Test
    public void testAllSameCost() {
        assertEquals(6, test.minCostII(new int[][]{{2, 2, 2}, {2, 2, 2}, {2, 2, 2}}));
    }

    @Test
    public void testForcedAlternation() {
        assertEquals(3, test.minCostII(new int[][]{{1, 100}, {100, 1}, {1, 100}}));
    }

    @Test
    public void testSingleHouseManyColors() {
        assertEquals(1, test.minCostII(new int[][]{{5, 3, 1, 2, 4}}));
    }

    @Test
    public void testGiantCase() {
        int n = 100;
        int k = 50;
        int[][] costs = new int[n][k];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k; j++) {
                costs[i][j] = (i + j) % k + 1;
            }
        }
        assertEquals(reference(costs), test.minCostII(costs));
        assertEquals(reference(costs), test.naiveDP(costs));
    }

    private int reference(int[][] costs) {
        if (costs.length == 0) return 0;
        int[][] dp = new int[costs.length][costs[0].length];
        dp[0] = costs[0].clone();
        for (int i = 1; i < costs.length; i++) {
            for (int color = 0; color < costs[i].length; color++) {
                dp[i][color] = Integer.MAX_VALUE;
                for (int previous = 0; previous < costs[i].length; previous++) {
                    if (previous != color) {
                        dp[i][color] = Math.min(dp[i][color], dp[i - 1][previous] + costs[i][color]);
                    }
                }
            }
        }
        return java.util.Arrays.stream(dp[dp.length - 1]).min().orElse(0);
    }

    @ParameterizedTest(name = "multi-color costs {0}")
    @CsvSource({"'1;2;3|3;2;1',2", "'5;1;5|1;5;1',2", "'2;8;4|7;3;9|6;1;5',10", "'9;1;9|9;9;1|1;9;9',3", "'4;4;4|4;4;4',8", "'1;10;10|10;10;1|1;10;10',3", "'7;2;9|8;6;3',5", "'3;1;8|2;9;4|5;2;6',5", "'10;20;30|30;20;10|20;10;30',30", "'6;5;4|4;5;6|6;4;5|5;6;4',16"})
    public void testAdditionalColorTransitions(String encoded, int expected) {
        assertEquals(expected, test.minCostII(parse(encoded)));
    }

    private static int[][] parse(String encoded) {
        String[] rows = encoded.split("\\|"); int[][] result = new int[rows.length][];
        for (int i = 0; i < rows.length; i++) { String[] values = rows[i].split(";"); result[i] = new int[values.length]; for (int j = 0; j < values.length; j++) result[i][j] = Integer.parseInt(values[j]); }
        return result;
    }
}
