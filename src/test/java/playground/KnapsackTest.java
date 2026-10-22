package playground;

import static org.junit.jupiter.api.Assertions.*;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class KnapsackTest {
    @ParameterizedTest @MethodSource("cases")
    void computesZeroOneOptimum(int[] weights, int[] values, int capacity, int expected) {
        assertEquals(expected, new Knapsack().knapsack(weights, values, capacity));
    }
    static Stream<Arguments> cases() {
        return Stream.of(
            Arguments.of(new int[]{2,2,6,5,4},new int[]{6,3,5,4,6},10,15),
            Arguments.of(new int[]{1},new int[]{7},0,0), Arguments.of(new int[]{1},new int[]{7},1,7),
            Arguments.of(new int[]{1},new int[]{7},5,7), Arguments.of(new int[]{2,3},new int[]{4,5},2,4),
            Arguments.of(new int[]{2,3},new int[]{4,5},3,5), Arguments.of(new int[]{2,3},new int[]{4,5},5,9),
            Arguments.of(new int[]{5,6},new int[]{10,12},4,0), Arguments.of(new int[]{5,6},new int[]{10,12},6,12),
            Arguments.of(new int[]{1,1,1},new int[]{2,3,4},2,7), Arguments.of(new int[]{0,2},new int[]{5,4},2,9),
            Arguments.of(new int[]{3,4,5},new int[]{-1,8,9},4,8), Arguments.of(new int[]{3,4,5},new int[]{4,8,9},8,13),
            Arguments.of(new int[]{10,20,30},new int[]{60,100,120},50,220), Arguments.of(new int[]{2,2,2},new int[]{-1,-2,-3},2,0),
            Arguments.of(new int[]{1,2,3,4},new int[]{1,4,5,7},5,9), Arguments.of(new int[]{4,3,2,1},new int[]{10,8,5,1},6,15),
            Arguments.of(new int[]{6,7,8},new int[]{13,14,15},14,28), Arguments.of(new int[]{2,5,7},new int[]{1,10,14},7,14)
        );
    }
    @org.junit.jupiter.api.Test void rejectsMismatchedArrays() { assertThrows(IllegalArgumentException.class, () -> new Knapsack().knapsack(new int[]{1}, new int[]{1,2}, 2)); }
}
