package solution.datastructure;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author BorisMirage
 * Time: 2022/07/04 00:51
 * Created with IntelliJ IDEA
 */

public class Twitter355Test {
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
}