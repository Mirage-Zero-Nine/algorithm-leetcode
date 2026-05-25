package solution.heap;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author BorisMirage
 * Time: 2022/11/04 11:09
 * Created with IntelliJ IDEA
 */

public class TopKFrequent_692Test {

    private final TopKFrequent_692 test = new TopKFrequent_692();

    @Test
    public void testHappyCases() {
        String[] words = new String[]{"i", "love", "leetcode", "i", "love", "coding"};
        List<String> expected = List.of("i", "love");
        assertIterableEquals(expected, test.topKFrequent(words, 2));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        String[] words = new String[]{"i"};
        List<String> expected = List.of("i");
        assertIterableEquals(expected, test.topKFrequent(words, 1));
        assertTrue(test.topKFrequent(new String[]{"a", "b", "c"}, 0).isEmpty());
    }

    @Test
    public void testTieBreakingAndLargeCase() {
        String[] words = new String[]{"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"};
        List<String> expected = List.of("the", "is", "sunny", "day");
        assertIterableEquals(expected, test.topKFrequent(words, 4));
        assertIterableEquals(
            List.of("alpha", "beta", "gamma"),
            test.topKFrequent(new String[]{
                "alpha", "beta", "gamma", "alpha", "beta", "gamma", "alpha", "beta", "gamma",
                "delta", "epsilon", "zeta"
            }, 3)
        );
    }

    @Test
    public void testAllSameWord() {
        assertIterableEquals(List.of("hello"), test.topKFrequent(new String[]{"hello", "hello", "hello"}, 1));
    }

    @Test
    public void testAlphabeticalTieBreak() {
        // All words appear once, alphabetical order determines result
        assertIterableEquals(List.of("a", "b"), test.topKFrequent(new String[]{"b", "c", "a"}, 2));
    }

    @Test
    public void testKEqualsWordCount() {
        assertIterableEquals(List.of("a", "b", "c"), test.topKFrequent(new String[]{"a", "b", "c"}, 3));
    }

    @Test
    public void testKIsOne() {
        assertIterableEquals(List.of("z"), test.topKFrequent(new String[]{"z", "z", "a", "b"}, 1));
    }

    @Test
    public void testTwoWordsEqualFrequency() {
        // "apple" and "banana" both appear twice, alphabetical: apple < banana
        assertIterableEquals(List.of("apple", "banana"), test.topKFrequent(
            new String[]{"banana", "apple", "banana", "apple"}, 2));
    }

    @Test
    public void testNegativeCaseKZero() {
        assertTrue(test.topKFrequent(new String[]{"x", "y"}, 0).isEmpty());
    }

    @Test
    public void testGiantCase() {
        // 1000 words, some repeated
        String[] words = new String[1000];
        for (int i = 0; i < 500; i++) words[i] = "frequent";
        for (int i = 500; i < 800; i++) words[i] = "medium";
        for (int i = 800; i < 900; i++) words[i] = "rare";
        for (int i = 900; i < 1000; i++) words[i] = "unique" + i;
        List<String> result = test.topKFrequent(words, 3);
        assertIterableEquals(List.of("frequent", "medium", "rare"), result);
    }

    @Test
    public void testManyTiesAlphabetical() {
        // All appear once, pick top 3 alphabetically
        assertIterableEquals(List.of("ant", "bat", "cat"),
            test.topKFrequent(new String[]{"dog", "cat", "bat", "ant", "elk"}, 3));
    }

    @Test
    public void testMixedFrequencies() {
        assertIterableEquals(List.of("a", "b"),
            test.topKFrequent(new String[]{"a", "a", "a", "b", "b", "c"}, 2));
    }

    @Test
    public void testEmptyArrayKZero() {
        // Empty input with k=0 should return empty list
        assertTrue(test.topKFrequent(new String[]{}, 0).isEmpty());
    }

    @Test
    public void testSingleWordKOne() {
        assertIterableEquals(List.of("hello"), test.topKFrequent(new String[]{"hello"}, 1));
    }

    @Test
    public void testAllSameFrequencyLexOrder() {
        // All words appear exactly once - result should be lexicographic ascending
        assertIterableEquals(List.of("apple", "banana", "cherry"),
            test.topKFrequent(new String[]{"cherry", "banana", "apple"}, 3));
    }

    @Test
    public void testMultipleTiesClassicExample() {
        // i:2, love:2, coding:1, leetcode:1 -> k=3: [i, love, coding] (lex tiebreak within freq groups)
        String[] words = {"i", "love", "leetcode", "i", "love", "coding"};
        assertIterableEquals(List.of("i", "love", "coding"), test.topKFrequent(words, 3));
    }

    @Test
    public void testKOneReturnsHighestFreqLexSmallest() {
        // "a" and "b" both appear 3 times, k=1 -> "a" (lex smallest among highest freq)
        assertIterableEquals(List.of("a"),
            test.topKFrequent(new String[]{"b", "a", "b", "a", "b", "a", "c"}, 1));
    }

    @Test
    public void testAllUniqueFrequencies() {
        // a:4, b:3, c:2, d:1 -> sorted purely by frequency
        String[] words = {"a", "a", "a", "a", "b", "b", "b", "c", "c", "d"};
        assertIterableEquals(List.of("a", "b", "c"), test.topKFrequent(words, 3));
    }

    @Test
    public void testMixedFreqGroupsWithLexTiebreak() {
        // freq 3: a, b; freq 2: x, y; freq 1: m -> k=4 should be [a, b, x, y]
        String[] words = {"b", "a", "b", "a", "b", "a", "y", "x", "y", "x", "m"};
        assertIterableEquals(List.of("a", "b", "x", "y"), test.topKFrequent(words, 4));
    }

    @Test
    public void testLargeInputPropertyBased() {
        // Generate 1000 words with seed 42, verify properties of result
        Random rng = new Random(42L);
        String[] pool = {"alpha", "beta", "gamma", "delta", "epsilon", "zeta", "eta", "theta", "iota", "kappa"};
        String[] words = new String[1000];
        for (int i = 0; i < 1000; i++) {
            words[i] = pool[rng.nextInt(pool.length)];
        }
        int k = 5;
        List<String> result = test.topKFrequent(words, k);

        assertEquals(k, result.size());

        // Build frequency map to verify ordering
        Map<String, Integer> freq = new HashMap<>();
        for (String w : words) freq.merge(w, 1, Integer::sum);

        // Verify: frequencies are non-increasing, and lex tiebreak within same freq
        for (int i = 0; i < result.size() - 1; i++) {
            int f1 = freq.get(result.get(i));
            int f2 = freq.get(result.get(i + 1));
            assertTrue(f1 >= f2, "Frequencies should be non-increasing");
            if (f1 == f2) {
                assertTrue(result.get(i).compareTo(result.get(i + 1)) < 0,
                    "Same-frequency words should be in lexicographic ascending order");
            }
        }
    }
}
