package solutions.design;

import org.junit.jupiter.api.Test;
import util.IsolatedTestProcess;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SeatManager_1845Test {

    @Test
    void reservesSeatsInIncreasingOrder() {
        SeatManager_1845 manager = new SeatManager_1845(5);

        assertEquals(1, manager.reserve());
        assertEquals(2, manager.reserve());
        assertEquals(3, manager.reserve());
        assertEquals(4, manager.reserve());
        assertEquals(5, manager.reserve());
    }

    @Test
    void reservesOnlySeatInSingletonManager() {
        SeatManager_1845 manager = new SeatManager_1845(1);

        assertEquals(1, manager.reserve());
    }

    @Test
    void returnedLowestSeatPrecedesSeatsStillAvailable() {
        SeatManager_1845 manager = new SeatManager_1845(6);

        assertEquals(1, manager.reserve());
        assertEquals(2, manager.reserve());
        manager.unreserve(1);

        assertEquals(1, manager.reserve());
        assertEquals(3, manager.reserve());
    }

    @Test
    void returnedHigherSeatWaitsBehindLowerAvailableSeats() {
        SeatManager_1845 manager = new SeatManager_1845(6);

        assertEquals(1, manager.reserve());
        assertEquals(2, manager.reserve());
        assertEquals(3, manager.reserve());
        manager.unreserve(3);

        assertEquals(3, manager.reserve());
        assertEquals(4, manager.reserve());
    }

    @Test
    void canReturnSeveralSeatsAndReservesThemInSortedOrder() {
        SeatManager_1845 manager = new SeatManager_1845(7);

        for (int expected = 1; expected <= 5; expected++) {
            assertEquals(expected, manager.reserve());
        }
        manager.unreserve(5);
        manager.unreserve(2);
        manager.unreserve(4);

        assertEquals(2, manager.reserve());
        assertEquals(4, manager.reserve());
        assertEquals(5, manager.reserve());
        assertEquals(6, manager.reserve());
    }

    @Test
    void returnedSeatCanBeReusedAcrossManyCycles() {
        SeatManager_1845 manager = new SeatManager_1845(1);

        for (int cycle = 0; cycle < 100; cycle++) {
            assertEquals(1, manager.reserve());
            manager.unreserve(1);
        }
        assertEquals(1, manager.reserve());
    }

    @Test
    void emptyManagerUsesExistingSoldOutExtension() {
        assertEquals(-1, new SeatManager_1845(0).reserve());
    }

    @Test
    void largeManagerStartsAtOneAndKeepsFinalSeatAvailable() {
        SeatManager_1845 manager = new SeatManager_1845(1000);

        assertEquals(1, manager.reserve());
        for (int seat = 2; seat < 1000; seat++) {
            assertEquals(seat, manager.reserve());
        }
        assertEquals(1000, manager.reserve());
    }

    @Test
    void largeReserveAndReturnSequencePreservesLowestFreeSeat() throws Exception {
        IsolatedTestProcess.run(SeatManager_1845Test.class,
                "runLargeReserveAndReturnSequencePreservesLowestFreeSeat", 10);
    }

    void runLargeReserveAndReturnSequencePreservesLowestFreeSeat() {
        int seatCount = 100_000;
        SeatManager_1845 manager = new SeatManager_1845(seatCount);
        for (int seat = 1; seat <= seatCount; seat++) {
            assertEquals(seat, manager.reserve());
        }
        for (int seat = seatCount; seat >= 2; seat -= 2) {
            manager.unreserve(seat);
        }
        for (int seat = 2; seat <= seatCount; seat += 2) {
            assertEquals(seat, manager.reserve());
        }
        assertEquals(-1, manager.reserve());
    }

    @Test
    void seededValidOperationSequencesMatchIndependentFreeSeatModel() throws Exception {
        IsolatedTestProcess.run(SeatManager_1845Test.class,
                "runSeededValidOperationSequencesMatchIndependentFreeSeatModel", 10);
    }

    void runSeededValidOperationSequencesMatchIndependentFreeSeatModel() {
        Random random = new Random(1845);
        SeatManager_1845 manager = new SeatManager_1845(25);
        BitSet free = new BitSet(26);
        free.set(1, 26);

        for (int step = 0; step < 5000; step++) {
            if (free.isEmpty() || (random.nextBoolean() && free.cardinality() < 25)) {
                int seat = random.nextInt(1, 26);
                if (!free.get(seat)) {
                    manager.unreserve(seat);
                    free.set(seat);
                }
            } else {
                int expected = free.nextSetBit(1);
                assertEquals(expected, manager.reserve(), "step=" + step);
                free.clear(expected);
            }
        }
    }

    @Test
    void exhaustivelyChecksAllValidShortOperationSequencesForUpToThreeSeats() throws Exception {
        IsolatedTestProcess.run(SeatManager_1845Test.class,
                "runExhaustivelyChecksAllValidShortOperationSequencesForUpToThreeSeats", 10);
    }

    void runExhaustivelyChecksAllValidShortOperationSequencesForUpToThreeSeats() {
        for (int seatCount = 1; seatCount <= 3; seatCount++) {
            enumerateValidSequences(seatCount, 0, new ArrayList<>());
        }
    }

    private void enumerateValidSequences(int seatCount, int depth, List<Character> prefix) {
        verifySequence(seatCount, prefix);
        if (depth == 6) {
            return;
        }

        prefix.add('R');
        enumerateValidSequences(seatCount, depth + 1, prefix);
        prefix.removeLast();

        for (int seat = 1; seat <= seatCount; seat++) {
            prefix.add((char) ('0' + seat));
            enumerateValidSequences(seatCount, depth + 1, prefix);
            prefix.removeLast();
        }
    }

    private void verifySequence(int seatCount, List<Character> operations) {
        SeatManager_1845 manager = new SeatManager_1845(seatCount);
        BitSet free = new BitSet(seatCount + 1);
        free.set(1, seatCount + 1);

        for (int index = 0; index < operations.size(); index++) {
            char operation = operations.get(index);
            if (operation == 'R') {
                int expected = free.nextSetBit(1);
                if (expected >= 0) {
                    assertEquals(expected, manager.reserve(), "seats=" + seatCount + ", ops=" + operations);
                    free.clear(expected);
                }
            } else {
                int seat = operation - '0';
                // Restrict the enumeration to valid unreserve operations from the problem contract.
                if (!free.get(seat)) {
                    manager.unreserve(seat);
                    free.set(seat);
                }
            }
        }
    }
}
