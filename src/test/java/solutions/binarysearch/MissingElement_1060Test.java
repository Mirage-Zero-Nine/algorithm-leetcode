package solutions.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MissingElement_1060Test {

    private final MissingElement_1060 test = new MissingElement_1060();

    @Test
    public void testHappyCases() {
        assertEquals(5, test.missingElement(new int[]{4, 7, 9, 10}, 1));
        assertEquals(8, test.missingElement(new int[]{4, 7, 9, 10}, 3));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(6, test.missingElement(new int[]{1, 2, 4}, 3));
        assertEquals(2, test.missingElement(new int[]{1, 3}, 1));
    }

    @Test
    public void testLargeCase() {
        assertEquals(51, test.missingElement(new int[]{1, 100, 1000, 100000}, 50));
    }

    @Test
    public void testNoMissingInsideArraySoAnswerAfterEnd() {
        assertEquals(6, test.missingElement(new int[]{1, 2, 3, 4, 5}, 1));
    }

    @Test
    public void testFirstMissingWithinInitialGap() {
        assertEquals(11, test.missingElement(new int[]{10, 12, 13}, 1));
    }

    @Test
    public void testKFallsInMiddleGap() {
        assertEquals(16, test.missingElement(new int[]{10, 12, 15, 20}, 4));
    }

    @Test
    public void testKBeyondTotalMissing() {
        assertEquals(24, test.missingElement(new int[]{10, 12, 15, 20}, 11));
    }

    @Test
    public void testLargeNumbersFromMainExample() {
        assertEquals(746431, test.missingElement(
            new int[]{746421, 1033196, 1647541, 4775111, 7769817, 8030384}, 10));
    }

    @Test
    public void testSingleBigGap() {
        assertEquals(1001, test.missingElement(new int[]{1000, 5000}, 1));
    }

    @Test
    public void testGiantCase() {
        int[] nums = new int[1000];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = 1000 + i * 3;
        }
        assertEquals(1001, test.missingElement(nums, 1));
    }
@Test
    public void testEverySmallUniqueSubsetAgainstMissingNumberEnumeration() {
        for (int mask = 1; mask < 256; mask++) {
            int[] values = new int[Integer.bitCount(mask)];
            int index = 0;
            for (int i = 0; i < 8; i++) if ((mask & (1 << i)) != 0) values[index++] = 10 + i;
            for (int k = 1; k <= 12; k++) {
                int missing = 0, candidate = values[0];
                while (missing < k) {
                    candidate++;
                    boolean present = false;
                    for (int value : values) if (value == candidate) present = true;
                    if (!present) missing++;
                }
                assertEquals(candidate, test.missingElement(values, k));
            }
        }
    }

    @Test
    public void testSingleValueStartsMissingCountImmediatelyAfterIt() {
        assertEquals(1007, test.missingElement(new int[]{1000}, 7));
    }

    @Test
    public void testGiantArithmeticGapsAtAndBeyondLastElement() {
        int[] values = new int[10000];
        for (int i = 0; i < values.length; i++) values[i] = 100 + 3 * i;
        assertEquals(30096, test.missingElement(values, 19998));
        assertEquals(30098, test.missingElement(values, 19999));
    }
}
