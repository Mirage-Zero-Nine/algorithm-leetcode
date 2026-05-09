package solution.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class PathInZigZagTree1104Test {

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
}
