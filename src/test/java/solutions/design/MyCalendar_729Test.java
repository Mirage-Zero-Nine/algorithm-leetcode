package solutions.design;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Test;

/**
 * Stateful contract tests for the half-open, single-booking calendar.
 *
 * <p>The reference model deliberately uses a list rather than the production
 * {@code TreeMap}; this independently checks both the predecessor lookup and
 * the rule that a rejected booking must not change future results.</p>
 */
public class MyCalendar_729Test {

    @Test
    public void officialExampleUsesHalfOpenIntervals() {
        MyCalendar_729 calendar = new MyCalendar_729();

        assertTrue(calendar.book(10, 20));
        assertFalse(calendar.book(15, 25));
        assertTrue(calendar.book(20, 30));
    }

    @Test
    public void firstEventCanStartAtLowerConstraintBoundary() {
        MyCalendar_729 calendar = new MyCalendar_729();

        assertTrue(calendar.book(0, 1));
        assertFalse(calendar.book(0, 1));
        assertTrue(calendar.book(1, 2));
    }

    @Test
    public void eventCanEndAtUpperConstraintBoundary() {
        MyCalendar_729 calendar = new MyCalendar_729();

        assertTrue(calendar.book(999_999_998, 1_000_000_000));
        assertFalse(calendar.book(999_999_999, 1_000_000_000));
        assertTrue(calendar.book(999_999_997, 999_999_998));
    }

    @Test
    public void adjacentEventsAreBothAccepted() {
        MyCalendar_729 calendar = new MyCalendar_729();

        assertTrue(calendar.book(0, 10));
        assertTrue(calendar.book(10, 20));
        assertTrue(calendar.book(20, 30));
        assertTrue(calendar.book(30, 31));
    }

    @Test
    public void overlapAtExistingStartIsRejected() {
        MyCalendar_729 calendar = new MyCalendar_729();

        assertTrue(calendar.book(10, 20));
        assertFalse(calendar.book(5, 11));
    }

    @Test
    public void overlapAtExistingEndIsRejected() {
        MyCalendar_729 calendar = new MyCalendar_729();

        assertTrue(calendar.book(10, 20));
        assertFalse(calendar.book(19, 30));
    }

    @Test
    public void containedEventIsRejected() {
        MyCalendar_729 calendar = new MyCalendar_729();

        assertTrue(calendar.book(10, 30));
        assertFalse(calendar.book(15, 25));
    }

    @Test
    public void coveringEventIsRejected() {
        MyCalendar_729 calendar = new MyCalendar_729();

        assertTrue(calendar.book(15, 25));
        assertFalse(calendar.book(10, 30));
    }

    @Test
    public void exactDuplicateIsRejected() {
        MyCalendar_729 calendar = new MyCalendar_729();

        assertTrue(calendar.book(1, 5));
        assertFalse(calendar.book(1, 5));
        assertFalse(calendar.book(1, 5));
    }

    @Test
    public void rejectedBookingDoesNotChangeFutureAvailability() {
        MyCalendar_729 calendar = new MyCalendar_729();

        assertTrue(calendar.book(10, 20));
        assertFalse(calendar.book(15, 25));
        // This remains available only if the rejected [15,25) was not stored.
        assertTrue(calendar.book(20, 30));
        assertTrue(calendar.book(30, 40));
        assertFalse(calendar.book(15, 20));
    }

    @Test
    public void eventCanBeInsertedBetweenExistingEvents() {
        MyCalendar_729 calendar = new MyCalendar_729();

        assertTrue(calendar.book(0, 5));
        assertTrue(calendar.book(10, 15));
        assertTrue(calendar.book(5, 10));
        assertFalse(calendar.book(4, 6));
        assertFalse(calendar.book(9, 11));
    }

    @Test
    public void oneCandidateOverlappingEitherOfSeveralEventsIsRejected() {
        MyCalendar_729 calendar = new MyCalendar_729();

        assertTrue(calendar.book(10, 20));
        assertTrue(calendar.book(30, 40));
        assertFalse(calendar.book(15, 31));
        assertFalse(calendar.book(29, 35));
        assertTrue(calendar.book(20, 30));
    }

    @Test
    public void outOfOrderNonOverlappingBookingsAreAccepted() {
        MyCalendar_729 calendar = new MyCalendar_729();

        assertTrue(calendar.book(40, 50));
        assertTrue(calendar.book(0, 10));
        assertTrue(calendar.book(20, 30));
        assertTrue(calendar.book(10, 20));
        assertTrue(calendar.book(30, 40));
    }

    @Test
    public void singleUnitIntervalsRespectBothAdjacentEndpoints() {
        MyCalendar_729 calendar = new MyCalendar_729();

        assertTrue(calendar.book(5, 6));
        assertTrue(calendar.book(4, 5));
        assertTrue(calendar.book(6, 7));
        assertFalse(calendar.book(5, 6));
        assertFalse(calendar.book(4, 6));
    }

    @Test
    public void narrowGapCanBeBookedExactlyOnce() {
        MyCalendar_729 calendar = new MyCalendar_729();

        assertTrue(calendar.book(0, 10));
        assertTrue(calendar.book(20, 30));
        assertTrue(calendar.book(10, 20));
        assertFalse(calendar.book(9, 11));
        assertFalse(calendar.book(19, 21));
    }

    @Test
    public void sameInstanceRetainsAllAcceptedBookingsAcrossCalls() {
        MyCalendar_729 calendar = new MyCalendar_729();

        assertTrue(calendar.book(100, 200));
        assertTrue(calendar.book(0, 50));
        assertTrue(calendar.book(250, 300));
        assertFalse(calendar.book(50, 101));
        assertFalse(calendar.book(199, 250));
        assertTrue(calendar.book(200, 250));
    }

    @Test
    public void separateInstancesDoNotShareBookings() {
        MyCalendar_729 first = new MyCalendar_729();
        MyCalendar_729 second = new MyCalendar_729();

        assertTrue(first.book(10, 20));
        assertFalse(first.book(15, 16));
        assertTrue(second.book(15, 16));
        assertTrue(second.book(10, 15));
        assertFalse(first.book(10, 20));
    }

    @Test
    public void boundariesCanBeBookedInSeparateAdjacentSegments() {
        MyCalendar_729 calendar = new MyCalendar_729();

        assertTrue(calendar.book(0, 1));
        assertTrue(calendar.book(1, 1_000_000_000));
        assertFalse(calendar.book(0, 1_000_000_000));
    }

    @Test
    public void deterministicSmallDomainSequenceMatchesIndependentOracle() {
        MyCalendar_729 calendar = new MyCalendar_729();
        List<int[]> accepted = new ArrayList<>();
        Random random = new Random(0x7295EEDL);

        for (int call = 0; call < 400; call++) {
            int start = random.nextInt(80);
            int end = start + 1 + random.nextInt(80 - start);
            assertBookingMatchesOracle(calendar, accepted, start, end, call);
        }
    }

    @Test
    public void deterministicWideDomainSequenceMatchesIndependentOracle() {
        MyCalendar_729 calendar = new MyCalendar_729();
        List<int[]> accepted = new ArrayList<>();
        Random random = new Random(0x729C0DEL);

        for (int call = 0; call < 200; call++) {
            int start = random.nextInt(1_000_000_000);
            int end = start + 1 + random.nextInt(1_000_000_000 - start);
            assertBookingMatchesOracle(calendar, accepted, start, end, call);
        }
    }

    @Test
    public void maximumLegalCallCountMatchesIndependentOracle() {
        MyCalendar_729 calendar = new MyCalendar_729();
        List<int[]> accepted = new ArrayList<>();

        for (int call = 0; call < 1_000; call++) {
            int start = (call % 2 == 0) ? call : call - 1;
            int end = start + 1;
            assertBookingMatchesOracle(calendar, accepted, start, end, call);
        }
    }

    @Test
    public void everySmallIntervalSequenceUsesHalfOpenOverlapRule() {
        MyCalendar_729 calendar = new MyCalendar_729();
        List<int[]> accepted = new ArrayList<>();

        for (int start = 0; start < 12; start++) {
            for (int end = start + 1; end <= 12; end++) {
                assertBookingMatchesOracle(calendar, accepted, start, end,
                        start + ":" + end);
            }
        }
    }

    @Test
    public void repeatedRejectedBookingsNeverBecomeAccepted() {
        MyCalendar_729 calendar = new MyCalendar_729();
        List<int[]> accepted = new ArrayList<>();

        assertBookingMatchesOracle(calendar, accepted, 10, 20, "initial");
        for (int attempt = 0; attempt < 20; attempt++) {
            assertBookingMatchesOracle(calendar, accepted, 11, 19, "contained-" + attempt);
            assertBookingMatchesOracle(calendar, accepted, 10, 20, "duplicate-" + attempt);
        }
        assertBookingMatchesOracle(calendar, accepted, 20, 21, "adjacent-after-rejections");
    }

    @Test
    public void acceptedIntervalsRemainDisjointAfterManyOutOfOrderCalls() {
        MyCalendar_729 calendar = new MyCalendar_729();
        List<int[]> accepted = new ArrayList<>();
        int[][] calls = {
                {90, 100}, {0, 10}, {40, 50}, {70, 80}, {20, 30},
                {10, 20}, {80, 90}, {50, 60}, {30, 40}, {60, 70}
        };

        for (int i = 0; i < calls.length; i++) {
            assertBookingMatchesOracle(calendar, accepted, calls[i][0], calls[i][1], i);
        }
        assertEquals(10, accepted.size());
    }

    @Test
    public void boundaryAdjacentBookingAfterRejectedCoveringEventStillSucceeds() {
        MyCalendar_729 calendar = new MyCalendar_729();

        assertTrue(calendar.book(100, 200));
        assertFalse(calendar.book(50, 250));
        assertTrue(calendar.book(0, 100));
        assertTrue(calendar.book(200, 1_000_000_000));
    }

    @Test
    public void distinctAcceptedIntervalsDoNotDependOnInsertionOrder() {
        MyCalendar_729 ascending = new MyCalendar_729();
        MyCalendar_729 descending = new MyCalendar_729();
        int[][] intervals = {{0, 10}, {10, 20}, {20, 30}, {30, 40}, {40, 50}};

        for (int[] interval : intervals) {
            assertTrue(ascending.book(interval[0], interval[1]));
        }
        for (int i = intervals.length - 1; i >= 0; i--) {
            assertTrue(descending.book(intervals[i][0], intervals[i][1]));
        }
        assertFalse(ascending.book(9, 11));
        assertFalse(descending.book(9, 11));
    }

    private static void assertBookingMatchesOracle(
            MyCalendar_729 calendar, List<int[]> accepted, int start, int end, Object label) {
        boolean expected = bookInOracle(accepted, start, end);
        assertEquals(expected, calendar.book(start, end),
                "call " + label + " = [" + start + ", " + end + ")");
    }

    private static boolean bookInOracle(List<int[]> accepted, int start, int end) {
        for (int[] interval : accepted) {
            if (interval[0] < end && start < interval[1]) {
                return false;
            }
        }
        accepted.add(new int[] {start, end});
        return true;
    }
}
