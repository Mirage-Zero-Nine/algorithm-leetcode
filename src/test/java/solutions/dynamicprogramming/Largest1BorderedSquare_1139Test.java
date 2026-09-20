package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import java.util.stream.Stream;
import org.junit.jupiter.api.DynamicTest;

public class Largest1BorderedSquare_1139Test {

    private final Largest1BorderedSquare_1139 test = new Largest1BorderedSquare_1139();

    @Test
    public void testHappyCases() {
        assertEquals(9, test.largest1BorderedSquare(new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}}));
        assertEquals(1, test.largest1BorderedSquare(new int[][]{{1, 1, 0, 0}}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.largest1BorderedSquare(new int[][]{{0}}));
        assertEquals(1, test.largest1BorderedSquare(new int[][]{{1}}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(1, test.largest1BorderedSquare(new int[][]{{1, 1, 1, 1}, {1, 0, 0, 1}, {1, 1, 1, 1}}));
    }

    @Test
    public void testAllZeros() {
        assertEquals(0, test.largest1BorderedSquare(new int[][]{{0, 0}, {0, 0}}));
    }

    @Test
    public void testAllOnes2x2() {
        assertEquals(4, test.largest1BorderedSquare(new int[][]{{1, 1}, {1, 1}}));
    }

    @Test
    public void test4x4AllOnes() {
        assertEquals(16, test.largest1BorderedSquare(new int[][]{{1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}}));
    }

    @Test
    public void testSingleRow() {
        assertEquals(1, test.largest1BorderedSquare(new int[][]{{1, 0, 1, 1, 1}}));
    }

    @Test
    public void testSingleColumn() {
        assertEquals(1, test.largest1BorderedSquare(new int[][]{{1}, {0}, {1}, {1}}));
    }

    @Test
    public void testNoSquarePossible() {
        assertEquals(1, test.largest1BorderedSquare(new int[][]{{1, 0, 1}, {0, 1, 0}, {1, 0, 1}}));
    }

    @Test
    public void testGiantGrid() {
        int n = 50;
        int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                grid[i][j] = 1;
        assertEquals(n * n, test.largest1BorderedSquare(grid));
    }

    @org.junit.jupiter.api.TestFactory
    public Stream<DynamicTest> additionalDistinctGrids() {
        return Stream.of(
                DynamicTest.dynamicTest("single zero", () -> assertEquals(0, test.largest1BorderedSquare(new int[][]{{0}}))),
                DynamicTest.dynamicTest("single one", () -> assertEquals(1, test.largest1BorderedSquare(new int[][]{{1}}))),
                DynamicTest.dynamicTest("horizontal pair", () -> assertEquals(1, test.largest1BorderedSquare(new int[][]{{1, 1}}))),
                DynamicTest.dynamicTest("vertical pair", () -> assertEquals(1, test.largest1BorderedSquare(new int[][]{{1}, {1}}))),
                DynamicTest.dynamicTest("border only square", () -> assertEquals(9, test.largest1BorderedSquare(new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}}))),
                DynamicTest.dynamicTest("diagonal ones", () -> assertEquals(1, test.largest1BorderedSquare(new int[][]{{1, 0}, {0, 1}}))),
                DynamicTest.dynamicTest("top-left candidate", () -> assertEquals(4, test.largest1BorderedSquare(new int[][]{{1, 1}, {1, 1}, {0, 0}}))),
                DynamicTest.dynamicTest("bottom-right candidate", () -> assertEquals(4, test.largest1BorderedSquare(new int[][]{{0, 0}, {1, 1}, {1, 1}}))),
                DynamicTest.dynamicTest("three by two all ones", () -> assertEquals(4, test.largest1BorderedSquare(new int[][]{{1, 1}, {1, 1}, {1, 1}}))),
                DynamicTest.dynamicTest("cross cannot form square", () -> assertEquals(1, test.largest1BorderedSquare(new int[][]{{0, 1, 0}, {1, 1, 1}, {0, 1, 0}}))),
                DynamicTest.dynamicTest("two separated squares", () -> assertEquals(4, test.largest1BorderedSquare(new int[][]{{1, 1, 0, 1, 1}, {1, 1, 0, 1, 1}}))));
    }
}
