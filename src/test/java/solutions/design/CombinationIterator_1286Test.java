package solutions.design;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import util.IsolatedTestProcess;

class CombinationIterator_1286Test {

    @Test
    void coversDistinctBoundaryAndOrderingScenarios() throws Exception {
        IsolatedTestProcess.run(CombinationIterator_1286Test.class,
                "scenarioDistinctBoundaryAndOrdering", 10);
    }

    private void scenarioDistinctBoundaryAndOrdering() {
        assertSequence("abc", 1, List.of("a", "b", "c"));
        assertSequence("abc", 2, List.of("ab", "ac", "bc"));
        assertSequence("abc", 3, List.of("abc"));
        assertSequence("abcd", 1, List.of("a", "b", "c", "d"));
        assertSequence("abcd", 2, List.of("ab", "ac", "ad", "bc", "bd", "cd"));
        assertSequence("abcd", 3, List.of("abc", "abd", "acd", "bcd"));
        assertSequence("abcd", 4, List.of("abcd"));
        assertSequence("aceg", 2, List.of("ac", "ae", "ag", "ce", "cg", "eg"));
        assertSequence("abcdefgh", 4, combinations("abcdefgh", 4));
        assertSequence("abcdefghijklmn", 7, combinations("abcdefghijklmn", 7));
        assertSequence("abcdefghijklmno", 15, List.of("abcdefghijklmno"));
    }

    @Test
    void singletonAndExhaustionAreReportedCorrectly() throws Exception {
        IsolatedTestProcess.run(CombinationIterator_1286Test.class,
                "scenarioSingletonAndPostExhaustion", 10);
    }

    private void scenarioSingletonAndPostExhaustion() {
        CombinationIterator_1286 iterator = new CombinationIterator_1286("z", 1);
        assertTrue(iterator.hasNext());
        assertEquals("z", iterator.next());
        assertFalse(iterator.hasNext());
        assertNull(iterator.next());
    }

    @Test
    void emptySourceAndOversizeLengthAreSupportedExhaustedExtensions() throws Exception {
        IsolatedTestProcess.run(CombinationIterator_1286Test.class,
                "scenarioEmptySourceAndOversizeLength", 10);
    }

    private void scenarioEmptySourceAndOversizeLength() {
        CombinationIterator_1286 emptySource = new CombinationIterator_1286("", 0);
        assertFalse(emptySource.hasNext());
        assertNull(emptySource.next());

        CombinationIterator_1286 tooLong = new CombinationIterator_1286("abc", 4);
        assertFalse(tooLong.hasNext());
        assertNull(tooLong.next());
    }

    @Test
    void repeatedHasNextDoesNotAdvanceTheIterator() throws Exception {
        IsolatedTestProcess.run(CombinationIterator_1286Test.class,
                "scenarioRepeatedHasNextDoesNotAdvance", 10);
    }

    private void scenarioRepeatedHasNextDoesNotAdvance() {
        CombinationIterator_1286 iterator = new CombinationIterator_1286("abc", 2);
        assertTrue(iterator.hasNext());
        assertTrue(iterator.hasNext());
        assertEquals("ab", iterator.next());
        assertTrue(iterator.hasNext());
        assertTrue(iterator.hasNext());
        assertEquals("ac", iterator.next());
        assertEquals("bc", iterator.next());
        assertFalse(iterator.hasNext());
        assertFalse(iterator.hasNext());
    }

    @Test
    void exhaustivelyChecksEveryCombinationForAllSubsetsOfSixLetters() throws Exception {
        IsolatedTestProcess.run(CombinationIterator_1286Test.class,
                "scenarioExhaustiveSubsetsOfSixLetters", 10);
    }

    private void scenarioExhaustiveSubsetsOfSixLetters() {
        // Enumerate every nonempty subset of abcdef at every valid length; recursive generation
        // is an independent expected-order oracle for the iterator's position advancement.
        for (int mask = 1; mask < (1 << 6); mask++) {
            StringBuilder source = new StringBuilder();
            for (int bit = 0; bit < 6; bit++) {
                if ((mask & (1 << bit)) != 0) {
                    source.append((char) ('a' + bit));
                }
            }
            for (int length = 1; length <= source.length(); length++) {
                assertSequence(source.toString(), length, combinations(source.toString(), length));
            }
        }
    }

    @Test
    void maximumConstraintTraversalReturnsAll6435ResultsInOrder() throws Exception {
        IsolatedTestProcess.run(CombinationIterator_1286Test.class,
                "scenarioMaximumConstraintTraversal", 10);
    }

    private void scenarioMaximumConstraintTraversal() {
        String source = "abcdefghijklmno";
        List<String> expected = combinations(source, 7);
        assertEquals(6435, expected.size());

        CombinationIterator_1286 iterator = new CombinationIterator_1286(source, 7);
        assertTrue(iterator.hasNext());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), iterator.next(), "result index " + i);
        }
        assertFalse(iterator.hasNext());
    }

    private static void assertSequence(String source, int length, List<String> expected) {
        CombinationIterator_1286 iterator = new CombinationIterator_1286(source, length);
        for (int i = 0; i < expected.size(); i++) {
            assertTrue(iterator.hasNext(), source + ", k=" + length + ", index=" + i);
            assertEquals(expected.get(i), iterator.next(), source + ", k=" + length + ", index=" + i);
        }
        assertFalse(iterator.hasNext(), source + ", k=" + length + " should be exhausted");
    }

    private static List<String> combinations(String source, int length) {
        List<String> results = new ArrayList<>();
        appendCombinations(source, length, 0, new StringBuilder(), results);
        return results;
    }

    private static void appendCombinations(String source, int length, int start,
                                           StringBuilder prefix, List<String> results) {
        if (prefix.length() == length) {
            results.add(prefix.toString());
            return;
        }
        int needed = length - prefix.length();
        for (int i = start; i <= source.length() - needed; i++) {
            prefix.append(source.charAt(i));
            appendCombinations(source, length, i + 1, prefix, results);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }
}
