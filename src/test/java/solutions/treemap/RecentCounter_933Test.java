package solutions.treemap;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;
import util.IsolatedTestProcess;

public class RecentCounter_933Test {

    @Test
    public void explicitSequences() throws Exception {
        IsolatedTestProcess.run(RecentCounter_933Test.class, "runExplicitSequences", 10);
    }

    @Test
    public void exhaustiveGapSequences() throws Exception {
        IsolatedTestProcess.run(RecentCounter_933Test.class, "runExhaustiveGapSequences", 10);
    }

    @Test
    public void maximumCallWorkload() throws Exception {
        IsolatedTestProcess.run(RecentCounter_933Test.class, "runMaximumCallWorkload", 10);
    }

    @Test
    public void staggeredEvictionsAndRefillAfterIdlePeriod() throws Exception {
        IsolatedTestProcess.run(RecentCounter_933Test.class, "runStaggeredEvictionsAndRefillAfterIdlePeriod", 10);
    }

    @Test
    public void deterministicVariedGapsNearMaximumTimestamp() throws Exception {
        IsolatedTestProcess.run(RecentCounter_933Test.class, "runDeterministicVariedGapsNearMaximumTimestamp", 10);
    }

    @Test
    public void repeatedBurstDrainAndRefillCycles() throws Exception {
        IsolatedTestProcess.run(RecentCounter_933Test.class, "runRepeatedBurstDrainAndRefillCycles", 10);
    }

    // IsolatedTestProcess uses a child JVM so a non-cooperative loop is forcibly stopped.
    public void runExplicitSequences() {
        // Ten distinct inputs cover initialization, dense and sparse traffic, both sides of the
        // inclusive boundary, expiration, repeated windows, and the maximum supported time.
        verify(new int[]{1}, new int[]{1});
        verify(new int[]{1, 100, 3001, 3002}, new int[]{1, 2, 3, 3}); // Official example.
        verify(new int[]{1, 3001}, new int[]{1, 2});
        verify(new int[]{1, 3002}, new int[]{1, 1});
        verify(new int[]{1, 3000, 3001}, new int[]{1, 2, 3});
        verify(new int[]{5, 100, 2500, 3005}, new int[]{1, 2, 3, 4});
        verify(new int[]{5, 100, 2500, 3006}, new int[]{1, 2, 3, 3});
        verify(new int[]{1, 2, 3, 3001, 3002, 6002}, new int[]{1, 2, 3, 4, 4, 2});
        verify(new int[]{10, 3010, 6010, 9010}, new int[]{1, 2, 2, 2});
        verify(new int[]{999_999_997, 1_000_000_000}, new int[]{1, 2});

        RecentCounter_933 first = new RecentCounter_933();
        RecentCounter_933 second = new RecentCounter_933();
        assertEquals(1, first.ping(1));
        assertEquals(1, second.ping(100));
        assertEquals(2, first.ping(2));
        assertEquals(1, second.ping(3101));
    }

    public void runExhaustiveGapSequences() {
        // Exhaust all 1 + 4 + ... + 4^5 gap patterns around the 3000-unit boundary.
        for (int length = 0; length <= 5; length++) {
            enumerateGapSequences(new int[length], 0);
        }
    }

    public void runMaximumCallWorkload() {
        RecentCounter_933 counter = new RecentCounter_933();
        // Consecutive calls fill and roll the window; expected counts follow directly from its
        // 3001 distinct integer times. The last call causes bulk eviction at the maximum t.
        for (int time = 1; time <= 9999; time++) {
            assertEquals(Math.min(time, 3001), counter.ping(time), "consecutive time " + time);
        }
        assertEquals(1, counter.ping(1_000_000_000));
    }

    public void runStaggeredEvictionsAndRefillAfterIdlePeriod() {
        // Several old requests expire together while a request exactly at the lower boundary
        // remains; the long idle gap then empties the window before a fresh burst arrives.
        verifyAgainstOracle(new int[]{1, 2, 3, 1000, 2000, 3001, 3002, 3003,
                6002, 6003, 6004, 9005, 9006, 9007, 9008});
    }

    public void runDeterministicVariedGapsNearMaximumTimestamp() {
        Random random = new Random(933);
        int[] times = new int[2000];
        int[] gapChoices = {1, 2, 17, 250, 900, 1500, 2999, 3000, 3001};
        int totalGap = 0;
        for (int i = 1; i < times.length; i++) {
            totalGap += gapChoices[random.nextInt(gapChoices.length)];
            times[i] = totalGap;
        }
        times[0] = 1_000_000_000 - totalGap;
        for (int i = 1; i < times.length; i++) {
            times[i] += times[0];
        }
        verifyAgainstOracle(times);
    }

    public void runRepeatedBurstDrainAndRefillCycles() {
        List<Integer> times = new ArrayList<>();
        int time = 1;
        for (int cycle = 1; cycle <= 6; cycle++) {
            int burstSize = cycle * 3;
            for (int request = 0; request < burstSize; request++) {
                times.add(time++);
            }
            time += cycle * 900; // Grow the gap until the preceding burst fully expires.
            times.add(time);
            time += 3002; // Drain the window before beginning the next burst.
        }
        verifyAgainstOracle(times.stream().mapToInt(Integer::intValue).toArray());
    }

    private void enumerateGapSequences(int[] gaps, int index) {
        if (index == gaps.length) {
            int[] times = new int[gaps.length + 1];
            times[0] = 1;
            for (int i = 0; i < gaps.length; i++) {
                times[i + 1] = times[i] + gaps[i];
            }
            verifyAgainstOracle(times);
            return;
        }
        for (int gap : new int[]{1, 2999, 3000, 3001}) {
            gaps[index] = gap;
            enumerateGapSequences(gaps, index + 1);
        }
    }

    private void verify(int[] times, int[] expected) {
        RecentCounter_933 counter = new RecentCounter_933();
        for (int i = 0; i < times.length; i++) {
            assertEquals(expected[i], counter.ping(times[i]),
                    "sequence " + Arrays.toString(times) + " at index " + i);
        }
    }

    private void verifyAgainstOracle(int[] times) {
        RecentCounter_933 counter = new RecentCounter_933();
        List<Integer> history = new ArrayList<>();
        for (int time : times) {
            history.add(time);
            assertEquals(countRecentByScanning(history, time), counter.ping(time),
                    () -> "sequence " + Arrays.toString(times) + " at time " + time);
        }
    }

    private int countRecentByScanning(List<Integer> history, int time) {
        int count = 0;
        for (int earlierTime : history) {
            if (earlierTime >= time - 3000 && earlierTime <= time) {
                count++;
            }
        }
        return count;
    }
}
