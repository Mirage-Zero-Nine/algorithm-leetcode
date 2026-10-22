package playground;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * @author BorisMirage
 * Time: 2022/06/18 21:20
 * Created with IntelliJ IDEA
 */

public class DistinctIdsTest {

    private final DistinctIds test = new DistinctIds();

    @ParameterizedTest
    @MethodSource("cases")
    public void removesLeastFrequentIds(int[] arr, int removed, int expected) {
        assertEquals(expected, test.distinctIds(arr, arr.length, removed));
    }

    static Stream<Arguments> cases() {
        return Stream.of(
                Arguments.of(new int[]{1,2,3,1,2,2,3}, 6, 1),
                Arguments.of(new int[]{1,1,1}, 0, 1), Arguments.of(new int[]{1,1,1}, 1, 1),
                Arguments.of(new int[]{1,1,1}, 3, 0), Arguments.of(new int[]{1,2,3}, 1, 2),
                Arguments.of(new int[]{1,2,3}, 2, 1), Arguments.of(new int[]{1,2,3}, 3, 0),
                Arguments.of(new int[]{1,2,3}, 10, 0), Arguments.of(new int[]{}, 5, 0),
                Arguments.of(new int[]{-1,-1,0,2}, 2, 1), Arguments.of(new int[]{4,4,5,5,6}, 2, 2),
                Arguments.of(new int[]{4,4,5,5,6}, 4, 1), Arguments.of(new int[]{4,4,5,5,6}, 1, 2),
                Arguments.of(new int[]{7,7,7,8,9,9}, 3, 1), Arguments.of(new int[]{7,7,7,8,9,9}, 4, 1),
                Arguments.of(new int[]{1,2,2,3,3,3}, 2, 2), Arguments.of(new int[]{1,2,2,3,3,3}, 5, 1),
                Arguments.of(new int[]{Integer.MIN_VALUE,Integer.MAX_VALUE}, 1, 1),
                Arguments.of(new int[]{5,5,5,5}, 2, 1), Arguments.of(new int[]{1,2,3,4,5}, 0, 5)
        );
    }
}
