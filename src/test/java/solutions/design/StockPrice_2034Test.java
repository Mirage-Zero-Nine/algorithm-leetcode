package solutions.design;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StockPrice_2034Test {
    @Test
    void processesOutOfOrderRecordsAndCorrections() {
        assertScenario(new int[][]{
                {1, 10, 10, 10, 10},
                {2, 5, 5, 5, 10},
                {1, 3, 5, 3, 5},
                {4, 2, 2, 2, 5}
        });
    }

    @Test
    void correctingTheLatestTimestampChangesCurrent() {
        assertScenario(new int[][]{
                {8, 40, 40, 40, 40},
                {3, 90, 40, 40, 90},
                {8, 11, 11, 11, 90}
        });
    }

    @Test
    void removesAnObsoleteUniqueMaximumAndMinimum() {
        assertScenario(new int[][]{
                {1, 2, 2, 2, 2},
                {2, 9, 9, 2, 9},
                {2, 3, 3, 2, 3},
                {1, 7, 3, 3, 7}
        });
    }

    @Test
    void retainsAnExtremeWhenAnotherTimestampStillUsesIt() {
        assertScenario(new int[][]{
                {1, 100, 100, 100, 100},
                {2, 100, 100, 100, 100},
                {3, 1, 1, 1, 100},
                {1, 50, 1, 1, 100}
        });
    }

    @Test
    void repeatedCorrectionToTheSamePriceKeepsCountsConsistent() {
        assertScenario(new int[][]{
                {5, 12, 12, 12, 12},
                {5, 12, 12, 12, 12},
                {5, 12, 12, 12, 12},
                {6, 12, 12, 12, 12},
                {5, 13, 12, 12, 13}
        });
    }

    @Test
    void tracksLowPricesAndRepeatedValues() {
        assertScenario(new int[][]{
                {1, 4, 4, 4, 4},
                {2, 2, 2, 2, 4},
                {3, 2, 2, 2, 4},
                {2, 6, 2, 2, 6},
                {3, 8, 8, 4, 8}
        });
    }

    @Test
    void supportsBothDocumentedValueBoundaries() {
        assertScenario(new int[][]{
                {1, 1, 1, 1, 1},
                {2, 1_000_000_000, 1_000_000_000, 1, 1_000_000_000},
                {2, 1, 1, 1, 1}
        });
    }

    @Test
    void canReplaceOneRecordWithANewMinimumOrMaximum() {
        assertScenario(new int[][]{
                {1, 20, 20, 20, 20},
                {2, 30, 30, 20, 30},
                {1, 40, 30, 30, 40},
                {2, 10, 10, 10, 40}
        });
    }

    @Test
    void handlesSingletonAndTimestampOne() {
        assertScenario(new int[][]{
                {1, 7, 7, 7, 7},
                {1, 6, 6, 6, 6}
        });
    }

    @Test
    void correctionCanRemoveTheLastCopyOfSeveralOldPrices() {
        assertScenario(new int[][]{
                {1, 1, 1, 1, 1},
                {2, 2, 2, 1, 2},
                {3, 3, 3, 1, 3},
                {1, 2, 3, 2, 3},
                {2, 3, 3, 2, 3},
                {3, 2, 2, 2, 3}
        });
    }

    @Test
    void handlesManyTimestampsSharingTheSamePrices() {
        assertScenario(new int[][]{
                {10, 5, 5, 5, 5},
                {1, 5, 5, 5, 5},
                {7, 5, 5, 5, 5},
                {5, 8, 5, 5, 8},
                {5, 5, 5, 5, 5}
        });
    }

    @Test
    void exhaustivelyChecksShortUpdateHistoriesAgainstTimestampMapOracle() throws Exception {
        util.IsolatedTestProcess.run(getClass(), "exhaustiveShortHistories", 10);
    }

    private void exhaustiveShortHistories() {
        // Enumerate every sequence of 1..6 updates over timestamps {1, 2} and prices {1, 2, 3}.
        int[] timestamps = {1, 2};
        int[] prices = {1, 2, 3};
        for (int length = 1; length <= 6; length++) {
            checkHistories(new int[length][2], 0, timestamps, prices);
        }
    }

    private void checkHistories(int[][] updates, int index, int[] timestamps, int[] prices) {
        if (index == updates.length) {
            checkAgainstOracle(updates);
            return;
        }
        for (int timestamp : timestamps) {
            for (int price : prices) {
                updates[index][0] = timestamp;
                updates[index][1] = price;
                checkHistories(updates, index + 1, timestamps, prices);
            }
        }
    }

    private void checkAgainstOracle(int[][] updates) {
        StockPrice_2034 stock = new StockPrice_2034();
        Map<Integer, Integer> records = new HashMap<>();
        for (int[] update : updates) {
            stock.update(update[0], update[1]);
            records.put(update[0], update[1]);

            int latestTimestamp = records.keySet().stream().mapToInt(Integer::intValue).max().orElseThrow();
            int minimum = records.values().stream().mapToInt(Integer::intValue).min().orElseThrow();
            int maximum = records.values().stream().mapToInt(Integer::intValue).max().orElseThrow();
            String context = "updates=" + java.util.Arrays.deepToString(updates);
            assertEquals(records.get(latestTimestamp), stock.current(), "current, " + context);
            assertEquals(minimum, stock.minimum(), "minimum, " + context);
            assertEquals(maximum, stock.maximum(), "maximum, " + context);
        }
    }

    @Test
    void handlesMaximumOperationCountWithCorrectionsAndChangingExtrema() throws Exception {
        util.IsolatedTestProcess.run(getClass(), "maximumOperationHistory", 10);
    }

    private void maximumOperationHistory() {
        StockPrice_2034 stock = new StockPrice_2034();
        TreeMap<Integer, Integer> oracle = new TreeMap<>();
        int timestampCount = 50_000;

        for (int timestamp = 1; timestamp <= timestampCount; timestamp++) {
            int price = (timestamp % 2 == 0) ? 1_000_000_000 : 1;
            stock.update(timestamp, price);
            oracle.put(timestamp, price);
        }
        for (int timestamp = timestampCount; timestamp >= 1; timestamp--) {
            int price = timestamp % 997 + 1;
            stock.update(timestamp, price);
            oracle.put(timestamp, price);
        }

        assertEquals(100_000, timestampCount * 2);
        assertSnapshot(stock, oracle);
    }

    private void assertScenario(int[][] steps) {
        StockPrice_2034 stock = new StockPrice_2034();
        for (int i = 0; i < steps.length; i++) {
            int[] step = steps[i];
            stock.update(step[0], step[1]);
            assertEquals(step[2], stock.current(), "current after step " + i);
            assertEquals(step[3], stock.minimum(), "minimum after step " + i);
            assertEquals(step[4], stock.maximum(), "maximum after step " + i);
        }
    }

    private void assertSnapshot(StockPrice_2034 stock, TreeMap<Integer, Integer> oracle) {
        assertEquals(oracle.lastEntry().getValue(), stock.current());
        assertEquals(oracle.values().stream().mapToInt(Integer::intValue).min().orElseThrow(), stock.minimum());
        assertEquals(oracle.values().stream().mapToInt(Integer::intValue).max().orElseThrow(), stock.maximum());
    }
}
