package solution.slidingWindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import solution.slidingwindow.LengthOfLongestSubstringKDistinct_340;

/**
 * @author BorisMirage
 * Time: 2024/12/01 11:51
 * Created with IntelliJ IDEA
 */

public class LengthOfLongestSubstringKDistinct_340Test {
    private final LengthOfLongestSubstringKDistinct_340 test = new LengthOfLongestSubstringKDistinct_340();

    @Test
    public void testEmpty() {
        assertEquals(0, test.lengthOfLongestSubstringKDistinct(null, 100));
        assertEquals(0, test.lengthOfLongestSubstringKDistinct("", 100));
        assertEquals(0, test.lengthOfLongestSubstringKDistinctBucket(null, 100));
        assertEquals(0, test.lengthOfLongestSubstringKDistinctBucket("", 100));
    }

    @Test
    public void test() {
        assertEquals(3, test.lengthOfLongestSubstringKDistinctBucket("eceba", 2));
        assertEquals(2, test.lengthOfLongestSubstringKDistinctBucket("aa", 2));

        assertEquals(3, test.lengthOfLongestSubstringKDistinct("eceba", 2));
        assertEquals(2, test.lengthOfLongestSubstringKDistinct("aa", 2));
    }
}