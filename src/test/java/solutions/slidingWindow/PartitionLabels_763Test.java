package solutions.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

public class PartitionLabels_763Test {

    private final PartitionLabels_763 solution = new PartitionLabels_763();

    @Test
    public void testBasicCase() {
        assertEquals(Arrays.asList(9, 7, 8), solution.partitionLabels("ababcbacadefegdehijhklij"));
    }

    @Test
    public void testSingleChar() {
        assertEquals(Collections.singletonList(1), solution.partitionLabels("a"));
    }

    @Test
    public void testAllSameChar() {
        assertEquals(Collections.singletonList(5), solution.partitionLabels("aaaaa"));
    }

    @Test
    public void testAllUniqueChars() {
        assertEquals(Arrays.asList(1, 1, 1, 1), solution.partitionLabels("abcd"));
    }

    @Test
    public void testEmptyString() {
        assertEquals(Collections.emptyList(), solution.partitionLabels(""));
    }

    @Test
    public void testTwoPartitions() {
        assertEquals(Arrays.asList(2, 2), solution.partitionLabels("aabb"));
    }

    @Test
    public void testEntireStringIsOnePartition() {
        assertEquals(Collections.singletonList(3), solution.partitionLabels("aba"));
    }

    @Test
    public void testRepeatedCharsAtEnd() {
        assertEquals(Arrays.asList(5, 1), solution.partitionLabels("abcab" + "d"));
    }

    @Test
    public void testAlternatingChars() {
        assertEquals(Collections.singletonList(5), solution.partitionLabels("ababa"));
    }

    @Test
    public void testThreePartitions() {
        assertEquals(Arrays.asList(2, 2, 2), solution.partitionLabels("aabbcc"));
    }

    @Test
    public void testGiantCase() {
        StringBuilder sb = new StringBuilder();
        // 26 blocks of 1000 chars each, each block uses a unique letter
        for (int i = 0; i < 26; i++) {
            char c = (char) ('a' + i);
            sb.append(String.valueOf(c).repeat(1000));
        }
        List<Integer> result = solution.partitionLabels(sb.toString());
        assertEquals(26, result.size());
        for (int size : result) {
            assertEquals(1000, size);
        }
    }
}
