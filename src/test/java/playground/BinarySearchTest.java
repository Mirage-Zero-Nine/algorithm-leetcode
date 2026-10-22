package playground;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

class BinarySearchTest {
    private final BinarySearch search = new BinarySearch();

    @Test void findsEveryValueInSortedRange() {
        int[] values = IntStream.rangeClosed(-50, 50).map(i -> i * 2).toArray();
        for (int i = 0; i < values.length; i++) assertEquals(i, search.searchPosition(values, values[i]));
    }
    @Test void returnsMinusOneForAbsentValues() {
        int[] values = {1, 3, 5, 7, 9};
        assertEquals(-1, search.searchPosition(values, 0)); assertEquals(-1, search.searchPosition(values, 2));
        assertEquals(-1, search.searchPosition(values, 10));
    }
    @Test void handlesSmallAndEmptyArrays() {
        assertEquals(-1, search.searchPosition(new int[0], 1)); assertEquals(0, search.searchPosition(new int[]{4}, 4));
        assertEquals(-1, search.searchPosition(new int[]{4}, 3)); assertEquals(-1, search.searchPosition(new int[]{4}, 5));
    }
    @Test void handlesDuplicatesAndIntegerBoundaries() {
        int[] values = {Integer.MIN_VALUE, -1, -1, 0, Integer.MAX_VALUE};
        assertEquals(0, search.searchPosition(values, Integer.MIN_VALUE)); assertEquals(4, search.searchPosition(values, Integer.MAX_VALUE));
        int position = search.searchPosition(values, -1); assertTrue(position == 1 || position == 2);
    }
    @ParameterizedTest @MethodSource("individualCases")
    void findsOrRejectsIndividualTargets(int[] values, int target, int expected) { assertEquals(expected, search.searchPosition(values, target)); }
    static Stream<Arguments> individualCases() { return Stream.of(
        Arguments.of(new int[]{0},0,0),Arguments.of(new int[]{0},1,-1),Arguments.of(new int[]{1,2},1,0),Arguments.of(new int[]{1,2},2,1),Arguments.of(new int[]{1,2},3,-1),
        Arguments.of(new int[]{-3,-2,-1},-3,0),Arguments.of(new int[]{-3,-2,-1},-1,2),Arguments.of(new int[]{-3,-2,-1},0,-1),Arguments.of(new int[]{2,4,6,8},6,2),Arguments.of(new int[]{2,4,6,8},5,-1),
        Arguments.of(new int[]{1,3,5,7,9},7,3),Arguments.of(new int[]{1,3,5,7,9},8,-1),Arguments.of(new int[]{-5,0,5,10},-5,0),Arguments.of(new int[]{-5,0,5,10},10,3),Arguments.of(new int[]{-5,0,5,10},-6,-1),
        Arguments.of(new int[]{1,1,2},1,1),Arguments.of(new int[]{1,1,2},2,2),Arguments.of(new int[]{Integer.MIN_VALUE,0,Integer.MAX_VALUE},Integer.MAX_VALUE,2),Arguments.of(new int[]{Integer.MIN_VALUE,0,Integer.MAX_VALUE},Integer.MIN_VALUE,0),Arguments.of(new int[]{},0,-1)
    ); }
}
