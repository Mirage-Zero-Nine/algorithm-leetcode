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
}
