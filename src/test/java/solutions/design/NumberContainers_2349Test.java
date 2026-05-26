package solutions.design;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2022/08/03 15:52
 * Created with IntelliJ IDEA
 */

public class NumberContainers_2349Test {

    private NumberContainers_2349 test;

    @BeforeEach
    public void setUp() {
        test = new NumberContainers_2349();
    }

    @Test
    public void test() {
        test.change(1, 10);
        assertEquals(test.find(10), 1);
        test.change(1, 20);
        assertEquals(test.find(10), -1);
        assertEquals(test.find(20), 1);
        assertEquals(test.find(30), -1);
    }

    @Test
    public void test1() {
        assertEquals(test.find(10), -1);
        test.change(2, 10);
        test.change(1, 10);
        test.change(3, 10);
        test.change(5, 10);
        assertEquals(test.find(10), 1);
        test.change(1, 20);
        assertEquals(test.find(10), 2);
    }

    @Test
    public void testReplaceWithSameValueKeepsIndex() {
        test.change(4, 7);
        test.change(4, 7);
        assertEquals(4, test.find(7));
    }

    @Test
    public void testFindReturnsSmallestIndex() {
        test.change(9, 3);
        test.change(2, 3);
        test.change(6, 3);
        assertEquals(2, test.find(3));
    }

    @Test
    public void testReplacingSmallestIndexMovesMinimum() {
        test.change(1, 8);
        test.change(2, 8);
        assertEquals(1, test.find(8));
        test.change(1, 9);
        assertEquals(2, test.find(8));
        assertEquals(1, test.find(9));
    }

    @Test
    public void testMultipleNumbersIndependent() {
        test.change(1, 100);
        test.change(2, 200);
        test.change(3, 100);
        assertEquals(1, test.find(100));
        assertEquals(2, test.find(200));
    }

    @Test
    public void testNegativeAndZeroValues() {
        test.change(5, 0);
        test.change(3, -1);
        test.change(1, -1);
        assertEquals(5, test.find(0));
        assertEquals(1, test.find(-1));
    }

    @Test
    public void testOverwriteChain() {
        test.change(10, 1);
        test.change(10, 2);
        test.change(10, 3);
        assertEquals(-1, test.find(1));
        assertEquals(-1, test.find(2));
        assertEquals(10, test.find(3));
    }

    @Test
    public void testSparseLargeIndex() {
        test.change(1_000_000_000, 42);
        assertEquals(1_000_000_000, test.find(42));
    }

    @Test
    public void testGiantCase() {
        for (int i = 1; i <= 5000; i++) {
            test.change(i, i % 5);
        }
        assertEquals(5, test.find(0));
        assertEquals(1, test.find(1));
        assertEquals(2, test.find(2));
        assertEquals(3, test.find(3));
        assertEquals(4, test.find(4));

        for (int i = 1; i <= 1000; i++) {
            test.change(i, 99);
        }
        assertEquals(1001, test.find(1));
        assertEquals(1, test.find(99));
    }
}
