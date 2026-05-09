package solution.orderedmap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2022/11/06 00:07
 * Created with IntelliJ IDEA
 */

public class FrequencySort451Test {

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
