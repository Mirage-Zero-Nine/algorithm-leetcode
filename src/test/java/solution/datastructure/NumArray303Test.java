package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NumArray303Test {

    @Test
    public void testHappyCases() {
        NumArray_303 na = new NumArray_303(new int[]{-2, 0, 3, -5, 2, -1});
        assertEquals(1, na.sumRange(0, 2));
        assertEquals(-1, na.sumRange(2, 5));
        assertEquals(-3, na.sumRange(0, 5));
    }

    @Test
    public void testEdgeCases() {
        NumArray_303 na = new NumArray_303(new int[]{1});
        assertEquals(1, na.sumRange(0, 0));
    }

    @Test
    public void testLargeCase() {
        NumArray_303 na = new NumArray_303(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        assertEquals(55, na.sumRange(0, 9));
        assertEquals(15, na.sumRange(0, 4));
    }
}
