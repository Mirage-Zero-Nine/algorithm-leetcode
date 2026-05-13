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

    @Test
    public void testHappyWrapAround() {
        // 3 servers, requests at 1,2,3 with load 3 each
        // req0 -> server 0, req1 -> server 1, req2 -> server 2
        List<Integer> result = test.busiestServers(3, new int[]{1, 2, 3}, new int[]{3, 3, 3});
        assertEquals(3, result.size());
    }

    @Test
    public void testHappyAllServersBusy() {
        // 2 servers, 4 requests with short load, all servers handle 2 each
        List<Integer> result = test.busiestServers(2, new int[]{1, 2, 3, 4}, new int[]{1, 1, 1, 1});
        assertTrue(result.containsAll(List.of(0, 1)));
    }

    @Test
    public void testNegativeAllDropped() {
        // 1 server, all requests overlap
        List<Integer> result = test.busiestServers(1, new int[]{1, 2, 3}, new int[]{10, 10, 10});
        assertEquals(List.of(0), result);
    }

    @Test
    public void testEdgeSingleRequest() {
        assertEquals(List.of(0), test.busiestServers(5, new int[]{1}, new int[]{1}));
    }

    @Test
    public void testEdgeTwoServersAlternating() {
        // Requests alternate between 2 servers
        List<Integer> result = test.busiestServers(2, new int[]{1, 2, 3, 4}, new int[]{1, 1, 1, 1});
        assertTrue(result.containsAll(List.of(0, 1)));
    }

    @Test
    public void testGiantCase() {
        int n = 1000;
        int[] arrival = new int[n];
        int[] load = new int[n];
        for (int i = 0; i < n; i++) {
            arrival[i] = i + 1;
            load[i] = 1;
        }
        List<Integer> result = test.busiestServers(10, arrival, load);
        // With load=1 and sequential arrivals, all 10 servers should be equally busy
        assertEquals(10, result.size());
    }
}
