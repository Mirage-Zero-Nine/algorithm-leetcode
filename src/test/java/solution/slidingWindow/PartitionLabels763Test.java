package solution.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class PartitionLabels763Test {

    private final PartitionLabels_763 test = new PartitionLabels_763();

    @Test
    public void testHappyCases() {
        assertEquals(List.of(9, 7, 8), test.partitionLabels("ababcbacadefegdehijhklij"));
        assertEquals(List.of(1, 1, 1), test.partitionLabels("abc"));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(List.of(), test.partitionLabels(""));
        assertEquals(List.of(1), test.partitionLabels("a"));
    }

    @Test
    public void testLargeCase() {
        assertEquals(List.of(2, 2, 2, 2, 2), test.partitionLabels("aabbccddee"));
    }

    @Test
    public void testSinglePartition() {
        assertEquals(List.of(5), test.partitionLabels("abcba"));
    }

    @Test
    public void testAllSameChar() {
        assertEquals(List.of(5), test.partitionLabels("aaaaa"));
    }

    @Test
    public void testEachCharUnique() {
        assertEquals(List.of(1, 1, 1, 1, 1), test.partitionLabels("abcde"));
    }

    @Test
    public void testTwoPartitions() {
        assertEquals(List.of(4, 4), test.partitionLabels("abbacddc"));
    }

    @Test
    public void testRepeatedSingleChar() {
        assertEquals(List.of(1, 1, 1), test.partitionLabels("xyz"));
    }

    @Test
    public void testLastCharSpansAll() {
        assertEquals(List.of(5), test.partitionLabels("abcda"));
    }

    @Test
    public void testGiantCase() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append((char) ('a' + (i % 26)));
        }
        // last char of each letter is at position 999-25+i%26... entire string is one partition
        // since 'a' appears at 0 and at 26*38=988, etc. All letters interleave -> single partition
        List<Integer> result = test.partitionLabels(sb.toString());
        // The string has all 26 letters repeating, so it's one big partition
        assertEquals(List.of(1000), result);
    }
}
