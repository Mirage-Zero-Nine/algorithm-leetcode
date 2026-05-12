package solution.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MyPow50Test {

    private final MyPow_50 test = new MyPow_50();

    @Test
    public void testHappyCases() {
        assertEquals(8.0, test.myPow(2.0, 3), 0.0001);
        assertEquals(9.0, test.myPow(3.0, 2), 0.0001);
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0.5, test.myPow(2.0, -1), 0.0001);
        assertEquals(1.0, test.myPow(1.0, Integer.MIN_VALUE), 0.0001);
    }

    @Test
    public void testLargeCase() {
        assertEquals(1024.0, test.myPow(2.0, 10), 0.0001);
        assertEquals(0.0009765625, test.myPow(2.0, -10), 0.0000001);
    }
}
