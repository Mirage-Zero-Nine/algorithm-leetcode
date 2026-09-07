package solutions.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

public class FindSubstring30Test {

    @Test
    public void testHappyCases() {
        List<Integer> result = new FindSubstring_30().findSubstring("barfoothefoobarman", new String[]{"foo", "bar"});
        assertTrue(result.contains(0) && result.contains(9));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(List.of(), new FindSubstring_30().findSubstring("wordgoodgoodgoodbestword", new String[]{"word", "good", "best", "word"}));
        assertEquals(List.of(), new FindSubstring_30().findSubstring("", new String[]{"a"}));
    }

    @Test
    public void testLargeCase() {
        List<Integer> result = new FindSubstring_30().findSubstring("wordgoodgoodgoodbestword", new String[]{"word", "good", "best", "good"});
        assertTrue(result.contains(8));
    }

    @Test
    public void testNullString() {
        assertEquals(List.of(), new FindSubstring_30().findSubstring(null, new String[]{"a"}));
    }

    @Test
    public void testNullWords() {
        assertEquals(List.of(), new FindSubstring_30().findSubstring("abc", null));
    }

    @Test
    public void testEmptyWords() {
        assertEquals(List.of(), new FindSubstring_30().findSubstring("abc", new String[]{}));
    }

    @Test
    public void testSingleCharWords() {
        List<Integer> result = new FindSubstring_30().findSubstring("abab", new String[]{"a", "b"});
        assertTrue(result.contains(0));
        assertTrue(result.contains(2));
    }

    @Test
    public void testNoMatch() {
        assertEquals(List.of(), new FindSubstring_30().findSubstring("abcdef", new String[]{"xyz"}));
    }

    @Test
    public void testExactMatch() {
        List<Integer> result = new FindSubstring_30().findSubstring("foobar", new String[]{"foo", "bar"});
        assertEquals(List.of(0), result);
    }

    @Test
    public void testGiantCase() {
        String s = "ab".repeat(5000);
        List<Integer> result = new FindSubstring_30().findSubstring(s, new String[]{"ab", "ab"});
        // every even index from 0 to 9996 should be a valid start
        assertTrue(result.size() > 0);
    }

    @org.junit.jupiter.params.ParameterizedTest(name = "independent oracle seed={0}")
    @org.junit.jupiter.params.provider.ValueSource(ints = {7, 19, 43, 71, 101, 211, 509, 997, 2027, 4093, 8191, 16381})
    public void testSeededCasesAgainstIndependentOracle(int seed) {
        java.util.Random random = new java.util.Random(seed);
        for (int sample = 0; sample < 40; sample++) {

            int wordLength = 1 + random.nextInt(3), count = 1 + random.nextInt(4);
            String[] words = new String[count];
            for (int i = 0; i < count; i++)
                words[i] = random.ints(wordLength, 'a', 'd').collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
            String s = random.ints(30, 'a', 'd').collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
            java.util.List<String> wanted = new java.util.ArrayList<>(java.util.Arrays.asList(words));
            java.util.Collections.sort(wanted);
            java.util.List<Integer> expected = new java.util.ArrayList<>();
            for (int left = 0; left + count * wordLength <= s.length(); left++) {
                java.util.List<String> candidate = new java.util.ArrayList<>();
                for (int i = 0; i < count; i++) candidate.add(s.substring(left + i * wordLength, left + (i + 1) * wordLength));
                java.util.Collections.sort(candidate);
                if (wanted.equals(candidate)) expected.add(left);
            }
            org.junit.jupiter.api.Assertions.assertEquals(expected, new FindSubstring_30().findSubstring(s, words));
        }
    }
}
