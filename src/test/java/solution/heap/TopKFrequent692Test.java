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

public class TopKFrequent692Test {

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
}
