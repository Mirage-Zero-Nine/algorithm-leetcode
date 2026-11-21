package solutions.design;

/**
 * Design a data structure that keeps track of the values in it and answers some queries regarding their frequencies.
 * Implement the FrequencyTracker class.
 * FrequencyTracker(): Initializes the FrequencyTracker object with an empty array initially.
 * void add(int number): Adds number to the data structure.
 * void deleteOne(int number): Deletes one occurrence of number from the data structure. The data structure may not contain number, and in this case nothing is deleted.
 * bool hasFrequency(int frequency): Returns true if there is a number in the data structure that occurs frequency number of times, otherwise, it returns false.
 *
 * @author BorisMirage
 * Time: 2026/11/20 20:11
 * Created with IntelliJ IDEA
 */

public class FrequencyTracker_2671 {
    private final int[] count;
    private final int[] frequency;

    /**
     * Creates an empty tracker using one array indexed by number and one array indexed by occurrence count.
     * Moving a number from count {@code c} to {@code c + 1} decrements the bucket for {@code c} and increments
     * the bucket for {@code c + 1}; therefore each update and query is constant time. The number range has
     * 100,001 slots and the frequency range has 200,001 slots, enough for the LeetCode bounds of 100,000
     * numbers and 200,000 calls. A number can reach frequency 200,000 after that many additions, although valid
     * queries ask only about frequencies through 100,000. The zero-frequency bucket is maintained internally but
     * is not a useful query result because absent values are not represented there as positive entries.
     *
     * <p>Time complexity: O(U + Q), where U = 100,001 number slots and Q = 200,001 frequency slots initialized.</p>
     * <p>Auxiliary space complexity: O(1) beyond the O(U + Q) retained storage for the two arrays.</p>
     */
    public FrequencyTracker_2671() {
        count = new int[100001];
        frequency = new int[200001];
    }

    /**
     * Adds one occurrence and moves the number between its old and new frequency buckets. Values at zero are
     * decremented unconditionally, so {@code frequency[0]} tracks the negative number of distinct active values;
     * only positive frequencies are part of the query contract.
     *
     * @param number a value in the supported range 1..100,000
     *               <p>Time complexity: O(1), using direct array indexing.</p>
     *               <p>Auxiliary space complexity: O(1), with no per-call allocation.</p>
     */
    public void add(int number) {
        // Remove the value from its prior bucket before placing it in the incremented bucket.
        count[number]++;
        frequency[count[number] - 1]--;
        frequency[count[number]]++;
    }

    /**
     * Removes one occurrence when present and moves the number to its decremented frequency bucket. A missing
     * number leaves both arrays unchanged.
     *
     * @param number a value in the supported range 1..100,000
     *               <p>Time complexity: O(1), using direct array indexing.</p>
     *               <p>Auxiliary space complexity: O(1), with no per-call allocation.</p>
     */
    public void deleteOne(int number) {
        if (count[number] <= 0) {
            return;
        }
        // Transfer this value from its current bucket, including bucket one when it becomes absent.
        count[number]--;
        frequency[count[number] + 1]--;
        frequency[count[number]]++;
    }

    /**
     * Reports whether at least one value currently has the requested positive occurrence count. The supported
     * frequency range is 1..100,000; the backing array also accommodates larger counts that can result when many
     * calls add the same number.
     *
     * @param frequency a queried occurrence count in the supported range 1..100,000
     * @return whether any number occurs exactly {@code frequency} times
     * <p>Time complexity: O(1), using direct array indexing.</p>
     * <p>Auxiliary space complexity: O(1), with no per-call allocation.</p>
     */
    public boolean hasFrequency(int frequency) {
        return this.frequency[frequency] > 0;
    }
}
