package playground;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

class LengthOfLongestIncreasingSubarrayTest {
    @ParameterizedTest @MethodSource("cases") void findsLongestStrictRun(int[] input, int expected) { assertEquals(expected, new LengthOfLongestIncreasingSubarray().longestSubarray(input)); }
    static Stream<Arguments> cases() { return Stream.of(
        Arguments.of(new int[]{},0),Arguments.of(new int[]{5},1),Arguments.of(new int[]{1,2},2),Arguments.of(new int[]{2,1},1),
        Arguments.of(new int[]{1,2,3,4},4),Arguments.of(new int[]{4,3,2,1},1),Arguments.of(new int[]{1,1,1},1),
        Arguments.of(new int[]{5,2,4,1,8,9,7},3),Arguments.of(new int[]{1,3,2,4,5,0},3),Arguments.of(new int[]{-3,-2,-1,-1,0},3),
        Arguments.of(new int[]{Integer.MIN_VALUE,0,Integer.MAX_VALUE},3),Arguments.of(new int[]{Integer.MAX_VALUE,Integer.MIN_VALUE},1),
        Arguments.of(new int[]{0,1,0,1,2,3},4),Arguments.of(new int[]{2,3,4,1,2},3),Arguments.of(new int[]{9,8,7,6,5,6},2),
        Arguments.of(new int[]{-2,-1,0,1,-1,0},4),Arguments.of(new int[]{1,2,2,3,4},3),Arguments.of(new int[]{-1,-2,-3},1),
        Arguments.of(new int[]{0,1},2),Arguments.of(new int[]{1,0},1)); }
    @org.junit.jupiter.api.Test void rejectsNull() { assertThrows(IllegalArgumentException.class, () -> new LengthOfLongestIncreasingSubarray().longestSubarray(null)); }
}
