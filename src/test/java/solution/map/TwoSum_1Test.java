package solution.map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

import static util.TestingUtility.readData;

/**
 * @author BorisMirage
 * Time: 2022/06/18 11:09
 * Created with IntelliJ IDEA
 */

public class TwoSum_1Test {

    private final TwoSum_1 test = new TwoSum_1();

    @Test
    public void testTwoSum() {
        int[] testArray = {3, 5, 6, 8, 7};
        assertArrayEquals(new int[]{0, 1}, Arrays.stream(test.twoSum(testArray, 8)).sorted().toArray());
    }

    @Test
    public void testHappyCases() {
        assertPairEquals(new int[]{0, 1}, test.twoSum(new int[]{2, 7, 11, 15}, 9));
        assertPairEquals(new int[]{1, 2}, test.twoSum(new int[]{3, 2, 4}, 6));
        assertPairEquals(new int[]{0, 1}, test.twoSum(new int[]{3, 3}, 6));
        assertPairEquals(new int[]{2, 4}, test.twoSum(new int[]{5, 75, 25, 10, 100}, 125));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertNull(test.twoSum(new int[]{1, 2, 3}, 100));
        assertPairEquals(new int[]{0, 2}, test.twoSum(new int[]{-3, 4, 3, 90}, 0));
        assertPairEquals(new int[]{0, 3}, test.twoSum(new int[]{0, 4, 3, 0}, 0));
        assertPairEquals(new int[]{1, 2}, test.twoSum(new int[]{Integer.MAX_VALUE, -2, 1, Integer.MIN_VALUE}, -1));
    }

    @Test
    public void testGiantCase() {
        int[] nums = new int[1000];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = -1_000_000;
        }
        nums[123] = 111_111;
        nums[987] = 222_222;
        assertPairEquals(new int[]{123, 987}, test.twoSum(nums, 333_333));
    }

    @Test
    public void testNegativeNumbers() {
        assertPairEquals(new int[]{0, 1}, test.twoSum(new int[]{-5, -3, 4}, -8));
    }

    @Test
    public void testZeroTarget() {
        assertPairEquals(new int[]{0, 1}, test.twoSum(new int[]{0, 0, 5}, 0));
    }

    @Test
    public void testLargeNumbers() {
        assertPairEquals(new int[]{0, 1}, test.twoSum(new int[]{Integer.MAX_VALUE, 0}, Integer.MAX_VALUE));
    }

    @Test
    public void testFirstAndLast() {
        assertPairEquals(new int[]{0, 4}, test.twoSum(new int[]{1, 5, 6, 7, 9}, 10));
    }

    @Test
    public void testAdjacentElements() {
        assertPairEquals(new int[]{2, 3}, test.twoSum(new int[]{10, 20, 30, 40}, 70));
    }

    @Test
    public void testSinglePairAvailable() {
        assertPairEquals(new int[]{0, 1}, test.twoSum(new int[]{1, 2}, 3));
    }

    /**
     * Parameterized "no solution" cases. The algorithm must return null
     * when no pair sums to the target.
     */
    @ParameterizedTest(name = "no solution for target {1} in {0}")
    @MethodSource("noSolutionCases")
    public void testNoSolutionCases(int[] nums, int target) {
        assertNull(test.twoSum(nums, target));
    }

    private static Stream<Arguments> noSolutionCases() {
        return Stream.of(
                Arguments.of(new int[]{}, 0),
                Arguments.of(new int[]{5}, 5),
                Arguments.of(new int[]{1, 2}, 100),
                Arguments.of(new int[]{1, 2, 3, 4, 5}, 100),
                Arguments.of(new int[]{-5, -3, -1}, 0),
                Arguments.of(new int[]{0, 0, 0}, 1),
                Arguments.of(new int[]{1, 1, 1, 1}, 3)
        );
    }

    /**
     * Parameterized happy-path cases. Asserts the returned indices are
     * distinct and the elements at those indices sum to the target.
     */
    @ParameterizedTest(name = "twoSum sums to {1}")
    @MethodSource("happyPathCases")
    public void testHappyPathProperty(int[] nums, int target) {
        int[] result = test.twoSum(nums, target);
        assertNotNull(result, "expected a pair");
        assertEquals(2, result.length);
        assertNotEquals(result[0], result[1], "indices must be distinct");
        assertEquals(target, nums[result[0]] + nums[result[1]]);
    }

    private static Stream<Arguments> happyPathCases() {
        return Stream.of(
                Arguments.of(new int[]{1, 2}, 3),
                Arguments.of(new int[]{2, 7, 11, 15}, 9),
                Arguments.of(new int[]{3, 2, 4}, 6),
                Arguments.of(new int[]{3, 3}, 6),
                Arguments.of(new int[]{0, 0}, 0),
                Arguments.of(new int[]{-1, 1}, 0),
                Arguments.of(new int[]{-3, 4, 3, 90}, 0),
                Arguments.of(new int[]{1000000000, 1000000000}, 2000000000),
                Arguments.of(new int[]{5, 75, 25, 10, 100}, 125),
                Arguments.of(new int[]{Integer.MAX_VALUE, -2, 1, Integer.MIN_VALUE}, -1)
        );
    }

    /**
     * Large-scale property-based test using the shared LargeData.txt
     * fixture (line 0 has ~38,000 ints in [0, 10000]). Builds a target
     * from two known indices to guarantee a solution exists, then asserts
     * the returned pair sums to the target. Allows for the possibility
     * that the algorithm finds a different valid pair (LargeData has
     * duplicate values).
     */
    @Test
    public void testLargeArrayFromFixtureFile() throws FileNotFoundException {
        int[] nums = readData(0);
        assertNotNull(nums);
        // Pick a target that is guaranteed achievable: nums[10] + nums[20].
        int target = nums[10] + nums[20];

        int[] result = test.twoSum(nums, target);
        assertNotNull(result);
        assertEquals(2, result.length);
        assertNotEquals(result[0], result[1]);
        assertEquals(target, nums[result[0]] + nums[result[1]]);
    }

    /**
     * Large randomized property test with a fixed seed so failures are
     * reproducible. Builds 50 random arrays of size 5000, plants a known
     * pair, verifies the algorithm finds a valid one.
     */
    @Test
    public void testLargeRandomizedReproducible() {
        Random rng = new Random(42L);
        int trials = 50;
        int n = 5000;

        for (int t = 0; t < trials; t++) {
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = rng.nextInt(2_000_000) - 1_000_000;
            }
            int idxA = rng.nextInt(n);
            int idxB;
            do {
                idxB = rng.nextInt(n);
            } while (idxB == idxA);
            int target = nums[idxA] + nums[idxB];

            int[] result = test.twoSum(nums, target);
            assertNotNull(result, "trial " + t + " expected a pair");
            assertEquals(2, result.length);
            assertNotEquals(result[0], result[1]);
            // Use long arithmetic to avoid overflow in the assertion.
            assertEquals((long) target,
                    (long) nums[result[0]] + (long) nums[result[1]],
                    "trial " + t + " produced wrong sum");
        }
    }

    private void assertPairEquals(int[] expected, int[] actual) {
        assertArrayEquals(expected, Arrays.stream(actual).sorted().toArray());
    }
}
