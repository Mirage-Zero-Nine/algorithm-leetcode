package solutions.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PredictTheWinner_486Test {

    private final PredictTheWinner_486 test = new PredictTheWinner_486();

    @Test
    public void testHappyCases() {
        assertFalse(test.predictTheWinner(new int[]{1, 5, 2}));
        assertTrue(test.predictTheWinner(new int[]{1, 5, 233, 7}));
    }

    @Test
    public void testEdgeCases() {
        assertTrue(test.predictTheWinner(new int[]{1}));
        assertTrue(test.predictTheWinner(new int[]{1, 2}));
    }

    @Test
    public void testLargeCase() {
        assertTrue(test.predictTheWinner(new int[]{1, 2, 3, 4, 5, 6}));
    }

    @Test
    public void testEvenLength() {
        assertTrue(test.predictTheWinner(new int[]{1, 2, 3, 4}));
    }

    @Test
    public void testEqualScores() {
        assertTrue(test.predictTheWinner(new int[]{1, 1, 1}));
    }

    @Test
    public void testPlayer1Loses() {
        assertFalse(test.predictTheWinner(new int[]{1, 5, 2}));
    }

    @Test
    public void testAllSameValues() {
        assertTrue(test.predictTheWinner(new int[]{5, 5, 5, 5, 5}));
    }

    @Test
    public void testDfsWithPruning() {
        assertFalse(test.predictTheWinnerDFSWithPruning(new int[]{1, 5, 2}));
        assertTrue(test.predictTheWinnerDFSWithPruning(new int[]{1, 5, 233, 7}));
    }

    @Test
    public void testDfs() {
        assertFalse(test.predictTheWinnerDFS(new int[]{1, 5, 2}));
        assertTrue(test.predictTheWinnerDFS(new int[]{1, 5, 233, 7}));
    }

    @Test
    public void testGiantCase() {
        int[] nums = new int[]{10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150, 160, 170, 180, 190, 200};
        assertTrue(test.predictTheWinner(nums));
    }

    @ParameterizedTest(name = "winner case {0}")
    @CsvSource({"'1,2',true", "'1,2,3',true", "'2,4,55,6,8',false", "'1,5,233,7',true", "'1,5,2',false", "'3,9,1,2',true", "'4,7,2,9',true", "'10,1,1,10',true", "'1,100,2,99',true", "'8,15,3,7,9,2',true"})
    public void testAdditionalOptimalPlayCases(String encoded, boolean expected) {
        assertEquals(expected, test.predictTheWinner(parse(encoded)));
    }

    private static int[] parse(String encoded) {
        String[] values = encoded.split(",");
        int[] result = new int[values.length];
        for (int i = 0; i < values.length; i++) result[i] = Integer.parseInt(values[i]);
        return result;
    }
}
