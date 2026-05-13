package solution.orderedmap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;
import org.junit.jupiter.api.Test;

public class TweetCounts_1348Test {

    @Test
    public void testHappyCases() {
        TweetCounts_1348 test = new TweetCounts_1348();
        test.recordTweet("tweet3", 0);
        test.recordTweet("tweet3", 60);
        test.recordTweet("tweet3", 10);

        assertEquals(List.of(2), test.getTweetCountsPerFrequency("minute", "tweet3", 0, 59));
        assertEquals(List.of(2, 1), test.getTweetCountsPerFrequency("minute", "tweet3", 0, 60));
    }

    @Test
    public void testNegativeCaseWithUnknownTweet() {
        TweetCounts_1348 test = new TweetCounts_1348();
        assertNull(test.getTweetCountsPerFrequency("minute", "unknown", 0, 59));
    }

    @Test
    public void testInvalidAndEdgeCases() {
        TweetCounts_1348 test = new TweetCounts_1348();
        test.recordTweet("single", 15);
        test.recordTweet("single", 15);

        assertEquals(List.of(2), test.getTweetCountsPerFrequency("minute", "single", 15, 15));
        assertEquals(List.of(2), test.getTweetCountsPerFrequency("day", "single", 0, 100));
    }

    @Test
    public void testLargeCase() {
        TweetCounts_1348 test = new TweetCounts_1348();
        for (int i = 0; i < 120; i++) {
            test.recordTweet("bulk", i);
        }
        assertEquals(List.of(60, 60), test.getTweetCountsPerFrequency("minute", "bulk", 0, 119));
        assertEquals(List.of(120), test.getTweetCountsPerFrequency("hour", "bulk", 0, 3599));
    }

    @Test
    public void testHappyHourFrequency() {
        TweetCounts_1348 test = new TweetCounts_1348();
        test.recordTweet("user1", 0);
        test.recordTweet("user1", 3600);
        test.recordTweet("user1", 7200);
        assertEquals(List.of(1, 1, 1), test.getTweetCountsPerFrequency("hour", "user1", 0, 7200));
    }

    @Test
    public void testHappyDayFrequency() {
        TweetCounts_1348 test = new TweetCounts_1348();
        test.recordTweet("daily", 0);
        test.recordTweet("daily", 86400);
        assertEquals(List.of(1, 1), test.getTweetCountsPerFrequency("day", "daily", 0, 86400));
    }

    @Test
    public void testHappyMultipleTweetsAtSameTime() {
        TweetCounts_1348 test = new TweetCounts_1348();
        test.recordTweet("spam", 5);
        test.recordTweet("spam", 5);
        test.recordTweet("spam", 5);
        assertEquals(List.of(3), test.getTweetCountsPerFrequency("minute", "spam", 0, 59));
    }

    @Test
    public void testNegativeUnknownUser() {
        TweetCounts_1348 test = new TweetCounts_1348();
        test.recordTweet("known", 10);
        assertNull(test.getTweetCountsPerFrequency("minute", "ghost", 0, 59));
    }

    @Test
    public void testEdgeExactBoundary() {
        TweetCounts_1348 test = new TweetCounts_1348();
        test.recordTweet("edge", 59);
        test.recordTweet("edge", 60);
        assertEquals(List.of(1, 1), test.getTweetCountsPerFrequency("minute", "edge", 0, 60));
    }

    @Test
    public void testEdgeNoTweetsInRange() {
        TweetCounts_1348 test = new TweetCounts_1348();
        test.recordTweet("out", 100);
        assertEquals(List.of(0), test.getTweetCountsPerFrequency("minute", "out", 0, 59));
    }

    @Test
    public void testGiantCase() {
        TweetCounts_1348 test = new TweetCounts_1348();
        for (int i = 0; i < 10000; i++) {
            test.recordTweet("giant", i);
        }
        List<Integer> result = test.getTweetCountsPerFrequency("hour", "giant", 0, 9999);
        int sum = result.stream().mapToInt(Integer::intValue).sum();
        assertEquals(10000, sum);
    }
}
