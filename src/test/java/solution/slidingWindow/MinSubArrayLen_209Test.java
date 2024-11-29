package solution.slidingWindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import solution.slidingwindow.MinSubArrayLen_209;

/**
 * @author BorisMirage
 * Time: 2024/11/29 13:07
 * Created with IntelliJ IDEA
 */

public class MinSubArrayLen_209Test {

    private final MinSubArrayLen_209 test = new MinSubArrayLen_209();

    @Test
    public void test() {
        assertEquals(2, test.minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));
    }

    @Test
    public void test1() {
        assertEquals(1, test.minSubArrayLen(4, new int[]{1, 4, 4}));
    }

    @Test
    public void test3() {
        assertEquals(2, test.minSubArrayLen(5, new int[]{1, 3, 2, 2, 2, 2, 2}));
    }

    @Test
    public void test4() {
        assertEquals(2, test.minSubArrayLen(8, new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    public void test5() {
        assertEquals(1, test.minSubArrayLen(7, new int[]{10}));
    }

    @Test
    public void test6() {
        assertEquals(5, test.minSubArrayLen(10, new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2}));
    }

    @Test
    public void test7() {
        assertEquals(5, test.minSubArrayLen(15, new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    public void test8() {
        assertEquals(1, test.minSubArrayLen(7, new int[]{1, 2, 3, 8}));
    }

    @Test
    public void test9() {
        assertEquals(2, test.minSubArrayLen(6, new int[]{2, 3, 1, 2, 4, 3}));
    }

    @Test
    public void testEmptyArray() {
        assertEquals(0, test.minSubArrayLen(5, new int[]{}));
        assertEquals(0, test.minSubArrayLen(0, new int[]{}));
    }

    @Test
    public void testEqualElementsSumExact() {
        assertEquals(5, test.minSubArrayLen(10, new int[]{2, 2, 2, 2, 2}));
        assertEquals(5, test.minSubArrayLen(5, new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1}));
    }

    @Test
    public void testArrayWithOnesNoMatch() {
        assertEquals(0, test.minSubArrayLen(15, new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1}));
    }

    @Test
    public void testInvalid() {
        assertEquals(0, test.minSubArrayLen(11, new int[]{1, 1, 1, 1, 1, 1, 1, 1}));
    }
}