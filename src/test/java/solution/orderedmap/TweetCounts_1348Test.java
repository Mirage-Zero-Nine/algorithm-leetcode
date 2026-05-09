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
}
