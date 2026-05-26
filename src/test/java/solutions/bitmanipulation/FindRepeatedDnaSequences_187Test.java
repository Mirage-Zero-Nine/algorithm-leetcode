package solutions.bitmanipulation;

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

    @Test public void testExactly10Chars() {
        List<String> res = solver.findRepeatedDnaSequences("ACGTACGTAC");
        assertTrue(res.isEmpty());
    }

    @Test public void testMultipleRepeats() {
        // "AAAAAAAAAA" repeated 3 times
        List<String> res = solver.findRepeatedDnaSequences("AAAAAAAAAAAA");
        assertEquals(1, res.size());
        assertTrue(res.contains("AAAAAAAAAA"));
    }

    @Test public void testAllSameChar() {
        // 20 A's -> "AAAAAAAAAA" appears at positions 0-10
        List<String> res = solver.findRepeatedDnaSequences("A".repeat(20));
        assertEquals(1, res.size());
        assertEquals("AAAAAAAAAA", res.get(0));
    }

    @Test public void testTwoDistinctRepeats() {
        String s = "AAAAACCCCCAAAAACCCCCC";
        List<String> res = solver.findRepeatedDnaSequences(s);
        assertTrue(res.contains("AAAAACCCCC"));
    }

    @Test public void testNegativeNoRepeatLongString() {
        // each 10-char window is unique
        String s = "ACGTACGTACGTACGTACGT";
        List<String> res = solver.findRepeatedDnaSequences(s);
        // check what actually repeats
        // positions: ACGTACGTAC, CGTACGTACG, GTACGTACGT, TACGTACGTA, ACGTACGTAC, ...
        // position 0 and 4 both give "ACGTACGTAC"
        assertTrue(res.contains("ACGTACGTAC"));
    }

    @Test public void testGiantCase() {
        // large string of repeating pattern
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            sb.append("ACGT");
        }
        List<String> res = solver.findRepeatedDnaSequences(sb.toString());
        // should find repeats without error
        assertTrue(res.size() > 0);
    }
}
