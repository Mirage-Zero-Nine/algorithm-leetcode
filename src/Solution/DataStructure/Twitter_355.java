package Solution.DataStructure;

import java.util.*;

/**
 * Design a simplified version of Twitter where users can post tweets, follow/unfollow another user, and is able to see the 10 most recent tweets in the user's news feed.
 * Implement the Twitter class:
 * - Twitter() Initializes your twitter object.
 * - void postTweet(int userId, int tweetId) Composes a new tweet with ID tweetId by the user userId. Each call to this function will be made with a unique tweetId.
 * - List<Integer> getNewsFeed(int userId) Retrieves the 10 most recent tweet IDs in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user themself. Tweets must be ordered from most recent to least recent.
 * - void follow(int followerId, int followeeId) The user with ID followerId started following the user with ID followeeId.
 * - void unfollow(int followerId, int followeeId) The user with ID followerId started unfollowing the user with ID followeeId.
 *
 * @author BorisMirage
 * Time: 2021/11/03 14:05
 * Created with IntelliJ IDEA
 */

public class Twitter_355 {
    private final Map<Integer, Set<Integer>> followingMap;  // user, set of following user
    private final Map<Integer, List<Integer>> userTweetMap; // user, list of published tweet
    private final Map<Integer, Integer> tweetTimeStampMap;  // tweet, publish time
    private int time = 0;                                   // timestamp

    /**
     * Initialize the class.
     * Keep 3 hash maps.
     * - Map 1: user and a hash set of its following user.
     * - Map 2: user and a list of its published tweet
     * - Map 3: tweet and its published time
     */
    public Twitter_355() {
        followingMap = new HashMap<>();
        userTweetMap = new HashMap<>();
        tweetTimeStampMap = new HashMap<>();
    }

    /**
     * Post a new tweet. Check if the user exist, then add the tweet to map with current time.
     *
     * @param userId  user id
     * @param tweetId tweet id
     */
    public void postTweet(int userId, int tweetId) {
        checkUserId(userId);
        userTweetMap.get(userId).add(0, tweetId);
        tweetTimeStampMap.put(tweetId, time);
        time++;
    }

    /**
     * Get news feed for a specific user.
     * Keep a priority queue with ListIterator. The order of priority queue is confirmed by checking the time in time map.
     * Add all the following users' ListIterator to the heap. Each time, poll out the top of the heap and add to the list.
     *
     * @param userId user id
     * @return news feed for a specifc user
     */
    public List<Integer> getNewsFeed(int userId) {

        if (!followingMap.containsKey(userId)) {
            return new LinkedList<>();
        }

        PriorityQueue<ListIterator<Integer>> pq = new PriorityQueue<>(new Comparator<ListIterator<Integer>>() {
            @Override
            public int compare(ListIterator<Integer> o1, ListIterator<Integer> o2) {
                int tweet1 = o1.next();
                o1.previous();
                int tweet2 = o2.next();
                o2.previous();
                return tweetTimeStampMap.get(tweet2) - tweetTimeStampMap.get(tweet1);
            }
        });

        for (int u : followingMap.get(userId)) {
            List<Integer> followingList = userTweetMap.get(u);
            if (followingList != null) {
                ListIterator<Integer> following = userTweetMap.get(u).listIterator();
                if (following.hasNext()) {
                    pq.add(following); // if all the user's tweets were added, do not add it back to the heap
                }
            }
        }

        int count = 0;
        List<Integer> out = new ArrayList<>();
        while (!pq.isEmpty() && count < 10) {
            ListIterator<Integer> current = pq.poll();
            if (current.hasNext()) {
                out.add(current.next()); // add next latest tweet to time feed
                count++;
            }
            if (current.hasNext()) {
                pq.add(current);
            }
        }

        return out;
    }

    /**
     * Add new followee to a user.
     *
     * @param followerId follower id
     * @param followeeId followee id
     */
    public void follow(int followerId, int followeeId) {
        checkUserId(followerId);
        Set<Integer> followingSet = followingMap.get(followerId);
        followingSet.add(followeeId);
    }

    /**
     * Remove a followee from a user.
     *
     * @param followerId follower id
     * @param followeeId followee id
     */
    public void unfollow(int followerId, int followeeId) {
        checkUserId(followerId);
        Set<Integer> followingSet = followingMap.get(followerId);
        followingSet.remove(followeeId);
    }

    /**
     * Check if given user is in the map.
     *
     * @param userId checking user
     */
    private void checkUserId(int userId) {
        if (!followingMap.containsKey(userId)) {
            Set<Integer> set = new HashSet<Integer>() {{
                add(userId);
            }};
            followingMap.put(userId, set); // a user always "following" itself
        }
        userTweetMap.putIfAbsent(userId, new LinkedList<>());
    }

    public static void main(String[] args) {
        Twitter_355 twitter = new Twitter_355();
        twitter.postTweet(1, 5); // User 1 posts a new tweet (id = 5).
        System.out.println(twitter.getNewsFeed(1));  // User 1's news feed should return a list with 1 tweet id -> [5]. return [5]
        twitter.follow(1, 2);    // User 1 follows user 2.
        twitter.postTweet(2, 6); // User 2 posts a new tweet (id = 6).
        System.out.println(twitter.getNewsFeed(1));  // User 1's news feed should return a list with 2 tweet ids -> [6, 5]. Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
        twitter.unfollow(1, 2);  // User 1 unfollows user 2.
        System.out.println(twitter.getNewsFeed(1)); // User 1's news feed should return a list with 1 tweet id -> [5], since user 1 is no longer following user 2.

        twitter = new Twitter_355();
        twitter.follow(1, 5);
        System.out.println(twitter.getNewsFeed(1));
    }
}
