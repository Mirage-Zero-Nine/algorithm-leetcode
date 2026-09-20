package solutions.bitmanipulation;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests one simultaneous generation of Conway's Game of Life (LeetCode 289).
 *
 * <p>The expected board for generated cases comes from a separate, copy-based
 * implementation. The production solution uses encoded in-place states, so
 * sharing its transition logic in the test would not detect a simultaneous
 * update error.</p>
 */
public class GameOfLife_289Test {

    @Test
    public void testOfficialExampleOne() {
        assertNextGeneration(new int[][]{
                {0, 1, 0},
                {0, 0, 1},
                {1, 1, 1},
                {0, 0, 0}
        }, new int[][]{
                {0, 0, 0},
                {1, 0, 1},
                {0, 1, 1},
                {0, 1, 0}
        });
    }

    @Test
    public void testOfficialExampleTwo() {
        assertNextGeneration(new int[][]{{1, 1}, {1, 0}}, new int[][]{{1, 1}, {1, 1}});
    }

    @Test
    public void testEmptyBoardIsHandledByImplementation() {
        int[][] board = {};
        new GameOfLife_289().gameOfLife(board);
        assertBoardEquals(new int[][]{}, board);
    }

    @Test
    public void testSingleDeadCellRemainsDead() {
        assertNextGeneration(new int[][]{{0}}, new int[][]{{0}});
    }

    @Test
    public void testSingleLiveCellDiesFromUnderpopulation() {
        assertNextGeneration(new int[][]{{1}}, new int[][]{{0}});
    }

    @Test
    public void testAllDeadBoardRemainsDead() {
        assertNextGeneration(new int[][]{{0, 0, 0}, {0, 0, 0}},
                new int[][]{{0, 0, 0}, {0, 0, 0}});
    }

    @Test
    public void testTwoByTwoBlockStillLife() {
        assertNextGeneration(new int[][]{{1, 1}, {1, 1}}, new int[][]{{1, 1}, {1, 1}});
    }

    @Test
    public void testBlockAtCornerStillLife() {
        assertNextGeneration(new int[][]{
                {1, 1, 0},
                {1, 1, 0},
                {0, 0, 0}
        }, new int[][]{
                {1, 1, 0},
                {1, 1, 0},
                {0, 0, 0}
        });
    }

    @Test
    public void testBeehiveStillLife() {
        assertNextGeneration(new int[][]{
                {0, 1, 1, 0},
                {1, 0, 0, 1},
                {0, 1, 1, 0}
        }, new int[][]{
                {0, 1, 1, 0},
                {1, 0, 0, 1},
                {0, 1, 1, 0}
        });
    }

    @Test
    public void testBoatStillLife() {
        assertNextGeneration(new int[][]{
                {1, 1, 0},
                {1, 0, 1},
                {0, 1, 0}
        }, new int[][]{
                {1, 1, 0},
                {1, 0, 1},
                {0, 1, 0}
        });
    }

    @Test
    public void testHorizontalBlinkerBecomesVertical() {
        assertNextGeneration(new int[][]{
                {0, 0, 0},
                {1, 1, 1},
                {0, 0, 0}
        }, new int[][]{
                {0, 1, 0},
                {0, 1, 0},
                {0, 1, 0}
        });
    }

    @Test
    public void testVerticalBlinkerReturnsAfterTwoGenerations() {
        int[][] board = {
                {0, 1, 0},
                {0, 1, 0},
                {0, 1, 0}
        };
        GameOfLife_289 solver = new GameOfLife_289();
        solver.gameOfLife(board);
        solver.gameOfLife(board);
        assertBoardEquals(new int[][]{
                {0, 1, 0},
                {0, 1, 0},
                {0, 1, 0}
        }, board);
    }

    @Test
    public void testToadOscillatorBecomesItsPerpendicularPhase() {
        assertNextGeneration(new int[][]{
                {0, 0, 0, 0},
                {0, 1, 1, 1},
                {1, 1, 1, 0},
                {0, 0, 0, 0}
        }, new int[][]{
                {0, 0, 1, 0},
                {1, 0, 0, 1},
                {1, 0, 0, 1},
                {0, 1, 0, 0}
        });
    }

    @Test
    public void testGliderMovesTowardLowerRight() {
        assertNextGeneration(new int[][]{
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {1, 1, 1, 0},
                {0, 0, 0, 0}
        }, new int[][]{
                {0, 0, 0, 0},
                {1, 0, 1, 0},
                {0, 1, 1, 0},
                {0, 1, 0, 0}
        });
    }

    @Test
    public void testCornerCellUsesOnlyItsThreeNeighbors() {
        int[][] initial = {
                {0, 1, 0},
                {1, 1, 0},
                {0, 0, 0}
        };
        assertNextGeneration(initial, nextGenerationOracle(initial));
    }

    @Test
    public void testEdgeCellExcludesCellsBeyondTheBoard() {
        assertNextGeneration(new int[][]{{1, 1, 1}}, new int[][]{{0, 1, 0}});
    }

    @Test
    public void testDiagonalNeighborsCountForAnInteriorCell() {
        int[][] initial = {
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };
        assertNextGeneration(initial, nextGenerationOracle(initial));
    }

    @Test
    public void testOverpopulationKillsTheCenterAndItsNeighborsUpdateFromOriginalState() {
        assertNextGeneration(new int[][]{
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1}
        }, new int[][]{
                {1, 0, 1},
                {0, 0, 0},
                {1, 0, 1}
        });
    }

    @Test
    public void testSimultaneousBirthsAndDeathsUseTheSameOriginalGeneration() {
        int[][] initial = {
                {1, 1, 0, 0},
                {1, 0, 1, 0},
                {0, 1, 1, 0},
                {0, 0, 0, 0}
        };
        assertNextGeneration(initial, nextGenerationOracle(initial));
    }

    @Test
    public void testSingleRowBoundary() {
        assertNextGeneration(new int[][]{{1, 1, 1, 1, 1}},
                new int[][]{{0, 1, 1, 1, 0}});
    }

    @Test
    public void testSingleColumnBoundary() {
        assertNextGeneration(new int[][]{{1}, {1}, {1}, {1}, {1}},
                new int[][]{{0}, {1}, {1}, {1}, {0}});
    }

    @Test
    public void testRectangularBoardUsesEachRowAndColumnCorrectly() {
        int[][] initial = {
                {1, 0, 0, 1, 0},
                {0, 1, 1, 0, 0}
        };
        assertNextGeneration(initial, nextGenerationOracle(initial));
    }

    @Test
    public void testAllThreeByThreeBoardsAgainstIndependentSimultaneousOracle() {
        for (int mask = 0; mask < 1 << 9; mask++) {
            int[][] initial = new int[3][3];
            for (int index = 0; index < 9; index++) {
                initial[index / 3][index % 3] = (mask >>> index) & 1;
            }
            assertNextGeneration(initial, nextGenerationOracle(initial));
        }
    }

    @Test
    public void testSeededRectangularBoardsAgainstIndependentOracle() {
        Random random = new Random(289_2026L);
        for (int caseNumber = 0; caseNumber < 120; caseNumber++) {
            int rows = 1 + random.nextInt(8);
            int columns = 1 + random.nextInt(8);
            int[][] board = new int[rows][columns];
            for (int row = 0; row < rows; row++) {
                for (int column = 0; column < columns; column++) {
                    board[row][column] = random.nextInt(4) == 0 ? 1 : 0;
                }
            }
            assertNextGeneration(board, nextGenerationOracle(board));
        }
    }

    @Test
    public void testMaximumLegalTwentyFiveByTwentyFiveBoardAgainstIndependentOracle() {
        int[][] board = new int[25][25];
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                board[row][column] = (row * 31 + column * 17 + row * column) % 7 < 3 ? 1 : 0;
            }
        }
        assertNextGeneration(board, nextGenerationOracle(board));
    }

    @Test
    public void testRepeatedCallsOnOneInstanceHaveNoCrossBoardState() {
        GameOfLife_289 solver = new GameOfLife_289();
        int[][] first = {{1, 1}, {1, 1}};
        int[][] second = {
                {0, 1, 0},
                {0, 1, 0},
                {0, 1, 0}
        };
        solver.gameOfLife(first);
        solver.gameOfLife(second);
        assertBoardEquals(new int[][]{{1, 1}, {1, 1}}, first);
        assertBoardEquals(nextGenerationOracle(new int[][]{
                {0, 1, 0},
                {0, 1, 0},
                {0, 1, 0}
        }), second);
    }

    @Test
    public void testBoardIsUpdatedInPlaceAndEveryOutputCellIsBinary() {
        int[][] board = {
                {1, 0, 1, 0},
                {0, 1, 0, 1},
                {1, 1, 0, 0}
        };
        int[][] sameReference = board;
        new GameOfLife_289().gameOfLife(board);
        assertSame(sameReference, board);
        for (int[] row : board) {
            for (int cell : row) {
                assertTrue(cell == 0 || cell == 1, "output cells must be 0 or 1");
            }
        }
    }

    private static void assertNextGeneration(int[][] initial, int[][] expected) {
        int[][] board = copyBoard(initial);
        new GameOfLife_289().gameOfLife(board);
        assertBoardEquals(expected, board);
    }

    /** Independent copy-based oracle that reads only the prior generation. */
    private static int[][] nextGenerationOracle(int[][] board) {
        if (board.length == 0) {
            return new int[0][];
        }
        int rows = board.length;
        int columns = board[0].length;
        int[][] next = new int[rows][columns];
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                int liveNeighbors = 0;
                for (int rowDelta = -1; rowDelta <= 1; rowDelta++) {
                    for (int columnDelta = -1; columnDelta <= 1; columnDelta++) {
                        if (rowDelta == 0 && columnDelta == 0) {
                            continue;
                        }
                        int neighborRow = row + rowDelta;
                        int neighborColumn = column + columnDelta;
                        if (neighborRow >= 0 && neighborRow < rows
                                && neighborColumn >= 0 && neighborColumn < columns) {
                            liveNeighbors += board[neighborRow][neighborColumn];
                        }
                    }
                }
                next[row][column] = liveNeighbors == 3
                        || (board[row][column] == 1 && liveNeighbors == 2) ? 1 : 0;
            }
        }
        return next;
    }

    private static int[][] copyBoard(int[][] board) {
        int[][] copy = new int[board.length][];
        for (int row = 0; row < board.length; row++) {
            copy[row] = board[row].clone();
        }
        return copy;
    }

    private static void assertBoardEquals(int[][] expected, int[][] actual) {
        assertEquals(expected.length, actual.length, "row count");
        for (int row = 0; row < expected.length; row++) {
            assertArrayEquals(expected[row], actual[row], "row " + row);
        }
    }
}
