package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2022/08/03 15:52
 * Created with IntelliJ IDEA
 */

public class NumberContainers2349Test {

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
}