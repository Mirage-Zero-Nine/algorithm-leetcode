package solutions.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class TimeMap_981Test {

    @Test
    void followsOfficialExample() {
        TimeMap_981 timeMap = new TimeMap_981();

        timeMap.set("foo", "bar", 1);
        assertEquals("bar", timeMap.get("foo", 1));
        assertEquals("bar", timeMap.get("foo", 3));

        timeMap.set("foo", "bar2", 4);
        assertEquals("bar2", timeMap.get("foo", 4));
        assertEquals("bar2", timeMap.get("foo", 5));
    }

    @Test
    void returnsEmptyForMissingKey() {
        TimeMap_981 timeMap = new TimeMap_981();

        timeMap.set("foo", "bar", 1);

        assertEquals("", timeMap.get("bar", 1));
    }

    @Test
    void returnsEmptyWhenQueryIsBeforeFirstTimestamp() {
        TimeMap_981 timeMap = new TimeMap_981();

        timeMap.set("foo", "bar", 5);

        assertEquals("", timeMap.get("foo", 1));
        assertEquals("", timeMap.get("foo", 4));
    }

    @ParameterizedTest
    @MethodSource("floorQueries")
    void returnsValueAtGreatestTimestampNotAfterQuery(int queryTimestamp, String expectedValue) {
        TimeMap_981 timeMap = new TimeMap_981();
        timeMap.set("foo", "bar1", 10);
        timeMap.set("foo", "bar2", 20);
        timeMap.set("foo", "bar3", 30);

        assertEquals(expectedValue, timeMap.get("foo", queryTimestamp));
    }

    private static Stream<Arguments> floorQueries() {
        return Stream.of(
                Arguments.of(5, ""),
                Arguments.of(10, "bar1"),
                Arguments.of(11, "bar1"),
                Arguments.of(19, "bar1"),
                Arguments.of(20, "bar2"),
                Arguments.of(25, "bar2"),
                Arguments.of(30, "bar3"),
                Arguments.of(100, "bar3"));
    }

    @Test
    void keepsIndependentHistoryForEachKey() {
        TimeMap_981 timeMap = new TimeMap_981();

        timeMap.set("foo", "foo1", 1);
        timeMap.set("bar", "bar2", 2);
        timeMap.set("foo", "foo3", 3);
        timeMap.set("bar", "bar4", 4);

        assertEquals("foo1", timeMap.get("foo", 2));
        assertEquals("foo3", timeMap.get("foo", 3));
        assertEquals("bar2", timeMap.get("bar", 3));
        assertEquals("bar4", timeMap.get("bar", 4));
    }

    @Test
    void handlesAKeyWithOneValue() {
        TimeMap_981 timeMap = new TimeMap_981();

        timeMap.set("foo", "bar", 10);

        assertEquals("", timeMap.get("foo", 9));
        assertEquals("bar", timeMap.get("foo", 10));
        assertEquals("bar", timeMap.get("foo", 11));
    }

    @Test
    void readsDoNotChangeHistoryBeforeALaterSet() {
        TimeMap_981 timeMap = new TimeMap_981();

        timeMap.set("foo", "bar1", 1);
        timeMap.set("foo", "bar2", 10);

        assertEquals("bar1", timeMap.get("foo", 5));
        assertEquals("bar2", timeMap.get("foo", 10));
        assertEquals("bar1", timeMap.get("foo", 5));

        timeMap.set("foo", "bar3", 20);

        assertEquals("bar1", timeMap.get("foo", 5));
        assertEquals("bar2", timeMap.get("foo", 19));
        assertEquals("bar3", timeMap.get("foo", 20));
    }

    @Test
    void isolatesManyKeysWithSparseHistories() {
        TimeMap_981 timeMap = new TimeMap_981();

        for (int i = 1; i <= 1_000; i++) {
            timeMap.set("key" + i, "value" + i, i);
        }

        for (int i = 1; i <= 1_000; i++) {
            assertEquals("value" + i, timeMap.get("key" + i, i));
            if (i > 1) {
                assertEquals("", timeMap.get("key" + i, i - 1));
            }
        }

        assertEquals("", timeMap.get("missing", 1_000));
    }

    @Test
    void handlesMaximumTimestampBoundary() {
        TimeMap_981 timeMap = new TimeMap_981();

        timeMap.set("foo", "beforeMax", 9_999_999);
        timeMap.set("foo", "max", 10_000_000);

        assertEquals("", timeMap.get("foo", 9_999_998));
        assertEquals("beforeMax", timeMap.get("foo", 9_999_999));
        assertEquals("max", timeMap.get("foo", 10_000_000));
    }

    @Test
    void handlesMaximumKeyAndValueLengths() {
        TimeMap_981 timeMap = new TimeMap_981();
        String key = "k".repeat(100);
        String value = "v".repeat(100);

        timeMap.set(key, value, 1);

        assertEquals(value, timeMap.get(key, 1));
    }

    @Test
    void handlesLowercaseLettersAndDigitsInKeysAndValues() {
        TimeMap_981 timeMap = new TimeMap_981();

        timeMap.set("key123", "value456", 1);

        assertEquals("value456", timeMap.get("key123", 1));
    }

    @Test
    void handlesLargeNumberOfOperations() {
        TimeMap_981 timeMap = new TimeMap_981();

        // LeetCode permits up to 2 * 10^5 total set/get operations.
        for (int timestamp = 1; timestamp <= 100_000; timestamp++) {
            timeMap.set("key", "value" + timestamp, timestamp);
        }

        for (int timestamp = 1; timestamp <= 100_000; timestamp++) {
            assertEquals("value" + timestamp, timeMap.get("key", timestamp));
        }
    }

    @Test
    void matchesIndependentTreeMapOracleForInterleavedHistories() {
        TimeMap_981 actual = new TimeMap_981();
        Map<String, TreeMap<Integer, String>> expected = new HashMap<>();
        Random random = new Random(981L);
        int timestamp = 0;

        // Timestamps are globally increasing, which also satisfies the
        // per-key strictly-increasing timestamp requirement from the problem.
        for (int i = 0; i < 1_000; i++) {
            timestamp += random.nextInt(3) + 1;
            String key = "key" + random.nextInt(10);
            String value = "value" + i;

            actual.set(key, value, timestamp);
            expected.computeIfAbsent(key, _ -> new TreeMap<>()).put(timestamp, value);
        }

        for (int i = 0; i < 1_000; i++) {
            String key = "key" + random.nextInt(10);
            int queryTimestamp = random.nextInt(timestamp) + 1;
            TreeMap<Integer, String> history = expected.get(key);
            Map.Entry<Integer, String> floor = history == null
                    ? null
                    : history.floorEntry(queryTimestamp);
            String expectedValue = floor == null ? "" : floor.getValue();

            assertEquals(expectedValue, actual.get(key, queryTimestamp));
        }
    }

    @Test
    void emptyStoreReturnsEmptyForEveryValidQueryTimestamp() {
        TimeMap_981 timeMap = new TimeMap_981();

        assertEquals("", timeMap.get("foo", 1));
        assertEquals("", timeMap.get("foo", 5_000_000));
        assertEquals("", timeMap.get("bar", 10_000_000));
    }

    @Test
    void exactTimestampSelectsTheVersionWrittenAtThatTimestamp() {
        TimeMap_981 timeMap = new TimeMap_981();

        timeMap.set("foo", "first", 1);
        timeMap.set("foo", "second", 2);
        timeMap.set("foo", "third", 3);

        assertEquals("first", timeMap.get("foo", 1));
        assertEquals("second", timeMap.get("foo", 2));
        assertEquals("third", timeMap.get("foo", 3));
    }

    @Test
    void choosesTheMostRecentEarlierVersionAcrossSeveralGaps() {
        TimeMap_981 timeMap = new TimeMap_981();

        timeMap.set("foo", "at2", 2);
        timeMap.set("foo", "at5", 5);
        timeMap.set("foo", "at9", 9);

        assertEquals("", timeMap.get("foo", 1));
        assertEquals("at2", timeMap.get("foo", 3));
        assertEquals("at2", timeMap.get("foo", 4));
        assertEquals("at5", timeMap.get("foo", 6));
        assertEquals("at5", timeMap.get("foo", 8));
        assertEquals("at9", timeMap.get("foo", 10));
    }

    @Test
    void laterWritesDoNotChangeTheAnswerForEarlierQueries() {
        TimeMap_981 timeMap = new TimeMap_981();

        timeMap.set("foo", "old", 2);
        assertEquals("old", timeMap.get("foo", 2));
        assertEquals("old", timeMap.get("foo", 4));

        timeMap.set("foo", "new", 8);

        assertEquals("old", timeMap.get("foo", 4));
        assertEquals("old", timeMap.get("foo", 7));
        assertEquals("new", timeMap.get("foo", 8));
    }

    @Test
    void keysWithCommonPrefixesKeepSeparateHistories() {
        TimeMap_981 timeMap = new TimeMap_981();

        timeMap.set("foo", "short", 1);
        timeMap.set("foobar", "long", 2);

        assertEquals("short", timeMap.get("foo", 2));
        assertEquals("long", timeMap.get("foobar", 2));
        assertEquals("", timeMap.get("fo", 2));
    }

    @Test
    void aValueAtTheMinimumTimestampIsAvailableFromThatPointForward() {
        TimeMap_981 timeMap = new TimeMap_981();

        timeMap.set("a", "b", 1);

        assertEquals("b", timeMap.get("a", 1));
        assertEquals("b", timeMap.get("a", 2));
        assertEquals("b", timeMap.get("a", 10_000_000));
    }

    @Test
    void maximumTimestampCanBeQueriedExactlyAndAfterward() {
        TimeMap_981 timeMap = new TimeMap_981();

        timeMap.set("foo", "max", 10_000_000);

        assertEquals("", timeMap.get("foo", 9_999_999));
        assertEquals("max", timeMap.get("foo", 10_000_000));
    }

    @Test
    void minimumLengthKeyAndValueRemainDistinctFromOtherEntries() {
        TimeMap_981 timeMap = new TimeMap_981();

        timeMap.set("a", "b", 1);
        timeMap.set("c", "d", 2);

        assertEquals("b", timeMap.get("a", 1));
        assertEquals("b", timeMap.get("a", 2));
        assertEquals("d", timeMap.get("c", 2));
    }

    @Test
    void repeatedReadsDoNotAlterAKeyHistory() {
        TimeMap_981 timeMap = new TimeMap_981();

        timeMap.set("foo", "v1", 3);
        timeMap.set("foo", "v2", 7);

        for (int i = 0; i < 50; i++) {
            assertEquals("v1", timeMap.get("foo", 5));
            assertEquals("v2", timeMap.get("foo", 7));
            assertEquals("", timeMap.get("foo", 1));
        }
    }

    @Test
    void freshInstancesDoNotShareStoredValues() {
        TimeMap_981 populated = new TimeMap_981();
        populated.set("foo", "bar", 1);

        TimeMap_981 fresh = new TimeMap_981();

        assertEquals("bar", populated.get("foo", 1));
        assertEquals("", fresh.get("foo", 1));
    }

    @Test
    void longSingleKeyHistoryMatchesAnIndependentFloorOracle() {
        TimeMap_981 actual = new TimeMap_981();
        TreeMap<Integer, String> expected = new TreeMap<>();

        for (int timestamp = 1; timestamp <= 4_096; timestamp++) {
            String value = "value" + timestamp;
            actual.set("foo", value, timestamp);
            expected.put(timestamp, value);
        }

        for (int query = 1; query <= 4_097; query++) {
            Map.Entry<Integer, String> floor = expected.floorEntry(query);
            assertEquals(floor == null ? "" : floor.getValue(), actual.get("foo", query));
        }
    }

    @Test
    void interleavedWritesAndReadsMatchAnIndependentOracle() {
        TimeMap_981 actual = new TimeMap_981();
        Map<String, TreeMap<Integer, String>> expected = new HashMap<>();

        int timestamp = 1;
        for (int operation = 0; operation < 300; operation++) {
            String key = "key" + (operation % 7);
            String value = "value" + operation;
            actual.set(key, value, timestamp);
            expected.computeIfAbsent(key, _ -> new TreeMap<>()).put(timestamp, value);

            assertOracleValue(actual, expected, key, timestamp);
            assertOracleValue(actual, expected, key, timestamp + 1);
            assertOracleValue(actual, expected, "missing", timestamp);
            timestamp++;
        }
    }

    @Test
    void sparseQueriesAcrossManyKeysMatchTheFloorOracle() {
        TimeMap_981 actual = new TimeMap_981();
        Map<String, TreeMap<Integer, String>> expected = new HashMap<>();

        int timestamp = 10;
        for (int keyIndex = 0; keyIndex < 20; keyIndex++) {
            String key = "key" + keyIndex;
            TreeMap<Integer, String> history = new TreeMap<>();
            for (int valueIndex = 0; valueIndex < 5; valueIndex++) {
                String value = key + "-" + valueIndex;
                actual.set(key, value, timestamp);
                history.put(timestamp, value);
                timestamp += 3;
            }
            expected.put(key, history);
        }

        for (int query = 1; query <= timestamp + 3; query++) {
            for (int keyIndex = 0; keyIndex < 20; keyIndex++) {
                String key = "key" + keyIndex;
                assertOracleValue(actual, expected, key, query);
            }
        }
    }

    @Test
    void valuesWithRepeatedWordsAreReturnedWithoutTruncationOrNormalization() {
        TimeMap_981 timeMap = new TimeMap_981();
        String value = "abcabcabc";

        timeMap.set("foo", value, 1);
        timeMap.set("foo", "abcabc", 2);

        assertEquals(value, timeMap.get("foo", 1));
        assertEquals("abcabc", timeMap.get("foo", 2));
    }

    @Test
    void aMissingKeyRemainsMissingAfterOtherKeysReceiveLaterWrites() {
        TimeMap_981 timeMap = new TimeMap_981();

        timeMap.set("foo", "bar", 1);
        assertEquals("", timeMap.get("absent", 1));
        timeMap.set("foo", "bar2", 2);
        timeMap.set("other", "value", 3);

        assertEquals("", timeMap.get("absent", 10));
        assertEquals("bar2", timeMap.get("foo", 10));
        assertEquals("value", timeMap.get("other", 10));
    }

    private static void assertOracleValue(TimeMap_981 actual,
                                          Map<String, TreeMap<Integer, String>> expected,
                                          String key,
                                          int queryTimestamp) {
        TreeMap<Integer, String> history = expected.get(key);
        Map.Entry<Integer, String> floor = history == null ? null : history.floorEntry(queryTimestamp);
        String expectedValue = floor == null ? "" : floor.getValue();
        assertEquals(expectedValue, actual.get(key, queryTimestamp));
    }
}
