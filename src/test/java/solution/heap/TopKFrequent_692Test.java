package solution.heap;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.List;

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
}
