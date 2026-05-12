package solution.bitmanipulation;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FindRepeatedDnaSequences_187Test {
    private final FindRepeatedDnaSequences_187 solver = new FindRepeatedDnaSequences_187();

    @Test public void testBasic() {
        List<String> res = solver.findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT");
        assertTrue(res.contains("AAAAACCCCC"));
        assertTrue(res.contains("CCCCCAAAAA"));
    }

    @Test public void testNoRepeat() {
        List<String> res = solver.findRepeatedDnaSequences("ACGTACGTAC");
        assertTrue(res.isEmpty());
    }

    @Test public void testShortString() {
        List<String> res = solver.findRepeatedDnaSequences("AAAA");
        assertTrue(res.isEmpty());
    }

    @Test public void testEmptyString() {
        List<String> res = solver.findRepeatedDnaSequences("");
        assertTrue(res.isEmpty());
    }

    @Test public void testSingleRepeat() {
        List<String> res = solver.findRepeatedDnaSequences("AAAAAAAAAAA");
        assertEquals(1, res.size());
        assertEquals("AAAAAAAAAA", res.get(0));
    }
}
