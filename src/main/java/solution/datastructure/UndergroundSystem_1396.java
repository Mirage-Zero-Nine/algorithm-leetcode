package solution.datastructure;

import java.util.HashMap;

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

    /*
     * The checkIn map stores all the passengers in the system.
     * The key is the passenger id, and the value is the object that stores depart station and depart time.
     * When a passenger exits the system, the entry related to this passenger will be removed to avoid duplication. */
    private final HashMap<Integer, StartTime> checkIn;

    /*
     * The key of checkOut map is the combination of start station and end station.
     * Value of the map is the total time consume under this certain path, with the number of passengers. */
    private final HashMap<String, TravelTime> checkOut;

    /**
     * Keep two hash maps to store the passenger information in system.
     * First map stores all the passengers in the system.
     * Second map stores all the paths (consists with start and end station), total time and number of passenger.
     */
    public UndergroundSystem_1396() {
        checkIn = new HashMap<>();
        checkOut = new HashMap<>();
    }

    /**
     * A new passenger enter the system. Add it to the check in map.
     *
     * @param id          passenger id
     * @param stationName name of depart station
     * @param t           depart time stamp
     */
    public void checkIn(int id, String stationName, int t) {
        checkIn.put(id, new StartTime(stationName, t));
    }

    /**
     * One passenger exit the system. Calculate the total travel time and remove it from the check in map.
     * Then add the total travel time to the path from its depart station to the destination station.
     * If the path (combined with start station and end station) exist, update the total travel time.
     * Otherwise, create a new entry in check out map.
     *
     * @param id          passenger id
     * @param stationName name of destination
     * @param t           arrived time stamp
     */
    public void checkOut(int id, String stationName, int t) {
        StartTime current = checkIn.get(id);
        checkIn.remove(id);     // the passenger has exit the system, remove it to avoid duplication

        String path = current.start + stationName;
        int travelTime = t - current.time;

        if (checkOut.containsKey(path)) {       // one new passenger travel on this exist path
            TravelTime tmp = checkOut.get(path);
            tmp.totalTime += travelTime;
            tmp.count++;
        } else {                                // one new passenger travel on a new path
            TravelTime tmp = new TravelTime(travelTime);
            checkOut.put(path, tmp);
        }
    }

    /**
     * Return the average time based on start station and end station.
     * The check out map stores all the paths with its total travel time and the number of passengers.
     *
     * @param startStation name of depart station
     * @param endStation   name of destination
     * @return average travel time between the given start and end station
     */
    public double getAverageTime(String startStation, String endStation) {
        String path = startStation + endStation;
        if (checkOut.containsKey(path)) {
            TravelTime tmp = checkOut.get(path);
            return (double) tmp.totalTime / tmp.count;
        }

        return 0;
    }

    /**
     * Stores the total time of travel and the total number of passengers travel through this path.
     */
    static class TravelTime {
        int totalTime;
        int count;

        /**
         * Constructor.
         *
         * @param totalTime total travel time
         */
        TravelTime(int totalTime) {
            this.totalTime = totalTime;
            count = 1;
        }
    }

    /**
     * Object stores the start timestamp and the name of depart station.
     */
    static class StartTime {
        String start;
        int time;

        /**
         * Constructor.
         *
         * @param start start station
         * @param time  start time
         */
        StartTime(String start, int time) {
            this.start = start;
            this.time = time;
        }
    }
}
