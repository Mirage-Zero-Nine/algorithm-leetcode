package solution.others;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

public class GeneratePossibleNextMoves293Test {

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
}
