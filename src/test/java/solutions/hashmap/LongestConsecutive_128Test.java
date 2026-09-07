package solutions.hashmap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LongestConsecutive_128Test {

    private final LongestConsecutive_128 test = new LongestConsecutive_128();

    @ParameterizedTest(name = "{0}")
    @MethodSource("knownCases")
    public void returnsExpectedLength(String description, int[] nums, int expected) {
        assertEquals(expected, test.longestConsecutive(nums));
    }

    private static Stream<Arguments> knownCases() {
        return Stream.of(
                arguments("empty input", new int[]{}, 0),
                arguments("single positive value", new int[]{1}, 1),
                arguments("single zero", new int[]{0}, 1),
                arguments("single negative value", new int[]{-1}, 1),
                arguments("unsorted values with one longest sequence", new int[]{100, 4, 200, 1, 3, 2}, 4),
                arguments("long sequence with a duplicate", new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1}, 9),
                arguments("duplicate between consecutive values", new int[]{1, 0, 1, 2}, 3),
                arguments("no consecutive values", new int[]{10, 30, 20, -10}, 1),
                arguments("all values are duplicates", new int[]{5, 5, 5, 5}, 1),
                arguments("duplicate does not extend a sequence", new int[]{1, 2, 0, 1}, 3),
                arguments("duplicate after joining two sequences", new int[]{1, 2, 4, 3, 3}, 4),
                arguments("duplicate boundary values", new int[]{10, 11, 13, 14, 12, 12}, 5),
                arguments("negative-only sequence", new int[]{-5, -3, -4, -10}, 3),
                arguments("sequence crosses zero", new int[]{2, -1, 1, 0, -2}, 5),
                arguments("longest sequence is first", new int[]{1, 2, 3, 4, 20, 22}, 4),
                arguments("longest sequence is in the middle", new int[]{-10, -9, 1, 2, 3, 4, 30}, 4),
                arguments("longest sequence is last", new int[]{-20, 1, 3, 4, 5, 6}, 4),
                arguments("two equally long sequences", new int[]{50, 51, 1, 2}, 2),
                arguments("sequences separated by one missing value", new int[]{1, 2, 4, 5}, 2),
                arguments("sequences separated by multiple gaps", new int[]{1, 3, 5, 7}, 1),
                arguments("values arrive in descending order", new int[]{5, 4, 3, 2, 1}, 5),
                arguments("zero is part of a longer sequence", new int[]{-2, -1, 0, 1, 2, 10}, 5),
                arguments("minimum values allowed by the problem", new int[]{-1_000_000_000, -999_999_999, -999_999_998}, 3),
                arguments("maximum values allowed by the problem", new int[]{999_999_998, 999_999_999, 1_000_000_000}, 3),
                arguments("far-apart values at both limits", new int[]{-1_000_000_000, 1_000_000_000}, 1),
                arguments("large consecutive sequence", consecutiveValues(10_000), 10_000)
        );
    }

    private static int[] consecutiveValues(int length) {
        int[] values = new int[length];
        for (int i = 0; i < length; i++) {
            values[i] = i;
        }
        return values;
    }
}
