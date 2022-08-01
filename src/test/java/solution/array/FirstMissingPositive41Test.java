package solution.array;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author BorisMirage
 * Time: 2022/06/18 15:59
 * Created with IntelliJ IDEA
 */

public class FirstMissingPositive41Test {
    private final FirstMissingPositive_41 test = new FirstMissingPositive_41();

    @Test
    public void setTest() {
        assertEquals(1, test.firstMissingPositive(new int[]{2, 3, 6, 7}));
        assertEquals(2, test.firstMissingPositive(new int[]{3, 4, -1, 1}));
        assertEquals(1, test.firstMissingPositive(new int[]{7, 8, 9, 11, 12}));
        assertEquals(3, test.firstMissingPositive(new int[]{1, 2, 0}));
        assertEquals(4, test.firstMissingPositive(new int[]{2, 5, 2, 1, 2, 3}));
        assertEquals(2, test.firstMissingPositive(new int[]{3, 4, 0, 1}));
        assertEquals(8, test.firstMissingPositive(new int[]{1, 2, 3, 4, 5, 6, 7}));
        assertEquals(4, test.firstMissingPositive(new int[]{7, 10, 12, 11, 9, 6, 5, 1, 2, 3}));
    }
}