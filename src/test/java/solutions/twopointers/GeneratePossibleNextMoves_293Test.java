package solutions.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

public class GeneratePossibleNextMoves_293Test {

    private final GeneratePossibleNextMoves_293 test = new GeneratePossibleNextMoves_293();

    @Test
    public void testHappyCases() {
        List<String> result = test.generatePossibleNextMoves("++++");
        assertEquals(3, result.size());
        assertTrue(result.contains("--++"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.generatePossibleNextMoves("+").size());
        assertEquals(0, test.generatePossibleNextMoves("----").size());
    }

    @Test
    public void testLargeCase() {
        List<String> result = test.generatePossibleNextMoves("+++++");
        assertEquals(4, result.size());
    }

    @Test
    public void testEmptyString() {
        assertEquals(0, test.generatePossibleNextMoves("").size());
    }

    @Test
    public void testSinglePlus() {
        assertEquals(0, test.generatePossibleNextMoves("+").size());
    }

    @Test
    public void testTwoPluses() {
        List<String> result = test.generatePossibleNextMoves("++");
        assertEquals(1, result.size());
        assertEquals("--", result.get(0));
    }

    @Test
    public void testAlternating() {
        assertEquals(0, test.generatePossibleNextMoves("+-+-+-").size());
    }

    @Test
    public void testPlusesAtEnd() {
        List<String> result = test.generatePossibleNextMoves("-+++");
        assertEquals(2, result.size());
        assertTrue(result.contains("-+--"));
        assertTrue(result.contains("---+"));
    }

    @Test
    public void testAllMinuses() {
        assertEquals(0, test.generatePossibleNextMoves("------").size());
    }

    @Test
    public void testGiantCase() {
        String s = "+".repeat(100);
        List<String> result = test.generatePossibleNextMoves(s);
        assertEquals(99, result.size());
    }

    @Test public void testThreePluses() { assertEquals(List.of("--+", "+--"), test.generatePossibleNextMoves("+++")); }
    @Test public void testSeparatedPairs() { assertEquals(2, test.generatePossibleNextMoves("++-++").size()); }
    @Test public void testPairAtStart() { assertEquals(List.of("--+--", "+----"), test.generatePossibleNextMoves("+++--")); }
    @Test public void testSinglePairAmongMinus() { assertEquals(List.of("----+", "--+--"), test.generatePossibleNextMoves("--+++")); }
    @Test public void testFourSeparatedRuns() { assertEquals(2, test.generatePossibleNextMoves("++--++--").size()); }
    @Test public void testOutputLengthPreserved() { for(String s:test.generatePossibleNextMoves("++++++")) assertEquals(6,s.length()); }
    @Test public void testOutputContainsNoPlusPair() { assertEquals(3, test.generatePossibleNextMoves("++++").size()); }
    @Test public void testMixedSymbols() { assertEquals(1, test.generatePossibleNextMoves("-++-").size()); }
    @Test public void testRepeatedInvocation() { assertEquals(3,test.generatePossibleNextMoves("++++").size()); assertEquals(0,test.generatePossibleNextMoves("----").size()); }
    @Test public void testFivePlusesCount() { assertEquals(4, test.generatePossibleNextMoves("+++++").size()); }
}
