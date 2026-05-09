package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LengthOfLongestSubstringTwoDistinct159Test {

    private final LengthOfLongestSubstringTwoDistinct_159 test = new LengthOfLongestSubstringTwoDistinct_159();

    @Test
    public void testHappyCases() {
        assertEquals(3, test.lengthOfLongestSubstringTwoDistinct("eceba"));
        assertEquals(5, test.lengthOfLongestSubstringTwoDistinct("ccaabbb"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(1, test.lengthOfLongestSubstringTwoDistinct("a"));
        assertEquals(2, test.lengthOfLongestSubstringTwoDistinct("ab"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(4, test.lengthOfLongestSubstringTwoDistinct("aabbccaabb"));
    }
}
