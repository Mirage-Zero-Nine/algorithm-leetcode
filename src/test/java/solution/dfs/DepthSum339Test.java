package solution.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import library.NestedInteger;
import org.junit.jupiter.api.Test;

public class DepthSum339Test {

    private final DepthSum_339 test = new DepthSum_339();

    @Test
    public void testHappyCases() {
        NestedInteger n1 = new NestedInteger(1), n2 = new NestedInteger(2), n3 = new NestedInteger(3);
        assertEquals(6, test.depthSum(List.of(n1, n2, n3)));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(0, test.depthSum(null));
        assertEquals(0, test.depthSum(List.of()));
    }

    @Test
    public void testLargeCase() {
        NestedInteger n1 = new NestedInteger(1), n2 = new NestedInteger(2);
        assertEquals(3, test.depthSum(List.of(n1, n2)));
    }
}
