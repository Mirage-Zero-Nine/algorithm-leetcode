package solutions.design;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2022/07/04 00:51
 * Created with IntelliJ IDEA
 */

public class Twitter_355Test {
    private Twitter_355 test;

    @BeforeEach
    public void setUp() {
        test = new Twitter_355();
    }

    @Test
    public void test() {
        test.postTweet(1, 5);  // User 1 posts a new tweet (id = 5).
        assertIterableEquals(Lists.newArrayList(5), test.getNewsFeed(1));  // User 1's news feed should return a list with 1 tweet id -> [5]. return [5]
        test.follow(1, 2);  // User 1 follows user 2.
        test.postTweet(2, 6);  // User 2 posts a new tweet (id = 6).
        assertIterableEquals(Lists.newArrayList(6, 5), test.getNewsFeed(1));  // User 1's news feed should return a list with 2 tweet ids -> [6, 5]. Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
        test.unfollow(1, 2);  // User 1 unfollows user 2.
        assertIterableEquals(Lists.newArrayList(5), test.getNewsFeed(1));  // User 1's news feed should return a list with 1 tweet id -> [5], since user 1 is no longer following user 2.
    }

    @Test
    public void testFollowEmptyOrNonExist() {
        test.follow(1, 5);
        assertIterableEquals(Lists.newArrayList(), test.getNewsFeed(1));
        assertIterableEquals(Lists.newArrayList(), test.getNewsFeed(100));
    }

    @Test
    public void testOwnTweetsAppearInReverseChronologicalOrder() {
        test.postTweet(1, 10);
        test.postTweet(1, 11);
        test.postTweet(1, 12);
        assertIterableEquals(Lists.newArrayList(12, 11, 10), test.getNewsFeed(1));
    }

    @Test
    public void testFeedLimitedToTenMostRecentTweets() {
        for (int i = 1; i <= 12; i++) {
            test.postTweet(1, i);
        }
        assertIterableEquals(
                Lists.newArrayList(12, 11, 10, 9, 8, 7, 6, 5, 4, 3),
                test.getNewsFeed(1)
        );
    }

    @Test
    public void testFollowAddsFolloweeTweetsToFeed() {
        test.postTweet(1, 100);
        test.postTweet(2, 200);
        test.follow(1, 2);
        assertIterableEquals(Lists.newArrayList(200, 100), test.getNewsFeed(1));
    }

    @Test
    public void testUnfollowRemovesFolloweeTweetsFromFeed() {
        test.postTweet(1, 100);
        test.follow(1, 2);
        test.postTweet(2, 201);
        assertIterableEquals(Lists.newArrayList(201, 100), test.getNewsFeed(1));
        test.unfollow(1, 2);
        assertIterableEquals(Lists.newArrayList(100), test.getNewsFeed(1));
    }

    @Test
    public void testUnfollowSelfRemovesOwnTweetsFromFeed() {
        test.postTweet(1, 7);
        assertIterableEquals(Lists.newArrayList(7), test.getNewsFeed(1));
        test.unfollow(1, 1);
        assertIterableEquals(Lists.newArrayList(), test.getNewsFeed(1));
    }

    @Test
    public void testFollowingMultipleUsersMergesByGlobalRecency() {
        test.postTweet(2, 20);
        test.postTweet(3, 30);
        test.postTweet(1, 10);
        test.follow(1, 2);
        test.follow(1, 3);
        assertIterableEquals(Lists.newArrayList(10, 30, 20), test.getNewsFeed(1));
    }

    @Test
    public void testDuplicateFollowDoesNotDuplicateFeedEntries() {
        test.follow(1, 2);
        test.follow(1, 2);
        test.postTweet(2, 50);
        assertIterableEquals(Lists.newArrayList(50), test.getNewsFeed(1));
    }

    @Test
    public void testGiantCaseManyUsersAndTweets() {
        for (int u = 1; u <= 30; u++) {
            test.follow(1, u);
        }
        int tweetId = 1000;
        for (int round = 0; round < 20; round++) {
            for (int u = 1; u <= 30; u++) {
                test.postTweet(u, tweetId++);
            }
        }
        assertIterableEquals(
                Lists.newArrayList(1599, 1598, 1597, 1596, 1595, 1594, 1593, 1592, 1591, 1590),
                test.getNewsFeed(1)
        );
    }
}
