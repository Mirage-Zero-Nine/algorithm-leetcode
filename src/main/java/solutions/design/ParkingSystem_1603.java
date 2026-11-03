package solutions.design;

/**
 * Design a parking system for a parking lot.
 * The parking lot has three kinds of parking spaces: big, medium, and small, with a fixed number of slots for each size.
 * Implement the ParkingSystem class:
 * ParkingSystem(int big, int medium, int small)
 * Initializes object of the ParkingSystem class. The number of slots for each parking space are given as part of the constructor.
 * bool addCar(int carType)
 * Checks whether there is a parking space of carType for the car that wants to get into the parking lot.
 * carType can be of three kinds: big, medium, or small, which are represented by 1, 2, and 3 respectively.
 * A car can only park in a parking space of its carType.
 * If there is no space available, return false, else park the car in that size space and return true.
 *
 * @author BorisMirage
 * Time: 2026/11/02 20:17
 * Created with IntelliJ IDEA
 */

public class ParkingSystem_1603 {
    private final long[] parking;

    /**
     * Creates independent counters for large, medium, and small spaces.
     * The private fixed-size array is changed only by {@link #addCar(int)}.
     *
     * <p>Time complexity: O(1), because exactly three counters are stored.</p>
     * <p>Auxiliary space complexity: O(1), because the fixed-size counter
     * array is the only retained data structure.</p>
     *
     * @param big    initial number of large spaces
     * @param medium initial number of medium spaces
     * @param small  initial number of small spaces
     */
    public ParkingSystem_1603(int big, int medium, int small) {
        parking = new long[]{big, medium, small};
    }

    /**
     * Attempts to reserve one space of the requested type. Subtracting first
     * makes zero the boundary: a counter that becomes negative means every
     * matching space was already reserved, so the request is rejected. The
     * problem supplies {@code carType} in the range 1 through 3.
     *
     * <p>Time complexity: O(1), because one counter is inspected and updated.</p>
     * <p>Auxiliary space complexity: O(1), because no storage grows with the
     * number of calls.</p>
     *
     * @param carType 1 for large, 2 for medium, or 3 for small
     * @return {@code true} when a matching space was available; otherwise
     * {@code false}
     */
    public boolean addCar(int carType) {
        return --parking[carType - 1] >= 0;
    }
}
