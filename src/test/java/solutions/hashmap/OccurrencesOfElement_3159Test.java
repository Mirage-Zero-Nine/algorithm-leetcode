package solutions.hashmap;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static util.TestingUtility.executeTestWithMeasuringTime;
import static util.TestingUtility.readData;

import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2024/12/06 14:28
 * Created with IntelliJ IDEA
 */

public class OccurrencesOfElement_3159Test {

    private static final OccurrencesOfElement_3159 test = new OccurrencesOfElement_3159();

    @Test
    public void test() throws Exception {
        assertArrayEquals(new int[]{0, -1, 2, -1}, executeTestWithMeasuringTime(() -> test.occurrencesOfElement(new int[]{1, 3, 1, 7}, new int[]{1, 3, 2, 4}, 1), 10));
    }

    @Test
    public void testInvalid() throws Exception {
        assertArrayEquals(new int[]{-1}, executeTestWithMeasuringTime(() -> test.occurrencesOfElement(new int[]{1, 3, 1, 7}, new int[]{1}, 10), 10));
    }

    @Test
    public void testLarge() throws Exception {
        int[] nums = readData(0), queries = readData(1), expected = readData(2);
        assertArrayEquals(expected, executeTestWithMeasuringTime(() -> test.occurrencesOfElement(nums, queries, 3314), 100));
    }

    @Test
    public void testNoOccurrence() throws Exception {
        assertArrayEquals(
                new int[]{-1, -1},
                executeTestWithMeasuringTime(() -> test.occurrencesOfElement(new int[]{2, 3, 4}, new int[]{1, 2}, 1), 10)
        );
    }

    @Test
    public void testAllElementsMatch() throws Exception {
        assertArrayEquals(
                new int[]{0, 1, 2, 3, -1},
                executeTestWithMeasuringTime(() -> test.occurrencesOfElement(new int[]{5, 5, 5, 5}, new int[]{1, 2, 3, 4, 5}, 5), 10)
        );
    }

    @Test
    public void testSingleElementArrayMatch() throws Exception {
        assertArrayEquals(
                new int[]{0, -1},
                executeTestWithMeasuringTime(() -> test.occurrencesOfElement(new int[]{9}, new int[]{1, 2}, 9), 10)
        );
    }

    @Test
    public void testSingleElementArrayNoMatch() throws Exception {
        assertArrayEquals(
                new int[]{-1},
                executeTestWithMeasuringTime(() -> test.occurrencesOfElement(new int[]{9}, new int[]{1}, 8), 10)
        );
    }

    @Test
    public void testEmptyInputsReturnEmptyOutput() throws Exception {
        assertArrayEquals(new int[]{}, executeTestWithMeasuringTime(() -> test.occurrencesOfElement(new int[]{}, new int[]{1, 2}, 1), 10));
        assertArrayEquals(new int[]{}, executeTestWithMeasuringTime(() -> test.occurrencesOfElement(new int[]{1, 2}, new int[]{}, 1), 10));
        assertArrayEquals(new int[]{}, executeTestWithMeasuringTime(() -> test.occurrencesOfElement(null, new int[]{1}, 1), 10));
        assertArrayEquals(new int[]{}, executeTestWithMeasuringTime(() -> test.occurrencesOfElement(new int[]{1}, null, 1), 10));
    }

    @Test
    public void testMixedQueriesWithRepeatedRequests() throws Exception {
        assertArrayEquals(
                new int[]{1, 3, 1, -1, 5},
                executeTestWithMeasuringTime(() -> test.occurrencesOfElement(
                        new int[]{4, 7, 8, 7, 9, 7},
                        new int[]{1, 2, 1, 4, 3},
                        7
                ), 10)
        );
    }

    @Test
    public void testGiantDeterministicCase() throws Exception {
        int n = 5000;
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = (i % 10 == 0) ? 42 : i;
        }
        int[] queries = new int[]{1, 2, 10, 100, 400, 600};
        assertArrayEquals(
                new int[]{0, 10, 80, 980, 3980, -1},
                executeTestWithMeasuringTime(() -> test.occurrencesOfElement(nums, queries, 42), 50)
        );
    }

    @Test public void testTargetAtLastPosition() { assertArrayEquals(new int[]{2}, test.occurrencesOfElement(new int[]{1,2,9}, new int[]{1}, 9)); }
    @Test public void testQueriesOutOfOrder() { assertArrayEquals(new int[]{4,0,2}, test.occurrencesOfElement(new int[]{7,1,7,2,7}, new int[]{3,1,2}, 7)); }
    @Test public void testNegativeTarget() { assertArrayEquals(new int[]{0,2,-1}, test.occurrencesOfElement(new int[]{-1,0,-1}, new int[]{1,2,3}, -1)); }
    @Test public void testZeroBasedValuesDoNotAffectOccurrenceNumber() { assertArrayEquals(new int[]{0,1}, test.occurrencesOfElement(new int[]{0,0}, new int[]{1,2}, 0)); }
    @Test public void testAllQueriesMissing() { assertArrayEquals(new int[]{-1,-1,-1}, test.occurrencesOfElement(new int[]{3,4}, new int[]{1,2,100}, 2)); }
    @Test public void testManyRepeatedQueries() { assertArrayEquals(new int[]{0,0,0,0}, test.occurrencesOfElement(new int[]{5,8,5}, new int[]{1,1,1,1}, 5)); }
    @Test public void testTargetOccursAtAlternatingPositions() { assertArrayEquals(new int[]{0,2,4}, test.occurrencesOfElement(new int[]{6,0,6,0,6}, new int[]{1,2,3}, 6)); }
    @Test public void testLargeQueryCount() { int[] q = new int[50]; java.util.Arrays.fill(q, 1); int[] expected = new int[50]; java.util.Arrays.fill(expected, 0); assertArrayEquals(expected, test.occurrencesOfElement(new int[]{4}, q, 4)); }
    @Test public void testEmptyQueriesWithMatches() { assertArrayEquals(new int[0], test.occurrencesOfElement(new int[]{1,1}, new int[0], 1)); }
    @Test public void testTargetOnlyOnce() { assertArrayEquals(new int[]{3,-1}, test.occurrencesOfElement(new int[]{2,3,4,8}, new int[]{1,2}, 8)); }
}
