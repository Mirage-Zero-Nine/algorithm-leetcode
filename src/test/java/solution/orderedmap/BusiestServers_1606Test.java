package solution.orderedmap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

public class BusiestServers_1606Test {

    private final BusiestServers_1606 test = new BusiestServers_1606();

    @Test
    public void testHappyCases() {
        assertEquals(List.of(1), test.busiestServers(3, new int[]{1, 2, 3, 4, 5}, new int[]{5, 2, 3, 3, 3}));
        assertEquals(List.of(0), test.busiestServers(3, new int[]{1, 2, 3, 4}, new int[]{1, 2, 1, 2}));
    }

    @Test
    public void testNegativeCaseWithDroppedRequests() {
        assertEquals(List.of(0, 1), test.busiestServers(2, new int[]{1, 2, 3, 4}, new int[]{10, 10, 10, 10}));
    }

    @Test
    public void testInvalidAndEdgeCases() {
        assertEquals(List.of(0), test.busiestServers(1, new int[]{5}, new int[]{2}));
        assertTrue(test.busiestServers(2, new int[0], new int[0]).containsAll(List.of(0, 1)));
    }

    @Test
    public void testLargeCase() {
        List<Integer> result = test.busiestServers(
            4,
            new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12},
            new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}
        );
        assertEquals(List.of(0, 1, 2, 3), result);
    }
}
