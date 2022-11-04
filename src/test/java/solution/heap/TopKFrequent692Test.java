package solution.heap;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author BorisMirage
 * Time: 2022/11/04 11:09
 * Created with IntelliJ IDEA
 */

public class TopKFrequent692Test {

    private final TopKFrequent_692 test = new TopKFrequent_692();

    @Test
    public void test() {
        String[] words = new String[]{"i", "love", "leetcode", "i", "love", "coding"};
        List<String> expected = Lists.newArrayList("i", "love");
        assertIterableEquals(expected, test.topKFrequent(words, 2));
    }

    @Test
    public void testOneWordOnly() {
        String[] words = new String[]{"i"};
        List<String> expected = Lists.newArrayList("i");
        assertIterableEquals(expected, test.topKFrequent(words, 1));
    }

    @Test
    public void testLongList() {
        String[] words = new String[]{"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"};
        List<String> expected = Lists.newArrayList("the", "is", "sunny", "day");
        assertIterableEquals(expected, test.topKFrequent(words, 4));
    }
}