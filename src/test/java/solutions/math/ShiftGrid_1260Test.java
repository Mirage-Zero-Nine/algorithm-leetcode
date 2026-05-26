package solutions.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class ShiftGrid_1260Test {

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

    @Test
    public void testSingleElement() {
        assertEquals(List.of(List.of(5)),
            test.shiftGrid(new int[][]{{5}}, 100));
    }

    @Test
    public void testSingleRow() {
        assertEquals(List.of(List.of(4, 1, 2, 3)),
            test.shiftGrid(new int[][]{{1, 2, 3, 4}}, 1));
    }

    @Test
    public void testSingleColumn() {
        assertEquals(List.of(List.of(5), List.of(1), List.of(2), List.of(3), List.of(4)),
            test.shiftGrid(new int[][]{{1}, {2}, {3}, {4}, {5}}, 1));
    }

    @Test
    public void testShiftByFullSize() {
        assertEquals(List.of(List.of(1, 2, 3), List.of(4, 5, 6)),
            test.shiftGrid(new int[][]{{1, 2, 3}, {4, 5, 6}}, 6));
    }

    @Test
    public void testShiftByTwo() {
        assertEquals(List.of(List.of(3, 4, 1), List.of(2, 3, 4)),
            test.shiftGrid(new int[][]{{1, 2, 3}, {4, 3, 4}}, 2));
    }

    @Test
    public void testNegativeAllZeros() {
        assertEquals(List.of(List.of(0, 0), List.of(0, 0)),
            test.shiftGrid(new int[][]{{0, 0}, {0, 0}}, 3));
    }

    @Test
    public void testGiantShift() {
        // 7 elements, shift by 10002 = shift by 10002 % 7 = 5
        assertEquals(List.of(List.of(2), List.of(3), List.of(4), List.of(7), List.of(6), List.of(5), List.of(1)),
            test.shiftGrid(new int[][]{{1}, {2}, {3}, {4}, {7}, {6}, {5}}, 10002));
    }
}
