package playground;

import org.junit.jupiter.api.Test;
import util.IsolatedTestProcess;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BookingServiceTest {

    @Test
    void assignsLowestSeatAndReportsAvailability() {
        BookingService service = new BookingService(3);
        List<BookingService.Booking> bookings = new ArrayList<>();

        assertEquals(3, service.freeSeatCount());
        bookings.add(reserve(service, "alice", 1, 3));
        bookings.add(reserve(service, "bob", 2, 2));
        checkInvariants(service, bookings, 3);
    }

    @Test
    void oneCustomerMayOwnMultipleBookings() {
        BookingService service = new BookingService(3);
        List<BookingService.Booking> bookings = new ArrayList<>();

        bookings.add(reserve(service, "alice", 1, 3));
        bookings.add(reserve(service, "alice", 2, 2));
        assertNotEquals(bookings.get(0).bookingId(), bookings.get(1).bookingId());
        checkInvariants(service, bookings, 3);
    }

    @Test
    void soldOutReservationReturnsEmptyWithoutChangingState() {
        BookingService service = new BookingService(1);
        List<BookingService.Booking> bookings = new ArrayList<>();
        bookings.add(reserve(service, "alice", 1, 1));

        assertEquals(Optional.empty(), service.reserve("bob"));
        assertEquals(0, service.freeSeatCount());
        checkInvariants(service, bookings, 1);
    }

    @Test
    void successfulCancellationMakesTheLowestSeatAvailableAgain() {
        BookingService service = new BookingService(2);
        List<BookingService.Booking> bookings = new ArrayList<>();
        BookingService.Booking first = reserve(service, "alice", 1, 2);
        bookings.add(first);
        bookings.add(reserve(service, "bob", 2, 1));

        assertEquals(BookingService.CancelResult.CANCELLED,
                service.cancel(first.bookingId(), "alice"));
        assertEquals(1, service.freeSeatCount());
        checkInvariants(service, bookings, 2);
        BookingService.Booking replacement = reserve(service, "carol", 1, 1);
        bookings.add(replacement);
        assertNotEquals(first.bookingId(), replacement.bookingId());
        checkInvariants(service, bookings, 2);
    }

    @Test
    void wrongCustomerCannotCancelAnotherCustomersBooking() {
        BookingService service = new BookingService(2);
        List<BookingService.Booking> bookings = new ArrayList<>();
        BookingService.Booking booking = reserve(service, "alice", 1, 2);
        bookings.add(booking);

        assertEquals(BookingService.CancelResult.WRONG_CUSTOMER,
                service.cancel(booking.bookingId(), "mallory"));
        assertEquals(1, service.freeSeatCount());
        assertTrue(service.findBooking(booking.bookingId()).orElseThrow().active());
        checkInvariants(service, bookings, 2);
    }

    @Test
    void unknownBookingCannotChangeAvailability() {
        BookingService service = new BookingService(2);
        List<BookingService.Booking> bookings = new ArrayList<>();
        bookings.add(reserve(service, "alice", 1, 2));

        assertEquals(BookingService.CancelResult.UNKNOWN_BOOKING,
                service.cancel(999, "alice"));
        assertEquals(Optional.empty(), service.findBooking(999));
        checkInvariants(service, bookings, 2);
    }

    @Test
    void staleBookingCancellationDoesNotFreeReusedSeat() {
        BookingService service = new BookingService(1);
        List<BookingService.Booking> bookings = new ArrayList<>();
        BookingService.Booking oldBooking = reserve(service, "alice", 1, 1);
        bookings.add(oldBooking);
        assertEquals(BookingService.CancelResult.CANCELLED,
                service.cancel(oldBooking.bookingId(), "alice"));
        BookingService.Booking newBooking = reserve(service, "bob", 1, 1);
        bookings.add(newBooking);

        assertEquals(BookingService.CancelResult.ALREADY_CANCELLED,
                service.cancel(oldBooking.bookingId(), "alice"));
        assertTrue(service.findBooking(oldBooking.bookingId()).isPresent());
        assertFalse(service.findBooking(oldBooking.bookingId()).orElseThrow().active());
        assertTrue(service.findBooking(newBooking.bookingId()).orElseThrow().active());
        assertEquals(0, service.freeSeatCount());
        checkInvariants(service, bookings, 1);
    }

    @Test
    void nonPositiveBookingIdIsTreatedAsUnknown() {
        BookingService service = new BookingService(2);

        assertEquals(Optional.empty(), service.findBooking(0));
        assertEquals(Optional.empty(), service.findBooking(-1));
        assertEquals(BookingService.CancelResult.UNKNOWN_BOOKING, service.cancel(0, "alice"));
        assertEquals(BookingService.CancelResult.UNKNOWN_BOOKING, service.cancel(-1, "alice"));
        assertEquals(2, service.freeSeatCount());
    }

    @Test
    void rejectsInvalidSeatCountsAndCustomerIds() {
        assertThrows(IllegalArgumentException.class, () -> new BookingService(0));
        assertThrows(IllegalArgumentException.class, () -> new BookingService(-1));
        BookingService service = new BookingService(1);
        assertThrows(NullPointerException.class, () -> service.reserve(null));
        assertThrows(IllegalArgumentException.class, () -> service.reserve("  "));
        assertThrows(NullPointerException.class, () -> service.cancel(1, null));
        assertThrows(IllegalArgumentException.class, () -> service.cancel(1, ""));
        assertEquals(1, service.freeSeatCount());
    }

    @Test
    void lookupReturnsBookingOwnershipSeatAndActiveState() {
        BookingService service = new BookingService(4);
        List<BookingService.Booking> bookings = new ArrayList<>();
        BookingService.Booking booking = reserve(service, "alice", 1, 4);
        bookings.add(booking);

        assertEquals(booking, service.findBooking(booking.bookingId()).orElseThrow());
        assertEquals("alice", booking.customerId());
        assertEquals(1, booking.seatNumber());
        assertTrue(booking.active());
        checkInvariants(service, bookings, 4);
    }

    @Test
    void returnedBookingsAreImmutableSnapshots() {
        BookingService service = new BookingService(2);
        List<BookingService.Booking> bookings = new ArrayList<>();
        BookingService.Booking snapshot = reserve(service, "alice", 1, 2);
        bookings.add(snapshot);
        assertEquals(BookingService.CancelResult.CANCELLED,
                service.cancel(snapshot.bookingId(), "alice"));

        assertTrue(snapshot.active());
        assertFalse(service.findBooking(snapshot.bookingId()).orElseThrow().active());
        checkInvariants(service, bookings, 2);
    }

    @Test
    void customerIdentifiersAreStoredAndComparedExactly() {
        BookingService service = new BookingService(2);
        BookingService.Booking booking = service.reserve(" Alice ").orElseThrow();

        assertEquals(" Alice ", booking.customerId());
        assertEquals(BookingService.CancelResult.WRONG_CUSTOMER,
                service.cancel(booking.bookingId(), "Alice"));
        assertEquals(1, service.freeSeatCount());
        assertEquals(BookingService.CancelResult.CANCELLED,
                service.cancel(booking.bookingId(), " Alice "));
        assertEquals(2, service.freeSeatCount());
    }

    @Test
    void repeatedLookupsAndCountsDoNotChangeServiceState() {
        BookingService service = new BookingService(3);
        BookingService.Booking first = service.reserve("alice").orElseThrow();
        BookingService.Booking second = service.reserve("bob").orElseThrow();
        assertEquals(BookingService.CancelResult.CANCELLED,
                service.cancel(first.bookingId(), "alice"));
        BookingService.Booking cancelled = service.findBooking(first.bookingId()).orElseThrow();

        for (int attempt = 0; attempt < 10; attempt++) {
            assertEquals(cancelled, service.findBooking(first.bookingId()).orElseThrow());
            assertEquals(second, service.findBooking(second.bookingId()).orElseThrow());
            assertEquals(2, service.freeSeatCount());
        }
        assertEquals(1, service.reserve("carol").orElseThrow().seatNumber());
        assertEquals(1, service.freeSeatCount());
    }

    @Test
    void bookingIdsAreUniqueAcrossEventsAndCannotCrossCancel() {
        BookingService firstEvent = new BookingService(1);
        BookingService secondEvent = new BookingService(1);
        BookingService.Booking first = firstEvent.reserve("alice").orElseThrow();
        BookingService.Booking second = secondEvent.reserve("alice").orElseThrow();

        assertNotEquals(first.bookingId(), second.bookingId());
        assertEquals(Optional.empty(), firstEvent.findBooking(second.bookingId()));
        assertEquals(Optional.empty(), secondEvent.findBooking(first.bookingId()));
        assertEquals(BookingService.CancelResult.UNKNOWN_BOOKING,
                secondEvent.cancel(first.bookingId(), "alice"));
        assertEquals(0, firstEvent.freeSeatCount());
        assertEquals(0, secondEvent.freeSeatCount());
    }

    @Test
    void failedCancellationPreservesAllActiveBookingsAndSeatAssignments() {
        BookingService service = new BookingService(4);
        List<BookingService.Booking> bookings = new ArrayList<>();
        bookings.add(reserve(service, "alice", 1, 4));
        bookings.add(reserve(service, "bob", 2, 3));
        bookings.add(reserve(service, "carol", 3, 2));

        assertEquals(BookingService.CancelResult.UNKNOWN_BOOKING, service.cancel(500, "alice"));
        assertEquals(BookingService.CancelResult.WRONG_CUSTOMER,
                service.cancel(bookings.get(1).bookingId(), "alice"));
        assertEquals(1, service.freeSeatCount());
        bookings.add(reserve(service, "dave", 4, 1));
        assertEquals(4, bookings.get(3).seatNumber());
        checkInvariants(service, bookings, 4);
    }

    @Test
    void manyBookingsKeepUniqueIdsAndOneActiveOwnerPerSeat() throws Exception {
        IsolatedTestProcess.run(BookingServiceTest.class,
                "runManyBookingsKeepUniqueIdsAndOneActiveOwnerPerSeat", 15);
    }

    @Test
    void exhaustivelyChecksShortOperationSequencesAgainstSeatModel() throws Exception {
        IsolatedTestProcess.run(BookingServiceTest.class,
                "runExhaustiveShortOperationSequencesAgainstSeatModel", 15);
    }

    void runExhaustiveShortOperationSequencesAgainstSeatModel() {
        // Enumerate every length-five sequence over reserve, valid cancel, wrong-owner cancel,
        // repeat-cancel, and unknown-cancel. A direct seat array is the independent oracle.
        int alphabetSize = 6;
        int sequenceLength = 5;
        int sequenceCount = (int) Math.pow(alphabetSize, sequenceLength);
        for (int encoded = 0; encoded < sequenceCount; encoded++) {
            int[] sequence = new int[sequenceLength];
            int value = encoded;
            for (int index = 0; index < sequenceLength; index++) {
                sequence[index] = value % alphabetSize;
                value /= alphabetSize;
            }
            checkAgainstModel(sequence, 3);
        }
    }

    @Test
    void seededLongOperationSequencesMatchSeatModel() throws Exception {
        IsolatedTestProcess.run(BookingServiceTest.class,
                "runSeededLongOperationSequencesMatchSeatModel", 15);
    }

    void runSeededLongOperationSequencesMatchSeatModel() {
        Random random = new Random(0x5EEDB00CL);
        for (int sequence = 0; sequence < 8; sequence++) {
            int[] operations = new int[500];
            for (int index = 0; index < operations.length; index++) {
                operations[index] = random.nextInt(5);
            }
            checkAgainstModel(operations, 17);
        }
    }

    @Test
    void largeEventAllocatesAndReusesLowestReleasedSeats() throws Exception {
        IsolatedTestProcess.run(BookingServiceTest.class,
                "runLargeEventAllocatesAndReusesLowestReleasedSeats", 15);
    }

    void runLargeEventAllocatesAndReusesLowestReleasedSeats() {
        int seatCount = 25_000;
        BookingService service = new BookingService(seatCount);
        List<BookingService.Booking> original = new ArrayList<>(seatCount);
        for (int seat = 1; seat <= seatCount; seat++) {
            original.add(reserve(service, "customer", seat, seatCount - seat + 1));
        }
        assertEquals(Optional.empty(), service.reserve("sold-out"));

        // Release every third seat in reverse order; allocation must still choose the minimum.
        for (int index = seatCount - 2; index >= 0; index -= 3) {
            BookingService.Booking booking = original.get(index);
            assertEquals(BookingService.CancelResult.CANCELLED,
                    service.cancel(booking.bookingId(), booking.customerId()));
        }
        for (int index = 2; index < seatCount; index += 3) {
            BookingService.Booking replacement = service.reserve("replacement").orElseThrow();
            assertEquals(index + 1, replacement.seatNumber());
            assertNotEquals(original.get(index).bookingId(), replacement.bookingId());
        }
        assertEquals(0, service.freeSeatCount());
    }

    private void checkAgainstModel(int[] operations, int seatCount) {
        BookingService service = new BookingService(seatCount);
        boolean[] occupied = new boolean[seatCount + 1];
        List<ModelBooking> history = new ArrayList<>();
        StringBuilder trace = new StringBuilder();
        for (int operation : operations) {
            trace.append(operation).append(',');
            switch (operation) {
                case 0 -> modelReserve(service, occupied, history, "alice", trace);
                case 1 -> modelReserve(service, occupied, history, "bob", trace);
                case 2 -> modelCancelFirstActive(service, occupied, history, false, trace);
                case 3 -> modelCancelFirstActive(service, occupied, history, true, trace);
                case 4 -> modelCancelFirstCancelled(service, history, trace);
                case 5 -> assertEquals(BookingService.CancelResult.UNKNOWN_BOOKING,
                        service.cancel(Long.MAX_VALUE, "alice"), trace.toString());
                default -> throw new AssertionError("unexpected operation=" + operation);
            }
            assertEquals(countFree(occupied), service.freeSeatCount(), "sequence=" + trace);
            for (ModelBooking expected : history) {
                BookingService.Booking actual = service.findBooking(expected.id).orElseThrow(
                        () -> new AssertionError("missing booking; sequence=" + trace));
                assertEquals(expected.customer, actual.customerId(), "sequence=" + trace);
                assertEquals(expected.seat, actual.seatNumber(), "sequence=" + trace);
                assertEquals(expected.active, actual.active(), "sequence=" + trace);
            }
        }
    }

    private void modelReserve(BookingService service, boolean[] occupied,
                             List<ModelBooking> history, String customer, StringBuilder trace) {
        int seat = 1;
        while (seat < occupied.length && occupied[seat]) seat++;
        Optional<BookingService.Booking> result = service.reserve(customer);
        if (seat == occupied.length) {
            assertEquals(Optional.empty(), result, "sequence=" + trace);
            return;
        }
        if (result.isEmpty()) {
            throw new AssertionError("expected seat=" + seat + "; sequence=" + trace);
        }
        BookingService.Booking booking = result.orElseThrow();
        assertEquals(seat, booking.seatNumber(), "sequence=" + trace);
        assertEquals(customer, booking.customerId(), "sequence=" + trace);
        assertTrue(booking.active(), "sequence=" + trace);
        assertTrue(history.stream().noneMatch(old -> old.id == booking.bookingId()),
                "booking ID reused; sequence=" + trace);
        occupied[seat] = true;
        history.add(new ModelBooking(booking.bookingId(), customer, seat, true));
    }

    private void modelCancelFirstActive(BookingService service, boolean[] occupied,
                                        List<ModelBooking> history, boolean wrongOwner,
                                        StringBuilder trace) {
        ModelBooking selected = history.stream().filter(booking -> booking.active).findFirst().orElse(null);
        if (selected == null) {
            assertEquals(BookingService.CancelResult.UNKNOWN_BOOKING,
                    service.cancel(1, "alice"), "sequence=" + trace);
            return;
        }
        if (wrongOwner) {
            String intruder = selected.customer.equals("alice") ? "bob" : "alice";
            assertEquals(BookingService.CancelResult.WRONG_CUSTOMER,
                    service.cancel(selected.id, intruder), "sequence=" + trace);
            return;
        }
        assertEquals(BookingService.CancelResult.CANCELLED,
                service.cancel(selected.id, selected.customer), "sequence=" + trace);
        selected.active = false;
        occupied[selected.seat] = false;
    }

    private void modelCancelFirstCancelled(BookingService service, List<ModelBooking> history,
                                          StringBuilder trace) {
        ModelBooking selected = history.stream().filter(booking -> !booking.active).findFirst().orElse(null);
        if (selected == null) {
            assertEquals(BookingService.CancelResult.UNKNOWN_BOOKING,
                    service.cancel(Long.MAX_VALUE, "alice"), "sequence=" + trace);
            return;
        }
        assertEquals(BookingService.CancelResult.ALREADY_CANCELLED,
                service.cancel(selected.id, selected.customer), "sequence=" + trace);
    }

    private int countFree(boolean[] occupied) {
        int free = 0;
        for (int seat = 1; seat < occupied.length; seat++) if (!occupied[seat]) free++;
        return free;
    }

    private static final class ModelBooking {
        private final long id;
        private final String customer;
        private final int seat;
        private boolean active;

        private ModelBooking(long id, String customer, int seat, boolean active) {
            this.id = id;
            this.customer = customer;
            this.seat = seat;
            this.active = active;
        }
    }

    void runManyBookingsKeepUniqueIdsAndOneActiveOwnerPerSeat() {
        int seatCount = 1000;
        BookingService service = new BookingService(seatCount);
        List<BookingService.Booking> bookings = new ArrayList<>();
        Set<Long> ids = new HashSet<>();
        for (int seat = 1; seat <= seatCount; seat++) {
            BookingService.Booking booking = reserve(service, "customer-" + (seat % 11), seat,
                    seatCount - seat + 1);
            assertTrue(ids.add(booking.bookingId()));
            bookings.add(booking);
        }
        assertEquals(Optional.empty(), service.reserve("sold-out"));
        checkInvariants(service, bookings, seatCount);

        for (int index = 0; index < bookings.size(); index += 2) {
            BookingService.Booking old = bookings.get(index);
            assertEquals(BookingService.CancelResult.CANCELLED,
                    service.cancel(old.bookingId(), old.customerId()));
        }
        for (int index = 0; index < seatCount / 2; index++) {
            BookingService.Booking replacement = reserve(service, "replacement-" + index,
                    index * 2 + 1, seatCount / 2 - index);
            assertTrue(ids.add(replacement.bookingId()));
            bookings.add(replacement);
        }
        checkInvariants(service, bookings, seatCount);
    }

    private BookingService.Booking reserve(BookingService service, String customerId,
                                           int expectedSeat, int expectedFreeCount) {
        BookingService.Booking booking = service.reserve(customerId).orElseThrow();
        assertEquals(customerId, booking.customerId());
        assertEquals(expectedSeat, booking.seatNumber());
        assertTrue(booking.active());
        assertEquals(expectedFreeCount - 1, service.freeSeatCount());
        return booking;
    }

    private void checkInvariants(BookingService service,
                                 List<BookingService.Booking> createdBookings, int seatCount) {
        int[] activeBookingsBySeat = new int[seatCount + 1];
        Set<Long> ids = new HashSet<>();
        for (BookingService.Booking created : createdBookings) {
            assertTrue(ids.add(created.bookingId()), "booking IDs must never be reused");
            BookingService.Booking latest = service.findBooking(created.bookingId()).orElseThrow();
            if (latest.active()) {
                assertTrue(latest.seatNumber() >= 1 && latest.seatNumber() <= seatCount);
                activeBookingsBySeat[latest.seatNumber()]++;
            }
        }

        int freeSeatsFromSnapshots = 0;
        for (int seat = 1; seat <= seatCount; seat++) {
            assertTrue(activeBookingsBySeat[seat] <= 1, "seat=" + seat + " has multiple active bookings");
            if (activeBookingsBySeat[seat] == 0) {
                freeSeatsFromSnapshots++;
            }
        }
        assertEquals(freeSeatsFromSnapshots, service.freeSeatCount());
    }
}
