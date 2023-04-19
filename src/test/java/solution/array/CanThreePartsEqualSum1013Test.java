package solution.array;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2023/04/18 17:54
 * Created with IntelliJ IDEA
 */

public class CanThreePartsEqualSum1013Test {

    private final CanThreePartsEqualSum_1013 test = new CanThreePartsEqualSum_1013();

    @Test
    public void test() {
        int[] array = {0, 2, 1, -6, 6, -7, 9, 1, 2, 0, 1};
        boolean expected = true;
        boolean result = test.canThreePartsEqualSum(array);
        assertEquals(expected, result);

        int[] array2 = {0, 2, 1, -6, 6, 7, 9, -1, 2, 0, 1};
        boolean expected2 = false;
        boolean result2 = test.canThreePartsEqualSum(array2);
        assertEquals(expected2, result2);

        int[] array3 = {3, 3, 6, 5, -2, 2, 5, 1, -9, 4};
        boolean expected3 = true;
        boolean result3 = test.canThreePartsEqualSum(array3);
        assertEquals(expected3, result3);

        int[] array4 = {1, -1, 1, -1};
        boolean expected4 = false;
        boolean result4 = test.canThreePartsEqualSum(array4);
        assertEquals(expected4, result4);
    }
}