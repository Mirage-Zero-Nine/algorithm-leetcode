package solution.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class SummaryRanges_228Test {

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

    @Test
    public void testAdditionalHappyCases() {
        assertEquals(List.of("-3->1"), test.summaryRanges(new int[]{-3, -2, -1, 0, 1}));
        assertEquals(List.of("5", "7", "9"), test.summaryRanges(new int[]{5, 7, 9}));
        assertEquals(List.of("10->12", "15->16"), test.summaryRanges(new int[]{10, 11, 12, 15, 16}));
    }

    @Test
    public void testAdditionalEdgeCases() {
        assertEquals(List.of(), test.summaryRanges(new int[]{}));
        assertEquals(List.of("-1"), test.summaryRanges(new int[]{-1}));
        assertEquals(List.of("0->1", "3"), test.summaryRanges(new int[]{0, 1, 3}));
    }

    @Test
    public void testAdditionalGiantCase() {
        assertEquals(
            List.of("1->10", "20->25", "30->35"),
            test.summaryRanges(new int[]{1,2,3,4,5,6,7,8,9,10,20,21,22,23,24,25,30,31,32,33,34,35})
        );
    }

    @Test
    public void testTwoConsecutive() {
        assertEquals(List.of("1->2"), test.summaryRanges(new int[]{1, 2}));
    }

    @Test
    public void testLargeGaps() {
        assertEquals(List.of("-100", "0", "100"), test.summaryRanges(new int[]{-100, 0, 100}));
    }

    @Test
    public void testGiantContinuousRange() {
        int[] arr = new int[1000];
        for (int i = 0; i < 1000; i++) arr[i] = i;
        assertEquals(List.of("0->999"), test.summaryRanges(arr));
    }

    @Test
    public void testNegativeNumbers() {
        assertEquals(List.of("-5->-3", "0", "2->3"), test.summaryRanges(new int[]{-5, -4, -3, 0, 2, 3}));
    }
}
