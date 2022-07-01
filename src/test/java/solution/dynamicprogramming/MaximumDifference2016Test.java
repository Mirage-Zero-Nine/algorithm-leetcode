package solution.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2022/06/30 20:39
 * Created with IntelliJ IDEA
 */

public class MaximumDifference2016Test {
    private final MaximumDifference_2016 test = new MaximumDifference_2016();

    @Test
    public void test() {
        assertEquals(4, test.maximumDifference(new int[]{7, 1, 5, 4}));
        assertEquals(4, test.maximumDifference(new int[]{9, 4, 3, 2}));
        assertEquals(4, test.maximumDifference(new int[]{1, 5, 2, 10}));
    }
}