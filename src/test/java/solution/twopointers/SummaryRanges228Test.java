package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class SummaryRanges228Test {

    private final SummaryRanges_228 test = new SummaryRanges_228();

    @Test
    public void testHappyCases() {
        assertEquals(List.of("0->2", "4->5", "7"), test.summaryRanges(new int[]{0, 1, 2, 4, 5, 7}));
        assertEquals(List.of("0", "2->4", "6", "8->9"), test.summaryRanges(new int[]{0, 2, 3, 4, 6, 8, 9}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(List.of(), test.summaryRanges(null));
        assertEquals(List.of("1"), test.summaryRanges(new int[]{1}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(List.of("1->5"), test.summaryRanges(new int[]{1, 2, 3, 4, 5}));
    }
}
