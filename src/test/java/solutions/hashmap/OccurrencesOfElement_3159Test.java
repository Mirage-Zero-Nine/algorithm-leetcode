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
}
