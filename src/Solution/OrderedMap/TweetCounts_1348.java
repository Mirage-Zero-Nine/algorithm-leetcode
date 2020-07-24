package Solution.OrderedMap;

import java.util.*;

/**
 * Implement the class TweetCounts_1348 that supports two methods:
 * 1. recordTweet(string tweetName, int time): Stores the tweetName at the recorded time (in seconds).
 * 2. getTweetCountsPerFrequency(string freq, string tweetName, int startTime, int endTime)
 *
 * @author BorisMirage
 * Time: 2020/05/24 12:52
 * Created with IntelliJ IDEA
 */

public class TweetCounts_1348 {
    Map<String, TreeMap<Integer, Integer>> m;

    /**
     * Keep a hash map to store all tweets based on tweet name.
     * Under each tweet name, use a tree map to store the count of tweet.
     * The key of the tree map is the time, and the value is the number of tweet at this time.
     * When getTweetCountsPerFrequency() is called, get the time range under this tweet name by calling tm.subMap().
     * Then count the total number of tweet at each time period based on given frequency.
     */
    public TweetCounts_1348() {
        this.m = new HashMap<>();
    }

    /**
     * Add a new tweet under the tweet name.
     *
     * @param tweetName owner of the tweet
     * @param time      time stamp of this tweet
     */
    public void recordTweet(String tweetName, int time) {
        if (!m.containsKey(tweetName)) {
            m.put(tweetName, new TreeMap<>());
        }

        TreeMap<Integer, Integer> tmp = m.get(tweetName);
        tmp.put(time, tmp.getOrDefault(time, 0) + 1);
    }

    /**
     * Convert the frequency to seconds first, since the tweets are stored in the unit of second.
     * Keep an int array as the bucket to count each period's tweet.
     * The size of array is based on frequency and time between start time and end time.
     *
     * @param freq      given frequency, minute, hour, or day, in string format
     * @param tweetName owner of tweet
     * @param startTime start time of query
     * @param endTime   end time of query
     * @return the total number of occurrences for the given tweetName per minute, hour, or day (depending on freq)
     */
    public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {

        /* Corner case */
        if (!m.containsKey(tweetName)) {
            return null;
        }

        List<Integer> out = new LinkedList<>();

        int interval = freq.equals("minute") ? 60 : freq.equals("hour") ? 3600 : 86400;     // minute, hour, day
        int[] bucket = new int[((endTime - startTime) / interval) + 1];     // slot under given frequency

        TreeMap<Integer, Integer> tmp = m.get(tweetName);       // all tweet under this name

        for (Map.Entry<Integer, Integer> e : tmp.subMap(startTime, endTime + 1).entrySet()) {
            int index = (e.getKey() - startTime) / interval;
            bucket[index] += e.getValue();
        }

        for (int n : bucket) {
            out.add(n);
        }

        return out;
    }
}
