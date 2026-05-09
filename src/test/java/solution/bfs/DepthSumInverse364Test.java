package solution.bfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import library.NestedInteger;
import org.junit.jupiter.api.Test;

public class DepthSumInverse364Test {

    private final DepthSumInverse_364 test = new DepthSumInverse_364();

    @Test
    public void testHappyCases() {
        NestedInteger n1 = new NestedInteger(1), n2 = new NestedInteger(1),
                n3 = new NestedInteger(2), n4 = new NestedInteger(1), n5 = new NestedInteger(1);
        assertEquals(6, test.depthSumInverse(List.of(n1, n2, n3, n4, n5)));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.depthSumInverse(null));
        assertEquals(0, test.depthSumInverse(List.of()));
    }

    @Test
    public void testLargeCase() {
        NestedInteger n1 = new NestedInteger(1), n2 = new NestedInteger(2), n3 = new NestedInteger(3);
        assertEquals(6, test.depthSumInverse(List.of(n1, n2, n3)));
    }
}
