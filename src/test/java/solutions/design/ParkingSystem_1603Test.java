package solutions.design;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.time.Duration;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

public class ParkingSystem_1603Test {

    @Test
    public void acceptsOneOfEachType() {
        ParkingSystem_1603 parking = new ParkingSystem_1603(1, 1, 1);
        assertTrue(parking.addCar(1));
        assertTrue(parking.addCar(2));
        assertTrue(parking.addCar(3));
    }

    @Test
    public void rejectsASecondLargeCarWhenCapacityIsOne() {
        ParkingSystem_1603 parking = new ParkingSystem_1603(1, 2, 3);
        assertTrue(parking.addCar(1));
        assertFalse(parking.addCar(1));
        assertTrue(parking.addCar(2));
        assertTrue(parking.addCar(3));
    }

    @Test
    public void rejectsASecondMediumCarWhenCapacityIsOne() {
        ParkingSystem_1603 parking = new ParkingSystem_1603(2, 1, 3);
        assertTrue(parking.addCar(2));
        assertFalse(parking.addCar(2));
        assertTrue(parking.addCar(1));
        assertTrue(parking.addCar(3));
    }

    @Test
    public void rejectsASecondSmallCarWhenCapacityIsOne() {
        ParkingSystem_1603 parking = new ParkingSystem_1603(2, 3, 1);
        assertTrue(parking.addCar(3));
        assertFalse(parking.addCar(3));
        assertTrue(parking.addCar(1));
        assertTrue(parking.addCar(2));
    }

    @Test
    public void exhaustsEachTypeIndependently() {
        ParkingSystem_1603 parking = new ParkingSystem_1603(2, 3, 1);
        int[] requests = {1, 1, 1, 2, 2, 2, 2, 3, 3};
        boolean[] expected = {true, true, false, true, true, true, false, true, false};
        assertSequence(parking, requests, expected);
    }

    @Test
    public void failedRequestDoesNotConsumeAFutureSpace() {
        ParkingSystem_1603 parking = new ParkingSystem_1603(1, 1, 1);
        assertTrue(parking.addCar(1));
        assertFalse(parking.addCar(1));
        assertTrue(parking.addCar(2));
        assertFalse(parking.addCar(2));
        assertTrue(parking.addCar(3));
        assertFalse(parking.addCar(3));
    }

    @Test
    public void interleavedRequestsUseOnlyTheirOwnCounters() {
        ParkingSystem_1603 parking = new ParkingSystem_1603(2, 1, 2);
        int[] requests = {3, 1, 2, 3, 1, 2, 3, 1};
        boolean[] expected = {true, true, true, true, true, false, false, false};
        assertSequence(parking, requests, expected);
    }

    @Test
    public void zeroCapacityTypeIsAlwaysRejectedWithoutAffectingOthers() {
        ParkingSystem_1603 parking = new ParkingSystem_1603(0, 2, 0);
        assertFalse(parking.addCar(1));
        assertTrue(parking.addCar(2));
        assertFalse(parking.addCar(3));
        assertTrue(parking.addCar(2));
        assertFalse(parking.addCar(2));
    }

    @Test
    public void repeatedCallsAfterCompleteExhaustionRemainFalse() {
        ParkingSystem_1603 parking = new ParkingSystem_1603(1, 1, 1);
        int[] requests = {1, 2, 3, 1, 2, 3, 1, 2, 3};
        boolean[] expected = {true, true, true, false, false, false, false, false, false};
        assertSequence(parking, requests, expected);
    }

    @Test
    public void separateInstancesDoNotShareRemainingCapacity() {
        ParkingSystem_1603 first = new ParkingSystem_1603(1, 1, 1);
        ParkingSystem_1603 second = new ParkingSystem_1603(1, 1, 1);
        assertTrue(first.addCar(1));
        assertFalse(first.addCar(1));
        assertTrue(second.addCar(1));
        assertFalse(second.addCar(1));
        assertTrue(first.addCar(2));
        assertTrue(second.addCar(2));
    }

    @Test
    public void maximumDocumentedCapacitySupportsTheMaximumCallCount() {
        ParkingSystem_1603 parking = new ParkingSystem_1603(1000, 1000, 1000);
        assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            for (int count = 0; count < 1000; count++) {
                failIfInterrupted();
                assertTrue(parking.addCar(count % 3 + 1), "request " + count);
            }
        });
    }

    @Test
    public void maximumCapacityReachesRejectionAtTheLastAllowedCall() {
        // A 999-capacity instance can expose both the final success and the
        // first rejection while staying within the 1,000-call contract.
        ParkingSystem_1603 parking = new ParkingSystem_1603(999, 0, 0);
        assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            for (int count = 0; count < 999; count++) {
                failIfInterrupted();
                assertTrue(parking.addCar(1), "request " + count);
            }
            failIfInterrupted();
            assertFalse(parking.addCar(1));
        });
    }

    @Test
    public void deterministicMixedWorkloadMatchesIndependentCounterOracle() {
        int[] capacities = {7, 4, 6};
        int[] requests = new int[300];
        for (int index = 0; index < requests.length; index++) {
            requests[index] = (index * 17 + 5) % 3 + 1;
        }
        assertSequence(new ParkingSystem_1603(capacities[0], capacities[1], capacities[2]),
                requests, expectedResults(capacities, requests));
    }

    @Test
    @Timeout(5)
    public void exhaustivelyChecksSmallCapacitiesAndShortRequestSequences() {
        // The official capacities include zero through 1000. This exhausts all
        // 27 capacity triples in 0..2 and all 3^0 + ... + 3^6 request
        // sequences; larger capacities and longer sequences are covered above.
        for (int big = 0; big <= 2; big++) {
            for (int medium = 0; medium <= 2; medium++) {
                for (int small = 0; small <= 2; small++) {
                    int[] capacities = {big, medium, small};
                    for (int length = 0; length <= 6; length++) {
                        int sequenceCount = 1;
                        for (int index = 0; index < length; index++) {
                            sequenceCount *= 3;
                        }
                        for (int encoded = 0; encoded < sequenceCount; encoded++) {
                            failIfInterrupted();
                            int[] requests = decodeRequestSequence(encoded, length);
                            ParkingSystem_1603 parking =
                                    new ParkingSystem_1603(big, medium, small);
                            assertArrayEquals(expectedResults(capacities, requests),
                                    actualResults(parking, requests),
                                    "capacities=" + Arrays.toString(capacities)
                                            + ", sequence=" + Arrays.toString(requests));
                        }
                    }
                }
            }
        }
    }

    private static void assertSequence(ParkingSystem_1603 parking, int[] requests, boolean[] expected) {
        assertEquals(requests.length, expected.length);
        boolean[] actual = new boolean[requests.length];
        for (int index = 0; index < requests.length; index++) {
            actual[index] = parking.addCar(requests[index]);
        }
        assertArrayEquals(expected, actual);
    }

    private static boolean[] expectedResults(int[] capacities, int[] requests) {
        int[] remaining = Arrays.copyOf(capacities, capacities.length);
        boolean[] expected = new boolean[requests.length];
        for (int index = 0; index < requests.length; index++) {
            int slot = requests[index] - 1;
            expected[index] = remaining[slot] > 0;
            if (expected[index]) {
                remaining[slot]--;
            }
        }
        return expected;
    }

    private static boolean[] actualResults(ParkingSystem_1603 parking, int[] requests) {
        boolean[] actual = new boolean[requests.length];
        for (int index = 0; index < requests.length; index++) {
            actual[index] = parking.addCar(requests[index]);
        }
        return actual;
    }

    private static int[] decodeRequestSequence(int encoded, int length) {
        int[] requests = new int[length];
        for (int index = 0; index < length; index++) {
            requests[index] = encoded % 3 + 1;
            encoded /= 3;
        }
        return requests;
    }

    // Test drivers throw immediately when the preemptive timeout interrupts them.
    private static void failIfInterrupted() {
        if (Thread.interrupted()) {
            throw new AssertionError("test workload was interrupted before it completed");
        }
    }
}
