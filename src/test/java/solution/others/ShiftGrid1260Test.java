package solution.others;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class ShiftGrid1260Test {

    private final ShiftGrid_1260 test = new ShiftGrid_1260();

    @Test
    public void testHappyCases() {
        assertEquals(List.of(List.of(4, 1), List.of(2, 3)),
            test.shiftGrid(new int[][]{{1, 2}, {3, 4}}, 1));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(List.of(List.of(1, 2), List.of(3, 4)),
            test.shiftGrid(new int[][]{{1, 2}, {3, 4}}, 0));
        assertEquals(List.of(List.of(1, 2), List.of(3, 4)),
            test.shiftGrid(new int[][]{{1, 2}, {3, 4}}, 4));
    }

    @Test
    public void testLargeCase() {
        assertEquals(List.of(List.of(9, 1, 2), List.of(3, 4, 5), List.of(6, 7, 8)),
            test.shiftGrid(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, 1));
    }
}
