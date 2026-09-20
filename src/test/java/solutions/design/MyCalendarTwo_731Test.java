package solutions.design;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Test;

/**
 * Contract tests for the stateful half-open interval calendar.
 *
 * <p>The small stateful scenarios use explicit expected values. Generated scenarios use a
 * deliberately independent list-of-accepted-intervals oracle: a proposed interval is rejected
 * exactly when it intersects the overlap of any two already accepted intervals. This avoids
 * reproducing the production sweep-line implementation in the test.
 */
public class MyCalendarTwo_731Test {

    @Test
    public void officialExamplePreservesRejectedBooking() {
        MyCalendarTwo_731 calendar = new MyCalendarTwo_731();
        assertTrue(calendar.book(10, 20));
        assertTrue(calendar.book(50, 60));
        assertTrue(calendar.book(10, 40));
        assertFalse(calendar.book(5, 15));
        assertTrue(calendar.book(5, 10));
        assertTrue(calendar.book(25, 55));
    }

    @Test
    public void duplicateIntervalsAllowTwoButRejectThird() {
        MyCalendarTwo_731 calendar = new MyCalendarTwo_731();
        assertTrue(calendar.book(1, 5));
        assertTrue(calendar.book(1, 5));
        assertFalse(calendar.book(1, 5));
        assertFalse(calendar.book(2, 4));
    }

    @Test
    public void nestedIntervalsReachTripleOnlyInCommonInterior() {
        MyCalendarTwo_731 calendar = new MyCalendarTwo_731();
        assertTrue(calendar.book(0, 100));
        assertTrue(calendar.book(20, 80));
        assertFalse(calendar.book(40, 60));
        assertTrue(calendar.book(80, 100));
        assertTrue(calendar.book(0, 20));
    }

    @Test
    public void halfOpenEndpointsDoNotOverlap() {
        MyCalendarTwo_731 calendar = new MyCalendarTwo_731();
        assertTrue(calendar.book(0, 10));
        assertTrue(calendar.book(10, 20));
        assertTrue(calendar.book(20, 30));
        assertTrue(calendar.book(0, 10));
        assertTrue(calendar.book(10, 20));
        assertFalse(calendar.book(5, 15));
    }

    @Test
    public void endpointTouchingDoesNotCreateTripleBooking() {
        MyCalendarTwo_731 calendar = new MyCalendarTwo_731();
        assertTrue(calendar.book(0, 10));
        assertTrue(calendar.book(0, 10));
        assertTrue(calendar.book(10, 20));
        assertTrue(calendar.book(10, 20));
        assertFalse(calendar.book(5, 15));
    }

    @Test
    public void partialOverlapTripleIsRejected() {
        MyCalendarTwo_731 calendar = new MyCalendarTwo_731();
        assertTrue(calendar.book(0, 30));
        assertTrue(calendar.book(10, 20));
        assertFalse(calendar.book(15, 25));
        assertTrue(calendar.book(20, 30));
    }

    @Test
    public void rejectedBookingIsRolledBackBeforeLaterBooking() {
        MyCalendarTwo_731 calendar = new MyCalendarTwo_731();
        assertTrue(calendar.book(0, 10));
        assertTrue(calendar.book(0, 10));
        assertFalse(calendar.book(0, 10));
        assertTrue(calendar.book(10, 20));
        assertTrue(calendar.book(10, 15));
        assertFalse(calendar.book(5, 11));
    }

    @Test
    public void rejectedLongIntervalDoesNotPoisonSeparatedRegions() {
        MyCalendarTwo_731 calendar = new MyCalendarTwo_731();
        assertTrue(calendar.book(0, 10));
        assertTrue(calendar.book(0, 10));
        assertTrue(calendar.book(20, 30));
        assertTrue(calendar.book(20, 30));
        assertFalse(calendar.book(0, 30));
        assertTrue(calendar.book(10, 20));
        assertTrue(calendar.book(30, 40));
    }

    @Test
    public void candidateCanSpanDifferentDoubleBookedRegions() {
        MyCalendarTwo_731 calendar = new MyCalendarTwo_731();
        assertTrue(calendar.book(0, 10));
        assertTrue(calendar.book(0, 10));
        assertTrue(calendar.book(20, 30));
        assertTrue(calendar.book(20, 30));
        assertTrue(calendar.book(10, 20));
        assertFalse(calendar.book(0, 30));
    }

    @Test
    public void disjointAndAdjacentBookingsRemainIndependent() {
        MyCalendarTwo_731 calendar = new MyCalendarTwo_731();
        assertTrue(calendar.book(0, 2));
        assertTrue(calendar.book(2, 4));
        assertTrue(calendar.book(4, 6));
        assertTrue(calendar.book(10, 12));
        assertTrue(calendar.book(12, 14));
        assertTrue(calendar.book(0, 2));
        assertFalse(calendar.book(1, 5));
    }

    @Test
    public void noOverlapAndSingleBookingsAreAccepted() {
        MyCalendarTwo_731 calendar = new MyCalendarTwo_731();
        assertTrue(calendar.book(3, 4));
        assertTrue(calendar.book(8, 12));
        assertTrue(calendar.book(15, 16));
        assertTrue(calendar.book(100, 200));
        assertTrue(calendar.book(0, 1));
        assertTrue(calendar.book(4, 8));
    }

    @Test
    public void verySmallIntervalsAndZeroBoundaryWork() {
        MyCalendarTwo_731 calendar = new MyCalendarTwo_731();
        assertTrue(calendar.book(0, 1));
        assertTrue(calendar.book(0, 1));
        assertFalse(calendar.book(0, 1));
        assertTrue(calendar.book(1, 2));
        assertTrue(calendar.book(1, 2));
        assertFalse(calendar.book(0, 2));
    }

    @Test
    public void maximumCoordinateBoundaryIsHalfOpen() {
        MyCalendarTwo_731 calendar = new MyCalendarTwo_731();
        assertTrue(calendar.book(0, 1_000_000_000));
        assertTrue(calendar.book(999_999_999, 1_000_000_000));
        assertFalse(calendar.book(999_999_999, 1_000_000_000));
        assertTrue(calendar.book(0, 1));
    }

    @Test
    public void tripleAtTheBeginningAndEndIsRejected() {
        MyCalendarTwo_731 calendar = new MyCalendarTwo_731();
        assertTrue(calendar.book(10, 20));
        assertTrue(calendar.book(10, 30));
        assertFalse(calendar.book(0, 11));
        assertTrue(calendar.book(30, 40));
        assertTrue(calendar.book(20, 30));
        assertFalse(calendar.book(19, 31));
    }

    @Test
    public void acceptedEventsMayOverlapInSeveralWays() {
        MyCalendarTwo_731 calendar = new MyCalendarTwo_731();
        assertTrue(calendar.book(0, 100));
        assertTrue(calendar.book(0, 50));
        assertTrue(calendar.book(50, 100));
        assertFalse(calendar.book(25, 50));
        assertFalse(calendar.book(50, 75));
        assertTrue(calendar.book(100, 125));
        assertFalse(calendar.book(49, 51));
    }

    @Test
    public void repeatedCallsAfterARejectedAttemptRemainDeterministic() {
        MyCalendarTwo_731 calendar = new MyCalendarTwo_731();
        assertTrue(calendar.book(10, 20));
        assertTrue(calendar.book(15, 25));
        assertFalse(calendar.book(12, 18));
        assertFalse(calendar.book(12, 18));
        assertTrue(calendar.book(20, 30));
        assertFalse(calendar.book(19, 21));
    }

    @Test
    public void independentCalendarsDoNotShareState() {
        MyCalendarTwo_731 first = new MyCalendarTwo_731();
        MyCalendarTwo_731 second = new MyCalendarTwo_731();
        assertTrue(first.book(0, 10));
        assertTrue(first.book(0, 10));
        assertFalse(first.book(0, 10));
        assertTrue(second.book(0, 10));
        assertTrue(second.book(0, 10));
        assertFalse(second.book(0, 10));
        assertTrue(first.book(10, 20));
        assertTrue(second.book(20, 30));
    }

    @Test
    public void officialMaximumCallCountWithDisjointDoubleBookings() {
        MyCalendarTwo_731 calendar = new MyCalendarTwo_731();
        for (int i = 0; i < 500; i++) {
            int start = 2 * i;
            assertTrue(calendar.book(start, start + 1));
        }
        for (int i = 0; i < 500; i++) {
            int start = 2 * i;
            assertTrue(calendar.book(start, start + 1));
        }
    }

    @Test
    public void deterministicOracleChecksSmallCoordinateSequences() {
        int[][][] sequences = {
            {{0, 3}, {1, 4}, {2, 5}, {5, 7}, {3, 6}, {0, 1}},
            {{0, 6}, {0, 2}, {2, 4}, {4, 6}, {1, 5}, {6, 9}},
            {{1, 2}, {1, 3}, {2, 5}, {3, 4}, {0, 10}, {5, 8}},
            {{0, 10}, {3, 7}, {3, 7}, {7, 9}, {0, 3}, {6, 10}},
            {{2, 8}, {0, 4}, {4, 9}, {1, 7}, {8, 10}, {0, 10}}
        };
        for (int[][] sequence : sequences) {
            assertMatchesOracle(sequence);
        }
    }

    @Test
    public void seededOracleChecksMixedOrderingAndRollback() {
        Random random = new Random(731_2026L);
        for (int sequence = 0; sequence < 20; sequence++) {
            int[][] bookings = new int[50][2];
            for (int i = 0; i < bookings.length; i++) {
                int start = random.nextInt(80);
                bookings[i][0] = start;
                bookings[i][1] = start + 1 + random.nextInt(20);
            }
            assertMatchesOracle(bookings);
        }
    }

    @Test
    public void seededOracleChecksLongValidCallStream() {
        Random random = new Random(73_1000L);
        int[][] bookings = new int[1000][2];
        for (int i = 0; i < bookings.length; i++) {
            int start = random.nextInt(1_000_000);
            bookings[i][0] = start;
            bookings[i][1] = start + 1 + random.nextInt(10_000);
        }
        assertMatchesOracle(bookings);
    }

    @Test
    public void exhaustiveShortSequencesMatchIndependentOracle() {
        List<int[]> intervals = new ArrayList<>();
        for (int start = 0; start < 4; start++) {
            for (int end = start + 1; end <= 4; end++) {
                intervals.add(new int[] {start, end});
            }
        }
        for (int offset = 0; offset < intervals.size(); offset++) {
            int[][] sequence = new int[8][2];
            for (int i = 0; i < sequence.length; i++) {
                int[] interval = intervals.get((offset + i * 3) % intervals.size());
                sequence[i] = interval.clone();
            }
            assertMatchesOracle(sequence);
        }
    }

    @Test
    public void oracleHandlesIntervalsAtBothOfficialBounds() {
        int max = 1_000_000_000;
        assertMatchesOracle(new int[][] {
            {0, 1}, {max - 1, max}, {0, max}, {0, 1}, {max - 1, max}, {1, max - 1}
        });
    }

    @Test
    public void oracleChecksManyNestedIntervals() {
        int[][] bookings = new int[40][2];
        for (int i = 0; i < bookings.length; i++) {
            bookings[i][0] = i;
            bookings[i][1] = 80 - i;
        }
        assertMatchesOracle(bookings);
    }

    @Test
    public void oracleChecksManyAdjacentDoubleBookings() {
        int[][] bookings = new int[80][2];
        for (int i = 0; i < 40; i++) {
            bookings[2 * i] = new int[] {i * 2, i * 2 + 2};
            bookings[2 * i + 1] = new int[] {i * 2, i * 2 + 2};
        }
        assertMatchesOracle(bookings);
    }

    private static void assertMatchesOracle(int[][] bookings) {
        MyCalendarTwo_731 calendar = new MyCalendarTwo_731();
        List<int[]> accepted = new ArrayList<>();
        for (int i = 0; i < bookings.length; i++) {
            int start = bookings[i][0];
            int end = bookings[i][1];
            boolean expected = canAccept(accepted, start, end);
            boolean actual = calendar.book(start, end);
            assertEquals(expected, actual, "booking index " + i + " = [" + start + ", " + end + ")");
            if (expected) {
                accepted.add(new int[] {start, end});
            }
        }
    }

    /** Returns false when the candidate intersects any pairwise overlap of accepted events. */
    private static boolean canAccept(List<int[]> accepted, int start, int end) {
        for (int i = 0; i < accepted.size(); i++) {
            for (int j = i + 1; j < accepted.size(); j++) {
                int overlapStart = Math.max(accepted.get(i)[0], accepted.get(j)[0]);
                int overlapEnd = Math.min(accepted.get(i)[1], accepted.get(j)[1]);
                if (Math.max(start, overlapStart) < Math.min(end, overlapEnd)) {
                    return false;
                }
            }
        }
        return true;
    }
}
