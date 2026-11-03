package playground;

import lombok.NonNull;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Design and implement an object-oriented parking lot system.
 *
 * <p>The system manages one parking lot with small, medium, and large spots.
 * A small vehicle fits in any spot, a medium vehicle fits in a medium or large
 * spot, and a large vehicle fits only in a large spot. When more than one spot
 * can fit a vehicle, use the smallest suitable spot.
 *
 * <p>Support parking and unparking vehicles, and querying the number of
 * available spots by size. Every vehicle has a unique identifier. Parking a
 * vehicle that is already in the lot, attempting to unpark an unknown vehicle,
 * or attempting to park when no suitable spot exists must fail without changing
 * the lot's state.
 *
 * <p>For example, with one small, one medium, and one large spot, parking a
 * medium vehicle uses the medium spot. Parking a small vehicle then uses the
 * small spot; a second small vehicle uses the large spot. Unparking the medium
 * vehicle makes the medium spot available again.
 *
 * <p>Choose the object model and public API. Keep the scope to a single lot,
 * three vehicle/spot sizes, parking, unparking, and availability queries.
 *
 * <p>The implementation should provide a constructor with the signature
 * {@code ParkingLotDesign(int smallCapacity, int mediumCapacity,
 * int largeCapacity)} and the public methods below for the required operations.
 */
public class ParkingLotDesign {

    /**
     * The only vehicle and parking-spot sizes supported by this exercise.
     */
    public enum Size {
        SMALL,
        MEDIUM,
        LARGE
    }

    private final Map<Size, Integer> capacityMap;
    private static final Size[] SIZES = Size.values();
    private final Map<String, Ticket> ticketMap;

    /**
     * Creates the lot's two pieces of state.
     *
     * <p>{@code capacityMap} stores the number of currently free physical spots
     * by size. {@code ticketMap} stores one ticket per parked vehicle, including
     * the spot size actually assigned to it so that a later exit can restore
     * the correct capacity. The maps are intentionally scoped to this one lot;
     * no state is shared between instances.
     *
     * <p>The public operations are intended for sequential use. The maps keep
     * the counters and tickets together, but this class does not define a
     * concurrent parking policy.
     *
     * <p>Time complexity: O(1) because the lot has exactly three spot sizes.
     *
     * <p>Auxiliary space: O(1) per parked vehicle, in addition to the three
     * capacity entries.
     *
     * @param smallCapacity  number of small spots
     * @param mediumCapacity number of medium spots
     * @param largeCapacity  number of large spots
     * @throws IllegalArgumentException if any capacity is negative
     */
    public ParkingLotDesign(int smallCapacity, int mediumCapacity, int largeCapacity) {
        if (smallCapacity < 0 || mediumCapacity < 0 || largeCapacity < 0) {
            throw new IllegalArgumentException("Spot capacities cannot be negative");
        }
        capacityMap = new ConcurrentHashMap<>(3);
        capacityMap.put(Size.SMALL, smallCapacity);
        capacityMap.put(Size.MEDIUM, mediumCapacity);
        capacityMap.put(Size.LARGE, largeCapacity);
        ticketMap = new ConcurrentHashMap<>();
    }

    /**
     * Attempts to park a uniquely identified vehicle in the smallest compatible spot.
     *
     * @param vehicleId   unique identifier of the vehicle
     * @param vehicleSize size of the vehicle
     * @return {@code true} when the vehicle was parked; {@code false} when
     * it is already parked or no compatible spot is available
     * @throws NullPointerException if {@code vehicleId} or {@code vehicleSize} is null
     *
     *                              <p>Time complexity: O(1), scanning at most the three ordered sizes.
     *
     *                              <p>Auxiliary space: O(1) for the operation, excluding the new ticket.
     */
    public boolean park(@NonNull String vehicleId, @NonNull Size vehicleSize) {
        // A ticket is the membership check: duplicate parking must not consume
        // another spot or alter the existing vehicle's allocation.
        if (ticketMap.containsKey(vehicleId)) {
            return false;
        }

        // Size.ordinal() orders compatibility from the vehicle's exact size
        // upward, so the first available entry is the smallest suitable spot.
        for (int i = vehicleSize.ordinal(); i < SIZES.length; i++) {
            Size size = SIZES[i];
            if (capacityMap.get(size) > 0) {
                // Record the allocated size on the ticket before returning. A
                // small vehicle may occupy a medium/large spot, and that exact
                // size—not the vehicle's requested size—must later be released.
                ticketMap.put(vehicleId, new Ticket(vehicleId, size));
                capacityMap.compute(size, (_, capacity) -> capacity - 1);
                return true;
            }
        }
        return false;
    }

    /**
     * Removes a vehicle from the lot.
     *
     * @param vehicleId identifier of the vehicle to remove
     * @return {@code true} when the vehicle was removed; {@code false} when
     * no vehicle with that identifier is parked
     * @throws NullPointerException if {@code vehicleId} is null
     *
     *                              <p>Time complexity: O(1).
     *
     *                              <p>Auxiliary space: O(1) for the operation.
     */
    public boolean unpark(@NonNull String vehicleId) {
        // corner cases
        if (!ticketMap.containsKey(vehicleId)) {
            return false;
        }

        Ticket unparkVehicle = ticketMap.get(vehicleId);
        // Release the physical spot recorded by the ticket. This matters when
        // the vehicle was assigned a larger fallback spot.
        Size size = unparkVehicle.parkedSpace;
        capacityMap.compute(size, (_, capacity) -> capacity + 1);
        ticketMap.remove(unparkVehicle.vehicleId);
        return true;
    }

    /**
     * Reports the currently available spots of one size.
     *
     * @param spotSize size to query
     * @return the number of unoccupied spots of {@code spotSize}
     * @throws NullPointerException if {@code spotSize} is null
     *
     *                              <p>Time complexity: O(1).
     *
     *                              <p>Auxiliary space: O(1).
     */
    public int available(@NonNull Size spotSize) {
        return capacityMap.get(spotSize);
    }

    /**
     * Retains one parked vehicle's allocation.
     *
     * <p>{@code parkedSpace} is deliberately separate from the vehicle size:
     * it identifies the physical spot consumed by this ticket and therefore
     * the counter that {@link #unpark(String)} must increment.
     */
    class Ticket {
        final String vehicleId;
        final long timestamp;
        boolean isParking;
        Size parkedSpace;

        /**
         * Records the vehicle and physical spot size assigned to its ticket.
         *
         * @param vehicleId identifier of the parked vehicle
         * @param size      physical spot size assigned to the vehicle
         * @throws NullPointerException if either argument is null
         */
        Ticket(@NonNull String vehicleId, @NonNull Size size) {
            this.vehicleId = vehicleId;
            timestamp = System.currentTimeMillis();
            isParking = true;
            parkedSpace = size;
        }
    }
}
