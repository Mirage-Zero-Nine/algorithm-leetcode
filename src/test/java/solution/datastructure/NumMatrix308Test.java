package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NumMatrix308Test {

    @Test
    public void testHappyCases() {
        NumMatrix_308 nm = new NumMatrix_308(new int[][]{{3, 0, 1, 4, 2}, {5, 6, 3, 2, 1}, {1, 2, 0, 1, 5}, {4, 1, 0, 1, 7}, {1, 0, 3, 0, 5}});
        assertEquals(8, nm.sumRegion(2, 1, 4, 3));
        nm.update(3, 2, 2);
        assertEquals(10, nm.sumRegion(2, 1, 4, 3));
    }

    @Test
    public void testEdgeCases() {
        NumMatrix_308 nm = new NumMatrix_308(new int[][]{{1}});
        assertEquals(1, nm.sumRegion(0, 0, 0, 0));
        nm.update(0, 0, 5);
        assertEquals(5, nm.sumRegion(0, 0, 0, 0));
    }

    @Test
    public void testLargeCase() {
        NumMatrix_308 nm = new NumMatrix_308(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        assertEquals(45, nm.sumRegion(0, 0, 2, 2));
        nm.update(1, 1, 10);
        assertEquals(50, nm.sumRegion(0, 0, 2, 2));
    }
}
