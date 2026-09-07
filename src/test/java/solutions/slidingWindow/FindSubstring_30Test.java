package solutions.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Collections;
import org.junit.jupiter.api.Test;

public class FindSubstring_30Test {

    @Test
    public void testHappyCase1() {
        FindSubstring_30 test = new FindSubstring_30();
        String s = "barfoothefoobarman";
        String[] words = {"foo", "bar"};
        List<Integer> result = test.findSubstring(s, words);
        Collections.sort(result);
        assertEquals(List.of(0, 9), result);
    }

    @Test
    public void testHappyCase2() {
        FindSubstring_30 test = new FindSubstring_30();
        String s = "wordgoodgoodgoodbestword";
        String[] words = {"word", "good", "best", "word"};
        // Each word length 4. 4 words. Total length 16.
        // Index 0: word, good, good, good (fail)
        // Index 4: good, good, good, best (fail)
        // Index 8: good, good, best, word (fail, missing one word)
        // Wait, words are {word, good, best, word}.
        // good, good, best, word -> missing one "word".
        assertEquals(List.of(), test.findSubstring(s, words));
    }

    @Test
    public void testHappyCase3() {
        FindSubstring_30 test = new FindSubstring_30();
        String s = "barfoofoobarthefoobarman";
        String[] words = {"bar", "foo", "the"};
        List<Integer> result = test.findSubstring(s, words);
        Collections.sort(result);
        assertEquals(List.of(6, 9, 12), result);
    }

    @Test
    public void testHappyCase4() {
        FindSubstring_30 test = new FindSubstring_30();
        String s = "wordgoodgoodgoodbestword";
        String[] words = {"word", "good", "best", "good"};
        assertEquals(List.of(8), test.findSubstring(s, words));
    }

    @Test
    public void testHappyCase5() {
        FindSubstring_30 test = new FindSubstring_30();
        String s = "lingmindraboofooowingdingbarwingmonkeypoundcake";
        String[] words = {"fooo", "barw", "ding", "wing"};
        assertEquals(List.of(13), test.findSubstring(s, words));
    }

    @Test
    public void testNegativeCase() {
        FindSubstring_30 test = new FindSubstring_30();
        String s = "wordgoodgoodgoodbestword";
        String[] words = {"word", "good", "best", "good", "bad"};
        assertEquals(List.of(), test.findSubstring(s, words));
    }

    @Test
    public void testEdgeCaseEmpty() {
        FindSubstring_30 test = new FindSubstring_30();
        assertEquals(List.of(), test.findSubstring("", new String[]{"a"}));
        assertEquals(List.of(), test.findSubstring("a", new String[]{}));
    }

    @Test
    public void testEdgeCaseSingleWord() {
        FindSubstring_30 test = new FindSubstring_30();
        String s = "aaaaa";
        String[] words = {"aa"};
        List<Integer> result = test.findSubstring(s, words);
        Collections.sort(result);
        assertEquals(List.of(0, 1, 2, 3), result);
    }

    @Test
    public void testEdgeCaseWordsLongerThanS() {
        FindSubstring_30 test = new FindSubstring_30();
        String s = "abc";
        String[] words = {"abcd"};
        assertEquals(List.of(), test.findSubstring(s, words));
    }

    @Test
    public void testGiantCase() {
        FindSubstring_30 test = new FindSubstring_30();
        int n = 1000;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append("ab");
        }
        String s = sb.toString();
        String[] words = {"ab", "ab"};
        // length of words = 2, total length 4.
        // indices 0, 2, 4, ..., 2*(n-2). Total n-1 indices.
        List<Integer> result = test.findSubstring(s, words);
        assertEquals(n - 1, result.size());
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
