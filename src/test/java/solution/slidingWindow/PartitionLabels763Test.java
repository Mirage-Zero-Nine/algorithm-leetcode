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
}
