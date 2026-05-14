package solution.map;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NumOfPairs_2023Test {

    private final NumOfPairs_2023 test = new NumOfPairs_2023();

    @Test
    public void testHappyCases() {
        assertEquals(4, test.numOfPairs(new String[]{"777", "7", "77", "77"}, "7777"));
        assertEquals(2, test.numOfPairs(new String[]{"123", "4", "12", "34"}, "1234"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(1, test.numOfPairs(new String[]{"1", "2"}, "12"));
        assertEquals(0, test.numOfPairs(new String[]{}, "abc"));
        assertEquals(0, test.numOfPairs(new String[]{"abc", "def"}, "xyz"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(12, test.numOfPairs(new String[]{"ab", "ab", "ab", "ab"}, "abab"));
    }

    @Test
    public void testNullInput() {
        assertEquals(0, test.numOfPairs(null, "abc"));
    }

    @Test
    public void testSingleElementMatchesFull() {
        // single element equals target, need another element to form pair
        assertEquals(0, test.numOfPairs(new String[]{"abc"}, "abc"));
    }

    @Test
    public void testTwoElementsConcatenate() {
        assertEquals(1, test.numOfPairs(new String[]{"a", "b"}, "ab"));
        assertEquals(1, test.numOfPairs(new String[]{"a", "b"}, "ba"));
    }

    @Test
    public void testBothDirections() {
        // "a"+"a" = "aa", both orders work: (0,1) and (1,0)
        assertEquals(2, test.numOfPairs(new String[]{"a", "a"}, "aa"));
    }

    @Test
    public void testNoMatch() {
        assertEquals(0, test.numOfPairs(new String[]{"x", "y", "z"}, "abc"));
    }

    @Test
    public void testOverlappingPrefixSuffix() {
        // target "aba": "a" is both prefix and suffix, "ba" is suffix, "ab" is prefix
        assertEquals(2, test.numOfPairs(new String[]{"a", "ba", "ab"}, "aba"));
    }

    @Test
    public void testGiantCase() {
        String[] nums = new String[1000];
        for (int i = 0; i < 1000; i++) nums[i] = "a";
        // target "aa": each pair (i,j) where i!=j, all are valid
        // count = 1000 * 999 = but algorithm counts differently
        // Let's compute: for each element, it's a prefix of "aa" (length 1 matches "a"), 
        // and it's a suffix of "aa" (length 1 matches "a")
        // For each element at position k: prefix count adds suffix.getOrDefault(1,0) which is count of suffixes seen so far
        // and suffix count adds prefix.getOrDefault(1,0) which is count of prefixes seen so far
        // This gives 0+0, 1+1, 2+2, 3+3, ... = 2*(0+1+2+...+999) = 2*499500 = 999000
        assertEquals(999000, test.numOfPairs(nums, "aa"));
    }
}
