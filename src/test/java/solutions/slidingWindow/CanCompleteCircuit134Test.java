package solutions.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CanCompleteCircuit134Test {

    private final CanCompleteCircuit_134 test = new CanCompleteCircuit_134();

    @Test
    public void testHappyCases() {
        assertEquals(3, test.canCompleteCircuit(new int[]{1, 2, 3, 4, 5}, new int[]{3, 4, 5, 1, 2}));
        assertEquals(-1, test.canCompleteCircuit(new int[]{2, 3, 4}, new int[]{3, 4, 3}));
    }

    @Test
    public void testNegativeAndEdgeCases() {
        assertEquals(-1, test.canCompleteCircuit(new int[]{3, 1, 1}, new int[]{1, 2, 3}));
        assertEquals(-1, test.canCompleteCircuit(null, null));
    }

    @Test
    public void testLargeCase() {
        assertEquals(4, test.canCompleteCircuit(new int[]{1, 2, 3, 4, 5}, new int[]{2, 3, 4, 5, 1}));
    }

    @Test
    public void testSingleStation() {
        assertEquals(0, test.canCompleteCircuit(new int[]{5}, new int[]{3}));
        assertEquals(-1, test.canCompleteCircuit(new int[]{3}, new int[]{5}));
    }

    @Test
    public void testExactFit() {
        assertEquals(0, test.canCompleteCircuit(new int[]{3, 3, 3}, new int[]{3, 3, 3}));
    }

    @Test
    public void testStartAtLastStation() {
        assertEquals(2, test.canCompleteCircuit(new int[]{1, 1, 5}, new int[]{2, 2, 3}));
    }

    @Test
    public void testEmptyArrays() {
        assertEquals(-1, test.canCompleteCircuit(new int[]{}, new int[]{}));
    }

    @Test
    public void testAllZeroGas() {
        assertEquals(-1, test.canCompleteCircuit(new int[]{0, 0, 0}, new int[]{1, 1, 1}));
    }

    @Test
    public void testStartAtZero() {
        assertEquals(0, test.canCompleteCircuit(new int[]{5, 1, 1}, new int[]{2, 2, 2}));
    }

    @Test
    public void testGiantCase() {
        int n = 10000;
        int[] gas = new int[n];
        int[] cost = new int[n];
        for (int i = 0; i < n; i++) {
            gas[i] = i + 1;
            cost[i] = i;
        }
        // total gas = n*(n+1)/2, total cost = n*(n-1)/2, gas > cost so solution exists
        // start at 0: gas[0]-cost[0]=1, always positive going forward
        assertEquals(0, test.canCompleteCircuit(gas, cost));
    }

    @org.junit.jupiter.params.ParameterizedTest(name = "independent oracle seed={0}")
    @org.junit.jupiter.params.provider.ValueSource(ints = {7, 19, 43, 71, 101, 211, 509, 997, 2027, 4093, 8191, 16381})
    public void testSeededCasesAgainstIndependentOracle(int seed) {
        java.util.Random random = new java.util.Random(seed);
        for (int sample = 0; sample < 40; sample++) {

            int n = 1 + random.nextInt(12);
            int[] gas = random.ints(n, 0, 8).toArray();
            int[] cost = random.ints(n, 0, 8).toArray();
            java.util.List<Integer> starts = new java.util.ArrayList<>();
            for (int start = 0; start < n; start++) {
                int tank = 0;
                boolean valid = true;
                for (int step = 0; step < n; step++) {
                    int station = (start + step) % n;
                    tank += gas[station] - cost[station];
                    if (tank < 0) { valid = false; break; }
                }
                if (valid) starts.add(start);
            }
            // The documented contract guarantees a unique starting station when a solution exists.
            if (starts.size() > 1) continue;
            int expected = starts.isEmpty() ? -1 : starts.get(0);
            org.junit.jupiter.api.Assertions.assertEquals(expected, new CanCompleteCircuit_134().canCompleteCircuit(gas, cost));
        }
    }
}
