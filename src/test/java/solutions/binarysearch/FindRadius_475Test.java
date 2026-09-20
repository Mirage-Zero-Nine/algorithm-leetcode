package solutions.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/** Contract tests for the binary-search solution to LeetCode 475. */
public class FindRadius_475Test {

    private final FindRadius_475 solution = new FindRadius_475();

    @ParameterizedTest(name = "{0}")
    @MethodSource("representativeCases")
    void representativeCasesUseAnIndependentOracle(String name, int[] houses, int[] heaters, int expected) {
        assertEquals(expected, bruteForceRadius(houses, heaters), name + " oracle");
        assertEquals(expected, solution.findRadius(houses.clone(), heaters.clone()), name);
    }

    private static Stream<Arguments> representativeCases() {
        return Stream.of(
                Arguments.of("official example 1", new int[]{1, 2, 3}, new int[]{2}, 1),
                Arguments.of("official example 2", new int[]{1, 2, 3, 4}, new int[]{1, 4}, 1),
                Arguments.of("official example 3", new int[]{1, 5}, new int[]{2}, 3),
                Arguments.of("one house exactly at a heater", new int[]{7}, new int[]{7}, 0),
                Arguments.of("historical singleton edge", new int[]{1}, new int[]{1}, 0),
                Arguments.of("historical one-heater edge", new int[]{1, 2, 3}, new int[]{1}, 2),
                Arguments.of("every house exactly matches a heater", new int[]{0, 4, 9}, new int[]{9, 0, 4}, 0),
                Arguments.of("one heater to the right", new int[]{1, 2, 3}, new int[]{5}, 4),
                Arguments.of("one heater to the left", new int[]{5, 6, 10}, new int[]{2}, 8),
                Arguments.of("house centered between heaters", new int[]{0, 10}, new int[]{5}, 5),
                Arguments.of("asymmetric gaps and outside houses", new int[]{0, 7, 10}, new int[]{3, 8}, 3),
                Arguments.of("widest interior gap determines radius", new int[]{0, 100, 200}, new int[]{0, 200}, 100),
                Arguments.of("historical nine-house gap case", new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, new int[]{1, 9}, 4),
                Arguments.of("houses need not be sorted", new int[]{10, 1, 5}, new int[]{3, 8}, 2),
                Arguments.of("heaters are sorted before binary search", new int[]{1, 2, 3, 4}, new int[]{4, 1}, 1),
                Arguments.of("historical outside-range case", new int[]{1, 10}, new int[]{5}, 5),
                Arguments.of("duplicate heaters", new int[]{1, 2, 3}, new int[]{2, 2, 2}, 1),
                Arguments.of("duplicate houses", new int[]{1, 1, 4, 4}, new int[]{2, 3}, 1),
                Arguments.of("duplicate exact heater positions", new int[]{7, 7}, new int[]{7, 7}, 0),
                Arguments.of("clustered heaters and distant house", new int[]{0, 1, 2, 100}, new int[]{1, 2}, 98),
                Arguments.of("several alternating nearest heaters", new int[]{5, 10, 15, 20, 25}, new int[]{0, 12, 30}, 8),
                Arguments.of("zero and maximum class-supported coordinate", new int[]{0, 1_000_000_000}, new int[]{500_000_000}, 500_000_000),
                Arguments.of("maximum endpoints have exact heaters", new int[]{0, 1_000_000_000}, new int[]{0, 1_000_000_000}, 0),
                Arguments.of("near maximum endpoint", new int[]{999_999_999, 1_000_000_000}, new int[]{1_000_000_000}, 1),
                Arguments.of("both houses outside the heater interval", new int[]{1, 10, 20}, new int[]{5, 15}, 5),
                Arguments.of("historical many-house many-heater case", new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, new int[]{2, 5, 8, 10}, 1),
                Arguments.of("historical even-spacing large case", IntStream.range(0, 200).map(i -> i * 2).toArray(),
                        IntStream.range(0, 20).map(i -> i * 20).toArray(), 18),
                Arguments.of("uneven duplicates and ties", new int[]{0, 2, 4, 6, 8}, new int[]{1, 5, 5, 9}, 1)
        );
    }

    @Test
    void exhaustiveSmallCoordinatesCheckTheIndependentOracle() {
        int[][] houseSets = {
            {0},
            {1, 1},
            {0, 2, 4},
            {4, 0, 2, 2},
            {0, 1, 2, 3, 4},
            {0, 0, 5, 5, 10},
            {2, 6, 9, 13}
        };
        int[][] heaterSets = {
            {0},
            {2},
            {0, 4},
            {1, 5},
            {3, 7},
            {0, 5, 10},
            {13, 2}
        };

        for (int[] houses : houseSets) {
            for (int[] heaters : heaterSets) {
                int expected = bruteForceRadius(houses, heaters);
                assertEquals(expected, solution.findRadius(houses.clone(), heaters.clone()),
                        () -> "houses=" + Arrays.toString(houses) + ", heaters=" + Arrays.toString(heaters));
            }
        }
    }

    @Test
    void repeatedCallsRemainIndependentWhenHeatersWereInitiallyUnsorted() {
        int[] houses = {1, 10, 17};
        int[] heaters = {20, 3, 11};
        int expected = bruteForceRadius(houses, heaters);

        assertEquals(expected, solution.findRadius(houses.clone(), heaters.clone()));
        assertEquals(expected, solution.findRadius(houses.clone(), heaters.clone()));
        assertEquals(0, solution.findRadius(new int[]{42}, new int[]{42}));
        assertEquals(expected, solution.findRadius(houses.clone(), heaters.clone()));
    }

    @Test
    void maximumLeetCodeSizedInputsAreCheckedWithoutAQuadraticProductionOracle() {
        int[] houses = IntStream.range(0, 30_000).toArray();
        int[] heaters = IntStream.range(0, 10_000).map(i -> i * 3).toArray();

        assertEquals(2, solution.findRadius(houses, heaters));
    }

    @Test
    void denseMaximumSizedDuplicateInputsStillUseTheCorrectBoundaryDistance() {
        int[] houses = new int[30_000];
        int[] heaters = new int[30_000];
        Arrays.fill(houses, 123_456_789);
        Arrays.fill(heaters, 123_456_789);

        assertEquals(0, solution.findRadius(houses, heaters));
    }

    /** Independent O(houses * heaters) oracle; long subtraction avoids reproducing int overflow. */
    private static int bruteForceRadius(int[] houses, int[] heaters) {
        long requiredRadius = 0;
        for (int house : houses) {
            long nearest = Long.MAX_VALUE;
            for (int heater : heaters) {
                nearest = Math.min(nearest, Math.abs((long) house - heater));
            }
            requiredRadius = Math.max(requiredRadius, nearest);
        }
        return Math.toIntExact(requiredRadius);
    }
}
