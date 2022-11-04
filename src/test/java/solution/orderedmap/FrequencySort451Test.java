package solution.orderedmap;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2022/11/06 00:07
 * Created with IntelliJ IDEA
 */

public class FrequencySort451Test {

    private final FrequencySort_451 test = new FrequencySort_451();

    @Test
    public void test() {
        assertEquals("eert", test.frequencySort("tree"));
        assertEquals("aaaccc", test.frequencySort("cccaaa"));
        assertEquals("bbAa", test.frequencySort("Aabb"));
    }

    @Test
    public void testBucketSort() {
        assertEquals("eert", test.frequencySortBucketSort("tree"));
        assertEquals("aaaccc", test.frequencySortBucketSort("cccaaa"));
        assertEquals("bbAa", test.frequencySortBucketSort("Aabb"));
    }
}