package solutions.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class PathInZigZagTree_1104Test {

    private final PathInZigZagTree_1104 test = new PathInZigZagTree_1104();

    @Test
    public void testHappyCases() {
        assertEquals(List.of(1, 3, 4, 14), test.pathInZigZagTree(14));
        assertEquals(List.of(1, 2, 6, 10), test.pathInZigZagTree(10));
    }

    @Test
    public void testEdgeCases() {
        assertEquals(List.of(1), test.pathInZigZagTree(1));
        assertEquals(List.of(1, 2), test.pathInZigZagTree(2));
    }

    @Test
    public void testLargeCase() {
        assertEquals(List.of(1, 2, 6, 10, 27), test.pathInZigZagTree(27));
    }

    @Test
    public void testLabel3() {
        assertEquals(List.of(1, 3), test.pathInZigZagTree(3));
    }

    @Test
    public void testLabel4() {
        assertEquals(List.of(1, 3, 4), test.pathInZigZagTree(4));
    }

    @Test
    public void testLabel7() {
        assertEquals(List.of(1, 2, 7), test.pathInZigZagTree(7));
    }

    @Test
    public void testLabel26() {
        assertEquals(List.of(1, 2, 6, 10, 26), test.pathInZigZagTree(26));
    }

    @Test
    public void testLabel16() {
        assertEquals(List.of(1, 3, 4, 15, 16), test.pathInZigZagTree(16));
    }

    @Test
    public void testGiantLabel() {
        List<Integer> result = test.pathInZigZagTree(1000000);
        assertEquals(1, result.get(0));
        assertEquals(1000000, result.get(result.size() - 1));
    }

    @Test
    public void testLabel5() {
        assertEquals(List.of(1, 3, 5), test.pathInZigZagTree(5));
    }
}
