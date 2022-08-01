package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2022/07/31 18:18
 * Created with IntelliJ IDEA
 */

public class NumArray307Test {
    private NumArray_307 test;

    @Test
    public void test() {
        test = new NumArray_307(new int[]{0, 9, 5, 7, 3});
        assertEquals(test.sumRange(4, 4), 3);
    }

    @Test
    public void test1() {
        test = new NumArray_307(new int[]{1, 3, 5});
        assertEquals(test.sumRange(0, 2), 9);
        test.update(1, 2);
        assertEquals(test.sumRange(0, 2), 8);
    }
}