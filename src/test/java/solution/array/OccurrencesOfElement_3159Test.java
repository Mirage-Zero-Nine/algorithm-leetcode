package solution.array;

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
}