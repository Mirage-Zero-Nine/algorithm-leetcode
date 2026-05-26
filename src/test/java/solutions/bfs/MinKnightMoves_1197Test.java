package solutions.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MinKnightMoves_1197Test {

    private final MinKnightMoves_1197 test = new MinKnightMoves_1197();

    @Test
    public void testHappyCases() {
        assertEquals(1, test.minKnightMoves(2, 1));
        assertEquals(4, test.minKnightMoves(5, 5));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(0, test.minKnightMoves(0, 0));
        assertEquals(4, test.minKnightMoves(1, 1));
    }

    @Test
    public void testLargeCase() {
        assertEquals(2, test.minKnightMoves(3, 3));
    }

    @Test
    public void testSpecialCaseOneZero() {
        assertEquals(3, test.minKnightMoves(1, 0));
        assertEquals(3, test.formula(1, 0));
    }

    @Test
    public void testSpecialCaseTwoTwo() {
        assertEquals(4, test.minKnightMoves(2, 2));
        assertEquals(4, test.formula(2, 2));
    }

    @Test
    public void testSymmetryAcrossAxes() {
        int a = test.minKnightMoves(4, 7);
        int b = test.minKnightMoves(-4, 7);
        int c = test.minKnightMoves(4, -7);
        int d = test.minKnightMoves(-4, -7);
        assertEquals(a, b);
        assertEquals(a, c);
        assertEquals(a, d);
    }

    @Test
    public void testSwapSymmetry() {
        assertEquals(test.minKnightMoves(8, 3), test.minKnightMoves(3, 8));
    }

    @Test
    public void testNearOriginCases() {
        assertEquals(3, test.minKnightMoves(0, 1));
        assertEquals(1, test.minKnightMoves(1, 2));
        assertEquals(2, test.minKnightMoves(2, 0));
    }

    @Test
    public void testFormulaMatchesBfsForTypicalPoints() {
        assertFormulaMatches(3, 0);
        assertFormulaMatches(4, 5);
        assertFormulaMatches(10, 7);
        assertFormulaMatches(12, 12);
    }

    @Test
    public void testGiantCoordinate() {
        int bfs = test.minKnightMoves(60, 60);
        int formula = test.formula(60, 60);
        assertEquals(formula, bfs);
    }

    private void assertFormulaMatches(int x, int y) {
        assertEquals(test.minKnightMoves(x, y), test.formula(x, y));
    }
}
