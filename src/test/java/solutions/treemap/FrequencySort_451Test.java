package solutions.treemap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2022/11/06 00:07
 * Created with IntelliJ IDEA
 */

public class FrequencySort_451Test {

    private final FrequencySort_451 test = new FrequencySort_451();

    @Test
    public void testHappyCases() {
        assertFrequencySorted("tree", test.frequencySort("tree"));
        assertFrequencySorted("cccaaa", test.frequencySort("cccaaa"));
    }

    @Test
    public void testNegativeTieCase() {
        assertFrequencySorted("Aabb", test.frequencySort("Aabb"));
        assertFrequencySorted("Aabb", test.frequencySortBucketSort("Aabb"));
    }

    @Test
    public void testInvalidAndEdgeCases() {
        assertNull(test.frequencySort(null));
        assertNull(test.frequencySortBucketSort(null));
        assertEquals("", test.frequencySort(""));
        assertEquals("", test.frequencySortBucketSort(""));
        assertEquals("a", test.frequencySort("a"));
        assertEquals("a", test.frequencySortBucketSort("a"));
    }

    @Test
    public void testLargeCase() {
        String input = "a".repeat(40) + "b".repeat(25) + "c".repeat(10);
        assertFrequencySorted(input, test.frequencySort(input));
        assertFrequencySorted(input, test.frequencySortBucketSort(input));
    }

    @Test
    public void testHappyAllDistinct() {
        assertFrequencySorted("abcdef", test.frequencySort("abcdef"));
        assertFrequencySorted("abcdef", test.frequencySortBucketSort("abcdef"));
    }

    @Test
    public void testHappyDigitsAndLetters() {
        String input = "112233aabb";
        assertFrequencySorted(input, test.frequencySort(input));
        assertFrequencySorted(input, test.frequencySortBucketSort(input));
    }

    @Test
    public void testHappyAllSameChar() {
        assertEquals("aaaa", test.frequencySort("aaaa"));
        assertEquals("aaaa", test.frequencySortBucketSort("aaaa"));
    }

    @Test
    public void testNegativeCaseSensitive() {
        // 'A' and 'a' are different characters
        String input = "AaAa";
        String result = test.frequencySort(input);
        assertFrequencySorted(input, result);
    }

    @Test
    public void testEdgeTwoChars() {
        assertEquals("aa", test.frequencySort("aa"));
        assertEquals("aa", test.frequencySortBucketSort("aa"));
    }

    @Test
    public void testGiantCase() {
        String input = "x".repeat(5000) + "y".repeat(3000) + "z".repeat(1000);
        String result = test.frequencySort(input);
        assertFrequencySorted(input, result);
        assertTrue(result.startsWith("x"));

        String result2 = test.frequencySortBucketSort(input);
        assertFrequencySorted(input, result2);
        assertTrue(result2.startsWith("x"));
    }

    @Test
    public void testBothApproachesWithWhitespace() {
        String input = "  aa bb  ";
        assertFrequencySorted(input, test.frequencySort(input));
        assertFrequencySorted(input, test.frequencySortBucketSort(input));
    }

    @Test
    public void testBothApproachesWithPunctuation() {
        String input = "!!??!!.";
        assertFrequencySorted(input, test.frequencySort(input));
        assertFrequencySorted(input, test.frequencySortBucketSort(input));
    }

    @Test
    public void testBothApproachesWithMixedCase() {
        String input = "aAaBbBaaa";
        assertFrequencySorted(input, test.frequencySort(input));
        assertFrequencySorted(input, test.frequencySortBucketSort(input));
    }

    @Test
    public void testTieGroupsRemainValidForBothApproaches() {
        String input = "aabbccddeeff";
        assertFrequencySorted(input, test.frequencySort(input));
        assertFrequencySorted(input, test.frequencySortBucketSort(input));
    }

    @Test
    public void testRepeatedCallsDoNotShareState() {
        assertFrequencySorted("aab", test.frequencySort("aab"));
        assertEquals("x", test.frequencySort("x"));
        assertFrequencySorted("aab", test.frequencySortBucketSort("aab"));
        assertEquals("y", test.frequencySortBucketSort("y"));
    }

    @Test
    public void testControlCharactersArePreserved() {
        String input = "\n\n\tA";
        assertFrequencySorted(input, test.frequencySort(input));
        assertFrequencySorted(input, test.frequencySortBucketSort(input));
    }

    @Test
    public void testNumericOnlyInput() {
        String input = "111223333";
        assertFrequencySorted(input, test.frequencySort(input));
        assertFrequencySorted(input, test.frequencySortBucketSort(input));
    }

    @Test
    public void testSingleCharacterBothApproaches() {
        assertEquals("!", test.frequencySort("!"));
        assertEquals("!", test.frequencySortBucketSort("!"));
    }

    @Test
    public void testAllCharactersTie() {
        String input = "abcd";
        assertFrequencySorted(input, test.frequencySort(input));
        assertFrequencySorted(input, test.frequencySortBucketSort(input));
    }

    @Test
    public void testFrequencyOrderWithThreeGroups() {
        String input = "aaabbbcccdde";
        assertFrequencySorted(input, test.frequencySort(input));
        assertFrequencySorted(input, test.frequencySortBucketSort(input));
    }

    private static void assertFrequencySorted(String input, String output) {
        assertEquals(input.length(), output.length());

        int[] inCount = new int[128];
        int[] outCount = new int[128];
        for (char c : input.toCharArray()) {
            inCount[c]++;
        }
        for (char c : output.toCharArray()) {
            outCount[c]++;
        }
        assertEquals(java.util.Arrays.toString(inCount), java.util.Arrays.toString(outCount));

        int previous = Integer.MAX_VALUE;
        int index = 0;
        while (index < output.length()) {
            char current = output.charAt(index);
            int count = 0;
            while (index < output.length() && output.charAt(index) == current) {
                index++;
                count++;
            }
            assertTrue(count <= previous);
            previous = count;
        }
    }
}
