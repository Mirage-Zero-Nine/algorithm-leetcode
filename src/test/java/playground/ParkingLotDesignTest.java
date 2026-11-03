package playground;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Timeout(value = 10, unit = TimeUnit.SECONDS, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
class ParkingLotDesignTest {

    @Test
    void zeroCapacitiesRejectEveryVehicle() {
        ParkingLotDesign lot = new ParkingLotDesign(0, 0, 0);
        assertFalse(lot.park("car", ParkingLotDesign.Size.SMALL));
        assertAvailability(lot, 0, 0, 0);
    }

    @Test
    void eachVehicleStartsInTheSmallestCompatibleSpot() {
        ParkingLotDesign lot = new ParkingLotDesign(1, 1, 1);
        assertTrue(lot.park("small", ParkingLotDesign.Size.SMALL));
        assertTrue(lot.park("medium", ParkingLotDesign.Size.MEDIUM));
        assertTrue(lot.park("large", ParkingLotDesign.Size.LARGE));
        assertAvailability(lot, 0, 0, 0);
    }

    @Test
    void smallVehicleFallsBackFromSmallToMediumToLarge() {
        ParkingLotDesign lot = new ParkingLotDesign(0, 1, 1);
        assertTrue(lot.park("first", ParkingLotDesign.Size.SMALL));
        assertTrue(lot.park("second", ParkingLotDesign.Size.SMALL));
        assertFalse(lot.park("third", ParkingLotDesign.Size.SMALL));
        assertAvailability(lot, 0, 0, 0);
    }

    @Test
    void mediumVehicleFallsBackToLargeOnly() {
        ParkingLotDesign lot = new ParkingLotDesign(4, 0, 1);
        assertTrue(lot.park("medium", ParkingLotDesign.Size.MEDIUM));
        assertAvailability(lot, 4, 0, 0);
    }

    @Test
    void noFitLeavesUnusedSmallerSpotsUntouched() {
        ParkingLotDesign lot = new ParkingLotDesign(3, 2, 0);
        assertFalse(lot.park("bus", ParkingLotDesign.Size.LARGE));
        assertAvailability(lot, 3, 2, 0);
    }

    @Test
    void duplicateEntryDoesNotMutateTheExistingAllocation() {
        ParkingLotDesign lot = new ParkingLotDesign(1, 1, 0);
        assertTrue(lot.park("same", ParkingLotDesign.Size.SMALL));
        assertFalse(lot.park("same", ParkingLotDesign.Size.MEDIUM));
        assertAvailability(lot, 0, 1, 0);
        assertTrue(lot.unpark("same"));
        assertAvailability(lot, 1, 1, 0);
    }

    @Test
    void unknownAndRepeatedExitAreNoOps() {
        ParkingLotDesign lot = new ParkingLotDesign(1, 1, 1);
        assertFalse(lot.unpark("missing"));
        assertTrue(lot.park("car", ParkingLotDesign.Size.LARGE));
        assertTrue(lot.unpark("car"));
        assertFalse(lot.unpark("car"));
        assertAvailability(lot, 1, 1, 1);
    }

    @Test
    void nullInputsAreRejectedBeforeAnyStateChange() {
        ParkingLotDesign lot = new ParkingLotDesign(1, 1, 1);
        assertThrows(NullPointerException.class, () -> lot.park(null, ParkingLotDesign.Size.SMALL));
        assertThrows(NullPointerException.class, () -> lot.park("car", null));
        assertThrows(NullPointerException.class, () -> lot.unpark(null));
        assertThrows(NullPointerException.class, () -> lot.available(null));
        assertAvailability(lot, 1, 1, 1);
    }

    @Test
    void nullVehicleSizeIsRejectedEvenWhenVehicleAlreadyExists() {
        ParkingLotDesign lot = new ParkingLotDesign(1, 1, 0);
        assertTrue(lot.park("car", ParkingLotDesign.Size.SMALL));
        assertThrows(NullPointerException.class, () -> lot.park("car", null));
        assertAvailability(lot, 0, 1, 0);
        assertTrue(lot.unpark("car"));
        assertAvailability(lot, 1, 1, 0);
    }

    @Test
    void negativeCapacitiesAreRejectedForEachPosition() {
        assertThrows(IllegalArgumentException.class, () -> new ParkingLotDesign(-1, 0, 0));
        assertThrows(IllegalArgumentException.class, () -> new ParkingLotDesign(0, -1, 0));
        assertThrows(IllegalArgumentException.class, () -> new ParkingLotDesign(0, 0, -1));
        assertThrows(IllegalArgumentException.class,
                () -> new ParkingLotDesign(Integer.MIN_VALUE, 0, 0));
        assertThrows(IllegalArgumentException.class,
                () -> new ParkingLotDesign(0, Integer.MIN_VALUE, 0));
        assertThrows(IllegalArgumentException.class,
                () -> new ParkingLotDesign(0, 0, Integer.MIN_VALUE));
    }

    @Test
    void zeroAndMaximumCapacitiesAreAcceptedWithoutAllocatingEverySpot() {
        ParkingLotDesign zero = new ParkingLotDesign(0, 0, 0);
        assertAvailability(zero, 0, 0, 0);
        ParkingLotDesign maximum = new ParkingLotDesign(Integer.MAX_VALUE, 0, 0);
        assertAvailability(maximum, Integer.MAX_VALUE, 0, 0);
        assertTrue(maximum.park("car", ParkingLotDesign.Size.SMALL));
        assertEquals(Integer.MAX_VALUE - 1, maximum.available(ParkingLotDesign.Size.SMALL));
    }

    @Test
    void exitReleasesTheActualFallbackSpot() {
        ParkingLotDesign lot = new ParkingLotDesign(0, 0, 1);
        assertTrue(lot.park("small", ParkingLotDesign.Size.SMALL));
        assertAvailability(lot, 0, 0, 0);
        assertTrue(lot.unpark("small"));
        assertAvailability(lot, 0, 0, 1);
    }

    @Test
    void exactSizeAvailabilityAndRepeatedReuseRemainAccurate() {
        ParkingLotDesign lot = new ParkingLotDesign(1, 1, 1);
        for (int i = 0; i < 4; i++) {
            String id = "small-" + i;
            assertTrue(lot.park(id, ParkingLotDesign.Size.SMALL));
            assertTrue(lot.unpark(id));
            assertAvailability(lot, 1, 1, 1);
        }
        assertTrue(lot.park("medium", ParkingLotDesign.Size.MEDIUM));
        assertAvailability(lot, 1, 0, 1);
    }

    @Test
    void instancesDoNotShareCapacityOrTickets() {
        ParkingLotDesign first = new ParkingLotDesign(1, 0, 0);
        ParkingLotDesign second = new ParkingLotDesign(1, 0, 0);
        assertTrue(first.park("car", ParkingLotDesign.Size.SMALL));
        assertFalse(first.park("other", ParkingLotDesign.Size.SMALL));
        assertTrue(second.park("car", ParkingLotDesign.Size.SMALL));
        assertTrue(first.unpark("car"));
        assertAvailability(second, 0, 0, 0);
    }

    @Test
    void exhaustiveShortOperationSequencesMatchIndependentPhysicalSpotOracle() {
        assertTimeout(Duration.ofSeconds(8), () -> {
            Operation[] operations = {
                    new Operation(true, "a", ParkingLotDesign.Size.SMALL),
                    new Operation(true, "a", ParkingLotDesign.Size.MEDIUM),
                    new Operation(true, "a", ParkingLotDesign.Size.LARGE),
                    new Operation(true, "b", ParkingLotDesign.Size.SMALL),
                    new Operation(true, "b", ParkingLotDesign.Size.MEDIUM),
                    new Operation(true, "b", ParkingLotDesign.Size.LARGE),
                    new Operation(false, "a", null),
                    new Operation(false, "b", null),
                    new Operation(false, "unknown", null)
            };
            runSequences(operations, new Operation[5], 0);
        });
    }

    @Test
    void largeCapacitiesSupportManyAllocationsAndReleases() {
        int capacity = 10_000;
        ParkingLotDesign lot = new ParkingLotDesign(capacity, capacity, capacity);
        for (int i = 0; i < capacity; i++) {
            assertTrue(lot.park("s" + i, ParkingLotDesign.Size.SMALL));
        }
        for (int i = 0; i < capacity; i++) {
            assertTrue(lot.park("m" + i, ParkingLotDesign.Size.MEDIUM));
        }
        for (int i = 0; i < capacity; i++) {
            assertTrue(lot.park("l" + i, ParkingLotDesign.Size.LARGE));
        }
        assertAvailability(lot, 0, 0, 0);
        assertFalse(lot.park("overflow", ParkingLotDesign.Size.SMALL));
        for (int i = 0; i < capacity; i += 2) {
            assertTrue(lot.unpark("s" + i));
            assertTrue(lot.unpark("m" + i));
            assertTrue(lot.unpark("l" + i));
        }
        assertAvailability(lot, capacity / 2, capacity / 2, capacity / 2);
    }

    private static void runSequences(Operation[] choices, Operation[] sequence, int depth) {
        // The class-level separate-thread timeout interrupts this traversal;
        // checking the flag makes a timeout stop the exhaustive work promptly.
        if (Thread.currentThread().isInterrupted()) {
            throw new AssertionError("exhaustive operation traversal was interrupted");
        }
        if (depth == sequence.length) {
            checkAgainstOracle(sequence);
            return;
        }
        for (Operation choice : choices) {
            sequence[depth] = choice;
            runSequences(choices, sequence, depth + 1);
        }
    }

    private static void checkAgainstOracle(Operation[] sequence) {
        ParkingLotDesign lot = new ParkingLotDesign(1, 1, 1);
        PhysicalOracle oracle = new PhysicalOracle(1, 1, 1);
        for (int i = 0; i < sequence.length; i++) {
            Operation operation = sequence[i];
            boolean expected = operation.park
                    ? oracle.park(operation.id, operation.size)
                    : oracle.unpark(operation.id);
            boolean actual = operation.park
                    ? lot.park(operation.id, operation.size)
                    : lot.unpark(operation.id);
            assertEquals(expected, actual, "sequence step " + i);
            assertAvailability(lot, oracle.free(ParkingLotDesign.Size.SMALL),
                    oracle.free(ParkingLotDesign.Size.MEDIUM), oracle.free(ParkingLotDesign.Size.LARGE));
        }
    }

    private static void assertAvailability(ParkingLotDesign lot, int small, int medium, int large) {
        assertEquals(small, lot.available(ParkingLotDesign.Size.SMALL));
        assertEquals(medium, lot.available(ParkingLotDesign.Size.MEDIUM));
        assertEquals(large, lot.available(ParkingLotDesign.Size.LARGE));
    }

    private record Operation(boolean park, String id, ParkingLotDesign.Size size) {}

    private static final class PhysicalOracle {
        private final List<Spot> spots = new ArrayList<>();
        private final Map<String, Spot> parked = new HashMap<>();

        private PhysicalOracle(int small, int medium, int large) {
            addSpots(ParkingLotDesign.Size.SMALL, small);
            addSpots(ParkingLotDesign.Size.MEDIUM, medium);
            addSpots(ParkingLotDesign.Size.LARGE, large);
        }

        private void addSpots(ParkingLotDesign.Size size, int count) {
            // The exhaustive oracle uses physical spot records rather than
            // the production counter representation, keeping its state model independent.
            for (int i = 0; i < count; i++) {
                spots.add(new Spot(size));
            }
        }

        private boolean park(String id, ParkingLotDesign.Size vehicleSize) {
            if (parked.containsKey(id)) {
                return false;
            }
            for (Spot spot : spots) {
                if (!spot.occupied && spot.size.ordinal() >= vehicleSize.ordinal()) {
                    spot.occupied = true;
                    parked.put(id, spot);
                    return true;
                }
            }
            return false;
        }

        private boolean unpark(String id) {
            Spot spot = parked.remove(id);
            if (spot == null) {
                return false;
            }
            spot.occupied = false;
            return true;
        }

        private int free(ParkingLotDesign.Size size) {
            int count = 0;
            for (Spot spot : spots) {
                if (spot.size == size && !spot.occupied) {
                    count++;
                }
            }
            return count;
        }

        private static final class Spot {
            private final ParkingLotDesign.Size size;
            private boolean occupied;

            private Spot(ParkingLotDesign.Size size) {
                this.size = size;
            }
        }
    }
}
