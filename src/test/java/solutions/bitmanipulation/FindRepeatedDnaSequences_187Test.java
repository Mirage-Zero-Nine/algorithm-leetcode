package solutions.bitmanipulation;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FindRepeatedDnaSequences_187Test {
    private final FindRepeatedDnaSequences_187 solver = new FindRepeatedDnaSequences_187();

    @Test public void testBasic() {
        List<String> res = solver.findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT");
        assertEquals(Set.of("AAAAACCCCC", "CCCCCAAAAA"), new HashSet<>(res));
        assertEquals(2, res.size());
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
        assertEquals(Set.of("AAAAAAAAAA"), new HashSet<>(res));
    }

    @Test public void testExactly10Chars() {
        List<String> res = solver.findRepeatedDnaSequences("ACGTACGTAC");
        assertTrue(res.isEmpty());
    }

    @Test public void testMultipleRepeats() {
        // "AAAAAAAAAA" repeated 3 times
        List<String> res = solver.findRepeatedDnaSequences("AAAAAAAAAAAA");
        assertEquals(1, res.size());
        assertEquals(Set.of("AAAAAAAAAA"), new HashSet<>(res));
    }

    @Test public void testAllSameChar() {
        // 20 A's -> "AAAAAAAAAA" appears at positions 0-10
        List<String> res = solver.findRepeatedDnaSequences("A".repeat(20));
        assertEquals(1, res.size());
        assertEquals(Set.of("AAAAAAAAAA"), new HashSet<>(res));
    }

    @Test public void testTwoDistinctRepeats() {
        String s = "AAAAACCCCCAAAAACCCCCC";
        List<String> res = solver.findRepeatedDnaSequences(s);
        assertEquals(Set.of("AAAAACCCCC"), new HashSet<>(res));
    }

    @Test public void testNegativeNoRepeatLongString() {
        String s = "ACGTACGTACGTACGTACGT";
        List<String> res = solver.findRepeatedDnaSequences(s);
        // positions: ACGTACGTAC, CGTACGTACG, GTACGTACGT, TACGTACGTA, ACGTACGTAC, ...
        assertEquals(Set.of("ACGTACGTAC", "CGTACGTACG", "GTACGTACGT", "TACGTACGTA"),
                new HashSet<>(res));
        assertEquals(4, res.size());
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

    @Test public void testPeriodicGiantDnaHasExactlyFourUniqueRepeats() {
        java.util.List<String> result = solver.findRepeatedDnaSequences("ACGT".repeat(10000));
        assertEquals(java.util.Set.of("ACGTACGTAC", "CGTACGTACG", "GTACGTACGT", "TACGTACGTA"),
                new java.util.HashSet<>(result));
        assertEquals(4, result.size());
    }

    @Test public void testSeededDnaAgainstSubstringFrequencyOracle() {
        java.util.Random random = new java.util.Random(1870906L);
        for (int sample = 0; sample < 100; sample++) {
            StringBuilder input = new StringBuilder();
            for (int i = 0; i < 80; i++) input.append("ACGT".charAt(random.nextInt(4)));
            input.append(input.substring(5, 35));
            java.util.Map<String, Integer> counts = new java.util.HashMap<>();
            for (int i = 0; i + 10 <= input.length(); i++)
                counts.merge(input.substring(i, i + 10), 1, Integer::sum);
            java.util.Set<String> expected = new java.util.HashSet<>();
            counts.forEach((sequence, count) -> { if (count > 1) expected.add(sequence); });
            java.util.List<String> actual = solver.findRepeatedDnaSequences(input.toString());
            assertEquals(expected, new java.util.HashSet<>(actual), "sample=" + sample);
            assertEquals(expected.size(), actual.size());
        }
    }

    @Test public void testRepeatedTSequenceAcrossElevenCharacterBoundary() {
        assertEquals(Set.of("TTTTTTTTTT"), new HashSet<>(solver.findRepeatedDnaSequences("TTTTTTTTTTT")));
        assertTrue(solver.findRepeatedDnaSequences("TTTTTTTTTT").isEmpty());
    }

    @Test public void testNineCharactersHasNoCompleteWindow() {
        assertTrue(solver.findRepeatedDnaSequences("ACGTACGTA").isEmpty());
    }

    @Test public void testElevenCharactersCanOnlyRepeatWhenWindowsOverlap() {
        assertEquals(Set.of("AAAAAAAAAA"),
                new HashSet<>(solver.findRepeatedDnaSequences("AAAAAAAAAAA")));
    }

    @Test public void testEachSingleBasePatternProducesOnlyItsOwnSequence() {
        for (char base : "ACGT".toCharArray()) {
            List<String> actual = solver.findRepeatedDnaSequences(String.valueOf(base).repeat(11));
            assertEquals(Set.of(String.valueOf(base).repeat(10)), new HashSet<>(actual), "base=" + base);
            assertEquals(1, actual.size(), "base=" + base);
        }
    }

    @Test public void testTwoDifferentRepeatedPatternsAreDeduplicated() {
        String input = "AAAAAAAAAACCCCCCCCCCAAAAAAAAAACCCCCCCCCC";
        Set<String> actual = new HashSet<>(solver.findRepeatedDnaSequences(input));
        assertTrue(actual.contains("AAAAAAAAAA"));
        assertTrue(actual.contains("CCCCCCCCCC"));
        assertEquals(repeatedWindowsByOracle(input),
                actual);
    }

    @Test public void testRepeatSeparatedByAFullDifferentWindow() {
        String first = "ACGTACGTAC";
        String input = first + "TTTTTTTTTT" + first;
        assertEquals(Set.of(first),
                new HashSet<>(solver.findRepeatedDnaSequences(input)));
    }

    @Test public void testRepeatedSequenceCanAppearAtBothStringBoundaries() {
        String input = "CGTACGTACG" + "AAAA" + "CGTACGTACG";
        assertEquals(Set.of("CGTACGTACG"),
                new HashSet<>(solver.findRepeatedDnaSequences(input)));
    }

    @Test public void testAllFourBasesParticipateInRepeatedWindows() {
        String input = "ACGTACGTAC" + "T" + "ACGTACGTAC";
        assertEquals(Set.of("ACGTACGTAC"),
                new HashSet<>(solver.findRepeatedDnaSequences(input)));
    }

    @Test public void testResultContainsNoWindowThatOccursOnlyOnce() {
        String input = "ACGTACGTAC" + "CCCCCCCCCC" + "ACGTACGTAC";
        Set<String> actual = new HashSet<>(solver.findRepeatedDnaSequences(input));
        assertEquals(Set.of("ACGTACGTAC", "CCCCCCCCCC"), actual);
        assertTrue(actual.stream().allMatch(sequence -> countOccurrences(input, sequence) > 1));
    }

    @Test public void testResultIsIndependentAcrossRepeatedCalls() {
        String repeated = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        Set<String> expected = Set.of("AAAAACCCCC", "CCCCCAAAAA");
        assertEquals(expected, new HashSet<>(solver.findRepeatedDnaSequences(repeated)));
        assertTrue(solver.findRepeatedDnaSequences("ACGTACGTAC").isEmpty());
        assertEquals(expected, new HashSet<>(solver.findRepeatedDnaSequences(repeated)));
    }

    @Test public void testReturnedListCanBeMutatedWithoutAffectingLaterCalls() {
        List<String> first = solver.findRepeatedDnaSequences("AAAAAAAAAAA");
        first.clear();
        assertEquals(Set.of("AAAAAAAAAA"),
                new HashSet<>(solver.findRepeatedDnaSequences("AAAAAAAAAAA")));
    }

    @Test public void testMaximumDocumentedInputLength() {
        String input = "ACGT".repeat(25_000);
        Set<String> expected = Set.of("ACGTACGTAC", "CGTACGTACG", "GTACGTACGT", "TACGTACGTA");
        List<String> actual = solver.findRepeatedDnaSequences(input);
        assertEquals(expected, new HashSet<>(actual));
        assertEquals(expected.size(), actual.size());
    }

    @Test public void testSeededSmallInputsMatchIndependentFrequencyOracle() {
        Random random = new Random(187202609L);
        for (int sample = 0; sample < 180; sample++) {
            int length = 10 + random.nextInt(91);
            StringBuilder input = new StringBuilder(length);
            for (int i = 0; i < length; i++) {
                input.append("ACGT".charAt(random.nextInt(4)));
            }
            Set<String> expected = repeatedWindowsByOracle(input.toString());
            List<String> actual = solver.findRepeatedDnaSequences(input.toString());
            assertEquals(expected, new HashSet<>(actual), "sample=" + sample);
            assertEquals(expected.size(), actual.size(), "sample=" + sample);
        }
    }

    private static Set<String> repeatedWindowsByOracle(String input) {
        Map<String, Integer> counts = new HashMap<>();
        for (int start = 0; start + 10 <= input.length(); start++) {
            counts.merge(input.substring(start, start + 10), 1, Integer::sum);
        }
        Set<String> repeated = new HashSet<>();
        counts.forEach((sequence, count) -> {
            if (count > 1) {
                repeated.add(sequence);
            }
        });
        return repeated;
    }

    private static int countOccurrences(String input, String sequence) {
        int count = 0;
        for (int start = 0; start + sequence.length() <= input.length(); start++) {
            if (input.regionMatches(start, sequence, 0, sequence.length())) {
                count++;
            }
        }
        return count;
    }
}
