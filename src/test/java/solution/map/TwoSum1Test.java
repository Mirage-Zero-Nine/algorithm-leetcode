package solution.map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author BorisMirage
 * Time: 2022/06/18 11:09
 * Created with IntelliJ IDEA
 */

public class TwoSum1Test {

    private final TwoSum_1 test = new TwoSum_1();

    @Test
    public void testTwoSum() {
        int[] testArray = {3, 5, 6, 8, 7};
        assertArrayEquals(new int[]{0, 1}, Arrays.stream(test.twoSum(testArray, 8)).sorted().toArray());
    }
}