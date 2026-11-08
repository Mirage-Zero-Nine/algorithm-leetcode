package solutions.design;

import java.util.HashMap;
import java.util.Map;

/**
 * Implement the class UndergroundSystem that supports three methods:
 * 1. checkIn(int id, string stationName, int t)
 * - A customer with id card equal to id, gets in the station stationName at time t.
 * - A customer can only be checked into one place at a time.
 * 2. checkOut(int id, string stationName, int t)
 * - A customer with id card equal to id, gets out from the station stationName at time t.
 * 3. getAverageTime(string startStation, string endStation)
 * Returns the average time to travel between the startStation and the endStation.
 * The average time is computed from all the previous traveling from startStation to endStation that happened directly.
 * Call to getAverageTime is always valid.
 * You can assume all calls to checkIn and checkOut methods are consistent.
 * That is, if a customer gets in at time t1 at some station, then it gets out at time t2 with t2 > t1.
 * All events happen in chronological order.
 *
 * @author BorisMirage
 * Time: 2020/05/01 19:03
 * Created with IntelliJ IDEA
 */

public class UndergroundSystem_1396 {
    // Maps each checked-in passenger id to its origin station and check-in time.
    private final Map<Integer, Entry> checkIns;
    // Maps each directed start-end route to [totalDuration, tripCount].
    private final Map<String, long[]> times;

    /**
     * Creates an empty system. {@code checkIns} uses a passenger id as its key and an {@link Entry}
     * containing the origin station and check-in time as its value. {@code times} uses a directed
     * {@code start-end} route key and stores [total duration, trip count], whose quotient is the
     * route average without retaining individual trips. Station names are bounded by 10 chars.
     *
     * <p>Time complexity: O(1), for creating the two empty hashmaps.</p>
     * <p>Auxiliary space complexity: O(1) initially; after later calls, retained storage is
     * O(P + R), where {@code P} is peak active passengers and {@code R} is distinct routes.</p>
     */
    public UndergroundSystem_1396() {
        checkIns = new HashMap<>();
        times = new HashMap<>();
    }

    /**
     * Records the passenger's origin and check-in time. The contract supplies an id that is not
     * currently checked in; checkout removes this entry before the id can be reused.
     * <p>Time complexity: O(1) amortized expected; occasional hashmap resizing is amortized.</p>
     * <p>Auxiliary space complexity: O(1) normally and O(P) transiently during resize, where
     * {@code P} is the current active-passenger map size.</p>
     *
     * @param id          passenger identifier
     * @param stationName origin station
     * @param t           check-in timestamp
     */
    public void checkIn(int id, String stationName, int t) {
        checkIns.put(id, new Entry(stationName, t));
    }

    /**
     * Completes a trip, removes the passenger's temporary check-in, and adds its duration to the
     * directed route aggregate. The aggregate stores a sum and count, so individual trips are not
     * retained.
     * <p>Time complexity: O(1) amortized expected; bounded-name hashing and occasional resizing
     * are amortized.</p>
     * <p>Auxiliary space complexity: O(1) normally and O(R) transiently when the route table
     * resizes, where {@code R} is its current distinct-route count.</p>
     *
     * @param id          passenger identifier
     * @param stationName destination station
     * @param t           check-out timestamp
     */
    public void checkOut(int id, String stationName, int t) {
        Entry e = checkIns.get(id);
        // Remove the temporary record so this completed trip cannot affect a later checkout.
        checkIns.remove(id);
        long[] arr = times.computeIfAbsent(getKey(e.stationName, stationName), _ -> new long[2]);
        // long keeps the route total safe across the maximum number of trips; cast before division
        // in getAverageTime so fractional averages are not truncated.
        arr[0] += t - e.time;
        arr[1]++;
    }

    /**
     * Returns the mean duration of completed trips on a directed route. The problem contract
     * guarantees that the requested route has at least one completed trip.
     * <p>Time complexity: O(1) expected under the bounded station-name contract.</p>
     * <p>Auxiliary space complexity: O(1) for the bounded temporary route key; the aggregate is
     * not copied.</p>
     *
     * @param startStation origin station
     * @param endStation   destination station
     * @return average travel time
     */
    public double getAverageTime(String startStation, String endStation) {
        long[] arr = times.get(getKey(startStation, endStation));
        // Cast before division so a fractional average is not truncated by integer arithmetic.
        return (double) arr[0] / arr[1];
    }

    /**
     * Builds a key for the directed route. Since station names are alphanumeric, the separator
     * cannot occur inside either name and therefore preserves both station boundaries.
     * <p>Time complexity: O(s + e), where {@code s} and {@code e} are station-name lengths.</p>
     * <p>Auxiliary space complexity: O(1) excluding the O(s + e) returned key.</p>
     *
     * @param start origin station
     * @param end   destination station
     * @return route key
     */
    private String getKey(String start, String end) {
        return start + "-" + end;
    }

    static class Entry {
        final String stationName;
        final int time;

        /**
         * Stores the origin and timestamp associated with one active passenger.
         * <p>Time complexity: O(1), aside from storing the station reference.</p>
         * <p>Auxiliary space complexity: O(1), because the station string is not copied.</p>
         *
         * @param stationName origin station
         * @param time        check-in timestamp
         */
        Entry(String stationName, int time) {
            this.stationName = stationName;
            this.time = time;
        }
    }
}
