package solution.backtracking;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BorisMirage
 * Time: 2022/10/21 18:21
 * Created with IntelliJ IDEA
 */

public class WordBreak140Test {

    private final WordBreak_140 test = new WordBreak_140();

    @Test
    public void test() {
        List<String> words = Lists.newArrayList("apple", "pen", "applepen", "pine", "pineapple");
        List<String> expected = Lists.newArrayList("pine apple pen apple", "pine applepen apple", "pineapple pen apple");
        assertTrue(expected.containsAll(test.wordBreak("pineapplepenapple", words)));

        words = Lists.newArrayList("cat", "cats", "and", "sand", "dog");
        expected = Lists.newArrayList("cats and dog", "cat sand dog");
        assertTrue(expected.containsAll(test.wordBreak("catsanddog", words)));
    }

    @Test
    public void testEmptyOutput() {
        List<String> words = Lists.newArrayList("cats", "dog", "sand", "and", "cat");
        assertIterableEquals(new ArrayList<>(), test.wordBreak("catsandog", words));
    }
}