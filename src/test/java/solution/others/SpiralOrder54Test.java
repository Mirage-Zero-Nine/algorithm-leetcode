package solution.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class SpiralOrder54Test {

    private final SpiralOrder_54 test = new SpiralOrder_54();

    @Test
    public void testHappyCases() {
        assertEquals(List.of(1, 2, 3, 6, 9, 8, 7, 4, 5),
            test.spiralOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(List.of(), test.spiralOrder(new int[][]{}));
        assertEquals(List.of(1), test.spiralOrder(new int[][]{{1}}));
    }

    @Test
    public void testLargeCase() {
        assertEquals(List.of(1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7),
            test.spiralOrder(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}}));
    }
}
