package solutions.heap;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TopKFrequent_347Test {
    private final TopKFrequent_347 solver = new TopKFrequent_347();

    @Test
    void returnsExpectedValuesForDistinctFrequencies() {
        assertBothApproachesReturn(new int[]{1, 1, 1, 2, 2, 3}, 2, Set.of(1, 2));
    }

    @Test
    void returnsTheOnlyDistinctValue() {
        assertBothApproachesReturn(new int[]{7, 7, 7, 7}, 1, Set.of(7));
    }

    @Test
    void supportsNegativeValuesZeroAndIntegerExtremes() {
        assertBothApproachesReturn(
                new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE,
                        Integer.MAX_VALUE, Integer.MAX_VALUE, 0, -1},
                2,
                Set.of(Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    @Test
    void returnsAllDistinctValuesWhenKEqualsDistinctCount() {
        assertBothApproachesReturn(new int[]{4, 4, 3, 2, 2, 1}, 4, Set.of(1, 2, 3, 4));
    }

    @Test
    void supportsKEqualToInputLengthWhenEveryValueIsDistinct() {
        assertBothApproachesReturn(new int[]{9, -1, 0, 5}, 4, Set.of(-1, 0, 5, 9));
    }

    @Test
    void acceptsAnyValidValuesWhenFrequenciesTie() {
        assertBothApproachesProduceTopK(new int[]{1, 1, 2, 2, 3, 3, 4, 4}, 2);
    }

    @Test
    void acceptsEitherValueAtTheFrequencyCutoff() {
        assertBothApproachesProduceTopK(new int[]{1, 1, 1, 1, 2, 2, 2, 3, 3, 4, 4}, 3);
    }

    @Test
    void handlesAnInputOfOneElement() {
        assertBothApproachesReturn(new int[]{42}, 1, Set.of(42));
    }

    @Test
    void doesNotModifyTheInput() {
        int[] nums = {5, 5, 4, 4, 4, 3, 2, 1};
        int[] original = nums.clone();

        assertBothApproachesProduceTopK(nums, 2);

        assertArrayEquals(original, nums);
    }

    @Test
    void handlesAHighNumberOfDistinctValues() {
        int[] nums = new int[5_050];
        int index = 0;
        for (int value = 0; value < 100; value++) {
            for (int count = 0; count < 100 - value; count++) {
                nums[index++] = value;
            }
        }

        assertBothApproachesReturn(nums, 5, Set.of(0, 1, 2, 3, 4));
    }

    @Test
    void supportsEveryValidKForMixedFrequencies() {
        int[] nums = {10, 10, 10, 10, 20, 20, 20, 30, 30, 40, 50, 50};

        for (int k = 1; k <= 5; k++) {
            assertBothApproachesProduceTopK(nums, k);
        }
    }

    @Test
    void supportsDeterministicGeneratedInputs() {
        Random random = new Random(347);

        for (int testCase = 0; testCase < 50; testCase++) {
            int length = 1 + random.nextInt(200);
            int distinctValueRange = 1 + random.nextInt(Math.min(length, 20));
            int[] nums = new int[length];
            for (int index = 0; index < length; index++) {
                nums[index] = random.nextInt(distinctValueRange) - distinctValueRange / 2;
            }

            for (int k = 1; k <= distinctValueCount(nums); k++) {
                assertBothApproachesProduceTopK(nums, k);
            }
        }
    }

    @Test
    void returnsEmptyArrayForNullInput() {
        assertBothApproachesReturnEmpty(null, 1);
    }

    @Test
    void returnsEmptyArrayForEmptyInput() {
        assertBothApproachesReturnEmpty(new int[0], 1);
    }

    @Test
    void returnsEmptyArrayWhenKExceedsInputLength() {
        assertBothApproachesReturnEmpty(new int[]{1, 1}, 3);
    }

    private void assertBothApproachesReturn(int[] nums, int k, Set<Integer> expected) {
        int[] heapResult = solver.topKFrequent(nums, k);
        int[] bucketResult = solver.topKFrequentBucketSort(nums, k);

        assertTopKResult(nums, k, heapResult);
        assertTopKResult(nums, k, bucketResult);
        assertEquals(expected, asSet(heapResult), "heap result");
        assertEquals(expected, asSet(bucketResult), "bucket-sort result");
    }

    private void assertBothApproachesProduceTopK(int[] nums, int k) {
        assertTopKResult(nums, k, solver.topKFrequent(nums, k));
        assertTopKResult(nums, k, solver.topKFrequentBucketSort(nums, k));
    }

    private void assertBothApproachesReturnEmpty(int[] nums, int k) {
        assertArrayEquals(new int[0], solver.topKFrequent(nums, k), "heap result");
        assertArrayEquals(new int[0], solver.topKFrequentBucketSort(nums, k), "bucket-sort result");
    }

    private void assertTopKResult(int[] nums, int k, int[] result) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : nums) {
            counts.merge(num, 1, Integer::sum);
        }

        Set<Integer> selected = asSet(result);
        assertEquals(k, result.length, "result length");
        assertEquals(k, selected.size(), "result must not contain duplicates");
        assertTrue(counts.keySet().containsAll(selected), "result contains a value absent from the input");

        int minimumSelectedFrequency = selected.stream().mapToInt(counts::get).min().orElseThrow();
        assertTrue(
                counts.entrySet().stream()
                        .filter(entry -> !selected.contains(entry.getKey()))
                        .noneMatch(entry -> entry.getValue() > minimumSelectedFrequency),
                "result omitted a value that is more frequent than a selected value"
        );
    }

    private Set<Integer> asSet(int[] values) {
        Set<Integer> result = new HashSet<>();
        for (int value : values) {
            result.add(value);
        }
        return result;
    }

    private int distinctValueCount(int[] nums) {
        Set<Integer> values = new HashSet<>();
        for (int num : nums) {
            values.add(num);
        }
        return values.size();
    }
}
