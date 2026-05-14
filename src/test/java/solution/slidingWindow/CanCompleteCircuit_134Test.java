package solution.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CanCompleteCircuit_134Test {

    private final CanCompleteCircuit_134 test = new CanCompleteCircuit_134();

    @Test
    public void testHappyCase1() {
        int[] gas = {1, 2, 3, 4, 5};
        int[] cost = {3, 4, 5, 1, 2};
        assertEquals(3, test.canCompleteCircuit(gas, cost));
    }

    @Test
    public void testHappyCase2() {
        int[] gas = {5, 1, 2, 3, 4};
        int[] cost = {4, 4, 1, 5, 1};
        assertEquals(4, test.canCompleteCircuit(gas, cost));
    }

    @Test
    public void testHappyCase3() {
        int[] gas = {2, 3, 4};
        int[] cost = {3, 4, 3};
        // total gas = 9, total cost = 10. Cannot complete.
        assertEquals(-1, test.canCompleteCircuit(gas, cost));
    }

    @Test
    public void testHappyCase4() {
        int[] gas = {1, 2, 3};
        int[] cost = {1, 2, 3};
        assertEquals(0, test.canCompleteCircuit(gas, cost));
    }

    @Test
    public void testNegativeCase1() {
        int[] gas = {1, 2};
        int[] cost = {2, 2};
        assertEquals(-1, test.canCompleteCircuit(gas, cost));
    }

    @Test
    public void testNegativeCase2() {
        int[] gas = {0, 0, 0};
        int[] cost = {1, 1, 1};
        assertEquals(-1, test.canCompleteCircuit(gas, cost));
    }

    @Test
    public void testEdgeCaseSingleStation() {
        assertEquals(0, test.canCompleteCircuit(new int[]{2}, new int[]{1}));
        assertEquals(-1, test.canCompleteCircuit(new int[]{1}, new int[]{2}));
    }

    @Test
    public void testEdgeCaseNullOrEmpty() {
        assertEquals(-1, test.canCompleteCircuit(null, null));
        assertEquals(-1, test.canCompleteCircuit(new int[]{}, new int[]{}));
    }

    @Test
    public void testEdgeCaseStartAtLast() {
        int[] gas = {1, 1, 3};
        int[] cost = {2, 2, 1};
        // total gas = 5, total cost = 5.
        // Station 0: -1
        // Station 1: -1
        // Station 2: 3-1=2. 2+1-2=1. 1+1-2=0. Start at 2.
        assertEquals(2, test.canCompleteCircuit(gas, cost));
    }

    @Test
    public void testGiantCase() {
        int n = 10000;
        int[] gas = new int[n];
        int[] cost = new int[n];
        for (int i = 0; i < n - 1; i++) {
            gas[i] = 1;
            cost[i] = 2;
        }
        gas[n - 1] = n + 1;
        cost[n - 1] = 1;
        // All stations except last one consume more than they provide.
        // Total gas: (n-1)*1 + (n+1) = 2n.
        // Total cost: (n-1)*2 + 1 = 2n-1.
        // Start must be n-1.
        assertEquals(n - 1, test.canCompleteCircuit(gas, cost));
    }
}
